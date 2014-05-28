/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.leanpoker.player.json;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.leanpoker.player.AbstractPlayer;
import org.leanpoker.player.Player;

/**
 *
 * @author isvorcz
 */
public class GameStatusJsonTest {
    
    public GameStatusJsonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPhase method, of class GameStatusJson.
     */
    @Test
    public void testMain() {
        GameStatusJson fromJson = GameStatusReader.readJson("initial.json");
        System.out.println("getPhase:");
        Phase expResult = Phase.FLOP;
        Phase result = fromJson.getPhase();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhase method, of class GameStatusJson.
     */
    @Test
    public void testMuzso() {
//        GameStatusJson fromJson = GameStatusReader.readJson("pair.json");
//        System.out.println("testMuzso getPhase:"+fromJson.getPhase());
//        AbstractPlayer player = new Player();
//        System.out.println("bet:"+player.betRequest(fromJson));
    }
}
