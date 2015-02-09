package Actors;

import com.developmental.warlocks.R;

import Spells.LoadOutInfo;
import Tools.Vector;

/**
 * Created by Scott on 30/12/13.
 */
public class BlockEnemy extends Enemy  {
    Vector basedest;
    public BlockEnemy( Vector _pos, LoadOutInfo[] s) {
        super(R.drawable.charsheetedit,s,_pos);
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
