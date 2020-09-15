/**
 * Bottom-Up CYK
 *
 * @author  Joana Velosa, jopa0118@student.umu.se
 * @since   2020-09-07
 */

public class BottomUp  extends Parser{

    private Grammar g;
    private long count;
    private int[][][] result;
    private long time;

    public BottomUp(Grammar g) {
        this.g = g;
        count = 0;
        time = 0;
    }


    boolean parse(String string) {
        count = 0;
        time = 0;
        long tempoT = System.nanoTime();
        result = new int[string.length()][string.length()][g.max];

        for (int i = 0; i < string.length(); i++) {

            for (int v = 0; v < g.max; v++) {

                boolean hasT = false;

                for (int o = 0; o < g.tr[v].length; o++) {
                    count++;
                    int te = g.tr[v][o];
                    if (te == (int) string.charAt(i)) {
                        hasT = true;
                        break;
                    }
                }
                if (hasT == true) {
                    result[i][0][v] = 1;
                }

            }

        }

        for (int j = 2; j <= string.length(); j++) {
            for (int i = 1; i <= string.length() - j + 1; i++) {

                for (int v = 0; v < g.max; v++) {
                    for (int k = 0; k < j - 1; k++) {

                        for (int w = 0; w < g.ntr[v].length; w++) {
                            count++;
                            Integer tempo1 = g.ntr[v][w];
                            w++;
                            Integer tempo2 = g.ntr[v][w];

                            if (result[i - 1][k][tempo1] == 1) {
                                if (result[i - 1 + k + 1][j - 1 - k - 1][tempo2] == 1) {

                                    result[i - 1][j - 1][v] = 1;
                                    break;
                                }
                            }

                        }
                        if (result[i - 1][j - 1][v] == 1) {
                            break;
                        }

                    }

                }
            }

        }

        if (result[0][string.length() - 1][g.begin] == 1) {
            time = System.nanoTime() - tempoT;
            return true;
        } else {
            time = System.nanoTime() - tempoT;
            return false;
        }
    }

    public long getCount() {
        return count;
    }

    public int[][][] getResult() {
        return result;
    }

    G getG() {
        return g;
    }

    @Override
    boolean parser(String string) {
        return false;
    }

    long getTime() {
        return time;
    }
}
