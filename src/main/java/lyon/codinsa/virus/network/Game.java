package lyon.codinsa.virus.network;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Game {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private String name;
    private String url;
    private OkHttpClient http;
    private Gson gson;

    private JoinResponse jr;

    public Game(String name, String url) {

        this.name = name;
        this.http = new OkHttpClient();
        this.url = url;
        this.gson = new Gson();
    }

    public boolean join() {

        Request req = new Request.Builder()
            .addHeader("IAName", name)
            .url(url + "/IA/Join")
            .method("POST", null)
            .build();

        try (Response response = http.newCall(req).execute()) {

            this.jr = gson.fromJson(response.body().string(), JoinResponse.class);
            if (!jr.status.equals("success")) {
                System.err.println("Error returned on request : " + req.url());
                System.err.println("Error : " + jr.error);
                return false;
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean reset() {

        Request req = new Request.Builder()
            .url(url + "/Reset")
            .get()
            .build();

        return stdReqCheck(req);
    }

    public boolean chooseMap(String mapName) {

        Request req = new Request.Builder()
            .url(url + "/Start/ChooseMap")
            .header("Map", mapName)
            .get()
            .build();

        return stdReqCheck(req);
    }

    public boolean startGame() {

        Request req = new Request.Builder()
            .url(url + "/Start/Game")
            .get()
            .build();

        return stdReqCheck(req);
    }

    private boolean stdReqCheck(Request req) {

        try (Response response = http.newCall(req).execute()) {

            StandardResponse parsed = gson.fromJson(response.body().string(), StandardResponse.class);
            if (!parsed.status.equals("success")) {
                System.err.println("Error returned on request : " + req.url());
                System.err.println("Error : " + parsed.error);
                return false;
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public JoinResponse getInfo() {

        return jr;
    }

    public String getName() {

        return name;
    }
}
