package SpellProjectiles;

import android.graphics.Color;
import android.graphics.Paint;

import com.developmental.warlocks.R;

import javax.microedition.khronos.opengles.GL10;

import HUD.PopupText;
import Particles.FireParticle;
import Spells.Archetype.ArchetypePower;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

/**
 * Created by Scott on 27/08/13.
 */
public class ExplosionIceProjectile extends ExplosionProjectile {


    public ExplosionIceProjectile(Vector _to, Vector _s, Collideable shooter,int Rank) {

        super(R.drawable.spell_boundsircle, _to, shooter, _s, 11,Rank);

        archetypePower= new ArchetypePower(0,50,0,0,0,0,0);

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
