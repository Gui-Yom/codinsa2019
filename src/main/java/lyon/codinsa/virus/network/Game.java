package lyon.codinsa.virus.network;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class Game {

    private String name;
    private OkHttpClient http;

    public static final MediaType JSON
        = MediaType.get("application/json; charset=utf-8");

    public Game(String name, String remote) {

        this.name = name;
        this.http = new OkHttpClient();

    }

}
