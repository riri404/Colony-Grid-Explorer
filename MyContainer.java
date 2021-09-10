/***
 * The plan is to have a generalized interface for containers
 * and call these methods from within the PQ instances
 * @param <T> - object associated with container
 */
public interface MyContainer<T extends Comparable> {
    /***
     * Generalized insertion method
     * @param obj - object to put into container
     */
    public void insert(T obj);

    /***
     * Generalized size method
     * @return - the number of elements in the container
     */
    public int getSize();

    /***
     * Generalized isEmpty method
     * @return - true if no elements, false otherwise
     */
    public boolean isEmpty();

    /***
     * Generalized remove method
     * @param remIdx - index to remove at
     * @return - value removed, null if unable to remove
     */
    public T remove(int remIdx);

    /***
     * Generalized get method
     * @param idx - retrieve the element at position idx
     * @return - the element at requested position or null
     */
    public T get(int idx);

    /***
     * Generalized find method
     * @param obj - find the requested object in the container
     * @return - index of requested object, -1 if not found
     */
    public int find(T obj);

    /***
     * Generalized min method
     * @return - retrieve he minimal element in container
     */
    public T min();

    /***
     * Generalized removeMin method
     * @return - retrieve the minimal element in the container
     * and remove it
     */
    public T removeMin();
}

