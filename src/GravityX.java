
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;

import javafx.scene.media.*;
import javafx.scene.paint.Color;



/**
 * 
 *
 */

 
public class GravityX extends Application 
{
	
	/* Final */
	public static final int SCREENWIDTH = 1280;
	public static final int SCREENHEIGHT = 800;
	
	/* Global time*/
	public int time = 0;
	public double timeDouble;
	public int t_in_seconds;
	
	/* Canvas */
	Canvas canvas_black;
	Canvas canvas_game;
	Canvas canvas_gameover;
	Canvas canvas_gameover_text;
	Canvas canvas_bubble_text;
	GraphicsContext context_black;
	GraphicsContext context_game;
	GraphicsContext context_gameover;
	GraphicsContext context_gameover_text1; 	//for "it's okay" string
	GraphicsContext context_gameover_text2;		//for "you tried" string
	GraphicsContext context_bubble_text;		//for "bubble" string
	
	/* Variables for the GUI */
	double gravityX_logo_y = 110;
	double logo_variable_y = -10;
	double game_over_variable_y = SCREENHEIGHT+1;
	double big_ship_menu_variable_x = 0;
	double small_ship_menu_variable_x = -10;
	int count_animation_explosion = 0;
	double opacity_canvas_context_game = 1;
	double opacity_canvas_context_gameover_text1 = 0;
	double opacity_canvas_context_gameover_text2 = 0;
	int current_character_position_line1 = 0;
	int current_character_position_line2 = 0;
	int current_character_position_line3 = 0;
	double previous_time_printed_character = 0;
	double speed_printed_character = 0.03;
	double time_game_over = 0;
	double time_last_keyframe =0;
	double keyanimation_number = 1; // for the game over animation
	double first_line = 650; // y=650px for first line
	double second_line = 695; // y=695px for second line
	double third_line = 740; // y=740px for second line
	Boolean read_second_line = true;
	Boolean read_third_line = true;
	String player_name = "player";
	double player_name_y = 577;
	
	/* Import graphics */
	Image background = new Image( "Graphics/background.png" );
	Image background_asteroids = new Image ("Graphics/background_asteroids.png");
	Image background_asteroids2 = new Image ("Graphics/background_asteroids2.png");
	Image gravityX_logo = new Image ("Graphics/gravityX_logo_1005_140.png");
	Image gameover_logo = new Image ("Graphics/gameover.png");
	Image chat_box_background = new Image("Graphics/chat_box.png");
	Image bhola_avatar_closed_mouth = new Image("Graphics/bhola_avatar_closed_mouth.png");
	Image bhola_avatar_open_mouth = new Image("Graphics/bhola_avatar_open_mouth.png");
	Image bhola_avatar_shadow = new Image("Graphics/bhola_avatar_shadow.png");
	
	Image bullet = new Image( "Graphics/bullet.png" );
	Image planet = new Image( "Graphics/planet.png" );
	
	Image ship1 = new Image( "Graphics/ship1.png" ); // 64px * 64px
	Image ship1BoostRight = new Image("Graphics/ship1BoostRight.png"); 		// 64px * 64px
	Image ship1BoostLeft = new Image( "Graphics/ship1BoostLeft.png" );
	Image ship1BoostUp = new Image( "Graphics/ship1BoostUp.png" );
	Image ship1BoostDown = new Image( "Graphics/ship1BoostDown.png" );
	Image ship_menu = new Image ("Graphics/ship_menu.png"); 				//420px  *420px
	Image ship_menu_boost = new Image ("Graphics/ship_menu_boost.png");		//420px  *420px
	Image alien = new Image( "Graphics/enemyship.png" ); 					//34px * 35px
	Image upgrade_bullet_plus = new Image( "Graphics/upgrade_bullet_plus.png" );
	Image caution = new Image ( "Graphics/caution_100_100.png");
	Image warning_message1 = new Image ("Graphics/warning_message1.png");	//770px * 90px
	Image warning_message2 = new Image ("Graphics/warning_message2.png");	//770px * 90px
	Image red_arrow_up = new Image ( "Graphics/red_arrow_up.png");			//70px * 70px
	Image red_arrow_left = new Image ( "Graphics/red_arrow_left.png");		//70px * 70px
	Image red_arrow_down = new Image ( "Graphics/red_arrow_down.png");		//70px * 70px
	Image red_arrow_right = new Image ( "Graphics/red_arrow_right.png");	//70px * 70px
	
	/* Import explosion graphics */
	Image explosion_64x64_1 = new Image( "Graphics/explosion_64x64/explosion_64x64_1.png" );
	Image explosion_64x64_2 = new Image( "Graphics/explosion_64x64/explosion_64x64_2.png" );
	Image explosion_64x64_3 = new Image( "Graphics/explosion_64x64/explosion_64x64_3.png" );
	Image explosion_64x64_4 = new Image( "Graphics/explosion_64x64/explosion_64x64_4.png" );
	Image explosion_64x64_5 = new Image( "Graphics/explosion_64x64/explosion_64x64_5.png" );
	Image explosion_64x64_6 = new Image( "Graphics/explosion_64x64/explosion_64x64_6.png" );
	Image explosion_64x64_7 = new Image( "Graphics/explosion_64x64/explosion_64x64_7.png" );
	Image explosion_64x64_8 = new Image( "Graphics/explosion_64x64/explosion_64x64_8.png" );
	
	Image explosion_128x128_1 = new Image( "Graphics/explosion_128x128/explosion_128x128_1.png" );
	Image explosion_128x128_2 = new Image( "Graphics/explosion_128x128/explosion_128x128_2.png" );
	Image explosion_128x128_3 = new Image( "Graphics/explosion_128x128/explosion_128x128_3.png" );
	Image explosion_128x128_4 = new Image( "Graphics/explosion_128x128/explosion_128x128_4.png" );
	Image explosion_128x128_5 = new Image( "Graphics/explosion_128x128/explosion_128x128_5.png" );
	Image explosion_128x128_6 = new Image( "Graphics/explosion_128x128/explosion_128x128_6.png" );
	Image explosion_128x128_7 = new Image( "Graphics/explosion_128x128/explosion_128x128_7.png" );
	Image explosion_128x128_8 = new Image( "Graphics/explosion_128x128/explosion_128x128_8.png" );
	
	/* Import avatar graphics */
	Image avatar_angry1 = new Image( "Graphics/avatar_angry1.png" );
	Image avatar_angry2 = new Image( "Graphics/avatar_angry2.png" );
	Image avatar_cry = new Image( "Graphics/avatar_cry.png" );
	Image avatar_culprit = new Image( "Graphics/avatar_culprit.png" );
	Image avatar_normal = new Image( "Graphics/avatar_normal.png" );
	Image avatar_shocked = new Image( "Graphics/avatar_shocked.png" );
	Image avatar_smile = new Image( "Graphics/avatar_smile.png" );
	
	/* Import sounds */
	String audio_boost_menu = "src/Sounds/Boost.wav";
	Media media_audio_boost_menu  = new Media(new File(audio_boost_menu).toURI().toString());
	public MediaPlayer mediaPlayer_audio_boost_menu = new MediaPlayer(media_audio_boost_menu);
	
	String background_music_file = "src/Sounds/game_music.mp3";     // For example
	Media media_background_music = new Media(new File(background_music_file).toURI().toString());
	public MediaPlayer mediaPlayer_background_music = new MediaPlayer(media_background_music);
	
	String audio_shoot_bullet_file = "src/Sounds/Shoot_01.mp3";
	Media media_audio_shoot_bullet  = new Media(new File(audio_shoot_bullet_file).toURI().toString());
	public MediaPlayer mediaPlayer_audio_shoot_bullet = new MediaPlayer(media_audio_shoot_bullet);
	
	String audio_planet_explode = "src/Sounds/Explosion_03.mp3";
	Media media_audio_planet_explode  = new Media(new File(audio_planet_explode).toURI().toString());
	
	String audio_enemy_explode = "src/Sounds/Explosion_02.mp3";
	Media media_audio_enemy_explode  = new Media(new File(audio_enemy_explode).toURI().toString());
	
	String audio_upgrade_pickup = "src/Sounds/Upgrade.mp3";
	Media media_audio_upgrade_pickup  = new Media(new File(audio_upgrade_pickup).toURI().toString());
	
	String audio_warning = "src/Sounds/Space_Alert1.wav";
	Media media_audio_warning  = new Media(new File(audio_warning).toURI().toString());
	
	String audio_intro_music = "src/Sounds/intro_music.mp3";
	Media media_audio_intro_music  = new Media(new File(audio_intro_music).toURI().toString());
	
	String audio_ending_music = "src/Sounds/ending_music.mp3";
	Media media_audio_ending_music  = new Media(new File(audio_ending_music).toURI().toString());
	public MediaPlayer mediaPlayer_audio_ending_music = new MediaPlayer(media_audio_ending_music);
	
	String audio_hit_planet = "src/Sounds/Hit_03.mp3";
	Media media_audio_hit_planet  = new Media(new File(audio_hit_planet).toURI().toString());
	
	String audio_bip_text_bubble = "src/Sounds/sfx-blipmale.wav";
	Media media_audio_bip_text_bubble  = new Media(new File(audio_bip_text_bubble).toURI().toString());
	
	String hopes_and_dreams = "src/Sounds/hopes_and_dreams.mp3";
	Media media_audio_hopes_and_dreams  = new Media(new File(hopes_and_dreams).toURI().toString());

	/*MediaPlayer mediaPlayer = new MediaPlayer(media_background_music);
    mediaPlayer.play();*/
	
	
	/* Import fonts */
	public Text t = new Text();

	/* Initialize texts objects */
	Text textsample = new Text (10, 20, "This is a text sample");
	
	/* Initialize scores */
	int scorePlayer1 = 0;
	
	/* Initialize variable for moving background */
	double moving_background_x = 0;
	double moving_background_variable = 0.2;
	double moving_background2_x = 1280;
	double moving_background2_variable = 0.2;
	
	/* Key States */
	public Boolean up = false;
	public Boolean down = false;
	public Boolean left = false;
	public Boolean right = false;
	public Boolean spaceBar = false;
	public Boolean esc = false; //Used to pause the game.
	public Boolean enter_key = false;
	public Boolean menu_screen_active = true;
	public Boolean start_game_transition = false;
	public Boolean play_boost_menu_sound = true;
	public Boolean gameover = false;
	public Boolean control = true;
	public Boolean game_screen_active = false;
	
	/* Game State */
	public Boolean gamePaused = true;
	
	//Initialize Sprites 
    Ship player = new Ship(-80 , SCREENHEIGHT/2);  
    ArrayList<Planet> planetsArray = new ArrayList<Planet>();  
    ArrayList<Projectile> projectilesArray = new ArrayList<Projectile>();
    ArrayList<Enemy> enemiesArray = new ArrayList<Enemy>();
    ArrayList<PowerUp> powerUpsArray = new ArrayList<PowerUp>();
	
	/* Main method */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    
    
    /* ------------------------ Start of "public void start" -----------------------------------*/
    /* create the stage */
    
