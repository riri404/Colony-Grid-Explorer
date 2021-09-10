/***
 * A sorted double linked list. Similar to SortedArray we override
 * insert, min, removeMin, and getMinNode
 */
public class SortedLinkedList<T extends Comparable> extends LinkedList<T> {
    public SortedLinkedList() {
        super();
    }

    /***
     * Purposely insert smallest elements at the front of the double linked list
     * @param obj - object to put into container
     */
    public void insert(T obj) {
        if(head == null || obj.compareTo(head.data) < 0) {
            head = new Node(obj, null, head);
        } else {
            // Walk list s.t we get the first element > obj then
            // insert
            Node curr = head.next, prev = head;
            while(curr != null && curr.data.compareTo(obj) <= 0) {
                prev = curr;
                curr = curr.next;
            }

            // insert in a way where
            // prev -> new node -> curr
            // guaranteed that new node isn't at head based on first statement
            new Node(obj, prev, curr);
        }

        size++;
    }

    /***
     * Head is now guaranteed to be the smallest
     * @return - smallest node
     */
    public Node getMinNode() {
        return head;
    }

    /***
     * Get the value of the smallest node in the linked list
     * @return - content of the smallest node
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
     * Remove smallest node in linked list
     * @return - content of smallest element
     */
    public T removeMin() {
        Node minNode = getMinNode();
        return removeNode(minNode);
    }
}