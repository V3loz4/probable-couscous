/**
 * Generate class
 *
 * In this glass words of given length with the possible terminals is generated
 *
 * @author  Joana Velosa, jopa0118@student.umu.se
 * @since   2020-09-07
 */

import java.util.*;

public class Generate {
    List<Character> allowed; // list of allowed characters
    private Random rnd;

    public Generate(G g){
        allowed = new ArrayList<Character>();
        rnd = new Random();

        for (int i = 0; i< g.max; i++){
            for(int j = 0; j < g.getTerminalRules()[i].length;j++){
                char tempo = Character.toChars(g.getTerminalRules()[i][j])[0];
                if(!allowed.contains(tempo)) {
                    allowed.add(tempo);
                }
            }
        }
    }

    public String generate(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(
                    allowed.get(rnd.nextInt(allowed.size())));
        }
        return builder.toString();
    }
}
