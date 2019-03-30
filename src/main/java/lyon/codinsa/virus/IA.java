package lyon.codinsa.virus;

import lyon.codinsa.virus.network.Game;
import lyon.codinsa.virus.network.Node;

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

        boolean doContinue = true;

        do {
            doContinue = game.gameWait().wait.equals("true");
            Thread.sleep(1000);
        } while (doContinue);

        System.out.println("Game started !");
        game.startGame();
        

        for (Node n : game.getBoard().object.plateau)
            System.out.println(n);

        System.out.println();

        for (Node n : game.getVisible().object.visible)
            System.out.println(n);

        System.exit(0);
    }

}
