package com.company;

public class LinkedListStack<T> {

    class Node<T>{
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next= null;
        }
    }

    private Node<T> top;

    public LinkedListStack() {
        this.top = null;
    }

    public void push(T data){
        Node<T> newNode = new Node<>(data);

        newNode.next = top;
        top = newNode;
    }

    public T pop(){
        if(top == null){
            return null;
        }

        T poppedItem = top.data;
        top = top.next;

        return poppedItem;
    }

    public void display(){
        Node<T> currNode = top;

        while(currNode != null){
            System.out.print(currNode.data + "->");
            currNode = currNode.next;
        }

        System.out.println();
    }

    public boolean isEmpty(){
        return top == null;
    }

    public T stackTop(){
        return top == null ? null : top.data;
    }
}







