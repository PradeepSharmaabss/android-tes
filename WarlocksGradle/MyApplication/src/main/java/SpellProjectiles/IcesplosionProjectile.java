package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import Game.GameObject;
import Game.ObjectType;
import HUD.PopupText;
import Particles.Particle;
import Tools.Vector;

/**
 * Created by Scott on 27/08/13.
 */
public class IcesplosionProjectile extends ExplosionProjectile {
    Paint Chunks = new Paint();

    public IcesplosionProjectile(Vector _to, Vector _s, GameObject shooter) {
        super(_to, _s, shooter);
        Chunks.setColor(Color.CYAN);
        this.paint.setColor(Color.WHITE);
        this.objectObjectType = ObjectType.IceSpell;

    }

}
