package de.hpk;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HPKParserTest {

    private static HPKParser parser;

    private static class DummyVisitor implements ParseTreeVisitor<Double> {
        @Override
        public Double visit(ParseTree parseTree) {
            return null;
        }

        @Override
        public Double visitChildren(RuleNode ruleNode) {
            return null;
        }

        @Override
        public Double visitTerminal(TerminalNode terminalNode) {
            return null;
        }

        @Override
        public Double visitErrorNode(ErrorNode errorNode) {
            return null;
        }
    }

    @BeforeClass
    public static void setUp() {
        HPKLexer lexer = new HPKLexer(CharStreams.fromString("5 + 7"));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser = new HPKParser(tokens);
    }

    @Test
    public void testGrammarFileName() {
        Assert.assertEquals("HPK.g4", parser.getGrammarFileName());
        Assert.assertNotNull(parser.getRuleNames());
        Assert.assertNotNull(parser.getSerializedATN());
        HPKParser.StatementContext statementContext = new HPKParser.StatementContext(null, 0);
        Assert.assertNull(statementContext.assignment());
        Assert.assertNull(statementContext.expression());
        Assert.assertNull(statementContext.functionDefinition());
        Assert.assertEquals(HPKParser.RULE_statement, new HPKParser.StatementContext(null, 0).getRuleIndex());
        Assert.assertNull(statementContext.accept(new DummyVisitor()));
        HPKParser.RootContext rootContext = new HPKParser.RootContext(null, 0);
        Assert.assertNull(rootContext.statement(0));
        Assert.assertNull(rootContext.EOF());
        Assert.assertEquals(HPKParser.RULE_root, rootContext.getRuleIndex());
        Assert.assertNull(rootContext.accept(new DummyVisitor()));
        Assert.assertNotNull(parser.root());

        HPKLexer lexer = new HPKLexer(CharStreams.fromString("$"));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HPKParser errorParser = new HPKParser(tokens);
        errorParser.removeErrorListeners();
        errorParser.setErrorHandler(new ANTLRErrorStrategy() {
            @Override
            public void reset(Parser parser) {

            }

            @Override
            public Token recoverInline(Parser parser) throws RecognitionException {
                return null;
            }

            @Override
            public void recover(Parser parser, RecognitionException e) throws RecognitionException {

            }

            @Override
            public void sync(Parser parser) throws RecognitionException {

            }

            @Override
            public boolean inErrorRecoveryMode(Parser parser) {
                return false;
            }

            @Override
            public void reportMatch(Parser parser) {

            }

            @Override
            public void reportError(Parser parser, RecognitionException e) {

            }
        });
        errorParser.root();

        errorParser.sempred(null, 5, 0);
        errorParser.sempred(null, 5, 1);
        errorParser.sempred(null, 5, 2);
        errorParser.sempred(null, 5, 3);
        errorParser.sempred(null, 5, 4);
        errorParser.sempred(null, 5, 5);
        errorParser.sempred(null, 5, 6);
        Assert.assertTrue(errorParser.sempred(null, 5, 7));
        Assert.assertTrue(errorParser.sempred(null, 6, 0));
    }

    @Test
    public void TestFunction() {
        HPKLexer lexer = new HPKLexer(CharStreams.fromString("y(x;y)= 5"));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HPKParser errorParser = new HPKParser(tokens);
        errorParser.removeErrorListeners();
        errorParser.functionDefinition();
    }

    @Test
    public void TestRoot() {
        HPKLexer lexer = new HPKLexer(CharStreams.fromString(";;,;"));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HPKParser errorParser = new HPKParser(tokens);
        errorParser.removeErrorListeners();
        errorParser.root();
    }

    @Test
    public void TestExpression() {
        HPKLexer lexer = new HPKLexer(CharStreams.fromString(";;,;"));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HPKParser errorParser = new HPKParser(tokens);
        errorParser.removeErrorListeners();
        errorParser.expression();
    }

    @Test
    public void TestAssignment() {
        HPKLexer lexer = new HPKLexer(CharStreams.fromString(";;,;"));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HPKParser errorParser = new HPKParser(tokens);
        errorParser.removeErrorListeners();
        errorParser.assignment();

        HPKParser.AssignmentContext rootContext = new HPKParser.AssignmentContext(null, 0);
        Assert.assertNull(rootContext.ASSIGN());
        Assert.assertEquals(HPKParser.RULE_assignment, rootContext.getRuleIndex());
        Assert.assertNull(rootContext.accept(new DummyVisitor()));
    }

    @Test
    public void TestFunctionDefinitionContext() {
        HPKParser.FunctionDefinitionContext functionDefinitionContext = new HPKParser.FunctionDefinitionContext(null, 0);
        Assert.assertNull(functionDefinitionContext.LEFT_BRACKET());
        Assert.assertNull(functionDefinitionContext.RIGHT_BRACKET());
        Assert.assertNull(functionDefinitionContext.ASSIGN());
        Assert.assertNull(functionDefinitionContext.VARIABLE(0));
        Assert.assertNotNull(functionDefinitionContext.SEPARATOR());
        Assert.assertNull(functionDefinitionContext.SEPARATOR(0));
        Assert.assertEquals(HPKParser.RULE_functionDefinition, functionDefinitionContext.getRuleIndex());
        Assert.assertNull(functionDefinitionContext.accept(new DummyVisitor()));
    }

    @Test
    public void TestFunctionCallContext() {
        HPKParser.FunctionCallContext functionCallContext = new HPKParser.FunctionCallContext(null, 0);
        Assert.assertNull(functionCallContext.LEFT_BRACKET());
        Assert.assertNull(functionCallContext.RIGHT_BRACKET());
        Assert.assertNull(functionCallContext.VARIABLE());
        Assert.assertNotNull(functionCallContext.SEPARATOR());
        Assert.assertNull(functionCallContext.expression(0));
        Assert.assertNull(functionCallContext.SEPARATOR(0));
        Assert.assertEquals(HPKParser.RULE_functionCall, functionCallContext.getRuleIndex());
        Assert.assertNull(functionCallContext.accept(new DummyVisitor()));

        HPKLexer lexer = new HPKLexer(CharStreams.fromString(";;,;"));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HPKParser errorParser = new HPKParser(tokens);
        errorParser.removeErrorListeners();
        errorParser.functionCall();
    }

    @Test
    public void TestExpressionContext() {
        HPKParser.ExpressionContext expressionContext = new HPKParser.ExpressionContext(null, 0);
        Assert.assertEquals(HPKParser.RULE_expression, expressionContext.getRuleIndex());
    }

    @Test
    public void TestBracketContext() {
        HPKParser.ExpressionContext expressionContext = new HPKParser.ExpressionContext(null, 0);
        HPKParser.BracketContext bracketContext = new HPKParser.BracketContext(expressionContext);
        Assert.assertNull(bracketContext.LEFT_BRACKET());
        Assert.assertNull(bracketContext.RIGHT_BRACKET());
        Assert.assertNull(bracketContext.accept(new DummyVisitor()));
    }

    @Test
    public void TestMultiplicationContext() {
        HPKParser.ExpressionContext expressionContext = new HPKParser.ExpressionContext(null, 0);
        HPKParser.MultiplicationContext multiplicationContext = new HPKParser.MultiplicationContext(expressionContext);
        Assert.assertNull(multiplicationContext.MULTIPLY());
        Assert.assertEquals(0, multiplicationContext.expression().size());
        Assert.assertNull(multiplicationContext.expression(0));
        Assert.assertNull(multiplicationContext.accept(new DummyVisitor()));
    }

    @Test
    public void TestAdditionContext() {
        HPKParser.ExpressionContext expressionContext = new HPKParser.ExpressionContext(null, 0);
        HPKParser.AdditionContext additionContext = new HPKParser.AdditionContext(expressionContext);
        Assert.assertNull(additionContext.PLUS());
        Assert.assertEquals(0, additionContext.expression().size());
        Assert.assertNull(additionContext.expression(0));
        Assert.assertNull(additionContext.accept(new DummyVisitor()));
    }

    @Test
    public void TestVariableContext() {
        HPKParser.ExpressionContext expressionContext = new HPKParser.ExpressionContext(null, 0);
        HPKParser.VariableContext variableContext = new HPKParser.VariableContext(expressionContext);
        Assert.assertNull(variableContext.PLUS());
        Assert.assertNull(variableContext.accept(new DummyVisitor()));
    }

    @Test
    public void TestSubtractionContext() {
        HPKParser.ExpressionContext expressionContext = new HPKParser.ExpressionContext(null, 0);
        HPKParser.SubtractionContext subtractionContext = new HPKParser.SubtractionContext(expressionContext);
        Assert.assertNull(subtractionContext.MINUS());
        Assert.assertEquals(0, subtractionContext.expression().size());
        Assert.assertNull(subtractionContext.expression(0));
        Assert.assertNull(subtractionContext.accept(new DummyVisitor()));
    }

    @Test
    public void TestNumberContext() {
        HPKParser.ExpressionContext expressionContext = new HPKParser.ExpressionContext(null, 0);
        HPKParser.NumberContext numberContext = new HPKParser.NumberContext(expressionContext);
        Assert.assertNull(numberContext.PLUS());
        Assert.assertNull(numberContext.accept(new DummyVisitor()));
    }

    @Test
    public void TestModuloContext() {
        HPKParser.ExpressionContext expressionContext = new HPKParser.ExpressionContext(null, 0);
        HPKParser.ModuloContext moduloContext = new HPKParser.ModuloContext(expressionContext);
        Assert.assertNull(moduloContext.MODULO());
        Assert.assertEquals(0, moduloContext.expression().size());
        Assert.assertNull(moduloContext.expression(0));
        Assert.assertNull(moduloContext.accept(new DummyVisitor()));
    }

    @Test
    public void TestFunctionCallerContext() {
        HPKParser.ExpressionContext expressionContext = new HPKParser.ExpressionContext(null, 0);
        HPKParser.FunctionCallerContext moduloContext = new HPKParser.FunctionCallerContext(expressionContext);
        Assert.assertNull(moduloContext.functionCall());
        Assert.assertNull(moduloContext.accept(new DummyVisitor()));
    }

    @Test
    public void TestTinyContext() {
        HPKParser.ExpressionContext expressionContext = new HPKParser.ExpressionContext(null, 0);
        HPKParser.TinyContext moduloContext = new HPKParser.TinyContext(expressionContext);
        Assert.assertNull(moduloContext.E());
        Assert.assertEquals(0, moduloContext.expression().size());
        Assert.assertNull(moduloContext.expression(0));
        Assert.assertNull(moduloContext.accept(new DummyVisitor()));
    }

    @Test
    public void TestDivisionContext() {
        HPKParser.ExpressionContext expressionContext = new HPKParser.ExpressionContext(null, 0);
        HPKParser.DivisionContext moduloContext = new HPKParser.DivisionContext(expressionContext);
        Assert.assertNull(moduloContext.DIVIDE());
        Assert.assertEquals(0, moduloContext.expression().size());
        Assert.assertNull(moduloContext.expression(0));
        Assert.assertNull(moduloContext.accept(new DummyVisitor()));
    }

    @Test
    public void TestPowerContext() {
        HPKParser.ExpressionContext expressionContext = new HPKParser.ExpressionContext(null, 0);
        HPKParser.PowerContext moduloContext = new HPKParser.PowerContext(expressionContext);
        Assert.assertNull(moduloContext.POW());
        Assert.assertEquals(0, moduloContext.expression().size());
        Assert.assertNull(moduloContext.expression(0));
        Assert.assertNull(moduloContext.accept(new DummyVisitor()));
    }
}
