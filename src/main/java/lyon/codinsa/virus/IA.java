package lyon.codinsa.virus;

import lyon.codinsa.virus.network.Game;

public class IA {

    public static void main(String[] args) {

        Game game = new Game("I_NSA Lyon", "http://127.0.0.1:8080");
        game.reset();
        game.join();
        game.chooseMap("map0.txt");
        System.out.println(game.getInfo().token);
    }

}
