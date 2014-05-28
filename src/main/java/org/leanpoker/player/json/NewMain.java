/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.leanpoker.player.json;

import java.util.Random;
import org.leanpoker.player.AbstractPlayer;
import org.leanpoker.player.Player;

/**
 *
 * @author isvorcz
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameStatusJson fromJson = GameStatusReader.readJson("pair.json");
        System.out.println("testMuzso getPhase:"+fromJson.getPhase());
        AbstractPlayer player = new Player();
        System.out.println("bet:"+player.betRequest(fromJson));
    }
    
}
