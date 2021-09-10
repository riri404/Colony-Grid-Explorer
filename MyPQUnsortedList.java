/***
 * Priority Queue of key value pairs - Unsorted Linked List implementation
 * @param <K> - keys
 * @param <V> - values
 */
public class MyPQUnsortedList<K extends Comparable, V>  extends MyPQ<K, V> {
    public MyPQUnsortedList() {
        container = new LinkedList<Pairs<K, V>>();
    }
}

