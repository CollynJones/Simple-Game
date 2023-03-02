package WeaponryAndItems;

import Characters.BaseUnit;
import Characters.PlayerCharacter;

import java.util.Scanner;

public class Weapon implements Equipable, Item
{
    String name;
    int slotNumber;
    boolean equipped;
    int statUsed;
    int damageModifier;
    String inspect;
    int tier;
    int price;
    public Weapon()
    {
        name = "Dagger";
        slotNumber = 0;
        equipped = false;
        statUsed = 1;
        damageModifier = 4;
        inspect = "A rusty dagger engraved with a name that has been lost to time.";
        this.tier = 1;
        int price = 20 * tier;
    }
    public Weapon(String name, int slotNumber, int statUsed, int damageModifier, String inspect, int tier)
    {
        this.name = name;
        this.slotNumber = slotNumber;
        this.statUsed = statUsed;
        this.damageModifier = damageModifier;
        this.inspect = inspect;
        this.tier = tier;
    }
    @Override
    public void equip(PlayerCharacter player)
    {
        Equipable currentSlot = player.checkSlot(0);
        if(currentSlot == null)
        {
            player.setGear(this, 0);
            this.equipped = true;
            player.removeItem(this);
        }
        else
        {
            try
            {
                Item toInventory = (Item) currentSlot;
                player.addItem(toInventory);
                this.equipped = true;
                player.removeItem(this);
            }
             catch (Exception e)
             {
                 System.out.println("Failed to Equip");
             }
        }
    }

    @Override
    public void dequip(PlayerCharacter player)
    {
        if(this != null)
        {
            System.out.println(this.name + " was dequipped from " + player.getName());
            player.addItem(this);
            player.setGear(null, 0);
            this.equipped = false;
        }
    }

    @Override
    public int checkSlot()
    {
        return this.slotNumber;
    }

    @Override
    public void inspectItem()
    {
        System.out.println(this.name);
        System.out.println(this.inspect);
    }

    @Override
    public void useItem(PlayerCharacter player)
    {
        if(equipped)
        {
            this.dequip(player);
            this.equipped = false;
        }
        else
        {
            this.equip(player);
            this.equipped = true;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void itemMenu(PlayerCharacter player)
    {
        int choice = 0;
        do
        {
            System.out.println("Item: " + this.name);
            System.out.println("1) Inspect Item");
            if (equipped)
            {
                System.out.println("2) Dequip");
            }
            else
            {
                System.out.println("2) Equip");
            }
            System.out.println("3) Exit");

            try
            {
                Scanner kb = new Scanner(System.in);
                choice = Integer.parseInt(kb.nextLine());
                switch(choice)
                {
                    case 1: this.inspectItem();
                            break;
                    case 2: if(this.equipped)
                    {
                        dequip(player);
                    }
                    else
                    {
                        equip(player);
                    }
                    break;
                    default:choice = 3;
                }

            }
            catch (Exception e)
            {
                System.out.println("Invalid Input");
            }
        }while(choice != 3);

    }

    @Override
    public int getPrice() {
        return (20 * tier);
    }

    public void weaponAttack(PlayerCharacter player, BaseUnit target)
    {
        int damage = this.attack(player, target);
        if(damage > 0)
        {
            System.out.println(player.getName() + " deals " + damage + " to " + target.getName());
            target.alterHP(-1 * damage);
        }
    }
    public int attack(PlayerCharacter player, BaseUnit target)
    {
        int rolled = player.roll();
        if(rolled == 20)
        {
            System.out.println("Critical Hit!");
            int damageDealt = player.roll(this.damageModifier) + player.roll(this.damageModifier) + player.getStatModifier(this.statUsed) + this.tier;

            return damageDealt;
        }
        else if(rolled == 1)
        {
            System.out.println("Cirtical Failiure!");
            return 0;
        }
        else
        {
            int toHit = rolled + player.getChanceToHit() + this.tier;
            if(toHit >= target.getAC())
            {
                System.out.println("The Attack Hits!");
                return (player.roll(this.damageModifier) + player.getStatModifier(this.statUsed) + this.tier);
            }
            else
            {
                System.out.println("The Attack Missed!");
                return 0;
            }
        }
    }
}
