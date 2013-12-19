package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;

import com.developmental.myapplication.Global;

import Actors.Player;
import Game.GameObject;
import Tools.Vector;

/**
 * Created by Scott on 27/07/13.
 */
public class HomingProjectile extends FireballProjectile {
    Player target;

    public HomingProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(_from, _to, shooter);
        this.acceleration = 0.2f;
        this.paint.setColor(Color.argb(255, 150, 150, 255));

        this.damagevalue = 7;
    }

    int i = 0;

    @Override
    public void Draw(Canvas c, float playerx, float playery) {
        super.Draw(c, playerx, playery);
        if (Global.DEBUG_MODE) {
            if (destination != null) {
                c.drawLine(this.rect.centerX() - playerx, this.rect.centerY() - playery, destination.x - playerx, destination.y - playery, Global.PaintBlack);
            }
        }
    }

    @Override
    public void Update() {
        super.Update();

        if (i++ % 15 == 0) {
            float td = 10000f;

            Player p = this.FindClosestPlayer(td);
            if(p !=null)
            {
                target=p;
            this.destination = target.feet;
            }
        }
    }
}
