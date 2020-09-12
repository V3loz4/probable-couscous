/**
 * This class contains the rules to what is a non-terminal
 *
 * @author  Joana Velosa, jopa0118@student.umu.se
 * @since   2020-09-07
 */

public class NTR {

    private int init;
    private int first;
    private int second;

    public NTR(int i, int first, int second) {
        this.init = init;
        this.first = first;
        this.second = second;
    }

    public int getInit() {
        return init;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}
