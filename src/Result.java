/**
 * Created by Mihai on 23.03.2016.
 */
class Result{
    private int psi;
    private int lambda;
    private int turn;
    private int satellite;

    @Override
    public String toString() {
        return  psi + " " + lambda + " " + turn + " " + satellite + '\n';
    }

    public Result(int psi, int lambda, int turn, int satellite) {
        this.psi = psi;
        this.lambda = lambda;
        this.turn = turn;
        this.satellite = satellite;
    }
}
