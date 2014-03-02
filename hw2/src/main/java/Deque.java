

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private Item[] deque;
	
	private Node first = null;
	private Node last = null;
	private int length = 0;
	
	private class Node
	{
		Item item;
		Node next;
		Node prev;
		
		Node( Item item ){
			this.item = item;
		}
		
		Node( Item item, Node next ) {
			this.item = item;
			this.next = next;
		}
		
		Node( Item item, Node next, Node prev ) {
			this.item = item;
			this.next = next;
			this.prev = prev;
		}
	}
	
	public Deque() {
		// construct an empty deque
	}
	
	public boolean isEmpty() {
		// is the deque empty?
		return length == 0;
	}

	public int size() {
		// return the number of items on the deque
		return length;
	}
	
	public void addFirst(Item item) {
		// insert the item at the front
		if ( item == null ) {
			throw new java.lang.NullPointerException();
		}
		first = new Node( item, first );
		length++;
	}
	
	public void addLast(Item item) {
		// insert the item at the end
		last = new Node( item, null, last );
		length++;
	}
	
	public Item removeFirst() {
		// delete and return the item at the front
		if ( isEmpty() ) {
			throw new java.util.NoSuchElementException();
		}
		Item returnItem = first.item;
		if ( length > 1 ) {
			first.next.prev = null;
			first = first.next;	
		} else {
			first = null;
			last = null;
		}
		length--;
		return returnItem;
	}
	
	public Item removeLast() {
		// delete and return the item at the end
		if ( isEmpty() ) {
			throw new java.util.NoSuchElementException();
		}
		Item returnItem = last.item;
		if ( length > 1 ) {
			last = last.prev;
			last.next.prev = null;
		} else {
			first = null;
			last = null;
		}
		length--;
		return returnItem;
	}
	
	public Iterator<Item> iterator() {
		// return an iterator over items in order from front to end
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {

		private Node current = first;
		public boolean hasNext() {
			return current != null;
		}

		public Item next() {
			if ( current.next == null ) {
				throw new java.util.NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();			
		}
	}
	public static void main(String[] args) {
		// unit testing
	}
}