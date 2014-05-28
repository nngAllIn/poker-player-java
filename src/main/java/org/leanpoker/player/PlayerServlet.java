package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.leanpoker.player.json.GameStatusJson;

@WebServlet("/")
public class PlayerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("Java player is running");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("bet_request")) {
            String gameState = req.getParameter("game_state");
            GameStatusJson status = new Gson().fromJson(gameState, GameStatusJson.class);
            resp.getWriter().print(Player.betRequest(status));
        }
        if (req.getParameter("action").equals("showdown")) {
            String gameState = req.getParameter("game_state");

            GameStatusJson status = new Gson().fromJson(gameState, GameStatusJson.class);
            Player.showdown(status);
        }
        if (req.getParameter("action").equals("version")) {
            resp.getWriter().print(Player.VERSION);
        }
    }
}
