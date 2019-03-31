package lyon.codinsa.virus.ai;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ArrayList;

import lyon.codinsa.virus.network.*;

public class AdvancedAI extends VirusAI {
	
	public AdvancedAI(Integer playerId) {
		super(playerId);
	}
	

	@Override
	public LinkedList<Action> play(Graph stateUpdate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public LinkedList<Action> getTransfers(ArrayList<Node> borders, Graph stateUpdate)
	{
		LinkedList<Action> possibleActions = new LinkedList<>();
		HashMap<Node,Link> distance = new HashMap<Node, Link>(); 
		for(Node node : borders)
		{
			BFS(distance, node, stateUpdate);
		}
		for(Node node : stateUpdate.getPlayerNodes(this.id))
		{
			if(!borders.contains(node))
			{
				possibleActions.add(new Action(node.getId(), distance.get(node).id, node.getQtCode()-1));
			}
		}
		return possibleActions;
	}
	
	public void BFS(HashMap<Node,Link> distance, Node node, Graph stateUpdate) {
		LinkedList<Node> toVisit = new LinkedList<Node>();
		HashMap<Node, Integer> currentDistance = new HashMap<Node, Integer>();
		toVisit.add(node);
		currentDistance.put(node, 0);
		HashSet<Node> visited = new HashSet<Node>();
		while(!visited.isEmpty())
		{
			Node src = toVisit.get(0);
			for(Link l: src.getNeighbors())
			{
				Node n = stateUpdate.getNode(l.id);
				if (distance.get(n) == null || (!visited.contains(n) && distance.get(n).debit > currentDistance.get(src)+1)) {
					toVisit.add(n);
					visited.add(n);
					distance.put(n,new Link(src.getId(),currentDistance.get(src)+1));
					currentDistance.put(n,currentDistance.get(src)+1);
				}
			}
			toVisit.remove(0);
		}
	}
	
	public ArrayList<Node> getBorders(Graph stateUpdate){
		ArrayList<Node> borders = new ArrayList<Node>();
		for(Integer nodeId : stateUpdate.getMap().keySet())
		{
			Node node = stateUpdate.getNode(nodeId);
			if(node.isBorder(stateUpdate))
			{
				borders.add(node);
			}
		}
		return borders;
	}
	
	
	
	public LinkedList<Action> attack(Graph state, Node source, Integer attackCode) {
		LinkedList<Action> possibleActions = new LinkedList<>();
		for(Link link : source.getNeighbors()) {
			Node n = state.getNode(link.id);
			if (!n.getOwner().equals(id)) {
				possibleActions.add(new Action(source.getId(), n.getId(), Math.min(link.debit, attackCode), (double) n.getProduction() / n.getQtCode()));
			}
		}
		Collections.sort(possibleActions, Collections.reverseOrder());
		int remainingCode = attackCode;
		LinkedList<Action> res = new LinkedList<>();
		for(Action a : possibleActions) {
			if(a.qtCode <= remainingCode) {
				res.add(a);
				remainingCode -= a.qtCode;
			}
			else {
				a.qtCode = remainingCode;
				res.add(a);
				break;
			}
		}
		return res;
	}
}
