package Actors;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.developmental.myapplication.RenderThread;

import java.util.ArrayList;

import Game.ObjectType;
import Spells.SpellInfo;
import Tools.Vector;

public class EllipseMovingAI extends Enemy {
    public EllipseMovingAI(int _charsheet, SpellInfo[] _spellList, Vector _position) {
        super(_charsheet,_spellList,_position);
    }
    public EllipseMovingAI(ArrayList<Bitmap> _spriteSheet, Vector _pos, SpellInfo[] s) {
        super(_spriteSheet, _pos,s);
        double _x = (RenderThread.l.platform.size.x / 2 - 3)
                * Math.cos((double) 0)
                + RenderThread.l.platform.position.x;
        double _y = (RenderThread.l.platform.size.y / 2 - 3)
                * Math.sin((double)0)
                + RenderThread.l.platform.position.y;
        this.objectObjectType = ObjectType.Enemy;
        this.destination = new Vector((float) _x, (float) _y);
        this.maxVelocity = 10;
        this.paint.setColor(Color.YELLOW);
    }
}
