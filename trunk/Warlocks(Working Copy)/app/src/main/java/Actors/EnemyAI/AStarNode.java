package Actors.EnemyAI;

/**
 * Created by Scott on 6/02/2015.
 */
public class AStarNode implements Comparable<AStarNode> {
    public Node node;
    public AStarNode previousNode;
    public float Heuristic;
    public float MovementCost;

    public float F() {
        return Heuristic + MovementCost;
    }

    public AStarNode(Node _n, AStarNode _p, Node ParentLocation) {
        node = _n;
        previousNode = _p;
        CalcHeuristic(node, ParentLocation);
        CalcMovementCost(node);
    }

    private void CalcMovementCost(Node node) {
        int i = 0;
        if (previousNode != null)
            i += previousNode.MovementCost;
        i += 1;
        MovementCost = i;
    }

    private void CalcHeuristic(Node start, Node finish) {
        Heuristic = Math.abs(start.indexX - finish.indexX) + Math.abs(start.indexY - finish.indexY);
    }

    @Override
    public int compareTo(AStarNode another) {
        return Float.compare(another.F(), F());

    }
}
