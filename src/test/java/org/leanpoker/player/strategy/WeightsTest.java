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

    public WeightsTest()
    {
    }

    @BeforeClass public static void setUpClass()
    {
        KING_FIVE = new ArrayList<CardJson>();
        SIX_PAIR = new ArrayList<CardJson>();

        KING_FIVE.add(new CardJson("K", "diamonds"));
        KING_FIVE.add(new CardJson("5", "hearts"));
        SIX_PAIR.add(new CardJson("6", "hearts"));
        SIX_PAIR.add(new CardJson("6", "diamonds"));
    }

    @Test public void testSomeMethod()
    {
        Weights weights = new Weights();
        int spw = weights.getFigure(SIX_PAIR).getWeight();

        Assert.assertTrue("Six pair weight: " + spw, (14 + 12) == spw);
        int kfw = weights.getFigure(KING_FIVE).getWeight();

        System.err.println("King five weight: " + kfw);
        System.out.println("King five weight: " + kfw);
        Assert.assertTrue("King five weight: " + kfw, 13 == kfw);
    }
}
