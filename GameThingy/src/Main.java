import Characters.*;

import WeaponryAndItems.Item;
import WeaponryAndItems.ItemFactoryMainParser;
import sun.java2d.loops.FillRect;
import java.util.*;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
/*
        BaseUnit firstCharacter = new PlayerCharacter();
        System.out.println("HP: " + firstCharacter.getHP());
        System.out.println("AC: " + firstCharacter.getAC());
        System.out.println("Chance to Hit: " + firstCharacter.getChanceToHit());
        System.out.println("Level: " + firstCharacter.getLevel());
        BaseUnit enemy = GeneralMethods.makeMonster();
        System.out.println(enemy.getName() + " Approaches!");
        System.out.println("HP: " + enemy.getHP());
        System.out.println("AC: " + enemy.getAC());
        System.out.println("Chance to Hit: " + enemy.getChanceToHit());
        Battle firstCombat = new Battle();
        boolean result = firstCombat.battleOneOnOne(firstCharacter, enemy);
        if(result)
        {
            firstCharacter.increaseExperience((Monster) enemy);
        }
        System.out.println("Would you like to try again? y/n");
        Scanner kb = new Scanner(System.in);
        String res = kb.nextLine();
        if(res.equalsIgnoreCase("y"))
        {
            while(res.equalsIgnoreCase("y"))
            {
                firstCombat.clearActionOrder();
                firstCharacter.fullHeal();
                enemy = GeneralMethods.makeMonster();
                System.out.println(enemy.getName() + " Approaches!");
                System.out.println("HP: " + enemy.getHP());
                System.out.println("AC: " + enemy.getAC());
                System.out.println("Chance to Hit: " + enemy.getChanceToHit());
                result = firstCombat.battleOneOnOne(firstCharacter, enemy);
                if (result)
                {
                    firstCharacter.increaseExperience((Monster) enemy);
                }
                System.out.println("Would you like to try again? y/n");
                kb = new Scanner(System.in);
                res = kb.nextLine();
            }
        }

        PlayerCharacter testPlayer = new PlayerCharacter();
        ItemFactoryMainParser fact = new ItemFactoryMainParser();
        Item TestItem;

        TestItem = fact.createItem(testPlayer);
        if(TestItem != null)
        {
            System.out.println("You got nothing");
        }
        else {
            testPlayer.addItem(TestItem);
        }
        testPlayer.characterMenu();
        */

        GeneralMethods.mainMenu();

    /*
    List of next steps
    -First things first. Saving it will read a file and then it will say the name of the saved character, if they select that name, then it will go to that line
    and create that character from the stored line
        Ideas for implementations: By adding a new function called Leaving or something like that, it will end the current location options which is what is needed to be done in
        the general methods, that should be moved into a new class called locationRegion that has a location inside of it as well as a funcion for leaving. This will make things nicer so that the call stack
        doesn't have Grassrest constantly queued up waiting to be used again.


    -Adding a quit/save function after taking a rest at Edwin's would be a good addition so it doesn't fuck me to hard while trying to save
        -Added the save function inside of the GeneralMethods file that will take in the player character and their location and area so that when they reload the game they will start up right from there.
        -In the file needs to save character stats, area, and location to have all areas created.

    -Cleaning up user inputs so accidentally putting in bad inputs doesn't crash the entire program and cause me to have to restart from scratch
        -Most likely just adding try catch blocks around any user inputs.

    -Adding money so that the user can start buy items
        -Pretty simple, just add a new variable in BaseUnit called currency and then add methods to get and change the currency. It is in BaseUnit so that enemies can have
        currency and it can be used to know how much gold the player gets from the enemy

    -Make an Inventory
        -Making an interface called Item allows there to be either a Linked List or an Array that has a maxsize of like 30.

    -Adding Items, starting with basic items like food and then add in items like black beads and poison
        -Using the interface Item, adding the use function onto the Item will allow the user for the item to consume the food and heal their hp. Items can be used inside and
        outside of battle, meaning a new menu has to be added to inspect character stats and their inventory

    -Adding equipment that can change your attack style and increase your damage roller
        -Giving these the Item interface will allows them to go into the inventory and also make their use ability check the current instance, being combat or not. If in combat then
        the use function will fail as you cannot switch items during combat, but you can do it outside of combat.

    -Adding Armor that can increase you AC and set it to a flat amount
        -Like with Weapons, Armor can be added ad an item that has the use function to equip itself. It cannot be equipped during combat, but can be done outside of combat.
        The check for the current instance of combat or not could be done by checking the current game state which could be a state that is changed in the GeneralMethods
        that has a static variable called GameState, and TRUE means combat while FALSE means not in combat. doing these checks will allow the user to wear the armor or not.
        -Equipping the armor will alter the AC based off the armors AC calculation. The armor calculation will calculate and send the armor back based on if there is armor or not.

    -Adding more areas that can be traveled to as well as new inns that can be used to save at.
        -Already done with area, but that can be used to create different location files that each have their own locations that are used and all just have an interface called location
        that allows them to be used in the Area object.

    -Adding the Save function saving the current user's location.
        -Already implemented hopefully as it should already be saved from when the Save function was first created.

    -implement spells and spell stores
        -Add a new interface called spells and have it first be simple by adding in Mana and different spell types. Each spell has a type for INT,WIS, or CHA. Each of these types
        are useful for deciding which spells a caster wants to learn and also add in stores that have a predetermined amount of spells that are all basic spells that deal damage based
        off of their type.
        -The BaseUnit will now get a new variable called mana that will be used to cast spells.
        -A new interface called spells will be made as well as spellTypes like spellCast or spellSave or spellAOESave, or spellAOEAttack. which will seperate the spells into
        different types based on how it will interact with enemies.

    -Add in Status Effects
        -Status effects should be simple by adding a new interface called status that has the ability of status effect which will have the effect timing, which will activate at a certain effect timing based on combat,
        and also have the effect which will cause the status effect to activate based on the current object that is of type status.

    -Add in Multiple Unit Combat
        -Should be simple, just have to add in ArrayList's instead of just simple 1 on 1, and add in new queries from the user to attack one of the multiple targets.

    -Add in Spells that Have Saving Throws
        -Like it was explained earlier, bring in a new type of spell called SpellSave.

    -Add in Multi Hit Spells like Fireball
        -Create the spellAOESave and the spellAOEAttack.

    -Create Simple one dimensional Dungeons
        Should be simple as making new locations with a series of methods called based on a premade map inside of the chat box made from interacting with all of the methods.

    -Add in healing spells
        -Add in a new spell called spellSingleHeal and spellAOEHealing

    -Add in new randomized Dungeons
        -Create a map by randomizing a 2d array and then making channels between each from a starting and end point. Just starting ideas on this

    -Add in area restricted enemies and text files for different area monsters or dungeon specific monsters
        -Should just have to add a new Monster that is an extenstion of Monster or BaseUnit called superiorMonster or monsterBoss.

    -Add in Boss Monsters that have abilities and apply Status Effects
        -Add in a new interface called abilites that would have status effects on attack and also add in a new check during their attack for any attack based abilites that would
        be added as a new type in the monsterBoss class.

    -Put program in GUI
        -Make it into a GUI, sounds simple, we'll see how it goes.

    -Add in GUI button options instead of Typing1

       -Should be pretty simple, just adding in buttons that give in specified outputs.

    -Add death mechanics
        -Make it so that either a character is deleted upon dying or a character gets an exp penalty for dying.

    -Add in simple characters
        -Adding in new classes for a player to play as.

    -Make Enemy AI
        -Make enemies use AOE abilites, give them the thought to attack healers, dps, or low health enemies. All AI based on the creature.

    -Add in Quests
        -Add in Quests that are just like, kill this creature or kill this dungeon and it will have random checks during gameplay based on types of quests made, be it before combat, after dungeon completion, or talking to certain NPC's

    -Add in new Features gained every 10 levels
        -Creating new special abilies that can be made as new abilites inside of either an ability interface or an ability class that has abilites for each class like clericAbilities or warriorAbilities.


     */

    }
}