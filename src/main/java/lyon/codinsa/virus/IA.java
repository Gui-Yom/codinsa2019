package lyon.codinsa.virus;

import lyon.codinsa.virus.ai.VirusAI;
import lyon.codinsa.virus.network.*;
import java.util.List;
import lyon.codinsa.virus.ai.LessBasicAI;

public class IA {

    public static final int startWait = 5000;
    public static final int turnWait = 700;

    public static void main(String[] args) throws InterruptedException {

        Game game = new Game(generateName(), "http://127.0.0.1:8080");
        game.join();
        System.out.println(game.getToken());
        
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        } while (!game.gameWait().wait.equals("true"));

        WaitResponse response;
        VirusAI ai = new LessBasicAI(game.getId()); // Change AI here
        boolean firstTurn = true;

        // Play until game ends
        int turn = 1;
        do {
            Board board = game.getBoard();
            Visible visible = game.getVisible();
            Graph graph = new Graph(board, visible);
            if (firstTurn) {
                graph.bfs(graph.getPlayerNodes(game.getId()).get(0).getId());
                firstTurn = false;
            }
            List<Action> actions = ai.play(graph);
            game.play(actions);
            response = game.endTurn();
            System.out.println(game.getToken() + " : " + graph);
            if(response.partyEnd.equals("success"))
                break;
            do {
                try {
                    Thread.sleep(turnWait);
                } catch (InterruptedException e) {}
            } while(game.getTurn().equals(turn));
            turn++;

        } while (true);
        System.out.println("Winner is : " + response.winner);
    }

    public static String generateName() {

        String name = "";
        for (int i = 0; i < 16; ++i) {
            name += (char) ('a' + (int) (Math.random() * 26));
        }
        return name;
    }

}
