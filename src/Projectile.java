
public class Projectile 
{
	
	/* 
	 * TODO Add option to use a different image depending the kind
	 * of Projectile.
	 * 
	 */
	
	
	/* Position */
	double x;
	double y;
	
	double x0;
	double y0;
	
	double x_previous;
	double y_previous;
	
	double Vx0;
	double Vy0 = 0;
	
	int speed = 15;
	String orientation;
	double bulletTimeFired;
	double bulletTimePreviousFrame;
	double t_elapsed_from_fire;
	
	/* Damege caused by the projectile */
	int damage = 300;
	
	
	public Projectile(double posX, double posY, String orientationOfShip, double bulletTime, double playerVx, double playerVy)
	{
		x = posX;
		y = posY;
		
		x0 = posX;
		y0 = posY;
		
		orientation = orientationOfShip;
		
		x_previous = posX;
		y_previous = posY;
		
		Vx0 = speed;

		bulletTimeFired = bulletTime;
		bulletTimePreviousFrame = bulletTime;	
		
		setInitialBulletSpeed(orientation, playerVx, playerVy);
	}
	
	
	// TODO: Nhat we need to fix this to take into consideration that enemies shoot bullets too. 
	//		 I was going to fix it but I didn't want to mess with the gravity you're working on before it's
	//       working without bugs.
	//		 P.S. The orientation of the enemies is set to "left" for now. 


	public void setInitialBulletSpeed(String orientationOfShip, double playerVx, double playerVy)
	{
		if (orientationOfShip.equals("up"))
		{
			Vy0 = -5 + playerVy/2;
		}
		else if (orientationOfShip.equals("down"))
		{
			Vy0 = 5 + playerVy/2;
		}
		else if (orientationOfShip.equals("left"))
		{
			Vx0 = Vx0 + playerVx/2;
		}
		else if (orientationOfShip.equals("right"))
		{
			Vx0 = Vx0+10 + playerVx/2;
		}
	}

}
