package WeaponryAndItems;

import Characters.PlayerCharacter;

public interface Item
{
    void inspectItem();
    void useItem(PlayerCharacter player);
    String getName();
    void itemMenu(PlayerCharacter player);
    int getPrice();
}
