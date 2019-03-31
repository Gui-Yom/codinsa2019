package lyon.codinsa.virus.ai;

import java.util.Collections;
import java.util.Comparator;
import lyon.codinsa.virus.network.Action;
import lyon.codinsa.virus.network.Graph;
import lyon.codinsa.virus.network.Link;
import lyon.codinsa.virus.network.Node;

import java.util.LinkedList;

public class BasicAI extends VirusAI {

    public BasicAI(Integer playerId) {

        super(playerId);
    }

    @Override
    public LinkedList<Action> play(Graph stateUpdate) {

        LinkedList<Action> attacks = new LinkedList<>();
        LinkedList<Node> myNodes = stateUpdate.getPlayerNodes(id);
        for (Node n : myNodes) {
            LinkedList<Link> links = new LinkedList(n.getNeighbors());
            Collections.sort(links, new Comparator<Link>() {
                @Override
                public int compare(Link o1, Link o2) {
                    Integer value1 = stateUpdate.getNode(o1.id).getQtCode();
                    Integer value2 = stateUpdate.getNode(o2.id).getQtCode();
                    return value1 - value2;
                }
            });
            for (Link link : links) {
                if (stateUpdate.getNode(link.id).getOwner().equals(id)) {
                    continue;
                }
                if (n.getQtCode() > 1) {
                    Integer qt = Math.min(n.getQtCode()-1, link.debit);
                    attacks.add(new Action(n.getId(), link.id, qt));
                }
            }
        }
        return attacks;
    }

}
