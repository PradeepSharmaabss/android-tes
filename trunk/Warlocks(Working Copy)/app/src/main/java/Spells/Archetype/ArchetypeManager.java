package Spells.Archetype;

/**
 * Created by Scott on 14/11/2014.
 */
public class ArchetypeManager {
ArchetypeStatus Poison= new ArchetypeStatus(200);
    ArchetypeStatus Burn= new ArchetypeStatus(200);
    ArchetypeStatus Vitro= new ArchetypeStatus(200);
    ArchetypeStatus Illusion= new ArchetypeStatus(200);
    ArchetypeStatus Frost= new ArchetypeStatus(200);
    ArchetypeStatus LifeSteal= new ArchetypeStatus(200);
    ArchetypeStatus Mind= new ArchetypeStatus(200);
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