    public void start(Stage theStage) 
    {
        /* Canvas initialization */
    	theStage.setTitle( "Gravity X" );
             
        Group group_canvas = new Group();
        Scene theScene = new Scene( group_canvas );
        theStage.setScene( theScene ); 
        
        // Black Canvas background
        canvas_black = new Canvas( SCREENWIDTH, SCREENHEIGHT ); //Set resolution
        context_black = canvas_black.getGraphicsContext2D();
        group_canvas.getChildren().add( canvas_black );  
        
        // Canvas for the game
        canvas_game = new Canvas( SCREENWIDTH, SCREENHEIGHT ); //Set resolution
        canvas_game.getGraphicsContext2D().setFont(Font.font("Retro Computer", 20));
        canvas_game.getGraphicsContext2D().setFill(Color.WHITE);
        group_canvas.getChildren().add( canvas_game );      
        context_game = canvas_game.getGraphicsContext2D();    
        /*context.setFont(Font.loadFont("file:C:/Users/minhnhat/workspace/GravityX/src/pixelmix.ttf", 30));*/
        
        // Canvas for the gameoverlogo over the game
        canvas_gameover = new Canvas( SCREENWIDTH, SCREENHEIGHT ); //Set resolution
        group_canvas.getChildren().add( canvas_gameover ); 
        context_gameover = canvas_gameover.getGraphicsContext2D();

        // Canvas for the text at gameover screen "it's okay you tried"
        canvas_gameover_text = new Canvas( SCREENWIDTH, SCREENHEIGHT ); //Set resolution
        canvas_gameover_text.getGraphicsContext2D().setFont(Font.font("pixelmix", 20));
        canvas_gameover_text.getGraphicsContext2D().setFill(Color.WHITE);
        group_canvas.getChildren().add( canvas_gameover_text );
        context_gameover_text1 = canvas_gameover_text.getGraphicsContext2D();
        context_gameover_text2 = canvas_gameover_text.getGraphicsContext2D();

	    // Canvas for the bubble text
        canvas_bubble_text = new Canvas( SCREENWIDTH, SCREENHEIGHT ); //Set resolution
        canvas_bubble_text.getGraphicsContext2D().setFont(Font.font("pixelmix", 35));
        canvas_bubble_text.getGraphicsContext2D().setFill(Color.WHITE);
        group_canvas.getChildren().add( canvas_bubble_text );
        context_bubble_text = canvas_bubble_text.getGraphicsContext2D();
        

        /*
         * Controls
         */
        
        /*
         * Update values when keys pressed.
         */
        EventHandler pressed = new EventHandler<KeyEvent>() 
        {
			@Override
			public void handle(KeyEvent event) 
			{
        		if (control == true)
        		{
        			if (event.getCode().equals(KeyCode.SPACE))
    				{
    					spaceBar = true;
    					System.out.println("SPACEBAR pressed");
    				} 
    				if (event.getCode().equals(KeyCode.LEFT))
    				{
    					left = true;
    					System.out.println("LEFT pressed");
    			   	} 
    				if (event.getCode().equals(KeyCode.RIGHT))
    				{
    					right = true;
    					System.out.println("RIGHT pressed");
    				} 
    				if (event.getCode().equals(KeyCode.UP))
    				{
    					up = true;
    					System.out.println("UP pressed");
    				}
    				if (event.getCode().equals(KeyCode.DOWN))
    				{
    					down = true;
    					System.out.println("DOWN pressed");
    				}   
    				if (event.getCode().equals(KeyCode.ESCAPE))
    				{
    					esc = true;
    					System.out.println("ESCAPE pressed");
    				}
    				if (event.getCode().equals(KeyCode.ENTER))
    				{
    					enter_key = true;
    					System.out.println("ENTER pressed");
    				}
    				if (event.getCode().equals(KeyCode.ENTER) && menu_screen_active == true )
    				{
    					start_game_transition = true;
    					System.out.println("ENTER pressed");
    				}
        		}
			}
        };
        theStage.getScene().setOnKeyPressed(pressed);
        
        /*
         * Update values when keys released.
         */
        EventHandler released = new EventHandler<KeyEvent>() 
        {
    		@Override
    		public void handle(KeyEvent event) 
    		{
    			if (event.getCode().equals(KeyCode.SPACE))
    			{
    				spaceBar = false;
    				System.out.println("SPACEBAR released");
    			} 
    			if (event.getCode().equals(KeyCode.LEFT))
    			{
     	    	   left = false;
     	    	   System.out.println("LEFT released");
     	        }
     	        if (event.getCode().equals(KeyCode.RIGHT))
     	        {
     	    	   right = false;
     	    	   System.out.println("RIGHT released");
     	        } 
     	        if (event.getCode().equals(KeyCode.UP))
     	        {
     	    	   up = false;
     	    	   System.out.println("UP released");
     	        }
     	        if (event.getCode().equals(KeyCode.DOWN))
     	        {
     	    	   down = false;
     	    	   System.out.println("DOWN released");
     	        }
     	        if (event.getCode().equals(KeyCode.ESCAPE))
				{
					esc = false;
					System.out.println("ESCAPE released");
				}
     	        if (event.getCode().equals(KeyCode.ENTER))
				{
					enter_key = false;
					System.out.println("ENTER released");
				}
 	       
    		}
        };
        theStage.getScene().setOnKeyReleased(released);
        
        
        ///////////////////////////////////////////////////////////////
             
        
        final long startNanoTime = System.nanoTime();
        
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {	
            	
            	/* Get scene time in seconds*/
            	timeDouble = (currentNanoTime - startNanoTime) / 1000000000.0;  
            	t_in_seconds = (int) timeDouble;
            	//System.out.println(timeDouble);
            	
            	/* Pause the game if you press esc */
            	if(esc == true)
        		{
            		gamePaused = true;
        		}
            	
            	/* Start the game by showing screen menu */
            	if(menu_screen_active == true)
        		{
        			scene_menu();
        		}
            	
                /* Update scene_game */
                if(game_screen_active == true)
                {
                	scene_game();
                }
                else
                {
                	//TODO Create Game Menu
                }
                
                /* Start game over screen if gameover is true */
                if(gameover == true)
                {
                	scene_gameover();
                }
                
            }
            
        }.start();
        
