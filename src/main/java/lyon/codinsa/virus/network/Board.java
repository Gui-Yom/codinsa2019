package lyon.codinsa.virus.network;

import java.util.List;

public class Board extends StandardResponse {

    public Container object;

    public class Container {

        public List<Node> plateau;
    }
}
