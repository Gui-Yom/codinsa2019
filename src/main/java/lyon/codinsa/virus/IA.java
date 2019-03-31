package lyon.codinsa.virus;

import lyon.codinsa.virus.ai.LessBasicAI;
import lyon.codinsa.virus.ai.VirusAI;
import lyon.codinsa.virus.network.*;

import java.util.List;

public class IA {

    public static void main(String[] args) throws InterruptedException {

        Game game = new Game(generateName(), "http://127.0.0.1:8080");
        game.join();
        System.out.println(game.getToken());

        do {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {}
        } while (game.getTurn() == 0);

        WaitResponse response;
        VirusAI ai = new LessBasicAI(game.getId()); // Change AI here

        // Play until game ends
        int turn = 1;
        do {
            Board board = game.getBoard();
            Visible visible = game.getVisible();
            Graph graph = new Graph(board, visible);
            if (turn == 1) {
                graph.bfs(graph.getPlayerNodes(game.getId()).get(0).getId());
            }
            List<Action> actions = ai.play(graph);
            game.play(actions);
            response = game.endTurn();
            System.out.println(game.getToken() + " : " + graph);
            if (response.partyEnd.equals("success"))
                break;
            do {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {}
            } while (game.getTurn() == turn);
            turn++;

        } while (true);
        System.out.println("Winner is : " + response.winner);
    }

    public static String generateName() {

        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 16; ++i) {
            name.append((char) ('a' + (int) (Math.random() * 26)));
        }
        return name.toString();
    }

}
