import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader extends Thread
{
	
	private String cur;
	private SharedQueue q;
	private BufferedReader bufferedReader;
	
	public Reader(String fname, SharedQueue curQ) throws FileNotFoundException
	{
		FileReader fileReader = new FileReader(fname);
		bufferedReader = new BufferedReader(fileReader);
		this.q = curQ;
	}
	public Reader()
	{}
	
	@Override
	public void run()
	{
		try {
			while ((this.cur = bufferedReader.readLine()) != null)
			{
				if(q.getSize() >= 100)
				{
					q.wakeUp();
					q.waiterReader();
				}
				q.enqueue(cur);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		q.setFalse();
		q.wakeUp();
	}
}