        theStage.show();
    }
    /* ------------------------ End of "public void start"-----------------------------------*/
    
    /*-------------------------- Start of public void scene_menu() --------------------------*/
    public void scene_menu()
    {    	
        if(start_game_transition == false)
        {
        	// Draw black canvas behind the game canvas
        	context_black.setFill(Color.BLACK);
        	context_black.fillRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
        	
        	/* Clear previous scene and draw background. */
        	context_game.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
        	
        	/* Draw the background */
        	context_game.drawImage(background,0, 0);
            
            /* Draw the logo */
        	context_game.drawImage(gravityX_logo, 175, 110);
            System.out.println("gravityX_logo_y : " + gravityX_logo_y);
            
            // Draw the big ship from the menu
            context_game.drawImage(ship_menu, -100, 200);
        }
        // start transition animation
        else
        {
        	// if logo is still on screen, keep doing animation
        	if(logo_variable_y < 40)
        	{
	        	/* Clear previous scene and draw background. */
        		context_game.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
	        	
	        	/* Draw the background */
        		context_game.drawImage(background,0, 0);
	            
	            // Draw the big ship from the menu
        		context_game.drawImage(ship_menu, -100, 200);
	            
	        	// Draw the logo and move it up each frame
	            logo_variable_y = logo_variable_y+1;
	            context_game.drawImage(gravityX_logo, 175, gravityX_logo_y-(Math.pow(logo_variable_y, 2)/3-100/3));
        	}
        	// if logo goes out of screen start moving big ship
        	else
        	{
        		// play the boost sound once
        		if(play_boost_menu_sound == true)
        		{
            		mediaPlayer_audio_boost_menu.play();
            		play_boost_menu_sound = false;
        		}
        		
        		/* Clear previous scene and draw background. */
        		context_game.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
	        	
	        	/* Draw the background */
        		context_game.drawImage(background,0, 0);
	            
        		// Draw the big ship from the menu and move it right each frame
        		big_ship_menu_variable_x = big_ship_menu_variable_x+1;
        		context_game.drawImage(ship_menu_boost, -100 + Math.pow(big_ship_menu_variable_x, 2.8)/60 , 200);
                
                // if the ship goes out of screen, go to animation of small ship
                if (-100 + Math.pow(big_ship_menu_variable_x, 2.8)/60 > SCREENWIDTH)
                {
                	// if small ship not at initial position yet, keep doing animation
                	if(player.x < SCREENWIDTH/16 -1 )
                	{
                		/* Clear previous scene and draw background. */
                		context_game.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
        	        	
        	        	/* Draw the background */
                		context_game.drawImage(background,0, 0);
        	            
                		// Draw the small ship from out of screen and move it right each frame until at initial position
        	        	small_ship_menu_variable_x = small_ship_menu_variable_x + 0.5;
                		player.x = SCREENWIDTH/16 - Math.pow(small_ship_menu_variable_x, 2);
                		context_game.drawImage(ship1BoostRight, player.x, SCREENHEIGHT/2);
                	}
                	// if ship at initial position, start the game
                	else
                	{
                		// unpause the game
                		menu_screen_active = false;
                		game_screen_active = true;
                		
                    	// set the first time of Previous Frame before game starts
                    	player.shipTimePreviousFrame = timeDouble;
                    	start_game_transition = false;
                    	
                    	// Play the background music
                        mediaPlayer_background_music.play();
                	}
                	
                }
        		
        	}
        }
    }
    
    
    
    /* ------------------------ Start of public void scene_game (int t) --------------------------*/
    /*
     * Create the scene.
     * @param double t current running time.
     */
    public void scene_game()
    {    	
    	/* Clear previous scene and draw background. */
    	context_game.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
    	
    	/* Draw the background */
    	context_game.drawImage(background,0, 0);
        
        /* Draw the moving background */
        
        // The position x of the background is slowly reduce each frame.
        moving_background_x = moving_background_x - moving_background_variable;
        // if the background is completely to the left, put it back to 0
        if (moving_background_x < -1280)
        {
        	moving_background_x = 1280;
        }
        // finaly, draw the moving background
        context_game.drawImage(background_asteroids, moving_background_x, 0);
        
        // The position x of the background2 is slowly reduce each frame.
        moving_background2_x = moving_background2_x - moving_background2_variable;
        // if the background is completely to the left, put it back to 0
        if (moving_background2_x < -1280)
        {
        	moving_background2_x = 1280;
        }
        // finaly, draw the moving background
        context_game.drawImage(background_asteroids2, moving_background2_x, 0);
        
        //context.fillText("This is a test", SCREENWIDTH/8 , SCREENHEIGHT/16);
        
        /* Display score */

        String scorePlayer1toString = "Score: " + Integer.toString(scorePlayer1);
        context_game.fillText(scorePlayer1toString, SCREENWIDTH/8, SCREENHEIGHT/16);
        
        /* Display health */    
        
        String healthPlayer1toString = "Health: " + Integer.toString(player.health);
        context_game.fillText(healthPlayer1toString, (SCREENWIDTH/8)*3, SCREENHEIGHT/16);
        
    	/* Call methods */
    	
    	/*
    	 * Generate Sprites
    	 */
    	generateSprites();
    	
    	/*
    	 * Remove Sprites
    	 * Very Important! game will slow down over time if sprites are not removed.
    	 */ 	
    	removeSpritesNoLongerOnCanvas();
    	
    	updatePlayer();
    	
    	/*
    	 * Update Projectile positions.
    	 */	
    	updateProjectiles();
    	
    	updateEnemies();
    	
    	updatePlanetsAndPowerUps();
    	
    	
    	/*
    	 * Check for collision between Sprites.
    	 */  	
    	checkForCollision();
    	
    	/* Draw Sprites.
    	 * Call drawSprites method.
    	 */
    	drawSprites();
    	
    	/*
    	 * Check life of player
    	 */
    	checkPlayerLife();
    	
    	/* Update time at the end of the scene
    	 * Keep this at the end.
    	 * */
    	time = t_in_seconds;
    }
    
    /* ------------------------ Start of public void scene_gameover () --------------------------*/
    /*
     * Create the scene.
     */
    public void scene_gameover()
    {
    	double speed_move_gameover_up = 5; //1
    	double speed_alpha_background = 0.005;
		double speed_alpha_text = 0.05; //0.005
		
		// Clear previous scene and draw background.
		context_gameover.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
		context_gameover_text1.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
		context_gameover_text2.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
		
    	// move game over logo up
    	if(game_over_variable_y > 101)
    	{	
        	// Draw logo game over
    		context_gameover.drawImage(gameover_logo, 100, game_over_variable_y);
    		
    		// move logo variable up
    		game_over_variable_y = game_over_variable_y - speed_move_gameover_up;            
    	}
    	// when gameover logo all the way up
    	else 
    	{
    		// Draw logo game over at its final position
    		context_gameover.drawImage(gameover_logo, 100, game_over_variable_y);
    		
    		// make the game canvas disapear
    		if(opacity_canvas_context_game>0)
    		{
    			opacity_canvas_context_game = opacity_canvas_context_game-speed_alpha_background;
    			context_game.setGlobalAlpha(opacity_canvas_context_game);
    		}
    		else
    		{
    			game_screen_active = false;
    			if(opacity_canvas_context_gameover_text1<1)
    			{
    				// make "it's okay" appear
            		opacity_canvas_context_gameover_text1 = opacity_canvas_context_gameover_text1+speed_alpha_text;
            		context_gameover_text1.setGlobalAlpha(opacity_canvas_context_gameover_text1);
            		String itsokay = "It's okay...";
            		context_gameover_text1.fillText(itsokay, SCREENWIDTH/10*4, SCREENHEIGHT/10*4);
    			}
    			else if (opacity_canvas_context_gameover_text2<1)
    			{
    				// rewrite "it's okay"
    				String itsokay = "It's okay...";
    				context_gameover_text1.setGlobalAlpha(1);
            		context_gameover_text1.fillText(itsokay, SCREENWIDTH/10*4, SCREENHEIGHT/10*4);
            		
            		// make "you tried" appear
    				opacity_canvas_context_gameover_text2 = opacity_canvas_context_gameover_text2+speed_alpha_text;
            		context_gameover_text2.setGlobalAlpha(opacity_canvas_context_gameover_text2);
    				String youtried = "you tried.";
            		context_gameover_text2.fillText(youtried, SCREENWIDTH/10*4, SCREENHEIGHT/10*5);
            		
            		// get the time of the keyframe
            		time_last_keyframe = timeDouble;
    			}
    			// when both "it's okay" and "you tried" fully opaque, print them fully opaque
    			else 
    			{
    				// make the control available again
        			control = true;
        			
        			
    				String itsokay = "It's okay...";
            		context_gameover_text1.fillText(itsokay, SCREENWIDTH/10*4, SCREENHEIGHT/10*4);
    				String youtried = "you tried.";
            		context_gameover_text2.fillText(youtried, SCREENWIDTH/10*4, SCREENHEIGHT/10*5);
            		
            		// after 3 seconds, stop the music
            		if (timeDouble - time_last_keyframe > 3 && keyanimation_number == 1)
            		{
            			// stop the game music after 3 seconds
            			mediaPlayer_audio_ending_music.stop();
            			keyanimation_number = keyanimation_number +1;
            		}

            		else
            		{

            			if(timeDouble - time_last_keyframe > 5 && keyanimation_number == 2)
            			{
            				System.out.println("Keyanimation_number : " + keyanimation_number);
            				
            				// Clear the screen
            				context_gameover.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text1.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text2.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				
            				// Draw the bubble background with Bhola
                			context_gameover.drawImage(chat_box_background, 0, 0);
                			
            				// write the content of the bubble text, one character at a time.
                    		String string_first_line = "I am sorry...I am so sorry...";
                    		String string_second_line = "I have failed to save the universe.";
                    		String string_third_line = "This is all my fault.";
                    		
                    		//-------------------------------play the first line
                    		if(current_character_position_line1 != string_first_line.length())
                    		{
                    			Character current_character = string_first_line.charAt(current_character_position_line1);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				||
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line1 != string_first_line.length())
	                        		{
	                        			current_character_position_line1 = current_character_position_line1+1;
	                        		}
	                        		else
	                        		{
	                        			read_second_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		
                    		//---------------------------------------Play the second line
                    		else if(current_character_position_line2 != string_second_line.length() && read_second_line == true)
                    		{
                    			Character current_character = string_second_line.charAt(current_character_position_line2);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line2 != string_second_line.length())
	                        		{
	                        			current_character_position_line2 = current_character_position_line2+1;
	                        		}
	                        		else
	                        		{
	                        			read_third_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		//---------------------------------------Play the third line
                    		else if(current_character_position_line3 != string_third_line.length() && read_third_line == true)
                    		{
                    			Character current_character = string_third_line.charAt(current_character_position_line3);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line3 != string_third_line.length())
	                        		{
	                        			current_character_position_line3 = current_character_position_line3+1;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		else
                    		{
                    			//Draw Bhola closed mouth
                    			context_gameover.drawImage(avatar_cry, 0, 0);
                    			
                    			// if press enter to go to next screen, reset values for next texts
                    			if (enter_key == true)
                        		{
                        			keyanimation_number = keyanimation_number+1;
                        			current_character_position_line1=0;
                        			current_character_position_line2=0;
                        			current_character_position_line3=0;
                        			System.out.println("Enter pressed");
                        		}
                    		}

                			//Write the text
                    		context_bubble_text.fillText(player_name, 35, player_name_y);
                    		context_bubble_text.fillText(string_first_line.substring(0,current_character_position_line1), 50, first_line);
                    		context_bubble_text.fillText(string_second_line.substring(0,current_character_position_line2), 50, second_line);
                    		context_bubble_text.fillText(string_third_line.substring(0,current_character_position_line3), 50, third_line);
            			}
            			
            			else if(keyanimation_number == 3)
            			{
            				System.out.println("Keyanimation_number : " + keyanimation_number);
            				
            				// Clear the screen
            				context_gameover.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text1.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text2.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_bubble_text.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				
            				// Draw the bubble background with Bhola
                			context_gameover.drawImage(chat_box_background, 0, 0);
                			
            				// write the content of the bubble text, one character at a time.
                    		String string_first_line = "Don't lose hope yet.";
                    		String string_second_line = "It is too early to give up.";
                    		String string_third_line = " ";
                    		
                    		//-------------------------------play the first line
                    		if(current_character_position_line1 != string_first_line.length())
                    		{
                    			Character current_character = string_first_line.charAt(current_character_position_line1);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				||
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_shadow, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_shadow, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line1 != string_first_line.length())
	                        		{
	                        			current_character_position_line1 = current_character_position_line1+1;
	                        		}
	                        		else
	                        		{
	                        			read_second_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		
                    		//---------------------------------------Play the second line
                    		else if(current_character_position_line2 != string_second_line.length() && read_second_line == true)
                    		{
                    			Character current_character = string_second_line.charAt(current_character_position_line2);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_shadow, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_shadow, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line2 != string_second_line.length())
	                        		{
	                        			current_character_position_line2 = current_character_position_line2+1;
	                        		}
	                        		else
	                        		{
	                        			read_third_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		//---------------------------------------Play the third line
                    		else if(current_character_position_line3 != string_third_line.length() && read_third_line == true)
                    		{
                    			Character current_character = string_third_line.charAt(current_character_position_line3);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_shadow, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_shadow, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line3 != string_third_line.length())
	                        		{
	                        			current_character_position_line3 = current_character_position_line3+1;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		else
                    		{
                    			//Draw Bhola closed mouth
                    			context_gameover.drawImage(bhola_avatar_shadow, 0, 0);
                    			
                    			// if press enter to go to next screen, reset values for next texts
                    			if (enter_key == true)
                        		{
                        			keyanimation_number = keyanimation_number+1;
                        			current_character_position_line1=0;
                        			current_character_position_line2=0;
                        			current_character_position_line3=0;
                        			System.out.println("Enter pressed");
                        		}
                    		}

                			//Write the text
                    		context_bubble_text.fillText(string_first_line.substring(0,current_character_position_line1), 50, first_line);
                    		context_bubble_text.fillText(string_second_line.substring(0,current_character_position_line2), 50, second_line);
                    		context_bubble_text.fillText(string_third_line.substring(0,current_character_position_line3), 50, third_line);
            			}
            			else if(keyanimation_number == 4)
            			{
            				System.out.println("Keyanimation_number : " + keyanimation_number);
            				
            				// Clear the screen
            				context_gameover.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text1.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text2.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_bubble_text.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				
            				// Draw the bubble background with Bhola
                			context_gameover.drawImage(chat_box_background, 0, 0);
                			
            				// write the content of the bubble text, one character at a time.
                    		String string_first_line = "AHHHH Who is this ?? Is that a ghost ???";
                    		String string_second_line = "Am I dead yet ? That's it. I'm dead.";
                    		String string_third_line = "That was quick.";
                    		
                    		//-------------------------------play the first line
                    		if(current_character_position_line1 != string_first_line.length())
                    		{
                    			Character current_character = string_first_line.charAt(current_character_position_line1);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				||
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_shocked, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_shocked, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line1 != string_first_line.length())
	                        		{
	                        			current_character_position_line1 = current_character_position_line1+1;
	                        		}
	                        		else
	                        		{
	                        			read_second_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		
                    		//---------------------------------------Play the second line
                    		else if(current_character_position_line2 != string_second_line.length() && read_second_line == true)
                    		{
                    			Character current_character = string_second_line.charAt(current_character_position_line2);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_shocked, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_shocked, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line2 != string_second_line.length())
	                        		{
	                        			current_character_position_line2 = current_character_position_line2+1;
	                        		}
	                        		else
	                        		{
	                        			read_third_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		//---------------------------------------Play the third line
                    		else if(current_character_position_line3 != string_third_line.length() && read_third_line == true)
                    		{
                    			Character current_character = string_third_line.charAt(current_character_position_line3);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_shocked, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_shocked, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line3 != string_third_line.length())
	                        		{
	                        			current_character_position_line3 = current_character_position_line3+1;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		else
                    		{
                    			//Draw Bhola closed mouth
                    			context_gameover.drawImage(avatar_shocked, 0, 0);
                    			
                    			// if press enter to go to next screen, reset values for next texts
                    			if (enter_key == true)
                        		{
                        			keyanimation_number = keyanimation_number+1;
                        			current_character_position_line1=0;
                        			current_character_position_line2=0;
                        			current_character_position_line3=0;
                        			System.out.println("Enter pressed");
                        		}
                    		}

                			//Write the text
                    		context_bubble_text.fillText(player_name, 35, player_name_y);
                    		context_bubble_text.fillText(string_first_line.substring(0,current_character_position_line1), 50, first_line);
                    		context_bubble_text.fillText(string_second_line.substring(0,current_character_position_line2), 50, second_line);
                    		context_bubble_text.fillText(string_third_line.substring(0,current_character_position_line3), 50, third_line);
            			}
            			else if(keyanimation_number == 5)
            			{
            				System.out.println("Keyanimation_number : " + keyanimation_number);
            				
            				// Clear the screen
            				context_gameover.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text1.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text2.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_bubble_text.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				
            				// Draw the bubble background with Bhola
                			context_gameover.drawImage(chat_box_background, 0, 0);
                			
            				// write the content of the bubble text, one character at a time.
                    		String string_first_line = "It is me, Guardian Bhola";
                    		String string_second_line = "the spirit of the universe.";
                    		String string_third_line = " ";
                    		
                    		//-------------------------------play the first line
                    		if(current_character_position_line1 != string_first_line.length())
                    		{
                    			Character current_character = string_first_line.charAt(current_character_position_line1);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				||
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_open_mouth, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line1 != string_first_line.length())
	                        		{
	                        			current_character_position_line1 = current_character_position_line1+1;
	                        		}
	                        		else
	                        		{
	                        			read_second_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		
                    		//---------------------------------------Play the second line
                    		else if(current_character_position_line2 != string_second_line.length() && read_second_line == true)
                    		{
                    			Character current_character = string_second_line.charAt(current_character_position_line2);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_open_mouth, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line2 != string_second_line.length())
	                        		{
	                        			current_character_position_line2 = current_character_position_line2+1;
	                        		}
	                        		else
	                        		{
	                        			read_third_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		//---------------------------------------Play the third line
                    		else if(current_character_position_line3 != string_third_line.length() && read_third_line == true)
                    		{
                    			Character current_character = string_third_line.charAt(current_character_position_line3);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_open_mouth, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line3 != string_third_line.length())
	                        		{
	                        			current_character_position_line3 = current_character_position_line3+1;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		else
                    		{
                    			//Draw Bhola closed mouth
                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
                    			
                    			// if press enter to go to next screen, reset values for next texts
                    			if (enter_key == true)
                        		{
                        			keyanimation_number = keyanimation_number+1;
                        			current_character_position_line1=0;
                        			current_character_position_line2=0;
                        			current_character_position_line3=0;
                        			System.out.println("Enter pressed");
                        		}
                    		}

                			//Write the text
                    		context_bubble_text.fillText(string_first_line.substring(0,current_character_position_line1), 50, first_line);
                    		context_bubble_text.fillText(string_second_line.substring(0,current_character_position_line2), 50, second_line);
                    		context_bubble_text.fillText(string_third_line.substring(0,current_character_position_line3), 50, third_line);
            			}
            			else if(keyanimation_number == 6)
            			{
            				System.out.println("Keyanimation_number : " + keyanimation_number);
            				
            				// Clear the screen
            				context_gameover.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text1.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text2.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_bubble_text.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				
            				// Draw the bubble background with Bhola
                			context_gameover.drawImage(chat_box_background, 0, 0);
                			
            				// write the content of the bubble text, one character at a time.
                    		String string_first_line = "Yep, that's right... I'm totally dead.";
                    		String string_second_line = " ";
                    		String string_third_line = " ";
                    		
                    		//-------------------------------play the first line
                    		if(current_character_position_line1 != string_first_line.length())
                    		{
                    			Character current_character = string_first_line.charAt(current_character_position_line1);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				||
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_shocked, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_shocked, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line1 != string_first_line.length())
	                        		{
	                        			current_character_position_line1 = current_character_position_line1+1;
	                        		}
	                        		else
	                        		{
	                        			read_second_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		
                    		//---------------------------------------Play the second line
                    		else if(current_character_position_line2 != string_second_line.length() && read_second_line == true)
                    		{
                    			Character current_character = string_second_line.charAt(current_character_position_line2);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_shocked, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_shocked, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line2 != string_second_line.length())
	                        		{
	                        			current_character_position_line2 = current_character_position_line2+1;
	                        		}
	                        		else
	                        		{
	                        			read_third_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		//---------------------------------------Play the third line
                    		else if(current_character_position_line3 != string_third_line.length() && read_third_line == true)
                    		{
                    			Character current_character = string_third_line.charAt(current_character_position_line3);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_shocked, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_shocked, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line3 != string_third_line.length())
	                        		{
	                        			current_character_position_line3 = current_character_position_line3+1;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		else
                    		{
                    			//Draw Bhola closed mouth
                    			context_gameover.drawImage(avatar_shocked, 0, 0);
                    			
                    			// if press enter to go to next screen, reset values for next texts
                    			if (enter_key == true)
                        		{
                        			keyanimation_number = keyanimation_number+1;
                        			current_character_position_line1=0;
                        			current_character_position_line2=0;
                        			current_character_position_line3=0;
                        			System.out.println("Enter pressed");
                        		}
                    		}

                			//Write the text
                    		context_bubble_text.fillText(player_name, 35, player_name_y);
                    		context_bubble_text.fillText(string_first_line.substring(0,current_character_position_line1), 50, first_line);
                    		context_bubble_text.fillText(string_second_line.substring(0,current_character_position_line2), 50, second_line);
                    		context_bubble_text.fillText(string_third_line.substring(0,current_character_position_line3), 50, third_line);
            			}
            			else if(keyanimation_number == 7)
            			{
            				System.out.println("Keyanimation_number : " + keyanimation_number);
            				
            				// Clear the screen
            				context_gameover.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text1.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text2.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_bubble_text.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				
            				// Draw the bubble background with Bhola
                			context_gameover.drawImage(chat_box_background, 0, 0);
                			
            				// write the content of the bubble text, one character at a time.
                    		String string_first_line = "I just wished I would have been welcomed";
                    		String string_second_line = "by a beautiful princess.";
                    		String string_third_line = "*sigh*";
                    		
                    		//-------------------------------play the first line
                    		if(current_character_position_line1 != string_first_line.length())
                    		{
                    			Character current_character = string_first_line.charAt(current_character_position_line1);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				||
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line1 != string_first_line.length())
	                        		{
	                        			current_character_position_line1 = current_character_position_line1+1;
	                        		}
	                        		else
	                        		{
	                        			read_second_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		
                    		//---------------------------------------Play the second line
                    		else if(current_character_position_line2 != string_second_line.length() && read_second_line == true)
                    		{
                    			Character current_character = string_second_line.charAt(current_character_position_line2);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line2 != string_second_line.length())
	                        		{
	                        			current_character_position_line2 = current_character_position_line2+1;
	                        		}
	                        		else
	                        		{
	                        			read_third_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		//---------------------------------------Play the third line
                    		else if(current_character_position_line3 != string_third_line.length() && read_third_line == true)
                    		{
                    			Character current_character = string_third_line.charAt(current_character_position_line3);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line3 != string_third_line.length())
	                        		{
	                        			current_character_position_line3 = current_character_position_line3+1;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		else
                    		{
                    			//Draw Bhola closed mouth
                    			context_gameover.drawImage(avatar_cry, 0, 0);
                    			
                    			// if press enter to go to next screen, reset values for next texts
                    			if (enter_key == true)
                        		{
                        			keyanimation_number = keyanimation_number+1;
                        			current_character_position_line1=0;
                        			current_character_position_line2=0;
                        			current_character_position_line3=0;
                        			System.out.println("Enter pressed");
                        		}
                    		}

                			//Write the text
                    		context_bubble_text.fillText(player_name, 35, player_name_y);
                    		context_bubble_text.fillText(string_first_line.substring(0,current_character_position_line1), 50, first_line);
                    		context_bubble_text.fillText(string_second_line.substring(0,current_character_position_line2), 50, second_line);
                    		context_bubble_text.fillText(string_third_line.substring(0,current_character_position_line3), 50, third_line);
            			}
            			else if(keyanimation_number == 8)
            			{
            				System.out.println("Keyanimation_number : " + keyanimation_number);
            				
            				// Clear the screen
            				context_gameover.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text1.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text2.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_bubble_text.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				
            				// Draw the bubble background with Bhola
                			context_gameover.drawImage(chat_box_background, 0, 0);
                			
            				// write the content of the bubble text, one character at a time.
                    		String string_first_line = "It is too early for you to lose hope.";
                    		String string_second_line = "Do not give up yet.";
                    		String string_third_line = "You still have a lot to do.";
                    		
                    		//-------------------------------play the first line
                    		if(current_character_position_line1 != string_first_line.length())
                    		{
                    			Character current_character = string_first_line.charAt(current_character_position_line1);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				||
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_open_mouth, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line1 != string_first_line.length())
	                        		{
	                        			current_character_position_line1 = current_character_position_line1+1;
	                        		}
	                        		else
	                        		{
	                        			read_second_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		
                    		//---------------------------------------Play the second line
                    		else if(current_character_position_line2 != string_second_line.length() && read_second_line == true)
                    		{
                    			Character current_character = string_second_line.charAt(current_character_position_line2);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_open_mouth, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line2 != string_second_line.length())
	                        		{
	                        			current_character_position_line2 = current_character_position_line2+1;
	                        		}
	                        		else
	                        		{
	                        			read_third_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		//---------------------------------------Play the third line
                    		else if(current_character_position_line3 != string_third_line.length() && read_third_line == true)
                    		{
                    			Character current_character = string_third_line.charAt(current_character_position_line3);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_open_mouth, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line3 != string_third_line.length())
	                        		{
	                        			current_character_position_line3 = current_character_position_line3+1;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		else
                    		{
                    			//Draw Bhola closed mouth
                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
                    			
                    			// if press enter to go to next screen, reset values for next texts
                    			if (enter_key == true)
                        		{
                        			keyanimation_number = keyanimation_number+1;
                        			current_character_position_line1=0;
                        			current_character_position_line2=0;
                        			current_character_position_line3=0;
                        			System.out.println("Enter pressed");
                        		}
                    		}

                			//Write the text
                    		context_bubble_text.fillText(string_first_line.substring(0,current_character_position_line1), 50, first_line);
                    		context_bubble_text.fillText(string_second_line.substring(0,current_character_position_line2), 50, second_line);
                    		context_bubble_text.fillText(string_third_line.substring(0,current_character_position_line3), 50, third_line);
            			}
            			else if(keyanimation_number == 9)
            			{
            				System.out.println("Keyanimation_number : " + keyanimation_number);
            				
            				// Clear the screen
            				context_gameover.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text1.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text2.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_bubble_text.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				
            				// Draw the bubble background with Bhola
                			context_gameover.drawImage(chat_box_background, 0, 0);
                			
            				// write the content of the bubble text, one character at a time.
                    		String string_first_line = "But my ship litterally just exploded.";
                    		String string_second_line = "And those enemies are too strong for me.";
                    		String string_third_line = "I really can't defeat them.";
                    		
                    		//-------------------------------play the first line
                    		if(current_character_position_line1 != string_first_line.length())
                    		{
                    			Character current_character = string_first_line.charAt(current_character_position_line1);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				||
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line1 != string_first_line.length())
	                        		{
	                        			current_character_position_line1 = current_character_position_line1+1;
	                        		}
	                        		else
	                        		{
	                        			read_second_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		
                    		//---------------------------------------Play the second line
                    		else if(current_character_position_line2 != string_second_line.length() && read_second_line == true)
                    		{
                    			Character current_character = string_second_line.charAt(current_character_position_line2);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line2 != string_second_line.length())
	                        		{
	                        			current_character_position_line2 = current_character_position_line2+1;
	                        		}
	                        		else
	                        		{
	                        			read_third_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		//---------------------------------------Play the third line
                    		else if(current_character_position_line3 != string_third_line.length() && read_third_line == true)
                    		{
                    			Character current_character = string_third_line.charAt(current_character_position_line3);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_cry, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line3 != string_third_line.length())
	                        		{
	                        			current_character_position_line3 = current_character_position_line3+1;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		else
                    		{
                    			//Draw Bhola closed mouth
                    			context_gameover.drawImage(avatar_cry, 0, 0);
                    			
                    			// if press enter to go to next screen, reset values for next texts
                    			if (enter_key == true)
                        		{
                        			keyanimation_number = keyanimation_number+1;
                        			current_character_position_line1=0;
                        			current_character_position_line2=0;
                        			current_character_position_line3=0;
                        			System.out.println("Enter pressed");
                        		}
                    		}

                			//Write the text
                    		context_bubble_text.fillText(player_name, 35, player_name_y);
                    		context_bubble_text.fillText(string_first_line.substring(0,current_character_position_line1), 50, first_line);
                    		context_bubble_text.fillText(string_second_line.substring(0,current_character_position_line2), 50, second_line);
                    		context_bubble_text.fillText(string_third_line.substring(0,current_character_position_line3), 50, third_line);
            			}
            			else if(keyanimation_number == 10)
            			{
            				System.out.println("Keyanimation_number : " + keyanimation_number);
            				
            				// Clear the screen
            				context_gameover.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text1.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text2.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_bubble_text.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				
            				// Draw the bubble background with Bhola
                			context_gameover.drawImage(chat_box_background, 0, 0);
                			
            				// write the content of the bubble text, one character at a time.
                    		String string_first_line = "As long as you are alive, there is hope.";
                    		String string_second_line = "You are the child of the prophecy.";
                    		String string_third_line = "JUST DO IT.";
                    		
                    		//-------------------------------play the first line
                    		if(current_character_position_line1 != string_first_line.length())
                    		{
                    			Character current_character = string_first_line.charAt(current_character_position_line1);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				||
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_open_mouth, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line1 != string_first_line.length())
	                        		{
	                        			current_character_position_line1 = current_character_position_line1+1;
	                        		}
	                        		else
	                        		{
	                        			read_second_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		
                    		//---------------------------------------Play the second line
                    		else if(current_character_position_line2 != string_second_line.length() && read_second_line == true)
                    		{
                    			Character current_character = string_second_line.charAt(current_character_position_line2);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_open_mouth, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line2 != string_second_line.length())
	                        		{
	                        			current_character_position_line2 = current_character_position_line2+1;
	                        		}
	                        		else
	                        		{
	                        			read_third_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		//---------------------------------------Play the third line
                    		else if(current_character_position_line3 != string_third_line.length() && read_third_line == true)
                    		{
                    			Character current_character = string_third_line.charAt(current_character_position_line3);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_open_mouth, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line3 != string_third_line.length())
	                        		{
	                        			current_character_position_line3 = current_character_position_line3+1;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		else
                    		{
                    			//Draw Bhola closed mouth
                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
                    			
                    			// if press enter to go to next screen, reset values for next texts
                    			if (enter_key == true)
                        		{
                        			keyanimation_number = keyanimation_number+1;
                        			current_character_position_line1=0;
                        			current_character_position_line2=0;
                        			current_character_position_line3=0;
                        			System.out.println("Enter pressed");
                        		}
                    		}

                			//Write the text
                    		context_bubble_text.fillText(string_first_line.substring(0,current_character_position_line1), 50, first_line);
                    		context_bubble_text.fillText(string_second_line.substring(0,current_character_position_line2), 50, second_line);
                    		context_bubble_text.fillText(string_third_line.substring(0,current_character_position_line3), 50, third_line);
            			}
            			else if(keyanimation_number == 11)
            			{
            				System.out.println("Keyanimation_number : " + keyanimation_number);
            				
            				// Clear the screen
            				context_gameover.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text1.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text2.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_bubble_text.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				
            				// Draw the bubble background with Bhola
                			context_gameover.drawImage(chat_box_background, 0, 0);
                			
            				// write the content of the bubble text, one character at a time.
                    		String string_first_line = "I will give you all my life source energy";
                    		String string_second_line = "to bring you back to life.";
                    		String string_third_line = " ";
                    		
                    		//-------------------------------play the first line
                    		if(current_character_position_line1 != string_first_line.length())
                    		{
                    			Character current_character = string_first_line.charAt(current_character_position_line1);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				||
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_open_mouth, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line1 != string_first_line.length())
	                        		{
	                        			current_character_position_line1 = current_character_position_line1+1;
	                        		}
	                        		else
	                        		{
	                        			read_second_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		
                    		//---------------------------------------Play the second line
                    		else if(current_character_position_line2 != string_second_line.length() && read_second_line == true)
                    		{
                    			Character current_character = string_second_line.charAt(current_character_position_line2);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_open_mouth, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line2 != string_second_line.length())
	                        		{
	                        			current_character_position_line2 = current_character_position_line2+1;
	                        		}
	                        		else
	                        		{
	                        			read_third_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		//---------------------------------------Play the third line
                    		else if(current_character_position_line3 != string_third_line.length() && read_third_line == true)
                    		{
                    			Character current_character = string_third_line.charAt(current_character_position_line3);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_open_mouth, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line3 != string_third_line.length())
	                        		{
	                        			current_character_position_line3 = current_character_position_line3+1;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		else
                    		{
                    			//Draw Bhola closed mouth
                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
                    			
                    			// if press enter to go to next screen, reset values for next texts
                    			if (enter_key == true)
                        		{
                        			keyanimation_number = keyanimation_number+1;
                        			current_character_position_line1=0;
                        			current_character_position_line2=0;
                        			current_character_position_line3=0;
                        			System.out.println("Enter pressed");
                        		}
                    		}

                			//Write the text
                    		context_bubble_text.fillText(string_first_line.substring(0,current_character_position_line1), 50, first_line);
                    		context_bubble_text.fillText(string_second_line.substring(0,current_character_position_line2), 50, second_line);
                    		context_bubble_text.fillText(string_third_line.substring(0,current_character_position_line3), 50, third_line);
            			}
            			else if(keyanimation_number == 12)
            			{
            				System.out.println("Keyanimation_number : " + keyanimation_number);
            				
            				// Clear the screen
            				context_gameover.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text1.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text2.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_bubble_text.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				
            				// Draw the bubble background with Bhola
                			context_gameover.drawImage(chat_box_background, 0, 0);
                			
            				// write the content of the bubble text, one character at a time.
                    		String string_first_line = "Believe in yourself.";
                    		String string_second_line = "Believe in those who love you.";
                    		String string_third_line = "Believe in those you love.";
                    		
                    		//-------------------------------play the first line
                    		if(current_character_position_line1 != string_first_line.length())
                    		{
                    			Character current_character = string_first_line.charAt(current_character_position_line1);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				||
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_open_mouth, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line1 != string_first_line.length())
	                        		{
	                        			current_character_position_line1 = current_character_position_line1+1;
	                        		}
	                        		else
	                        		{
	                        			read_second_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		
                    		//---------------------------------------Play the second line
                    		else if(current_character_position_line2 != string_second_line.length() && read_second_line == true)
                    		{
                    			Character current_character = string_second_line.charAt(current_character_position_line2);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_open_mouth, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line2 != string_second_line.length())
	                        		{
	                        			current_character_position_line2 = current_character_position_line2+1;
	                        		}
	                        		else
	                        		{
	                        			read_third_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		//---------------------------------------Play the third line
                    		else if(current_character_position_line3 != string_third_line.length() && read_third_line == true)
                    		{
                    			Character current_character = string_third_line.charAt(current_character_position_line3);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(bhola_avatar_open_mouth, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line3 != string_third_line.length())
	                        		{
	                        			current_character_position_line3 = current_character_position_line3+1;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		else
                    		{
                    			//Draw Bhola closed mouth
                    			context_gameover.drawImage(bhola_avatar_closed_mouth, 0, 0);
                    			
                    			// if press enter to go to next screen, reset values for next texts
                    			if (enter_key == true)
                        		{
                        			keyanimation_number = keyanimation_number+1;
                        			current_character_position_line1=0;
                        			current_character_position_line2=0;
                        			current_character_position_line3=0;
                        			System.out.println("Enter pressed");
                        			// Start playing hopes and dreams
                    				MediaPlayer mediaPlayer_hopes_and_dreams = new MediaPlayer(media_audio_hopes_and_dreams);
                    				mediaPlayer_hopes_and_dreams.play();
                        		}
                    		}

                			//Write the text
                    		context_bubble_text.fillText(string_first_line.substring(0,current_character_position_line1), 50, first_line);
                    		context_bubble_text.fillText(string_second_line.substring(0,current_character_position_line2), 50, second_line);
                    		context_bubble_text.fillText(string_third_line.substring(0,current_character_position_line3), 50, third_line);
            			}
            			else if(keyanimation_number == 13)
            			{
            				System.out.println("Keyanimation_number : " + keyanimation_number);
            				
            				// Clear the screen
            				context_gameover.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text1.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text2.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_bubble_text.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				
            				// Draw the bubble background with Bhola
                			context_gameover.drawImage(chat_box_background, 0, 0);
                			
            				// write the content of the bubble text, one character at a time.
                    		String string_first_line = "That's right, I can't give up yet.";
                    		String string_second_line = "A lot of lives are counting on me.";
                    		String string_third_line = "I can't let them down.";
                    		
                    		//-------------------------------play the first line
                    		if(current_character_position_line1 != string_first_line.length())
                    		{
                    			Character current_character = string_first_line.charAt(current_character_position_line1);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				||
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_smile, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_smile, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line1 != string_first_line.length())
	                        		{
	                        			current_character_position_line1 = current_character_position_line1+1;
	                        		}
	                        		else
	                        		{
	                        			read_second_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		
                    		//---------------------------------------Play the second line
                    		else if(current_character_position_line2 != string_second_line.length() && read_second_line == true)
                    		{
                    			Character current_character = string_second_line.charAt(current_character_position_line2);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_smile, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_smile, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line2 != string_second_line.length())
	                        		{
	                        			current_character_position_line2 = current_character_position_line2+1;
	                        		}
	                        		else
	                        		{
	                        			read_third_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		//---------------------------------------Play the third line
                    		else if(current_character_position_line3 != string_third_line.length() && read_third_line == true)
                    		{
                    			Character current_character = string_third_line.charAt(current_character_position_line3);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_smile, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_smile, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line3 != string_third_line.length())
	                        		{
	                        			current_character_position_line3 = current_character_position_line3+1;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		else
                    		{
                    			//Draw Bhola closed mouth
                    			context_gameover.drawImage(avatar_smile, 0, 0);
                    			
                    			// if press enter to go to next screen, reset values for next texts
                    			if (enter_key == true)
                        		{
                        			keyanimation_number = keyanimation_number+1;
                        			current_character_position_line1=0;
                        			current_character_position_line2=0;
                        			current_character_position_line3=0;
                        			System.out.println("Enter pressed");
                        		}
                    		}

                			//Write the text
                    		context_bubble_text.fillText(player_name, 35, player_name_y);
                    		context_bubble_text.fillText(string_first_line.substring(0,current_character_position_line1), 50, first_line);
                    		context_bubble_text.fillText(string_second_line.substring(0,current_character_position_line2), 50, second_line);
                    		context_bubble_text.fillText(string_third_line.substring(0,current_character_position_line3), 50, third_line);
            			}
            			else if(keyanimation_number == 14)
            			{		
            				System.out.println("Keyanimation_number : " + keyanimation_number);
            				
            				// Clear the screen
            				context_gameover.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text1.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text2.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_bubble_text.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				
            				// Draw the bubble background with Bhola
                			context_gameover.drawImage(chat_box_background, 0, 0);
                			
            				// write the content of the bubble text, one character at a time.
                    		String string_first_line = "All my life I've been running away from my troubles.";
                    		String string_second_line = "But not this time.";
                    		String string_third_line = "It is time for me to FIGHT.";
                    		
                    		//-------------------------------play the first line
                    		if(current_character_position_line1 != string_first_line.length())
                    		{
                    			Character current_character = string_first_line.charAt(current_character_position_line1);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				||
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_normal, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_normal, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line1 != string_first_line.length())
	                        		{
	                        			current_character_position_line1 = current_character_position_line1+1;
	                        		}
	                        		else
	                        		{
	                        			read_second_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		
                    		//---------------------------------------Play the second line
                    		else if(current_character_position_line2 != string_second_line.length() && read_second_line == true)
                    		{
                    			Character current_character = string_second_line.charAt(current_character_position_line2);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_normal, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_normal, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line2 != string_second_line.length())
	                        		{
	                        			current_character_position_line2 = current_character_position_line2+1;
	                        		}
	                        		else
	                        		{
	                        			read_third_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		//---------------------------------------Play the third line
                    		else if(current_character_position_line3 != string_third_line.length() && read_third_line == true)
                    		{
                    			Character current_character = string_third_line.charAt(current_character_position_line3);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_normal, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_normal, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line3 != string_third_line.length())
	                        		{
	                        			current_character_position_line3 = current_character_position_line3+1;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		else
                    		{
                    			//Draw Bhola closed mouth
                    			context_gameover.drawImage(avatar_normal, 0, 0);
                    			
                    			// if press enter to go to next screen, reset values for next texts
                    			if (enter_key == true)
                        		{
                        			keyanimation_number = keyanimation_number+1;
                        			current_character_position_line1=0;
                        			current_character_position_line2=0;
                        			current_character_position_line3=0;
                        			System.out.println("Enter pressed");
                        		}
                    		}

                			//Write the text
                    		context_bubble_text.fillText(player_name, 35, player_name_y);
                    		context_bubble_text.fillText(string_first_line.substring(0,current_character_position_line1), 50, first_line);
                    		context_bubble_text.fillText(string_second_line.substring(0,current_character_position_line2), 50, second_line);
                    		context_bubble_text.fillText(string_third_line.substring(0,current_character_position_line3), 50, third_line);
            			}
            			else if(keyanimation_number == 15)
            			{		
            				System.out.println("Keyanimation_number : " + keyanimation_number);
            				
            				// Clear the screen
            				context_gameover.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text1.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_gameover_text2.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				context_bubble_text.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            				
            				// Draw the bubble background with Bhola
                			context_gameover.drawImage(chat_box_background, 0, 0);
                			
            				// write the content of the bubble text, one character at a time.
                    		String string_first_line = "NO MATTER HOW MANY THEY ARE...";
                    		String string_second_line = "I WILL DEFEAT THEM ALL AND PROTECT WHAT I";
                    		String string_third_line = "CHERISH MOST IN THIS UNIVERSE !!!";
                    		
                    		//-------------------------------play the first line
                    		if(current_character_position_line1 != string_first_line.length())
                    		{
                    			Character current_character = string_first_line.charAt(current_character_position_line1);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				||
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_angry2, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_angry2, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line1 < string_first_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line1 != string_first_line.length())
	                        		{
	                        			current_character_position_line1 = current_character_position_line1+1;
	                        		}
	                        		else
	                        		{
	                        			read_second_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		
                    		//---------------------------------------Play the second line
                    		else if(current_character_position_line2 != string_second_line.length() && read_second_line == true)
                    		{
                    			Character current_character = string_second_line.charAt(current_character_position_line2);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_angry2, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_angry2, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line2 < string_second_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line2 != string_second_line.length())
	                        		{
	                        			current_character_position_line2 = current_character_position_line2+1;
	                        		}
	                        		else
	                        		{
	                        			read_third_line = true;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		//---------------------------------------Play the third line
                    		else if(current_character_position_line3 != string_third_line.length() && read_third_line == true)
                    		{
                    			Character current_character = string_third_line.charAt(current_character_position_line3);
                    		
	                    		if(current_character.compareTo(' ') != 0 
	                    				|| 
	                    				(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length()))
	                    		{
	                        		//Draw Bhola open mouth
	                        		context_gameover.drawImage(avatar_angry2, 0, 0);
	                    		}
	                    		else
	                    		{
	                    			//Draw Bhola closed mouth
	                    			context_gameover.drawImage(avatar_angry2, 0, 0);
	                    		}
	                    		
	                    		if(timeDouble - previous_time_printed_character > speed_printed_character && current_character_position_line3 < string_third_line.length())
	                    		{
	                        		//play the bip text bubble sound only when there is a character there.
	                        		if(current_character.compareTo(' ') != 0)
	                        		{
	                        			MediaPlayer mediaPlayer_audio_bip_text_bubble = new MediaPlayer(media_audio_bip_text_bubble);
	                        			mediaPlayer_audio_bip_text_bubble.setVolume(0.1);
	                            		mediaPlayer_audio_bip_text_bubble.play();
	                        		}
	
	                        		// increment character position only if we're not at the last letter
	                        		if(current_character_position_line3 != string_third_line.length())
	                        		{
	                        			current_character_position_line3 = current_character_position_line3+1;
	                        		}
	                        		previous_time_printed_character = timeDouble;
	                    		}
                    		}
                    		else
                    		{
                    			//Draw Bhola closed mouth
                    			context_gameover.drawImage(avatar_angry2, 0, 0);
                    			
                    			// if press enter to go to next screen, reset values for next texts
                    			if (enter_key == true)
                        		{
                        			keyanimation_number = keyanimation_number+1;
                        			current_character_position_line1=0;
                        			current_character_position_line2=0;
                        			current_character_position_line3=0;
                        			System.out.println("Enter pressed");
                        		}
                    		}

                			//Write the text
                    		context_bubble_text.fillText(player_name, 35, player_name_y);
                    		context_bubble_text.fillText(string_first_line.substring(0,current_character_position_line1), 50, first_line);
                    		context_bubble_text.fillText(string_second_line.substring(0,current_character_position_line2), 50, second_line);
                    		context_bubble_text.fillText(string_third_line.substring(0,current_character_position_line3), 50, third_line);
            			}
            		}
    			}
    		}
    	}
    }
    
    
    /* ------------------------ Scene Methods ----------------------------------------*/
    /*
     *  drawSprites
     *  Draws all the sprites in the game.
     *  
     * */
    public void drawSprites()
    {
    	/* Draw projectilesArray */   	
    	for (Projectile temp : projectilesArray)
    	{
    		context_game.drawImage(bullet, temp.x, temp.y);
		}
    	
    	/* Draw power ups */   
    	for (PowerUp temp : powerUpsArray)
    	{
    		context_game.drawImage(upgrade_bullet_plus, temp.x, temp.y);
		}
    	
    	/* ****************************** Draw enemies ***********************************************/ 
    	/*
    	 * if enemy is not destroyed draw its image
    	 * if its destroyed draw explosion sequence
    	 * and delete enemy at the end.
    	 */
    	if(enemiesArray.isEmpty() == false)
    	{
    		for (int k = enemiesArray.size()-1; k>=0 ; k--)
        	{
        		if(enemiesArray.get(k).destroyed == false)
        		{
        			context_game.drawImage(alien, enemiesArray.get(k).x, enemiesArray.get(k).y);
        		}
        		else
        		{	
        			double number_of_frames_to_stay = 5;
        			// play the sound of explosion first
        			if(enemiesArray.get(k).explodingSequenceState == 1)
        			{
        				MediaPlayer mediaPlayer_audio_enemy_explode = new MediaPlayer(media_audio_enemy_explode);
                        mediaPlayer_audio_enemy_explode.play();
        			}
        			// then display the differents explosions frames
        			if(enemiesArray.get(k).explodingSequenceState <= 1*number_of_frames_to_stay)
        			{
        				context_game.drawImage(explosion_64x64_1, enemiesArray.get(k).x, enemiesArray.get(k).y);
    					enemiesArray.get(k).explodingSequenceState += 1;
        			}
        			else if (enemiesArray.get(k).explodingSequenceState <= 2*number_of_frames_to_stay)
        			{
        				context_game.drawImage(explosion_64x64_2, enemiesArray.get(k).x, enemiesArray.get(k).y);
    					enemiesArray.get(k).explodingSequenceState += 1;
        			}
        			else if (enemiesArray.get(k).explodingSequenceState <= 3*number_of_frames_to_stay)
        			{
        				context_game.drawImage(explosion_64x64_3, enemiesArray.get(k).x, enemiesArray.get(k).y);
    					enemiesArray.get(k).explodingSequenceState += 1;
        			}
        			else if (enemiesArray.get(k).explodingSequenceState <= 4*number_of_frames_to_stay)
        			{
        				context_game.drawImage(explosion_64x64_4, enemiesArray.get(k).x, enemiesArray.get(k).y);
    					enemiesArray.get(k).explodingSequenceState += 1;
        			}
        			else if (enemiesArray.get(k).explodingSequenceState <= 5*number_of_frames_to_stay)
        			{
        				context_game.drawImage(explosion_64x64_5, enemiesArray.get(k).x, enemiesArray.get(k).y);
    					enemiesArray.get(k).explodingSequenceState += 1;
        			}
        			else if (enemiesArray.get(k).explodingSequenceState <= 6*number_of_frames_to_stay)
        			{
        				context_game.drawImage(explosion_64x64_6, enemiesArray.get(k).x, enemiesArray.get(k).y);
    					enemiesArray.get(k).explodingSequenceState += 1;
        			}
        			else if (enemiesArray.get(k).explodingSequenceState <= 7*number_of_frames_to_stay)
        			{
        				context_game.drawImage(explosion_64x64_7, enemiesArray.get(k).x, enemiesArray.get(k).y);
    					enemiesArray.get(k).explodingSequenceState += 1;
        			}
        			else
        			{
        				context_game.drawImage(explosion_64x64_8, enemiesArray.get(k).x, enemiesArray.get(k).y);
    					/* Delete enemy */
    					enemiesArray.remove(k);
        			}
        		}
    		}
    	}
    	
    	/* -----------------------------End draw enemies or destroy enemy animation ------------------------- */
    	
    	/* **************************** Start Draw planets OR destroy planet animation ************************/ 
    	if(planetsArray.isEmpty() == false)
    	{
    		for (int j = planetsArray.size()-1; j >= 0 ; j--)
        	{
    			// Draw normal image of planet if not destroyed
        		if(planetsArray.get(j).destroyed == false)
        		{
        			context_game.drawImage(planet, planetsArray.get(j).x, planetsArray.get(j).y);
        		}
        		// else play the animation of planet explosion
        		else
        		{	
        			double number_of_frames_to_stay = 5;
        			
        			// play the sound of explosion first
        			if(planetsArray.get(j).explodingSequenceState == 1)
        			{
        				MediaPlayer mediaPlayer_audio_planet_explode = new MediaPlayer(media_audio_planet_explode);
                        mediaPlayer_audio_planet_explode.play();
        			}
        			
        			// then display the differents explosions frames
        			if(planetsArray.get(j).explodingSequenceState <= 1*number_of_frames_to_stay)
        			{
        				context_game.drawImage(explosion_128x128_1, planetsArray.get(j).x, planetsArray.get(j).y);
    					planetsArray.get(j).explodingSequenceState += 1;
        			}
        			else if (planetsArray.get(j).explodingSequenceState <= 2*number_of_frames_to_stay)
        			{
        				context_game.drawImage(explosion_128x128_2, planetsArray.get(j).x, planetsArray.get(j).y);
    					planetsArray.get(j).explodingSequenceState += 1;
        			}
        			else if (planetsArray.get(j).explodingSequenceState <= 3*number_of_frames_to_stay)
        			{
        				context_game.drawImage(explosion_128x128_3, planetsArray.get(j).x, planetsArray.get(j).y);
    					planetsArray.get(j).explodingSequenceState += 1;
        			}
        			else if (planetsArray.get(j).explodingSequenceState <= 4*number_of_frames_to_stay)
        			{
        				context_game.drawImage(explosion_128x128_4, planetsArray.get(j).x, planetsArray.get(j).y);
    					planetsArray.get(j).explodingSequenceState += 1;
        			}
        			else if (planetsArray.get(j).explodingSequenceState <= 5*number_of_frames_to_stay)
        			{
        				context_game.drawImage(explosion_128x128_5, planetsArray.get(j).x, planetsArray.get(j).y);
    					planetsArray.get(j).explodingSequenceState += 1;
        			}
        			else if (planetsArray.get(j).explodingSequenceState <= 6*number_of_frames_to_stay)
        			{
        				context_game.drawImage(explosion_128x128_6, planetsArray.get(j).x, planetsArray.get(j).y);
    					planetsArray.get(j).explodingSequenceState += 1;
        			}
        			else if (planetsArray.get(j).explodingSequenceState <= 7*number_of_frames_to_stay)
        			{
        				context_game.drawImage(explosion_128x128_7, planetsArray.get(j).x, planetsArray.get(j).y);
    					planetsArray.get(j).explodingSequenceState += 1;
        			}
        			else
        			{
        				context_game.drawImage(explosion_128x128_8, planetsArray.get(j).x, planetsArray.get(j).y);   
        				planetsArray.remove(j);
        			}
        		}
    		}
    	}
    	
    	/* -------------------------------End draw planets or destroy planet animation------------------------------*/
    	
    	
    	/* ******************************* Start Draw player or destroy  player animation  ****************************
    	 * Draw the correct image depending on key pressed
    	 */
    	// Draw the correct image depending on state
    	if(right == true )
    	{
    		context_game.drawImage( ship1BoostRight, player.x, player.y );
    	}
    	
    	else if(left == true )
    	{
    		context_game.drawImage( ship1BoostLeft, player.x, player.y );
    	}
    	
    	else if(up==true )
    	{
    		context_game.drawImage( ship1BoostUp, player.x, player.y );
    	}
    	
    	else if(down==true)
    	{
    		context_game.drawImage( ship1BoostDown, player.x, player.y );
    	}
    	else
    	{
    		context_game.drawImage( ship1, player.x, player.y );
    	}
    	if (player.exploding == true)
		{	
			double number_of_frames_to_stay = 3;
			
			if(count_animation_explosion < 5)
			{
				// play the sound of explosion first
				if(player.explodingSequenceState == 1)
				{
					MediaPlayer mediaPlayer_audio_planet_explode = new MediaPlayer(media_audio_planet_explode);
	                mediaPlayer_audio_planet_explode.play();
				}
				
				// then display the different explosions frames
				if(player.explodingSequenceState <= 1*number_of_frames_to_stay)
				{
					context_game.drawImage(explosion_64x64_1, player.x, player.y);
					player.explodingSequenceState += 1;
				}
				else if (player.explodingSequenceState <= 2*number_of_frames_to_stay)
				{
					context_game.drawImage(explosion_64x64_2, player.x, player.y);
					player.explodingSequenceState += 1;
				}
				else if (player.explodingSequenceState <= 3*number_of_frames_to_stay)
				{
					context_game.drawImage(explosion_64x64_3, player.x, player.y);
					player.explodingSequenceState += 1;
				}
				else if (player.explodingSequenceState <= 4*number_of_frames_to_stay)
				{
					context_game.drawImage(explosion_64x64_4, player.x, player.y);
					player.explodingSequenceState += 1;
				}
				else if (player.explodingSequenceState <= 5*number_of_frames_to_stay)
				{
					context_game.drawImage(explosion_64x64_5, player.x, player.y);
					player.explodingSequenceState += 1;
				}
				else if (player.explodingSequenceState <= 6*number_of_frames_to_stay)
				{
					context_game.drawImage(explosion_64x64_6, player.x, player.y);
					player.explodingSequenceState += 1;
				}
				else if (player.explodingSequenceState <= 7*number_of_frames_to_stay)
				{
					context_game.drawImage(explosion_64x64_7, player.x, player.y);
					player.explodingSequenceState += 1;
				}
				else
				{
					context_game.drawImage(explosion_64x64_8, player.x, player.y); 
					player.explodingSequenceState = 1;
					count_animation_explosion = count_animation_explosion+1;
				}
			}
			if (count_animation_explosion == 5)
			{
				player.x = 100000;
				player.y = 100000;
				player.time_of_death = timeDouble;
				time_game_over = timeDouble;
				System.out.println("time game over : " + time_game_over);
				gameover = true;
				// play ending music media
				mediaPlayer_audio_ending_music.play();
			}
		}
    	
    	/*-------------------------End draw player or destroy player animation -----------------------*/	
    	
    	
    	/* ************************************** Start draw warning *********************************
    	/* Draw Warning if player out of bound*/
    	// if player out of bound top screen
    	if (player.y + player.size < 0 && player.exploding == false)
    	{
    		// alternate warning message every second
    		if((int)timeDouble%2 == 0)
    		{
    			context_game.drawImage( warning_message2, SCREENWIDTH/2-770/2, SCREENHEIGHT/3);
    		}
    		else
    		{
    			context_game.drawImage( warning_message1, SCREENWIDTH/2-770/2, SCREENHEIGHT/3);
    		}
    		// Draw the arrow indicating position of ship
    		context_game.drawImage(red_arrow_up, player.x, 0);
    		if(timeDouble - player.time_audio_warning > 1)
    		{
    			MediaPlayer mediaPlayer_audio_warning = new MediaPlayer(media_audio_warning);
    			mediaPlayer_audio_warning.play();
        		player.time_audio_warning = timeDouble;
    		}
    	}
    	// if player out of bound left screen
    	else if (player.x + player.size < 0 && player.exploding == false)
    	{
    		// alternate warning message every second
    		if((int)timeDouble%2 == 0)
    		{
    			context_game.drawImage( warning_message2, SCREENWIDTH/2-770/2, SCREENHEIGHT/3);
    		}
    		else
    		{
    			context_game.drawImage( warning_message1, SCREENWIDTH/2-770/2, SCREENHEIGHT/3);
    		}
    		context_game.drawImage(red_arrow_left, 0, player.y);
    		if(timeDouble - player.time_audio_warning > 1)
    		{
    			MediaPlayer mediaPlayer_audio_warning = new MediaPlayer(media_audio_warning);
    			mediaPlayer_audio_warning.play();
        		player.time_audio_warning = timeDouble;
    		}
    	}
    	// if player out of bound bottom screen
    	else if (player.y > SCREENHEIGHT && player.exploding == false)
    	{
    		// alternate warning message every second
    		if((int)timeDouble%2 == 0)
    		{
    			context_game.drawImage( warning_message2, SCREENWIDTH/2-770/2, SCREENHEIGHT/3);
    		}
    		else
    		{
    			context_game.drawImage( warning_message1, SCREENWIDTH/2-770/2, SCREENHEIGHT/3);
    		}
    		context_game.drawImage(red_arrow_down, player.x, SCREENHEIGHT-70);
    		if(timeDouble - player.time_audio_warning > 1)
    		{
    			MediaPlayer mediaPlayer_audio_warning = new MediaPlayer(media_audio_warning);
    			mediaPlayer_audio_warning.play();
        		player.time_audio_warning = timeDouble;
    		}
    	}
    	// if player out of bound right screen
    	else if (player.x > SCREENWIDTH && player.exploding == false)
    	{
    		// alternate warning message every second
    		if((int)timeDouble%2 == 0)
    		{
    			context_game.drawImage( warning_message2, SCREENWIDTH/2-770/2, SCREENHEIGHT/3);
    		}
    		else
    		{
    			context_game.drawImage( warning_message1, SCREENWIDTH/2-770/2, SCREENHEIGHT/3);
    		}
    		context_game.drawImage(red_arrow_right, SCREENWIDTH-70, player.y);
    		if(timeDouble - player.time_audio_warning > 1)
    		{
    			MediaPlayer mediaPlayer_audio_warning = new MediaPlayer(media_audio_warning);
    			mediaPlayer_audio_warning.play();
        		player.time_audio_warning = timeDouble;
    		}
    	}
    	/* ------------------------------- End draw warning --------------------------------*/
    }
    
    /*
     * checkForCollision
     * Check for collision between projectiles and planets 
     */
    public void checkForCollision()
    {
    	// Check collision between planets and projectile
    	if(planetsArray.isEmpty() == false)
    	{
    		for (int i = planetsArray.size()-1; i >= 0; i--) 
        	{
        		//TODO: fix
        		for (int j = projectilesArray.size()-1; j >= 0; j--)
        		{
        			if (projectilesArray.get(j).x > planetsArray.get(i).x 
        					&& projectilesArray.get(j).x < planetsArray.get(i).x + planetsArray.get(i).size 
        					&& projectilesArray.get(j).y > planetsArray.get(i).y 
        					&& projectilesArray.get(j).y < planetsArray.get(i).y + planetsArray.get(i).size)
        			{
        				planetsArray.get(i).health -= projectilesArray.get(j).damage;

        				MediaPlayer mediaPlayer_media_audio_hit_planet = new MediaPlayer(media_audio_hit_planet);
        			    mediaPlayer_media_audio_hit_planet.play();
        			    
        				projectilesArray.remove(j);
            			scorePlayer1 = scorePlayer1 + 200;
            		}
        			
        			/* Destroy planet if health is 0 or below */
        			if(planetsArray.get(i).health <= 0)
        			{
        				planetsArray.get(i).destroyed = true;
        			}
        		}
    		}
    	}
    	
    	
    	/* Check for collision between projectiles and enemies */   	
    	for (int i = 0; i < enemiesArray.size(); i++) 
    	{
    		for (int j = 0; j < projectilesArray.size(); j++)
    		{
    			if (projectilesArray.get(j).x > enemiesArray.get(i).x && projectilesArray.get(j).x < enemiesArray.get(i).x + enemiesArray.get(i).size && projectilesArray.get(j).y > enemiesArray.get(i).y && projectilesArray.get(j).y < enemiesArray.get(i).y + enemiesArray.get(i).size)
    			{
    				enemiesArray.get(i).destroyed = true;
        			projectilesArray.remove(j);		
        			scorePlayer1 = scorePlayer1 + 800;
        		}
    		}
    		
		}
    	
    	

    	
    	/* Check for collision between player and planets */  	
    	
    	for (int i = 0; i < planetsArray.size(); i++) 
    	{
    		if (
    			// check top left corner of ship
    			(
    				   player.x > planetsArray.get(i).x 
    			    && player.x < (planetsArray.get(i).x + planetsArray.get(i).size) 
    			    && player.y > planetsArray.get(i).y 
    			    && player.y < (planetsArray.get(i).y + planetsArray.get(i).size)
    			)
    			||
    			//check top right corner of ship
    			(
    				   (player.x + player.size) > planetsArray.get(i).x 
    	    		&& (player.x + player.size) < (planetsArray.get(i).x + planetsArray.get(i).size) 
    	    		&&  player.y > planetsArray.get(i).y 
    	    		&&  player.y < (planetsArray.get(i).y + planetsArray.get(i).size)
    			)
    			||
    			//check bottom left corner of ship
    			(
    				    player.x >  planetsArray.get(i).x 
    	    		&&  player.x < (planetsArray.get(i).x + planetsArray.get(i).size) 
    	    		&& (player.y + player.size) > planetsArray.get(i).y 
    	    		&& (player.y + player.size) < (planetsArray.get(i).y + planetsArray.get(i).size)
    			)
    			||
    			//check bottom right corner of ship
    			(
    				   (player.x + player.size) > planetsArray.get(i).x 
    	    		&& (player.x + player.size) < (planetsArray.get(i).x + planetsArray.get(i).size) 
    	    		&& (player.y + player.size) > planetsArray.get(i).y 
    	    		&& (player.y + player.size) < (planetsArray.get(i).y + planetsArray.get(i).size)
    			)
    		   )
    		{
    			
    			// Reduce life of the ship (player hits planet)
    			if (planetsArray.get(i).destroyed == false)
    			{
    				player.health -= planetsArray.get(i).damageIfCollision;
    			}
    				
    			// Set planet.destroyed to true
    			planetsArray.get(i).destroyed = true;
    		}
		}
    	
    	/* Check for collision between player and enemies */  	
    	for (int i = 0; i < enemiesArray.size(); i++) 
    	{
    		if (
    			// check top left corner of ship
    			(
    				  player.x >= enemiesArray.get(i).x 
    	  		   && player.x <= (enemiesArray.get(i).x + enemiesArray.get(i).size) 
    	  		   && player.y >= enemiesArray.get(i).y 
    	  		   && player.y <= (enemiesArray.get(i).y + enemiesArray.get(i).size)
    	    	)
    			||
    	    	// check top right corner of ship
    	    	(
    	    		 (player.x + player.size) >= enemiesArray.get(i).x 
    	    	  && (player.x + player.size) <= (enemiesArray.get(i).x + enemiesArray.get(i).size) 
    	    	  &&  player.y >= enemiesArray.get(i).y 
    	    	  &&  player.y <= (enemiesArray.get(i).y + enemiesArray.get(i).size)
    	    	)
    	    	||
    	    	// check bottom left corner of ship
    	    	(
    	    		  player.x >= enemiesArray.get(i).x 
    	    	  &&  player.x <= (enemiesArray.get(i).x + enemiesArray.get(i).size) 
    	    	  && (player.y + player.size) >= enemiesArray.get(i).y 
    	    	  && (player.y + player.size) <= (enemiesArray.get(i).y + enemiesArray.get(i).size)
    	    	)
    	    	||
    	    	// check bottom right corner of ship
    	    	(
    	    		 (player.x + player.size) >= enemiesArray.get(i).x 
    	    	  && (player.x + player.size) <= (enemiesArray.get(i).x + enemiesArray.get(i).size) 
    	    	  && (player.y + player.size) >= enemiesArray.get(i).y 
    	    	  && (player.y + player.size) <= (enemiesArray.get(i).y + enemiesArray.get(i).size)
    	    	)
    		   )
    		{
    			// Reduce life of the ship
    			if (enemiesArray.get(i).destroyed == false)
    			{
    				player.health -= enemiesArray.get(i).damageIfCollision;
    			}
    			
    			// Set enemy.destroyed to true
    			enemiesArray.get(i).destroyed = true;    			
    		}
		}
    	
    	/* Check for collision between player and PowerUps */ 
    	
    	for (int i = 0; i < powerUpsArray.size(); i++) 
    	{
    		powerUpsArray.get(i).x -= 1; //Make it move left.
    		if (
    			(
    				   player.x >= powerUpsArray.get(i).x 
    			    && player.x <= (powerUpsArray.get(i).x + powerUpsArray.get(i).size) 
    			    && player.y >= powerUpsArray.get(i).y 
    			    && player.y <= (powerUpsArray.get(i).y + powerUpsArray.get(i).size)
    			)
    			||
    			(
    				   (player.x + player.size) >= powerUpsArray.get(i).x 
    	    		&& (player.x + player.size) <= (powerUpsArray.get(i).x + powerUpsArray.get(i).size) 
    	    		&& (player.y + player.size) >= powerUpsArray.get(i).y 
    	    		&& (player.y + player.size) <= (powerUpsArray.get(i).y + powerUpsArray.get(i).size)
    			)
    		   )
    		{
    			
    			/* Add PowerUp Bonuses to player. */
    			player.bulletRate = player.bulletRate*0.9;
    			
    			player.health += powerUpsArray.get(i).bonusHealth;
    			System.out.println(player.health);
    			
    			/* ---------------------------------------------- */
    			// Play the sound
    			MediaPlayer mediaPlayer_audio_upgrade_pickup = new MediaPlayer(media_audio_upgrade_pickup);
    			mediaPlayer_audio_upgrade_pickup.play();
    		        
    		        
    			// Remove the powerup from the array
    			powerUpsArray.remove(i);
    		}
		}
    }
    
    /*
     * removeSpritesNoLongerOnCanvas
     * Whenever an sprite moves outside the canvas it is deleted.
     */
    public void removeSpritesNoLongerOnCanvas(){
    	
    	/* Remove planets no longer on the Screen */
    	for (int i = 0; i < planetsArray.size(); i++) 
    	{
    		if(
    		      planetsArray.get(i).x < -128 
    		   || planetsArray.get(i).x > (SCREENWIDTH + 128) 
    		   || planetsArray.get(i).y < -128 
    		   || planetsArray.get(i).y > (SCREENHEIGHT+128)
    		  )
    		{
    			planetsArray.remove(i);
    		}
		}
    	
    	/* Remove enemies no longer on the Screen */
    	for (int i = 0; i < enemiesArray.size(); i++) 
    	{
    		if(
    			   enemiesArray.get(i).x < -64 
    			|| enemiesArray.get(i).x > (SCREENWIDTH + 64) 
    			|| enemiesArray.get(i).y < -64 || enemiesArray.get(i).y > (SCREENHEIGHT+64)
    		)
    		{
    			enemiesArray.remove(i);
    		}
		}
    	
    	/* Remove power ups no longer on the Screen */
    	
    	for (int i = 0; i < powerUpsArray.size(); i++) 
    	{
    		if(
    			   powerUpsArray.get(i).x < -64 
    			|| powerUpsArray.get(i).x > (SCREENWIDTH + 64) 
    			|| powerUpsArray.get(i).y < -64 
    			|| powerUpsArray.get(i).y > (SCREENHEIGHT+64)
    		)
    		{
    			powerUpsArray.remove(i);
    		}
		}
    	
    	/* Remove projectiles no longer on the Screen */
    	for (int i = 0; i < projectilesArray.size(); i++) 
    	{
    		if(projectilesArray.get(i).x < -128 || projectilesArray.get(i).x > (SCREENWIDTH + 128) || projectilesArray.get(i).y < -128 || projectilesArray.get(i).y > (SCREENHEIGHT+128))
    		{
    			projectilesArray.remove(i);
    		}
		}
    		
    }

     /*
      * generateSprites
      * Creates all the enemies Power ups and planets
      */
    public void generateSprites()
    {
    	
    	/* Generate Sprites
    	 * This part is important. We definitely have to put this part as its own function.
    	 * Here is were we decide which enemy, planet and power up appears. 
    	 * When creating an enemy, for example:
    	 * 		Enemy tempEnemy = new Enemy(x,y);
    	 *		enemiesArray.add(tempEnemy); 
    	 * We can increase their speed, health, and damage the longer the game lasts. 
    	 * 
    	 */
    	
    	
    	/* Generate random planets */
    	/* Every 4 seconds, a new planet appears*/ 
    	if (time%4 == 0 && time != t_in_seconds)
    	{
    		Random r = new Random();
    		int low = SCREENWIDTH;
    		int high = SCREENWIDTH + 125;
    		int x = r.nextInt(high-low) + low;
    		
    		low = 0;
    		high = SCREENHEIGHT-125;
    		int y = r.nextInt(high-low) + low;
    		
    		Planet tempPlanet = new Planet(x,y);
    		planetsArray.add(tempPlanet);
    		
    	}
    	
    	/* Generate random enemy */
    	/* Every 3 seconds, a new enemy appears*/ 
    	if (time%3 == 0 && time != t_in_seconds)
    	{
    		Random r = new Random();
    		int low = SCREENWIDTH;
    		int high = SCREENWIDTH + 125;
    		int x = r.nextInt(high-low) + low;
    		
    		low = 0;
    		high =SCREENHEIGHT;
    		int y = r.nextInt(high-low) + low;
    		
    		Enemy tempEnemy = new Enemy(x,y);
    		enemiesArray.add(tempEnemy);
    		
    	}
    	
    	/* Generate random PowerUp */
    	/* Every 15 seconds, a new PowerUp appears*/ 
    	if (time%15 == 0 && time != t_in_seconds)
    	{
    		Random r = new Random();
    		int low = SCREENWIDTH;
    		int high = SCREENWIDTH + 125;
    		int x = r.nextInt(high-low) + low;
    		
    		low = 0;
    		high =SCREENHEIGHT;
    		int y = r.nextInt(high-low) + low;
    		
    		PowerUp tempPowerUp = new PowerUp(x,y);
    		powerUpsArray.add(tempPowerUp);
    		
    	}
    	
    	/* Every 3 seconds, a projectile is created for each enemy  */ 
    	
    	if (time%3 == 0 && time != t_in_seconds)
    	{
    		for (Enemy tempEnemy : enemiesArray)
        	{
    			System.out.println("Last Bullet Time = " + tempEnemy.lastBulletTime);
        		System.out.println("Current Time = " + timeDouble);
        		if ((timeDouble - tempEnemy.lastBulletTime) > 1)
        		{
        			double newBulletTime = timeDouble;
            		System.out.println(newBulletTime);
            		tempEnemy.setLastBulletTime(newBulletTime);
            		Projectile temp = new Projectile(tempEnemy.x+100, tempEnemy.y+100 ,tempEnemy.orientation, newBulletTime, player.Vx, player.Vy);
            		projectilesArray.add(temp);
        		}
    		}
    		
    	 }
    	
    	
    	/* Create projectiles */   	
    	
    	/* Player projectiles */
    	
    	if(spaceBar == true)
    	{    	
    		System.out.println("Last Bullet Time = " + player.lastBulletTime);
    		System.out.println("Current Time = " + timeDouble);
    		if ((timeDouble - player.lastBulletTime) > player.bulletRate)
    		{
    			double newBulletTime = timeDouble;
        		System.out.println(newBulletTime);
        		player.setLastBulletTime(newBulletTime);
        		Projectile temp = new Projectile(player.x+54, player.y+28 ,player.orientation, newBulletTime, player.Vx, player.Vy);
        		projectilesArray.add(temp);
        		// Play the background music
                MediaPlayer mediaPlayer_audio_shoot_bullet = new MediaPlayer(media_audio_shoot_bullet);
                mediaPlayer_audio_shoot_bullet.play();
    		}
    	}
    	
    }

    /*
     * Update position for sprites.
     */
    
    /* updateProjectiles
     * Nhat
     * */
    public void updateProjectiles()
    {
    	/* Update Projectiles */    	
    	for (Projectile temp_projectile : projectilesArray)
    	{
    		if (planetsArray.isEmpty() == false)
    		{
    			// time elapsed since last frame
    			double t_variable = (timeDouble-temp_projectile.bulletTimePreviousFrame)*10;
    			
    			//initialize Acceleration applied to bullets to 0
    			double Ax = 0;
    			double Ay = 0;
    			
    			//apply acceleration on pullets for each planet on screen
	    		for (Planet temp_planet : planetsArray)
	    		{
        			// Calculate the value of Acceleration applied on bullets for each planet
        			double x_difference = (temp_projectile.x - (temp_planet.x+64));
        			double y_difference = (temp_projectile.y - (temp_planet.y+64));
        			double distance = (Math.sqrt(Math.pow(x_difference, 2) + Math.pow(y_difference, 2)));
        			double G_constant = 100000;
        			temp_planet.acceleration_for_bullet =  G_constant/Math.pow(distance, 2);
        			double x_unitvector = x_difference/distance;
        			double y_unitvector = y_difference/distance;
        			Ax = Ax -temp_planet.acceleration_for_bullet*x_unitvector;
        			Ay = Ay -temp_planet.acceleration_for_bullet*y_unitvector;

	    		}
	    		// Set the new previous frame time to the current one
	    		temp_projectile.bulletTimePreviousFrame = timeDouble;  
	    		
	    		// Calculate the velocity components
	    		temp_projectile.Vx0 = temp_projectile.Vx0 + Ax*t_variable;
    			temp_projectile.Vy0 = temp_projectile.Vy0 + Ay*t_variable;
    			
    			// Calculate the position components
    			temp_projectile.x = temp_projectile.x + temp_projectile.Vx0*t_variable + 0.5*Ax*Math.pow(t_variable, 2);
    			temp_projectile.y = temp_projectile.y + temp_projectile.Vy0*t_variable + 0.5*Ay*Math.pow(t_variable, 2);
    		}
    		else
    		{
    			// Get the time elapsed since the previous frame
    			double t_variable = (timeDouble-temp_projectile.bulletTimePreviousFrame)*10;
    			
    			// update the position components
    			temp_projectile.x = temp_projectile.x + temp_projectile.Vx0*t_variable;
    			temp_projectile.y = temp_projectile.y + temp_projectile.Vy0*t_variable;
    			
    			System.out.println("-----Vx0 : " + temp_projectile.Vx0);
    			System.out.println("-----Vy0 : " + temp_projectile.Vy0);
    			
    			temp_projectile.bulletTimePreviousFrame = timeDouble;
    		}
		}
    }
    /*
     * updateEnemies
     * Eduardo
     */
    public void updateEnemies()
    {
    	/* --------------- Update enemies positions ---------------------- */ 
    	/* TODO This and when enemies shoot make the enemy AI.
    	 * We should put them together when we have everything working.
    	 */
    	if (enemiesArray.isEmpty() == false)
    	{
    		for (int i = enemiesArray.size()-1 ; i>=0 ; i--)
        	{	
        		/* Make enemy move forward */
    			enemiesArray.get(i).x -= 2;
        		/* Chase player and avoid planets */
        		/* Check if there is a planet obstructing. */
        		Boolean planetObstructing = false;
        		for (int j = planetsArray.size()-1 ; j >= 0 ; j--) 
            	{
            		if (
            			(
            				enemiesArray.get(i).x > (planetsArray.get(j).x -25) // 25 pixels is the closest the enemy is going to get to any planet. 
            			    && enemiesArray.get(i).x < (planetsArray.get(j).x + (planetsArray.get(j).size +25)) 
            			    && enemiesArray.get(i).y > (planetsArray.get(j).y -25)
            			    && enemiesArray.get(i).y < (planetsArray.get(j).y + (planetsArray.get(j).size + 25))
            			)
            			||
            			(
            				   (enemiesArray.get(i).x + enemiesArray.get(i).size) > (planetsArray.get(j).x -25) 
            	    		&& (enemiesArray.get(i).x + enemiesArray.get(i).size) < (planetsArray.get(j).x + (planetsArray.get(j).size +25)) 
            	    		&& (enemiesArray.get(i).y + enemiesArray.get(i).size) > (planetsArray.get(j).y -25)
            	    		&& (enemiesArray.get(i).y + enemiesArray.get(i).size) < (planetsArray.get(j).y + (planetsArray.get(j).size + 25))
            			)
            		   )
            		{	
            			
            			/* Set planetObstructing */
            			planetObstructing = true;
            			
            			/* Make enemy avoid planet */
            			if
              		  	(
              		  		(enemiesArray.get(i).y + enemiesArray.get(i).size) > planetsArray.get(j).y
              		  	)
            			{
            				// Make enemy move to the opposite direction of where the planet is.
            				enemiesArray.get(i).y += enemiesArray.get(i).speed;
            				/* Because the enemy is faster than the planets it can still collide.
            				 * Make it stop so it does not collide.
            				 */
            				enemiesArray.get(i).x += 2;
            			}
            			else if
            			(
            					enemiesArray.get(i).y + enemiesArray.get(i).size < planetsArray.get(j).y
            			)
            			{
            				// Make enemy move to the opposite direction of where the planet is.
            				enemiesArray.get(i).y -= enemiesArray.get(i).speed;
            				
            				/* Because the enemy is faster than the planets it can still collide.
            				 * Make it stop so it does not collide.
            				 */
            				enemiesArray.get(i).x += 2;
            			}
            			
            		}
        		}
        		
        		/* Chase players */
        		if(planetObstructing)
        		{
        			// Do not delete this if.
        			for (int j = planetsArray.size()-1 ; j >= 0 ; j--) 
                	{
                		if (
                			(
                					enemiesArray.get(i).x > (planetsArray.get(j).x)
                			    && enemiesArray.get(i).x < (planetsArray.get(j).x + (planetsArray.get(j).size)) 
                			    && enemiesArray.get(i).y > (planetsArray.get(j).y)
                			    && enemiesArray.get(i).y < (planetsArray.get(j).y + (planetsArray.get(j).size))
                			)
                			||
                			(
                				   (enemiesArray.get(i).x + enemiesArray.get(i).size) > (planetsArray.get(j).x) 
                	    		&& (enemiesArray.get(i).x + enemiesArray.get(i).size) < (planetsArray.get(j).x + (planetsArray.get(j).size)) 
                	    		&& (enemiesArray.get(i).y + enemiesArray.get(i).size) > (planetsArray.get(j).y)
                	    		&& (enemiesArray.get(i).y + enemiesArray.get(i).size) < (planetsArray.get(j).y + (planetsArray.get(j).size))
                			)
                		   )
                		{
                			//Delete enemy.
                			enemiesArray.remove(i);
                		}
                	}
        		}
        		else if
        		  (
        			player.y > enemiesArray.get(i).y
        		  )
        		{
        			enemiesArray.get(i).y += enemiesArray.get(i).speed;
        		}
        		else if
    			(
    				player.y + player.size < enemiesArray.get(i).y
    			)
        		{
        			enemiesArray.get(i).y -= enemiesArray.get(i).speed;
        		}
        			
    		}
    	}
    	
    	/* ----------------- End Update enemies positions -------------------------*/ 
    }
    
    /*
     * updatePlayer
     * Nhat
     * */
    public void updatePlayer()
    {
    	/* Handle Player */
    	//Update position
        double velocity_variable = 0.3;
    	if(up == true && control == true)
    	{
    		player.Vy = player.Vy - velocity_variable;
    		player.orientation = "up";
    	}
    	if(down == true && control == true)
    	{
    		player.Vy = player.Vy + velocity_variable;
    		player.orientation = "down";
    	}
    	if(left == true && control == true)
    	{
    		player.Vx = player.Vx - 1.5*velocity_variable;
    		player.orientation = "left";
    	}
    	if(right == true && control == true)
    	{
    		player.Vx = player.Vx + velocity_variable;
    		player.orientation = "right";
    	}
    	
    	
    	
    	/* apply gravity on ship with planets */
    	if (planetsArray.isEmpty() == false)
		{
			// time elapsed since last frame
			double t_variable = (timeDouble-player.shipTimePreviousFrame)*10;
			
			//initialize Acceleration applied to ship to 0
			double Ax_calculated = 0;
			double Ay_calculated = 0;
			double distance;
			
			//apply acceleration on ship for each planet on screen
    		for (Planet temp_planet : planetsArray)
    		{
    			// Calculate the value of Acceleration applied on bullets for each planet
    			double x_difference = (player.x - (temp_planet.x+64));
    			double y_difference = (player.y - (temp_planet.y+64));
    			distance = (Math.sqrt(Math.pow(x_difference, 2) + Math.pow(y_difference, 2)));
    			if (distance <350)
    			{
    				distance = 350;
    			}
    			double G_constant_for_ship = 100000;
    			temp_planet.acceleration_for_ship =  G_constant_for_ship/Math.pow(distance, 2);
    			double x_unitvector = x_difference/distance;
    			double y_unitvector = y_difference/distance;
    			Ax_calculated = Ax_calculated -temp_planet.acceleration_for_ship*x_unitvector;
    			Ay_calculated = Ay_calculated -temp_planet.acceleration_for_ship*y_unitvector;
    		}
    		
    		
    		// Set the new previous frame time to the current one
    		player.shipTimePreviousFrame = timeDouble;  
    		
    		// Set the Acceleration calculated to the ship
    		player.Ax = Ax_calculated;
    		player.Ay = Ay_calculated;
    		
    		// Calculate the velocity components of the ship
    		player.Vx = player.Vx + player.Ax*t_variable;
			player.Vy = player.Vy + player.Ay*t_variable;
			
			// Calculate the position components of the ship
			player.x = player.x + player.Vx*t_variable + 0.5*player.Ax*Math.pow(t_variable, 2);
			player.y = player.y + player.Vy*t_variable + 0.5*player.Ay*Math.pow(t_variable, 2);
		}
		else
		{
			// Set the new previous frame time to the current one
    		player.shipTimePreviousFrame = timeDouble;
		}
    }
    
    /*
     * updatePlanetsAndPowerUps
     * Eduardo
     */
    public void updatePlanetsAndPowerUps()
    {
    	
    	/* Update planets positions */   	
    	for (Planet temp : planetsArray)
    	{
    		temp.x -= 1;
		}
    	
    	
    	
    	/* Update power ups positions */ 
    	
    	for (PowerUp temp : powerUpsArray)
    	{
    		temp.x -= 1;
		}
    	
    }
    public void checkPlayerLife()
    {
		// If the ship goes below 0, set game over.
		if(player.health <= 0)
		{
			mediaPlayer_background_music.stop();
			player.exploding = true;
			control = false;
		}
    }

}
/* -----------------------------end of public class GravityX extends Application ----------------*/



