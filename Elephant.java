import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Elephant, our hero
 * 
 * @author Sena Godek 
 * @version May 2025
 */
public class Elephant extends Actor
{
    GreenfootSound elephantSound = new GreenfootSound("elephantcub.mp3");
    GreenfootImage[] idleRight = new GreenfootImage[8];
    GreenfootImage[] idleLeft = new GreenfootImage[8];
    
    //Direction the elephant is facing
    String facing = "right";
    SimpleTimer animationTimer = new SimpleTimer();
    /**
    * Constructor - The code that gets run   
    */
    public Elephant()
    {
        for(int i = 0; i < idleRight.length; i++)
        {
            idleRight[i] = new GreenfootImage("images/elephant_idle/idle" + i + ".png");
            idleRight[i].scale(100,100);
        }
        
        for(int i = 0; i < idleLeft.length; i++)
        {
            idleLeft[i] = new GreenfootImage("images/elephant_idle/idle" + i + ".png");
            idleLeft[i].mirrorHorizontally();
            idleLeft[i].scale(100,100);
        }
        
        animationTimer.mark();
        
        //initial elephant image
        setImage(idleRight[0]);
    }
    
    /**
    * Animate the elephant   
    */
   
    int imageIndex = 0;
    public void animateElephant()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        
        if(facing.equals("right"))
        {
            setImage(idleRight[imageIndex]);
            imageIndex = (imageIndex + 1) % idleRight.length;
        }
        else
        {
            setImage(idleLeft[imageIndex]);
            imageIndex = (imageIndex + 1) % idleLeft.length;
        }
    }
    
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.isKeyDown("left")){
            move(-2);
            facing = "left";
        }
        else if(Greenfoot.isKeyDown("right")){
            move(2);
            facing = "right";
        }
        
        //remove Pizza of elephant eatsit
        eat();
        
        // Animate the elephant
        animateElephant();
    }
    
    /**
    * Eats the Pizza and spawn new Pizza if an Pizza is eaten
    */
    public void eat(){
        if(isTouching(Pizza.class)){
            removeTouching(Pizza.class);
            MyWorld world = (MyWorld) getWorld();
            world.createPizza();
            world.increaseScore();
            elephantSound.play();
        }    
    }
}