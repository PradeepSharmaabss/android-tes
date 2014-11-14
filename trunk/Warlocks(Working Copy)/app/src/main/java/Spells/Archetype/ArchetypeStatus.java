package Spells.Archetype;

import developmental.warlocks.GL.NewHeirarchy.Collideable;

/**
 * Created by Scott on 14/11/2014.
 */
public class ArchetypeStatus {
    public int Stacks = 0;
    public int Countdown = 0;
    private int graceperiod = 200;
    public ArchetypeStatus (int _g)
    {
        graceperiod = _g;
    }
    void AddStacks(int stacks)
    {
        this.Stacks+=stacks;
        this.Countdown = graceperiod;

    }
    void Update( Collideable parent)
    {
        if(this.Stacks==100)
        {
            this.Countdown=0;
            this.Stacks=0;
            GetEffect(parent);
        }
        if(this.Countdown>0)
        {
            Countdown--;

        }
        else
        {
            Stacks = 0;
        }
    }

    protected void GetEffect(Collideable parent) {
    }

}
