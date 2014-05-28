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
public class PlayerJson {
     public int id;
public String            name;
            public String status;
            public String version;
     public int        stack;
     public int        bet;
            public List<CardJson> hole_cards; 
}
