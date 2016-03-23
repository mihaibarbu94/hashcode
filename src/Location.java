/**
 * Created by Mihai on 23.03.2016.
 */
class Location {
    private int psi;
    private int lambda;

    public int getPsi() {
        return psi;
    }

    public int getLambda() {
        return lambda;
    }

    public void setLambda(int lambda) {
        if (lambda < -648000) {
            this.lambda = 648000 - (-lambda % 648000);
        } else {
            this.lambda = lambda % 648000;
        }
    }

    @Override
    public String toString() {
        return "Location{" +
                "psi=" + psi +
                ", lambda=" + lambda +
                '}';
    }

    public Location(int psi, int lambda) {
        this.psi = psi;
        this.lambda = lambda;
    }
}
