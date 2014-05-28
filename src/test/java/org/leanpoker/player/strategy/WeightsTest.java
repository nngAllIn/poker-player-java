package org.leanpoker.player.strategy;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.leanpoker.player.json.CardJson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zkloczka
 */
public class WeightsTest
{
    private static List<CardJson> KING_FIVE;
    private static List<CardJson> SIX_PAIR;
    private static List<CardJson> SIX_EIGHT_PAIR;
    private static List<CardJson> THREE_FULL;

    public WeightsTest()
    {
    }

    @BeforeClass public static void setUpClass()
    {
        KING_FIVE = new ArrayList<CardJson>();
        SIX_PAIR = new ArrayList<CardJson>();
        SIX_EIGHT_PAIR = new ArrayList<CardJson>();
        THREE_FULL = new ArrayList<CardJson>();

        // king five
        KING_FIVE.add(new CardJson("K", "diamonds"));
        KING_FIVE.add(new CardJson("5", "hearts"));

        // six pair
        SIX_PAIR.add(new CardJson("6", "hearts"));
        SIX_PAIR.add(new CardJson("6", "diamonds"));

        //six eight pair
        SIX_EIGHT_PAIR.addAll(SIX_PAIR);
        SIX_EIGHT_PAIR.add(new CardJson("8", "diamonds"));
        SIX_EIGHT_PAIR.add(new CardJson("8", "hearts"));

        // three full
        THREE_FULL.add(new CardJson("3", "hearts"));
        THREE_FULL.add(new CardJson("3", "spades"));
        THREE_FULL.add(new CardJson("3", "diamonds"));
        THREE_FULL.addAll(SIX_PAIR);
    }

    @Test public void testSomeMethod()
    {
        Weights weights = new Weights();
        int spw = weights.getFigure(SIX_PAIR).getWeight();
        int kfw = weights.getFigure(KING_FIVE).getWeight();
        int sepw = weights.getFigure(SIX_EIGHT_PAIR).getWeight();
        int tfw = weights.getFigure(THREE_FULL).getWeight();

        Assert.assertTrue("Six pair weight: " + spw, (14 + 12) == spw);
        Assert.assertTrue("King five weight: " + kfw, 13 == kfw);
        Assert.assertTrue("six eight pair weight: " + sepw, (Weights.MAXW_PAIR + (Weights.PAIR_TWO * 8)) == sepw);
        Assert.assertTrue("three full weight: " + tfw, (Weights.MAXW_FLUSH + (Weights.FULL * 3)) == tfw);
    }
}
