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


    public ExplosionProjectile(int resourceID, Vector _to, Collideable shooter, Vector _s,float _dmg,int Rank) {
        super(resourceID,_to, _to, shooter,Rank);
        this.damagevalue= _dmg;
        this.size= _s;

        this.objectObjectType = ObjectType.Explosion;
        this.position.x-=bounds.Radius;
        this.position.y-=bounds.Radius;
        this.knockback= 30;
        this.bounds.Center = position;
        this.velocity= new Vector(0,0);
        for(int i = 0; i<8; i++)
        {
            SimpleGLRenderer.addParticle(new FireParticle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, R.drawable.spell_fireball));

        }
        if(Global.DEBUG_MODE)
        {
//            SimpleGLRenderer.addParticle(new Particle(_to,new Vector(0,0), 20, this.paint));
            SimpleGLRenderer.popupTexts.add(new PopupText(PopupText.TextType.Poison, "Explosion Created at " + bounds.Center.x + " , " + bounds.Center.y, SimpleGLRenderer.archie.position, 100));
        }
     }

    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld) {
        //super.draw(gl,offsetX,offsetY,dontDrawInRelationToWorld);
    }

    @Override
    protected void setFrames() {
        this.framecount = 1;
        FramesNoTail();
    }

}
