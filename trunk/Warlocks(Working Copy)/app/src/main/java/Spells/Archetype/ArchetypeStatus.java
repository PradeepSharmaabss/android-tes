package Spells.Archetype;

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
    void Update()
    {
        if(this.Countdown>0)
        {
            Countdown--;

        }
        else
        {
            Stacks = 0;
        }
    }


}
