package lyon.codinsa.virus.network;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Graph {

    private HashMap<Integer, Node> map = new HashMap<>();
    public static HashMap<Integer, Integer> bfsDirections = new HashMap<Integer, Integer>();

    public Graph(Board board, Visible visible) {

        for (Node node : board.object.plateau) {
            node.setVisible(false);
            if (node.getOwner() == null) {
                node.setOwner(-1);
            }
            map.put(node.getId(), node);
        }
        for (Node node : visible.object.visible) {
            map.get(node.getId()).setVisible(true);
            if (node.getOwner() != null) {
                map.get(node.getId()).setOwner(node.getOwner());
            }
            map.get(node.getId()).setQtCode(node.getQtCode());
            map.get(node.getId()).setNeighbors(node.getNeighbors());
        }
    }
    
    public void bfs(Integer start) {
        HashMap<Integer, Integer> visited = new HashMap<Integer, Integer>();
        for(Map.Entry<Integer, Node> entry : map.entrySet()) {
            visited.put(entry.getKey(), 0);
            bfsDirections.put(entry.getKey(), 0);
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.push(start);
        while(!queue.isEmpty()) {
            Integer nodeId = queue.pop();
            visited.replace(nodeId, 1);
            for(Link next : map.get(nodeId).getNeighbors()) {
                bfsDirections.replace(nodeId, next.id);
                if(visited.get(next.id).equals(0)) {
                    queue.push(next.id);
                }
            }
        }
    }

    public String toString() {

        StringBuilder ans = new StringBuilder();
        for (Node n : map.values()) {
            ans.append(map.get(n.getId()).toString()).append("\n");
        }
        return ans.toString();
    }


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

        LinkedList<Node> nodes = new LinkedList<>();
        for (Node n : map.values()) {
            if (n.getOwner() != null && n.getOwner().equals(id)) {
                nodes.add(n);
            }
        }
        return nodes;
    }

    public HashSet<Node> getTargetableNodes(LinkedList<Node> playerNodes) {

        HashSet<Node> nodes = new HashSet<>();
        for (Node node : playerNodes) {
            for (Link link : node.getNeighbors()) {
                if (!getNode(link.id).getId().equals(node.getId())) {
                    nodes.add(getNode(link.id));
                }
            }
        }
        return nodes;
    }

}
