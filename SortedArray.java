/***
 * In the SortedArray we need only to override insert, getMinIdx, min, and
 * removeMin
 * @param <T> - values to store
 */
public class SortedArray<T extends Comparable> extends Array<T> {
    /***
     * Identical constructor to base class
     * @param initSize - initial size
     */
    public SortedArray(int initSize) {
        super(initSize);
    }

    /***
     * Identical constructor to base class
     */
    public SortedArray() {
        super();
    }

    /***
     * Overrided version of insert to force minimal elements at the end
     * @param obj - object to insert
     */
    @Override
    public void insert(T obj) {
        // try to insert in a way where the smallest element is at the end
        if(size >= capacity) {
            allocateMore();
        }

        int insIdx = size;
        // array is in descending order, so scan for next largest element
        // from right to left
        for(int idx = size - 1; idx >= 0; idx--, insIdx--) {
            if(array[idx].compareTo(obj) > 0) {
                // found element bigger. therefore, should go here
                break;
            }
        }

        pushRight(insIdx);
        array[insIdx] = obj;
    }

    /***
     * Because minimum elements are at the end, this step is easier
     * @return - index of last element, which is just the last
     */
    public int getMinIdx() {
        return size - 1;
    }

    /***
     * Retrieve the minimum element, null if it doesn't exist
     * @return - minimum element
     */
    public T min() {
        int minIdx = getMinIdx();
        if(minIdx == -1) {
            return null;
        } else {
            return array[minIdx];
        }
    }

    /***
     * Remove and return the smallest element in the array
     * @return - minimum element, null if not found
     */
    public T removeMin() {
        int minIdx = getMinIdx();
        if(minIdx == -1) {
            return null;
        } else {
            return remove(minIdx);
        }
    }
}