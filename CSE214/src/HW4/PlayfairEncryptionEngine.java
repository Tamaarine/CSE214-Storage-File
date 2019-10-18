package HW4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The PlayfairEncryptionEngine is the main driver for the application for the 
 * Playfair encryption application program. The driver will first ask the user for input
 * in order to generate a KeyTable object for encryption and decryption, if the user
 * decided not to enter anything then a default KeyTable object will be created which
 * consists of letter A to Z not including J in alphabetical order. The driver
 * will also offer the following menu choices.
 * <p>
 * (CK) - Change key (Generates a new encryption key)
 * <p>
 * (PK) - Print key (displays the 5x5 table version of the key)
 * <p>
 * (EN) - Encrypt (Prompts user input and encrypts the input)
 * <p>
 * (DE) - Decrypt (Prompts user input and decrypts the input)
 * <p>
 * (Q) - Quit
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class PlayfairEncryptionEngine
{
    /**
     * The main method will first ask the user for a initial key phrase then generates
     * a key table according to it, if none is provided it will generate a default one.
     * Then it will presents a menu to the user allowing them to interact with the program
     * 
     * @param args 
     *  args contains the text from the command line that starts the program
     */
    public static void main(String [] args)
    {
        //InputStreamReader and BufferedReader work together to get the input from
        //the user
        InputStreamReader input=new InputStreamReader(System.in);
        BufferedReader reader=new BufferedReader(input);

        //isFinished boolean represent whether or not the program is completed or not
        boolean isFinished=false;
        
        //firstTime boolean represent whether or not is it the first time the user
        //interacting with the program, it will trigger whether or not to ask
        //the user initially for a key phrase
        boolean firstTime=true;
        
        String userInput="";
        
        //key will be holding the key phrase use for encryption and decryption
        KeyTable key=new KeyTable();
        
        //toEncrypt will be holding the Bigrams to be used for encryption
        Phrase toEncrypt=new Phrase();
        
        //encrypted will be holding the Bigram that is encrypted
        Phrase encrypted=new Phrase();
        
        //toDecrypt will be holding the Bigrams to be used for decryption
        Phrase toDecrypt=new Phrase();
        
        //decryped will be holding the Bigram that is decrypted
        Phrase decrypted=new Phrase();
        
        while(!isFinished)
        {
            //This means that it is the first time that user open up the program
            //therefore we manually ask for a key for the first run
            if(firstTime)
            {
                //Asking the user for a key phrase for the first time
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
            System.out.println("Menu:\n"
                            + "(CK) - Change key\n"
                            + "(PK) - Print key\n"
                            + "(EN) - Encrypt\n"
                            + "(DE) - Decrypt\n"
                            + "(Q) - Quit\n");
            
            //Asking the user for input
            System.out.print("Please select an option: ");
            try
            {
                userInput=reader.readLine();
            }
            catch(IOException i)
            {
                System.out.println("Error when entering in menu options\n");
            }
            
            //This means that the user have entered in CK which means to change
            //the key
            if(userInput.equalsIgnoreCase("CK"))
            {
                //Asking the user for a key phrase
                System.out.print("Enter key phrase: ");
                try
                {
                    userInput=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering in key phrase");
                }
                
                //This means that if the user did input something then make a key
                //according to it
                if(!userInput.isEmpty())
                {
                    key=KeyTable.buildFromString(userInput);
                            
                }
                //This means that the user entered in nothing thus we show the error
                //message
                else
                {
                    System.out.println("You have entered in an empty key phrase\n");
                }
            }
            //This means that the user have entered PK which is to just print the key
            else if(userInput.equalsIgnoreCase("PK"))
            {
                System.out.println(key);
            }
            //This means that the user have entere EN which is to encrypt a message
            else if(userInput.equalsIgnoreCase("EN"))
            {
                //Asking the user to input a message that they want to encrypt
                System.out.print("Please enter a phrase to encrypt: ");
                try
                {
                    userInput=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering in a phrase to encrypt\n");
                }
                
                //This means that the user have at least entered somethign to encrypt
                if(!userInput.isEmpty())
                {
                    //Turning the userInput into Bigrams
                    toEncrypt=Phrase.buildPhraseFromStringforEnc(userInput);
                    
                    //Encrypting the message
                    encrypted=toEncrypt.encrypt(key);
                    
                    //Printing out the encrypted message
                    System.out.print("Encryped text is: ");
                    while(!encrypted.isEmpty())
                    {
                        Bigram removed=encrypted.dequeue();
                        
                        System.out.print(removed);
                    }
                    
                    System.out.println("\n");
                }
                //This means that the user have entered in a empty line thus nothing
                //to encrypted we show a error message
                else
                {
                    System.out.println("You have entered an empty phrase to encrypt\n");
                }
            }
            //This means that the user have entered in DE which means to decrypt a message
            else if(userInput.equalsIgnoreCase("DE"))
            {
                //This is asking the user for a message to decrypt
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
                    
                    //We put inside a try and catch statement so that if anyting goes
                    //wrong it is not the program's fault at decrypting the message but
                    //the user's fault for entering an invalid encrypted message
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

                        //Prining out the message
                        System.out.print("Decrypted tet is : ");
                        while(!decrypted.isEmpty())
                        {
                            Bigram removed=decrypted.dequeue();

                            System.out.print(removed);
                        }

                        System.out.println("\n");
                    }
                    //Telling the user that they have entered in an invalid encrypted message
                    catch(Exception e)
                    {
                        System.out.println("Error when decrypting, sorry\n");
                        
                        while(!toDecrypt.isEmpty())
                        {
                            toDecrypt.dequeue();
                        }
                    }
                }
                //This means that the user have entered in nothign therefore we tell them
                //the error 
                else
                {
                    System.out.println("You have entered an empty phrase to decrypt\n");
                }
            }
            //This means that the user have entered in Q which is to shut down the program
            else if(userInput.equalsIgnoreCase("Q"))
            {
                isFinished=true;
                
                System.out.println("Program terminating... Have a wonderful day! ^ - ^");
            }
        }
    }
}