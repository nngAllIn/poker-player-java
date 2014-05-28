/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

import org.leanpoker.player.json.GameStatusJson;
import org.leanpoker.player.json.PlayerJson;

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
        int out = 0;
        for (PlayerJson p : status.players) {
            if ("out".equals(p.status)) {
                out++;
            }
        }
        return out < (status.players.size() - 2) ? 0 : other.betRequest(status);
    }

    @Override
    public String getVersion() {
        return "Castle";
    }

}
