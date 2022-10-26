import java.io.*;
import java.util.*;

public class ParallelSearchCoarse {
	public static void main(String args[]) throws InterruptedException, FileNotFoundException {
		
		if( args.length < 2) {
			System.out.println("Usage: Java ParallelSearchCoarse FileName Pattern");
			System.exit(0);
		}
		
		String fname = args[0];         // fileName = files/wikipedia2text-extracted.txt
		String pattern = args[1];       // pattern = "(John) (.+?) ";
		long start = System.currentTimeMillis();
		
		// Create your thread reader and searcher here
		// TODO
		
		SharedQueue q = new SharedQueue();		
		Reader reader = new Reader(fname, q);
		Searcher searcher = new Searcher(q, pattern);
		reader.start();
		searcher.start();
		reader.join();
		searcher.join();
		long end = System.currentTimeMillis();
		System.out.println("Total number of lines searched " + q.getTotalLines());
		System.out.println("Total occurence of pattern " + pattern + " " + q.getTotal());
		System.out.println("Time cost for concurrent solution is " + (end - start));
		
	}

}
