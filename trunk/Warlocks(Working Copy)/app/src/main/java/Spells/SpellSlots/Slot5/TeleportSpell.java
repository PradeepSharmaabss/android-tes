package Spells.SpellSlots.Slot5;

import Actors.Player;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;

/**
 * Created by Scott on 21/10/2014.
 */
public class TeleportSpell extends Spell {

    public TeleportSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
    }
    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        this.parent.position = new Vector(Dest.x , Dest.y );
        this.parent.destination=null;
        (this).parent.velocity=new Vector(0,0);
    }

}
