package de.hpk;

import de.lab4inf.wrb.WRBScript;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("test");
        WRBScript script = new WRBScript();
        script.parse("6 E 7");
        //CharStream charStream = CharStreams.fromString("x=-42;x*7;5+7;7*7;12-17;12--17;8/-4;6*(7+3)");
        /*CharStream charStream = CharStreams.fromString("6 ?7");
        HPKLexer lexer = new HPKLexer(charStream);  //instantiate a lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer); //scan stream for tokens
        HPKParser parser = new HPKParser(tokens);  //parse the tokens
        ParseTree tree = parser.root();
        MathVisitor hpkVisitor = new MathVisitor();
        hpkVisitor.visit(tree);
        for (int i = 0, length = hpkVisitor.getResults().size(); i < length; i++) {
            System.out.println("result:" + hpkVisitor.getResults().get(i));
        }*/
    }
}
