/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player.json;

import java.util.List;

/**
 *
 * @author isvorcz
 */
public class GameStatusJson {

    public int small_blind;

    public int current_buy_in;
    public int pot;
    public int minimum_raise;                        //     current_buy_in - players[in_action][bet] + minimum_raise

    public int dealer;                               //     The first player is (dealer+1)%(players.length)

    public int orbits;                               //     button returned to the same player.)

    public int in_action;
    public List<PlayerJson> players;
    public List<CardJson> community_cards;

    public Phase getPhase() {
        return community_cards == null || community_cards.isEmpty() ? Phase.PREFLOP //
                : community_cards.size() == 3 ? Phase.FLOP //
                : community_cards.size() == 4 ? Phase.RIVER : Phase.TURN; //
    }

    public List<CardJson> getMyCards() {
        return getMyPlayer().hole_cards;
    }

    public PlayerJson getMyPlayer() {
        return players.get(in_action);
    }

    public int getHandPair() {
        int c0 = getCardRadRank(getMyCards().get(0));
        int c1 = getCardRadRank(getMyCards().get(1));
        return c0 == c1 ? c0 : 0;
    }

    public int getCardRadRank(CardJson card) {
        switch (card.rank) {
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 8;
            case "10":
                return 10;
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return 0;
        }
    }
}
