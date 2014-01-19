package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import com.developmental.myapplication.GL.NewHeirachy.GameObject;
import Game.ObjectType;
import HUD.PopupText;
import Particles.Particle;
import Tools.Vector;

/**
 * Created by Scott on 27/08/13.
 */
public class ExplosionProjectile extends Projectile {
    Paint Chunks = new Paint();

    public ExplosionProjectile(Vector _to, Vector _s, GameObject shooter) {
        super(_to, _to, shooter, 1, 0, _s, 111);
        Chunks.setColor(Color.YELLOW);
        this.objectObjectType = ObjectType.Explosion;
        this.position.x-=bounds.Radius;
        this.position.y-=bounds.Radius;
        this.knockback= 30;
        this.bounds.Center = position;
        this.velocity= new Vector(0,0);
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.paint));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.Chunks));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.Chunks));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.paint));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.Chunks));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.paint));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.Chunks));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.paint));
        if(Global.DEBUG_MODE)
        {
            RenderThread.addParticle(new Particle(_to,new Vector(0,0), 20, this.paint));
            RenderThread.popupTexts.add(new PopupText(PopupText.TextType.Poison, "Explosion Created at " + bounds.Center.x + " , " + bounds.Center.y, RenderThread.archie.position, 100));
        }
     }

    @Override
    public void Draw(Canvas canvas, float playerx, float playery) {

    }
}
