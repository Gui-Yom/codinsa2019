package lyon.codinsa.virus.ai;

import java.util.LinkedList;
import lyon.codinsa.virus.network.Action;
import lyon.codinsa.virus.network.Graph;

public abstract class VirusAI {
    public Integer id;
    
    public VirusAI(Integer playerId) {
        id = playerId;
    }
    
    public abstract LinkedList<Action> play(Graph stateUpdate);
}
