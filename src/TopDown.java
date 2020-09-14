/**
 * Top-Down CYK
 *
 * @author  Joana Velosa, jopa0118@student.umu.se
 * @since   2020-09-07
 */

import java.io.*;
import java.util.*;

public class TopDown  extends Parser{
    private Grammar g;
    private long count;
    private String string;
    private int[][][] result;
    private long time;

    public int[][][] getResult() {
        return result;
    }

    public Grammar getG() {
        return g;
    }

    public long getCount() {
        return count;
    }

    public TopDown(Grammar g) {
        this.g = g;
        count = 0;
        time = 0;
    }

    boolean parse(String string) {
        this.string = string;
        count = 0;
        time = 0;
        result = new int[g.max][string.length()][string.length()];
        long tempoT = System.nanoTime();
        boolean rtrn = parse(g.begin, 0, string.length());
        time = System.nanoTime() - tempoT;
        return rtrn;

    }



    boolean parse(int beg, int i, int j) {

        if (result[beg][i][j - 1] != 0) {
            count++;
            return result[beg][i][j - 1] == 1;
        }

        if (i == j - 1) {

            for(int o=0; o< g.tr[beg].length;o++) {
                count++;
                int temp = g.tr[beg][o];
                if (temp == string.charAt(i)) {
                    result[beg][i][j - 1] = 1;
                    return true;
                }
            }

            result[beg][i][j - 1] = 4;
            return false;

        }

        for (int k = i + 1; k < j; k++) {

            for(int w=0; w< g.ntr[beg].length;w++) {
                count++;
                int temp1 = g.ntr[beg][w];
                w++;
                int temp2 = g.ntr[beg][w];
                if (parse(temp1, i, k) && parse(temp2, k, j)) {

                    result[beg][i][j - 1] = 1;
                    return true;
                }
            }
        }
        result[beg][i][j - 1] = 4;
        return false;

    }

    @Override
    boolean parser(String string) {
        return false;
    }

    @Override
    long getTime() {
        return time;
    }
}
