/**
 * An  abstract  class Grammar that  can  be  subclassed  by  specifying  the  rules  of  a  context-free grammar in
 * Chomsky normal.  The class should allow efficient access to the rules,  where efficiency  should  be  judged
 * relative  to  the  way  in  which  the  rules  are  accessed  in  the  CYK algorithm.  In particular, it is probably
 * a good idea to represent the terminal rules separately from  the  non-terminal  ones.   Moreover,  I  suggest  to
 * translate  the  non-terminals  into  integers while initializing the internal data structures.  In this way,  one
 * may for example represent aright-hand side as a pair of integers, and can implement the set of non-terminal rules
 * as an array of arrays of right-hand sides.  Then, if a given non-terminal i has n non-terminal rules, one can
 * efficiently loop over their right-hand sides as rule[i][j] for j= 0, . . . , n−1 (and one can loop over all rules by
 * additionally looping over i)..
 *
 * @author  Joana Velosa, jopa0118@student.umu.se
 * @since   2020-08-16
 */

public abstract class Grammar {

}
