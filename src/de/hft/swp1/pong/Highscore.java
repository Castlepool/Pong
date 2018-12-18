package de.hft.swp1.pong;

public class Highscore
{
    public final String playerName;
    public final int    score;

    public Highscore(String playerName, int score)
    {
        this.playerName = playerName;
        this.score      = score;
    }
}
