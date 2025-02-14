package main.java.LeetCodeProblem;

import java.util.HashMap;
import java.util.Map;

public class LruCache {

    int capacity;
    Map<Integer,Node> cache = new HashMap<>();
    Node head , tail;


    public LruCache(int capacity){

        this.capacity = capacity;
        this.head = new Node(0,0);
        this.tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }

    public void put(int key, int value){

        if(cache.containsKey(key))
                remove(cache.get(key));

        if(cache.size() == this.capacity)
            remove(this.tail.prev);

        insert(new Node(key,value));

    }

    public int get(int key){

        if(!cache.containsKey(key))
            return -1;

        Node node = cache.get(key);
        remove(node);
        insert(node);

        return node.value;

    }

    public void insert(Node node){

        cache.put(node.key, node);
        node.next = this.head.next;
        this.head.next.prev = node;
        this.head.next = node;
        node.prev = this.head;

    }

    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        cache.remove(node.key);
    }

    public class Node{

        int key;
        int value;

        Node next, prev;

        public Node(int key, int value){
            this.key  = key;
            this.value = value;
        }
    }



    public static void main(String[] args){

        LruCache obj = new LruCache(0);

        String[] actions = new String[]{"LRUCache","put","put","get","put","get","put","get","get","get"};
        Integer[][] values = new Integer[][]{{2},{1,1},{2,2},{1},{3,3},{2},{4,4},{1},{3},{4}};

        for(int i=0;i<actions.length;i++){

            switch(actions[i]){

                case "LRUCache":
                    obj = new LruCache(values[i][0]);
                    break;

                case "put":
                    obj.put(values[i][0],values[i][1]);
                    break;
                case "get":
                    System.out.println("Value for key = "+values[i][0] + " = " + obj.get(values[i][0]));
            }

        }

    }
}
