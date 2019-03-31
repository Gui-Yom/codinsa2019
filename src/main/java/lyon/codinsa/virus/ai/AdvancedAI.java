package lyon.codinsa.virus.ai;

import java.util.LinkedList;
import java.util.ArrayList;

import lyon.codinsa.virus.network.Action;
import lyon.codinsa.virus.network.Graph;
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
	
	
	

}
