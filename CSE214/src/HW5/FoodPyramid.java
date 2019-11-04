package HW5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FoodPyramid
{
    //Instance variable
    private OrganismTree tree;
    
    //Default constructor
    public FoodPyramid(OrganismNode apexPredator)
    {
        tree=new OrganismTree(apexPredator);
    }
    
    public static void main(String [] args)
    {
        InputStreamReader input=new InputStreamReader(System.in);
        BufferedReader reader=new BufferedReader(input);
        
        String userInput="";
        
        boolean isFinished=false;
        
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
        
        try
        {
            foodTree.moveCursor("dsa");
        }
        catch(IllegalArgumentException i)
        {
            System.out.println(i);
        }
        
        
        System.out.println("Cursor is now on "+foodTree.getCursor().getName());
        
        while(!isFinished)
        {
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
                
                try
                {
                    foodTree.addPlantChild(userInput);
                }
                catch(PositionNotAvailableException p)
                {
                    System.out.println(p);
                }
                catch(IllegalArgumentException i)
                {
                    System.out.println(i);
                }
                
            }
            //This means that the user entered in AC which means to create a new
            //animal child and add it to the cursor
            else if(userInput.equalsIgnoreCase("AC"))
            {
                String organismName="";
                boolean organismIsHerbivore=false;
                boolean organismIsCarnivore=false;
                
                boolean validInput=false;
               
                System.out.print("What is the name of the organism?: ");
                try
                {
                    organismName=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering the name of a organism to be added");
                }
                
                //This for loop will be keep asking the user for a valid option for the
                //animal that is going to be added to the cursor. It will keep on
                //repeating until the user have inputted a correct valid choice
                while(!validInput)
                {
                    //Prompting the user for a input
                    System.out.print("Is the organism an herbivore / a carnivore / an omnivore?"
                            + " (H / C / O): ");
                    try
                    {
                        userInput=reader.readLine();
                    }
                    catch(IOException i)
                    {
                        System.out.println("Error when entering the ");
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
                }
                
                //After the while loop then we have all of the information that we
                //need to add the new organism node to the cursor
                try
                {
                    foodTree.addAnimalChild(organismName, organismIsHerbivore, organismIsCarnivore);
                }
                catch(PositionNotAvailableException p)
                {
                    System.out.println(p);
                }
                catch(IllegalArgumentException i)
                {
                    System.out.println(i);
                }
                
                
                
                
            }
            //This means that the user have entered in RC which means to remove
            //a child node of the cursor based on the inputted name
            else if(userInput.equalsIgnoreCase("RC"))
            {
                System.out.print("What is the name of the organism to be removed?: ");
                try
                {
                    userInput=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering a organism to remove");
                }
                
                try
                {
                    foodTree.removeChild(userInput);
                }
                catch(IllegalArgumentException i)
                {
                    System.out.println(i);
                }
                
            }
            else if(userInput.equalsIgnoreCase("P"))
            {
                try
                {
                    String toPrint=foodTree.listPrey();

                    System.out.println(toPrint);
                }
                catch(IsPlantException i)
                {
                    System.out.println(i);
                }
                
            }
            //This means that the user want to print out the food chain 
            else if(userInput.equalsIgnoreCase("C"))
            {
               System.out.println(foodTree.listFoodChain());
            }
            //This means that the user have inputted in F which means to print the
            //food pyramid at cursor. Calling the printOrganismTree method
            else if(userInput.equalsIgnoreCase("F"))
            {
                foodTree.printOrganismTree();
            }
            else if(userInput.equalsIgnoreCase("LP"))
            {
                System.out.println(foodTree.listAllPlants());
                
            }
            else if(userInput.equalsIgnoreCase("R"))
            {
                foodTree.cursorRest();
                
                System.out.println("Cursor successfully reset to root!");
            }
            else if(userInput.equalsIgnoreCase("M"))
            {
                System.out.print("Move to?: ");
                try
                {
                    userInput=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when inputting a name to move the cursor to");
                }
                
                try
                {
                    foodTree.moveCursor(userInput);
                }
                catch(IllegalArgumentException i)
                {
                    System.out.println(i);
                }
                
                System.out.println(foodTree.getCursor().getName());
                
                
                
            }
            else if(userInput.equalsIgnoreCase("Q"))
            {
                isFinished=true;
            }
            
            
            

        }
        
    }
}