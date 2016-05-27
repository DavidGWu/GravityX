
public class Ship
{
	
	/* 
	 * TODO Add option to use a different image depending the kind
	 * of Ship. i.e. Player 2 instead of Player 1.
	 * 
	 */
	
	
	/* Position */
	double x;
	double y;
	
	/* Velocity */
	double Vx;
	double Vy;
	
	/* Acceleration */
	double Ax;
	double Ay;
	
	int size = 64; //64x64 pixels
	int speed = 5;
	double lastBulletTime = 0;
	double shipTimePreviousFrame = 0;
	double bulletRate = 1;
	double time_audio_warning = 0;
	double time_of_death;
	int explodingSequenceState = 1;
	
	boolean exploding = false;
	
	/* State */
	
	String orientation = "right";
	
	/* Health */
	
	int health = 100;
	
	public Ship(double posX, double posY)
	{
		x = posX;
		y = posY;
	}
	
	public void setLastBulletTime(double lastBulletTimeFired)
	{
		lastBulletTime = lastBulletTimeFired;
	}
}
