package org.leanpoker.player.strategy;

import org.leanpoker.player.json.CardJson;

import java.util.List;

/**
 *
 * @author zkloczka
 */
public class Figure
{
    private List<CardJson> cards;
    private int pokerFigure;
    private int rank;  // a pair drill full poker dominans erteke
    private int weight;

    public List<CardJson> getCards()
    {
        return cards;
    }

    public void setCards(List<CardJson> cards)
    {
        this.cards = cards;
    }

    public int getPokerFigure()
    {
        return pokerFigure;
    }

    public void setPokerFigure(int pokerFigure)
    {
        this.pokerFigure = pokerFigure;
    }

    public int getRank()
    {
        return rank;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }

    public int getWeight()
    {
        return weight;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }
}
