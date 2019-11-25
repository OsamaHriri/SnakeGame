package model;

import java.util.Arrays;
import java.util.Objects;

public class Snake {

    private int head;
    private int tail;
    private int length;
    private int souls;
    private int[][] placeOnBoard;

    public Snake(int head, int tail, int length, int souls, int[][] placeOnBoard) {
        this.head = head;
        this.tail = tail;
        this.length = length;
        this.souls = souls;
        this.placeOnBoard = placeOnBoard;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public int getTail() {
        return tail;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSouls() {
        return souls;
    }

    public void setSouls(int souls) {
        this.souls = souls;
    }

    public int[][] getPlaceOnBoard() {
        return placeOnBoard;
    }

    public void setPlaceOnBoard(int[][] placeOnBoard) {
        this.placeOnBoard = placeOnBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snake snake = (Snake) o;
        return head == snake.head &&
                tail == snake.tail &&
                length == snake.length &&
                souls == snake.souls &&
                Arrays.equals(placeOnBoard, snake.placeOnBoard);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(head, tail, length, souls);
        result = 31 * result + Arrays.hashCode(placeOnBoard);
        return result;
    }

    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head +
                ", tail=" + tail +
                ", length=" + length +
                ", souls=" + souls +
                ", placeOnBoard=" + Arrays.toString(placeOnBoard) +
                '}';
    }
}
