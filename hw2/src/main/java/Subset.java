
public class Subset {

	public static void main(String[] args) {
		RandomizedQueue<String> rQ = new RandomizedQueue<String>();
		for (String s: StdIn.readAllStrings()){
			rQ.enqueue(s);
		}
		for (int i = 0; i < Integer.parseInt(args[0]); i++){
			StdOut.println( rQ.dequeue() );
		}

	}

}
