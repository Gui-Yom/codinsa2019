package lyon.codinsa.virus.ai;

import java.util.LinkedList;
import lyon.codinsa.virus.network.Action;
import lyon.codinsa.virus.network.AttackCommand;
import lyon.codinsa.virus.network.Graph;
import lyon.codinsa.virus.network.Link;
import lyon.codinsa.virus.network.Node;

public class BasicAI extends VirusAI {
    
    public BasicAI(Integer playerId) {
        super(playerId);
    }

    @Override
    public LinkedList<Action> play(Graph stateUpdate) {
        LinkedList<Action> attacks = new LinkedList<>();
        LinkedList<Node> myNodes = stateUpdate.getPlayerNodes(id);
        for(Node n : myNodes) {
            for(Link link : n.getNeighbors()) {
                if(n.getQtCode() > 0) {
                    Integer qt = Math.min(n.getQtCode(), link.debit);
                    if(n.getQtCode() - qt > 0)
                        attacks.add(new Action(n.getId(), link.id, qt));
                }
            }
        }
        return attacks;
    }
    
}
