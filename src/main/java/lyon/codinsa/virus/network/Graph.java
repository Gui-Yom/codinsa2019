package lyon.codinsa.virus.network;

import java.util.HashMap;

public class Graph {
	private HashMap<Integer,Node> map;
	
	
	public Node getNode(Integer id) {
		return map.get(id);
	}
	
	public void addNode(Node node) {
		map.put(node.getId(),node);
	}
	
	public HashMap<Integer, Node> getMap() {
		return map;
	}

	public void setMap(HashMap<Integer, Node> map) {
		this.map = map;
	}
	
	
	
	
	

}
