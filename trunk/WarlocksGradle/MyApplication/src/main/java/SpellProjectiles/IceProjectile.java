package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;

import com.developmental.myapplication.GL.NewHeirachy.GameObject;
import Game.ObjectType;
import Particles.IceParticle;
import Tools.Vector;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.R;
import com.developmental.myapplication.GL.SimpleGLRenderer;

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
        SimpleGLRenderer.addParticle(new IceParticle(this.bounds.Center, Vector.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 10, this.paint));
        SimpleGLRenderer.addParticle(new IceParticle(this.bounds.Center, Vector.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 10, this.paint));
        SimpleGLRenderer.addParticle(new IceParticle(this.bounds.Center, Vector.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 10, this.paint));
        SimpleGLRenderer.addParticle(new IceParticle(this.bounds.Center, Vector.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 10, this.paint));
    }

    @Override
    public void Draw(Canvas c, float playerx, float playery) {

        c.drawCircle(this.rect.centerX() - playerx, this.rect.centerY() - playery, this.size.x / 2,
                this.paint);

    }

}
