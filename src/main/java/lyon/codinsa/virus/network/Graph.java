package lyon.codinsa.virus.network;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Graph {

    private HashMap<Integer, Node> map;


    public Node getNode(Integer id) {
        return map.get(id);
    }

    public void addNode(Node node) {
        map.put(node.getId(), node);
    }

    public HashMap<Integer, Node> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, Node> map) {
        this.map = map;
    }
    
    public LinkedList<Node> getPlayerNodes(Integer id) {
        LinkedList<Node> nodes = new LinkedList<Node>();
        for(Node n : map.values()) {
            if(n.getOwner().equals(id)) {
                nodes.add(n);
            }
        }
        return nodes;
    }
    
    public HashSet<Node> getTargetableNodes(LinkedList<Node> playerNodes) {
        HashSet<Node> nodes = new HashSet<Node>();
        for(Node node : playerNodes) {
            for(Link link : node.getNeighbors()) {
                if(!getNode(link.id).getId().equals(node.getId())) {
                    nodes.add(getNode(link.id));
                }
            }
        }
        return nodes;
    }

}
