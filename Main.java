package Assignment2;
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    public LinkedList() {
        this.head = null;
    }

    public void insertAtPos(int data, int pos) {
        Node newNode = new Node(data);
        if (pos == 1) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;
        int count = 1;
        while (current != null && count < pos - 1) {
            current = current.next;
            count++;
        }

        if (current == null) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    public void deleteAtPosition(int pos) {
        if (head == null) {
            throw new IndexOutOfBoundsException("List is empty");
        }

        if (pos == 1) {
            head = head.next;
            return;
        }

        Node current = head;
        int count = 1;
        while (current != null && count < pos - 1) {
            current = current.next;
            count++;
        }

        if (current == null || current.next == null) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }

        current.next = current.next.next;
    }

    public void deleteAfterNode(int prevNodeData) {
        Node current = head;
        while (current != null && current.data != prevNodeData) {
            current = current.next;
        }

        if (current == null || current.next == null) {
            throw new IllegalArgumentException("Node not found or no node to delete after");
        }

        current.next = current.next.next;
    }

    public int searchNode(int value) {
        Node current = head;
        int position = 1;
        while (current != null) {
            if (current.data == value) {
                return position;
            }
            current = current.next;
            position++;
        }
        return -1;
    }
}

class Stack {
    private Node top;

    public Stack() {
        this.top = null;
    }

    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (top == null) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        int data = top.data;
        top = top.next;
        return data;
    }

    public int peek() {
        if (top == null) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        return top.data;
    }
}


public class Main {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.insertAtPos(10, 1);
        ll.insertAtPos(20, 2);
        ll.insertAtPos(30, 3);
        ll.insertAtPos(25, 3);
        System.out.println("LinkedList after insertions:");
        Node current = ll.head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");

        ll.deleteAtPosition(3);
        System.out.println("LinkedList after deletion at position 3:");
        current = ll.head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");

        ll.deleteAfterNode(10);
        System.out.println("LinkedList after deleting after node with data 10:");
        current = ll.head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");

        int pos = ll.searchNode(20);
        System.out.println("Node with value 20 found at position: " + pos);

        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack top element: " + stack.peek());
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Stack top element after pop: " + stack.peek());
    }
}