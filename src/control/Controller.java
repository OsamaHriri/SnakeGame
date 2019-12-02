package control;

import java.net.URISyntaxException;

import javafx.fxml.FXML;
import model.*;
import view.MainGameView;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Controller{
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
     *  Boolean block to prevent pressing keys too fast, so that the snake's head could turn around.
     For example, when snake was moving left, pressing the up and right key very fast could just change the head's direction
     to right, without changing the position in Y-axis, causing the head to hit the second part of it's body
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
     * Variable to change snake's speed at point intervals
     */
    private int speedPointsConstraint;
    private Snake snake;
    private BodyPart head;
    private MainGameView view;
    private Board board;
    /**
     * MediaPlayer object, controls the music played in game
     */
    private MediaPlayer audio;

    public static Controller getInstance()
    {
        if (instance == null)
            instance = new Controller();

        return instance;
    }


    public Controller() {
        state = GameState.Started;
        up = down = right = left = pause = resume = start = false;
        view = new MainGameView();

        snake = view.getSnake();
        head = snake.getHead();
        board = view.getBoard();
        keyActive = true;

       /* try {
            audio = new Sound().getAudio();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/

    }

    /**
     * Method to handle pressed keys on scene given as argument
     * @param scene on which events are performed
     */
    /*
    private void movement(Scene scene) {

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            public void	handle(KeyEvent e){

                switch(e.getCode()) {
                    case UP:
                        if(!down && keyActive && state == GameState.Running) {
                            up = true;
                            left = false;
                            right = false;
                            keyActive = false;
                        }
                        break;
                    case DOWN:
                        if(!up && keyActive && (left || right) && state == GameState.Running) {
                            down = true;
                            left = false;
                            right = false;
                            keyActive = false;
                        }
                        break;
                    case LEFT:
                        if(!right && keyActive && state == GameState.Running) {
                            left = true;
                            up = false;
                            down = false;
                            keyActive = false;
                        }
                        break;
                    case RIGHT:
                        if(!left && keyActive && state == GameState.Running) {
                            right = true;
                            up = false;
                            down = false;
                            keyActive = false;
                        }
                        break;
                    case SPACE: // pause or resume game
                        if(state == GameState.Running || state == GameState.Paused) {
                            if(pause == false) {
                                pause = true;
                                resume = false;
                            }
                            else {
                                resume = true;
                                pause = false;
                                resume();
                            }
                        }
                        break;
                    case ENTER:{ // start or restart the game
                        if(state == GameState.Started)
                            start = true;
                        if(state == GameState.Finished) {
                            start = true;
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
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            }
        });
    }

*/
    /**
     * Method to handle snake's position and movement on board
     * @param dx - movement in X-axis, 1 for right, -1 for left
     * @param dy - movement in Y-axis, 1 for down, -1 for up
     */
    public void move(int dx, int dy , Snake s) {
             BodyPart head = s.getHead();
        if(dx != 0 || dy != 0) { // if snake is meant to move

            // temporary variables to hold BodyParts
            BodyPart prev = new BodyPart(head.getX(), head.getY()), next = new BodyPart(head.getX(), head.getY());

            // move head in X-axis

            head.setX(head.getX()+(dx*GameObject.SIZE));


            // check if head didn't go beyond screen(>WIDTH or <0), if yes set it on the other side
            if(head.getX() > MainGameView.WIDTH) {
                head.setX(GameObject.SIZE/2);
            }
            else if(head.getX() < 0) {
                head.setX(MainGameView.WIDTH - GameObject.SIZE/2);
            }

            // move head in Y-axis

            head.setY(head.getY()+(dy*GameObject.SIZE));

            //System.out.printf(temp1+"->"+temp+" ");
            // check if head didn't go beyond screen(>HEIGHT or <0), if yes set it on the other side
            if(head.getY() > MainGameView.HEIGHT) {
                // for 2 points next to ScoreView panel
                if((head.getX() == GameObject.SIZE/2 || head.getX() == MainGameView.HEIGHT - GameObject.SIZE/2) && head.getY() == MainGameView.HEIGHT + GameObject.SIZE/2);
                else
                    head.setY(GameObject.SIZE/2);
            }
            else if(head.getY() < 0) {
                head.setY(MainGameView.HEIGHT - GameObject.SIZE/2);
            }

            // moving the snake's body, each point gets the position of the one in front
            for(int i = 1; i < s.getSize(); ++i) {

                next.setX( s.getBodyPart(i).getX());
                next.setY( s.getBodyPart(i).getY());

                s.getBodyPart(i).setX(prev.getX());
                s.getBodyPart(i).setY(prev.getY());
                prev.setX(next.getX());
                prev.setY(next.getY());
            }
        }
    }

    /**
     * The gameloop, handles user input, updates and renders the game
     */
    /*
    private void resume(){


        new AnimationTimer(){

            int i=0;
            @Override
            public void handle(long now) {

					/*show fps
					 updateFps();
					 System.out.println("FPS: " + fpsCurrent);
					*/

             /*  // when moving up
                if(up && !down) {

                    dy = -1;
                    dx = 0;
                }
                // when moving down
                if(!up && down) {

                    dy = 1 ;
                    dx = 0;
                }
                // when moving left
                if(left && !right) {

                    dy = 0;
                    dx = -1;
                }
                //when moving right
                if(right && !left) {
                    dy = 0;
                    dx = 1;
                }
                // when game paused
                if(pause && !resume) {
                    state = GameState.Paused;
                    view.render();
                    stop();
                }
                // when game resumed
                if(resume && !pause) {
                    state = GameState.Running;
                    resume = false;
                }
                // when game started or restarted
                if(start && (state == GameState.Finished || state == GameState.Started)) {
                    restart();
                    start = false;
                }
                // when game finished
                if(state == GameState.Finished) {
                    stop();
                }
                // when game is running, make movement
                if(state == GameState.Running) {
                    if(i==speedConstraint) { // control the speed of snake
                        move(dx, dy);
                        keyActive = true; // unlock possibility to press another key after snake made it's move
                        i=0; // counter to slow down the snake
                    }
                    ++i;
                }

                update(); // updating the game parameters, positions, etc.
                view.render(); // rendering the scene
                movement(view.getScene()); // handling user key input on actual scene
            }
        }.start(); // starting the timer

    }
    */

/* shows fps
	int frameCount = 0;
	int fpsCurrent = 0;
	long prevTime = -1;
	private void updateFps() {

	   frameCount++;

	   long currTime = System.currentTimeMillis();

	   if( currTime - prevTime >= 1000) {

	    fpsCurrent = frameCount;

	    prevTime = currTime;
	    frameCount = 0;
	   }
	}
*/
    /**
     * The update method
     */
    public void update() {

        board.updateFruit(); // updates the state of fruits
        board.checkEaten(); // check if a fruit has been eaten

        if(board.checkCollision() == GameState.Finished) { // check if a collision occurred
            state = GameState.Finished; //
        }
        //setSound(); // updating the sound

        // setting snake speed due to gathered points
        /*
        if(speedConstraint > 2 && board.getScore() >= speedPointsConstraint)
            speedConstraint = 2; 		   //snake will move faster
        if((speedConstraint == 2) && (board.getScore() - speedPointsConstraint) >= 10) {
            speedPointsConstraint += 30;  // next interval 30 points further
            speedConstraint = 3; 	   	  // back to original speed
        }*/
    }

    /**
     * Method responsible for music in game
     */
    private void setSound() {

        if(state == GameState.Running)
            audio.play(); // play music or resume when it was paused, when game is running
        if(state == GameState.Paused)
            audio.pause(); // pause music when game is paused
        if(state == GameState.Finished)
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
        speedPointsConstraint = 50;
    }

    /**
     * Static method for returning the actual state of game for the Model and View classes
     * @return Actual state of the game
     */
    public static GameState getState() {
        return state;
    }

    /**
     * Returns the stage, pass it to Main class
     * @return The game stage
     */
    public Stage getStage() {
        return view.getStage();
    }
   public boolean[] getArguments(){
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

    public void setUp(boolean up){this.up = up;}
    public void setDown(boolean down){this.down = down;}
    public void setLeft(boolean left){this.left = left;}
    public void setRight(boolean right){this.right = right;}
    public void setPause(boolean pause){this.pause = pause;}
    public void setResume(boolean resume){this.resume = resume;}
    public void setStart(boolean start){this.start = start;}
    public void setKatActive(boolean keyActive){this.keyActive = keyActive;}
    public int getDx(){return this.dx;}
    public int getDy(){return this.dy;}




}
