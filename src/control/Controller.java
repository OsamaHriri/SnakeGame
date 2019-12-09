package control;


import java.util.ArrayList;


import model.*;


import javafx.scene.media.MediaPlayer;

import view.MainGameView;

public class Controller {
    private static Controller instance;
    /**
     * Actual state of the game
     */
    protected static GameState state;
    /**
     * Boolean variables describing user input
     */
    private boolean up, down, right, left, pause, resume, start;
    /**
     * Boolean block to prevent pressing keys too fast, so that the snake's head could turn around.
     * For example, when snake was moving left, pressing the up and right key very fast could just change the head's direction
     * to right, without changing the position in Y-axis, causing the head to hit the second part of it's body
     */
    private boolean keyActive;
    /**
     * The movement in X and Y-axis
     */
    private int dx, dy;
    /**
     * Variable to control snake's speed
     */
    private int speedConstraint;


    /**
     * Array to hold fruits
     */
    private ArrayList<Fruit> fruits;

    /**
     * Array to hold Obsticales
     */
    private ArrayList<Obstacle> obstacles;
    /**
     * Variable to hold Snake Object
     */
    private Snake snake;
    /**
     * Variable to hold Snake's Body Object
     */
    private BodyPart head;
    /**
     * Variable to hold Board Object
     */
    private Board board;
    /**
     * MediaPlayer object, controls the music played in game
     */
    private MediaPlayer audio;

    /**
     * Singletone
     *
     * @return instance
     */
    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();

        return instance;
    }

    //TODO
    /* initialize game parameters in controller not View
     * */
    public Controller() {
        state = GameState.Started;
        up = down = right = left = pause = resume = start = false;
        board = new Board();
        snake = board.getSnake();
        fruits = board.getFruits();
        keyActive = true;
        obstacles = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Obstacle obs = new Obstacle(10 + 20 * i, 10);
            obstacles.add(obs);
            obs = new Obstacle(10 + 20 * i, 590);
            obstacles.add(obs);
        }
        for (int i = 1; i < 29; i++) {
            Obstacle obs = new Obstacle(10, 10 + 20 * i);
            obstacles.add(obs);
            obs = new Obstacle(590, 10 + i * 20);
            obstacles.add(obs);
        }



    }


    /**
     * Method to handle snake's position and movement on board
     *
     * @param dx - movement in X-axis, 1 for right, -1 for left
     * @param dy - movement in Y-axis, 1 for down, -1 for up
     */
    public void move(int dx, int dy, Snake s) {
        BodyPart head = s.getHead();
        if (dx != 0 || dy != 0) { // if snake is meant to move

            // temporary variables to hold BodyParts
            BodyPart prev = new BodyPart(head.getX(), head.getY()), next = new BodyPart(head.getX(), head.getY());

            // move head in X-axis

            head.setX(head.getX() + (dx * GameObject.SIZE));


            // check if head didn't go beyond screen(>WIDTH or <0), if yes set it on the other side
            if (head.getX() > MainGameView.WIDTH) {
                head.setX(GameObject.SIZE / 2);
            } else if (head.getX() < 0) {
                head.setX(MainGameView.WIDTH - GameObject.SIZE / 2);
            }

            // move head in Y-axis

            head.setY(head.getY() + (dy * GameObject.SIZE));

            //System.out.printf(temp1+"->"+temp+" ");
            // check if head didn't go beyond screen(>HEIGHT or <0), if yes set it on the other side
            if (head.getY() > MainGameView.HEIGHT) {
                // for 2 points next to ScoreView panel
                if ((head.getX() == GameObject.SIZE / 2 || head.getX() == MainGameView.HEIGHT - GameObject.SIZE / 2) && head.getY() == MainGameView.HEIGHT + GameObject.SIZE / 2)
                    ;
                else
                    head.setY(GameObject.SIZE / 2);
            } else if (head.getY() < 0) {
                head.setY(MainGameView.HEIGHT - GameObject.SIZE / 2);
            }

            // moving the snake's body, each point gets the position of the one in front
            for (int i = 1; i < s.getSize(); ++i) {

                next.setX(s.getBodyPart(i).getX());
                next.setY(s.getBodyPart(i).getY());

                s.getBodyPart(i).setX(prev.getX());
                s.getBodyPart(i).setY(prev.getY());
                prev.setX(next.getX());
                prev.setY(next.getY());
            }
        }
    }


    public void checkCollision() {

        int headX, headY, helpX, helpY;


        headX = snake.getHead().getX();
        headY = snake.getHead().getY();

        // checks if snake hit itself
        for (int i = 1; i < snake.getSize(); ++i) {

            helpX = snake.getBodyPart(i).getX();
            helpY = snake.getBodyPart(i).getY();

            if (helpX == headX && helpY == headY) {
                //highscore = score;

                state =  GameState.Finished;
            }
        }
        // checks is snake hits obsticale
        for (int i = 0; i < obstacles.size(); ++i) {

            helpX = obstacles.get(i).getX();
            helpY = obstacles.get(i).getY();

            if (helpX == headX && helpY == headY) {
                // highscore = score;


                state = GameState.Finished;
            }
        }
    }


    /**
     * Method responsible for music in game
     */
    private void setSound() {

        if (state == GameState.Running)
            audio.play(); // play music or resume when it was paused, when game is running
        if (state == GameState.Paused)
            audio.pause(); // pause music when game is paused
        if (state == GameState.Finished)
            audio.stop(); // stop the music when game is finished
    }

    /**
     * Restarting the game by setting basic parameters to their primary values
     */
    public void restart() {
        state = GameState.Running;
        dx = dy = 0;
        up = down = left = right = false;
        speedConstraint = 3;

    }


    /**
     * Getters And Setters
     *
     * @return
     */
    public boolean[] getArguments() {
        boolean[] arguments = new boolean[8];

        arguments[0] = this.up;
        arguments[1] = this.down;
        arguments[2] = this.left;
        arguments[3] = this.right;
        arguments[4] = this.pause;
        arguments[5] = this.resume;
        arguments[6] = this.start;
        arguments[7] = this.keyActive;


        return arguments;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void setResume(boolean resume) {
        this.resume = resume;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void setKatActive(boolean keyActive) {
        this.keyActive = keyActive;
    }

    public int getDx() {
        return this.dx;
    }

    public int getDy() {
        return this.dy;
    }
    public Snake getSnake(){return this.snake;}
    public Board getBoard(){return this.board;}
    public ArrayList<Fruit> getFruits(){return this.fruits;}
    public ArrayList<Obstacle> getObstacles() { return obstacles;}
    public GameState gameState(){return state;}
}
