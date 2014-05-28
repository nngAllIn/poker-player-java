package org.leanpoker.player;

import org.leanpoker.player.json.CardJson;
import org.leanpoker.player.json.GameStatusJson;
import org.leanpoker.player.json.PlayerJson;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
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
        int bet = status.current_buy_in - status.players.get(status.in_action).bet;
        Double rank = myRanking(status);
        Double highBet = Math.ceil(status.minimum_raise
                  + (new Double(status.players.get(status.in_action).stack - status.minimum_raise) * rank));
        int activePlayerCount = 0;

        for (PlayerJson player : status.players)
        {
            if (!player.status.equalsIgnoreCase("out"))
            {
                activePlayerCount++;
            }
        }

        if (activePlayerCount > 2)
        {
            bet = 0;
        }
        else
        {
            if (((betReqCount < 3) || (rank.compareTo(0.0) > 0))
                  //&& ((status.current_buy_in - status.players.get(status.in_action).bet) < (status.players.get(status.in_action).stack / 2))
                  && (highBet > status.minimum_raise))
            {
                bet = status.current_buy_in - status.players.get(status.in_action).bet + highBet.intValue();
            }
            else if ((status.community_cards.size() > 3) && (rank < 0.01))
            {
                bet = 0;
            }
        }

        log("RR, # " + round + "/" + betReqCount + ", ccards: " + status.community_cards.size() + ", mr " + status.minimum_raise + ", R "
              + rank + ", B " + bet + ", HB " + highBet.intValue() + ", a.p.c. " + activePlayerCount);

        return bet;
    }

    private static int getCardRank(String rank)
    {
        String[] ranks = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
        int ret = 0;

        for (int i = 0; i < ranks.length; i++)
        {
            if (ranks[i].equals(rank))
            {
                ret = i;

                break;
            }
        }

        return ret;
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
        HashSet<String> pairs = new HashSet<String>();
        String drill = null;
        int maxRank = 0;
        int maxSpecialRank = 0;
        int maxRankCount = 0;
        int maxSuitCount = 0;

        for (CardJson card : allCards)
        {
            Integer count = rankCount.get(card.rank);

            if (count == null)
            {
                count = 0;
            }

            int k = getCardRank(card.rank);

            if (k > maxRank)
            {
                maxRank = k;
            }

            count++;
            rankCount.put(card.rank, count);
            if (count == 2)
            {
                if (k > maxSpecialRank)
                {
                    maxSpecialRank = k;
                }

                pairs.add(card.rank);
            }
            else if (count == 3)
            {
                if (k > maxSpecialRank)
                {
                    maxSpecialRank = k;
                }

                drill = card.rank;
                pairs.remove(card.rank);
            }

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
        int rank = 0;

        if (maxSpecialRank > 0)
        {
            if (drill != null)
            {
                rank = 30 + maxSpecialRank;
            }
            else if (pairs.size() > 0)
            {
                rank = 20 + maxSpecialRank;
            }
        }
        else
        {
            rank = maxRank;
        }

        // 0 <= rank <= 14
        return new Double(rank) / 40;
    }
}
