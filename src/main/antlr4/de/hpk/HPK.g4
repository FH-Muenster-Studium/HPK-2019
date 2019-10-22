grammar HPK;
// Leerzeichen, Tabs und Zeilenumbrüche ignorieren
WHITESPACE: [ \t\n\r]+ -> skip;

// Eine Zahl besteht aus hintereinander geschriebeneden Zahlen von 0 bis 9 oder aus 2 hintereinander
// geschriebeneden Zahlen von 0 bis 9 die mit einem Punkt getrennt sind oder die Zahl beginnt mit
// einem Punkt und endet mit hintereinander geschriebeneden Zahlen von 0 bis 9
NUMBER: ([0-9]+ ([.] [0-9]+)?) | ([.] [0-9]+);

MODULO: ('mod' | '%');
E: ('e' | 'E');

// Ein Buchstable ist ein Groß- oder Kleinbuchstabe von a bis z Eine Variable beginnt stets mit
// hintereinander geschriebenden Buchstaben, diese können auch Zahlen beinhalten und auch mit einer
// Zahl enden
VARIABLE: ('a' ..'z' | 'A' ..'Z')+ NUMBER* VARIABLE*;

POW: ('^' | '**');
PLUS: '+';
MINUS: '-';
MULTIPLY: '*';
DIVIDE: '/';
LEFT_BRACKET: '(';
RIGHT_BRACKET: ')';
ASSIGN: '=';
SEPARATOR: ',';

// Root besteht aus hintereinanderausführenden Statements jeweils mit ';' getrennt
root: statement (';' statement)* EOF;

// Ein statement ist eine expression, eine Funktionsdefinition oder die Festlegung einer Variable
statement: (expression | assignment | functionDefinition);

// Festlegung einer Variable e.g. x = (2^(1/2))^(1/2)
assignment: VARIABLE ASSIGN expression;

// Funktionsdefinition e.g. y(x) = x + 2
functionDefinition:
	name = VARIABLE LEFT_BRACKET (VARIABLE (SEPARATOR VARIABLE)*) RIGHT_BRACKET ASSIGN expression;

// Funktionsaufruf e.g. y(5)
functionCall:
	name = VARIABLE LEFT_BRACKET (
		expression (SEPARATOR expression)*
	) RIGHT_BRACKET;

// Klammerregel e.g. -(5 * 7)
//bracketExpression:
//	sign = MINUS? LEFT_BRACKET expression RIGHT_BRACKET;

// Zahl kann positiv oder negativ deklariert sein e.g. -5; +5
//numberExpression: sign = (MINUS | PLUS)? NUMBER;

// Eine Variable kann positiv oder negativ deklariert sein e.g. -x; +x
//variableExpression: sign = (MINUS | PLUS)? VARIABLE;

// <assoc = right> meint das der operator rechts associativ ist, so dass 1^2^3 als 1^(2^3) geparsed wird anstelle von (1^2)^3 https://de.wikipedia.org/wiki/Operatorassoziativit%C3%A4t

expression:
	sign = MINUS? LEFT_BRACKET expression RIGHT_BRACKET													# Bracket
	| left = expression E right = expression							# Tiny
	| <assoc = right> base = expression POW exponent = expression		# Power
	| dividend = expression DIVIDE divisor = expression					# Division
	| firstFactor = expression MULTIPLY lastFactor = expression			# Multiplication
	| <assoc = right> number = expression MODULO quotient = expression	# Modulo
	| minuend = expression MINUS subtrahend = expression				# Subtraction
	| firstSummand = expression PLUS lastSummand = expression			# Addition
	| functionCall														# FunctionCaller
	| sign = (MINUS | PLUS)? NUMBER										# Number
	| sign = (MINUS | PLUS)? VARIABLE									# Variable
	;
