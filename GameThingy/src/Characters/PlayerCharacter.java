package Characters;
import WeaponryAndItems.Weapon;

import java.util.*;

public class PlayerCharacter extends BaseUnit
{
    private int experience;
    private int experienceToLevel;

    public PlayerCharacter()
    {
        super();
        this.experience = 0;
    }
    @Override
    public void increaseExperience(int amount)
    {
        this.experience += amount;
        if(levelCheck())
        {
            this.experience = this.experience - this.experienceToLevel;
            this.level++;
            this.levelUp();
        }
    }
    public void calcExperienceToLevelUp()
    {
        this.experienceToLevel = this.getLevel() * 20;
    }
    public void levelUp()
    {
        System.out.println("Congratulations " + this.getName() + " have leveled up to " + this.getLevel());
        calcExperienceToLevelUp();
        System.out.println(("Please select which stat to increase by 1!"));
        System.out.println("0) Strength: " + this.getStats(0));
        System.out.println("1) Dexterity " + this.getStats(1));
        System.out.println("2) Constitution " + this.getStats(2));
        System.out.println("3) Intelligence " + this.getStats(3));
        System.out.println("4) Wisdom " + this.getStats(4));
        System.out.println("5) Charisma " + this.getStats(5));
        Scanner kb = new Scanner(System.in);
        int statIncrease;
        while(true) {
            System.out.println("Your Choice: ");
            try {
                statIncrease = Integer.parseInt(kb.nextLine());
                break;
            }
            catch (Exception e)
            {

            }
        }
            this.increaseStat(statIncrease, 1);
        switch(statIncrease)
        {
            case 0: System.out.println("You increased Strength by 1 to " + this.getStats(0));
                break;
            case 1: System.out.println("You increased Dexterity by 1 to " + this.getStats(1));
                break;
            case 2: System.out.println("You increased Constitution by 1 to " + this.getStats(2));
                break;
            case 3: System.out.println("You increased Intelligence by 1 to " + this.getStats(3));
                break;
            case 4: System.out.println("You increased Wisdom by 1 to " + this.getStats(4));
                break;
            case 5: System.out.println("You increased Charisma by 1 to " + this.getStats(5));
                break;
        }
        this.calcHP();
        this.calcAC();
        this.calcToHit();
        this.calcExperienceToLevelUp();
        this.calcProfBonus();
        this.fullHeal();
    }
    public boolean levelCheck()
    {
        return this.experience > this.experienceToLevel;
    }
    @Override
    public void targetAttack(BaseUnit target)
    {
        int damage;
        if(this.gear[0] != null)
        {
            damage = this.gear[0].attack(this, target);
        }
        else {
            damage = this.attack(target);
        }
        if(damage > 0)
        {
            System.out.println(this.name + " deals " + damage + " to " + target.getName());
            target.alterHP(-1 * damage);
        }

    }

    @Override
    public void characterMenu() {
        int choice;
        do
        {
            while (true) {
            System.out.println(this.name + "'s character menu");
            System.out.println("1) Character Stats\n2) Inventory\n3) Character Gear");
            System.out.println("4) Exit Menu");
            System.out.print("Your Choice: ");
            Scanner kb = new Scanner(System.in);
            choice = 3;
            try {
                choice = Integer.parseInt(kb.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid Input");
            }
        }
        switch (choice) {
            case 1:
                this.displayStats();
                break;
            case 2:
                this.displayInventory();
                break;
            case 3:
                this.displayGear();
                break;
            default:
                choice = 4;
                break;
        }

        } while(choice != 4);
    }
    public void displayStats()
    {
        System.out.println(this.name + "'s Character Stats");
        System.out.println("Level " + this.level);
        System.out.println("Experience " + this.experience);
        System.out.println("Experience till level up " + this.experienceToLevel);
        System.out.println("Proficiency Bonus " + this.profBonus);
        System.out.println("Max Health " + this.hp);
        System.out.println("Current Health " + this.currentHp);
        System.out.println("Armor Class " + this.AC);
        System.out.println("Currency " + this.currency);
        System.out.println("Strength " + getStats(0));
        System.out.println("Dexterity " + getStats(1));
        System.out.println("Constitution " + getStats(2));
        System.out.println("Intelligence " + getStats(3));
        System.out.println("Wisdom " + getStats(4));
        System.out.println("Charisma " + getStats(5));
        Scanner kb = new Scanner(System.in);
        kb.nextLine();
    }
    public void displayInventory()
    {
        int choice = 0;
        int curSize = this.inventory.size();
        do
        {
            int x = 0;
            //System.out.println("Inventory is not set up yet");
            if(this.inventory.size() == 0)
            {
                System.out.println("The Inventory is Empty");
                return;
            }
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println(i + ") " + this.inventory.get(i).getName());
                x++;
            }
            System.out.println(x + ") to exit");
            System.out.print("Which item do you wish to interact with: ");

            try{
                Scanner kb = new Scanner(System.in);
                choice = Integer.parseInt(kb.nextLine());
                if(choice >= 0 && choice < this.inventory.size())
                {
                    this.inventory.get(choice).itemMenu(this);
                    if(curSize != this.inventory.size())
                    {
                        choice = 0;
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("Invalid Input");
            }
        }while(choice != this.inventory.size());
    }
    public void displayGear()
    {
        int choice = 0;
        do {
            if (gear[0] != null) {
                System.out.println("1) Weapon: " + gear[0].getName());
            } else {
                System.out.println("1) Weapon None");
            }
            if (gear[1] != null) {
                System.out.println("2) Armor " + gear[1].getName());
            } else {
                System.out.println("2) Armor none");
            }
            try {
                Scanner kb = new Scanner(System.in);
                choice = Integer.parseInt(kb.nextLine());

            } catch (Exception e) {
                choice = 4;
            }
            switch (choice) {
                case 1:
                    if (gear[0] != null) {
                        Weapon temp = (Weapon) gear[0];
                        temp.itemMenu(this);
                    } else {
                        System.out.println("No Item Equipped, Exiting");
                        choice = 4;
                    }
                    break;
                case 2:
                    if (gear[1] != null) {
                        Weapon temp = (Weapon) gear[1];
                        temp.itemMenu(this);
                    } else {
                        System.out.println("No Item Equipped, Exitting");
                        choice = 4;
                    }
                    break;
            }
        }while(choice != 4);
    }
    public void setAC(int ac)
    {
        this.AC = ac;
    }
    @Override
    public void battleActions(BaseUnit target)
    {
        int action;
        while(true) {
            System.out.println("It is " + this.name + "'s turn!");
            System.out.println("1) Attack");
            System.out.println("2) use Item");
            System.out.print("Your Action ");
            try {
                Scanner kb = new Scanner(System.in);
                action = Integer.parseInt(kb.nextLine());
                break;
            }
            catch (Exception e)
            {
                System.out.println("Input failed");
            }
        }

        switch(action)
        {
            case 1:
                if(gear[0] != null)
                {
                    gear[0].weaponAttack(this, target);
                }
                else {
                    this.targetAttack(target);
                }
                break;
            case 2:
                this.displayInventory();
                break;
        }
    }
    public void printInventory()
    {
        for(int i = 0; i < inventory.size(); i++)
        {
            System.out.println(i + ") " + inventory.get(i).getName());
        }
    }
}
