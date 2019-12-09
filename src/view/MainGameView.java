package view;

import java.lang.reflect.Array;
import java.util.ArrayList;

import control.Controller;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import model.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGameView {

    /**
     * virable to hold Controller instane
     */
    private Controller control;
    /**
     * Variable to control snake's speed
     */
    private int speedConstraint = 3;
    /**
     * Width of the game board
     */
    public final static int WIDTH = 600;
    /**
     * Height of the game board
     */
    public final static int HEIGHT = 600;

    /**
     * Snake object
     */
    private Snake snake;
    /**
     * Object to hold the actual scene
     */
    private Scene scene;
    /**
     * The stage
     */
    private Stage stage;
    /**
     * Actual state of the game
     */
    private GameState state;
    /**
     * Array to hold fruits passed by board class
     */
    private ArrayList<Fruit> fruits;
    /**
     * Object that holds the super fruit by board class
     */

    /**
     * Array of obstacles
     */
    ArrayList<Obstacle> obstacles;
    /**
     * Board object
     */
    private Board board;
    private Group g;
    @FXML
    private Pane canvas;
    private Color bodyColor;

    /**
     * Default constructor initializes the basic variables and objects
     */
    public MainGameView() {

        control = Controller.getInstance();
        stage = Game.stage;
        scene = Game.scene;
        state = GameState.Started;
        initializeVariables();

        /* try {
            audio = new Sound().getAudio();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/


    }


    /**
     * The render method, that displays the graphics
     */
    public void render() {

             state = control.gameState();

        switch (state) { // checks for actual game state

            case Started:    // if game state is Started display the starting screen
                whenStarted();
                break;
            case Running:
                whenRunning(); // if Running show the board, snake, objects, etc.
                break;
            case Paused: // if Paused show the pause screen
                whenPaused();
                break;
            case Finished: // if Finished show the ending game screen and display the score
                whenFinished();
                break;
            default:
                break;
        }
    }

    /**
     * Method to get the scene
     *
     * @return Returns the actual scene
     */
    public Scene getScene() {
        return stage.getScene();
    }

    /**
     * Method to get the stage
     *
     * @return Returns the game stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Method for displaying starting screen
     */
    private void whenStarted() {


    }

    /**
     * Main render method to display the actual running game
     */
    private void whenRunning() {

        //grid.getChildren().clear(); // clear grid
        canvas.getChildren().clear(); // clear canvas


        int helpX, helpY, snakeY, snakeX; // variables for loops

        // snake's head to canvas
        Circle c = new Circle(snake.getHead().getX(), snake.getHead().getY(), GameObject.SIZE / 2);
        c.setFill(BodyPart.HEAD_COLOR);
        canvas.getChildren().add(c);
        bodyColor = BodyPart.BODY_COLOR;


        // snake's body to canvas
        for (int i = 1; i < snake.getSize(); ++i) {
            snakeX = snake.getBodyPart(i).getX();
            snakeY = snake.getBodyPart(i).getY();
            c = new Circle(snakeX, snakeY, GameObject.SIZE / 2);
            c.setFill(bodyColor);
            canvas.getChildren().add(c);
            ;

        }

        // loading fruits to canvas


        for (int i = 0; i < fruits.size(); ++i) {
            helpX = fruits.get(i).getX();
            helpY = fruits.get(i).getY();
            c = new Circle(helpX, helpY, GameObject.SIZE / 2);
            c.setFill(Fruit.FRUIT_COLOR);
            canvas.getChildren().add(c);
        }
        // loading the super fruit to canvas


        for (int i = 0; i < obstacles.size(); ++i) {
            helpX = obstacles.get(i).getX();
            helpY = obstacles.get(i).getY();
            Rectangle r = new Rectangle(helpX - (GameObject.SIZE / 2), helpY - (GameObject.SIZE / 2), GameObject.SIZE, GameObject.SIZE);

            r.setFill(Obstacle.OBSTACLE_COLOR);
            canvas.getChildren().add(r);
        }
        // adding canvas that holds the game objects, and stack that holds the score display, together

        //  grid.add(stack, 0, 1);
        //  grid.add(canvas, 0, 0);
        Scene s = canvas.getScene();
        s.setRoot(canvas);
        Game.stage.setScene(s);

    }

    /**
     * Method invoked when game is paused
     */
    private void whenPaused() {

        g = new Group();
        Circle c1, c2;
        Rectangle r;
        Text largeText, smallText, t1, t2, t3, t4;

        largeText = new Text(WIDTH / 2 - 190, HEIGHT / 2 - 30, "Game Paused");
        largeText.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 50));
        smallText = new Text(WIDTH / 2 - 190, HEIGHT / 2 + 30, "Press SPACE to resume");
        smallText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        smallText.setFill(Color.DARKGREEN);

        c1 = new Circle(WIDTH / 2 - 200, HEIGHT / 2 + 150, GameObject.SIZE / 2, Fruit.FRUIT_COLOR);


        t1 = new Text(WIDTH / 2 - 180, HEIGHT / 2 + 154, "- normal fruit for 1 point");
        t1.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 13));
        t2 = new Text(WIDTH / 2 - 180, HEIGHT / 2 + 194, "- super fruit gives 3 points and puts Snake into super state");
        t2.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 13));
        t3 = new Text(WIDTH / 2 - 180, HEIGHT / 2 + 234, "- obstacle, hitting it ends game");
        t3.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 13));
        t4 = new Text(WIDTH / 2 - 270, HEIGHT / 2 + 270, "Super state - normal fruits give 2 points and snake is immune to obstacles");
        t4.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 13));

        g.getChildren().addAll(smallText, largeText, c1, t1, t2, t3, t4);
        scene.setRoot(g);
        stage.setScene(scene);
    }

    /**
     * Shows the finish screen when game is ended
     */
    private void whenFinished() {
        System.out.print(state);
        g = new Group();
        Text largeText = new Text(WIDTH / 2 - 220, HEIGHT / 2 - 60, "Game Over");
        largeText.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 70));
        largeText.setFill(Color.RED);
        Text largeText2 = new Text(WIDTH / 2 - 170, HEIGHT / 2 + 20, "FINAL SCORE: " + board.getHighScore());
        largeText2.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 37));
        Text smallText = new Text(WIDTH / 2 - 160, HEIGHT / 2 + 100, "Press ENTER to replay");
        smallText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 25));
        smallText.setFill(Color.DARKGREEN);
        Text smallText2 = new Text(WIDTH / 2 - 130, HEIGHT / 2 + 130, " or ESCAPE to exit");
        smallText2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 25));
        smallText2.setFill(Color.DARKGREEN);
        g.getChildren().addAll(smallText, largeText2, smallText2, largeText);
        Scene s = canvas.getScene();
        s.setRoot(g);
        Game.stage.setScene(s);

    }


    private int frameCount = 0;
    private int fpsCurrent = 0;
    private long prevTime = -1;

    private void updateFps() {

        frameCount++;

        long currTime = System.currentTimeMillis();

        if (currTime - prevTime >= 1000) {

            fpsCurrent = frameCount;

            prevTime = currTime;
            frameCount = 0;
        }
    }
    /**
     * Method to handle pressed keys on scene given as argument
     * @param e is the key pressed on screen
     */

    @FXML
    public void movmentHandler(KeyEvent e) {
        boolean[] arguments = Controller.getInstance().getArguments();
        boolean up = arguments[0];
        boolean down = arguments[1];
        boolean left = arguments[2];
        boolean right = arguments[3];
        boolean pause = arguments[4];
        boolean keyActive = arguments[7];


        switch (e.getCode()) {

            case UP:

                if (!down && keyActive && state == GameState.Running) {
                    Controller.getInstance().setUp(true);
                    Controller.getInstance().setLeft(false);
                    Controller.getInstance().setRight(false);
                    Controller.getInstance().setKatActive(false);


                }
                break;
            case DOWN:
                if (!up && keyActive && (left || right) && state == GameState.Running) {
                    Controller.getInstance().setDown(true);
                    Controller.getInstance().setLeft(false);
                    Controller.getInstance().setRight(false);
                    Controller.getInstance().setKatActive(false);
                }
                break;
            case LEFT:
                if (!right && keyActive && state == GameState.Running) {
                    Controller.getInstance().setLeft(true);
                    Controller.getInstance().setUp(false);
                    Controller.getInstance().setDown(false);
                    Controller.getInstance().setKatActive(false);
                }
                break;
            case RIGHT:
                if (!left && keyActive && state == GameState.Running) {
                    Controller.getInstance().setRight(true);
                    Controller.getInstance().setUp(false);
                    Controller.getInstance().setDown(false);
                    Controller.getInstance().setKatActive(false);
                }
                break;
            case SPACE: // pause or resume game
                if (state == GameState.Running || state == GameState.Paused) {
                    if (pause == false) {
                        Controller.getInstance().setPause(true);
                        Controller.getInstance().setResume(false);
                    } else {
                        Controller.getInstance().setPause(false);
                        Controller.getInstance().setResume(true);
                        resume();
                    }
                }
                break;
            case ENTER: { // start or restart the game
                if (state == GameState.Started)
                    Controller.getInstance().setStart(true);
                resume();
                if (state == GameState.Finished) {
                    Controller.getInstance().setStart(true);
                    resume();
                }
            }
            break;
            case ESCAPE: // exit program
                System.exit(0);
                break;
            default:
                break;
        }
    }
    /**
     * The gameloop, handles user input, updates and renders the game
     */
    private void resume() {

        new AnimationTimer() {

            int i = 0;

            @Override
            public void handle(long now) {

                int dy = control.getDy();
                int dx = control.getDx();
                boolean[] arguments = control.getArguments();
                boolean up = arguments[0];
                boolean down = arguments[1];
                boolean left = arguments[2];
                boolean right = arguments[3];
                boolean pause = arguments[4];
                boolean resume = arguments[5];
                boolean start = arguments[6];


/*
					 updateFps();
					 System.out.println("FPS: " + fpsCurrent);

*/
                // when moving up
                if (up && !down) {

                    dy = -1;
                    dx = 0;
                }
                // when moving down
                if (!up && down) {

                    dy = 1;
                    dx = 0;
                }
                // when moving left
                if (left && !right) {

                    dy = 0;
                    dx = -1;
                }
                //when moving right
                if (right && !left) {
                    dy = 0;
                    dx = 1;
                }
                // when game paused
                if (pause && !resume) {
                    state = GameState.Paused;
                    render();
                    stop();
                }
                // when game resumed
                if (resume && !pause) {
                    state = GameState.Running;
                    resume = false;
                }
                // when game started or restarted
                if (start && (state == GameState.Finished || state == GameState.Started)) {
                    control.restart();
                    start = false;
                }
                // when game finished
                if (state == GameState.Finished) {
                    System.out.print(state);
                    stop();
                }
                // when game is running, make movement
                if (state == GameState.Running) {
                    if (i == 5) {
                        Controller.getInstance().move(dx, dy, snake);
                        render();
                        Controller.getInstance().setKatActive(true); // unlock possibility to press another key after snake made it's move
                        i = 0;
                    }
                    ++i;
                }
                update(); // updating the game parameters, positions, etc.
                render(); // rendering the scene

            }
        }.start(); // starting the timer

    }

    public void update() {

        board.updateFruit(); // updates the state of fruits
        board.checkEaten(); // check if a fruit has been eaten
        initializeVariables();// update variables
        control.checkCollision();//check for collision with snake or obsticlae

        //setSound(); // updating the sound

    }

    private void initializeVariables() {
        this.snake = control.getSnake();
        this.obstacles = control.getObstacles();
        this.board = control.getBoard();
        this.fruits = control.getFruits();

    }

}
