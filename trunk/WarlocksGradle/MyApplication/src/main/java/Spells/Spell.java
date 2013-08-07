package Spells;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import Actors.Player;
import Game.GameObject;
import Game.ObjectType;
import Game.SpellEffect;
import Input.Pointer;
import SpellProjectiles.FireballProjectile;
import Tools.Vector;
import com.developmental.warlocks.RenderThread;

public class Spell {
	public int Cooldown = 100;
    int CastTime = 3;
	public int Current = 0;
	GameObject parent;
Paint p;
    int sz= 40;
	public Spell(GameObject _parent) {
		this.parent = _parent;
		p = new Paint();
        p.setColor(Color.RED);
		// owner = parent.id;
	}
    public void DrawButton(Canvas c,int x, int y)
    {
        c.drawCircle(x,y,sz,p);
    }

	public void Cast(List<Pointer> dest) {
		if (RenderThread.finger.sz() >= 2)
			for (int x = 0; x < dest.size(); x++)
				if (dest.get(x).WithinScreen())
					if (this.Current == 0) {
						Shoot(dest.get(x).WorldPos(parent.position));
						this.Current = this.Cooldown;

                        this.parent.Debuffs.add(new SpellEffect(this.CastTime,SpellEffect.EffectType.Cast));
                        if(this.parent.objectObjectType== ObjectType.Enemy||this.parent.objectObjectType== ObjectType.Player)
                        {
                            ((Player) this.parent).Animate(dest.get(x).WorldPos(parent.position));
                        }
					}
	}

	public void Cast(Vector dest) {
		Shoot(dest);

//        try {
//            ClientTask.Send("localhost");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // Current = Cooldown;
	}

	public void Update() {
		if (this.Current > 0)
			this.Current -= 1;
	}

	void Shoot(Vector Dest) {
		RenderThread.addObject(new FireballProjectile(new Vector(this.parent.rect.left
				+ this.parent.rect.width() / 2, this.parent.rect.top
				+ this.parent.rect.height() / 2), Dest.get(), this.parent));
	}
}
