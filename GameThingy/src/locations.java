import Characters.*;
import WeaponryAndItems.*;
import jdk.nashorn.internal.runtime.ECMAException;

import javax.sound.midi.SysexMessage;
import java.io.FileNotFoundException;
import java.util.*;
public class locations
{
    String locationName;
    public locations(String name)
    {
        this.locationName = name;
    }
    public String getLocationName()
    {
        return this.locationName;
    }
    public void setLocationName(String newLocation)
    {
        this.locationName = newLocation;
    }
    public void moonlitSmith(PlayerCharacter player)
    {
        if(this.locationName.equalsIgnoreCase("Moonlit Smith"))
        {
            System.out.println("Welcome to the Moonlit Smith");
            System.out.println("The sounds of clanking metal and a wave of heat comes over you as you enter the Smith");
            System.out.println("There appears to be a few customers browsing the wares here but there is a Moon Elf behind the counter");
            System.out.println("Approaching, he waves you over to the counter");
            System.out.println("Welcome to the Moonlit Smith! What can I do for you today?");
            int choice = -1;
            do {
                System.out.println("1) Buy");
                System.out.println("2) Sell");
                System.out.println("3) Leave");
                System.out.print("Your choice: ");
                try
                {
                    Scanner kb = new Scanner(System.in);
                    choice = Integer.parseInt(kb.nextLine());
                    switch(choice)
                    {
                        case 1: this.buyMoonlitSmith(player);
                                break;
                        case 2: this.sellMoonlitSmith(player);
                                break;
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Invalid Input, Leaving the Moonlit Smith");
                    choice = 3;

                }
            }while(choice != 3);
            this.setLocationName("Grassrest");
        }
        else
        {
            System.out.println("Your location is invalid, sending you back to Grassrest");
        }
    }
    public void buyMoonlitSmith(PlayerCharacter player)
    {
        int choice = -1;
        do
        {
            System.out.println("Wheat would you like to buy?");
            System.out.println("1) Health Potion");
            System.out.println("2) Weapon");
            System.out.println("3) Armor");
            System.out.println("4) Exit");
            System.out.print("Your choice: ");
            try
            {
                Scanner kb = new Scanner(System.in);
                choice = Integer.parseInt(kb.nextLine());
                int tier;
                int cost;
                String ans;
                switch(choice)
                {
                    case 1: System.out.print("What tier: ");
                            tier = Integer.parseInt(kb.nextLine());
                            cost = 20 * tier;
                            System.out.println("That will cost " + cost);
                            if(player.getCurrency() > cost)
                            {
                                System.out.println("Would you like to buy a tier " + tier + " Health Potion? y/n");
                                System.out.print("Your choice: ");
                                ans = kb.nextLine();
                                if(ans.equalsIgnoreCase("y"))
                                {
                                    if(player.getInvenSize() >= 8)
                                    {
                                        System.out.println("Your inventory is full");
                                    }
                                    else {
                                        player.decreaseCurrency(cost);
                                        double hpRestore = (Math.random() * 15) + 1;
                                        int hpPass = (int) hpRestore;
                                        player.addItem(new HealthAltering("Health Potion Tier " + tier, hpPass, tier));
                                    }
                                }
                            }
                            break;
                    case 2:
                        int weaponChoice = 0;
                        System.out.println("What kind of weapon would you like to purchhase?");
                        System.out.println("1) Sword");
                        System.out.println("2) Dagger");
                        System.out.println("3) Magic Book");
                        System.out.println("4) Holy Staff");
                        System.out.println("5) Lute");
                        weaponChoice = Integer.parseInt(kb.nextLine());
                        System.out.print("What tier: ");
                        tier = Integer.parseInt(kb.nextLine());
                        String weaponName;
                        switch(weaponChoice)
                        {
                            case 1:
                                cost = 30 * tier;
                                System.out.println("A new sword will cost " + cost);
                                if(player.getCurrency() > cost) {
                                    System.out.println("Would you like to buy a tier " + tier + " Sword? y/n");
                                    System.out.print("Your choice: ");
                                    ans = kb.nextLine();

                                    if (ans.equalsIgnoreCase("y")) {
                                        if (player.getInvenSize() >= 8) {
                                            System.out.println("Your inventory is full");
                                        } else {
                                            player.decreaseCurrency(cost);
                                            double dmg = (Math.random() * 15) + 6;
                                            int dmgPass = (int) dmg;
                                            player.addItem(new Weapon("Sword Tier " + tier, 0, 0, dmgPass, "A weapon created by Sylran of the Moonlit Smith", tier));
                                        }
                                    }
                                }
                                else
                                {
                                    System.out.println("You do not have enough gold");
                                }
                                break;
                            case 2:
                                cost = 20 * tier;
                                System.out.println("A new dagger will cost " + cost);
                                if(player.getCurrency() > cost) {
                                    System.out.println("Would you like to buy a tier " + tier + " Dagger? y/n");
                                    System.out.print("Your choice: ");
                                    ans = kb.nextLine();
                                    if (ans.equalsIgnoreCase("y")) {
                                        if (player.getInvenSize() >= 8) {
                                            System.out.println("Your inventory is full");
                                        } else {
                                            player.decreaseCurrency(cost);
                                            double dmg = (Math.random() * 15) + 5;
                                            int dmgPass = (int) dmg;
                                            player.addItem(new Weapon("Dagger Tier " + tier, 0, 1, dmgPass, "A weapon created by Sylran of the Moonlit Smith", tier));
                                        }
                                    }
                                }
                                else {
                                    System.out.println("You do not have enough gold");
                                }
                                break;
                            case 3:
                                cost = 30 * tier;
                                System.out.println("A new magic staff will cost " + cost);
                                if(player.getCurrency() > cost) {
                                    System.out.println("Would you like to buy a tier " + tier + " Magic Staff? y/n");
                                    System.out.print("Your choice: ");
                                    ans = kb.nextLine();
                                    if (ans.equalsIgnoreCase("y")) {
                                        if (player.getInvenSize() >= 8) {
                                            System.out.println("Your inventory is full");
                                        } else {
                                            player.decreaseCurrency(cost);
                                            double dmg = (Math.random() * 15) + 8;
                                            int dmgPass = (int) dmg;
                                            player.addItem(new Weapon("Magic Staff Tier " + tier, 0, 3, dmgPass, "A weapon created by Sylran of the Moonlit Smith", tier));
                                        }
                                    }
                                }
                                else {
                                    System.out.println("You do not have enough gold");
                                }
                                break;
                            case 4:
                                cost = 30 * tier;
                                System.out.println("A new holy book will cost " + cost);
                                if(player.getCurrency() > cost) {
                                    System.out.println("Would you like to buy a tier " + tier + " Book? y/n");
                                    System.out.print("Your choice: ");
                                    ans = kb.nextLine();
                                    if (ans.equalsIgnoreCase("y")) {

                                        if (player.getInvenSize() >= 8) {
                                            System.out.println("Your inventory is full");
                                        } else {
                                            player.decreaseCurrency(cost);
                                            double dmg = (Math.random() * 15) + 10;
                                            int dmgPass = (int) dmg;
                                            player.addItem(new Weapon("Holy Book Tier " + tier, 0, 4, dmgPass, "A weapon created by Sylran of the Moonlit Smith", tier));
                                        }
                                    }
                                }
                                else{
                                    System.out.println("You do not have enough gold");
                                }
                                break;
                            case 5:
                                cost = 40 * tier;
                                System.out.println("A new Lute will cost " + cost);
                                if(player.getCurrency() > cost) {
                                    System.out.println("Would you like to buy a tier " + tier + " Lute? y/n");
                                    System.out.print("Your choice: ");
                                    ans = kb.nextLine();
                                    if (ans.equalsIgnoreCase("y")) {
                                        if (player.getInvenSize() >= 8) {
                                            System.out.println("Your inventory is full");
                                        } else {
                                            player.decreaseCurrency(cost);
                                            double dmg = (Math.random() * 15) + 1;
                                            int dmgPass = (int) dmg;
                                            player.addItem(new Weapon("Bard's Lue Tier " + tier, 0, 5, dmgPass, "A weapon created by Sylran of the Moonlit Smith", tier));
                                        }
                                    }
                                }
                                else {
                                    System.out.println("You do not have enough gold");
                                }
                                break;
                            default:
                                System.out.println("Invalid Input");
                                break;
                        }


                        break;
                    case 3:
                        System.out.println("What kind of armor would you like to purchhase?");
                        System.out.println("1) Strength Armor");
                        System.out.println("2) Dexterity Armor");
                        System.out.println("3) Magic Armor");
                        System.out.println("4) Holy Armor");
                        int armorChoice = Integer.parseInt(kb.nextLine());
                        System.out.print("What tier: ");
                        tier = Integer.parseInt(kb.nextLine());
                        switch(armorChoice)
                        {
                            case 1:
                                cost = 80 * tier;
                                System.out.println("A new set of Strength Armor will cost " + cost);
                                if(player.getCurrency() > cost) {
                                    System.out.println("Would you like to buy a tier " + tier + " Armor? y/n");
                                    System.out.print("Your choice: ");
                                    ans = kb.nextLine();
                                    if (ans.equalsIgnoreCase("y")) {
                                        if (player.getInvenSize() >= 8) {
                                            System.out.println("Your inventory is full");
                                        } else {
                                            player.decreaseCurrency(cost);
                                            double ac = (Math.random() * 6) + 10;
                                            int acPass = (int) ac;
                                            player.addItem(new Armor("Strength Armor Tier " + tier, 1, 0, acPass, "Armor created by Sylran of the Moonlit Smith", tier));
                                        }
                                    }
                                }
                                else {
                                    System.out.println("You do not have enough gold");
                                }
                                break;

                            case 2:
                                cost = 60 * tier;
                                System.out.println("A set of Dexterity Armor will cost " + cost);
                                if(player.getCurrency() > cost) {
                                    System.out.println("Would you like to buy a tier " + tier + " Dexterity Armor? y/n");
                                    System.out.print("Your choice: ");
                                    ans = kb.nextLine();
                                    if (ans.equalsIgnoreCase("y")) {
                                        if (player.getInvenSize() >= 8) {
                                            System.out.println("Your inventory is full");
                                        } else {
                                            player.decreaseCurrency(cost);
                                            double ac = (Math.random() * 4) + 10;
                                            int acPass = (int) ac;
                                            player.addItem(new Armor("Dexterity Armor Tier " + tier, 1, 1, acPass, "Armor created by Sylran of the Moonlit Smith", tier));
                                        }
                                    }
                                }
                                else{
                                    System.out.println("You do not have enough gold");
                                }
                                break;

                            case 3:
                                cost = 70 * tier;
                                System.out.println("A set of Magic Armor will cost " + cost);
                                if(player.getCurrency() > cost) {
                                    System.out.println("Would you like to buy a tier " + tier + " Magic Armor? y/n");
                                    System.out.print("Your choice: ");
                                    ans = kb.nextLine();
                                    if (ans.equalsIgnoreCase("y")) {
                                        if (player.getInvenSize() >= 8) {
                                            System.out.println("Your inventory is full");
                                        } else {
                                            player.decreaseCurrency(cost);
                                            double ac = (Math.random() * 8) + 10;
                                            int acPass = (int) ac;
                                            player.addItem(new Armor("Magic Armor Tier " + tier, 1, 3, acPass, "Armor created by Sylran of the Moonlit Smith", tier));
                                        }
                                    }
                                }
                                else {
                                    System.out.println("You do not have enough gold");
                                }
                                break;

                            case 4:
                                cost = 90 * tier;
                                System.out.println("A set of Holy Armor will cost " + cost);
                                if(player.getCurrency() > cost) {
                                    System.out.println("Would you like to buy a tier " + tier + " Holy Armor? y/n");
                                    System.out.print("Your choice: ");
                                    ans = kb.nextLine();
                                    if (ans.equalsIgnoreCase("y")) {
                                        if (player.getInvenSize() >= 8) {
                                            System.out.println("Your inventory is full");
                                        } else {
                                            player.decreaseCurrency(cost);
                                            double ac = (Math.random() * 4) + 10;
                                            int acPass = (int) ac;
                                            player.addItem(new Armor("Holy Armor Tier " + tier, 1, 4, acPass, "Armor created by Sylran of the Moonlit Smith", tier));
                                        }
                                    }
                                }
                                else{
                                    System.out.println("You do not have enough gold");
                                }
                                break;

                            default:
                                System.out.println("Invalid Input");
                                break;
                        }
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Invalid Input, setting choice to 4");
                choice = 4;
            }
        }while(choice != 4);
    }
    public void sellMoonlitSmith(PlayerCharacter player)
    {
        int choice = player.getInvenSize();
        do {
            if(player.getInvenSize() <= 0)
            {
                System.out.println("You have nothing to sell");
                choice = 0;
            }
            else {
                player.printInventory();
                try
                {
                    int invenSize = player.getInvenSize();
                    System.out.println(invenSize + ") Exit");
                    Scanner kb = new Scanner(System.in);
                    choice = Integer.parseInt(kb.nextLine());
                    if(choice >= 0 && choice < invenSize)
                    {
                        System.out.println("Would you like to sell your " + player.getItemName(choice));
                        System.out.println("0 for yes, 1 for no");
                        int sellChoice = Integer.parseInt(kb.nextLine());
                        if(sellChoice == 0)
                        {
                            player.increaseCurrency(15 * player.getItem(choice).getPrice());
                            player.removeItemIndex(choice);

                        }
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Invalid Input");
                }
            }
        }while(choice != player.getInvenSize() && player.getInvenSize() != 0);
    }
    public void lonelyJester(BaseUnit character)
    {
        if(this.locationName.equalsIgnoreCase("Lonely Jester Inn"))
        {
            System.out.println("Welcome to the Lonely Jester Inn");
            System.out.println("There are many patrons drinking, dancing, and singing!");
            System.out.println("You lock eyes with a dwarf behind the counter and calls out to you");
            System.out.println("Edwin the Bar Keep offers you a night of rest and relaxation!");
            System.out.print("Do you accept? y/n -->");
            Scanner kb = new Scanner(System.in);
            String choice;
            try {
                choice = kb.nextLine();
            }
            catch (Exception e)
            {
                choice = "y";
            }
            if(choice.equalsIgnoreCase("y"))
            {
                character.fullHeal();
                System.out.println(character.getName() + " accepts the offer and stays the night! You regain full hp!");
            }
            else
            {
                System.out.println("Edwin calls you a little bitch and kicks you out, never disrespect Edwin you cretin");
            }
            System.out.println("You leave the Lonely Jester and head back into the city streets of Grassrest");
            this.setLocationName("Grassrest");
        }
    }
    public void mayorsOffice()
    {
        if(this.locationName.equalsIgnoreCase("Mayor's Office"))
        {
            System.out.println("You knock of the door of the Mayor's Office and head inside");
            System.out.println("Everyone Seems to be very busy on the inside and pays you no mind");
            System.out.println("You silently leave as there is nothing important for you to say or do");
            this.setLocationName("Grassrest");
        }
    }
    public void freeExpanse(PlayerCharacter player) {
        System.out.println("You walked through the massive walls of Grassrest and into the expansive fields of the Free Expanse");
        System.out.println("You venture out and see many sights from Deer prancing in the fields to goblin's feasting on the body of human");
        Monster enemy;
        boolean explore = true;
        do {
            try {
                enemy = (Monster) GeneralMethods.makeMonster();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println("A " + enemy.getName() + " appears and attacks!");
            Battle fExpanse = new Battle();
            boolean res = fExpanse.battleOneOnOne(player, enemy);
            if (res) {
                ItemFactoryMainParser item = new ItemFactoryMainParser();
                Item drop = item.createItem(player);
                System.out.println("You were victorious! Would you like to continue exploring? y/n");
                Scanner kb = new Scanner(System.in);
                String ans;
                try {
                    ans = kb.nextLine();
                }
                catch (Exception e)
                {
                    ans = "y";
                }
                if (ans.equalsIgnoreCase("y")) {
                    System.out.println("You continue your exploration");
                }
                else
                {
                    System.out.println("You finish your exploration and return to Grassrest");
                    explore = false;
                }
            }
            else
            {
                System.out.println("After being defeated, a small group of adventurers take you back");
                System.out.println("to town and bandage you up, you should be more careful!");
                player.fullHeal();
                explore = false;
            }

        }while(explore);
        this.setLocationName("Grassrest");
    }
}
