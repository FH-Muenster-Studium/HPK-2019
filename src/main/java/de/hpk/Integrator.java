package de.hpk;

import de.lab4inf.wrb.Function;

public class Integrator {
    private double eps = 1.E-9;

    static {
        try {
            System.load(System.getProperty("java.library.path") + "/libhpkNative.so");
        } catch (Throwable error) {
            System.out.println("load error:" + error.toString());
            System.out.println("path:" + System.getProperty("java.library.path"));
            throw error;
        }
    }

    /**
     * Integrate the function f from a to b.
     *
     * @param f function to differentiate.
     * @param a lower border
     * @param b upper border
     * @return F(b)-F(a)
     */
    public double integrate(Function f, double a, double b) {
        return integrate(f, a, b, eps);
    }

    /**
     * Integrate the function f from a to b.
     *
     * @param f   function to differentiate.
     * @param a   lower border
     * @param b   upper border
     * @param eps precision to reach
     * @return F(b)-F(a)
     */
    public native double integrate(Function f, double a, double b, double eps);

    /**
     * Get the value of eps.
     *
     * @return the eps
     */
    public double getEps() {
        return eps;
    }

    /**
     * Set the eps attribute.
     *
     * @param eps the eps to set
     */
    public void setEps(double eps) {
        this.eps = eps;
    }
}
