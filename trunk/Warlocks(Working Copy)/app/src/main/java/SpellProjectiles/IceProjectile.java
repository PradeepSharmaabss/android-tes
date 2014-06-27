package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;

import com.developmental.warlocks.R;

import Game.ObjectType;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.NewHeirarchy.glParticle;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;


/**
 * Created by Scott on 7/29/13.
 */
public class IceProjectile extends Projectile {
    public IceProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.iceball,_from, _to, shooter, 100, 10, new Vector(50, 50), 6);
        this.paint.setColor(Color.BLUE);
        this.objectObjectType = ObjectType.IceSpell;
    }



    @Override
    public void Update() {
        super.Update();
        SimpleGLRenderer.addParticle(new glParticle(this.bounds.Center, Vector.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 10, R.drawable.iceball));
        SimpleGLRenderer.addParticle(new glParticle(this.bounds.Center, Vector.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 10, R.drawable.iceball));
        SimpleGLRenderer.addParticle(new glParticle(this.bounds.Center, Vector.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 10,  R.drawable.iceball));
        SimpleGLRenderer.addParticle(new glParticle(this.bounds.Center, Vector.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 10,  R.drawable.iceball));
    }

    @Override
    public void Draw(Canvas c, float playerx, float playery) {

        c.drawCircle(this.rect.centerX() - playerx, this.rect.centerY() - playery, this.size.x / 2,
                this.paint);

    }

}
