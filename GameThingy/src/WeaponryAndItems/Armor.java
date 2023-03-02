package WeaponryAndItems;

import Characters.BaseUnit;
import Characters.PlayerCharacter;

import java.util.Scanner;

public class Armor implements Equipable, Item
{
    String name;
    int slotNumber;
    boolean equipped;
    int statUsed;
    int baseAC;
    String inspect;
    int tier;
    int totalAC;
    public Armor(String name, int slotNumber, int stat, int AC, String inspect, int tier)
    {
        this.name = name;
        this.slotNumber = slotNumber;
        this.statUsed = stat;
        this.baseAC = AC;
        this.inspect = inspect;
        this.tier = tier;
        this.totalAC = tier + baseAC;
        this.equipped = false;
    }
    @Override
    public void equip(PlayerCharacter player)
    {
        if(this.equipped)
        {
            System.out.println("This item is already equipped");
        }
        else
        {
            Equipable currentSlot = player.checkSlot(this.slotNumber);
            if(currentSlot == null)
            {
                player.setGear(this, this.slotNumber);
                player.removeItem(this);
                this.equipped = true;
            }
            else
            {
                try
                {
                    Item toInventory = (Item) currentSlot;
                    player.addItem(toInventory);
                    player.setGear(this, this.slotNumber);
                    player.removeItem(this);
                    this.equipped = true;
                }
                catch (Exception e)
                {
                    System.out.println("Failed to Equip");
                }

            }
        }
    }

    @Override
    public void dequip(PlayerCharacter player)
    {
        System.out.println(this.name + " was dequipped from " + player.getName());
        player.addItem(this);
        player.setGear(null, this.slotNumber);
        player.calcAC();
        this.equipped = false;
    }

    @Override
    public int checkSlot()
    {
        return this.statUsed;
    }

    @Override
    public int attack(PlayerCharacter player, BaseUnit target)
    {
        System.out.println(this.name + " is an armor, this does not have an attack method");
        return 0;
    }

    @Override
    public void inspectItem()
    {
        System.out.println(this.inspect);
    }

    @Override
    public void useItem(PlayerCharacter player)
    {

    }

    @Override
    public String getName() {
        return this.name;

    }

    @Override
    public void weaponAttack(PlayerCharacter playerCharacter, BaseUnit target) {
        System.out.println("You should not hit people with armor, kinda weird");
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
        return 0;
    }
}
