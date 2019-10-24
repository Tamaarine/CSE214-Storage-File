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
        
        OrganismNode apexPredator=new OrganismNode("Eagle", false, false, true);
        
        OrganismTree foodTree=new OrganismTree(apexPredator);

        
        try
        {
            foodTree.getRoot().addPrey(new OrganismNode("Hello",false,false,false));
            foodTree.getRoot().addPrey(new OrganismNode("CSE", false, false, false));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        foodTree.moveCursor("CSE");
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

            if(userInput.equalsIgnoreCase("PC"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("AC"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("RC"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("P"))
            {
                
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