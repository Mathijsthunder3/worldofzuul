import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
import java.util.ArrayList;
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private int maxGewicht;
    private ArrayList pItems;
    private Item zwaard;
    private Item schild;
    private Item healPotion;
    private Item kaart;
    private Item boog;
    private Item pijl;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        maxGewicht=100;
        createRooms();
        parser = new Parser();
        pItems= new ArrayList<Item>();
        addItems();
        parser = new Parser();
        previousRooms = new Stack<>();
    }
    public void addItems(){
        zwaard = new Item(15,"het zwaard van je mama");
        pItems.add(zwaard);
        schild = new Item(30,"het schild van je mama");
        pItems.add(schild);
        healPotion = new Item(5, "het herlevingsdrankje");
        pItems.add(healPotion);
        kaart = new Item(20, "maakt het mogelijk terug te lopen");
        pItems.add(kaart);
        boog = new Item(15, "om de monstertjes een beetje pijn te doen");
        pItems.add(boog);
        pijl = new Item(2, "iedere 2 gewicht van dit item is een pijl");
        pItems.add(pijl);
        
    }
  
   /**
    * Goes back to the previous room
    */
   public void back()
   {
        currentRoom = previousRooms.pop();
   }
    

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // initialise room exits
        outside.setExits(null, theater, lab, pub);
        theater.setExits(null, null, null, outside);
        pub.setExits(null, outside, null, null);
        lab.setExits(outside, office, null, null);
        office.setExits(null, null, null, lab);

        currentRoom = outside;  // start game outside
   }

   /**
    *  Main play routine.  Loops until end of play.
    */
    
   public void play() 
   {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
   }

   /**
    * Print out the opening message for the player.
    */
   private void printWelcome()
   {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        
       printLocationInfo();
   }

   /**
    * Given a command, process (that is: execute) the command.
    * @param command The command to be processed.
    * @return true If the command ends the game, false otherwise.
    */
   private boolean processCommand(Command command) 
   {
        boolean wantToQuit = false;

        if(command.isUnknown())
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
        {
            printHelp();
        }
        else if (commandWord.equals("go"))
        {
            goRoom(command);
        }
        else if (commandWord.equals("quit"))
        {
            wantToQuit = quit(command);
        } else if (commandWord.equals("back"))
        {
            back();
        }
        else if (commandWord.equals("look")){
            getLongDescription();
        }

        return wantToQuit;
   }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void getLongDescription(){
        
    }
        
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.getCommandWords().showCommands();
    }
    private void printLocationInfo()
   {
    System.out.println(currentRoom.getExits().keySet());
      if(currentRoom.getExit("north") != null) {
                System.out.print("north ");
      }
      if(currentRoom.getExit("east") != null)
      {
                System.out.print("east ");
      }
      if(currentRoom.getExit("south") != null)
      {
                System.out.print("south ");
      }
      if(currentRoom.getExit("west") != null)
      {
                System.out.print("west ");
      }
      System.out.println();
    }
    
   /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
   private void goRoom(Command command) 
   {
        if(!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("north")){
            nextRoom = currentRoom.getDir("north");
            nextRoom = currentRoom.getExit("north");
        }
        if(direction.equals("east")){
            nextRoom = currentRoom.getDir("east");
            nextRoom = currentRoom.getExit("east");
        }
        if(direction.equals("south")){
            nextRoom = currentRoom.getDir("south");
            nextRoom = currentRoom.getExit("south");
        }
        if(direction.equals("west")){
            nextRoom = currentRoom.getDir("west");
            nextRoom = currentRoom.getExit("west");
        }

        if (nextRoom == null)
        {
            System.out.println("There is no door!");
        }
        else 
        {
            currentRoom = nextRoom;
            System.out.println("You are " + currentRoom.getDescription());
            System.out.print("Exits: ");
            printLocationInfo();
            if(nextRoom.isVisited()==false){
                roomItems();
            }
        }
   }
   public int randomNumber(int max){
       return(int)(Math.random() * max) + 0;
    }
   public void roomItems(){
       int i = pItems.size()/
       int aantalItems = randomNumber(i);
       if(aantalItems >3){
        }

   }
   /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
   private boolean quit(Command command) 
   {
        if(command.hasSecondWord())
        {
            System.out.println("Quit what?");
            return false;
        }
        else
        {
            return true;  // signal that we want to quit
        }
   }
}
