import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {
	  
	  private Node head, tail;
	  private int size;
	  public DoublyLinkedList() {
	      this.size = 0;
	      this.head = this.tail = null;
	  }
	  
	  //defined node class
	  private class Node {
	      T data;
	      Node next;
	      Node prev;
	      public  Node(T data) {
	          this.data = data;
	          this.next = null;
	          this.prev = null;
	      }
	  }
	  
	  //insert node at front
	  public void insertFront(T data) {
	      Node newNode = new Node(data);
	      if(isHeadNull(head)) {
	          head = newNode;
	          tail = head;
	          size++;
	          return;
	      }
	      newNode.next = head;
	      head.prev = newNode;
	      head = newNode;
	      size++;
	  }
	  
	  //insert node at position
	  public void insertAtPosition(int position, T data) throws IllegalArgumentException {
	      if(position < 0 || position > size) {
	          throw new IllegalArgumentException("Invalid position: position must be between 0 and size");
	      }
	      if(position == 0) {
	          insertFront(data);
	          return;
	      }
	      if(position == size) {
	          insertLast(data);
	          return;
	      }
	      Node newNode = new Node(data);
	      Node temp = head;
	      for(int i=0; i<position-1 && temp != null; i++) {
	          temp = temp.next;
	  }
	      newNode.next = temp.next;
	      temp.next.prev = newNode;
	      temp.next = newNode;
	      newNode.prev = temp;
	      size++;
	  }
	  
	  //insert node at end
	  public void insertLast(T data) {
	      if(isHeadNull(head)) {
	          insertFront(data);
	          return;
	      }
	      Node newNode = new Node(data);
	      tail.next = newNode;
	      newNode.prev = tail;
	      tail = newNode;
	      size++;
	  }
	  
	  //remove a first node
	  public void removeFront() throws Exception {
	      if(isHeadNull(head)) {
	          throw new NoSuchElementException();
	      }
	      if(size == 1) {
	          head = tail = null;
	          size--;
	          return;
	      }
	      Node temp = head;
	      head = head.next;
	      temp.next = null;
	      head.prev = null;
	      size--;
	  }
	  
	  //remove a specified position node from a DoublyLinkedList
	  public void removeAtPosition(int position) throws Exception {
	      if(isHeadNull(head)) {
	          throw new NoSuchElementException();
	      }
	      if(!isSafe(position)) {
	          throw new IllegalArgumentException("Invalid position: position must be between 0 and size-1");
	      }
	      if(position == 0) {
	          removeFront();
	          return;
	      }
	      if(position == size-1) {
	          removeLast();
	          return;
	      }
	      Node temp = head;
	      for(int i=0; i<position && temp != null; i++) {
	          temp = temp.next;
	      }
	      temp.prev.next = temp.next;
	      if(temp.next != null) {
	          temp.next.prev = temp.prev;
	      }
	      size--;
	  }
	  
	  //remove a last node
	  public void removeLast() throws Exception {
	      if(isHeadNull(head)) {
	          throw new NoSuchElementException();
	      }
	      if(size == 1) {
	          removeFront();
	          return;
	      }
	      tail = tail.prev;
	      tail.next = null;
	      size--;
	  }
	  
	  //get data of a specified position node
	  public T get(int position) throws Exception {
	      if(isHeadNull(head)) {
	          throw new NoSuchElementException();
	      }
	      if(!isSafe(position)) {
	          throw new IllegalArgumentException("Invalid position: position must be between 0 and size-1");
	      }
	      if(position == 0) {
	          return head.data;
	      }
	      if(position == size-1) {
	          return tail.data;
	      }
	      Node temp = head;
	      for(int i=0; i<position && temp != null; i++) {
	          temp = temp.next;
	      }
	      return temp.data;
	  }
	  
	  //search element in a forward direction
	  public boolean search(T element) throws Exception {
	      if(isHeadNull(head)) {
	          throw new NoSuchElementException();
	      }
	      if(size == 1) {
	          return head.data.equals(element);
	      }
	      Node temp = head;
	      while (temp != null) {
	          if(temp.data.equals(element)) {
	              return true;
	          }
	          temp = temp.next;
	      }
	      return false;
	  }
	  
	  //search element in a backward direction
	  public boolean searchReverse(T element) throws Exception {
	      if(isHeadNull(head)) {
	          throw new NoSuchElementException();
	      }
	      if(tail.data.equals(element)) {
	          return true;
	      }
	      Node temp = tail;
	      while (temp != null) {
	          if(temp.data.equals(element)) {
	              return true;
	          }
	          temp = temp.prev;
	      }
	      return false;
	  }
	  
	  //get linkedlist size
	  public int size() {
	      return this.size;
	  }
	  
	  //check linkedlist is empty or not
	  public boolean isEmpty() {
	      return this.size == 0;
	  }
	  
	  //display node in a forward direction
	  public void forwardDisplay() {
	      if(isHeadNull(head)) {
	          throw new NoSuchElementException("DoublyLinkedList is Empty");
	      }
	      Node temp = head;
	      while (temp != null) {
	          System.out.print(temp.data + " -> ");
	          temp = temp.next;
	      }
	      System.out.println("End");
	  }
	  
	  //display node in a backward direction
	  public void backwardDisplay() {
	          if(tail == null) {
	          throw new NoSuchElementException("DoublyLinkedList is Empty");
	      }
	      Node temp = tail;
	      while (temp != null) {
	          System.out.print(temp.data + " -> ");
	          temp = temp.prev;
	      }
	      System.out.println("End");
	  }
	  
	  //get a iterator for a linkedlist
	  public Iterator<T> iterator() {
	      return new MyIterator();
	  }
	  
	  //defined a custom iterator
	  private class MyIterator implements Iterator<T> {
	      private Node current;
	      public MyIterator() {
	          this.current = head;
	      }
	      
	      @Override
	      public boolean hasNext() {
	          return current != null;
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
	  
	  private boolean isSafe(int position) {
	      return position >= 0 && position < size;
	  }
	  
	  private boolean isHeadNull(Node head) {
	      return head == null;
	  }
	  
	  //overriding toString() method
	 @Override
	 public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[ ");
	    if(isHeadNull(head)) {
	       return sb.append("]").toString();
	    }
	    Node temp = head;
	    while (temp != null) {
	        sb.append(temp.data).append(" -> ");
	        temp = temp.next;
	    }
	    sb.append("End").append("]");
	    return sb.toString();
	}
	  
}