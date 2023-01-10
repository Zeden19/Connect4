/*
Author: Arjun Sharma (251240847)
Date: October 19, 2022
A node class that is used for separate chaining
 */
public class Node <T> {
        private Node<T> next;
        private T data;

    /**
     * The constructor for the node
     * @param data: the data that you want to store in the node
     */
    public Node(T data) {
        this.data = data;
        next = null;
    }

    /**
     * Getting the next node
     * @return returning the next node that was assigned
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * setting the next node
     * @param node: the node that you want to next to point to
     */
    public void setNext(Node<T> node) {
        next = node;
    }

    /**
     * The data that is stored in the node
     * @return data stored in the node
     */
    public T getData() {
        return data;
    }

}
