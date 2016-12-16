package MovableObjects;

import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

import Generators.Block;
/**
 * In game an instance of this class will work as an NPC.
 * It chases after the closest player, pushes them and reduces 
 * their score by 20.
 * 
 * @author anonymous
 * @see Avatar.
 * @see Player1.
 * @see Player2.
 * 
 * Called by:
 * @see World.java.
 */
public class Bot extends Avatar {
	
	Player player1 = new Player1();
	Player player2 = new Player1();
	
	public final int goldTake = 20;
	private String BotXdirection = "";
	private String BotYdirection = "";
	public static String botState="";
	private boolean active = true;
	long startSleepTime;
	int sleepingTime = 3;

	public void init(Player player1, Player player2, float X, float Y) {
		/**
		 * This method is used to set variables and pointers to allow the
		 * class to interact with other classes in the game.
		 * 
		 * Called by:
		 * 	@see World.java
		 * 
		 * @param player1
		 * 	The player1 instance passed in from the class World.java.
		 * 	This allows the bot class to reference this player class so it can 
		 * 	work out distances, and influence the correct player.
		 * 
		 * @param player2
		 * 	The player2 instance passed in from the class World.java.
		 * 	This allows the bot class to reference this player class so it can 
		 * 	work out distances, and influence the correct player.
		 * 
		 * @param X
		 * 	A variable passed in to set the Bot's X starting coordinate.
		 * 
		 * @param Y
		 * 	A variable passed in to set the Bot's Y starting coordinate.
		 */
		this.player1 = player1;
		this.player2 = player2;
		this.speed = 3;
		this.X = X;
		this.Y = Y;
	}

	public void update(CopyOnWriteArrayList<Block> blocks) {
		/**
		 * This method coordinates the Bot's in game logic.
		 * The bot spends time inactive after attacking a player
		 * so it starts by checking to see if it can be active.
		 * Then if the bot is active it sets its direction to the closest
		 * player and moves in that direction, it also attempts to attack
		 * - which is successful if the player is touching the bot..
		 * 
		 * @param blocks
		 * 	This is passed in from World.java class. It is
		 * 	an array list of all blocks in the game. 
		 * 
		 * @see World.java
		 * 	Uses this method to update Bot's logic.		 * 
		 */

		// Check is sleeping
		checkIfSleeping();
		// Change direction
		if (getActive())
			attackPlayer();

		// make player change its co-ordinates.
		moveBot();
	}

	private void checkIfSleeping() {
		/**
		 * This method sets the bot's active attribute to be
		 * true is enough time has passed since being inactive.
		 * 
		 * @see update().
		 * 	Calls this method to check if bot can become active again.
		 */

		if (!getActive()) {
			long elapsedTime = (System.currentTimeMillis() - startSleepTime) / 1000;
			// System.out.println("Elapsed time: " + elapsedTime);
			if ((int) elapsedTime > (int) sleepingTime) {
				setActive(true);
			}
		}
	}

	private void attackPlayer() {
		/**
		 * This method identifies the closest player
		 * and tries to hit it.
		 * 
		 * @see setDirectionToPlayer()
		 * 	This method returns the closest player to the bot.
		 * @see tryToHitPlayer()
		 * 	This method will knock the player, steal 20 score points
		 * 	and will make the bot inactive.
		 */

		Player closest = setDirectionToPlayer();
		tryToHitPlayer(closest);
	}

	private Player setDirectionToPlayer() {
		/**
		 * This method sets the bot's BotXdirection and
		 * BotYdirection variables to the closest player's location.
		 * It also sets the state correctly so the corresponding image
		 * can be selected.
		 * 
		 * @see findClosestPlayer()
		 * 	This method uses the class to identify the closest player.
		 * 
		 * @see attackPlayer()
		 * 	The current method is called by this method.
		 * 
		 * @return closest
		 * 	Returns the object of the closest player to the bot's
		 * 	location.
		 */

		Player closest = findClosestPlayer();
		
		if (closest.getX() < this.X){
			BotXdirection = "LEFT";
			botState = "faceleft";
		}
		if (closest.getX() > this.X){
			BotXdirection = "RIGHT";
			botState = "faceright";
		}
		if (closest.getY() < this.Y){
			BotYdirection = "UP";
			botState = "faceup";
		}
		if (closest.getY() > this.Y){
			BotYdirection = "DOWN";
			botState = "facedown";
		}
		return closest;
	}

	private Player findClosestPlayer() {
		/**
		 * This method uses the calculateDist() method to calculate the
		 * distance between each player and the bot.
		 * 
		 * @return player1, player2
		 * 	This method returns the closest player instance to the bots
		 * 	location.
		 */

		double dist = calculateDist(player1.getX(), player1.getY(), this.X, this.Y);
		double dist2 = calculateDist(player2.getX(), player2.getY(), this.X, this.Y);
		if (dist < dist2)
				return player1;
		return player2;
	}

	private void tryToHitPlayer(Player closest) {
		/**
		 * This method coordinates the bot's hit functionality.
		 * If the bot is in contact with the closest player then
		 * it will knock the player, remove 20 score points, and it will
		 * then make the bot inactive (until woken up after 3 seconds).
		 * 
		 * @see isTouching()
		 * 	Used to determing if the bot is touching the closest player.
		 * 
		 * @see knockPlayer()
		 * 	Used to move the player.
		 * 
		 * @see stealGold()
		 * 	Used to remove gold/score from the target player.
		 * 
		 * @see makeInactive()
		 * 	Used to make the current bot inactive.
		 * 
		 */

		if (isTouching((int) closest.getX(), (int) closest.getY(), 60, 60)) {
			knockPlayer(closest);
			stealGold(closest);
			makeInactive();
		}
	}

