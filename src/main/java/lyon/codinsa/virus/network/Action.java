package lyon.codinsa.virus.network;

public class Action implements Comparable<Action> {

    public int owner;
    public int from;
    public int to;
    public int qtCode;
    public Integer potential = 0;

    public Action(int from, int to, int qtCode) {

        this.from = from;
        this.to = to;
        this.qtCode = qtCode;
    }

    public Action(int from, int to, int qtCode, Integer potential) {

        this.from = from;
        this.to = to;
        this.qtCode = qtCode;
        this.potential = potential;
    }

    public int compareTo(Action action) {

        return potential.compareTo(action.potential);
    }

}
