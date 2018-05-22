



/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
import java.util.HashMap;
import java.util.ArrayList;
public class Room 
{

    private HashMap<String,Room> exits;
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private int itemweight;
    private String itemdescription;
    private ArrayList items;
    private boolean visited;
    
    /**
     * returns north or east or south or west
     */
    public boolean isVisited(){
        return visited;
    }
    public Room(){
        visited=false;
        items= new ArrayList<Item>();
    }
    /**
     * returns north or east or south or west
     */
    public void addItem(Item item){
        items.add(item);
    }
    public ArrayList getItems(){
        return items;
    }
    public void removeItem(int i){
        items.remove(i);
    }
    public Room getExit(String direction)
    {
        if(direction == "north")
        {
            return northExit; 
        }
        else if(direction == "east")
        {
            return eastExit;
        }
        else if(direction == "south")
        {
            return southExit;
        }
        else if(direction == "west")
        {
            return westExit;
        }
        return null;
    }
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room getDir(String direction)
    {
        return exits.get(direction);
    }
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String,Room>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public HashMap getExits(){
        return exits;
    }
   
    
   
    public void setExits(Room north, Room east, Room south, Room west) 
    {
        if(north !=null){
        exits.put("north",north);
    }
    if(east !=null){
        exits.put("east",east);
    }
    if(west !=null){
        exits.put("west",west);
    }
    if(south !=null){
        exits.put("south",south);
    }
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

}
