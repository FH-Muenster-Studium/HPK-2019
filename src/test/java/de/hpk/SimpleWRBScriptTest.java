/*
 * Project: WRB
 *
 * Copyright (c) 2008-2013,  Prof. Dr. Nikolaus Wulff
 * University of Applied Sciences, Muenster, Germany
 * Lab for Computer Sciences (Lab4Inf).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package de.hpk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import de.lab4inf.wrb.Script;
import de.lab4inf.wrb.WRBScript;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of the Wulff RunsBeta-Script language.
 *
 * @author nwulff
 * @version $Id: SimpleWRBScriptTest.java,v 1.4 2018/10/23 15:06:32 nwulff Exp $
 * @since 16.10.2013
 */
public class SimpleWRBScriptTest {
    final double eps = 1.E-8;
    Script script;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public final void setUp() throws Exception {
        script = getScript();
        assertNotNull("no script implementation", script);
    }

    /**
     * Get the actual implementation for the script test.
     *
     * @return script implementation
     */
    protected Script getScript() {
        return new WRBScript();
    }

    /**
     * Test method for
     * {@link de.lab4inf.wrb.Script#getVariable(java.lang.String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testGetUnknownVariable() throws Exception {
        String key = "dummy";
        script.getVariable(key);
    }

    /**
     * Test method for
     * {@link de.lab4inf.wrb.Script#setVariable(java.lang.String, double)}. and
     * {@link de.lab4inf.wrb.WRBScript#getVariable(java.lang.String)}.
     */
    @Test
    public final void testSetGetVariable() throws Exception {
        double y, x = 2.78;
        String key = "XYZ";
        script.setVariable(key, x);
        y = script.getVariable(key);
        assertEquals(x, y, eps);
        x = Math.random();
        script.setVariable(key, x);
        y = script.getVariable(key);
        assertEquals(x, y, eps);
    }

    /**
     * Test method for {@link de.lab4inf.wrb.Script#parse(java.lang.String)}.
     * Testing some very simple operation. More to come...
     */

    @Test
    public final void testPlus() throws Exception {
        String task = "2+3";
        assertEquals(5.0, script.parse(task), eps);
    }

    @Test
    public final void testMinus() throws Exception {
        String task = "2 - 6";
        assertEquals(-4.0, script.parse(task), eps);
    }

    @Test
    public final void testConstant() throws Exception {
        String task = "0815; 4711";
        assertEquals(4711.0, script.parse(task), eps);
    }

    @Test
    public final void testSigned() throws Exception {
        String task = "-2 + 6";
        assertEquals(4.0, script.parse(task), eps);
    }

    @Test
    public void testSignedSecondArg() throws Exception {
        String task = "2 + -6";
        assertEquals(-4.0, script.parse(task), eps);
    }

    @Test
    public final void testMixedFloat() throws Exception {
        String task = "2.0/3 - 5.2*4";
        assertEquals(2. / 3.0 - 5.2 * 4, script.parse(task), eps);
    }

    @Test
    public final void testLongAdd() throws Exception {
        String task = "2.0 + 3 + 4.0 + 5";
        assertEquals(14, script.parse(task), eps);
    }

    @Test
    public final void testLongMult() throws Exception {
        String task = "2 * 3.0 * 4 * 5.000";
        assertEquals(120, script.parse(task), eps);
    }

    @Test
    public final void testLongMixed() throws Exception {
        String task = "2.0 * 3 * 4.0 + 5 + 6.0 / 3 ";
        assertEquals(31, script.parse(task), eps);
    }

    @Test
    public void testParseBracket() throws Exception {
        String task = " 2*(4.0 + 3)";
        assertEquals(14, script.parse(task), eps);
    }

    @Test
    public final void testWrongFloat() throws Exception {
        String task = "2 + .3";
        //((WRBScript) script).setThrowing(false);
        assertEquals(2.3, script.parse(task), eps);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testWrongToken() throws Exception {
        String task = "2 + ?3";
        ((WRBScript) script).setThrowing(true);
        script.parse(task);
        fail("keine Exception geworfen");
    }

    @Test
    public final void Whitespace() throws Exception {
        String task = "2 +.3";
        assertEquals(2.3, script.parse(task), eps);
    }

    @Test
    public final void Whitespace2() throws Exception {
        String task = "2 +(.3)";
        assertEquals(2.3, script.parse(task), eps);
    }

    @Test
    public final void POW() throws Exception {
        String task = "2**3";
        assertEquals(8.0, script.parse(task), eps);
    }

    @Test
    public final void POWAssociative() throws Exception {
        String task = "1**3**4";
        assertEquals(1.0, script.parse(task), eps);
    }

    @Test
    public final void POWAssociative2() throws Exception {
        String task = "4**1**2";
        assertEquals(4.0, script.parse(task), eps);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void POWError() throws Exception {
        String task = "1***3";
        assertEquals(8.0, script.parse(task), eps);
        fail("keine Exception geworfen");
    }

    @Test
    public final void Mod() throws Exception {
        String task = "12 % 13";
        assertEquals(12, script.parse(task), eps);
    }

    @Test
    public final void Mod2() throws Exception {
        String task = "12 mod 13";
        assertEquals(12, script.parse(task), eps);
    }

    @Test
    public final void Mod3() throws Exception {
        String task = "11 mod 4 mod 3";
        assertEquals(0.0, script.parse(task), eps);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void Mod4() throws Exception {
        String task = "12 mod 0";
        script.parse(task);
        fail("keine Exception geworfen");
    }

    @Test
    public final void Mod5() throws Exception {
        String task = "0 mod 5";
        assertEquals(0.0, script.parse(task), eps);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void DivideZero() throws Exception {
        String task = "12 / 0";
        script.parse(task);
        fail("keine Exception geworfen");
    }

    @Test
    public final void E() throws Exception {
        String task = "10 e 5";
        assertEquals(1000000, script.parse(task), eps);
    }

    @Test
    public final void Variables() throws Exception {
        String task = "x0 = 7; x1 = 14; x1 / x0";
        assertEquals(2.0, script.parse(task), eps);
    }

    @Test
    public final void Variables2() throws Exception {
        String task = "x.0 = 8; x.1 = 16; x.1 / x.0";
        assertEquals(2.0, script.parse(task), eps);
    }
}
