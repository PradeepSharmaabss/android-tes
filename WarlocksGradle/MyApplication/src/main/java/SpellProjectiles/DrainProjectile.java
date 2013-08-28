package SpellProjectiles;

import android.util.Log;

import com.developmental.myapplication.RenderThread;

import Actors.Player;
import Game.GameObject;
import Tools.Vector;

/**
 * Created by Scott on 28/08/13.
 */
public class DrainProjectile extends Projectile {

    public DrainProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(_from, _to, shooter);
    }
    int phase = 0;
    @Override
    public void Update() {
         super.Update();
        if(phase++==50 )
        {
            health=50;
            float td = 10000;
            Player target=null;
            for(Player p: RenderThread.players)
            {
                if(p.id!=owner.id)
                {
                    float distanceX = this.position.x - p.position.x;
                    float distanceY = this.position.y -p.position.y;
                    float totalDist = Math.abs(distanceX) + Math.abs(distanceY);
                    if(totalDist<td)
                    {
                        td = totalDist;
                        target=p;
                        Log.d("INET", "TARGET SET");
                    }
                }
                if(target!=null)
                this.velocity = GetVel(this.position, target.position);
            }
        }
    }
}
