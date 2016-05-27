
public class PowerUp {
	
	/* 
	 * TODO Add option to use a different image depending the kind
	 * of PowerUp.
	 * 
	 */
	
	/* Position */
	
	int x;
	int y;
	
	/* Size*/
	
	int size = 64; // 64x64 pixels.
	
	/* Bonus and duration in seconds. */
	
	int duration = 100; // After 100 seconds substract the bonuses from player so it goes back to it default values.
	int bonusSpeed = 0; // Add this speed to the ships speed.
	int bonusHealth = 200; 
	// TODO Add more bonuses like created projectile damage, etc.
	
	
	public PowerUp(int posX, int posY)
	{
		x = posX;
		y = posY;	
	}
	
	/*
	 * TODO Add setters.
	 */
	
}