	protected boolean isTouching(int x, int y, int width, int height) {
		/**
		 * This method also exists in the Avatar class, although java is behaving in a weird way
		 * when this class tries to use it (other inherited methods work perfectly).
		 * 
		 * This method checks to see if the X-Y coordinates overlap (with reference to height and width),
		 * to determine if their areas overlap.
		 * 
		 * @param x
		 * 	This is the x coordinate.
		 * @param y
		 * 	This is the y coordinate.
		 * @param width
		 * 	This is the width of the object.
		 * @param height
		 * 	This is the height of the object.
		 * 
		 * @return true/false
		 * 	This method returns true if the arguments overlap with the current object's area.
		 * 	This method returns false if the arguments overlap with the current object's area.
		 */


		// Checking current in target space
		if (this.X >= x && this.X <= x + width) {
			if (this.Y >= y && this.Y <= y + height) {
				return true;
			}
		}
		// Checking target in current space.
		if (x >= this.X && x <= this.X + this.width) {
			if (y >= this.Y && y <= this.Y + this.height) {
				return true;
			}
		}
		return false;
	}

	private void knockPlayer(Player closest) {
		/**
		 * This method determines the bot's direction and 
		 * calls the player's method to get knocked in the appropriate 
		 * direction.
		 * 
		 * @power 
		 * 	This variable sets the distance to knock the player.
		 * 
		 * @see tryToHitPlayer()
		 * 	Calls the current method.
		 */

		int power = 50;
		// Knock the player in the right direction.
		if (BotXdirection.equals("RIGHT"))
			closest.getKnocked(power, BotXdirection);
		if (BotXdirection.equals("LEFT"))
			closest.getKnocked(power, BotXdirection);
		if (BotYdirection.equals("DOWN"))
			closest.getKnocked(power, BotYdirection);
		if (BotYdirection.equals("UP"))
			closest.getKnocked(power, BotYdirection);

	}

	private void stealGold(Player closest) {
		/**
		 * This method coordinates the stealing gold function of
		 * the bot.
		 * 
		 * @param closest
		 * 	This is the player instance which the method takes the
		 * 	gold from. 
		 * 	In the context it is used, it was named closest because it
		 * 	is the closest player instance to the bot, and hence the one 
		 * 	which will have its gold taken.
		 * 
		 * @see tryToHitPlayer()
		 * 	Calls the current method.
		 * 
		 * @see .getScore()
		 * 	Getter which returns the player instance score.
		 * 
		 * @see .setScore()
		 * 	Setter which sets the player instance score.
		 */

		if (closest.getScore() > goldTake)
			closest.setScore(closest.getScore() - goldTake);
		else
			closest.setScore(0);
	}

	private void makeInactive() {
		/**
		 * When called this method sets the bot's active attribute to false
		 * and removes all directions so it will not move.
		 * The attribute stays false until 3 seconds have elapsed. During this time
		 * period the bot will not chase the players and will not attempt to hit them.
		 * 
		 * After this time period the bot will become active again.
		 * 
		 * @see tryToHitPlayer()
		 * 	This method calls the current method.
		 * 
		 * @see setActive()
		 * 	The current method uses this method to set the bot's active attribute to false.
		 *  
		 */
		setActive(false);
		BotXdirection = "";
		BotYdirection = "";
		startSleepTime = System.currentTimeMillis();
	}

	private void moveBot() {
		/**
		 * This method changes the bot's coordinates dependent
		 * on the bot's direction variable.
		 * 
		 * @see update()
		 * 	This method calls the current method.
		 */
		if (BotXdirection.equals("LEFT"))
			X -= speed;
		if (BotXdirection.equals("RIGHT"))
			X += speed;
		if (BotYdirection.equals("UP"))
			Y -= speed;
		if (BotYdirection.equals("DOWN"))
			Y += speed;
	}

	public void render(Graphics g) {
		/**
		 * This method draws the bot's relevant image file and coordinates to the 
		 * graphics screen.
		 * 
		 * @param g
		 * 	This param is the graphics image which can be drawn to, to display the screen.
		 * 
		 * @param i
		 * 	This variable dictates which of the bot's images to display, which is determined
		 * 	by the bot's direction.
		 * 	
		 * @see World.java
		 * 	This class calls the render function of the bot.
		 * 
		 */
		int i = 0;
		if (botState == "facedown") {
			i = 6;
		} else if (botState == "faceleft") {
			i = 4;
		} else if (botState == "faceright") {
			i = 5;
		} else if (botState == "faceup") {
			i = 7;
		}
		g.drawImage(Avatar.p[i], (int) this.X, (int) this.Y, null);
	}
		public boolean getActive() {
		return active;
	}
	//Getters
	public String getBotXdirection(){
		return BotXdirection;
	}
	public String getBotYdirection(){
		return BotYdirection;
	}
	//Setters
	public void setBotXdirection(String dir){
		BotXdirection = dir;
	}
	public void setBotYdirection(String dir){
		BotYdirection = dir;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
