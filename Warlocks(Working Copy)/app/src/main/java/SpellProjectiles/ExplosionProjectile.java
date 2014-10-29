package SpellProjectiles;

import android.graphics.Color;
import android.graphics.Paint;

import com.developmental.warlocks.R;

import javax.microedition.khronos.opengles.GL10;

import HUD.PopupText;
import Tools.Vector;
import Particles.FireParticle;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

/**
 * Created by Scott on 27/08/13.
 */
public class ExplosionProjectile extends Projectile {
    Paint Chunks = new Paint();

    public ExplosionProjectile(Vector _to, Vector _s, Collideable shooter) {
        super(R.drawable.spell_boundsircle,_to, _to, shooter, 1, 0, _s, 111);
        Chunks.setColor(Color.YELLOW);
        this.objectObjectType = ObjectType.Explosion;
        this.position.x-=bounds.Radius;
        this.position.y-=bounds.Radius;
        this.knockback= 30;
        this.bounds.Center = position;
        this.velocity= new Vector(0,0);
        SimpleGLRenderer.addParticle(new FireParticle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, R.drawable.spell_fireball));
        SimpleGLRenderer.addParticle(new FireParticle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.spell_fireball));
        SimpleGLRenderer.addParticle(new FireParticle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.spell_fireball));
        SimpleGLRenderer.addParticle(new FireParticle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, R.drawable.spell_fireball));
        SimpleGLRenderer.addParticle(new FireParticle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.spell_fireball));
        SimpleGLRenderer.addParticle(new FireParticle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.spell_fireball));
        SimpleGLRenderer.addParticle(new FireParticle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.spell_fireball));
        SimpleGLRenderer.addParticle(new FireParticle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, R.drawable.spell_fireball));
        if(Global.DEBUG_MODE)
        {
//            SimpleGLRenderer.addParticle(new Particle(_to,new Vector(0,0), 20, this.paint));
            SimpleGLRenderer.popupTexts.add(new PopupText(PopupText.TextType.Poison, "Explosion Created at " + bounds.Center.x + " , " + bounds.Center.y, SimpleGLRenderer.archie.position, 100));
        }
     }

    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld) {

    }

    @Override
    protected void setFrames() {
        this.framecount = 1;
        FramesNoTail();
    }

}
