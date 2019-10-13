package HW4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayfairEncryptionEngine
{
    public static void main(String [] args)
    {
        InputStreamReader input=new InputStreamReader(System.in);
        BufferedReader reader=new BufferedReader(input);

        boolean isFinished=false;
        
        
        
        String userInput="";
        
        while(!isFinished)
        {
            //Printing the menu options
            System.out.println("(CK) - Change key\n"
                            + "(PK) - Print key\n"
                            + "(EN) - Encrypt\n"
                            + "(DE) - Decrypt\n"
                            + "(Q) - Quit\n");
            
            System.out.print("Please select an option: ");
            try
            {
                userInput=reader.readLine();
            }
            catch(IOException i)
            {
                System.out.println("Error when entering in menu options");
            }
            
            if(userInput.equalsIgnoreCase("CK"))
            {
                
                
            }
            else if(userInput.equalsIgnoreCase("PK"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("EN"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("DE"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("Q"))
            {
                isFinished=true;
            }
            
            
        }
        
        
        
        
        
        
        
        
        
    }
}