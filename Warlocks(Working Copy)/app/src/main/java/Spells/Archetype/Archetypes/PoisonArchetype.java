package Spells.Archetype.Archetypes;

import Spells.Archetype.ArchetypeStatus;
import developmental.warlocks.GL.NewHeirarchy.Collideable;

/**
 * Created by Scott on 14/11/2014.
 */
public class PoisonArchetype extends ArchetypeStatus {
    public PoisonArchetype(int _g) {
        super(_g);
    }

    @Override
    protected void GetEffect(Collideable parent) {
        super.GetEffect(parent);
       //TODO Add poison Effect
    }
}
