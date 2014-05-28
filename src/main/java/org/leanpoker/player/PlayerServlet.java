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
    static AbstractPlayer playerBasic = new PlayerBasic();
    static AbstractPlayer playerMuzso = new Player();
    static AbstractPlayer playerSmart = new PlayerSmart();
    static AbstractPlayer[] players = new AbstractPlayer[] {   /*playerBasic,*/playerMuzso, playerSmart, new PlayerSvo() };
    static AbstractPlayer player = playerMuzso;

    static
    {
        Random r = new Random();
        int idx = r.nextInt(players.length);

        System.err.println("AI, random player index: " + idx + ", player version: " + player.getVersion());
        player = players[idx];
    }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.getWriter().print("Java player is running");
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
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
