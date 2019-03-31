package lyon.codinsa.virus.ai;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import lyon.codinsa.virus.network.*;

public class AdvancedAI extends VirusAI {
	
	public AdvancedAI(Integer playerId) {
		super(playerId);
	}
	

	@Override
	public List<Action> play(Graph stateUpdate) {
		List<Action> attacks = new ArrayList<>();
		ArrayList<Node> borders = getBorders(stateUpdate);
		for(Node n : borders) {
			attacks.addAll(attack(stateUpdate, n, n.getQtCode()-1));
		}
		attacks.addAll(getTransfers(borders,stateUpdate));
		return attacks;
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
		while(!toVisit.isEmpty())
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
		for(Node node : stateUpdate.getPlayerNodes(this.id))
		{
                    if(node.isBorder(stateUpdate))
                    {
                        borders.add(node);
                    }
		}
		return borders;
	}
	
	
	
	public LinkedList<Action> attack(Graph state, Node source, Integer attackCode) {
		LinkedList<Action> possibleActions = new LinkedList<>();
		LinkedList<Action> res = new LinkedList<>();
                int z = 0;
		for(Link link : source.getNeighbors()) {
			Node n = state.getNode(link.id);
			if (!n.getOwner().equals(id)) {
                            if (n.getIsServer() && n.getQtCode() < attackCode) {
                                possibleActions.add(new Action(source.getId(), n.getId(), Math.min(link.debit, attackCode), (double) n.getProduction() / 0.001));
                                z+= attackCode;
                            }else {
                                possibleActions.add(new Action(source.getId(), n.getId(), Math.min(link.debit, n.getQtCode() + 1), (double) n.getProduction() / (n.getQtCode()+0.001)));
                                z += n.getQtCode()+1;
                            }
                        }      
                }
		Collections.sort(possibleActions, Collections.reverseOrder());
		int remainingCode = attackCode;
                if(z < remainingCode) {
                    int send = Math.floorDiv(remainingCode, source.getNeighbors().size());
                    for(Link link : source.getNeighbors()) {
                        res.add(new Action(source.getId(), link.id, Math.min(link.debit, send)));
                    }
                }
		for(Action a : possibleActions) {
			if(a.qtCode <= remainingCode) {
                            res.add(a);
                            remainingCode -= a.qtCode;
			}
                        else if (a == possibleActions.getLast() && a.qtCode-1 < remainingCode) {
                            a.qtCode = remainingCode;
                            res.add(a);
                        }
                        else if (a.qtCode < remainingCode) {
                            a.qtCode = a.qtCode;
                            res.add(a);
                        }
			else {
                            break;
			}
		}
		return res;
	}
}
