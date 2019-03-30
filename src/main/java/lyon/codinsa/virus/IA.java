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

class IAThread implements Runnable {
    private Game game;
    
    public IAThread(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        System.out.println(game.getToken());
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        } while (game.gameWait().wait.equals("true"));
        System.out.println(game.getToken());
        
        
        WaitResponse response;
        VirusAI ai = new BasicAI(game.getId()); // Change AI here
        do {
            Board board = game.getBoard();
            Visible visible = game.getVisible();
            
            Graph graph = new Graph(board, visible);
            System.out.println(game.getToken() + " : " + graph);
            LinkedList<Action> actions = ai.play(graph);
            game.play(actions);
            response = game.endTurn();
            do {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            } while (game.gameWait().wait.equals("true"));
        } while(response.partyEnd.equals("success"));
    }
}

public class IA {

    public static void main(String[] args) throws InterruptedException {

        Game game1 = new Game("INSA", "http://127.0.0.1:8080");
        game1.reset();
        game1.join();
        
        Game game2 = new Game("LYON", "http://127.0.0.1:8080");
        game2.join();
        game2.startGame();
        /*.out.println("erhreh");
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        } while (game1.gameWait().wait.equals("true"));*/
        System.out.println(game1.getToken());
        
        Game game = game1;
        
        WaitResponse response;
        VirusAI ai = new BasicAI(game.getId()); // Change AI here
        do {
            Board board = game.getBoard();
            Visible visible = game.getVisible();
            
            Graph graph = new Graph(board, visible);
            System.out.println(game.getToken() + " : " + graph);
            LinkedList<Action> actions = ai.play(graph);
            game.play(actions);
            response = game.endTurn();

            System.out.println(game.getToken() + " : " + graph);
            System.out.println("P1: " + game.getId());
            
            
            
            Board board2 = game2.getBoard();
            Visible visible2 = game2.getVisible();
            
            Graph graph2 = new Graph(board2, visible2);
            System.out.println(game2.getToken() + " : " + graph2);
            LinkedList<Action> actions2 = ai.play(graph2);
            game2.play(actions2);
            response = game2.endTurn();
            System.out.println("P2: " + game2.getId());
            /*do {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            } while (game.gameWait().wait.equals("true"));*/
        } while(response.partyEnd.equals("success"));
    }

}
