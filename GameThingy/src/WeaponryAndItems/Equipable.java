package WeaponryAndItems;

import Characters.BaseUnit;
import Characters.PlayerCharacter;

public interface Equipable
{
    void equip(PlayerCharacter player);
    void dequip(PlayerCharacter player);
    int checkSlot();
    int attack(PlayerCharacter player, BaseUnit target);
    String getName();

    void weaponAttack(PlayerCharacter playerCharacter, BaseUnit target);
}
