package WeaponryAndItems;

import Characters.PlayerCharacter;
import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ItemFactoryMainParser
{

    public  ItemFactoryMainParser()
    {

    }

    public Item createItem(PlayerCharacter player)
    {
        String myFile = "C:\\Users\\twigg\\IdeaProjects\\MyGame\\src\\LootTeir1.txt";
        File inFile = new File(myFile);
        Scanner fin;
        try {
            fin = new Scanner(inFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int amountOfItems = Integer.parseInt(fin.nextLine());
        double loopAmount = amountOfItems * Math.random();
        int loops = (int) loopAmount;
        for(int i = 0; i < loops; i++)
        {
            fin.nextLine();
        }
        String itemToCreate = fin.nextLine();
        fin.close();
        String[] parsed = itemToCreate.split(" ");
        switch(parsed[0])
        {
            case "Gold":int gold =  (int) (50 * Math.random());
                        player.increaseCurrency(gold);
                        System.out.println(" dropped " + gold + "!");
                        return null;
            case "Sword":return new Weapon("Sword", 0, 0, 10, "A sword once used by a brave knight", Integer.parseInt(parsed[2]));

            case "Dagger":return new Weapon();
            case "Club": return new Weapon("Club", 0, 0, 6, "A shotty wooden club", Integer.parseInt(parsed[2]));
            case "Armor": return this.createArmor(player, parsed);
            case "Staff": return this.createStaff(player, parsed);
            case "Book": return this.createBook(player, parsed);
            case "Instrument": return new Weapon("Lute", 0, 5, 8, "A Lute that has been used many times by different musicians", Integer.parseInt(parsed[2]));
            case "Great": return this.createGreatWeapon(player, parsed);
            case "Potion": return this.createPotion(player, parsed);
            case "Weapon": return this.createWeapon(player, parsed);
            default:
                System.out.println(" dropped nothing!");
                return null;
        }
    }
    public Item createItem(String[] item)
    {
        return null;
    }
    public Item createArmor(PlayerCharacter player, String[] parsed)
    {
        int statUsed = this.identifyStat(parsed[1]);
        double doubAC = (Math.random() * 8) + 11;
        int AC = (int) doubAC;
        //public Armor(String name, int slotNumber, int stat, int AC, String inspect, int tier)
        return new Armor(parsed[1] + " " + parsed[0] + " " + parsed[2] + " " + parsed[3], 1, statUsed, AC, "Armor forged long ago", Integer.parseInt(parsed[3]));

    }
    public Item createStaff(PlayerCharacter player, String[] parsed)
    {
        int statUsed = identifyStat(parsed[1]);

        return new Weapon(parsed[1] + " " + parsed[0] + " " + parsed[2] + " " + parsed[3], 0, statUsed, 12, "A staff created by a powerful Mage", Integer.parseInt(parsed[3]));
    }
    public Item createBook(PlayerCharacter player, String[] parsed)
    {
        //public Weapon(String name, int slotNumber, int statUsed, int damageModifier, String inspect, int tier)
        int statUsed = identifyStat(parsed[1]);

        return new Weapon(parsed[1] + " " + parsed[0] + " " + parsed[2] + " " + parsed[3], 0, statUsed, 10, "A book created hundreds of years ago", Integer.parseInt(parsed[3]));
    }
    public Item createGreatWeapon(PlayerCharacter player, String[] parsed)
    {
        double dmgMod = (Math.random() * 15) + 12;
        int dmg = (int) dmgMod;
        //public Weapon(String name, int slotNumber, int statUsed, int damageModifier, String inspect, int tier), Great Sword Tier 1
        return new Weapon(parsed[0] + " " + parsed[1] + " " + parsed[2] + " " + parsed[3], 0, 0, dmg, "A great weapon used by great warriors", Integer.parseInt(parsed[3]));
    }
    public Item createPotion(PlayerCharacter player, String[] parsed)
    {
        // public HealthAltering(String name, int healthModifier, int tier)
        double hpRestore = (Math.random() * 15) + 1;
        int hpPass = (int) hpRestore;
        //Potion Health Tier 1
        return new HealthAltering(parsed[1] + " " + parsed[0] + " " + parsed[2] + " " + parsed[3], hpPass, Integer.parseInt(parsed[3]));
    }
    public Item createWeapon(PlayerCharacter player, String[] parsed)
    {
        double val = (Math.random() * 10) + 1;
        int valInt = (int) val;
        int dmg;
        double dmgMod;
        switch(valInt)
        {
            case 0:
                dmgMod = (Math.random() * 25) + 10;
                dmg = (int) dmgMod;
                return new Weapon("Shock and Awe", 0, 0, dmg, "The weapons of Jandar, the champion of giants", Integer.parseInt(parsed[2]));
            case 1:
                dmgMod = (Math.random() * 15) + 10;
                dmg = (int) dmgMod;
                return new Weapon("Blast Bow", 0, 1, dmg, "The crossbow of Gaylord Booker, a cultist in the Cult of Gwen who helped heroes clear the fog", Integer.parseInt(parsed[2]));
            case 2:
                dmgMod = (Math.random() * 25) + 10;
                dmg = (int) dmgMod;
                return new Weapon("Dabria's Staff", 0, 4, dmg, "The staff of Dabria, a Druid who manipulates creatures to her will", Integer.parseInt(parsed[2]));
            case 3:
                dmgMod = (Math.random() * 15) + 10;
                dmg = (int) dmgMod;
                return new Weapon("Mystra's Book", 0, 3, dmg, "A book used by Hunjar, the Minotuar Librarian", Integer.parseInt(parsed[2]));
            case 4:
                dmgMod = (Math.random() * 15) + 10;
                dmg = (int) dmgMod;
                return new Weapon("Glaive of Torm", 0, 0, dmg ,"A glaive used by the god torm and the Hero Montana", Integer.parseInt(parsed[2]));
            case 5:
                dmgMod = (Math.random() * 15) + 10;
                dmg = (int) dmgMod;
                return new Weapon("Lightblade", 0, 0, dmg, "A blade of light used by the Hero Benedak", Integer.parseInt(parsed[2]));
            case 6:
                dmgMod = (Math.random() * 15) + 10;
                dmg = (int) dmgMod;
                return new Weapon("Rose-Petal Longbow", 0, 1, dmg, "The bow of Wuarion, the mayor of Cobblersvile", Integer.parseInt(parsed[2]));
            case 7:
                dmgMod = (Math.random() * 15) + 10;
                dmg = (int) dmgMod;
                return new Weapon("Moonblade Kolvar", 0, 4, dmg, "A sword used by the Moon Sentinel Astrum", Integer.parseInt(parsed[2]));
            case 8:
                dmgMod = (Math.random() * 15) + 10;
                dmg = (int) dmgMod;
                return new Weapon("Book of Pholtus", 0, 5, dmg, "A weapon used by the mage Astrea who cleared the fog of Majorian", 3);
            case 9:
                dmgMod = (Math.random() * 15) + 10;
                dmg = (int) dmgMod;
                return new Weapon("Great Thieve's Blade", 0, 1, dmg, "A dagger used by the assassin Hazen, a man lost in time.", Integer.parseInt(parsed[2]));
            case 10:
                dmgMod = (Math.random() * 15) + 10;
                dmg = (int) dmgMod;
                return new Weapon("Great Magic Staff", 0, 3,dmg,"A great staff used by the warlock Aelar", Integer.parseInt(parsed[2]));
            case 11:
                dmgMod = (Math.random() * 15) + 10;
                dmg = (int) dmgMod;
                return new Weapon("Great Sword Tier 3", 0, 0, dmg, "A Great Sword created by a famous blacksmith of the west", Integer.parseInt(parsed[2]));


            default:
                //public Weapon(String name, int slotNumber, int statUsed, int damageModifier, String inspect, int tier), Great Sword Tier 1
                dmgMod = (Math.random() * 15) + 12;
                dmg = (int) dmgMod;
                return new Weapon("Great Sword Tier 3", 0, 0, dmg, "A Great Sword created by a famous blacksmith of the west", Integer.parseInt(parsed[2]));
        }

    }

    public int identifyStat(String type)
    {
        switch(type)
        {
            case "Dexterity":
                return 1;
            case "Holy":
                return 5;
            case "Magic":
                return 4;
            default:
                return 0;
        }
    }
}
