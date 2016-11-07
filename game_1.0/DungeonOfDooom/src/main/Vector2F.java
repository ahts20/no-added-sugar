package main;

public class Vector2F {
	//This class collects contains coordinates of an object and can also calculate distances between objects. 
	public float xpos;
	public float ypos;
	
	public static float worldXpos;
	public static float worldYpos;
	
	public Vector2F(){
		this.xpos = 0.0f;
		this.ypos = 0.0f;
	}
	
	public Vector2F(float xpos, float ypos){
		this.xpos = xpos;
		this.ypos = ypos;
	}
	
	public Vector2F getScreenLocation(){
		return new Vector2F(xpos, ypos);
	}
	
	public Vector2F getWorldLocation(){
		return new Vector2F(xpos - worldXpos, ypos - worldYpos);
	}
	public static double getDistanceOnScreen(Vector2F vec1, Vector2F vec2){
		//finding a distance between the two vectors
		float v1 = vec1.xpos - vec2.xpos;
		float v2 = vec1.ypos - vec2.ypos;
		//this will give you the distance between the two vectors
		return Math.sqrt(v1*v1 + v2*v2);
	}
}
