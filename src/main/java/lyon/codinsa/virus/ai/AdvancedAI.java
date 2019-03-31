package lyon.codinsa.virus.ai;

import java.util.Collections;
import java.util.LinkedList;

import lyon.codinsa.virus.network.Action;
import lyon.codinsa.virus.network.Graph;
import lyon.codinsa.virus.network.Link;
import lyon.codinsa.virus.network.Node;

public class AdvancedAI extends VirusAI {
	
	public AdvancedAI(Integer playerId) {
		super(playerId);
	}
	

	@Override
	public LinkedList<Action> play(Graph stateUpdate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public LinkedList<Action> attack(Graph state, Node source, Integer attackCode) {
		LinkedList<Action> possibleActions = new LinkedList<>();
		for(Link link : source.getNeighbors()) {
			Node n = state.getNode(link.id);
			if (!n.getOwner().equals(id)) {
				possibleActions.add(new Action(source.getId(), n.getId(), Math.min(link.debit, attackCode), n.getProduction() / n.getQtCode()));
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
