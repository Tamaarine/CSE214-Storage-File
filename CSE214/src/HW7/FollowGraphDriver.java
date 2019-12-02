package HW7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;

public class FollowGraphDriver
{
    public static void main(String [] args)
    {
        //InputStreamReader and BufferedReader will work together to read the
        //user key input
        InputStreamReader input=new InputStreamReader(System.in);
        
        BufferedReader reader=new BufferedReader(input);
        
        //This FollowGraph will be the main graph that the user will be interacting
        //with
        FollowGraph graph=new FollowGraph();
        
        try
        {
            FileInputStream file=new FileInputStream("FollowGraph.obj");
            ObjectInputStream inputStream=new ObjectInputStream(file);
            
            graph=(FollowGraph)inputStream.readObject();
        }
        catch(FileNotFoundException f)
        {
            System.out.println(f);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
        catch(ClassNotFoundException c)
        {
            System.out.println(c);
        }
        
        //This String will be keeping track of the userInput
        String userInput="";
        
        //This boolean variable will be keeping track whether or not the user
        //is finishing with the program
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
            
            //This means that the user have entered in U which means to add in a
            //new user to the list of users
            if(userInput.equalsIgnoreCase("U"))
            {
                //Asking the user for the user name to create
                System.out.print("Please enter the name of the user: ");
                try
                {
                    userInput=reader.readLine();
                    
                    //Adding in the new user
                    graph.addUser(userInput);
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering in the user name");
                }
                
            }
            //This means that the user have entered in C which means to add in a
            //new connection between two users
            else if(userInput.equalsIgnoreCase("C"))
            {
                String userFrom="";
                String userTo="";
                
                System.out.print("Please enter the source of the connection to add: ");
                try
                {
                    userFrom=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when the user is entering the source of connection");
                }
                
                System.out.print("Please enter the dest of the connection to add: ");
                try
                {
                    userTo=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when the user is entering the destination of connection");
                }
                
                graph.addConnection(userFrom, userTo);
            }
            //This means that the user have entered in AU which means to load in a
            //file that contains different users
            else if(userInput.equalsIgnoreCase("AU"))
            {
                //Asking the user for the file name
                System.out.print("Enter the file name: ");
                try
                {
                    userInput=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering in the file name to load");
                }
                
                graph.loadAllUsers(userInput);
            }
            else if(userInput.equalsIgnoreCase("AC"))
            {
                System.out.print("Enter the file name: ");
                try
                {
                    userInput=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering the connection text file");
                }
                
                graph.loadAllConnections(userInput);
            }
            //This means that the user have entered in P which means to print all
            //users based on the next menu options that user picked which sorts
            //the user list in a specific order
            else if(userInput.equalsIgnoreCase("P"))
            {
                System.out.println("(SA) Sort Users by Name\n"
                        + "(SB) Sort Users by Number of Followers\n"
                        + "(SC) Sort Users by Number of Following\n"
                        + "(Q) Quit - Going back to the main menu\n");
                
                System.out.print("Enter a selection: ");
                try
                {
                    userInput=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering in the secondary option"
                            + " for the print menu");
                }
                
                //This means that the user want to sort the list of users by 
                //their names
                if(userInput.equalsIgnoreCase("SA"))
                {
                    graph.printAllUsers(new NameComparator());
                }
                //This means that the user want to sort the list of users by their
                //amount of followers they have
                else if(userInput.equalsIgnoreCase("SB"))
                {
                    graph.printAllUsers(new FollowersComparator());
                }
                //This means that the user want to sort the list of users by their
                //amount of following
                else if(userInput.equalsIgnoreCase("SC"))
                {
                    graph.printAllUsers(new FollowingComparator());
                }
                
                System.out.println();
            }
            else if(userInput.equalsIgnoreCase("L"))
            {
                
            }
            else if(userInput.equalsIgnoreCase("RU"))
            {
                System.out.print("Please enter the user to remove: ");
                try
                {
                    userInput=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering the user to remove");
                }
                
                graph.removeUser(userInput);
            }
            else if(userInput.equalsIgnoreCase("RC"))
            {
                String userFrom="";
                String userTo="";
                
                System.out.print("Please enter the source of the connection to remove: ");
                try
                {
                    userFrom=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering the source of connection to remvoe");
                }
                
                
                System.out.print("Please enter the dest of the connection to remove: ");
                try
                {
                    userTo=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering the destion of the connetion to remove");
                }
                
                graph.removeConnection(userFrom, userTo);
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
                
                System.out.println("FollowGraph object saved into file FollowGraph.obj");
                System.out.println("Program terminating.... Have a great day ^ w ^!");
                
                //We have to do the serailization here
                try
                {
                    FileOutputStream file=new FileOutputStream("FollowGraph.obj");
                    ObjectOutputStream outStream=new ObjectOutputStream(file);
                    
                    outStream.writeObject(graph);
                }
                catch(FileNotFoundException f)
                {
                    System.out.println(f);
                }
                catch(IOException i)
                {
                    System.out.println("Error when constructing outStream");
                }
            }
            
            
            
            
            
            
            
            
            
            
        
        }
        
        
        
        
    }
}