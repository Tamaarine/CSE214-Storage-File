package HW7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FollowGraphDriver
{
    public static void main(String [] args)
    {
        InputStreamReader input=new InputStreamReader(System.in);
        
        BufferedReader reader=new BufferedReader(input);
        
        String userInput="";
        
        boolean isFinished=false;
        
        //This while loop will keep asking the user for a menu option and interaction
        //if it is not terminated
        while(!isFinished)
        {
            //Menu options
            System.out.println("(U) Add user\n"
                    + "(C) Add Connection\n"
                    + "(AU) Load all Users\n"
                    + "(AC) Load all Connections\n"
                    + "(P) Print all Users\n"
                    + "(L) Print all Loops\n"
                    + "(RU) Remove User\n"
                    + "(RC) Remove Connection\n"
                    + "(SP) Find Shortest Path\n"
                    + "(AP) Find All Paths\n"
                    + "(Q) Quit\n");

            System.out.print("Enter a selection: ");

            try
            {
                userInput=reader.readLine();
            }
            catch(IOException i)
            {
                System.out.println("Error when reading input from user");
            }
            
            if(userInput.equalsIgnoreCase("U"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("C"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("AU"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("AC"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("P"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("L"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("RU"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("RC"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("SP"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("AP"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("Q"))
            {
                isFinished=true;
                
                //We have to do the serailization here
            }
            
            
            
            
            
            
            
            
            
            
        
        }
        
        
        
        
    }
}