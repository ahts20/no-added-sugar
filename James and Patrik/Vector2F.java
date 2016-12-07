package my.project.gop.main;

public class Vector2F {
	
	//everything else will be drawn here
	public float xpos;
	public float ypos;
	
	//the world will be drawn at this location
	public static float worldXpos;
	public static float worldYpos;
	
	public Vector2F() {
		this.xpos = 0.0f;
		this.ypos = 0.0f;
	}
	
	//setting the xpos and ypos for later on use
	public Vector2F(float xpos, float ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
	}
	
	//zeroing the method to 0,0
	public static Vector2F zero() {
		return new Vector2F(0,0);
	}
	
	//need to normalize the data so the next object doesn't carry on in the
	//previous position
	public void normalize(){
		double length = Math.sqrt(xpos * xpos + ypos * ypos);
		
		if(length != 0.0){
			float s = 1.0f / (float) length;
			xpos = xpos*s;
			ypos = ypos*s;
		}
	}
	
	public Vector2F getScreenLocation(){
		return new Vector2F(xpos, ypos);
	}
	
	public Vector2F getWorldLocation(){
		return new Vector2F(xpos - worldXpos, ypos - worldYpos);
	}
	
	//boolean will equal true if xpos is the same
	public boolean equals(Vector2F vec){
		return (this.xpos == vec.xpos && this.ypos == vec.ypos);
	}
	
	//creating a copy of the vector vec
	public Vector2F copy(Vector2F vec){
		xpos = vec.xpos;
		ypos = vec.ypos;
		return  new Vector2F(xpos, ypos);
	}
	
	//adding vectors to one another
	public Vector2F add(Vector2F vec){
		xpos = xpos + vec.xpos;
		ypos = ypos + vec.ypos;
		return new Vector2F(xpos, ypos);
	}
	
	public static void setWorldVariables(float wx, float wy){
		worldXpos = wx;
		worldYpos = wy;
	}
	
	public static double getDistanceOnScreen(Vector2F vec1, Vector2F vec2){
		//finding a distance between the two vectors
		float v1 = vec1.xpos - vec2.xpos;
		float v2 = vec1.ypos - vec2.ypos;
		//this will give you the distance between the two vectors
		return Math.sqrt(v1*v1 + v2*v2);
	}
	
	public static double getDistance(Vector2F vec){
		float dx = Math.abs(vec.xpos);
		float dy = Math.abs(vec.ypos);
		return Math.sqrt(dx * dx + dy * dy);
	}
	
//	public double getDistanceBetweenWorldVectors(Vector2F vec){
//		float dx = Math.abs(getWorldLocation().xpos - vec.getWorldLocation().xpos);
//		float dy = Math.abs(getWorldLocation().ypos - vec.getWorldLocation().ypos);
//		return Math.abs(dx * dx - dy * dy);
//	}
	
	
}
