package Input;

import java.io.Serializable;

import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.NewHeirarchy.glButton;
import developmental.warlocks.GL.SimpleGLRenderer;

public class Pointer implements Serializable {
    public Vector startPos = new Vector(0, 0);

    public Pointer() {
        this.position = new iVector(0, 0);
        this.down = false;
    }

    public iVector position;
    public boolean down;

    public void Update() {
        this.position = new iVector(0, 0);
        this.down = false;
    }

    public void Update2(iVector pos) {
        this.position = pos.get();
        this.down = true;
    }

    public boolean WithinScreen() {
        if (this.position != null) {
            boolean b = false;
            for (glButton g : SimpleGLRenderer.buttons) {
                if (g.Contains(this)) {
                    b = true;
                }
            }
          if(!b)
                return true;
        }
        return false;
    }

    public Vector WorldPos(Vector vector) {
        return new Vector(this.position.x + vector.x
                - SimpleGLRenderer.size.x / 2, this.position.y
                + vector.y - SimpleGLRenderer.size.y / 2);
    }

    public iVector iWorldPos(Vector v) {
        Vector t = WorldPos(v);
        return new iVector(((int)t.x), ((int) t.y));
    }
}
