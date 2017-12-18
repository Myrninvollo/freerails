/*
 * Created on 27-Jul-2005
 *
 */
package freerails.util;

import java.util.SortedMap;

/**
 *
 * @author jkeller1
 * @param <T>
 */
public class List1DDiff<T> extends ListXDDiffs<T> implements List1D<T> {

    private static final long serialVersionUID = -6058018396890452219L;

    private final List1D<T> underlyingList;

    /**
     *
     * @param diffs
     * @param list
     * @param listID
     */
    public List1DDiff(SortedMap<ListKey, Object> diffs, List1D<T> list,
                      Enum listID) {
        super(diffs, listID);
        underlyingList = list;
    }

    /**
     *
     * @param i
     * @return
     */
    public T get(int i) {
        return get(new int[]{i});
    }

    @Override
    Object getUnderlyingList() {
        return underlyingList;
    }

    /**
     *
     * @return
     */
    public int size() {
        return super.size(new int[0]);
    }

    @Override
    T uGet(int... i) {
        if (i.length != 1)
            throw new IllegalArgumentException();
        return underlyingList.get(i[0]);
    }

    /**
     *
     * @param element
     * @return
     */
    public int add(T element) {
        return super.addElement(element);
    }

    /**
     *
     * @return
     */
    public T removeLast() {
        return super.removeLast();
    }

    /**
     *
     * @param i
     * @param element
     */
    public void set(int i, T element) {
        super.set(element, i);

    }

    @Override
    int getUnderlyingSize(int... dim) {
        if (dim.length != 0)
            throw new IllegalArgumentException(String.valueOf(dim.length));

        return underlyingList.size();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof List1D && Lists.equals(this, (List1D) obj);
    }

    @Override
    public int hashCode() {
        return size();
    }

}
