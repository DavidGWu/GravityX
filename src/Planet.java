
public class Planet 
{
	
	/* 
	 * TODO Add option to use a different image depending the kind
	 * of Planet.
	 * 
	 */
	
	
	/* Position */
	int x;
	int y;
	int mass = 500;
	int size = 128; // 128x128 pixels.
	int health = 1000;
	double acceleration_for_bullet;
	double acceleration_for_ship;
	
	boolean destroyed = false; //Set to true when destroyed.
	int explodingSequenceState = 1; // The image to be drawed when destroyed == true.
	
	int damageIfCollision = 400; // Damage dealt to player if there is a direct collision.
	
	
	public Planet(int posX, int posY)
	{
		x = posX;
		y = posY;	
	}	
}
