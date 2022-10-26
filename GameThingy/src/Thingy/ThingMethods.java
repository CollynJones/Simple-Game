package Thingy;
import java.util.*;

public class ThingMethods {

	
	public static int menu(final Scanner kb)
	{
		int choice = -2;
		while(choice > 2 || choice < -1)
		{
			System.out.println("Welcome to my Game Thing!");
			System.out.println("Select one of the options!");
			System.out.println("1) New Game");
			System.out.println("2) Load Game");
			System.out.print("Your choice: ");
			choice = Integer.parseInt(kb.nextLine());
			if(choice > 2 || choice < -1)
			{
				System.out.println("Choice not between 1 and 2");
			}
		}
		return choice;
	}
	
	public static void battle(BaseUnit character, BaseUnit enemy)
	{
		BaseUnit[] init = new BaseUnit[2];
		int charInit = character.initiative();
		int enemyInit = enemy.initiative();
		if(enemyInit > charInit)
		{
			init[0] = enemy;
			init[1] = character;
		}
		else
		{
			init[0] = character;
			init[1] = enemy;
		}
		while(enemy.isAlive() || character.isAlive())
		{
			for(int i = 0; i < init.length; i++)
			{
				init[i].battleOptions();
			}
		}
	}
}
