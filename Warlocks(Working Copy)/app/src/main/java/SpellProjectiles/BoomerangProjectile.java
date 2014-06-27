package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import com.developmental.warlocks.R;

import Game.ObjectType;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.NewHeirarchy.glParticle;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

/**
 * Created by Scott on 28/08/13.
 */
public class BoomerangProjectile extends Projectile {
    public BoomerangProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.bomerang,_from, _to, shooter, 90000, 20f, new Vector(100, 100), 3);
        this.acceleration = 0.5f;
        this.objectObjectType= ObjectType.Boomerang;
        this.paint.setColor(Color.argb(255,150,190,210));
        this.knockback= 25;

    }

    @Override
    protected void setFrames() {
        FramesNoTail();
    }

    @Override
    public void Draw(Canvas canvas, float playerx, float playery) {
       // super.Draw(canvas, playerx, playery);
//bounds.Draw(canvas,playerx,playery,Global.PaintOutline);
//DrawBlade(canvas,bounds.Center.x-playerx,bounds.Center.y-playery,(i*15)%360);
        for(int x=0; x<5; x++)
        {

            DrawBlade(canvas,bounds.Center.x-playerx,bounds.Center.y-playery,(lifePhase*15+72*x)%360);
        }
        float angle = (float) Math.toDegrees((float) Math.atan2(this.velocity.y, this.velocity.x) - Math.atan2(0, 0));
        if(lifePhase%3==2)
        SimpleGLRenderer.addParticle(new glParticle(this.bounds.Center.add(this.velocity), new Vector(0, 0), 15, 0/*,angle*/));
          //   canvas.drawArc(new RectF(this.position.x-playerx,this.position.y-playery,100+this.position.x-playerx,100+this.position.y-playery),(i * 5)%360,(100+i*5)%360,true, Global.PaintOutline);
    }
   protected void DrawBlade(Canvas canvas , float playerx,float playery,float angle)
    {


        float t2 =30;
        canvas.drawArc(new RectF(playerx-bounds.Radius,playery-bounds.Radius,bounds.Radius+playerx,bounds.Radius+playery),angle,t2,true,this.paint);
        canvas.drawArc(new RectF(playerx-bounds.Radius,playery-bounds.Radius,bounds.Radius+playerx,bounds.Radius+playery),angle,t2,true, Global.PaintOutline);
    }


    @Override
    public void Update() {
        super.Update();
        if(lifePhase>15)
        if (    lifePhase% 5 == 4) {



            this.destination = owner.feet;
        }
        if (lifePhase > 50)
            if (this.bounds.CollidesWith(owner.bounds))
                SimpleGLRenderer.delObject(this.id);

    }
}
