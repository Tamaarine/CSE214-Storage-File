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
            foodTree.getRoot().addPrey(new OrganismNode("Hello",false,false,false));
            foodTree.getRoot().getLeft().addPrey(new OrganismNode("pew",false,false,false));
            foodTree.getRoot().getLeft().addPrey(new OrganismNode("asd",false,false,false));
            foodTree.getRoot().getLeft().addPrey(new OrganismNode("dsa",false,false,false));
            
            foodTree.getRoot().addPrey(new OrganismNode("CSE", false, false, false));
            foodTree.getRoot().addPrey(new OrganismNode("NODAOSDN",false,false,false));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        foodTree.moveCursor("dsa");
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
                
            }
            else if(userInput.equalsIgnoreCase("AC"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("RC"))
            {
                
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
            else if(userInput.equalsIgnoreCase("C"))
            {
                
                
                
            }
            else if(userInput.equalsIgnoreCase("F"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("LP"))
            {
                
                
            }
            else if(userInput.equalsIgnoreCase("R"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("M"))
            {
                
                
            }
            else if(userInput.equalsIgnoreCase("Q"))
            {
                isFinished=true;
            }
            
            
            

        }
        
    }
}