package developmental.warlocks;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

import developmental.warlocks.GL.NewHeirarchy.GameObject;


public class Quadtree {

    private int MAX_OBJECTS = 10;
    private int MAX_LEVELS = 5;

    private int level;
    private List objects;
    private RectF bounds;
    private Quadtree[] nodes;

    /*
     * Constructor
     */
    public Quadtree(int pLevel, RectF pBounds) {
        // Log.d("QuadTree","Level"+ pLevel+"x"+pBounds.left+" y "+pBounds.top+ " w "+ pBounds.width()  + " h "+ pBounds.height());
        level = pLevel;
        objects = new ArrayList();
        bounds = pBounds;
        nodes = new Quadtree[4];
//        if(pLevel<MAX_LEVELS)
//        split();

    }

    /*
     * Clears the quadtree
     */
    public void clear() {
        objects.clear();

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].clear();
                nodes[i] = null;
            }
        }
    }

    /*
 * Splits the node into 4 subnodes
 */
    private void split() {
        int subWidth = (int) Math.abs(bounds.width() / 2);
        int subHeight = (int) Math.abs(bounds.height() / 2);
        int x;
        x = (int) bounds.left;
        int y;
        y = (int) bounds.top;

        nodes[0] = new Quadtree(level + 1, new RectF(x + subWidth, y, x + 2 * subWidth, y + subHeight));
        nodes[1] = new Quadtree(level + 1, new RectF(x, y, x + subWidth, y + subHeight));
        nodes[2] = new Quadtree(level + 1, new RectF(x, y + subHeight, x + subWidth, y + 2 * subHeight));
        nodes[3] = new Quadtree(level + 1, new RectF(x + subWidth, y + subHeight, x + 2 * subWidth, y + 2 * subHeight));
    }

    /*
 * Determine which node the object belongs to. -1 means
 * object cannot completely fit within a child node and is part
 * of the parent node
 */
    private int getIndex(GameObject g) {
        RectF pRect = g.rect;
        int index = -1;
        double verticalMidpoint = bounds.left + (bounds.width() / 2);
        double horizontalMidpoint = bounds.top + (bounds.height() / 2);

        // Object can completely fit within the top quadrants
        boolean topQuadrant = (pRect.top < horizontalMidpoint && pRect.top + pRect.height() < horizontalMidpoint);
        // Object can completely fit within the bottom quadrants
        boolean bottomQuadrant = (pRect.top > horizontalMidpoint);
        // Log.d(pRect.left+","+pRect.top+"," + pRect.width()+","+pRect.height(),"TARGET RECT");
        // Object can completely fit within the left quadrants
        if (pRect.left < verticalMidpoint && pRect.left + pRect.width() < verticalMidpoint) {
            if (bottomQuadrant) {
                index = 2;
                //   Log.d("TOP LEFT","DDD");
            } else if (topQuadrant) {
                index = 1;

                // Log.d("BOT LEFT","DDD");
            }
        }
        // Object can completely fit within the right quadrants
        else if (pRect.left > verticalMidpoint) {
            if (topQuadrant) {
                index = 0;

                //Log.d("TOP RIGHT","DDD");
            } else if (bottomQuadrant) {
                index = 3;

                //   Log.d("BOT RIGHT","DDD");
            }
        }

        return index;
    }

    /*
 * Insert the object into the quadtree. If the node
 * exceeds the capacity, it will split and add all
 * objects to their corresponding nodes.
 */
    public void insert(GameObject g) {
        RectF pRect = g.rect;
        //  Log.d("SPLITTING1", "RECT: x = " +pRect.left + ", y = " +pRect.top+",width = " + pRect.width()+  ", height = " +pRect.height());

        if (nodes[0] != null) {
            int index = getIndex(g);
            if (index != -1) {
                nodes[index].insert(g);

                return;
            }
        }
        //Log.d("SPLITTING2", "RECT: x = " +pRect.left + ", y = " +pRect.top+",width = " + pRect.width()+  ", height = " +pRect.height());

        objects.add(g);

        if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
            if (nodes[0] == null) {
                //     Log.d("SPLITTING3", "RECT: x = " +this.bounds.left + ", y = " +this.bounds.top+",width = " + this.bounds.width()+  ", height = " +this.bounds.height());
                split();
            }

            int i = 0;
            while (i < objects.size()) {
                int index = getIndex((GameObject) objects.get(i));
                if (index != -1) {
                    nodes[index].insert((GameObject) objects.remove(i));
                } else {
                    i++;
                }
            }
        }
    }

    /*
 * Return all objects that could collide with the given object
 */
    public List retrieve(List returnObjects, GameObject g) {
        int index = getIndex(g);
        if (index != -1 && nodes[0] != null) {
            nodes[index].retrieve(returnObjects, g);
        }

        returnObjects.addAll(objects);

        return returnObjects;
    }

    public void Draw(Canvas c, float playerx, float playery) {
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.STROKE);

        c.drawRect(this.bounds.left - playerx, this.bounds.top - playery, this.bounds.right - playerx, this.bounds.bottom - playery, p);
        //   c.drawRect(new RectF(this.bounds.left,this.bounds.top,this.bounds.width(),this.bounds.height()), p);
//       c.drawLine(this.bounds.centerX() - playerx, this.bounds.top - playery, this.bounds.centerX() - playerx, this.bounds.bottom - playery, p);
//        c.drawLine(this.bounds.left-playerx,this.bounds.centerY()-playery,this.bounds.right-playerx,this.bounds.centerY()-playery,p);

        if (nodes[0] != null) {
            nodes[0].Draw(c, playerx, playery);

            nodes[1].Draw(c, playerx, playery);

            nodes[2].Draw(c, playerx, playery);

            nodes[3].Draw(c, playerx, playery);
        }
    }

}