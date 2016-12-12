package main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.concurrent.CopyOnWriteArrayList;

public class Bot extends Avatar {
	Player player = new Player();

	private String BotXdirection = "";
	private String BotYdirection = "";
	public static String botState="";
	boolean active = true;
	long startSleepTime;
	int sleepingTime = 3;

	public void init(Player player) {
		this.BotXdirection = "";
		this.BotYdirection = "";
		this.player = player;
		this.speed = 3;
		this.X = (Main.width / 3) - (playerWidth / 3);
		this.Y = (Main.height / 3) - (playerHeight / 3);
	}

	public void update(CopyOnWriteArrayList<Block> blocks) {
		// Check is sleeping
		checkIfSleeping();
		// Change direction
		if (active)
			attackPlayer();

		// make player change its co-ordinates.
		moveBot();
	}

	private void checkIfSleeping() {
		// System.out.println(active);
		if (!active) {
			long elapsedTime = (System.currentTimeMillis() - startSleepTime) / 1000;
			// System.out.println("Elapsed time: " + elapsedTime);
			if ((int) elapsedTime > (int) sleepingTime) {
				active = true;
			}
		}
	}

	private void attackPlayer() {
		// Move towards player.
		setDirectionToPlayer();
		// Hit player
		tryToHitPlayer();
	}

	private void setDirectionToPlayer() {

		if (player.getX() < this.X){
			BotXdirection = "LEFT";
			botState = "faceleft";
		}
		if (player.getX() > this.X){
			BotXdirection = "RIGHT";
			botState = "faceright";
		}
		if (player.getY() < this.Y){
			BotYdirection = "UP";
			botState = "faceup";
		}
		if (player.getY() > this.Y){
			BotYdirection = "DOWN";
			botState = "facedown";
		}
	}

	private void tryToHitPlayer() {
		if (isTouching((int) player.getX(), (int) player.getY(), 60, 60)) {
			knockPlayer();
			stealGold();
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

	private void knockPlayer() {
		int power = 200;
		// Knock the player in the right direction.
		if (BotXdirection.equals("RIGHT"))
			player.setX((float) (player.getX() + power));
		if (BotXdirection.equals("LEFT"))
			player.setX((float) (player.getX() - power));
		if (BotXdirection.equals("DOWN"))
			player.setY((float) (player.getY() + power));
		if (BotXdirection.equals("UP"))
			player.setY((float) (player.getY() - power));

	}

	private void stealGold() {
		int goldTake = 20;
		if (player.getScore() > goldTake)
			player.setScore(player.getScore() - goldTake);
		else
			player.setScore(0);
	}

	private void makeInactive() {
		active = false;
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
	
	//Getters
	public String getBotXdirection(){
		return this.BotXdirection;
	}
	public String getBotYdirection(){
		return this.BotYdirection;
	}
	//Setters
	public void setBotXdirection(String dir){
		this.BotXdirection = dir;
	}
	public void setBotYdirection(String dir){
		this.BotYdirection = dir;
	}
}
