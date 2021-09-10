/***
 * A pair class that allows for abstraction of a comparable Key + Value type
 * @param <K> - Key (comparable)
 * @param <V> - Value
 */
public class Pairs<K extends Comparable, V> implements Comparable {
    public K first;
    public V second;

    /***
     * Simple constructor for key + value pair
     * @param keySet - set first to this
     * @param valSet - set second to this
     */
    public Pairs(K keySet, V valSet) {
        first = keySet;
        second = valSet;
    }

    public Pairs() {
        this(null, null);
    }

    /***
     * Compare current to next.
     * Note that it's as simple as just comparing the
     * key field to the other key field
     * @param that - Pair object
     * @return - returns negative if this < that, zero if equivalent keys,
     * positive if this > that</>
     */
    @Override
    public int compareTo(Object that) {
        if(that == null || that.getClass() != Pairs.class) {
            // put negative
            return -1;
        }

        Pairs<K, V> thatObj = (Pairs<K, V>)that;
        return first.compareTo(thatObj.first);
    }

    /***
     * Method to convert Pair object to string
     * @return - stringified pair
     */
    public String toString() {
        return "(" + first.toString() + ", " + second.toString() + ")";
    }

    /***
     * Method to compare two pairs for equality
     * @param that - other object to consider
     * @return true if equal, false otherwise
     */
    public boolean equals(Object that) {
        if(that.getClass() != Pairs.class) {
            return false;
        }

        Pairs<K, V> thatPair = (Pairs)that;
        return first.equals(thatPair.first) && second.equals(thatPair.second);
    }
}
