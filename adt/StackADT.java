package BooksApp.adt;

import java.util.NoSuchElementException;

public class StackADT<E> implements AbstractStack<E> {
    private class Node<E>{
        private E element;
        private Node<E> next;
        private Node(E element){
            this.element = element;
            this.next = null;
        }
    }

    private int size;
    private Node<E> top;

    public StackADT(){
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.next = top;
        this.top = newNode;
        this.size++;
    }

    @Override
    public E pop() {

        if(this.isEmpty()){
            throw new NoSuchElementException("Stack is empty.");
        }

        E oldElement = this.top.element;

        // Option 1
        /*Node<E> tempNode = top;
        this.top = this.top.next;
        tempNode.next = null;
        this.size--;*/

        // Option 2
        Node<E> tempNode = top.next;
        this.top.next = null;
        this.top = tempNode;
        this.size--;

        return oldElement;
    }

    @Override
    public E peek() {
        if(this.isEmpty()){
            throw new NoSuchElementException("Stack is empty.");
        }

        return this.top.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if(this.size == 0)
            return true;
        return false;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("[");

        Node<E> tempNode = top;

        while(tempNode != null){
            result.append(tempNode.element);

            if(tempNode.next != null){
                result.append(", ");
            }

            tempNode = tempNode.next;
        }

        result.append("]");
        return result.toString();
    }
}

class StackADTRunner{
    public static void main(String[] args) {
    }
}
