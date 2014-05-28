/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

import org.leanpoker.player.json.CardJson;
import org.leanpoker.player.json.GameStatusJson;
import org.leanpoker.player.json.Phase;
import org.leanpoker.player.strategy.Figure;
import org.leanpoker.player.strategy.Weights;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KERiii
 */
public class PlayerSmart extends AbstractPlayer
{
    public final String VERSION = "Starsky";
    Weights w = new Weights();

    @Override public String getVersion()
    {
        return VERSION;
    }

    @Override public int betRequest(GameStatusJson status)
    {
        List<CardJson> hand = status.players.get(status.in_action).hole_cards;
        List<CardJson> table = status.community_cards;
        List<CardJson> allcards = new ArrayList<CardJson>();

        allcards.addAll(hand);
        allcards.addAll(table);
        Figure figHand = w.getFigure(hand);
        Figure figTable = w.getFigure(table);
        Figure figAll = w.getFigure(allcards);

//        if (hand.get(0).rank.equals(hand.get(1).rank) && (status.current_buy_in - status.players.get(status.in_action).bet > status.players.get(status.in_action).stack / 2)) {
//            return status.current_buy_in - status.players.get(status.in_action).bet + status.minimum_raise * 3;
//        } else {
//            return 0;
//        }
        int bet = 0;

        if (status.getPhase().ordinal() == Phase.PREFLOP.ordinal())
        {
            if (figHand.getWeight() >= 20)
            {
                bet = status.players.get(status.in_action).stack / 3;
            }
            else if (figHand.getWeight() >= 26)
            {
                bet = status.players.get(status.in_action).stack;
            }
            else if (figHand.getWeight() > 12)
            {
                bet = status.small_blind * 2;
            }
        }
        else if (status.getPhase().ordinal() >= Phase.FLOP.ordinal())
        {
            if (((figAll.getWeight() - figTable.getWeight()) > 10) && (figAll.getPokerFigure() > Weights.PAIR))
            {
                bet = Math.max(status.pot * 2 / 3, status.minimum_raise);
            }
        }

        return bet;
    }

    @Override public void showdown(GameStatusJson status)
    {
    }
}
