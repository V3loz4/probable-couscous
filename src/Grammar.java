/**
 * CYK Grammar class
 *
 * @author  Joana Velosa, jopa0118@student.umu.se
 * @since   2020-09-07
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Grammar extends G{
    int max = 100;
    int min = 0;
    int[][] ntr = new int[max][max];
    int[][] tr = new int[max][max];
    int begin;

    // here the format is not checked, so the input already needs to be in CNF

    public Grammar(File input) throws FileNotFoundException {

        Scanner reader = new Scanner(input);

        List<NTR> nttemp = new ArrayList<NTR>(); // NTR - Non-Terminal rules
        List<TR>  ttemp = new ArrayList<TR>(); // tr - Terminal Rules

        if (reader.hasNextLine()) {
            String init = reader.nextLine(); // init - initialize
            begin = init.charAt(0) - 'A';
            if (init.length() > 3){
                int first = init.charAt(2);
                int second = init.charAt(3);
                NTR nont = new NTR (begin + 'A', first, second); // nont - non-terminal
                nttemp.add(nont);
            }
            else {
                int term = init.charAt(2);
                TR terminal = new TR (begin + 'A', term);
                ttemp.add(terminal);
            }
        }
        else {
            System.out.println("This file does not contain a grammar.");
        }

        while (reader.hasNextLine()){
            String init = reader.nextLine(); // init - initialize
            if (init.length() > 3){
                int from = init.charAt(0);
                int first = init.charAt(2);
                int second = init.charAt(3);
                NTR nont = new NTR (from, first, second); // nont - non-terminal
                nttemp.add(nont);
            }
            else {
                int from = init.charAt(0);
                int term = init.charAt(2);
                TR terminal = new TR (from, term);
                ttemp.add(terminal);
            }
        }

        for (int k = 0; k < max; k++){
            ntr[k] = new int[nttemp.size()];
        }

        for (int k = 0; k < max; k++){
            tr[k] = new int[ttemp.size()];
        }

        int i = 0;
        while(!nttemp.isEmpty()){
            NTR tempo = nttemp.remove(0);
            ntr[tempo.getInit() - 'A'][i] = tempo.getFirst() - 'A';
            i++;
            ntr[tempo.getInit() - 'A'][i] = tempo.getSecond() - 'A';
            i++;
        }

        int a = 0;
        while (!ttemp.isEmpty()){
            TR tempo = ttemp.remove(0);
            tr[tempo.getInit() - 'A'][a] = tempo.getEnd();
            a++;
        }
        reader.close();
    }

    int[][] getTerminalRules(){
        return tr;
    }
}
