/*
 * Created on 04-Jul-2005
 *
 */
package freerails.world.common;

import freerails.util.Immutable;

import java.util.Arrays;

/**
 * An immutable list of ints.
 *
 * @author Luke
 */
@Immutable
public class ImInts implements FreerailsSerializable {

    private static final long serialVersionUID = -7171552118713000676L;

    private final int ints[];

    /**
     *
     * @param i
     */
    public ImInts(int... i) {
        this.ints = i.clone();
    }

    /**
     *
     * @param i
     * @return
     */
    public static ImInts fromBoolean(boolean... i) {
        int[] ii = new int[i.length];
        for (int j = 0; j < i.length; j++) {
            ii[j] = i[j] ? 1 : 0;
        }
        return new ImInts(ii);
    }

    /**
     *
     * @return
     */
    public int size() {
        return ints.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ImInts))
            return false;

        final ImInts other = (ImInts) o;

        return Arrays.equals(ints, other.ints);
    }

    @Override
    public int hashCode() {
        return ints.length;
    }

    /**
     *
     * @param i
     * @return
     */
    public int get(int i) {
        return ints[i];
    }

    /**
     *
     * @return
     */
    public ImInts removeLast() {
        int[] newInts = new int[ints.length - 1];
        System.arraycopy(ints, 0, newInts, 0, newInts.length);
        return new ImInts(newInts);
    }

    /**
     *
     * @param extra
     * @return
     */
    public ImInts append(int... extra) {
        int[] newInts = new int[ints.length + extra.length];
        System.arraycopy(ints, 0, newInts, 0, ints.length);
        System.arraycopy(extra, 0, newInts, ints.length, extra.length);
        return new ImInts(newInts);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append("[");
        for (int i = 0; i < ints.length; i++) {
            sb.append(ints[i]);
            if (i + 1 < ints.length)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Returns the sum of the ints stored in the list.
     * @return 
     */
    public int sum() {
        int sum = 0;
        for (int anInt : ints) {
            sum += anInt;
        }
        return sum;
    }

}
