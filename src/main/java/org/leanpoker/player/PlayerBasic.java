package org.leanpoker.player;

import java.util.List;
import org.leanpoker.player.json.CardJson;
import org.leanpoker.player.json.GameStatusJson;

public class PlayerBasic extends AbstractPlayer {

    static final String VERSION = "Latte";

    @Override
    public  int betRequest(GameStatusJson status) {
        List<CardJson> cards = status.players.get(status.in_action).hole_cards;
        
        if (cards.get(0).rank.equals(cards.get(1).rank) && (status.current_buy_in - status.players.get(status.in_action).bet > status.players.get(status.in_action).stack / 2)) {
            return status.current_buy_in - status.players.get(status.in_action).bet + status.minimum_raise;
        } else {
            return 0;
        }
    }

    public void showdown(GameStatusJson status) {

    }
}
