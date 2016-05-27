
public class Enemy 
{
	
	/* 
	 * TODO Add option to use a different image depending the kind
	 * of Enemy.
	 * 
	 */
	
	
	/* Position */
	double x;
	double y;
	int size = 64; // 64x64 pixels
	
	/* Type of movement 
	 * This String value selects how the enemies are going to move or if
	 * They are going to have AI. 
	 * 
	 * */
	
	String TypeOfMovement = "AI";
	
	boolean destroyed = false;
	int explodingSequenceState = 1; // The image to be drawed when destroyed == true.
	
	int damageIfCollision = 100; // The amount of damage dealt to player if both sprites collide.
	int speed = 1;
	
	double lastBulletTime = 0;
	
	/* State */
	String orientation = "left";
	
	public Enemy(double posX, double posY)
	{
		x = posX;
		y = posY;	
	}	
	
	public void setLastBulletTime(double lastBulletTimeFired)
	{
		lastBulletTime = lastBulletTimeFired;
	}
	
}
