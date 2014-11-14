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
    int firestacks = 0;

    ArchetypePower(int _p, int _i, int _m, int _l, int _fr, int _v, int _fi) {
        this.poisonStacks = _p;
        this.illusionStacks = _i;
        this.mindStacks = _m;
        this.lifeStealStacks = _l;
        this.frostStacks = _fr;
        this.vitroStacks = _v;
        this.firestacks = _fi;
    }

}
