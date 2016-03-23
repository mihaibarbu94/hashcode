/**
 * Created by Mihai on 23.03.2016.
 */
class Satellite {
    private int psi;
    private int lambda;
    private int velocity;
    private int w;
    private int d;
    private int numTurns;

    public int getId() {
        return id;
    }

    public int getNumTurns() {
        return numTurns;
    }

    public void setNumTurns(int numTurns) {
        this.numTurns = numTurns;
    }

    private int delPsi;
    private int delLambda;
    private int id;

    public void setDelPsi(int delPsi) {
        this.delPsi = delPsi;
    }

    public void setDelLambda(int delLambda) {
        this.delLambda = delLambda;
    }

    public int getDelLambda() {
        return delLambda;
    }

    public int getDelPsi() {
        return delPsi;
    }

    public void setPsi(int psi) {
        this.psi = psi;
    }

    public void setLambda(int lambda) {
        if (lambda < -648000) {
            this.lambda = 648000 - (-lambda % 648000);
        } else {
            this.lambda = lambda % 648000;
        }
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getPsi() {
        return psi;
    }

    public int getLambda() {
        return lambda;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getW() {
        return w;
    }

    public int getD() {
        return d;
    }

    @Override
    public String toString() {
        return "Satellite{" +
                "psi=" + psi +
                ", lambda=" + lambda +
                ", velocity=" + velocity +
                ", w=" + w +
                ", d=" + d +
                ", id=" + id +
                '}';
    }

    public Satellite(int psi, int lambda, int velocity, int w, int d, int
            delPsi, int delLambda, int id) {
        this.psi = psi;
        this.lambda = lambda;
        this.velocity = velocity;
        this.w = w;
        this.d = d;
        this.delPsi = delPsi;
        this.delLambda = delLambda;
        this.id = id;
    }

    public Satellite(int psi, int lambda, int velocity, int w, int d, int id) {
        this.psi = psi;
        this.lambda = lambda;
        this.velocity = velocity;
        this.w = w;
        this.d = d;
        this.delPsi = 0;
        this.delLambda = 0;
        this.id = id;
    }
}
