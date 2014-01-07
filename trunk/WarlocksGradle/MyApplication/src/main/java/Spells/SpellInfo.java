package Spells;

/**
 * Created by Scott on 3/01/14.
 */
public class SpellInfo {
    public SpellType spellType;
    public int Rank;
    public SpellInfo(SpellType _s, int _r)
    {
        spellType= _s;
        Rank= _r;
    }
    public void SetOrIncrement(SpellType s)
    {
        if(spellType ==s)
        {
            if(Rank<7)
               Rank+=1;
        }
        else
        {
            spellType = s;
            Rank= 1;
        }
    }
    @Override
    public String toString() {
        return Rank+ ". " + spellType.toString();
    }
}
