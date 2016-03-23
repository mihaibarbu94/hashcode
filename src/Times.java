/**
 * Created by Mihai on 23.03.2016.
 */
class Times {
    private int start;
    private int end;

    @Override
    public String toString() {
        return "Times{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public Times(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
