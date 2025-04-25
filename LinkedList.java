import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {	
	
	/**
        * LinkedList
	* implementing custom LinkedList class like a SinglyLinkedList class in java
        * it supports various features such as insert (front, at position, end), remove (front, at position, end), get, search, reverse, display and iterator that supports enhanced-for loops
	* Operations:
        * -> insert(T): insert node in main three places such as front, at position, end.
	* -> remove(): remove node from a LinkedList at three places such as front, at position, end.
        * -> get(P): return a node element at a specified position.
	* -> search(T): search a node element in a LinkedList.
        * -> reverse(): reverse a LinkedList like in-place reversal.
	* -> iterator(): it going to return a custom iterator object that supports enhanced-for loops.
        * -> size(): return a size of a LinkedList.
	* -> isEmpty(): checks whether a LinkedList is empty or not.
	*/
	
    //defining a Node class for representing individual series of Nodes that form a well structured LinkedList
	private class Node<T> {
	     T data;
	     Node<T> next;
	     Node(T data) {
	         this.data = data;
	         this.next = null;
     	}
	}
	
	private Node<T> head;
	private Node<T> tail;
    private int size;
	LinkedList() {
	    this.size = 0;
	    this.head = this.tail = null;
	}	

	//insert node at front of the LinkedList
	public void insertFront(T data) {
	    Node<T> newNode = new Node<>(data);
	    newNode.next = head;
	    if(head == null) {
	        tail = newNode;
	    }
	    head = newNode;
	    size++;
	}

	//insert a node at specified position that is given in a function parameter
	public void insertAtPosition(int position, T data) throws IllegalArgumentException {
	    if(position < 0 || position > size) {
	        throw new IllegalArgumentException("position cannot be negative as well as not more then or equal to a linkedlist length because its a 0 based index");
	    }
	    if(position == 0) {
	        insertFront(data);
	        return;
	    }
	    if(position == size) {
	        insertLast(data);
	        return;
	    }
	    Node<T> temp = head;
	    for(int i=0; i<position-1 && temp != null; i++) {
	        temp = temp.next;
	    }
	    Node<T> newNode = new Node<>(data);
	    newNode.next = temp.next;
	    temp.next = newNode;
	    size++;
	}

	//insert a node at end of the LinkedList
	public void insertLast(T data) {
	    if(head == null) {
	        insertFront(data);
	        return;
	    }
	    Node<T> newNode = new Node<>(data);
	    tail.next = newNode;
	    tail = newNode;
	    size++;
	}

	//remove a front most node means a first node from a LinkedList
	public void removeFront() throws Exception {
	    if(head == null) {
	        throw new Exception("LinkedList is empty. cannot delete elements");
	    }
	    if(size == 1) {
	        head = tail = null;
	        size--;
	        return;
	    }
	    head = head.next;
	    size--;
	}

	//remove a specified position node from a given LinkedList
	public void removeAtPosition(int position) throws Exception {
	    if(head == null) {
	        removeFront();
	        return;
	    }
	    if(position < 0 || position >= size) {
	        throw new IllegalArgumentException("Position cannot be negative as well as not more then or equal to a  LinkedList length because its a 0 based index");
	    }
	    if(position == 0) {
	        removeFront();
	        return;
	    }
	    if(position == size-1) {
	        removeLast();
	        return;
	    }
	    Node<T> temp = head;
	    Node<T> prev = null;
	    for(int i=0; i<position && temp != null; i++) {
	        prev = temp;
	        temp = temp.next;
	    }
	    if(temp != null) {
	        prev.next = temp.next;
	        size--;
	    }
	}

	//remove a last most node from a LinkedList
	public void removeLast() throws Exception {
	    if(head == null || size == 1) {
	        removeFront();
	        return;
	    }
	    Node<T> temp = head;
	    while (temp.next != tail) {
	        temp = temp.next;
	    }
	    temp.next = null;
	    tail = temp;
	    size--;
	}

	//get a node at specified position
	public T get(int position) throws Exception {
	    if(head == null) {
	        throw new Exception("LinkedList is Empty");
	    }
	    if(position < 0 || position >= size) {
	        throw new Exception("Position cannot be negative as well as not be more then or equal to a LinkedList length");
	    }
	    if(position == 0) {
	        return head.data;
	    }
	    if(position == size-1) {
	        return tail.data;
	    }
	    Node<T> temp = head;
	    for(int i=0; i<position && temp != null; i++) {
	        temp = temp.next;
	    }
	    return temp.data;
	}

	//to search a specified node in a given LinkedList
	public boolean search(T element) throws Exception {
	    if(head == null) {
	        throw new Exception("LinkedList is Empty");
	    }
	    Node<T> temp = head;
	    while (temp != null) {
	        if(temp.data.equals(element)) {
	            return true;
	        }
	        temp = temp.next;
	    }
	    return false;
	}

	//reverse a LinkedList like in-place reversal
	public void reverse() {
	    Node<T> prev  = null;
	    Node<T> nextNode = null;
	    Node<T> current = head;
	    while (current != null) {
	        nextNode = current.next;
	        current.next = prev;
	        prev = current;
	        current = nextNode;
	    }
	    head = prev;
	}

	//find the length of the LinkedList
	public int size() {
	    return this.size;
	}

	//checks whether LinkedList is empty or not
	public boolean isEmpty() {
	    return size == 0;
	}

	//display entire LinkedList
	public void display() {
	    if(head == null) return;
	    Node<T> temp = head;
	    while (temp != null) {
	        System.out.print(temp.data + " -> ");
	        temp = temp.next;
	    }
	    System.out.println("End");
	}

	//get a custom iterator object that supports such as enabling enhanced-for loops
	public Iterator<T> iterator() {
	    return new MyIterator();
	}

	//defining a custom iterator class
	private class MyIterator implements Iterator<T> {
	    private Node<T> current;
	    MyIterator() {
	        this.current = head;
	    }
	    
	    @Override 
	    public boolean hasNext() {
	        return this.current != null;
	    }
	    @Override
	    public T next() {
	        if(!hasNext()) {
	            throw new NoSuchElementException();
	        }
	        T data = current.data;
	        current = current.next;
	        return data;
	    }
	}

	//overriding toString() method
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[ ");
	    if(head == null) {
	       return sb.append("]").toString();
	    }
	    Node<T> temp = head;
	    while (temp != null) {
	        sb.append(temp.data).append(" -> ");
	        temp = temp.next;
	    }
	    sb.append("End").append("]");
	    return sb.toString();
	}
}
