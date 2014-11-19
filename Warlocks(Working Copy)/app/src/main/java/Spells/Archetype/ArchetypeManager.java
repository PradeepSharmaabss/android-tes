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
    ArchetypeStatus Poison;
    ArchetypeStatus Burn;
    ArchetypeStatus Vitro;
    ArchetypeStatus Illusion;
    ArchetypeStatus Frost;
    ArchetypeStatus LifeSteal;
    ArchetypeStatus Mind;
    Collideable parent;

    public ArchetypeManager(Collideable _p) {
        parent = _p;
        Poison = new PoisonArchetype(200, _p);
        Burn = new BurnArchetype(200, _p);
        Vitro = new VitroArchetype(200, _p);
        Illusion = new IllusionArchetype(200, _p);
        Frost = new FrostArchetype(200, _p);
        LifeSteal = new LifeStealArchetype(200, _p);
        Mind = new MindArchetype(200, _p);
    }

    public void AddStacks(ArchetypePower power, Collideable Sender) {

        Burn.AddStacks(power.burnStacks, Sender, parent);
        Frost.AddStacks(power.frostStacks, Sender, parent);
        Vitro.AddStacks(power.vitroStacks, Sender, parent);
        Illusion.AddStacks(power.illusionStacks, Sender, parent);
        LifeSteal.AddStacks(power.lifeStealStacks, Sender, parent);
        Poison.AddStacks(power.poisonStacks, Sender, parent);
        Mind.AddStacks(power.mindStacks, Sender, parent);
    }

    public void Update() {
        Poison.Update();
        Burn.Update();
        Vitro.Update();
        Illusion.Update();
        Mind.Update();
        LifeSteal.Update();
        Frost.Update();
    }

}
