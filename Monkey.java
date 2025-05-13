import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Monkey, our hero
 * 
 * @author Sena Godek 
 * @version May 2025
 */
public class Monkey extends Actor
{
    GreenfootImage[] idleRight = new GreenfootImage[4];
    GreenfootImage[] idleLeft = new GreenfootImage[4];
    
    //Direction the Monkey is facing
    String facing = "right";
    SimpleTimer animationTimer = new SimpleTimer();
    /**
    * Constructor - The code that gets run   
    */
    public Monkey()
    {
        for(int i = 0; i < idleRight.length; i++)
        {
            idleRight[i] = new GreenfootImage("images/monkey_idle/idle" + (i + 1) + ".png");
            idleRight[i].scale(100,100);
        }
        
        for(int i = 0; i < idleLeft.length; i++)
        {
            idleLeft[i] = new GreenfootImage("images/monkey_idle/idle" + (i + 1) + ".png");
            idleLeft[i].mirrorHorizontally();
            idleLeft[i].scale(100,100);
        }
        
        animationTimer.mark();
        
        //initial Monkey image
        setImage(idleRight[0]);
    }
    
    /**
    * Animate the Monkey   
    */
   
    int imageIndex = 0;
    public void animateMonkey()
    {
        if(animationTimer.millisElapsed() < 170)
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
        
        //remove Pizza of Monkey eatsit
        eat();
        
        // Animate the Monkey
        animateMonkey();
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
        }    
    }
}