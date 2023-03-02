package WeaponryAndItems;

import Characters.PlayerCharacter;
import java.util.Scanner;

public class HealthAltering implements Item
{
    private int healthModifier;
    private String name;
    int tier;
    public HealthAltering()
    {
        this.healthModifier = 1;
        this.tier = 1;
        this.name = "Health Potion Tier 1";
    }
    public HealthAltering(String name, int healthModifier, int tier)
    {
        this.name = name;
        this.healthModifier = healthModifier;
        this.tier = tier;
    }
    @Override
    public void inspectItem()
    {
        System.out.println(this.name);
        System.out.println("A potion used to restore health");
        System.out.println("Tier: " + this.tier + "Health Modifier: " + this.healthModifier);
    }
    @Override
    public void useItem(PlayerCharacter user)
    {
        int userCon = user.getStats(2);
        double restoredHP = (userCon * Math.random()) * this.healthModifier * this.tier;
        user.alterHP((int) restoredHP);
        System.out.println(user.getName() + " restored " + restoredHP + " hp using a Health Potion!");
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public void itemMenu(PlayerCharacter player)
    {
        int choice = 0;
        do {
            System.out.println(this.name + "'s Item Menu");
            System.out.println("1) Inspect Item");
            System.out.println("2) Use Item");
            System.out.println("3) Exit");
            try {
                Scanner kb = new Scanner(System.in);
                choice = Integer.parseInt(kb.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid Input");
                choice = 3;
            }
            switch (choice)
            {
                case 1: this.inspectItem();
                        break;
                case 2: this.useItem(player);
                        break;
            }
        }while(choice != 3);
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
