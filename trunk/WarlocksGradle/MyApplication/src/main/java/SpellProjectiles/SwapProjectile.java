package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;

import com.developmental.myapplication.GL.NewHeirachy.GameObject;
import Game.ObjectType;
import Tools.Vector;

import com.developmental.myapplication.R;
import com.developmental.myapplication.GL.SimpleGLRenderer;

/**
 * Created by Scott on 7/29/13.
 */
public class SwapProjectile extends Projectile {
    public SwapProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.boundscircle,_from, _to, shooter, 100, 15f, new Vector(50, 50), 0);
        this.paint.setColor(Color.GREEN);
        this.objectObjectType = ObjectType.SwapProjectile;
    }

    public void Swap(GameObject obj) {
        Vector l;
        l = obj.position;
        obj.position = this.owner.position;
        this.owner.position = l;
        SimpleGLRenderer.delObject(this.id);
    }

    @Override
    public void Draw(Canvas c, float playerx, float playery) {

        c.drawCircle(this.rect.centerX() - playerx, this.rect.centerY() - playery, this.size.x / 2,
                this.paint);

    }

}
