package de.hpk;

import java.util.ArrayList;
import java.util.List;

public class MathVisitor extends HPKBaseVisitor<Double> {

    private final List<Double> results = new ArrayList<>();

    private final VariableRepository variableRepository;

    public MathVisitor() {
        variableRepository = new Variables();
    }

    public VariableRepository getVariableRepository() {
        return variableRepository;
    }

    public List<Double> getResults() {
        return results;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitRoot(HPKParser.RootContext ctx) {
        List<HPKParser.StatementContext> statements = ctx.statement();
        for (int i = 0, length = statements.size(); i < length; i++) {
            HPKParser.StatementContext statementContext = statements.get(i);
            results.add(visit(statementContext));
        }
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitStatement(HPKParser.StatementContext ctx) {
        return super.visitStatement(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitAssignment(HPKParser.AssignmentContext ctx) {
        double value = visit(ctx.expression());
        variableRepository.setVariable(ctx.VARIABLE().getText(), value);
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitFunctionDefinition(HPKParser.FunctionDefinitionContext ctx) {
        return super.visitFunctionDefinition(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitFunctionCall(HPKParser.FunctionCallContext ctx) {
        return super.visitFunctionCall(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitBracket(HPKParser.BracketContext ctx) {
        double inner = visit(ctx.expression());
        if (ctx.sign != null && ctx.sign.getText().equals(ctx.MINUS().getText())) {
            inner *= -1;
        }
        return inner;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitMultiplication(HPKParser.MultiplicationContext ctx) {
        double firstFactor = visit(ctx.firstFactor);
        double lastFactor = visit(ctx.lastFactor);
        return firstFactor * lastFactor;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitAddition(HPKParser.AdditionContext ctx) {
        double firstSummand = visit(ctx.firstSummand);
        double lastSummand = visit(ctx.lastSummand);
        return firstSummand + lastSummand;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitVariable(HPKParser.VariableContext ctx) {
        double variableValue = variableRepository.getVariable(ctx.VARIABLE().getText());
        if (ctx.sign != null && ctx.sign.getText().equals(ctx.MINUS().getText())) {
            variableValue *= -1;
        }
        return variableValue;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitSubtraction(HPKParser.SubtractionContext ctx) {
        double minuend = visit(ctx.minuend);
        double subtrahend = visit(ctx.subtrahend);
        return minuend - subtrahend;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitNumber(HPKParser.NumberContext ctx) {
        double value = Double.parseDouble(ctx.NUMBER().getText());
        if (ctx.sign != null && ctx.sign.getText().equals(ctx.MINUS().getText())) {
            value *= -1;
        }
        return value;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitModulo(HPKParser.ModuloContext ctx) {
        return super.visitModulo(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitFunctionCaller(HPKParser.FunctionCallerContext ctx) {
        return super.visitFunctionCaller(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitTiny(HPKParser.TinyContext ctx) {
        return super.visitTiny(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitDivision(HPKParser.DivisionContext ctx) {
        double dividend = visit(ctx.dividend);
        double divisor = visit(ctx.divisor);
        return dividend / divisor;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Double visitPower(HPKParser.PowerContext ctx) {
        return super.visitPower(ctx);
    }
}
