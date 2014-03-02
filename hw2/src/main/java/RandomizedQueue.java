

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] rQueue;
	private int pos = 0;
	public RandomizedQueue() {
		// construct an empty randomized queue
		rQueue = (Item[]) new Object[1];
	}
	
	public boolean isEmpty() {
		// is the queue empty?
		return pos == 0;
	}
	
	public int size() {
		// return the number of items on the queue
		return pos;
	}
	
	public void enqueue(Item item) {
		// add the item
		rQueue[pos++] = item;
		if (pos == rQueue.length) {
			resize(2*rQueue.length);
		}
	}
	
	private void resize(int size) {
		Item[] newQueue = (Item[]) new Object[size];
		for (int i = 0; i < rQueue.length; i++) {
			newQueue[i] = rQueue[i];
		}
		rQueue = newQueue;
	}

	public Item dequeue() {
		// delete and return a random item
		if ( isEmpty() ) {
			throw new java.util.NoSuchElementException();
		}
		int del = StdRandom.uniform(pos);
		Item returnItem = rQueue[del];
		rQueue[del] = rQueue[--pos];
		
		if ( pos < rQueue.length / 4 ) {
			shrink();
		}
		return returnItem;
	}
	private void shrink() {
		Item[] newQueue = (Item[]) new Object[rQueue.length / 2];
		for (int i = 0; i < newQueue.length; i++){
			newQueue[i] = rQueue[i];
		}
		rQueue = newQueue;
	}

	public Item sample() {
		// return (but do not delete) a random item
		return rQueue[ StdRandom.uniform(pos)];
	}
	
	public Iterator<Item> iterator() {
		// return an independent iterator over items in random order
		return new rQueueIterator();
	}
	
	private class rQueueIterator implements Iterator<Item> {

		private int visitCount;
		private boolean[] visited;
		
		rQueueIterator() {
			visited = new boolean[pos];
		}
		public boolean hasNext() {
			return visitCount != pos;
		}

		public Item next() {
			if ( !hasNext() ) {
				throw new java.util.NoSuchElementException();
			}
			
			while (true) {
		        int index = StdRandom.uniform(pos);
		        if (!visited[index]) {
		        	visited[index] = true;
		        	visitCount++;
		          return rQueue[index];
		        }
		    }
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();			
		}
	}
	
	public static void main(String[] args) {
		// unit testing
	}
	
}