import java.util.*;

public class Mysolver extends Solver{

    public void solve()
    {
        Node initNode = new Node (getCurrentState(),null,null,0);
        Queue<Node> frontier = new Queue<Node>();
        frontier.add(initNode);
        ArrayList<Node> explored = new ArrayList<Node>();
        Node current = initNode;

        while(!frontier.isEmpty())
        {
            current = frontier.poll();
            explored.add(current);
            if(current.equals(isGoalState()))
            {
                break;
            }
            else{
                ArrayList<Action> moves = getAvailableMoves();
                for(int i = 0;i < (moves.size()); i++)
                {
                    Node child = new Node(getNextState(current.getState(), moves.get(i)), current, moves.get(i), (current.pathCost + 1));
                    if(!frontier.contains(child) && !explored.contains(child))
                    {
                        if(isGoalState(child.state)) frontier.add(child);
                    }
                    break;
                }
            }
        }
        for(Node now: frontier)
            {
                Action a = now.getAction();
                makeAction(a);
            }
    }

}
