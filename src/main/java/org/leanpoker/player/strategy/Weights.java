package org.leanpoker.player.strategy;

import org.leanpoker.player.json.CardJson;

import java.util.List;

/**
 *
 * @author zkloczka
 */
public class Weights
{
    public static final int NOPARSED = 0;
    public static final int HIGH = 1;
    public static final int PAIR = 2;
    public static final int PAIR_TWO = 3;
    public static final int DRILL = 4;
    public static final int STRAIGHT = 5;  //szamsor
    public static final int FLUSH = 6;  // szinsor
    public static final int FULL = 7;
    public static final int POKER = 8;
    public static final int STRAIGHT_FLUSH = 9;  // szin sor ES szamsor
    public static final int ROYAL_FLUSH = 10;  // szin sor ES szamsor Asz veggel
    public static final int NUM_2 = 2;
    public static final int NUM_3 = 3;
    public static final int NUM_4 = 4;
    public static final int NUM_5 = 5;
    public static final int NUM_6 = 6;
    public static final int NUM_7 = 7;
    public static final int NUM_8 = 8;
    public static final int NUM_9 = 9;
    public static final int NUM_10 = 10;
    public static final int NUM_J = 11;
    public static final int NUM_Q = 12;
    public static final int NUM_K = 13;
    public static final int NUM_A = 14;

    //max weights
    public static final int MAXW_HIGH = 14;
    public static final int MAXW_PAIR = (2 * 14) + MAXW_HIGH;  // ACE pair weight is 2*14 = 28
    public static final int MAXW_PAIR_TWO = (3 * 14) + MAXW_PAIR;
    public static final int MAXW_DRILL = (4 * 14) + MAXW_PAIR_TWO;
    public static final int MAXW_STRAIGHT = (5 * 14) + MAXW_DRILL;
    public static final int MAXW_FLUSH = (6 * 14) + MAXW_STRAIGHT;
    public static final int MAXW_FULL = (7 * 14) + MAXW_FLUSH;
    public static final int MAXW_POKER = (8 * 14) + MAXW_FULL;
    public static final int MAXW_STRAIGHT_FLUSH = (9 * 14) + MAXW_POKER;
    public static final int MAXW_ROYAL_FLUSH = (10 * 14) + MAXW_STRAIGHT_FLUSH;

    public static void main(String[] args) throws Exception
    {
        System.out.println("MAXW_HIGH: " + MAXW_HIGH);
        System.out.println("MAXW_PAIR: " + MAXW_PAIR);
        System.out.println("MAXW_PAIR_TWO: " + MAXW_PAIR_TWO);
        System.out.println("MAXW_DRILL: " + MAXW_DRILL);
        System.out.println("MAXW_STRAIGHT: " + MAXW_STRAIGHT);
        System.out.println("MAXW_FLUSH: " + MAXW_FLUSH);
        System.out.println("MAXW_FULL: " + MAXW_FULL);
        System.out.println("MAXW_POKER: " + MAXW_POKER);
        System.out.println("MAXW_STRAIGHT_FLUSH: " + MAXW_STRAIGHT_FLUSH);
        System.out.println("MAXW_ROYAL_FLUSH: " + MAXW_ROYAL_FLUSH);
    }

    public Figure getFigure(List<CardJson> cards)
    {
        Figure result = figure(cards);

        return result;
    }

    private void setWeightIfNeed(Figure figure, int weight)
    {
        if (figure.getWeight() < weight)
        {
            figure.setWeight(weight);
        }
    }

    private int calcRank(CardJson card)
    {
        int result = 0;
        String rank = card.rank.toUpperCase();

        if (rank.equals("2"))
        {
            result = 2;
        }
        else if (rank.equals("3"))
        {
            result = 3;
        }
        else if (rank.equals("4"))
        {
            result = 4;
        }
        else if (rank.equals("5"))
        {
            result = 5;
        }
        else if (rank.equals("6"))
        {
            result = 6;
        }
        else if (rank.equals("7"))
        {
            result = 7;
        }
        else if (rank.equals("8"))
        {
            result = 8;
        }
        else if (rank.equals("9"))
        {
            result = 9;
        }
        else if (rank.equals("10"))
        {
            result = 10;
        }
        else if (rank.equals("J"))
        {
            result = 11;
        }
        else if (rank.equals("Q"))
        {
            result = 12;
        }
        else if (rank.equals("K"))
        {
            result = 13;
        }
        else if (rank.equals("A"))
        {
            result = 14;
        }

        return result;
    }

    private Figure figure(List<CardJson> actualGame)
    {
        Figure result = newFigure();
        int[] ranks = new int[15];

        for (CardJson card : actualGame)
        {
            int rank = calcRank(card);

            ranks[rank] = ranks[rank] + 1;
        }

        for (int ii = 0; ii < 15; ii++)
        {
            switch (ranks[ii])
            {

                case 1:
                    if (result.getFigure() == NOPARSED)
                    {
                        result.setFigure(HIGH);
                        result.setWeight(ii);
                    }

                    break;

                case 2:
                    switch (result.getFigure())
                    {

                        case NOPARSED:
                            result.setFigure(PAIR);
                            result.setWeight(ii * PAIR);
                            break;

                        case PAIR:
                            result.setFigure(PAIR_TWO);
                            setWeightIfNeed(result, ii * PAIR_TWO);
                            break;

                        case (DRILL):
                            result.setFigure(FULL);
                            setWeightIfNeed(result, ii * FULL);
                            break;
                    }

                    break;

                case 3:
                    switch (result.getFigure())
                    {

                        case NOPARSED:
                            result.setFigure(DRILL);
                            setWeightIfNeed(result, ii * DRILL);
                            break;

                        case PAIR:
                            result.setFigure(FULL);
                            setWeightIfNeed(result, ii * FULL);
                            break;
                    }

                    break;

                case 4:
                    result.setFigure(POKER);
                    setWeightIfNeed(result, ii * POKER);
                    break;
            }
        }

        return result;
    }

    private Figure newFigure()
    {
        Figure result = new Figure();

        result.setFigure(NOPARSED);
        result.setWeight(NOPARSED);

        return result;
    }
}
