package Actors;

import android.graphics.Color;

import Game.ObjectType;
import Spells.SpellInfo;
import Tools.Vector;
import developmental.warlocks.GL.SimpleGLRenderer;

public class EllipseMovingAI extends Enemy {
    public EllipseMovingAI(int _charsheet, SpellInfo[] _spellList, Vector _position) {
        super(_charsheet,_spellList,_position);
    }
    public EllipseMovingAI( Vector _pos, SpellInfo[] s) {
        super( _pos,s);
        double _x = (SimpleGLRenderer.l.platform.size.x / 2 - 3)
                * Math.cos((double) 0)
                + SimpleGLRenderer.l.platform.position.x;
        double _y = (SimpleGLRenderer.l.platform.size.y / 2 - 3)
                * Math.sin((double)0)
                + SimpleGLRenderer.l.platform.position.y;
        this.objectObjectType = ObjectType.Enemy;
        this.destination = new Vector((float) _x, (float) _y);
        this.maxVelocity = 10;
    }
}
