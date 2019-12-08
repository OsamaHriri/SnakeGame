package model;

import java.util.Objects;

/**
 *  Game class is made for game History
 */
public class Game {
    Player player;
    int score;
    int numOfSouls;
    String date;
    String durationOfGame;
    Snake snake;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumOfSouls() {
        return numOfSouls;
    }

    public void setNumOfSouls(int numOfSouls) {
        this.numOfSouls = numOfSouls;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDurationOfGame() {
        return durationOfGame;
    }

    public void setDurationOfGame(String durationOfGame) {
        this.durationOfGame = durationOfGame;
    }

    public Game(Player player, int score, int numOfSouls, String date, String durationOfGame) {
        this.player = player;
        this.score = score;
        this.numOfSouls = numOfSouls;
        this.date = date;
        this.durationOfGame = durationOfGame;
        this.snake = new Snake();
    }

    public void addToScore(int points) {
        this.score += points;
    }

    public void addToSouls(int souls) {
        this.numOfSouls += souls;
    }

    public void addToSnakeLength(int length) {
        this.snake.addLengthToSnake(length);
    }

    @Override
    public String toString() {
        return "Game{" +
                "playerId='" + player + '\'' +
                ", score=" + score +
                ", numOfSouls=" + numOfSouls +
                ", date='" + date + '\'' +
                ", durationOfGame='" + durationOfGame + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return score == game.score &&
                numOfSouls == game.numOfSouls &&
                Objects.equals(player, game.player) &&
                Objects.equals(date, game.date) &&
                Objects.equals(durationOfGame, game.durationOfGame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, score, numOfSouls, date, durationOfGame);
    }
}
