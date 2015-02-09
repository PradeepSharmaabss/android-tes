package Spells.SpellSlots.Slot5;

import java.util.ArrayList;

import Actors.Player;
import Spells.Archetype.ArchetypePower;
import Spells.LoadOutInfo;
import Spells.Spell;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 20/11/2014.
 */
public class MiddleOfActionSpell extends Spell {
    public MiddleOfActionSpell(Player _parent, LoadOutInfo s) {
        super(_parent, s);
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        ArrayList<Player> a = new ArrayList<Player>();
        for (Player p : SimpleGLRenderer.players) {
            if (p.id != parent.id && !p.dead) {
                a.add(p);
            }
        }
        Vector v = new Vector(0, 0);
        for (Player p : a) {
            v = v.add(p.position);
            p.archetypeManager.AddStacks(new ArchetypePower(0, 0, 0, 60, 0, 0, 0), parent);
        }
        v.y = v.y / a.size();
        v.x = v.x / a.size();
        this.parent.position = v;
        this.parent.destination = null;
        (this).parent.velocity = new Vector(0, 0);
    }
}
