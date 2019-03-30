package lyon.codinsa.virus;

import java.util.LinkedList;
import lyon.codinsa.virus.ai.BasicAI;
import lyon.codinsa.virus.ai.VirusAI;
import lyon.codinsa.virus.network.Action;
import lyon.codinsa.virus.network.Board;
import lyon.codinsa.virus.network.Game;
import lyon.codinsa.virus.network.Graph;
import lyon.codinsa.virus.network.Node;
import lyon.codinsa.virus.network.Visible;
import lyon.codinsa.virus.network.WaitResponse;

public class IA {

    public static void main(String[] args) throws InterruptedException {

        String url = "http://127.0.0.1:8080";

        Game game = new Game("I_NSA Lyon", url);
        game.reset();
        game.join();
        System.out.println(game.getToken());

        Game game2 = new Game("I_NSA Lyon 2", url);
        game2.join();
        System.out.println(game2.getToken());

        //game.chooseMap("map0");

        do {
            Thread.sleep(1000);
        } while (game.gameWait().wait.equals("true"));

        System.out.println("Game started !");
        game.startGame();
        
        WaitResponse response;
        VirusAI ai = new BasicAI(game.getId()); // Change AI here
        do {
            Board board = game.getBoard();
            Visible visible = game.getVisible();
            
            Graph graph = new Graph(board, visible);
            LinkedList<Action> actions = ai.play(graph);
            game.play(actions);
            response = game.endTurn();
            do {
                Thread.sleep(1000);
            } while (game.gameWait().wait.equals("true"));
        } while(response.partyEnd.equals("success"));

        for (Node n : game.getBoard().object.plateau)
            System.out.println(n);

        System.out.println();

        for (Node n : game.getVisible().object.visible)
            System.out.println(n);

        System.exit(0);
    }

}
