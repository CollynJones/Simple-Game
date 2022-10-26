
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SharedQueue 
{
	private LinkedList<String> queue;
	private int limit;
	private int size;
	private boolean isDone = true;
	private int total;
	private int totalLines;
	
	public SharedQueue()
	{
		queue = new LinkedList<String>();
		this.limit = 100;
		this.size = 0;
	}
	public synchronized void enqueue(String item)
	{
		this.queue.addLast(item);
		this.size++;
	}
	
	public synchronized String dequeue()
	{
		if(this.size == 0)
		{
			return null;
		}
		
		String toRet = queue.pollFirst();
		this.size--;
		this.totalLines++;
		return toRet;
	}
	
	public synchronized void waiterReader()
	{
		while(this.size >= this.limit)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public synchronized void waiterSearcher()
	{
		
		while(this.size < this.limit && this.isDone)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public synchronized void wakeUp()
	{
		notifyAll();
		//System.out.println(queue.size());
	}
	public int getSize()
	{
		return this.size;
	}
	public void setFalse()
	{
		this.isDone = false;
	}
	public boolean checkIfDone()
	{
		return this.isDone;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
	public int getTotal()
	{
		return this.total;
	}
	public int getTotalLines()
	{
		return this.totalLines;
	}
	public void displayFirst()
	{
		System.out.println(queue.getFirst());
	}
}
