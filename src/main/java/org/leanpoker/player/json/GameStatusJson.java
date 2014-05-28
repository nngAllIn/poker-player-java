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
}
