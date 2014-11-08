package Actors;


import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;


public class Player extends GameObject {
    int frame = 0;
    int timer = 0;
    double angleInDegrees = 0;
    public GameObject Target = null;
    private final int FramesShown = 1;
    public Player(int _charsheet, SpellInfo[] _spellList, Vector _position)
    {
        super(_charsheet,_position,_position.add(new Vector(50,-33)),new Vector(100,100),_spellList);
        this.objectObjectType = ObjectType.Player;
    }


    public boolean Shielded = false;

    @Override
    public void Update() {
        super.Update();

        bounds.Center = feet;
        if (!this.casting)
            Animate(this.destination);
    }

    // based on angle to the destination point the players frame is chosen. it
    // then cycles through until the angle changes
    public void Animate(Vector dest) {
        super.Animate(dest);

    }
}