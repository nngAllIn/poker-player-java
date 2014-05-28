package org.leanpoker.player.strategy;

import org.leanpoker.player.json.CardJson;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zkloczka
 */
public class Weights
{

    //poker cases
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

    //ranks
    public static final int NUM_ERROR = -1;
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

    //suits
    public static final int SUIT_ERROR = -1;
    public static final int SUIT_CLUBS = 3;
    public static final int SUIT_DIAMONDS = 2;
    public static final int SUIT_HEARTS = 1;
    public static final int SUIT_SPADES = 0;

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

    private boolean setWeightIfNeed(Figure figure, int weight, int rank)
    {
        boolean result = false;

        if (figure.getWeight() < weight)
        {
            figure.setWeight(weight);
            figure.setRank(rank);
            result = true;
        }

        return result;
    }

    private int calcRank(CardJson card)
    {
        int result = NUM_ERROR;
        String rank = card.rank.toUpperCase();

        if (rank.equals("2"))
        {
            result = NUM_2;
        }
        else if (rank.equals("3"))
        {
            result = NUM_3;
        }
        else if (rank.equals("4"))
        {
            result = NUM_4;
        }
        else if (rank.equals("5"))
        {
            result = NUM_5;
        }
        else if (rank.equals("6"))
        {
            result = NUM_6;
        }
        else if (rank.equals("7"))
        {
            result = NUM_7;
        }
        else if (rank.equals("8"))
        {
            result = NUM_8;
        }
        else if (rank.equals("9"))
        {
            result = NUM_9;
        }
        else if (rank.equals("10"))
        {
            result = NUM_10;
        }
        else if (rank.equals("J"))
        {
            result = NUM_J;  // 11;
        }
        else if (rank.equals("Q"))
        {
            result = NUM_Q;  // 12;
        }
        else if (rank.equals("K"))
        {
            result = NUM_K;  // 13;
        }
        else if (rank.equals("A"))
        {
            result = NUM_A;  // 14;
        }

        return result;
    }

    private int calcSuit(String cardSuit)
    {
        int result = SUIT_ERROR;
        String sVal = cardSuit.toLowerCase();

        if (sVal.equals("spades"))
        {
            result = SUIT_SPADES;
        }
        else if (sVal.equals("hearts"))
        {
            result = SUIT_HEARTS;
        }
        else if (sVal.equals("clubs"))
        {
            result = SUIT_CLUBS;
        }
        else if (sVal.equals("diamonds"))
        {
            result = SUIT_DIAMONDS;
        }

        return result;
    }

    private Figure figure(List<CardJson> cards)
    {
        Figure result = newFigure();
        int[] ranks = new int[15];
        int[] colors = new int[cards.size()];
        int ii = 0;

        for (CardJson card : cards)
        {
            int rank = calcRank(card);

            ranks[rank] = ranks[rank] + 1;
            int suit = calcSuit(card.suit);

            colors[ii] = suit;

            ii++;
        }

        figureNonSerie(result, ranks);
        figureSeries(result, colors, cards);

        return result;
    }

    private Figure figureNonSerie(Figure figure, int[] ranks)
    {
        for (int ii = 0; ii < 15; ii++)
        {
            switch (ranks[ii])
            {

                case 1:
                    if (figure.getPokerFigure() == NOPARSED)
                    {
                        figure.setPokerFigure(HIGH);
                        setWeightIfNeed(figure, ii * HIGH, ii);
                    }

                    break;

                case 2:
                    switch (figure.getPokerFigure())
                    {

                        case NOPARSED:
                            figure.setPokerFigure(PAIR);
                            figure.setWeight(ii * PAIR);
                            break;

                        case PAIR:
                            figure.setPokerFigure(PAIR_TWO);
                            setWeightIfNeed(figure, ii * PAIR_TWO, ii);
                            break;

                        case (DRILL):
                            figure.setPokerFigure(FULL);
                            setWeightIfNeed(figure, ii * FULL, ii);
                            break;
                    }

                    break;

                case 3:
                    switch (figure.getPokerFigure())
                    {

                        case NOPARSED:
                            figure.setPokerFigure(DRILL);
                            setWeightIfNeed(figure, ii * DRILL, ii);
                            break;

                        case PAIR:
                            figure.setPokerFigure(FULL);
                            setWeightIfNeed(figure, ii * FULL, ii);
                            break;
                    }

                    break;

                case 4:
                    figure.setPokerFigure(POKER);
                    setWeightIfNeed(figure, ii * POKER, ii);
                    break;
            }
        }

        return figure;
    }

    private Figure figureSeries(Figure figure, int[] colors, List<CardJson> cards)
    {
        if (figure.getPokerFigure() < STRAIGHT)
        {
            int[] ranks = new int[cards.size()];

            for (int ii = 0; ii < cards.size(); ii++)
            {
                ranks[ii] = calcRank(cards.get(ii));
            }

            Arrays.sort(ranks);
            int straight = 0;

            for (int ii = 1; ii < ranks.length; ii++)
            {
                if ((ranks[ii - 1] + 1) == ranks[ii])
                {
                    straight++;
                }
                else
                {
                    straight = 0;
                }
            }

            if (straight >= 5)
            {
                figure.setPokerFigure(STRAIGHT);
            }
        }

        if (figure.getPokerFigure() < FLUSH)
        {
            int flush = 0;
            int prevSuit = SUIT_ERROR;

            for (int ii = 0; ii < colors.length; ii++)
            {
                if (colors[ii] != prevSuit)
                {
                    flush = 0;
                }
                else
                {
                    flush++;
                }

                prevSuit = colors[ii];
            }

            if (flush >= 5)
            {
                figure.setPokerFigure(FLUSH);
            }
        }

        return figure;
    }

    private Figure newFigure()
    {
        Figure result = new Figure();

        result.setPokerFigure(NOPARSED);
        result.setWeight(NOPARSED);

        return result;
    }
}
