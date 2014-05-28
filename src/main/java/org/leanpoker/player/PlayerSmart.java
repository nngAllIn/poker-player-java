/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

import java.util.ArrayList;
import java.util.List;
import org.leanpoker.player.json.CardJson;
import org.leanpoker.player.json.GameStatusJson;
import org.leanpoker.player.strategy.Figure;
import org.leanpoker.player.strategy.Weights;

/**
 *
 * @author KERiii
 */
public class PlayerSmart extends AbstractPlayer{
    
    Weights w = new Weights();
    public final String VERSION = "Starsky";
    
    
        
    @Override
        public int betRequest(GameStatusJson status) {
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
        
        if (figHand.getWeight()>=24  ){
            return 10000;
        } 
        else   return 0;
        
    }

    @Override
    public void showdown(GameStatusJson status) {

    }

    @Override
    public String getVersion() {
        return VERSION;
    }

}
