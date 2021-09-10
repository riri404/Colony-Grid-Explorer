/***
 * Priority Queue of key value pairs - Sorted Linked List implementation
 * @param <K> - keys
 * @param <V> - values
 */
public class MyPQSortedList<K extends Comparable, V>  extends MyPQ<K, V> {
    public MyPQSortedList() {
        container = new SortedLinkedList<Pairs<K, V>>();
    }
}

