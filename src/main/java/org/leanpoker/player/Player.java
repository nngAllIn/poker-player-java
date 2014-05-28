package org.leanpoker.player;

import org.leanpoker.player.json.CardJson;
import org.leanpoker.player.json.GameStatusJson;
import org.leanpoker.player.json.PlayerJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player
{
    static final String VERSION = "Nespresso";

    public static int betRequest(GameStatusJson status)
    {

        //List<CardJson> cards = status.players.get(status.in_action).hole_cards;
        Double rank = myRanking(status);

        if ((rank.compareTo(0.0) > 0)
              && ((status.current_buy_in - status.players.get(status.in_action).bet) > (status.players.get(status.in_action).stack / 2)))
        {
            Double raise = Math.floor(new Double(status.players.get(status.in_action).stack - status.minimum_raise) * rank);

            System.err.println("AI, " + rank + ", " + raise);

            return status.current_buy_in - status.players.get(status.in_action).bet + raise.intValue();
        }
        else
        {
            return 0;
        }
    }

    public static void showdown(GameStatusJson status)
    {
    }

    private static double myRanking(GameStatusJson status)
    {
        PlayerJson we = status.players.get(status.in_action);
        List<CardJson> allCards = new ArrayList<CardJson>();

        allCards.addAll(we.hole_cards);
        allCards.addAll(status.community_cards);
        HashMap<String, Integer> rankCount = new HashMap<String, Integer>();
        HashMap<String, Integer> suitCount = new HashMap<String, Integer>();
        int maxRankCount = 0;
        int maxSuitCount = 0;

        for (CardJson card : allCards)
        {
            Integer count = rankCount.get(card.rank);

            if (count == null)
            {
                count = 0;
            }

            count++;
            rankCount.put(card.rank, count);
            if (count > maxRankCount)
            {
                maxRankCount = count;
            }

            count = suitCount.get(card.suit);
            if (count == null)
            {
                count = 0;
            }

            count++;
            if (count > maxSuitCount)
            {
                maxSuitCount = count;
            }

            suitCount.put(card.suit, count);
        }

        System.err.println("AI, " + maxRankCount + ", " + maxSuitCount);

        // 0 <= rank <= 14
        return (maxRankCount + maxSuitCount) / 14;
    }
}
