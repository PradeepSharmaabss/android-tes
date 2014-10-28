package Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.Renderable;
import developmental.warlocks.Global;

/**
 * Class for Storing and drawing a destination vector, like when the player is tapping around the map
 * Created by Scott on 5/30/13.
 */
public class Destination extends Renderable {



    protected Paint paint = new Paint();



    public Destination(Vector v) {
        super(R.drawable.effect_particle);
        this.position= v.get();
        this.mGrid= Global.spellSpritesMeteor;
    }

    @Override
    public void Update() {
        super.Update();
    }





}
