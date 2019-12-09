package model;

import control.Controller;
import view.MainGameView;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Board {

	/**
	 * Number of GameObjects to store in X-axis
	 */
	private static final int BWIDTH = MainGameView.WIDTH/GameObject.SIZE;
	/**
	 * Number of GameObjects to store in Y-axis
	 */
	private static final int BHEIGHT = MainGameView.HEIGHT/GameObject.SIZE;
	/**
	 * List of fruits
	 */
	private ArrayList<Fruit> fruits;

	/**
	 * Score value
	 */
	private int score, highscore, fruitsEaten;
	/**
	 * Snake object
	 */
	private Snake snake; 
	/**
	 * Snake's head
	 */
	private BodyPart head; 
	/**
	 * Random number for generating points to place objects on them
	 */
	Random rand;
	/**
	 * State of the game
	 */
	private GameState state;

	/**
	 * Default constructor of board class to initialize starting variables
	 */
	public Board() {


		fruits = new ArrayList<>();

		score = fruitsEaten = 0;
		snake = new Snake();
		rand = new Random();
		head = snake.getHead();
		state = GameState.Started;


		
	}
	
	/**
	 * Method to check if an collision occurred, either of the snake head with it's body or with an obstacle on the board
	 * @return Returns the finished state of game
	 */

	/**
	 * Method called in controller to update the state of obstacles in the game

	public void updateObstacles() {
		
		if(fruitsEaten % 10 == 0 && fruitsEaten != 0 && !addObstacle) {	// place an obstacle each 10 points
			addObstacle = true; // Possible to add obstacle, also ensures only one is added
			obstaclesNumber++; // Now we can have one obstacle more on board
		}
		while(obstacles.size() < obstaclesNumber && addObstacle) {	//placing possible amount of obstacles
			placeObstacles();
		}
	}
	*/
	/**
	 * Looks for a point on the board to add new obstacle. Checks it to not collide with the snake body or fruits 

	private void placeObstacles() {
		
		int obstacleX = 0 , obstacleY = 0, helpX, helpY;	
		int headX = snake.getHead().getX(), headY = snake.getHead().getY();
		boolean collision = true, helpS, helpF;		//helpS if doesn't collide with snake, helpF for fruit

		while(collision) {
			
			helpS = helpF = false;
			obstacleX = (rand.nextInt(BWIDTH)*GameObject.SIZE)+GameObject.SIZE/2; // random point on board
			obstacleY = (rand.nextInt(BHEIGHT)*GameObject.SIZE)+GameObject.SIZE/2;
		
			for(int i = 0; i < snake.getSize(); ++i) {	// to not collide with snake
				
				helpX = snake.getBodyPart(i).getX();
				helpY = snake.getBodyPart(i).getY();
	
				// if collides, start while again and generate new point
				if(helpX == obstacleX && helpY == obstacleY) {
					break;
				}
				
				// leave 4 places in the row in front of head snake, so it doesn't bump up and block the road
				if(obstacleX == headX) { 
					if(Math.abs(obstacleY - headY) < 4 * GameObject.SIZE) {
						break;
					}
				}
				else if(obstacleY == headY) {
					if(Math.abs(obstacleX - headX) < 4 * GameObject.SIZE) {
						break;
					}
				}
				
				// if doesn't collide with any snake part, go over to check fruits
				if(i == snake.getSize() - 1) {
					helpS = true;
				}
			}
					
			if(helpS) {	// to not collide with fruits
				
				// if there are no fruits on the field
				if(fruits.size() == 0) {
					helpF = true;
				}
				else {
					
					for(int i = 0; i < fruits.size(); ++i) {
						
						helpX = fruits.get(i).getX();
						helpY = fruits.get(i).getY();
			
						// back to while to generate new point, and check everything again
						if(helpX == obstacleX && helpY == obstacleY) {
							break;
						}
						
						// doesn't collide with fruits
						if(i == fruits.size() - 1) {
							helpF = true;
						}	
					}
				}
				// if there's a super fruit on board, check to not collide with it
				if(isSuper) {
					if(obstacleX == sFruit.getX() && obstacleY == sFruit.getY()) {
						continue;
					}
				}
				
				// point for obstacle doesn't collide with any snake part or fruit
				if(helpF) {
					collision = false;
				}
			}
		}
		// add new obstacle
		addObstacle(obstacleX, obstacleY);
	}
	*/
	/**
	 * Add new obstacle to array
	 * @param X coordinate
	 * @param Y coordinate

	private void addObstacle(int X, int Y) {
		obstacles.add(new Obstacle(X, Y));
	}
	*/
	/**
	 * Method to check if snake ate a fruit
	 */
	public void checkEaten() {
		
		int headX, headY, foodX, foodY;
		headX = head.getX();
		headY = head.getY();
		// checks if it's the super fruit
		/*if(isSuper) {
		
			if(sFruit.getX() == headX && sFruit.getY() == headY) {
				removeSuperFruit();
				++fruitsEaten;
				score+=3;
				superState = true;
				timeSFruit.stop();
				if(timeSuper != null) {
					timeSuper.stop();
				}
				timeSuper = new Timeline(new KeyFrame(Duration.millis(SuperFruit.SUPER_STATE_TIME), lambda->superState=false));
				timeSuper.play();
				addObstacle = false; // unlock possibility to add another obstacle
				return;
			}
		}*/
		// check for a fruit on board 	
		for(int i = 0; i < fruits.size(); ++i){
			
			foodX = fruits.get(i).getX();
			foodY = fruits.get(i).getY();
			
			if(foodX == headX && foodY == headY) {
				
				removeFruit(i);
				addLength(); // adds body part to snake
				++fruitsEaten;
				++score;


		

			}		
		}
	}
	
	/**
	 * Method for updating fruits on board
	 */
	public void updateFruit() {
		
		int foodX = 0, foodY = 0; // foodX, foodY - coordinates for normal fruit, with s for super
		int []place; // place on board, will hold X and Y

		if(fruits.size() <= 0) { // if there's no fruit

			place = placeFruit();
			foodX = place[0];
			foodY = place[1];

			addFruit(foodX, foodY);
		}
	}
	
	/**
	 * Method to place a fruit on the board
	 * @return Returns point(X,Y) on board
	 */
	private int[] placeFruit() {
		
		int []point = new int[2];
		
		int helpX, helpY, foodX = 0, foodY = 0;
		boolean helpS,helpO;	// for Snake and Obstacles
		boolean collision = true;
		ArrayList<Obstacle> obstacles = Controller.getInstance().getObstacles();

		while(collision) {
				
			helpS =helpO= false;
			foodX = (rand.nextInt(BWIDTH)*GameObject.SIZE)+GameObject.SIZE/2;
			foodY = (rand.nextInt(BHEIGHT)*GameObject.SIZE)+GameObject.SIZE/2;
				
			for(int i = 0; i < snake.getSize(); ++i) {
					
				helpX = snake.getBodyPart(i).getX();
				helpY = snake.getBodyPart(i).getY();
	
				if(helpX == foodX && helpY == foodY) {
					break;
				}
						
				if(i == snake.getSize() - 1) {
					helpS = true;
				}
			}

			if(helpS) {



					for(int i = 0; i < obstacles.size(); ++i) {

						helpX = obstacles.get(i).getX();
						helpY = obstacles.get(i).getY();

						if(foodX == helpX && foodY == helpY) {
							break;
						}

						if(i == obstacles.size() - 1) {
							helpO = true;
						}
					}

				if(helpO) {
					collision = false;
				}
			}
		}
		point[0] = foodX;
		point[1] = foodY;
		return point;	
	}
	
	/**
	 * Method to generate a new fruit in the game(2 if it's time for the super-fruit)
	 * @param foodX X coordinate of normal fruit
	 * @param foodY	Y coordinate of normal fruit

	 */
	public void addFruit(int foodX, int foodY) {
		

		fruits.add(new Fruit(foodX, foodY)); // add new fruit to fruit array
	}
	
	/**
	 * Method to remove a normal fruit from the board, after being eaten by snake
	 * @param i Position of the fruit in array list
	 */
	public void removeFruit(int i) {
		fruits.remove(i);
	}
	

	

	
	/**
	 * Add new part to snake's body after eating a fruit
	 */
	public void addLength() {
		BodyPart b1 = snake.getBodyPart(snake.getSize()-1), b2 = snake.getBodyPart(snake.getSize()-2);
		if(b1.getX() > b2.getX())
			snake.addBodyPart(b1.getX()+GameObject.SIZE, b1.getY());
		else if(b1.getX() < b2.getX())
			snake.addBodyPart(b1.getX()-GameObject.SIZE, b1.getY());
		else if(b1.getY() >= b2.getY())
			snake.addBodyPart(b1.getX(), b1.getY()+GameObject.SIZE);
		else if(b1.getY() >= b2.getY())
			snake.addBodyPart(b1.getX(), b1.getY()-GameObject.SIZE);
	}
	
	/**
	 * Resets basic values of the game after lose
	 */
	private void reset() {
		snake.setStart();

		fruits.clear();
		score = fruitsEaten = 0;

	}
	
	/**
	 * Returns fruits
	 * @return Array with fruits
	 */
	public ArrayList<Fruit> getFruits(){
		return fruits;
	}
	


	/**
	 * Returns the snake
	 * @return Snake object
	 */
	public Snake getSnake() {
		return snake;
	}
	

	
	/**
	 * Returns the actual score
	 * @return Value of score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Returns the highscore when game finished
	 * @return Value of final score
	 */
	public int getHighScore() {
		return highscore;
	}
	
	/**
	 * Returns the actual state of the game
	 * @return Value of GameState
	 */
	public GameState getState() {
		return state;
	}
	

}