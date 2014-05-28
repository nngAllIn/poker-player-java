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
    private int figure;
    private int weight;

    public List<CardJson> getCards()
    {
        return cards;
    }

    public void setCards(List<CardJson> cards)
    {
        this.cards = cards;
    }

    public int getFigure()
    {
        return figure;
    }

    public void setFigure(int figure)
    {
        this.figure = figure;
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
