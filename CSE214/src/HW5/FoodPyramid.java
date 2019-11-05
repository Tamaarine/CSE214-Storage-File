package HW5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The FoodPyramid class will be the main driver application of the ternary 
 * organism tree. It will contain a OrganismTree member variable that is named
 * tree which will be the one that the user will be interacting with through a list
 * of commands. At the start of the program, the user will be asked about the apex
 * predator to get information about its name and diet in order to begin the organism
 * tree, and cannot be changed after the user have entered it. The list of commands
 * are.
 * <p>
 * (PC) - Create New Plant Child
 * <p>
 * (AC) -Create New Animal Child
 * <p>
 * (RC) - Remove Child
 * <p>
 * (P) - Print out Cursor's Prey
 * <p>
 * (C) - Print Out Food Chain
 * <p>
 * (F) - Print Out Food Pyramid at Cursor
 * <p>
 * (LP) - List All Plants Supporting Cursor
 * <p>
 * (R) - Reset Cursor to Root
 * <p>
 * (M) - Move Cursor to Child
 * <p>
 * (Q) - Quit
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class FoodPyramid
{
    //This OrganismTree will serve as the main OrganismTree that the user will
    //be able to interacting with
    private OrganismTree tree;
    
    /**
     * Default constructor of the FoodPyramid, it will take in an OrganismNode
     * which is the apexPredator and start the OrganismTree with it
     * 
     * @param apexPredator 
     *  The apex predator to start the tree with
     */
    public FoodPyramid(OrganismNode apexPredator)
    {
        tree=new OrganismTree(apexPredator);
    }
    
    /**
     * The main method will start the program and contain a menu screen that
     * allow the user to interact various function with the OrganismTree
     * 
     * @param args 
     *  args contains the text from the command line that starts the program
     */
    public static void main(String [] args)
    {
        //InputStreamReader and BufferedReader work together to receive the
        //user input from the keyboard
        InputStreamReader input=new InputStreamReader(System.in);
        BufferedReader reader=new BufferedReader(input);
        
        String userInput="";
        
        //This boolean represent whether or not the user have finish with the
        //program or not
        boolean isFinished=false;
        
        //apexValidInput are the boolean to check whether or not the finish have
        //finished making the apexPredator for the start of the OrganismTree
        boolean apexValidInput=false;
        
        //This is before the consturction of the food pyramid. Asking the user for
        //an apex predator to start the food pyramid
        OrganismNode apexPredator=new OrganismNode();
        
        //Information about the apex predator
        String apexName="";
        boolean apexIsHerbivore=false;
        boolean apexIsCarnivore=false;
        
        //Asking the user for the name of the apex predator
        System.out.print("What is the name of the apex predator?: ");
        try
        {
            apexName=reader.readLine();
        }
        catch(IOException i)
        {
            System.out.println("Error when entering in the name of the apex predator");
        }
        
        //This will keep asking the user for a valid input of the apex predator
        while(!apexValidInput)
        {
            System.out.print("Is the organism an herbivore / a carnivore / an omnivore?"
                    + " (H / C / O): ");
            try
            {
                userInput=reader.readLine();
            }
            catch(IOException i)
            {
                System.out.println("Error when entering in the apex predator's traits");
            }
            
            //This means that the user have entered in H as the apex predator's
            //diet habit which is herbivore
            if(userInput.equalsIgnoreCase("H"))
            {
                apexIsHerbivore=true;
                apexIsCarnivore=false;
                
                apexValidInput=true;
            }
            //This means that the user have entered in C as the apex predator's
            //diet habit which is carnivore
            else if(userInput.equalsIgnoreCase("C"))
            {
                apexIsCarnivore=true;
                apexIsHerbivore=false;
                
                apexValidInput=true;
            }
            //This means that the user have entered in O as the apex predator's
            //diet habit which is omnivore that eats both animal and plants
            else if(userInput.equalsIgnoreCase("O"))
            {
                apexIsCarnivore=true;
                apexIsHerbivore=true;
                
                apexValidInput=true;
            }
            else
            {
                System.out.println("Invalid apex predator diet option, please try again\n");
            }
        }
        
        //After the while loop we have all of the informations to construct the
        //apex predator of the food pyramid
        apexPredator.setName(apexName);
        apexPredator.setIsCarnivore(apexIsCarnivore);
        apexPredator.setIsHerbivore(apexIsHerbivore);
        
        //It is definitely not a plant because it is a apex predator, plants can't
        //be predator in our food pyramid
        apexPredator.setIsPlant(false);
        
        System.out.println("\nConstructing food pyramid...\n");

        //Starting the organism tree with our apex predator
        OrganismTree foodTree=new OrganismTree(apexPredator);
        
        //Making Prgoram
        while(!isFinished)
        {
            //Menu options
            System.out.println("Menu:\n"
                    + "(PC) - Create New Plant Child\n"
                    + "(AC) - Create New Animal Child\n"
                    + "(RC) - Remove Child\n"
                    + "(P) - Print Out Cursor's Prey\n"
                    + "(C) - Print Out Food Chain\n"
                    + "(F) - Print Out Food Pyramid at Cursor\n"
                    + "(LP) - List All Plant's Supporting Cursor\n"
                    + "(R) - Reset Cursor to Root\n"
                    + "(M) - Move Cursor to Child\n"
                    + "(Q) - Quit\n");
            
            //Asking the user for a option input
            System.out.print("Please select an option: ");
            try
            {
                userInput=reader.readLine();
            }
            catch(IOException i)
            {
                System.out.println("Error when inputting menu options");
            }

            //This means that the user entered in PC which means to create a new plant
            //child and add it to the cursor node
            if(userInput.equalsIgnoreCase("PC"))
            {
                //These four boolean variables are used to whether or not to
                //proceed to add the new plant node to the cursor
                boolean isAPlant=foodTree.getCursor().getIsPlant();
                boolean cursorFull=foodTree.cursorIsFull();
                boolean isAHerbivore=foodTree.getCursor().getIsHerbivore();
                boolean isACarnivore=foodTree.getCursor().getIsCarnivore();
                
                //This means that the cursor is currently full of nodes therefore
                //we tell the user that we cannot add anymore nodes to it
                if(cursorFull)
                {
                    System.out.println("ERROR: There is no more room for more prey"
                            + " for this predator.\n");
                }
                //Otherwise we keep checking using the other booleans
                else
                {
                    //This means that the cursor is currently on a plant OrganismNode
                    //thus it cannot be added a prey to, therefore we display the approriate
                    //message
                    if(isAPlant)
                    {
                        System.out.println("ERROR: The cursor is at a plant node. Plants"
                                + " cannot be predators.\n");
                    }
                    //Then we keep going to check with the diet of the cursor
                    else
                    {
                        //This means that the animal is either omnivore or
                        //herbivore therefore we can add the new plant node to it
                        if(isACarnivore&&isAHerbivore||(isAHerbivore))
                        {
                            //Asking the user for the name of the plant organism
                            System.out.print("What is the name of the organism?: ");
                            try
                            {
                                userInput=reader.readLine();
                            }
                            catch(IOException i)
                            {
                                System.out.println(i);
                            }

                            //Adding the plant child to the foodTree at the cursor
                            try
                            {
                                foodTree.addPlantChild(userInput);
                            }
                            catch(PositionNotAvailableException p)
                            {
                                System.out.println(p+"\n");
                            }
                            catch(IllegalArgumentException i)
                            {
                                System.out.println(i+"\n");
                            }
                        }
                        //But otherweise this means that the cursor is not a
                        //omnivore or is a carnivore therefore we don't add the
                        //new plant and tell the user about it
                        else
                        {
                            //Error
                            System.out.println("ERROR: This prey cannot be added as it"
                                    + " does not match the diet of the predator.\n");
                        }
                    }
                }
            }
            //This means that the user entered in AC which means to create a new
            //animal child and add it to the cursor
            else if(userInput.equalsIgnoreCase("AC"))
            {
                //Information about the new animal cursor that is going to be added
                String organismName="";
                boolean validInput=false;
                boolean organismIsHerbivore=false;
                boolean organismIsCarnivore=false;
                
                //These four boolean variables are used to determine whether to
                //proceed to ask the user about the new animal node to add to
                //the cursord
                boolean cursorFull=foodTree.cursorIsFull();
                boolean isAPlant=foodTree.getCursor().getIsPlant();
                boolean isAHerbivore=foodTree.getCursor().getIsHerbivore();
                boolean isACarnivore=foodTree.getCursor().getIsCarnivore();
                
                //This means that the cursor is currently full of nodes therefore
                //we tell the user that we cannot add anymore nodes to it
                if(cursorFull)
                {
                    System.out.println("ERROR: There is no more room for more prey"
                            + " for this predator.\n");
                }
                //Otherwise we keep checking
                else
                {
                    //This means that the cursor is currently a plant therefore
                    //we cannot add any prey to it
                    if(isAPlant)
                    {
                        System.out.println("ERROR: The cursor is at a plant node. Plants"
                                + " cannot be predators.\n");
                    }
                    //Otherweise we continue checking
                    else
                    {
                        //This means that the cursor is either a omnivore or a 
                        //carnivore so adding a new animal node to it is okay
                        if(isAHerbivore&&isACarnivore||(isACarnivore))
                        {
                            //Asking the name of the prey
                            System.out.print("What is the name of the organism?: ");
                            try
                            {
                                organismName=reader.readLine();
                            }
                            catch(IOException i)
                            {
                                System.out.println("Error when entering the name"
                                        + " of a organism to be added\n");
                            }

                            //This for loop will be keep asking the user for a valid option for the
                            //animal that is going to be added to the cursor. It will keep on
                            //repeating until the user have inputted a correct valid choice
                            while(!validInput)
                            {
                                //Prompting the user for a input
                                System.out.print("Is the organism an herbivore /"
                                        + " a carnivore / an omnivore?"
                                        + " (H / C / O): ");
                                try
                                {
                                    userInput=reader.readLine();
                                }
                                catch(IOException i)
                                {
                                    System.out.println("Error when entering the charteristics"
                                            + " of the organism\n");
                                }

                                //This means that the user have picked the organism to be a herbivore
                                if(userInput.equalsIgnoreCase("H"))
                                {
                                    organismIsHerbivore=true;
                                    organismIsCarnivore=false;

                                    validInput=true;
                                }
                                //This means that the user have picked the organism to be a 
                                //carnivore
                                else if(userInput.equalsIgnoreCase("C"))
                                {
                                    organismIsHerbivore=false;
                                    organismIsCarnivore=true;

                                    validInput=true;
                                }
                                //This means that the user have picked the organism to be a
                                //omnivore
                                else if(userInput.equalsIgnoreCase("O"))
                                {
                                    organismIsHerbivore=true;
                                    organismIsCarnivore=true;

                                    validInput=true;
                                }
                                //Telling the user that they have enter in a 
                                //invalid option
                                else
                                {
                                    System.out.println("Invalid apex predator diet"
                                            + " option, please try again\n");
                                }
                                
                            }

                            //After the while loop then we have all of the information that we
                            //need to add the new organism node to the cursor
                            try
                            {
                                foodTree.addAnimalChild(organismName, organismIsHerbivore,
                                        organismIsCarnivore);
                            }
                            catch(PositionNotAvailableException p)
                            {
                                System.out.println(p+"\n");
                            }
                            catch(IllegalArgumentException i)
                            {
                                System.out.println(i+"\n");
                            }
                        }
                        //But this means that the animal is not omnivore or is 
                        //a herbivore therefore we have to tell the user that
                        //they cannot add any prey to it
                        else
                        {
                            //Error
                            System.out.println("ERROR: This prey cannot be added as it"
                                    + " does not match the diet of the predator.\n");
                        }
                    }
                }
            }
            //This means that the user have entered in RC which means to remove
            //a child node of the cursor based on the inputted name
            else if(userInput.equalsIgnoreCase("RC"))
            {
                boolean cursorEmpty=foodTree.cursorIsEmpty();
                
                //Again this means that the cursor have no prey then there
                //is no point in entering any names
                if(cursorEmpty)
                {
                    System.out.println("The cursor have no prey\n");
                }
                //Then if cursor is not empty then we can proceed to ask about the
                //child that it want to remove
                else
                {
                    //Asking the user for the name of the cursor's child to remove
                    System.out.print("What is the name of the organism to be removed?: ");
                    try
                    {
                        userInput=reader.readLine();
                    }
                    catch(IOException i)
                    {
                        System.out.println("Error when entering a organism to remove");
                    }

                    //Trying to remove the child from the cursor
                    try
                    {
                        foodTree.removeChild(userInput);
                    }
                    //IllegalArgumentException means that the child is not found in
                    //the direct child of the cursor
                    catch(IllegalArgumentException i)
                    {
                        System.out.println(i);
                    }
                }
            }
            //This means that the user have inputed in P which means to print out
            //the prey of the cursor
            else if(userInput.equalsIgnoreCase("P"))
            {
                boolean cursorEmpty=foodTree.cursorIsEmpty();
                
                //This means that the cursor have no prey therefore we don't
                //need to remove anything
                if(cursorEmpty)
                {
                    System.out.println("The cursor have no prey\n");
                }
                //However, this means that the cursor have some prey therefore
                //we can proceed to remove
                else
                {
                    //Listing out the prey of the cursor
                    try
                    {
                        String toPrint=foodTree.listPrey();

                        System.out.println(toPrint+"\n");
                    }
                    catch(IsPlantException i)
                    {
                        System.out.println(i);
                    }
                }
            }
            //This means that the user want to print out the food chain 
            else if(userInput.equalsIgnoreCase("C"))
            {
                //Printing out the foodchain by calling the listFoodChain mehtod
               System.out.println(foodTree.listFoodChain());
                System.out.println("");
            }
            //This means that the user have inputted in F which means to print the
            //food pyramid at cursor. Calling the printOrganismTree method
            else if(userInput.equalsIgnoreCase("F"))
            {
                //Printing out the OrganismTree by calling the printOrganismTree
                //method
                foodTree.printOrganismTree();
            }
            //This means that the user have inputted LP which means to list out
            //all of the plants that is supporting the cursor
            else if(userInput.equalsIgnoreCase("LP"))
            {
                //Listing out all the plant by calling the listAllPlants method
                System.out.println(foodTree.listAllPlants());
                System.out.println("");
            }
            //This means that the user have uinputted in R which means to reset the
            //cursor back to the root and displaying the correct message
            else if(userInput.equalsIgnoreCase("R"))
            {
                //Resetting the cursor
                foodTree.cursorRest();
                
                System.out.println("Cursor successfully reset to root!\n");
            }
            //This means that the user have inputted M which means to move the cursor
            //to one of its child
            else if(userInput.equalsIgnoreCase("M"))
            {
                boolean cursorEmpty=foodTree.cursorIsEmpty();
                
                //This means that there are no prey for the cursor to move to there
                //fore there is no point in asking the user for a node
                if(cursorEmpty)
                {
                    System.out.println("The cursor have no prey to move to\n");
                }
                //If this is true then there are node for the cursor to move to
                //therefore we can ask the user which one
                else
                {
                    //Asking the user for the child to move the cursor to
                    System.out.print("Move to?: ");
                    try
                    {
                        userInput=reader.readLine();
                    }
                    catch(IOException i)
                    {
                        System.out.println("Error when inputting a name to move "
                                + "the cursor to");
                    }

                    //Moving the cursor to one of it's child, if it doesn't find it as
                    //a child then the corresponding message will be displayed to the user
                    try
                    {
                        foodTree.moveCursor(userInput);
                    }
                    catch(IllegalArgumentException i)
                    {
                        System.out.println(i+"\n");
                    }
                }
                
            }
            //This means that the user have inputted Q which means to shut down the program
            else if(userInput.equalsIgnoreCase("Q"))
            {
                isFinished=true;
                
                System.out.println("Program terminating successfully... "
                        + "Have a great day ^ w ^");
            }
        }
    }
}