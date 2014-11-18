package Spells.Archetype;

import HUD.PopupText;
import Spells.Archetype.Archetypes.BurnArchetype;
import Spells.Archetype.Archetypes.FrostArchetype;
import Spells.Archetype.Archetypes.IllusionArchetype;
import Spells.Archetype.Archetypes.LifeStealArchetype;
import Spells.Archetype.Archetypes.MindArchetype;
import Spells.Archetype.Archetypes.PoisonArchetype;
import Spells.Archetype.Archetypes.VitroArchetype;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.SimpleGLRenderer;

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
    public ArchetypeManager(Collideable _p)
    {
        parent= _p;
        Poison= new PoisonArchetype(200,_p);
        Burn= new BurnArchetype(200,_p);
        Vitro= new VitroArchetype(200,_p);
        Illusion= new IllusionArchetype(200,_p);
        Frost= new FrostArchetype(200,_p);
        LifeSteal= new LifeStealArchetype(200,_p);
        Mind= new MindArchetype(200,_p);
    }
    public void AddStacks(ArchetypePower power,Collideable Sender)
    {

         Burn.AddStacks(power.burnStacks,Sender);
        Frost.AddStacks(power.frostStacks,Sender);
        Vitro.AddStacks(power.vitroStacks,Sender);
        Illusion.AddStacks(power.illusionStacks,Sender);
        LifeSteal.AddStacks(power.lifeStealStacks,Sender);
        Poison.AddStacks(power.poisonStacks,Sender);
        Mind.AddStacks(power.mindStacks,Sender);
    }
    public void Update()
    {
        Poison.Update();
        Burn.Update();
        Vitro.Update();
        Illusion.Update();
        Mind.Update();
        LifeSteal.Update();
        Poison.Update();
    }

}
