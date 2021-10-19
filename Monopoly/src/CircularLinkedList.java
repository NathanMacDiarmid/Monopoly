public class CircularLinkedList {
    private Node head = null;
    private Node tail = null;
    private int size;

    public CircularLinkedList() {
        this.size = 0;
    }

    public void addNode(int position, Property property) {
        Node newNode = new Node(position, property);
        this.size++;

        if (this.head == null) {
            this.head = newNode;
        }
        else {
            this.tail.next = newNode;
        }

        this.tail = newNode;
        this.tail.next = head;
    }

    public Node getHead() {
        return this.head;
    }

    public Node getTail() {
        return this.tail;
    }

    public int getSize() {
        return this.size;
    }

}
