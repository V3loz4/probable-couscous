/**
 * Naive CYK
 *
 * @author  Joana Velosa, jopa0118@student.umu.se
 * @since   2020-09-07
 */

import java.io.*;
import java.util.*;

public class Naive extends Parser{

    private Grammar g;
    private long count;
    private String string;
    private long time;

    @Override
    public Grammar getG() {
        return g;
    }

    @Override
    boolean parser(String string) {
        return false;
    }

    @Override
    public long getCount() {
        return count;
    }

    @Override
    public long getTime() {
        return time;
    }

    public Naive (Grammar g){ // g is for grammar
        this.g = g;
        count = 0;
        time = 0;
    }

    boolean parse(String string){
        this.string = string;
        count = 0;
        time = 0;
        long tempoT = System.nanoTime();
        boolean rtrn = parse(g.begin, 0, string.length()); // rtrn is return
        time = System.nanoTime()-tempoT;
        return rtrn;
    }

    boolean parse(int A, int i, int j){
        if (i == j-1){
            boolean rule = false;
            for (int r = 0; r < g.tr[A].length; r++){
                count++;
                int tempo = g.tr[A][r];
                if (tempo == string.charAt(i)){
                    rule = true;
                    return true;
                }
            }
            return rule;
        }
        else {
            boolean rule = false;
            for (int l = 0; l < g.ntr[A].length; l++){
                int tempo1 = g.ntr[A][l];
                l++;
                int tempo2 = g.ntr[A][l];
                for (int k = i+1; k < j; k++){
                    count++;
                    if (parse(tempo1, i, k) && parse(tempo2, k, j)){
                        rule = true;
                        return true;
                    }
                }
            }
            return rule;
        }
    }
}
