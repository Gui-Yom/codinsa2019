package lyon.codinsa.virus.network;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

/**
 * Point d'entrée de la gestion de l'API du serveur.
 */
public class Game {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static final MediaType FORM = MediaType.get("application/x-www-form-urlencoded");

    private String name;
    private String url;
    private OkHttpClient http;
    private Gson gson;

    private int id;
    private String token;

    public Game(String name, String url) {

        this.name = name;
        this.http = new OkHttpClient();
        this.url = url;
        this.gson = new Gson();
    }

    public boolean join() {

        Request req = new Request.Builder()
            .url(url + "/IA/Join?IAName=" + name)
            .header("IAName", name)
            .post(RequestBody.create(FORM, "IAName=" + name))
            .build();

        //System.out.println(req);

        try (Response response = http.newCall(req).execute()) {

            JoinResponse jr = gson.fromJson(response.body().string(), JoinResponse.class);
            if (!jr.status.equals("success")) {
                System.err.println("Error returned on request : " + req.url());
                System.err.println("Error : " + jr.error);
                System.err.println("Error msg : " + jr.message);
                System.err.println(response.code());
                return false;
            }
            this.id = jr.id;
            this.token = jr.token;
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
            .url(url + "/Start/ChooseMap?Map=" + mapName)
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
                System.err.println("Error msg : " + parsed.message);
                System.err.println(response.code());
                return false;
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean play(List<Action> actions) {

        for (Action a : actions)
            a.owner = id;

        Request req = new Request.Builder()
            .url(url + "/PlayAction?Token=" + token)
            .header("Token", token)
            .post(RequestBody.create(JSON, gson.toJson(actions)))
            .build();

        return stdReqCheck(req);
    }

    public Board getBoard() {

        Request req = new Request.Builder()
            .url(url + "/Get/Board?Token=" + token)
            .header("Token", token)
            .get()
            .build();

        try (Response response = http.newCall(req).execute()) {

            Board parsed = gson.fromJson(response.body().string(), Board.class);
            if (!parsed.status.equals("success")) {
                System.err.println("Error returned on request : " + req.url());
                System.err.println("Error : " + parsed.error);
                System.err.println("Error msg : " + parsed.message);
                System.err.println(response.code());
                return getBoard();
            }
            return parsed;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Visible getVisible() {

        Request req = new Request.Builder()
            .url(url + "/Get/Visible?Token=" + token)
            .header("Token", token)
            .get()
            .build();

        try (Response response = http.newCall(req).execute()) {

            Visible parsed = gson.fromJson(response.body().string(), Visible.class);
            if (!parsed.status.equals("success")) {
                System.err.println("Error returned on request : " + req.url());
                System.err.println("Error : " + parsed.error);
                System.err.println("Error msg : " + parsed.message);
                System.err.println(response.code());
                return getVisible();
            }
            return parsed == null ? getVisible() : parsed;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public WaitResponse endTurn() {

        Request req = new Request.Builder()
            .url(url + "/End/Turn")
            //.header("Token", token)
            .post(RequestBody.create(FORM, "Token=" + token))
            .build();

        try (Response response = http.newCall(req).execute()) {

            WaitResponse parsed = gson.fromJson(response.body().string(), WaitResponse.class);
            if (!parsed.status.equals("success")) {
                System.err.println("Error returned on request : " + req.url());
                System.err.println("Error : " + parsed.error);
                System.err.println("Error msg : " + parsed.message);
                System.err.println(response.code());
                return endTurn();
            }
            return parsed;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public WaitResponse gameWait() {

        Request req = new Request.Builder()
            .url(url + "/Wait?Token=" + token)
            .header("Token", token)
            .get()
            .build();

        try (Response response = http.newCall(req).execute()) {

            WaitResponse parsed = gson.fromJson(response.body().string(), WaitResponse.class);
            if (!parsed.status.equals("success")) {
                System.err.println("Error returned on request : " + req.url());
                System.err.println("Error : " + parsed.error);
                System.err.println("Error msg : " + parsed.message);
                System.err.println(response.code());
                return gameWait();
            }
            return parsed;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTurn() {

        Request req = new Request.Builder()
            .url(url + "/Get/Turn?Token=" + token)
            .header("Token", token)
            .get()
            .build();

        try (Response response = http.newCall(req).execute()) {

            return gson.fromJson(response.body().string(), GetTurnResponse.class).turn;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getName() {

        return name;
    }

    public int getId() {

        return id;
    }

    public String getToken() {

        return token;
    }

    public void close() {

    }
}
