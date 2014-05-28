/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.leanpoker.player.json;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author isvorcz
 */
@Ignore public class GameStatusJsonTest {
    
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
    public void testGetPhase() {
        System.out.println("getPhase");
        GameStatusJson instance = new GameStatusJson();
        Phase expResult = null;
        Phase result = instance.getPhase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMyCards method, of class GameStatusJson.
     */
    @Test
    public void testGetMyCards() {
        System.out.println("getMyCards");
        GameStatusJson instance = new GameStatusJson();
        List<CardJson> expResult = null;
        List<CardJson> result = instance.getMyCards();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMyPlayer method, of class GameStatusJson.
     */
    @Test
    public void testGetMyPlayer() {
        System.out.println("getMyPlayer");
        GameStatusJson instance = new GameStatusJson();
        PlayerJson expResult = null;
        PlayerJson result = instance.getMyPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHandPair method, of class GameStatusJson.
     */
    @Test
    public void testGetHandPair() {
        System.out.println("getHandPair");
        GameStatusJson instance = new GameStatusJson();
        int expResult = 0;
        int result = instance.getHandPair();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCardRadRank method, of class GameStatusJson.
     */
    @Test
    public void testGetCardRadRank() {
        System.out.println("getCardRadRank");
        CardJson card = null;
        GameStatusJson instance = new GameStatusJson();
        int expResult = 0;
        int result = instance.getCardRadRank(card);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
