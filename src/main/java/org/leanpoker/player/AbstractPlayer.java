package org.leanpoker.player;

import org.leanpoker.player.json.GameStatusJson;

public abstract class AbstractPlayer {

    protected static GameData data = new GameData();
    
    public static class GameData {
        GameStatusJson lastStatus;
    }
    
    public final String VERSION = "Nespresso";

    public abstract int betRequest(GameStatusJson status);

    public void showdown(GameStatusJson status) {
        try {
            System.err.println("showdown:" + status.players.get(0).hole_cards == null ? -1 : status.players.get(0).hole_cards.size());
        } catch (Exception e) {
        }
    };

    public abstract String getVersion();
}
