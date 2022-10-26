package Thingy;
import java.util.*;


public class BaseUnit {
								//Stats, STR, DEX, CON, INT, WIS, CHA, LUCK, FAITH
								//index  0    1     2    3   4    5     6    7
	protected int[] baseStats = {10, 10, 10, 10, 10, 10, 10, 10};
	protected String name;
	protected int exp;
	protected int expToLevel;
	protected int[] status;
	protected int level;
	protected RollDie roller;
	protected int hp;
	
	public BaseUnit() {
		System.out.println("Ah I see you didn't need anything special!");
		System.out.println("This will be interesting");
		this.exp = 0;
		this.level = 1;
		Scanner kb = new Scanner(System.in);
		System.out.println("What is your name naked man? ");
		this.name = kb.nextLine();
		expToLevelCalc();
		this.roller = RollDie.getInstance();
		this.hp = 10;
		
		
		
		kb.close();
	}
	public BaseUnit(int[] baseStats, String name, int exp, int expToLevel, int[] status, int level)
	{
		for(int i = 0; i < baseStats.length; i++)
		{
			this.baseStats[i] = baseStats[i];
		}
		this.name = name;
		this.exp = exp;
		this.expToLevel = expToLevel;
		this.level = level;
		for(int i = 0; i < status.length; i++)
		{
			this.status[i] = status[i];
		}
		this.roller = RollDie.getInstance();
	}
	public void expToLevelCalc()
	{
		int val = 20;
		int temp = this.exp;
		while(temp > 0)
		{
			val = val * 7;
			temp = temp / 2;
		}
		this.expToLevel = val;
		
	}
	public void gainEXP(int exp)
	{
		this.exp += exp;
		System.out.println(this.name + " gains " + exp + " to a total of " + this.exp);
		if(checkLevelUp())
		{
			levelUp();
		}
		
	}
	public boolean checkLevelUp()
	{
		if(this.exp >= this.expToLevel)
		{
			return true;
		}
		return false;
	}
	public void levelUp()
	{
		this.level++;
		System.out.println("Congradulations! You leveled up to level " + this.level);
		expToLevelCalc();
		System.out.println("Select the value you wish to advance!");
		System.out.println("0) STR: " + baseStats[0]);
		System.out.println("1) DEX: " + baseStats[1]);
		System.out.println("2) CON: " + baseStats[2]);
		System.out.println("3) INT: " + baseStats[3]);
		System.out.println("4) WIS: " + baseStats[4]);
		System.out.println("5) CHA: " + baseStats[5]);
		System.out.println("6) LUCK: " + baseStats[6]);
		System.out.println("7) FAITH: " + baseStats[7]);
		Scanner kb = new Scanner(System.in);
		int choice = Integer.parseInt(kb.nextLine());
		switch(choice)
		{
		case 0: alterStats(0, 1); break;
		case 1: alterStats(1, 1); break;
		case 2: alterStats(2, 1); break;
		case 3: alterStats(3, 1); break;
		case 4: alterStats(4, 1); break;
		case 5: alterStats(5, 1); break;
		case 6: alterStats(6, 1); break;
		case 7: alterStats(7, 1); break;
		default: alterStats(0, 1); break;
		}
		kb.close();
	
	
	}
	public void alterStats(int index, int amount)
	{
		
	}
	public int initiative()
	{
		return (roller.roll(20) + baseStats[1]);
	}
	public boolean isAlive()
	{
		return (this.hp > 0);
	}
	public void battleOptions()
	{
		System.out.println("here be the attack options for " + this.name);
	}
}
