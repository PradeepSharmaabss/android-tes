package Spells;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import Game.GameObject;
import SpellProjectiles.BoomerangBladeProjectile;
import SpellProjectiles.BoomerangProjectile;
import Tools.Vector;
import Tools.iVector;

/**
 * Created by Scott on 28/08/13.
 */
public class BoomerangSpell extends Spell {
    public BoomerangSpell(GameObject _parent) {
        super(_parent);
    }

    @Override
    void Shoot(iVector Dest) {
        RenderThread.addObject(new BoomerangProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
    }
    @Override
    public void DrawButton(Canvas c, int x, int y, float w, float h) {
        DrawB(c, x+w/2, y + h/2);
    }
    public void DrawB(Canvas canvas, float playerx, float playery) {
        // super.Draw(canvas, playerx, playery);
//bounds.Draw(canvas,playerx,playery,Global.PaintOutline);
//DrawBlade(canvas,bounds.Center.x-playerx,bounds.Center.y-playery,(i*15)%360);
        for(int x=0; x<5; x++)
        {

            DrawBlade(canvas,playerx,playery,(1*15+72*x)%360);
        }
        //   canvas.drawArc(new RectF(this.position.x-playerx,this.position.y-playery,100+this.position.x-playerx,100+this.position.y-playery),(i * 5)%360,(100+i*5)%360,true, Global.PaintOutline);
    }
    protected void DrawBlade(Canvas canvas , float playerx,float playery,float angle)
    {


        float t2 =30;
        canvas.drawArc(new RectF(playerx-50,playery-50,50+playerx,50+playery),angle,t2,true,Global.PaintMagenta);
        canvas.drawArc(new RectF(playerx-50,playery-50,50+playerx,50+playery),angle,t2,true, Global.PaintOutline);
    }
}
