/*
 * Created on 12-Jul-2005
 *
 */
package freerails.world.common;

import freerails.util.Immutable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Immutable
public class ImHashSet<E extends FreerailsSerializable> implements
        FreerailsSerializable {

    private static final long serialVersionUID = -4098862905501171517L;

    private final HashSet<E> hashSet;

    public ImHashSet(HashSet<E> hashSet) {
        this.hashSet = new HashSet<>(hashSet);
    }

    public ImHashSet(E... values) {
        this.hashSet = new HashSet<>();
        Collections.addAll(hashSet, values);
    }

    public ImHashSet(List<E> values) {
        this.hashSet = new HashSet<>();
        hashSet.addAll(values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ImHashSet))
            return false;

        final ImHashSet imHashSet = (ImHashSet) o;

        return hashSet.equals(imHashSet.hashSet);
    }

    @Override
    public int hashCode() {
        return hashSet.hashCode();
    }

    public boolean contains(E e) {
        return hashSet.contains(e);
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            final Iterator<E> it = hashSet.iterator();

            public boolean hasNext() {
                return it.hasNext();
            }

            public E next() {
                return it.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();

            }

        };
    }

}
