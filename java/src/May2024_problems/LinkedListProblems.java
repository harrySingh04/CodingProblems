package May2024_problems;

public class LinkedListProblems {



    public boolean checkPalindrome(LinkedList list){

        Node fastPointer = list.head;

        Node slowPointer = list.head;

        while(fastPointer != null && fastPointer.getNext() != null){

            fastPointer = fastPointer.getNext();
            slowPointer = slowPointer.getNext();
            fastPointer = fastPointer.getNext();
        }

        Node secondList = reverseLinkedList(slowPointer);
        return compareLists(list.head, secondList);

    }

    public Node reverseLinkedList(Node pointer){

        Node prev = null;
        Node next = null;
        Node currentNode = pointer;

        while(currentNode != null){
            next = currentNode.getNext();
            currentNode.setNext(prev);
            prev = currentNode;
            currentNode =  next;

        }

        return prev;
    }

    public boolean compareLists(Node firstList, Node secondList){

        while(secondList != null && firstList.getNext() != null){
            if(firstList.getData() != secondList.getData())
                return false;
            firstList = firstList.getNext();
            secondList = secondList.getNext();
        }

        return true;
    }

}
