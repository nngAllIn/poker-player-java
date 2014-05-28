package org.leanpoker.player;

import org.leanpoker.player.json.GameStatusJson;

public abstract class AbstractPlayer {

    public final String VERSION = "Nespresso";

    public abstract int betRequest(GameStatusJson status);

    public abstract void showdown(GameStatusJson status);

    public abstract String getVersion();
}
