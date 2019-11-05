package de.hpk;

import de.lab4inf.wrb.Script;
import de.lab4inf.wrb.WRBScript;

public class MathTest extends AbstractScriptTest {

    /**
     * Factory method, get a fresh script implementation for the test.
     * <p>
     * Note: This method can be  called several times during one test case
     * and should return independent scripts.
     *
     * @return script implementation
     */
    @Override
    protected Script getScript() {
        return new WRBScript();
    }
}
