package lyon.codinsa.virus.ai;

import java.util.HashSet;
import java.util.LinkedList;
import lyon.codinsa.virus.network.AttackCommand;
import lyon.codinsa.virus.network.Graph;
import lyon.codinsa.virus.network.Node;

public class BasicAI implements VirusAI {
    public Integer id;

    @Override
    public LinkedList<AttackCommand> play(Graph stateUpdate) {
        LinkedList<Node> myNodes = stateUpdate.getPlayerNodes(Integer.SIZE);
        HashSet<Node> targets = stateUpdate.getTargetableNodes(myNodes);
        
        return null;
    }
    
}
