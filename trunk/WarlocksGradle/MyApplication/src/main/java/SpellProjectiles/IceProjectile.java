package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;

import Game.GameObject;
import Game.ObjectType;
import Game.SpellEffect;
import Tools.Vector;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by Scott on 7/29/13.
 */
public class IceProjectile extends Projectile{
    public IceProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(_from, _to, shooter);
        this.paint.setColor(Color.BLUE);
        this.objectObjectType = ObjectType.IceSpell;
        this.size=new Vector(15,15);
    }
    @Override
    public void Collision(GameObject obj) {
        obj.Debuffs.add(new SpellEffect(100, SpellEffect.EffectType.Freeze, Global.Sprites.get(3)));
        RenderThread.delObject(this.id);

    }
    @Override
    public void Draw(Canvas c,float playerx,float playery) {

        c.drawCircle(this.rect.centerX()-playerx, this.rect.centerY()-playery, this.size.x / 2,
                this.paint);

    }

}
