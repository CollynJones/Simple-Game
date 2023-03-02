import Characters.*;
import java.util.*;
import java.io.*;

import static java.lang.Integer.parseInt;

public class GeneralMethods
{
    public static BaseUnit makeMonster() throws FileNotFoundException {
        String myFile = "myMonsters.txt";
        File inFile = new File(myFile);
        Scanner fin = new Scanner(inFile);
        int monsterNum = parseInt(fin.nextLine());
        int line = (int) (Math.random() * monsterNum);
        for(int i = 0; i < line; i++)
        {
            fin.nextLine();
        }
        String monsterStats = fin.nextLine();
        String[] monster;
        monster = monsterStats.split(",");
        fin.close();
        String name = monster[0];
        int[] stats = new int[] {parseInt(monster[1].trim()), parseInt(monster[2]), parseInt(monster[3]), parseInt(monster[4]), parseInt(monster[5]), parseInt(monster[6])};
        int hp = parseInt(monster[7]);
        int AC = parseInt(monster[8]);
        int profBonus = parseInt(monster[9]);
        int attackType = parseInt(monster[10]);
        int damage = parseInt(monster[11]);

        return new Monster(monster[0], new int[] {parseInt(monster[1]), parseInt(monster[2]), parseInt(monster[3]), parseInt(monster[4]), parseInt(monster[5]), parseInt(monster[6])}, parseInt(monster[7]), parseInt(monster[8]), parseInt(monster[9]), parseInt(monster[10]), parseInt(monster[11]), parseInt(monster[12]));
    }

    public static void randomMonsterGenerator() throws FileNotFoundException
    {
        String myFile = "C:\\Users\\twigg\\IdeaProjects\\MyGame\\src\\myMonsters.txt";
        File inFile = new File(myFile);
        Scanner fin = new Scanner(inFile);

        int monsterNum = parseInt(fin.nextLine());
        String[] monsterNames = new String[monsterNum];
        String[] tempArray;
        for(int i = 0; i < monsterNum; i++)
        {
            tempArray = fin.nextLine().split(",");
            monsterNames[i] = tempArray[0];
        }
        fin.close();
        int randMon = (int) (Math.random() * monsterNum);
        System.out.println(monsterNames[randMon]);
        for(int i = 0; i < 20; i++)
        {
            randMon = (int) (Math.random() * monsterNum);
            System.out.println(monsterNames[randMon]);
        }

    }

    public static void mainMenu()
    {
        System.out.println("Welcome to Meat Grinder!");
        System.out.println("Would you like to start a new game (1) or reload an existing save?");
        Scanner kb = new Scanner(System.in);
        String res;
        try {
            res = kb.nextLine();
        } catch (Exception e) {
            System.out.println("Input Error, Starting a New Game");
            res = "1";
        }
        if(res.equalsIgnoreCase("2"))
        {
            resumeGame();
        }
        else
        {
            startNewGame();
        }
    }

    public static void startNewGame()
    {
        PlayerCharacter player = new PlayerCharacter();
        Grassrest(player);
    }

    public static void resumeGame()
    {
        System.out.println("This feature is not created yet, Starting a New Game");
        startNewGame();
    }

    public static void Grassrest(PlayerCharacter player)
    {
        System.out.println("Welcome to the City of Grassrest!");
        System.out.println("There is not much to do here in this small town but we do have places to visit!");
        System.out.println("Here are some notable places you can go to!");
        int choice = 1;
        locations area = new locations("Grassrest");

        do
        {
            choice = grassrestChoices();
            switch(choice)
            {
                case 1: System.out.println("You head towards the Moonlit Smith");
                        area.setLocationName("Moonlit Smith");
                        area.moonlitSmith(player);
                        break;
                case 2: System.out.println("You head towards the Lonely Jester Inn");
                        area.setLocationName("Lonely Jester Inn");
                        area.lonelyJester(player);
                        break;
                case 3: System.out.println("You head towards the Mayor's Office");
                        area.setLocationName("Mayor's Office");
                        area.mayorsOffice();
                        break;
                case 4: System.out.println("You head outside of Grassrest into the Free Expanse");
                        area.setLocationName("Free Expanse");
                        area.freeExpanse(player);
                        break;
                case 5: player.characterMenu();
                        break;
                case 6: System.out.println("The Game will now Exit");
                        break;
                default:
                    System.out.println("Invalid Input, You stay in the Streets for the night and take 1 damage");
                    player.alterHP(-1);
                    choice = 0;
            }
            if(choice == 6)
            {
                break;
            }
        }while(choice <= 5 && choice > 0);
    }

    public static int grassrestChoices()
    {
        System.out.println("Where would you like to go?");
        System.out.println("1) The Moonlit Smith");
        System.out.println("2) The Lonely Jester Inn");
        System.out.println("3) Mayor's Office");
        System.out.println("4) Outside of Town");
        System.out.println("5) Character Menu");
        System.out.println("6) Exit");
        System.out.print("Your Destination: ");
        Scanner kb = new Scanner(System.in);
        int choice;
        try {
            choice = Integer.parseInt(kb.nextLine());
        }
        catch(Exception e)
        {
            choice = 4;
        }
        return choice;
    }
    public static boolean saveTheGame(PlayerCharacter player)
    {
        System.out.println("Do you wish to save the game? y/n");
        Scanner kb = new Scanner(System.in);
        String ans;
        try {
            ans = kb.nextLine();
        }
        catch(Exception e)
        {
            ans = "y";
        }
        if(ans.equalsIgnoreCase("y"))
        {
            System.out.println("The game has been saved(But not actually, this function has not been made yet....");
            System.out.println("Do you wish to exit the game? y/n");
            ans = kb.nextLine();
            if(ans.equalsIgnoreCase("y"))
            {
                return false;
            }

            return true;
        }
        return false;
    }

    public static boolean saveCharacter(PlayerCharacter player)
    {
        return false;
    }
}
