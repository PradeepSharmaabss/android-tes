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
        Log.e("A STAR !Closest", closestNode.indexX + ", " + closestNode.indexY);

        Node dest = SafeNodes.get(Global.GetRandomNumer.nextInt(SafeNodes.size()));
        Log.e("A STAR !Destination", dest.indexX + ", " + dest.indexY);
        ArrayList<AStarNode> openList = new ArrayList<AStarNode>();
        ArrayList<AStarNode> cclosed = new ArrayList<AStarNode>();
        TreeMap<Integer, AStarNode> oopenList = new TreeMap<Integer, AStarNode>();
        int safeList[][] = new int[Nodes.length][Nodes[0].length];
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                safeList[x][y] = 0;
            }
        }
        //Generate Surrounding Nodes
        boolean completed = false;
        AStarNode currentlybeinglookedatnode = new AStarNode(closestNode, null, dest);
        while (!currentlybeinglookedatnode.node.isEqualTo(dest) && completed == false) {
            Log.e("A STAR !CURRENT!!", currentlybeinglookedatnode.node.indexX + ", " + currentlybeinglookedatnode.node.indexY);
            if (currentlybeinglookedatnode.node.indexX > 0 && safeList[currentlybeinglookedatnode.node.indexX - 1][currentlybeinglookedatnode.node.indexY] == 0) {
                Node left = Nodes[currentlybeinglookedatnode.node.indexX - 1][currentlybeinglookedatnode.node.indexY];
                Log.e("A STAR !Right", left.indexX + ", " + left.indexY);
                if (left.isSafe()) {
                    openList.add(new AStarNode(left, currentlybeinglookedatnode, dest));
                } else {
                    safeList[closestNode.indexX - 1][closestNode.indexY] = 1;
                }
            }
            if (currentlybeinglookedatnode.node.indexX < Nodes.length - 1 && safeList[currentlybeinglookedatnode.node.indexX + 1][currentlybeinglookedatnode.node.indexY] == 0) {
                Node right = Nodes[closestNode.indexX + 1][closestNode.indexY];
                Log.e("A STAR !Left", right.indexX + ", " + right.indexY);
                if (right.isSafe())
                    openList.add(new AStarNode(right, currentlybeinglookedatnode, dest));
                else {
                    safeList[currentlybeinglookedatnode.node.indexX + 1][currentlybeinglookedatnode.node.indexY] = 1;
                }
            }

            if (currentlybeinglookedatnode.node.indexY > 0 && safeList[currentlybeinglookedatnode.node.indexX][currentlybeinglookedatnode.node.indexY - 1] == 0) {
                Node up = Nodes[currentlybeinglookedatnode.node.indexX][currentlybeinglookedatnode.node.indexY - 1];
                Log.e("A STAR !Up", up.indexX + ", " + up.indexY);
                if (up.isSafe())
                    openList.add(new AStarNode(up, currentlybeinglookedatnode, dest));
                else {
                    safeList[currentlybeinglookedatnode.node.indexX][currentlybeinglookedatnode.node.indexY - 1] = 1;
                }
            }
            if (currentlybeinglookedatnode.node.indexY > Nodes[0].length - 1 && safeList[currentlybeinglookedatnode.node.indexX][currentlybeinglookedatnode.node.indexY + 1] == 0) {

                Node down = Nodes[currentlybeinglookedatnode.node.indexX][currentlybeinglookedatnode.node.indexY + 1];
                Log.e("A STAR !Down", down.indexX + ", " + down.indexY);
                if (down.isSafe())
                    openList.add(new AStarNode(down, currentlybeinglookedatnode, dest));
                else {
                    safeList[currentlybeinglookedatnode.node.indexX][currentlybeinglookedatnode.node.indexY + 1] = 1;
                }
            }
            Collections.sort(openList);

            if (openList.size() > 0) {

                safeList[currentlybeinglookedatnode.node.indexX][currentlybeinglookedatnode.node.indexY] = 1;
                currentlybeinglookedatnode = openList.get(0);
                safeList[currentlybeinglookedatnode.node.indexX][currentlybeinglookedatnode.node.indexY] = 1;
                openList.remove(0);
            }
            if (openList.size() == 0) {
                completed = true;
            }
        }
        if (completed == false) {
            while (currentlybeinglookedatnode.previousNode != null) {
                if (currentlybeinglookedatnode != null && currentlybeinglookedatnode.node != null) {
                    path.add(currentlybeinglookedatnode.node);
                    currentlybeinglookedatnode = currentlybeinglookedatnode.previousNode;
                }
            }
            Collections.reverse(path);
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
