package Spells.Archetype;

import Spells.Archetype.Archetypes.BurnArchetype;
import Spells.Archetype.Archetypes.FrostArchetype;
import Spells.Archetype.Archetypes.IllusionArchetype;
import Spells.Archetype.Archetypes.LifeStealArchetype;
import Spells.Archetype.Archetypes.MindArchetype;
import Spells.Archetype.Archetypes.PoisonArchetype;
import Spells.Archetype.Archetypes.VitroArchetype;
import developmental.warlocks.GL.NewHeirarchy.Collideable;

/**
 * Created by Scott on 14/11/2014.
 */
public class ArchetypeManager {
ArchetypeStatus Poison= new PoisonArchetype(200);
    ArchetypeStatus Burn= new BurnArchetype(200);
    ArchetypeStatus Vitro= new VitroArchetype(200);
    ArchetypeStatus Illusion= new IllusionArchetype(200);
    ArchetypeStatus Frost= new FrostArchetype(200);
    ArchetypeStatus LifeSteal= new LifeStealArchetype(200);
    ArchetypeStatus Mind= new MindArchetype(200);
    public ArchetypeManager()
    {

    }
    public void AddStacks(ArchetypePower power)
    {
        Burn.AddStacks(power.firestacks);
        Frost.AddStacks(power.frostStacks);
        Vitro.AddStacks(power.vitroStacks);
        Illusion.AddStacks(power.illusionStacks);
        LifeSteal.AddStacks(power.lifeStealStacks);
        Poison.AddStacks(power.poisonStacks);
        Mind.AddStacks(power.mindStacks);
    }
    public void Update(Collideable parent)
    {
        Poison.Update(parent);
        Burn.Update(parent);
        Vitro.Update(parent);
        Illusion.Update(parent);
        Mind.Update(parent);
        LifeSteal.Update(parent);
        Poison.Update(parent);
    }

}
