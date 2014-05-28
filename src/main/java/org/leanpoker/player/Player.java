package org.leanpoker.player;

import org.leanpoker.player.json.CardJson;
import org.leanpoker.player.json.GameStatusJson;
import org.leanpoker.player.json.PlayerJson;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Player extends AbstractPlayer
{
    static int round = 0;
    public final String VERSION = "Nespresso India";
    int betReqCount = 0;

    @Override public String getVersion()
    {
        return VERSION;
    }

    @Override public int betRequest(GameStatusJson status)
    {
        if (betReqCount == 0)
        {
            round++;
        }

        betReqCount++;
        int bet = (status.minimum_raise > 0) ? status.minimum_raise : 1;
        Double rank = myRanking(status);
        Double highBet = Math.ceil(status.minimum_raise
                  + (new Double(status.players.get(status.in_action).stack - status.minimum_raise) * (rank + 0.2)));

        log("RR, # " + round + "/" + betReqCount + ", mr " + status.minimum_raise + ", R " + rank + ", B " + bet + ", HB "
              + highBet.intValue());
        if (((betReqCount < 4) || (rank.compareTo(0.0) > 0))
              && ((status.current_buy_in - status.players.get(status.in_action).bet) < (status.players.get(status.in_action).stack / 2))
              && (highBet > bet))
        {
            bet = status.current_buy_in - status.players.get(status.in_action).bet + highBet.intValue();
        }

        return bet;
    }

    private static void log(String msg)
    {
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat fmt = new SimpleDateFormat();

        System.err.println("AI, " + fmt.format(cal.getTime()) + ", " + msg);
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

        log("RC " + maxRankCount + ", SC " + maxSuitCount + ", P " + status.pot);

        // 0 <= rank <= 14
        return (maxRankCount + maxSuitCount) / 14;
    }
}
