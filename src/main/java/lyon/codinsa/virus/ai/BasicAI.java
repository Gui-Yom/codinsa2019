package lyon.codinsa.virus.ai;

import lyon.codinsa.virus.network.Action;
import lyon.codinsa.virus.network.Graph;
import lyon.codinsa.virus.network.Link;
import lyon.codinsa.virus.network.Node;

import java.util.*;

public class BasicAI extends VirusAI {

    public BasicAI(Integer playerId) {

        super(playerId);
    }

    @Override
    public List<Action> play(Graph stateUpdate) {

        List<Action> attacks = new ArrayList<>();
        LinkedList<Node> myNodes = stateUpdate.getPlayerNodes(id);
        for (Node n : myNodes) {
            boolean surroundedByFriendlyNodes = true;
            LinkedList<Link> links = new LinkedList(n.getNeighbors());
            Collections.sort(links, new Comparator<Link>() {
                @Override
                public int compare(Link o1, Link o2) {

                    Integer value1 = stateUpdate.getNode(o1.id).getQtCode();
                    Integer value2 = stateUpdate.getNode(o2.id).getQtCode();
                    if (stateUpdate.getNode(o1.id).getIsServer()) {
                        value2 += 1000;
                    }
                    if (stateUpdate.getNode(o2.id).getIsServer()) {
                        value1 += 1000;
                    }
                    return value1 - value2;
                }
            });
            for (Link link : links) {
                if (stateUpdate.getNode(link.id).getOwner().equals(id)) {
                    continue;
                }
                surroundedByFriendlyNodes = false;
                if (n.getQtCode() > 1) {
                    Integer qt = Math.min(n.getQtCode() - 1, link.debit);
                    attacks.add(new Action(n.getId(), link.id, qt));
                }
            }
            if (surroundedByFriendlyNodes) {
                for (Link link : links) {
                    if (stateUpdate.bfsDirections.get(n.getId()).equals(link.id)) {
                        if (n.getQtCode() > 1) {
                            Integer qt = Math.min(n.getQtCode() - 1, link.debit);
                            attacks.add(new Action(n.getId(), link.id, qt));
                        }
                        break;
                    }
                }
            }
        }
        return attacks;
    }

}
