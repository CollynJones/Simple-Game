import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Searcher extends Thread
{
	private int total;
	private SharedQueue q;
	private String cur;
	private String pattern;
	
	public Searcher(SharedQueue q, String pat)
	{
		this.q = q;
		this.pattern = pat;
	}
	
	@Override
	public void run()
	{
		q.waiterSearcher();
		while(q.getSize() >= 0)
		{
			if(q.getSize() <= 0 && q.checkIfDone())
			{
				q.wakeUp();
				q.waiterSearcher();
			}
			this.cur = q.dequeue();
			if(this.cur == null)
			{
				break;
			}
			else
			{
				Pattern pattern = Pattern.compile(this.pattern);
				Matcher matcher = pattern.matcher(this.cur);
				while(matcher.find())
				{
					this.total++;
				}
			}
		}
		q.setTotal(this.total);
	}
	
	public int getResults()
	{
		return this.total;
	}
}
