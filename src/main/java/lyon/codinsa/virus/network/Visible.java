package lyon.codinsa.virus.network;

import java.util.List;

public class Visible extends StandardResponse {

    public Container object;

    public class Container {

        public List<Node> visible;
    }
}
