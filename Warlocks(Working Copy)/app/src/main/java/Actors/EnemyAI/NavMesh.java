package Actors.EnemyAI;

import javax.microedition.khronos.opengles.GL10;

import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.Global;

/**
 * Created by Scott on 24/10/2014.
 */
public class NavMesh {
    iVector size;
    Node[][] Nodes;
    public NavMesh(iVector _size)
    {
        this.size=  _size;
        GenerateNodes();

    }

//    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld){
//        for(int x = 0; x<size.x; x++)
//        {
//            for(int y = 0; y<size.y; y++) {
//                Nodes[x][y].d.draw(gl,offsetX,offsetY,dontDrawInRelationToWorld);
//            }
//
//       }
//    }
    private void GenerateNodes() {
        Nodes = new Node[size.x][size.y];
        float width = Global.WORLD_BOUND_SIZE.x/size.x;
        float height = Global.WORLD_BOUND_SIZE.y/size.y;
        for(int x = 0; x<size.x; x++)
        {
            for(int y = 0; y<size.y; y++)
            {
                Nodes[x][y] = new Node((int)(width*x),(int)(height*y));
            }
        }
    }

    public void Update() {
        for(int x = 0; x<size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                Nodes[x][y].type=Node.CheckType(Nodes[x][y].x,Nodes[x][y].y);
            }
        }
    }
}
