package lyon.codinsa.virus.network;

public class Action {

    public int owner;
    public int from;
    public int to;
    public int qtCode;
    public Double potential = 0.0;

    public Action(int from, int to, int qtCode) {

        this.from = from;
        this.to = to;
        this.qtCode = qtCode;
    }
    public Action(int from, int to, int qtCode, Double potential) {

        this.from = from;
        this.to = to;
        this.qtCode = qtCode;
        this.potential = potential;
    }
    public int compareTo(Action action) {
        return potential.compareTo(action.potential);
    }
    /*
    public static List<Action> batchCreate(int... values) {

        if (values.length % 3 != 0) {
            System.err.println("[Action::batchCreate] Incorrect params / Should be modulo 3");
            return null;
        }

        int n = values.length / 3;
        ArrayList<Action> actions = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            actions.add(new Action(values[i * n], values[i * n + 1], values[i * n + 2]));
        }
        return actions;
    }
    */

}
