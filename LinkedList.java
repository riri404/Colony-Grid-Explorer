/***
 * An unsorted double linked list
 */
public class LinkedList<T extends Comparable> implements MyContainer<T> {
    protected Node head;
    protected int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    /***
     * Insertion into an unsorted doubly linked list
     * @param obj - object to put into container
     */
    public void insert(T obj) {
        // because it's unsorted just insert at the head
        head = new Node(obj, null, head);
        size++;
    }

    /***
     * Getter for size
     * @return - number of elements in container
     */
    public int getSize() {
        return size;
    }

    /***
     * Determine if container is empty
     * @return - true if so, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /***
     * Remove the node at position idx
     * @param idx - index to remove
     * @return - content of removed node
     */
    public T remove(int idx) {
        Node found = moveTo(idx);
        return removeNode(found);
    }

    /***
     * Go through the process of removing a node in the linked list
     * @param node - node to remove
     * @return - data within removed node
     */
    public T removeNode(Node node) {
        T res = null;
        if(node != null) {
            if(head == node) {
                // update head node first
                head = head.next;
            }

            // now remove node and adjust size
            res = node.data;
            node.remove();
            size--;

        }

        return res;
    }

    /***
     * Retrieve data at the requested position
     * @param idx - retrieve the element at position idx
     * @return - data, null if bad index
     */
    public T get(int idx) {
        Node found = moveTo(idx);
        T res = null;
        if(found != null) {
            res = found.data;
        }

        return res;
    }

    /***
     * Try to find obj in our linked list
     * @param obj - find the requested object in the container
     * @return - position in the linked list if found, -1 if not found
     */
    public int find(T obj) {
        int res = -1;
        Node curr = head;
        for(int idx = 0; idx < size; idx++, curr = curr.next) {
            if(curr.data.equals(obj)) {
                res = idx;
                break;
            }
        }

        return res;
    }

    /***
     * Find the minimal element in the linked list
     * @return - value of the minimal element
     */
    public T min() {
        Node minNode = getMinNode();
        T res = null;
        if(minNode != null) {
            res = minNode.data;
        }

        return res;
    }

    /***
     * Find the minimal element in the linked list and remove it
     * @return - value of the minimal element
     */
    public T removeMin() {
        Node minNode = getMinNode();
        return removeNode(minNode);
    }

    /***
     * Retrieve the node that contains the smallest data in the list
     * @return - null if no such node or the node itself
     */
    protected Node getMinNode() {
        if(head == null) {
            return null;
        }

        Node minNode = head;
        T minVal = head.data;
        Node curr = head.next;
        while(curr != null) {
            if(curr.data.compareTo(minVal) < 0) {
                minVal = curr.data;
                minNode = curr;
            }

            curr = curr.next;
        }

        return minNode;
    }

    /***
     * Given a position in the linked list, retrieve the node
     * at the requested position
     * @param idx - position to move to (zero indexed)
     * @return - node that is found
     */
    protected Node moveTo(int idx) {
        if(idx < 0 || idx >= size) {
            return null;
        }

        Node curr = head;
        for(; idx > 0; idx--) {
            curr = curr.next;
        }

        return curr;
    }
    /***
     * Inner double node class defined for linked list traversal
     */
    protected class Node {
        T data;
        Node next;
        Node prev;

        /***
         * Constructor for a node. Note it connects neighbors too
         * @param newData - data to set
         * @param left - node that should be on the left
         * @param right - node that should be on the right
         */
        public Node(T newData, Node left, Node right) {
            data = newData;
            next = right;
            if(right != null) {
                right.prev = this;
            }

            prev = left;
            if(left != null) {
                left.next = this;
            }
        }

        public Node(T newData) {
            this(newData, null, null);
        }

        /***
         * Remove the neighbor links to this node
         */
        public void remove() {
            if(next != null) {
                next.prev = prev;
            }

            if(prev != null) {
                prev.next = next;
            }
        }
    }
}

