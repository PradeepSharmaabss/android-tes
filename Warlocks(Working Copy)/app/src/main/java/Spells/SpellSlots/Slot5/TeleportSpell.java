package Spells.SpellSlots.Slot5;

import SpellProjectiles.FiresprayProjectile;
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
public class TeleportSpell extends Spell {

    public TeleportSpell(GameObject _parent, SpellInfo s) {
        super(_parent, s);
    }
    @Override
    protected void Shoot(iVector Dest) {
        this.parent.position = new Vector(Dest.x , Dest.y );
        this.parent.destination=null;
        (this).parent.velocity=new Vector(0,0);
    }

}
