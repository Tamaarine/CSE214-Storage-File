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
        
        boolean firstTime=true;
        
        String userInput="";
        
        KeyTable key=new KeyTable();
        
        Phrase toEncrypt=new Phrase();
        
        Phrase encrypted=new Phrase();
        
        while(!isFinished)
        {
            //This means that it is the first time that user open up the program
            //therefore we manually ask for a key for the first run
            if(firstTime)
            {
                System.out.print("Enter key phrase: ");
                
                try
                {
                    userInput=reader.readLine();
                    firstTime=false;
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering key phrase");
                }
                
                //This means that the user have entered in nothing therefore we
                //use the default key from A to Z excluding J
                if(userInput.isEmpty())
                {
                    key=KeyTable.buildFromString("");
                }
                //This means that the user did enter in something for the key
                else
                {
                    key=KeyTable.buildFromString(userInput);
                }
            }
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
                System.out.print("Enter key phrase: ");

                try
                {
                    userInput=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering in key phrase");
                }
                
                if(!userInput.isEmpty())
                {
                    key=KeyTable.buildFromString(userInput);
                            
                }
                else
                {
                    System.out.println("You have entered in an empty key phrase");
                }
            }
            else if(userInput.equalsIgnoreCase("PK"))
            {
                System.out.println(key);
            }
            else if(userInput.equalsIgnoreCase("EN"))
            {
                System.out.print("Please enter a phrase to encrypt: ");
                
                try
                {
                    userInput=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering in a phrase to encrypt");
                }
                
                if(!userInput.isEmpty())
                {
                    toEncrypt=Phrase.buildPhraseFromStringforEnc(userInput);
                    
                    encrypted=toEncrypt.encrypt(key);
                    
                    System.out.print("Encryped text is: ");
                    
                    while(!encrypted.isEmpty())
                    {
                        Bigram removed=encrypted.dequeue();
                        
                        System.out.print(removed);
                    }
                    
                    System.out.println("\n");
                }
                else
                {
                    System.out.println("You have entered an empty phrase to encrypt");
                }
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