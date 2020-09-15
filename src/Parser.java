/**
 * CYK Parser Class
 *
 *
 *
 * @author  Joana Velosa, jopa0118@student.umu.se
 * @since   2020-09-07
 */

import java.io.*;
import java.util.*;

public abstract class Parser {
    private G g;

    abstract boolean parser (String string);

    abstract long getCount();

    abstract G getG();

    abstract long getTime();
}
