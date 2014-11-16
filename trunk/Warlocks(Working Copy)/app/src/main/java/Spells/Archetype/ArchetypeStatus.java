package Spells.Archetype;

import com.developmental.warlocks.R;

import HUD.PopupText;
import Spells.SpellEffect;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 14/11/2014.
 */
public class ArchetypeStatus {
    public int Stacks = 0;
    public int Countdown = 0;
    private int graceperiod = 200;
    private Collideable Parent;

    public ArchetypeStatus (Collideable _p,int _g)
    {
        graceperiod = _g;
        this.Parent = _p;
    }
    void AddStacks(int stacks)
    {

        this.Stacks+=stacks;
        this.Countdown = graceperiod;

    }
    void Update( )
    {
        if(this.Stacks>=100)
        {
            this.Countdown=0;
            this.Stacks=0;
            GetEffect(Parent);
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
     //   SimpleGLRenderer.popupTexts.add(new PopupText(PopupText.TextType.Burn,"EFFECT!",SimpleGLRenderer.archie.position.get(),50));
    }

}
