/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player.json;

/**
 *
 * @author isvorcz
 */
public class CardJson
{
    public String rank;  // 2..10,J,Q,K,A
    public String suit;  //COLOR: spades, hearts, clubs, diamonds

    public CardJson() {
    }

    public CardJson(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }
    
    
}
