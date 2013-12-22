package Game;

import android.graphics.Color;
import android.graphics.RectF;

import Tools.Vector;

public class Block extends GameObject {
    int i = 0;
    Vector startpos;
    public Block() {
        super();
        paint.setColor(Color.GRAY);
    }

    public Block(int x, int y) {
        this();
        position = new Vector(x, y);
        startpos=position.get();
        this.rect = new RectF(this.position.x, this.position.y, this.position.x
                + this.size.x, this.position.y + this.size.y);
        this.bounds.Center = this.getCenter();
        acceleration = 10;
    }


}
