package lyon.codinsa.virus.ai;

import java.util.LinkedList;
import lyon.codinsa.virus.network.AttackCommand;
import lyon.codinsa.virus.network.Graph;
import lyon.codinsa.virus.network.Link;
import lyon.codinsa.virus.network.Node;

public class BasicAI implements VirusAI {
    public Integer id;
    
    public BasicAI(Integer playerId) {
        id = playerId;
    }

    @Override
    public LinkedList<AttackCommand> play(Graph stateUpdate) {
        LinkedList<AttackCommand> attacks = new LinkedList<AttackCommand>();
        LinkedList<Node> myNodes = stateUpdate.getPlayerNodes(id);
        for(Node n : myNodes) {
            for(Link link : n.getNeighbors()) {
                
            }
        }
        return attacks;
    }
    
}
