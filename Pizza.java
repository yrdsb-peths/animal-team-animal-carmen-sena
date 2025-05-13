import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Food for our Monkey.
 * 
 * @author Carmen Cheung 
 * @version May 12, 2025
 */
public class Pizza extends Actor
{
    /**
     * Act - do whatever the pizza wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int speed = 1; 
    public void act()
    {
        GreenfootImage image = new GreenfootImage("pizza2.png");
        image.scale(50, 50);
        setImage(image); 
        //Let the pizza fall.
        int x = getX();
        int y = getY() + speed;
        setLocation(x, y);
        
        //Remove pizza and draw the game Over when pizza gets to bottom
        MyWorld world = (MyWorld)getWorld();
        if(getY() >= world.getHeight())
        {
            world.gameOver();
            world.removeObject(this); 
        }
    }
    
    public void setSpeed (int spd)
    {
        speed = spd; 
    }
}
