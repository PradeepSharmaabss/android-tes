package Actors.EnemyAI;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

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
    float width;
    float height;
    ArrayList<Node> SafeNodes;

    public NavMesh(iVector _size) {
        this.size = _size;
        height = Global.WORLD_BOUND_SIZE.y / size.y;
        width = Global.WORLD_BOUND_SIZE.x / size.x;
        GenerateNodes();

    }

    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld) {
        for(int x = 0; x<size.x; x++)
        {
            for (int y = 0; y < size.y; y++) {
                Nodes[x][y].d.draw(gl, offsetX, offsetY, dontDrawInRelationToWorld);
            }

        }
    }

    private void GenerateNodes() {
        Nodes = new Node[size.x][size.y];

        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {


                Nodes[x][y] = new Node(x, y, (int) (width * x), (int) (height * y));
            }
        }
    }

    public ArrayList<Node> GetSafeNodePath(Vector closestPosition) {
        ArrayList<Node> path = new ArrayList<Node>();
        Node closestNode = Nodes[((int) (closestPosition.x / width))][((int) (closestPosition.y / height))];
        Log.e("A STAR", "BEGIN A* PATHFINDING");
        Log.e("A STAR !Closest", closestNode.indexX + ", " + closestNode.indexY);

        Node dest = SafeNodes.get(Global.GetRandomNumer.nextInt(SafeNodes.size()));
        Log.e("A STAR !Destination", dest.indexX + ", " + dest.indexY);
        //  ArrayList<AStarNode> openList = new ArrayList<AStarNode>();
        //  ArrayList<AStarNode> cclosed = new ArrayList<AStarNode>();
        TreeMap<Integer, AStarNode> oopenList = new TreeMap<Integer, AStarNode>();
        TreeMap<Integer, AStarNode> cclosedList = new TreeMap<Integer, AStarNode>();

        //Generate Surrounding Nodes
        boolean completed = false;
        AStarNode currentlybeinglookedatnode = new AStarNode(closestNode, null, dest);
        while (!currentlybeinglookedatnode.node.isEqualTo(dest) && completed == false) {


            Log.e("A STAR!", "Current:" + currentlybeinglookedatnode.node.indexX + ", " + currentlybeinglookedatnode.node.indexY);
            if (currentlybeinglookedatnode.node.indexX > 0) {
                Node left = Nodes[currentlybeinglookedatnode.node.indexX - 1][currentlybeinglookedatnode.node.indexY];

                Log.e("A STAR !Left", left.indexX + ", " + left.indexY);
                if (left.isSafe()) {
                    if (cclosedList.get(left.indexX + left.indexY * size.x) == null) {
                        AStarNode p = new AStarNode(left, currentlybeinglookedatnode, dest);
                        AStarNode o = oopenList.get(left.indexX + left.indexY * size.x);
                        if (o != null) {
                            if (o.F() < p.F())
                                oopenList.put(left.indexY * size.x + left.indexX, o);
                            else
                                oopenList.put(left.indexY * size.x + left.indexX, p);
                        } else {
                            oopenList.put(left.indexY * size.x + left.indexX, p);
                        }
                    }
                    //    openList.add(new AStarNode(left, currentlybeinglookedatnode, dest));
                } else
                    cclosedList.put(left.indexX + left.indexY * size.x, new AStarNode(left, currentlybeinglookedatnode, dest));
            }
            if (currentlybeinglookedatnode.node.indexX < Nodes.length - 1) {
                Node right = Nodes[currentlybeinglookedatnode.node.indexX + 1][currentlybeinglookedatnode.node.indexY];

                Log.e("A STAR !Right", right.indexX + ", " + right.indexY);
                if (right.isSafe()) {
                    if (cclosedList.get(right.indexX + right.indexY * size.x) == null) {
                        AStarNode p = new AStarNode(right, currentlybeinglookedatnode, dest);
                        AStarNode o = oopenList.get(right.indexX + right.indexY * size.x);
                        if (o != null) {
                            if (o.F() < p.F())
                                oopenList.put(right.indexY * size.x + right.indexX, o);
                            else
                                oopenList.put(right.indexY * size.x + right.indexX, p);
                        } else {
                            oopenList.put(right.indexY * size.x + right.indexX, p);
                        }
                    }
                    //    openList.add(new AStarNode(left, currentlybeinglookedatnode, dest));
                } else
                    cclosedList.put(right.indexX + right.indexY * size.x, new AStarNode(right, currentlybeinglookedatnode, dest));
            }

            if (currentlybeinglookedatnode.node.indexY > 0) {
                Node up = Nodes[currentlybeinglookedatnode.node.indexX][currentlybeinglookedatnode.node.indexY - 1];

                Log.e("A STAR !Up", up.indexX + ", " + up.indexY);
                if (up.isSafe()) {
                    if (cclosedList.get(up.indexX + up.indexY * size.x) == null) {
                        AStarNode p = new AStarNode(up, currentlybeinglookedatnode, dest);
                        AStarNode o = oopenList.get(up.indexX + up.indexY * size.x);
                        if (o != null) {
                            if (o.F() < p.F())
                                oopenList.put(up.indexY * size.x + up.indexX, o);
                            else
                                oopenList.put(up.indexY * size.x + up.indexX, p);
                        } else {
                            oopenList.put(up.indexY * size.x + up.indexX, p);
                        }
                    }
                    //    openList.add(new AStarNode(left, currentlybeinglookedatnode, dest));
                } else
                    cclosedList.put(up.indexX + up.indexY * size.x, new AStarNode(up, currentlybeinglookedatnode, dest));
            }
            if (currentlybeinglookedatnode.node.indexY < Nodes[0].length - 1) {

                Node down = Nodes[currentlybeinglookedatnode.node.indexX][currentlybeinglookedatnode.node.indexY + 1];

                Log.e("A STAR !Down", down.indexX + ", " + down.indexY);
                if (down.isSafe()) {
                    if (cclosedList.get(down.indexX + down.indexY * size.x) == null) {
                        AStarNode p = new AStarNode(down, currentlybeinglookedatnode, dest);
                        AStarNode o = oopenList.get(down.indexX + down.indexY * size.x);
                        if (o != null) {
                            if (o.F() < p.F())
                                oopenList.put(down.indexY * size.x + down.indexX, o);
                            else
                                oopenList.put(down.indexY * size.x + down.indexX, p);
                        } else {
                            oopenList.put(down.indexY * size.x + down.indexX, p);
                        }
                    }
                    //    openList.add(new AStarNode(left, currentlybeinglookedatnode, dest));
                } else
                    cclosedList.put(down.indexX + down.indexY * size.x, new AStarNode(down, currentlybeinglookedatnode, dest));
            }
            //   Collections.sort(openList);

            if (oopenList.size() > 0) {

                int i = oopenList.firstKey();
                currentlybeinglookedatnode = oopenList.get(i);
                oopenList.remove(i);
                cclosedList.put(i, currentlybeinglookedatnode);

            }
            if (oopenList.size() == 0) {
                completed = true;
            }
        }
        Log.e("A STAR", "Complete");
        if (completed == false) {
            while (currentlybeinglookedatnode.previousNode != null) {
                if (currentlybeinglookedatnode != null && currentlybeinglookedatnode.node != null) {

                    path.add(currentlybeinglookedatnode.node);
                    currentlybeinglookedatnode = currentlybeinglookedatnode.previousNode;
                }
            }
            Collections.reverse(path);
            Log.e("ASTAR", "CLOSEST NODE:" + closestNode.indexX + "," + closestNode.indexY);
            Log.e("ASTAR", "Destination NODE:" + dest.indexX + "," + dest.indexY);


        }

        if (completed == true)
            return null;
        return path;
    }

    public void Update() {
        SafeNodes = new ArrayList<Node>();
        for(int x = 0; x<size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                Nodes[x][y].type=Node.CheckType(Nodes[x][y].x,Nodes[x][y].y);
                switch (Nodes[x][y].type) {

                    case Platform:
                    case Ice:
                        SafeNodes.add(Nodes[x][y]);
                        break;
                    case Lava:
                        break;
                }
            }
        }
    }
}
