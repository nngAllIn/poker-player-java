package org.leanpoker.player;

import com.google.gson.Gson;

import org.leanpoker.player.json.GameStatusJson;

import java.io.IOException;

import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class PlayerServlet extends HttpServlet
{
    AbstractPlayer playerBasic = new PlayerBasic();
    AbstractPlayer playerMuzso = new Player();
    AbstractPlayer playerSmart = new PlayerSmart();
    AbstractPlayer[] players = new AbstractPlayer[] { playerBasic, playerMuzso, playerSmart };

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.getWriter().print("Java player is running");
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Random r = new Random();
        int idx = r.nextInt() % players.length;
        AbstractPlayer player = players[idx];

        System.err.println("AI, player idx: " + idx + ", version: " + player.VERSION);
        if (req.getParameter("action").equals("bet_request"))
        {
            String gameState = req.getParameter("game_state");
            GameStatusJson status = new Gson().fromJson(gameState, GameStatusJson.class);

            resp.getWriter().print(player.betRequest(status));
        }

        if (req.getParameter("action").equals("showdown"))
        {
            String gameState = req.getParameter("game_state");
            GameStatusJson status = new Gson().fromJson(gameState, GameStatusJson.class);

            player.showdown(status);
        }

        if (req.getParameter("action").equals("version"))
        {
            resp.getWriter().print(player.getVersion());
        }
    }
}
