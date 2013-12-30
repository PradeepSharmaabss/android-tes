package Spells;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import Actors.Player;
import Game.GameObject;
import Game.ObjectType;
import Game.SpellEffect;
import HUD.PopupText;
import SpellProjectiles.FireballProjectile;
import Tools.Vector;
import Tools.iVector;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

public class Spell {
    public int Cooldown = 50;
    int CastTime = 3;
    public int Current = 0;
    GameObject parent;
    Paint p;
    Bitmap curr;
    int castphase;
    boolean fired = false;
    int sz = 40;
   protected iVector targetLocation;
    public Spell(GameObject _parent) {
        this.parent = _parent;
        p = new Paint();
        castphase = CastTime;
        p.setColor(Color.RED);
        curr = Global.ButtonImages.get(4);
        // owner = parent.id;
    }

    public void DrawButton(Canvas c, int x, int y, float w, float h) {
        c.drawBitmap(curr, x, y + 10, p);
    }

    public boolean Cast(iVector[] dest) {
        if(!parent.frozen&&!parent.dead)
        if (dest.length > 0)
            for (int x = 0; x < dest.length; x++)

                if (this.Current == 0) {
                    this.targetLocation = parent.position.subtract(dest[x]);
                    castphase= 0;
                    fired=  true;
                    this.Current = this.Cooldown;

                    this.parent.Debuffs.add(new SpellEffect(this.CastTime, SpellEffect.EffectType.Cast, Global.Sprites.get(2), this.parent));
                    if (this.parent.objectObjectType == ObjectType.Enemy || this.parent.objectObjectType == ObjectType.Player) {
                        ((Player) this.parent).Animate(new Vector(dest[x].x, dest[x].y));
                    }
                    return true;
                }
        return false;
    }
    public void Cast(iVector dest) {

        if(!parent.frozen&&!parent.dead&&!parent.casting)

                if (this.Current == 0) {
                    this.targetLocation = parent.position.subtract(dest);
                    castphase= 0;
                    fired=  true;
                    this.Current = this.Cooldown;

                    this.parent.Debuffs.add(new SpellEffect(this.CastTime, SpellEffect.EffectType.Cast, Global.Sprites.get(2), this.parent));
                    if (this.parent.objectObjectType == ObjectType.Enemy || this.parent.objectObjectType == ObjectType.Player) {
                        ((Player) this.parent).Animate(new Vector(dest.x, dest.y));
                    }
                    return;
                }
    }

    public void Update() {
        if(fired)
        {
            castphase+=1;
            if(castphase==CastTime)
            {
                Shoot((parent.position.subtract(this.targetLocation)));
                fired= false;
            }
        }
        else
        {
        if (this.Current > 0)
            this.Current -= 1;
        }
    }

    void Shoot(iVector Dest) {
        RenderThread.addObject(new FireballProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
    }
}
