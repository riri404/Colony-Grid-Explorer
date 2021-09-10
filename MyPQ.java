/***
 * Abstract class for priority queue
 * @param <K> - Comparable class keys
 * @param <V> - Value class 
 * The other priority queues will set their container to the correct data structure
 */
public abstract class MyPQ<K extends Comparable, V> {
    protected MyContainer<Pairs<K, V>> container;
    /***
     * Insert a key, value pair into the priority queue
     * @param key - key
     * @param val - value
     */
    public void insert(K key, V val) {
        container.insert(new Pairs<>(key, val));
    }

    /***
     * Retrieve the value associated with the smallest key
     * @return - smallest k-v pair if found, null otherwise
     */
    public Pairs<K, V> min() {
        return container.min();
    }

    /***
     * Remove the value associated with the smallest key
     * @return - the smallest k-v pair and remove it
     */
    public Pairs<K, V> removeMin() {
        return container.removeMin();
    }

    public int size() {
        return container.getSize();
    }

    public boolean isEmpty() {
        return container.isEmpty();
    }
}