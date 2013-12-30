package Actors;

import android.graphics.Bitmap;

import java.util.ArrayList;

import Tools.Vector;

/**
 * Created by Scott on 30/12/13.
 */
public class BlockEnemy extends Enemy  {
    public BlockEnemy(ArrayList<Bitmap> _spriteSheet, Vector _pos) {
        super(_spriteSheet, _pos);
    }

    @Override
    protected void AIMoveUpdate() {
//        super.AIMoveUpdate();
    }
}
