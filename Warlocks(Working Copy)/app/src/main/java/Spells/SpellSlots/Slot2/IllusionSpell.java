package Spells.SpellSlots.Slot2;

import android.util.Log;

import com.developmental.warlocks.R;

import SpellProjectiles.BoomerangProjectile;
import SpellProjectiles.FireballProjectile;
import SpellProjectiles.IllusionFakeProjectile;
import SpellProjectiles.IllusionRealProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

/**
 * Created by Scott on 21/10/2014.
 */
public class IllusionSpell extends Spell {
    public IllusionSpell(GameObject _parent, SpellInfo s) {
        super(_parent, s);
    }

    @Override
    protected void Shoot(iVector Dest) {
        SimpleGLRenderer.addObject(new IllusionRealProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
        int k = Global.GetRandomNumer.nextInt(3);
        double degrees = Math.atan2((double)Dest.y-this.parent.bounds.Center.y,(double)Dest.x-this.parent.bounds.Center.x);
        double degrees1 =0;
        double degrees2 =0;
        switch(k)
        {
            case 0:
                degrees1 = degrees+Math.toRadians(22.5);
                degrees2 = degrees-Math.toRadians(22.5);

                break;
            case 1:
                degrees1= degrees+Math.toRadians(22.5);
                degrees2= degrees1+Math.toRadians(22.5);

                break;
            case 2:
                degrees1 = degrees-Math.toRadians(22.5);
                degrees2 = degrees1-Math.toRadians(22.5);
                break;
        }

        float w = Vector.DistanceBetween(this.parent.bounds.Center,new Vector(Dest.x,Dest.y));
        Vector Dest1 = new Vector((float)(w*Math.cos(degrees1)+ this.parent.bounds.Center.x),(float)(w*Math.sin(degrees1)+ this.parent.bounds.Center.y));

        SimpleGLRenderer.addObject(new IllusionFakeProjectile(this.parent.bounds.Center, Dest1, this.parent));
        Vector Dest2 = new Vector((float)(w*Math.cos(degrees2)+ this.parent.bounds.Center.x),(float)(w*Math.sin(degrees2)+ this.parent.bounds.Center.y));

        SimpleGLRenderer.addObject(new IllusionFakeProjectile(this.parent.bounds.Center, Dest2, this.parent));
    }
}
