package de.hpk;

import de.lab4inf.wrb.Function;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class MathFunction implements Function {

    private final MathVisitor mathVisitor;

    private final VariableRepository variableRepository;

    private final HPKParser.FunctionDefinitionContext functionDefinitionContext;

    private final String[] parameterNames;

    public MathFunction(MathVisitor mathVisitor, HPKParser.FunctionDefinitionContext functionDefinitionContext) {
        this.mathVisitor = mathVisitor;
        this.functionDefinitionContext = functionDefinitionContext;
        this.variableRepository = new VariableRepositoryImpl(mathVisitor.getVariableRepository());
        List<TerminalNode> variables = functionDefinitionContext.VARIABLE();
        int length = variables.size() - 1;
        this.parameterNames = new String[length];
        for (int i = 0; i < length; i++) {
            parameterNames[i] = variables.get(i + 1).getText();
        }
    }

    @Override
    public double eval(double... args) {
        FunctionUtils.checkFunctionCount(args.length, this.parameterNames.length);
        for (int i = 0, length = args.length; i < length; i++) {
            this.variableRepository.setVariable(this.parameterNames[i], args[i]);
        }

        VariableRepository variableRepositoryOld = this.mathVisitor.getVariableRepository();
        this.mathVisitor.setVariableRepository(variableRepository);
        double result = this.mathVisitor.visit(functionDefinitionContext.expression());
        this.mathVisitor.setVariableRepository(variableRepositoryOld);
        return result;
    }
}
