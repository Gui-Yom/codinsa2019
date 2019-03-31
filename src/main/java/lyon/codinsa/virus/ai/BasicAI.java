package lyon.codinsa.virus.ai;

import lyon.codinsa.virus.network.Action;
import lyon.codinsa.virus.network.Graph;
import lyon.codinsa.virus.network.Link;
import lyon.codinsa.virus.network.Node;

import java.util.ArrayList;
import java.util.Collections;
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
            LinkedList<Link> links = new LinkedList(n.getNeighbors());
            Collections.sort(links, (o1, o2) -> {
                Integer value1 = stateUpdate.getNode(o1.id).getQtCode();
                Integer value2 = stateUpdate.getNode(o2.id).getQtCode();
                return value1 - value2;
            });
            for (Link link : links) {
                if (stateUpdate.getNode(link.id).getOwner().equals(id)) {
                    continue;
                }
                if (n.getQtCode() > 1) {
                    Integer qt = Math.min(n.getQtCode() - 1, link.debit);
                    attacks.add(new Action(n.getId(), link.id, qt));
                }
            }
        }
        return attacks;
    }

}
