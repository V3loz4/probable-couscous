/**
 * CYK Linear Grammar class
 *
 * @author  Joana Velosa, jopa0118@student.umu.se
 * @since   2020-09-07
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class LinearG extends G{
    int max = 100;
    int[][] ntr = new int[max][0];
    int[][] tr = new int[max][0];
    int begin;

    // here the format is not checked, so the input already needs to be in CNF

    public LinearG(File input) throws FileNotFoundException {

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
            int tempoF = tempo.getFirst();
            int tempoS = tempo.getSecond();

            if ((tempoF - 'A' ) <= 0 && (tempoF - 'A') < max){
                tempoF = tempoF - 'A';
            }

            if ((tempoS - 'A') <= 0 && (tempoS - 'A') < max){
                tempoS = tempoS - 'A';
            }

            ntr[tempo.getInit() - 'A'][i] = tempoF;
            i++;
            ntr[tempo.getInit() - 'A'][i] = tempoS;
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
