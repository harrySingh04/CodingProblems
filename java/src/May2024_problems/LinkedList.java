package May2024_problems;

public class LinkedList {

    Node head;

    public void insertAtHead(Node node){

        Node currentNode = this.head;
        node.setNext(currentNode);
        this.head = node;
    }

    public void createLinkedList(int[] lst) {
        for (int i = lst.length - 1; i >= 0; i--) {
            Node newNode = new Node(lst[i]);
            insertAtHead(newNode);
        }
    }

    public void displayLinkedList(){

        Node currentNode = this.head;

        System.out.println("Linked List display ");
        while(currentNode != null){
            System.out.println(currentNode.getData());
            if(currentNode.getNext() != null)
                System.out.println("->");
            currentNode = currentNode.getNext();
        }
    }
}
