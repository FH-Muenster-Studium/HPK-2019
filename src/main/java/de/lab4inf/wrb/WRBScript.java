package de.lab4inf.wrb;

import de.hpk.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;
import java.util.Set;

public class WRBScript implements Script, ANTLRErrorListener {

    private VariableRepository variableRepository = new Variables();

    private boolean throwing = true;

    public WRBScript() {

    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
        if (!throwing) return;
        throw new IllegalArgumentException(e.getMessage());
    }

    @Override
    public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {
    }

    @Override
    public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {
    }

    @Override
    public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {
    }

    private double parse(CharStream charStream) {
        HPKLexer lexer = new HPKLexer(charStream);
        lexer.addErrorListener(this);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HPKParser parser = new HPKParser(tokens);
        parser.addErrorListener(this);
        ParseTree tree = parser.root();
        MathVisitor hpkVisitor = new MathVisitor();
        hpkVisitor.visit(tree);
        variableRepository = hpkVisitor.getVariableRepository();
        if (hpkVisitor.getResults().size() == 0) return 0.0;
        return hpkVisitor.getResults().get(hpkVisitor.getResults().size() - 1);
    }

    /**
     * Parse the given String into an internal representation (e.g. as AST) and
     * evaluate the expression(s).
     *
     * @param definition of the script expression(s) as a String
     * @return the result of the parsed evaluation
     */
    @Override
    public double parse(String definition) {
        return parse(CharStreams.fromString(definition));
    }

    /**
     * Parse the InputStream into an internal representation (e.g. as AST) and
     * evaluate the expression(s).
     *
     * @param defStream of the script expression(s) as InputStream
     * @return the result of the parsed evaluation
     * @throws IOException in case of a stream I/O error
     */
    @Override
    public double parse(InputStream defStream) throws IOException {
        return parse(CharStreams.fromStream(defStream));
    }

    /**
     * Get all script known function names.
     *
     * @return set with the function names
     */
    @Override
    public Set<String> getFunctionNames() {
        return null;
    }

    /**
     * Get all script known variable names.
     *
     * @return set with the variables names
     */
    @Override
    public Set<String> getVariableNames() {
        return variableRepository.getVariableNames();
    }

    /**
     * Add a named function to this script.
     *
     * @param name of the function to be unique
     * @param fct  to add
     */
    @Override
    public void setFunction(String name, Function fct) {

    }

    /**
     * Get a (unique!) named function.
     *
     * @param name of the function
     * @return an implementation
     */
    @Override
    public Function getFunction(String name) {
        return null;
    }

    /**
     * Get the value of a (unique!) named variable.
     *
     * @param name of the variable
     * @return the actual variable value
     */
    @Override
    public double getVariable(String name) {
        return variableRepository.getVariable(name);
    }

    /**
     * Set the value of a named variable.
     *
     * @param name  of the variable to be unique
     * @param value the new variable setting
     */
    @Override
    public void setVariable(String name, double value) {
        variableRepository.setVariable(name, value);
    }

    /**
     * New default method since JDK 1.8!
     * Concatenation of "this" script with "that" script. This
     * default implementation simply adds the context of the other
     * script, which means it may overwrite the internal script state.
     * <p>
     * Implementing script classes should change this behaviour to
     * construct a new fresh script merged from "this" and "that" as return
     * without changing the internal state of "this" and "that".
     *
     * @param that another script with variables and functions to add
     * @return the (optional new)) build script.
     */
    @Override
    public Script concat(Script that) {
        WRBScript script = new WRBScript();
        script.variableRepository = variableRepository;
        return script;
    }

    public void setThrowing(boolean throwing) {
        this.throwing = throwing;
    }
}
