package lyon.codinsa.virus.ai;

import java.util.LinkedList;
import lyon.codinsa.virus.network.AttackCommand;
import lyon.codinsa.virus.network.Graph;

public interface VirusAI {
    public LinkedList<AttackCommand> play(Graph stateUpdate);
}
