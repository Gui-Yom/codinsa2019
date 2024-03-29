package lyon.codinsa.virus.ai;

import lyon.codinsa.virus.network.Action;
import lyon.codinsa.virus.network.Graph;

import java.util.List;

public abstract class VirusAI {

    public Integer id;

    public VirusAI(Integer playerId) {

        id = playerId;
    }

    public abstract List<Action> play(Graph stateUpdate);
}
