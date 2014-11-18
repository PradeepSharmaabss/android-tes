package Spells.Archetype;

/**
 * Created by Scott on 14/11/2014.
 */
public class ArchetypePower {
    int poisonStacks = 0;
    int illusionStacks = 0;
    int mindStacks = 0;
    int lifeStealStacks = 0;
    int frostStacks = 0;
    int vitroStacks = 0;
    int burnStacks = 0;

    public ArchetypePower(int _poison, int _illusion, int _mind, int _lifesteal, int _frost, int _vitro, int _burn) {
        this.poisonStacks = _poison;
        this.illusionStacks = _illusion;
        this.mindStacks = _mind;
        this.lifeStealStacks = _lifesteal;
        this.frostStacks = _frost;
        this.vitroStacks = _vitro;
        this.burnStacks = _burn;
    }

}
