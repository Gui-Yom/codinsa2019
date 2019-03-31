package lyon.codinsa.virus;

import lyon.codinsa.virus.ai.AdvancedAI;
import lyon.codinsa.virus.ai.VirusAI;
import lyon.codinsa.virus.network.*;

import java.util.List;

public class IA {

    public static final int startWait = 5000;
    public static final int turnWait = 700;

    public static void main(String[] args) throws InterruptedException {

        Game game = new Game(generateName(), "http://127.0.0.1:8080");
        game.join();
        System.out.println(game.getToken());

        // Wait for other players to connect
        try { Thread.sleep(startWait); } catch (InterruptedException e) {}

        game.startGame();

        WaitResponse response;
        VirusAI ai = new AdvancedAI(game.getId()); // Change AI here
        boolean firstTurn = true;

        // Play until game ends
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

            try { Thread.sleep(turnWait); } catch (InterruptedException e) {}

        } while (!response.partyEnd.equals("success"));

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
