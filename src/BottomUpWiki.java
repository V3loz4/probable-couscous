/**
 * Bottom Up Wiki class
 *
 * This is the Bottom up implementation of CYK following the Wikipedia pseudo-code
 *
 * @author  Joana Velosa, jopa0118@student.umu.se
 * @since   2020-09-07
 */

public class BottomUpWiki extends Parser{
    private Grammar g;
    private long count;
    private int[][][] result;
    private long time;

    public BottomUpWiki(Grammar g) {
        this.g = g;
        count = 0;
        time = 0;
    }

    boolean parse(String string) {
        count = 0;
        time = 0;
        long tempotime = System.nanoTime();
        result = new int[string.length()][string.length()][26];

        for (int i = 0; i < string.length(); i++) {

            for (int v = 0; v < 26; v++) {
                count++;
                boolean hasterm = false;

                for (int o = 0; o < g.tr[v].length; o++) {
                    int te = g.tr[v][o];
                    if (te == (int) string.charAt(i)) {
                        hasterm = true;
                        break;
                    }
                }
                if (hasterm == true) {
                    result[i][0][v] = 1;
                }

            }

        }

        for (int j = 2; j <= string.length(); j++) {
            for (int i = 1; i <= string.length() - j + 1; i++) {

                for (int v = 0; v < 26; v++) {

                    for (int w = 0; w < g.ntr[v].length; w++) {
                        count++;
                        int temp1 = g.ntr[v][w];
                        w++;
                        int temp2 = g.ntr[v][w];

                        if (temp1 >= 0 && temp1 < 26) {
                            if (result[i - 1][j - 2][temp1] == 1) {
                                if ((int) string.charAt(i + j - 2) == temp2) {
                                    result[i - 1][j - 1][v] = 1;
                                    break;
                                }
                            }
                        }

                        if (temp2 >= 0 && temp2 < 26) {
                            if (result[i][j - 2][temp2] == 1) {
                                if ((int) string.charAt(i - 1) == temp1) {
                                    result[i - 1][j - 1][v] = 1;
                                    break;
                                }
                            }
                        }

                    }

                }
            }

        }

        if (result[0][string.length() - 1][g.begin] == 1) {
            time = System.nanoTime() - tempotime;
            return true;
        } else {
            time = System.nanoTime() - tempotime;
            return false;
        }
    }

    public long getCounter() {
        return count;
    }

    public int[][][] getResults() {
        return result;
    }

    @Override
    boolean parser(String string) {
        return false;
    }

    @Override
    long getCount() {
        return 0;
    }

    Grammar getG() {
        return g;
    }

    long getTime() {
        return time;
    }
}
