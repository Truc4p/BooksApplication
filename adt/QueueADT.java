package BooksApp.adt;

public class QueueADT<E> implements AbstractQueue<E> {
    private class Node<E>{
        private E element;
        private Node<E> next;
        private Node(E element){
            this.element = element;
            this.next = null;
        }
    }

    private Node<E> head;
    private int size;

    public QueueADT(){
        this.head = null;
        this.size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> newNode = new Node<>(element);

        if(this.isEmpty()){
            this.head = newNode;
        } else {
            Node<E> tempNode = this.head;
            while(tempNode.next != null){
                tempNode = tempNode.next;
            }
            tempNode.next = newNode;
        }

        this.size++;
    }

    @Override
    public E poll() {
        if(this.isEmpty()){
            throw new IllegalStateException("Queue is currently empty.");
        }

        E oldElement = this.head.element;

        Node<E> tempNode = head;
        this.head = this.head.next;
        tempNode.next = null;
        this.size--;
        return oldElement;

    }

    @Override
    public E peek() {

        if(this.isEmpty()){
            throw new IllegalStateException("Queue is currently empty.");
        }

        return this.head.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("[");

        Node<E> tempNode = head;

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

class QueueADTRunner {
    public static void main(String[] args) {
    }
}