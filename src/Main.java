/**
 * CYK Main Class
 *
 * @author  Joana Velosa, jopa0118@student.umu.se
 * @since   2020-09-07
 */

import javax.sound.sampled.Line;
import java.io.*;

public class Main {
        private static int WARMUP_ROUNDS = 100000;
        private static int WARMUP_MAXLENGTH = 5;

        public static void main(String[] args) {

            File file = new File("C:\\Users\\joana\\IdeaProjects\\CYK\\src\\grammar.txt");
            File file2 = new File("C:\\Users\\joana\\IdeaProjects\\CYK\\src\\grammar2.txt");
            File file3 = new File("C:\\Users\\joana\\IdeaProjects\\CYK\\src\\grammar3.txt");
            File file4 = new File("C:\\Users\\joana\\IdeaProjects\\CYK\\src\\grammar4.txt");

            Grammar g;
            Grammar g2;
            Grammar g3;
            LinearG g4;

            try {

                g = new Grammar(file);
                g2 = new Grammar(file2);
                g3 = new Grammar(file3);
                g4 = new LinearG(file4);


                Generate gen3 = new Generate(g3);
                Generate gen4 = new Generate(g4);

                BottomUp botUp1 = new BottomUp(g);
                Naive naive1 = new Naive(g);
                TopDown topDo1 = new TopDown(g);

                BottomUpWiki linearBotUp = new BottomUpWiki(g4);

                warmUpParser(botUp1);
                warmUpParser(topDo1);
                warmUpParser(naive1);
                warmUpParser(linearBotUp);

                PrintWriter writer = new PrintWriter("C:\\Users\\joana\\IdeaProjects\\CYK\\src\\result.txt");

                // first time
                // then counter
                // then the same for the next grammar

                long[] timearraynaiv = new long[20];
                long[] ctdarraynaiv = new long[20];

                long[] timearraybot = new long[20];
                long[] ctdarraybot = new long[20];

                long[] timearraytop = new long[20];
                long[] ctdarraytop = new long[20];

                int n = 1;

                System.out.print("second grammar");

                BottomUp botUp2 = new BottomUp(g2);
                Naive naive2 = new Naive(g2);
                TopDown topDo2 = new TopDown(g2);

                for (int i = 1; i <= 20; i++) {
                    n = 4 * i;
                    if (i >= 3) {
                        n = 10 * (i - 2);
                    }
                    writer.println("second grammar");
                    writer.println(n);

                    System.out.println();
                    System.out.println(n);

                    for (int t = 0; t < 20; t++) {

                        String test1 = "";

                        for(int st=0; st <n/2.;st++) {
                            test1 = test1+"(";
                        }
                        for(int st=0; st <n/2.;st++) {
                            test1 = test1 + ")";
                        }

                        if (i < 3) {
                            naive2.parse(test1);
                            timearraynaiv[t] = naive2.getTime();
                            ctdarraynaiv[t] = naive2.getCount();

                            System.out.println(naive2.getCount());
                        }

                        System.out.println(botUp2.parse(test1));

                        timearraybot[t] = botUp2.getTime();
                        ctdarraybot[t] = botUp2.getCount();

                        System.out.println(botUp2.getCount());


                        topDo2.parse(test1);

                        timearraytop[t] = topDo2.getTime();
                        ctdarraytop[t] = topDo2.getCount();

                        System.out.println(topDo2.getCount());
                    }

                    double total = 0;
                    for (int w = 0; w < 20; w++) {
                        total = total + timearraynaiv[w];
                    }
                    double averagenaiv = total / 20.;

                    total = 0;
                    for (int w = 0; w < 20; w++) {
                        total = total + timearraybot[w];
                    }
                    double averagebot = total / 20.;

                    total = 0;
                    for (int w = 0; w < 20; w++) {
                        total = total + timearraytop[w];
                    }
                    double averagetop = total / 20.;

                    total = 0;
                    for (int w = 0; w < 20; w++) {
                        total = total + ctdarraynaiv[w];
                    }
                    double averagenaiv2 = total / 20.;

                    total = 0;
                    for (int w = 0; w < 20; w++) {
                        total = total + ctdarraybot[w];
                    }
                    double averagebot2 = total / 20.;

                    total = 0;
                    for (int w = 0; w < 20; w++) {
                        total = total + ctdarraytop[w];
                    }
                    double averagetop2 = total / 20.;

                    if (i < 3) {
                        writer.println(averagenaiv);
                        writer.println(averagenaiv2);
                    }
                    writer.println(averagebot);
                    writer.println(averagebot2);
                    writer.println(averagetop);
                    writer.println(averagetop2);

                }

                System.out.print("third grammar");

                BottomUp botUp3 = new BottomUp(g3);
                Naive naive3 = new Naive(g3);
                TopDown topDo3 = new TopDown(g3);

                n = 1;
                for (int i = 1; i <= 20; i++) {

                    n = 4 * i;
                    if (i >= 3) {
                        n = 10 * (i - 2);
                    }
                    writer.println("third grammar");
                    writer.println(n);

                    System.out.println();
                    System.out.println(n);

                    for (int t = 0; t < 20; t++) {
                        n--;
                        String test1 = gen3.generate(n)+"b";
                        n++;
                        if (i < 3) {
                            naive3.parse(test1);
                            timearraynaiv[t] = naive3.getTime();
                            ctdarraynaiv[t] = naive3.getCount();

                            System.out.println(naive3.getCount());
                        }

                        System.out.println(botUp3.parse(test1));

                        timearraybot[t] = botUp3.getTime();
                        ctdarraybot[t] = botUp3.getCount();

                        System.out.println(botUp3.getCount());


                        System.out.println(topDo3.parse(test1));

                        timearraytop[t] = topDo3.getTime();
                        ctdarraytop[t] = topDo3.getCount();

                        System.out.println(topDo3.getCount());
                    }

                    double total = 0;
                    for (int w = 0; w < 20; w++) {
                        total = total + timearraynaiv[w];
                    }
                    double averagenaiv = total / 20.;

                    total = 0;
                    for (int w = 0; w < 20; w++) {
                        total = total + timearraybot[w];
                    }
                    double averagebot = total / 20.;

                    total = 0;
                    for (int w = 0; w < 20; w++) {
                        total = total + timearraytop[w];
                    }
                    double averagetop = total / 20.;

                    total = 0;
                    for (int w = 0; w < 20; w++) {
                        total = total + ctdarraynaiv[w];
                    }
                    double averagenaiv2 = total / 20.;

                    total = 0;
                    for (int w = 0; w < 20; w++) {
                        total = total + ctdarraybot[w];
                    }
                    double averagebot2 = total / 20.;

                    total = 0;
                    for (int w = 0; w < 20; w++) {
                        total = total + ctdarraytop[w];
                    }
                    double averagetop2 = total / 20.;

                    if (i < 3) {
                        writer.println(averagenaiv);
                        writer.println(averagenaiv2);
                    }
                    writer.println(averagebot);
                    writer.println(averagebot2);
                    writer.println(averagetop);
                    writer.println(averagetop2);
                }

                System.out.println("linear grammar");

                long[] lineartime = new long[20];
                long[] linearctd = new long[20];

                n = 1;
                for (int i = 1; i <= 20; i++) {
                    n = 4 * i;
                    if (i >= 3) {
                        n = 10 * (i - 2);
                    }
                    writer.println("linear grammar");
                    writer.println(n);

                    System.out.println();
                    System.out.println(n);

                    for (int t = 0; t < 20; t++) {

                        String test1 = gen4.generate(n);

                        boolean results = linearBotUp.parse(test1);

                        linearctd[t] = linearBotUp.getCount();

                        lineartime[t] = linearBotUp.getTime();

                        System.out.println(results);

                        System.out.println(linearBotUp.getCount());

                    }

                    double total = 0;
                    for (int w = 0; w < 20; w++) {
                        total = total + lineartime[w];
                    }
                    double averagetime = total / 20.;

                    total = 0;
                    for (int w = 0; w < 20; w++) {
                        total = total + linearctd[w];
                    }
                    double averagectd = total / 20.;

                    writer.println(averagetime);
                    writer.println(averagectd);

                }

                writer.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        private static void warmUpParser(Parser parser) {
            Generate gen = new Generate(parser.getG());
            for (int i = 0; i < WARMUP_ROUNDS; i++) {
                parser.parser(gen.generate(WARMUP_MAXLENGTH));
                if (i % (WARMUP_ROUNDS / 100) == 0) {
                    int percentage = i / (WARMUP_ROUNDS / 100);
                    System.out.print("\rWarming up: " + percentage + "%");
                }
            }
        }
}
