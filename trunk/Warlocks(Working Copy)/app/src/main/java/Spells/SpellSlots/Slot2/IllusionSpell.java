package Spells.SpellSlots.Slot2;

import Actors.Player;
import SpellProjectiles.IllusionFakeProjectile;
import SpellProjectiles.IllusionRealProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

/**
 * Created by Scott on 21/10/2014.
 */
public class IllusionSpell extends Spell {
    public IllusionSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
        archetype = Archetype.Illusion;
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new IllusionRealProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent, this.Rank));
        int k = Global.GetRandomNumer.nextInt(3);
        double degrees = Math.atan2((double) Dest.y - Origin.y, (double) Dest.x - Origin.x);
        double degrees1 = 0;
        double degrees2 = 0;
        switch (k) {
            case 0:
                degrees1 = degrees + Math.toRadians(22.5);
                degrees2 = degrees - Math.toRadians(22.5);

                break;
            case 1:
                degrees1 = degrees + Math.toRadians(22.5);
                degrees2 = degrees1 + Math.toRadians(22.5);

                break;
            case 2:
                degrees1 = degrees - Math.toRadians(22.5);
                degrees2 = degrees1 - Math.toRadians(22.5);
                break;
        }

        float w = Vector.DistanceBetween(Origin, new Vector(Dest.x, Dest.y));
        Vector Dest1 = new Vector((float) (w * Math.cos(degrees1) + Origin.x), (float) (w * Math.sin(degrees1) + Origin.y));

        SimpleGLRenderer.addObject(new IllusionFakeProjectile(Origin, Dest1, this.parent, this.Rank));
        Vector Dest2 = new Vector((float) (w * Math.cos(degrees2) + Origin.x), (float) (w * Math.sin(degrees2) + Origin.y));

        SimpleGLRenderer.addObject(new IllusionFakeProjectile(Origin, Dest2, this.parent, this.Rank));
    }
}
