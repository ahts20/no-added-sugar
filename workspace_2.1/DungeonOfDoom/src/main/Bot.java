package main;

import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

public class Bot extends Avatar {
	/**
	 * In game an instance of this class will work as an NPC.
	 * It chases after the closest player, pushes them and reduces 
	 * their score by 20.
	 * 
	 * @author James
	 * @see Avatar.
	 * @see Player1.
	 * @see Player2.
	 * 
	 * Called by:
	 * @see World.java.
	 */
	
	
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
		 * 
		 * 
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
		// System.out.println(active);
		if (!getActive()) {
			long elapsedTime = (System.currentTimeMillis() - startSleepTime) / 1000;
			// System.out.println("Elapsed time: " + elapsedTime);
			if ((int) elapsedTime > (int) sleepingTime) {
				setActive(true);
			}
		}
	}

	private void attackPlayer() {
		// Move towards player.
		Player closest = setDirectionToPlayer();
		// Hit player
		tryToHitPlayer(closest);
	}

	private Player setDirectionToPlayer() {
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
		double dist = calculateDist(player1.getX(), player1.getY(), this.X, this.Y);
		double dist2 = calculateDist(player2.getX(), player2.getY(), this.X, this.Y);
		if (dist < dist2)
				return player1;
		return player2;
	}

	private double calculateDist(double x, double y, float x2, float y2) {
		double X = Math.abs(x - x2);
		double Y = Math.abs(y - y2);
		double dist = Math.pow(X, 2);
		dist += Math.pow(Y, 2);
		return dist;
	}

	private void tryToHitPlayer(Player closest) {
		if (isTouching((int) closest.getX(), (int) closest.getY(), 60, 60)) {
			knockPlayer(closest);
			stealGold(closest);
			makeInactive();
		}
	}

	protected boolean isTouching(int x, int y, int width, int height) {
		/**
		 * Buggy for some reason in Avatar. Adjusted for smaller visible section
		 * of image.
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
		int power = 100;
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
		if (closest.getScore() > goldTake)
			closest.setScore(closest.getScore() - goldTake);
		else
			closest.setScore(0);
	}

	private void makeInactive() {
		setActive(false);
		BotXdirection = "";
		BotYdirection = "";
		startSleepTime = System.currentTimeMillis();
	}

	private void moveBot() {
		if (BotXdirection.equals("LEFT"))
			X -= speed;
		if (BotXdirection.equals("RIGHT"))
			X += speed;
		if (BotYdirection.equals("UP"))
			Y -= speed;
		if (BotYdirection.equals("DOWN"))
			Y += speed;
	}

	public void render(Graphics g, int i) {
		// g.drawRect((int) this.X, (int) Y, this.playerWidth, this.playerHeight);
		g.drawImage(this.p[i], (int) this.X, (int) this.Y, null);
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
