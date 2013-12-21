package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.developmental.myapplication.Global;

import Game.GameObject;
import Tools.Vector;

/**
 * Created by Scott on 21/12/13.
 */
public class BoomerangBladeProjectile extends BoomerangProjectile {


    public BoomerangBladeProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(_from, _to, shooter);
    }
    @Override
    protected void DrawBlade(Canvas canvas, float playerx, float playery, float angle) {

        float centerx = (float) (playerx+bounds.Radius*Math.cos(Math.toRadians(angle)));
        float centery = (float) (playery + bounds.Radius*Math.sin(Math.toRadians(angle)));
        float t2 =30;
        //canvas.drawRect(new RectF(centerx-bounds.Radius/2,centery-bounds.Radius/2,bounds.Radius/2+centerx,bounds.Radius/2+centery),Global.PaintCyan);
        canvas.drawArc(new RectF(centerx-bounds.Radius,centery-bounds.Radius,bounds.Radius+centerx,bounds.Radius+centery),(angle+180)%360,t2,true,this.paint);
        canvas.drawArc(new RectF(centerx-bounds.Radius,centery-bounds.Radius,bounds.Radius+centerx,bounds.Radius+centery),(angle+180)%360,t2,true,Global.PaintOutline);

    }

    @Override
    public void Draw(Canvas canvas, float playerx, float playery) {
        // super.Draw(canvas, playerx, playery);
//bounds.Draw(canvas,playerx,playery,Global.PaintOutline);
        canvas.drawCircle(bounds.Center.x-playerx,bounds.Center.y-playery,bounds.Radius/2+5,this.paint);

        canvas.drawCircle(bounds.Center.x-playerx,bounds.Center.y-playery,bounds.Radius/2+5,Global.PaintOutline);
        for(int x=0; x<6; x++)
        {

            DrawBlade(canvas,bounds.Center.x-playerx,bounds.Center.y-playery,(lifePhase*15+60*x)%360);
        }

       // bounds.Draw(canvas,playerx,playery,Global.PaintOutline);
        //   canvas.drawArc(new RectF(this.position.x-playerx,this.position.y-playery,100+this.position.x-playerx,100+this.position.y-playery),(i * 5)%360,(100+i*5)%360,true, Global.PaintOutline);
    }
}
