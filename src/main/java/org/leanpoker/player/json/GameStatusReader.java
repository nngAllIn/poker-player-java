/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player.json;

import com.google.gson.Gson;
import java.io.InputStreamReader;

/**
 *
 * @author isvorcz
 */
public class GameStatusReader {

    public static GameStatusJson readJson(String name) {
        Gson gson = new Gson();
        GameStatusJson fromJson = gson.fromJson(new InputStreamReader(
                GameStatusReader.class.getClassLoader().getResourceAsStream("META-INF/" + name)), GameStatusJson.class);
        return fromJson;
    }
}
