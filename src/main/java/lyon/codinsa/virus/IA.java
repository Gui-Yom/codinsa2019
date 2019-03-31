package lyon.codinsa.virus;

import java.util.List;
import lyon.codinsa.virus.ai.LessBasicAI;

import lyon.codinsa.virus.ai.VirusAI;
import lyon.codinsa.virus.network.Action;
import lyon.codinsa.virus.network.Board;
import lyon.codinsa.virus.network.Game;
import lyon.codinsa.virus.network.Graph;
import lyon.codinsa.virus.network.Visible;
import lyon.codinsa.virus.network.WaitResponse;

public class IA {
    
    public static String generateName() {
        String name = "";
        for(int i=0; i<16; ++i) {
            name += (char)('a' + (int)(Math.random() * 26));
        }
        return name;
    }

    public static void main(String[] args) throws InterruptedException {

        Game game = new Game(generateName(), "http://127.0.0.1:8080");
        // game.reset();
        game.join();
        System.out.println(game.getToken());
        
        //do {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {}
        //} while (!game.gameWait().wait.equals("true"));
        game.startGame();
        
        WaitResponse response;
        VirusAI ai = new LessBasicAI(game.getId()); // Change AI here
        boolean firstTurn = true;
        do {
            Board board = game.getBoard();
            Visible visible = game.getVisible();
            Graph graph = new Graph(board, visible);
            if(firstTurn) {
                graph.bfs(graph.getPlayerNodes(game.getId()).get(0).getId());
                firstTurn = false;
            }
            List<Action> actions = ai.play(graph);
            game.play(actions);
            response = game.endTurn();
            System.out.println(game.getToken() + " : " + graph);
            System.out.println("P1: " + game.getId());

            //do {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            //} while (game.gameWait().wait.equals("true"));
        } while(!response.partyEnd.equals("success"));
    }

}
