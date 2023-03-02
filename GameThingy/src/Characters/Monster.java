package Characters;

public class Monster extends BaseUnit
{
    private int expWorth;
    private int lootTier;

    public Monster()
    {
        super("Skeleton", (new int[] {14, 10, 12, 10, 10, 10}) , 14, 12, 2, 0, 10);
    }
    public Monster(String name, int[]stats, int hp, int AC, int profBonus, int AttackType, int damage,int experience)
    {
        super(name, stats, hp, AC, profBonus, AttackType, damage);
        this.expWorth = experience;
    }

    @Override
    public void battleActions(BaseUnit target)
    {
        System.out.println("It is " + this.name + "'s turn!");
        this.targetAttack(target);
    }
    @Override
    public int getExpWorth()
    {
        return this.expWorth;
    }
}
