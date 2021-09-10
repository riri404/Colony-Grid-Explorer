/***
 * A simple array class - this is the unsorted version
 * The sorted version will inherit and override some methods
 * @param <T> - have an array of T
 */
public class Array<T extends Comparable> implements MyContainer<T>{
    protected T [] array;
    // maximum size of array
    protected int capacity;

    protected int size;

    /***
     * Allocate an array with the given size
     * @param initSize - size >= 0
     */
    public Array(int initSize) {
        array = (T[]) new Comparable[initSize];
        capacity = initSize * 2;
        size = initSize;
    }

    /***
     * Default constructor - just initializes an empty list
     */
    public Array() {
        this(0);
    }

    /***
     * Insert obj into the array. Unsorted version, so we'll append to the end
     * @param obj - object to insert
     */
    public void insert(T obj) {
        if(size >= capacity) {
            allocateMore();
        }

        // Simply append because unsorted
        array[size] = obj;
        size++;
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

    /***
     * Helper to retrieve the index of the minimum element
     * @return - -1 if not valid, index of the minimum element if found
     */
    public int getMinIdx() {
        T minVal = null;
        int minIdx = -1;
        for(int idx = 0; idx < size; idx++) {
            if(array[idx].compareTo(minVal) < 0) {
                minVal = array[idx];
                minIdx = idx;
            }
        }

        return minIdx;
    }

    /***
     * Remove array[idx]
     * @param remIdx - index to remove
     * @return - null if out of bounds, actual element removed
     */
    public T remove(int remIdx) {
        if(remIdx < 0 || remIdx >= size) {
            return null;
        }

        if(size * 4 < capacity) {
            allocateLess();
        }

        T res = array[remIdx];
        pushLeft(remIdx);
        return res;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int idx) {
        if(idx < 0 || idx >= size) {
            return null;
        }

        return array[idx];
    }

    /***
     * Return index of obj in the array
     * @param obj - object to locate
     * @return index if found, -1 otherwise
     */
    public int find(T obj) {
        int res = -1;
        for(int idx = 0; idx < size; idx++) {
            if(array[idx].equals(obj)) {
                res = idx;
                break;
            }
        }

        return res;
    }

    /***
     * Either double or allocate two elements in the array
     * Note: size doesn't change
     */
    protected void allocateMore() {
        if(capacity == 0) {
            capacity = 2;
        } else {
            capacity += capacity;
        }

        // Allocate new array
        T [] newArr  = (T[]) new Comparable[capacity];
        copyElements(newArr);

        array = newArr;
    }

    /***
     * Halve our current capacity
     * Note: size doesn't change
     */
    protected void allocateLess() {
        if(capacity < 2 ) {
            return;
        }

        // halve the capacity, allocate, then re-assign
        capacity /= 2;
        T [] newArr = (T[]) new Comparable[capacity];
        copyElements(newArr);

        array = newArr;

    }

    /***
     * Copy all elements from current array into new one
     * @param dst - destination array to copy to
     */
    protected void copyElements(T[] dst) {
        for(int i = 0; i < size; i++) {
            dst[i] = array[i];
        }
    }

    /***
     * Starting at index idx, copy everything over to the left
     * @param leftIdx - index to be replaced
     */
    protected void pushLeft(int leftIdx) {
        for(int idx = leftIdx + 1; idx < size; idx++) {
            array[idx - 1] = array[idx];
        }

        size--;
    }

    /***
     * Starting at index idx, copy everything over to the right
     * @param rightIdx - index to be replaced
     */
    protected void pushRight(int rightIdx) {
        T prevVal = array[rightIdx];
        for(int idx = rightIdx; idx < size; idx++) {
            T nextVal = array[idx + 1];
            array[idx + 1] = prevVal;
            prevVal = nextVal;
        }

        size++;
    }
}
