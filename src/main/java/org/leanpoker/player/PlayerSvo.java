 -/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

import java.util.List;
import org.leanpoker.player.json.CardJson;
import org.leanpoker.player.json.GameStatusJson;
import org.leanpoker.player.json.Phase;

/**
 *
 * @author isvorcz
 */


public class PlayerSvo extends AbstractPlayer {

    AbstractPlayer other = new PlayerSmart();

    @Override
    public int betRequest(GameStatusJson status) {
        if (status.getHandPair() > 11) {
            return 5000;
        }
//            if (Phase.PREFLOP == status.getPhase()) {
        List<CardJson> cards = status.getMyCards();
        if (status.getHandPair() > 0 || status.getCardRadRank(cards.get(0)) >= 13 || status.getCardRadRank(cards.get(1)) >= 13) {
            return status.call();
        }
        return 0;
//            }
    }

    @Override
    public String getVersion() {
        return "Castle";
    }

}
