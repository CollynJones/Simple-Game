import Characters.BaseUnit;
import Characters.PlayerCharacter;
import WeaponryAndItems.Item;
import WeaponryAndItems.ItemFactoryMainParser;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import java.util.ArrayList;

public class Battle
{
    public ArrayList<BaseUnit> actionOrder = new ArrayList<BaseUnit>();

    public Battle(ArrayList<BaseUnit> actionOrder) {
        this.actionOrder = actionOrder;
    }
    public Battle()
    {

    }

    public boolean battleOneOnOne(@NotNull BaseUnit player, BaseUnit Enemy)
    {
        player.rollActionScore();
        Enemy.rollActionScore();
        List<BaseUnit> Enemies = new ArrayList<BaseUnit>();
        Enemies.add(Enemy);
        if(player.getActionScore() > Enemy.getActionScore())
        {
            this.actionOrder.add(player);
            this.actionOrder.add(Enemy);
        }
        else
        {
            actionOrder.add(Enemy);
            actionOrder.add(player);
        }
        int damage;
        while(Enemy.healthCheck() && player.healthCheck())
        {
            for(int i = 0; i < actionOrder.size(); i++) {
                if(i == 0)
                {
                    actionOrder.get(i).battleActions(actionOrder.get(i + 1));
                }
                else{
                    actionOrder.get(i).battleActions(actionOrder.get(i - 1));
                }
                if(!(Enemy.healthCheck() && player.healthCheck()))
                {
                    break;
                }
            }
            System.out.println(player.getName() + " has " + player.getCurrentHp() + " \n" + Enemy.getName() + " has " + Enemy.getCurrentHp());
        }
        if(player.healthCheck())
        {
            System.out.println(player.getName() + " has slain " + Enemy.getName() + " and has gained " + Enemy.getExpWorth());

            player.increaseExperience(Enemy.getExpWorth());
            ItemFactoryMainParser fact = new ItemFactoryMainParser();
            System.out.print(Enemy.getName());
            Item newItem = fact.createItem((PlayerCharacter) player);
            if (newItem != null)
            {
                System.out.println(" dropped " + newItem.getName() + "!");
                player.addItem(newItem);
            }
            return true;
        }
        else
        {
            System.out.println(Enemy.getName() + " is the winner!");
            return false;
        }
    }
    public void clearActionOrder()
    {
        this.actionOrder.clear();
    }
}
