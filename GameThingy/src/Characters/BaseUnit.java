package Characters;
import WeaponryAndItems.*;

import java.util.*;

public class BaseUnit
{
    protected String name;
    protected int hp;
    protected int currentHp;
    protected int AC;
    protected int chanceToHit;
    protected int[] stats = new int[6];
    protected int profBonus;
    protected int level;
    protected int AttackType;
    protected int damage = 8;
    protected int currentActionScore;
    protected int currency;

    protected ArrayList<Item> inventory = new ArrayList<Item>();

    protected Equipable[] gear = new Equipable[2];
    public BaseUnit()
    {
        Scanner kb = new Scanner(System.in);

        while(true) {
            try {
                System.out.println("What is your name?");
                System.out.print("Your name: ");
                this.name = kb.nextLine();
                if(!(this.name.isEmpty()))
                {
                    break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Invalid Input");
            }
            }
        for(int i = 0; i < stats.length; i++)
        {
            double temp = (Math.random() * 10) + 10;
            stats[i] = (int) temp;
        }
        System.out.print("Hello " + this.name + " Your stats are ");
        for(int i = 0; i < stats.length; i++) {
            System.out.print(stats[i] + " ");
        }
        kb.nextLine();
        this.level = 1;
        this.AttackType = 0;
        this.currency = 20;
        this.calcHP();
        this.calcAC();
        this.calcToHit();
        this.fullHeal();
        this.calcProfBonus();
        this.gear[0] = new Weapon();
        this.gear[0].dequip((PlayerCharacter) this);
    }

    public BaseUnit(String name, int[]stats, int hp, int AC, int profBonus, int AttackType, int damage)
    {
        this.name = name;
        for(int i = 0; i < this.stats.length; i++)
        {
            this.stats[i] = stats[i];
        }
        this.hp = hp;
        this.AC = AC;
        this.profBonus = profBonus;
        this.AttackType = AttackType;
        calcToHit();
        this.damage = damage;
        this.fullHeal();
    }
    public void calcHP()
    {
        this.hp = 8 + this.profBonus + (this.getStatModifier(2) * this.level);
    }
    public void calcAC()
    {
        this.AC = 8 + this.getStatModifier(1);
    }
    public void calcToHit()
    {
        this.chanceToHit = this.profBonus + this.getStatModifier(this.AttackType);
    }
    public int getStatModifier(int type)
    {
        return ((this.stats[type] - 10) / 2);
    }
    public int getAC()
    {
        return this.AC;
    }
    public int getHP()
    {
        return this.hp;
    }
    public int getCurrentHp()
    {
        return this.currentHp;
    }
    public int getChanceToHit()
    {
        return this.chanceToHit;
    }

    public int getProfBonus()
    {
        return this.profBonus;
    }
    public int getLevel()
    {
        return this.level;
    }
    public int getAttackType()
    {
        return this.AttackType;
    }
    public int getStats(int statType)
    {
        return this.stats[statType];
    }
    public int getStatWithModifier(int statType)
    {
        return this.getStatModifier(statType);
    }
    public String getName()
    {
        return this.name;
    }
    public int roll()
    {
        return (int) ((Math.random() * 20) + 1);
    }
    public int roll(int dieSize)
    {
        return (int) ((Math.random() * dieSize) + 1);
    }
    public int attack(BaseUnit target)
    {
        int rolled = this.roll();
        if(rolled == 20)
        {
            System.out.println("Critical Hit!");
            int damageDealt = this.roll(damage) + this.roll(damage) + this.getStatModifier(this.AttackType);
            return damageDealt;
        }
        else if(rolled == 1)
        {
            System.out.println("Cirtical Failiure!");
            return 0;
        }
        else
        {
            int toHit = rolled + this.chanceToHit;
            if(toHit >= target.getAC())
            {
                System.out.println("The Attack Hits!");
                return this.roll(damage) + this.getStatModifier(this.AttackType);
            }
            else
            {
                System.out.println("The Attack Missed!");
                return 0;
            }
        }
    }
    public void alterHP(int bonus)
    {
        this.currentHp += bonus;
    }
    public void rollActionScore()
    {
        this.currentActionScore = roll() + this.getStatModifier(1);
    }
    public int getActionScore()
    {
        return this.currentActionScore;
    }
    public boolean healthCheck()
    {
        return (this.currentHp > 0);
    }
    public void battleActions(BaseUnit target)
    {
        int action;
        while(true) {
            System.out.println("It is " + this.name + "'s turn!");
            System.out.println("1) Attack");
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
                this.targetAttack(target);
            case 2:
        }
    }

    public void targetAttack(BaseUnit target)
    {
        int damage = this.attack(target);
        if(damage > 0)
        {
            System.out.println(this.name + " deals " + damage + " to " + target.getName());
            target.alterHP(-1 * damage);
        }

    }
    public void fullHeal()
    {
        this.currentHp = this.hp;
    }
    public void increaseStat(int stat, int amount)
    {
        this.stats[stat] += amount;
    }
    public void calcProfBonus()
    {
        this.profBonus = 2 + (this.level / 5);
    }
    public void increaseExperience(int amount)
    {

    }
    public int getExpWorth()
    {
        return 0;
    }
    public int getCurrency()
    {
        return this.currency;
    }
    public void increaseCurrency(int money)
    {
        this.currency += money;
    }
    public void decreaseCurrency(int minusMoney)
    {
        this.currency -= minusMoney;
    }
    public void characterMenu()
    {

    }
    public int getInvenSize()
    {
        return this.inventory.size();
    }
    public void addItem(Item newItem)
    {
        if(this.inventory.size() >= 8)
        {
            System.out.println(newItem.getName() + " cannot be added to the inventory as the inventory is full");
        }
        else
        {
            inventory.add(newItem);
        }
    }
    public void removeItem(Item toRemove)
    {
        this.inventory.remove(toRemove);
    }
    public void removeItemIndex(int index)
    {
        this.inventory.remove(index);
    }
    public Equipable checkSlot(int slot)
    {
        return this.gear[slot];
    }
    public void setGear(Equipable newEquipment, int slot)
    {
        this.gear[slot] = newEquipment;
    }

    public String getItemName(int itemIndex)
    {
        if(!(getInvenSize() == 0)) {
            if (itemIndex >= 0 && itemIndex < getInvenSize()) {
                return inventory.get(itemIndex).getName();
            }
            else {
                return "Invalid Name";
            }
        }
        return "Empty";
    }
    public Item getItem(int choice)
    {
        return this.inventory.get(choice);
    }
}
