/**
 * This class contains the rules for what is a terminal
 *
 * @author  Joana Velosa, jopa0118@student.umu.se
 * @since   2020-09-07
 */

public class TR {

    private int init;
    private int end;

    public TR(int init, int end) {
        this.init = init;
        this.end = end;
    }

    public int getInit(){
        return init;
    }

    public int getEnd(){
        return end;
    }
}
