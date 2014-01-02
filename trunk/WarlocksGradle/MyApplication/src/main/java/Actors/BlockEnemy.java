package Actors;

import android.graphics.Bitmap;

import java.util.ArrayList;

import Tools.Vector;

/**
 * Created by Scott on 30/12/13.
 */
public class BlockEnemy extends Enemy  {
    Vector basedest;
    public BlockEnemy(ArrayList<Bitmap> _spriteSheet, Vector _pos) {
        super(_spriteSheet, _pos);
        basedest = _pos.get();
        howOftenMovesOccur = 4;
        howOftenAttacksOccur = 10000;
    }

    @Override
    protected void AIMoveUpdate() {
//        super.AIMoveUpdate();
        this.destination = basedest;
    }
}
