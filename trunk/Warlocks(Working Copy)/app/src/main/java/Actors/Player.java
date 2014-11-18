package Actors;


import javax.microedition.khronos.opengles.GL10;

import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;


public class Player extends GameObject {
    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld) {
        super.draw(gl, offsetX, offsetY, dontDrawInRelationToWorld);
        if(shadowClone!=null)
        {
            shadowClone.draw(gl,offsetX,offsetY,dontDrawInRelationToWorld);
        }
    }

    public Player(int _charsheet, SpellInfo[] _spellList, Vector _position)
    {
        super(_charsheet,_position,_position.add(new Vector(50,-33)),new Vector(100,100),_spellList);
        this.objectObjectType = ObjectType.Player;
    }


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
