package org.leanpoker.player;

import com.google.gson.JsonElement;
import org.leanpoker.player.json.GameStatusJson;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
        return 0;
    }

    public static void showdown(JsonElement game) {
    }

    public static int betRequest(GameStatusJson status) {
        if (status.current_buy_in - status.players.get(status.in_action).bet > status.players.get(status.in_action).stack / 2) {
            return status.current_buy_in - status.players.get(status.in_action).bet + status.minimum_raise;
        } else {
            return 0;
        }
    }

    public static void showdown(GameStatusJson status) {

    }
}
