package HW4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
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
        
        Phrase toDecrypt=new Phrase();
        
        Phrase decrypted=new Phrase();
        
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
                System.out.println("Error when entering in menu options\n");
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
                    System.out.println("You have entered in an empty key phrase\n");
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
                    System.out.println("Error when entering in a phrase to encrypt\n");
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
                    System.out.println("You have entered an empty phrase to encrypt\n");
                }
            }
            else if(userInput.equalsIgnoreCase("DE"))
            {
                System.out.print("Please enter a phrase to decrypt: ");
                
                try
                {
                    userInput=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering a phrase to decrypt\n");
                }
                
                //This means that the user have entered in a phrase to decrypt
                if(!userInput.isEmpty())
                {
                    //Eventhough we are promised that it is a valid encryped message
                    //we should convert it into all upper case just in case
                    userInput=userInput.toUpperCase();
                    
                    //And we should also removed any spaces
                    userInput=userInput.replaceAll("\\s","");
                    
                    try
                    {
                        //We are gurante that the userInput is a valid encrypted message
                        //therefore we just have to parse two letters at a time and set it
                        //as a Bigram up until the end of the userInput
                        for(int i=0;i<userInput.length();i=i+2)
                        {
                            Bigram toBeAdded=new Bigram();

                            toBeAdded.setFirst(userInput.charAt(i));
                            toBeAdded.setSecond(userInput.charAt(i+1));

                            //Then we add it into toDecrypt because they are the ones we have
                            //to decrypt
                            toDecrypt.enqueue(toBeAdded);
                        }

                        //Decrypting the message
                        decrypted=toDecrypt.decrypt(key);

                        while(!decrypted.isEmpty())
                        {
                            Bigram removed=decrypted.dequeue();

                            System.out.print(removed);
                        }

                        System.out.println("\n");
                    }
                    catch(Exception e)
                    {
                        System.out.println("Error when decrypting, sorry\n");
                    }
                }
                else
                {
                    System.out.println("You have entered an empty phrase to decrypt\n");
                }
            }
            else if(userInput.equalsIgnoreCase("Q"))
            {
                isFinished=true;
                
                System.out.println("Program terminating... Have a wonderful day! ^ - ^");
            }
        }
    }
}