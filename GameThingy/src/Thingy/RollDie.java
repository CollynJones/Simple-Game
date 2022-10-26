package Thingy;

public class RollDie {

	private RollDie() {//private for the Singleton
		// TODO Auto-generated constructor stub
	}
	private volatile static RollDie uniqueInstance;
 
    public static RollDie getInstance() {//makes sure there is only ever one Dice object
       if (uniqueInstance == null) {
          synchronized (RollDie.class) {
             if (uniqueInstance == null) {
                uniqueInstance = new RollDie();
             }
          }
       }
       return uniqueInstance;
    }
 
    public int roll(final int dieCount)//rolls special dice
    {
    	int roll;
    	double tempRoll = (Math.random() * dieCount) + 1;
    	roll = (int) tempRoll;
    	
    	return roll;
    }
}
