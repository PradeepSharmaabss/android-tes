package com.example.warlockgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
        level = pLevel;
        objects = new ArrayList();
        bounds = pBounds;
        nodes = new Quadtree[4];
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
        int subWidth = (int)(bounds.width() / 2);
        int subHeight = (int)(bounds.height() / 2);
        int x;
        x = (int)bounds.left;
        int y;
        y = (int)bounds.top;

        nodes[0] = new Quadtree(level+1, new RectF(x + subWidth, y, subWidth, subHeight));
        nodes[1] = new Quadtree(level+1, new RectF(x, y, subWidth, subHeight));
        nodes[2] = new Quadtree(level+1, new RectF(x, y + subHeight, subWidth, subHeight));
        nodes[3] = new Quadtree(level+1, new RectF(x + subWidth, y + subHeight, subWidth, subHeight));
    }
    /*
 * Determine which node the object belongs to. -1 means
 * object cannot completely fit within a child node and is part
 * of the parent node
 */
    private int getIndex(RectF pRect) {
        int index = -1;
        double verticalMidpoint = bounds.left + (bounds.width() / 2);
        double horizontalMidpoint = bounds.top + (bounds.height() / 2);

        // Object can completely fit within the top quadrants
        boolean topQuadrant = (pRect.top < horizontalMidpoint && pRect.left + pRect.height() < horizontalMidpoint);
        // Object can completely fit within the bottom quadrants
        boolean bottomQuadrant = (pRect.top > horizontalMidpoint);

        // Object can completely fit within the left quadrants
        if (pRect.left < verticalMidpoint && pRect.left + pRect.width() < verticalMidpoint) {
            if (topQuadrant) {
                index = 1;
            }
            else if (bottomQuadrant) {
                index = 2;
            }
        }
        // Object can completely fit within the right quadrants
        else if (pRect.left > verticalMidpoint) {
            if (topQuadrant) {
                index = 0;
            }
            else if (bottomQuadrant) {
                index = 3;
            }
        }

        return index;
    }
    /*
 * Insert the object into the quadtree. If the node
 * exceeds the capacity, it will split and add all
 * objects to their corresponding nodes.
 */
    public void insert(RectF pRect) {
        if (nodes[0] != null) {
            int index = getIndex(pRect);
            if (index != -1) {
                nodes[index].insert(pRect);

                return;
            }
        }

        objects.add(pRect);

        if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
            if (nodes[0] == null) {
                split();
            }

            int i = 0;
            while (i < objects.size()) {
                int index = getIndex((RectF)objects.get(i));
                if (index != -1) {
                    nodes[index].insert((RectF)objects.remove(i));
                }
                else {
                    i++;
                }
            }
        }
    }
    /*
 * Return all objects that could collide with the given object
 */
    public List retrieve(List returnObjects, RectF pRect) {
        int index = getIndex(pRect);
        if (index != -1 && nodes[0] != null) {
            nodes[index].retrieve(returnObjects, pRect);
        }

        returnObjects.addAll(objects);

        return returnObjects;
    }
    void Draw(Canvas c,float playerx,float playery)
    {
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        c.drawRect(new RectF(this.bounds.left-playerx,this.bounds.top-playery,this.bounds.right-playerx,this.bounds.bottom-playery), p);
        c.drawLine(this.bounds.centerX() - playerx, this.bounds.top - playery, this.bounds.centerX() - playerx, this.bounds.bottom - playery, p);
        c.drawLine(this.bounds.left-playerx,this.bounds.centerY()-playery,this.bounds.right-playerx,this.bounds.centerY()-playery,p);

        if(nodes[0]!=null)
        {
            nodes[0].Draw(c,playerx,playery);
        }
        if(nodes[1]!=null)
        {
            nodes[1].Draw(c,playerx,playery);
        }
        if(nodes[2]!=null)
        {
            nodes[2].Draw(c,playerx,playery);
        }
        if(nodes[3]!=null)
        {
            nodes[3].Draw(c,playerx,playery);
        }
    }

}