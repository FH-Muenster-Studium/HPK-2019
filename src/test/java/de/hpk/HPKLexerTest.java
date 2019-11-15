package de.hpk;

import org.antlr.v4.runtime.CharStreams;
import org.junit.Assert;
import org.junit.Test;

public class HPKLexerTest {
    @Test
    public void testTokenNames() {
        HPKLexer hpkLexer = new HPKLexer(CharStreams.fromString("5 + 7;"));
        Assert.assertNotNull(hpkLexer.getTokenNames());
    }

    @Test
    public void testVocabulary() {
        HPKLexer hpkLexer = new HPKLexer(CharStreams.fromString("5 + 7;"));
        Assert.assertNotNull(hpkLexer.getVocabulary());
    }

    @Test
    public void testGrammarFileName() {
        HPKLexer hpkLexer = new HPKLexer(CharStreams.fromString("5 + 7;"));
        Assert.assertEquals("HPK.g4", hpkLexer.getGrammarFileName());
    }

    @Test
    public void testRuleNames() {
        HPKLexer hpkLexer = new HPKLexer(CharStreams.fromString("5 + 7;"));
        Assert.assertNotNull(hpkLexer.getRuleNames());
    }

    @Test
    public void testSerializedATN() {
        HPKLexer hpkLexer = new HPKLexer(CharStreams.fromString("5 + 7;"));
        Assert.assertNotNull(hpkLexer.getSerializedATN());
    }

    @Test
    public void testATN() {
        HPKLexer hpkLexer = new HPKLexer(CharStreams.fromString("5 + 7;"));
        Assert.assertNotNull(hpkLexer.getATN());
    }

    @Test
    public void testChannelNames() {
        HPKLexer hpkLexer = new HPKLexer(CharStreams.fromString("5 + 7;"));
        Assert.assertNotNull(hpkLexer.getChannelNames());
    }

    @Test
    public void testModeNames() {
        HPKLexer hpkLexer = new HPKLexer(CharStreams.fromString("5 + 7;"));
        Assert.assertNotNull(hpkLexer.getModeNames());
    }
}
