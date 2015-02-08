package Actors;


import javax.microedition.khronos.opengles.GL10;

import Spells.Archetype.ArchetypePower;
import Spells.SpellInfo;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;


public class Player extends GameObject {
    public Player(int _charsheet, SpellInfo[] _spellList, Vector _position) {
        super(_charsheet, _position, _position.add(new Vector(50, -33)), new Vector(100, 100), _spellList);
        this.objectObjectType = ObjectType.Player;
    }

    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld) {
        if (!invisible)
            super.draw(gl, offsetX, offsetY, dontDrawInRelationToWorld);
        if (shadowClone != null) {
            shadowClone.draw(gl, offsetX, offsetY, dontDrawInRelationToWorld);
        }

    }

    // based on angle to the destination point the players frame is chosen. it
    // then cycles through until the angle changes
    public void Animate(Vector dest) {
        super.Animate(dest);

    }

    @Override
    public void Update() {
        super.Update();

        bounds.Center = feet;
        if (!this.casting)
            Animate(this.destination);

    }

    public ArchetypePower CalcArchetypePower() {
        int illusion = 0;
        int burn = 0;
        int lifesteal = 0;
        int frost = 0;
        int confuse = 0;
        int poison = 0;
        int glass = 0;
        for (int i = 1; i < 6; i++) {
            switch (Spells[i].archetype) {
                case Burn:
                    burn++;
                    break;
                case Poison:
                    poison++;
                    break;
                case Illusion:
                    illusion++;
                    break;
                case Confuse:
                    confuse++;
                    break;
                case Frost:
                    frost++;
                    break;
                case Vitro:
                    glass++;
                    break;
                case Lifesteal:
                    lifesteal++;
                    break;
            }
        }

        return new ArchetypePower(poison, illusion, confuse, lifesteal, frost, glass, burn);
    }
}
