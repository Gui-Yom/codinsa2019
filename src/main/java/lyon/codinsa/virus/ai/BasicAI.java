package lyon.codinsa.virus.ai;

import lyon.codinsa.virus.network.Action;
import lyon.codinsa.virus.network.Graph;
import lyon.codinsa.virus.network.Link;
import lyon.codinsa.virus.network.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BasicAI extends VirusAI {

    public BasicAI(Integer playerId) {

        super(playerId);
    }

    @Override
    public List<Action> play(Graph stateUpdate) {

        List<Action> attacks = new ArrayList<>();
        LinkedList<Node> myNodes = stateUpdate.getPlayerNodes(id);
        for (Node n : myNodes) {
            for (Link link : n.getNeighbors()) {
                if (n.getQtCode() > 0) {
                    Integer qt = Math.min(n.getQtCode(), link.debit);
                    if (n.getQtCode() - qt > 0)
                        attacks.add(new Action(n.getId(), link.id, qt));
                }
            }
        }
        return attacks;
    }

}
