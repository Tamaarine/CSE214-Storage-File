package HW2Edited;

import java.io.*;

/**
 * The Player class acts as the driver of the application. It will first create an
 * empty SongLinkedList object and then the program will then ask the users for
 * menu inputs in order to execute certain tasks as specified by the menu options.
 * The user will be ask of more information if it is necessary. The list of menu options
 * are
 * <p>
 * (A) Add Song to Playlist
 * <p>
 * (F) Go to Next Song
 * <p>
 * (B) Go to Previous Song
 * <p>
 * (R) Remove Song from Playlist
 * <p>
 * (L) Play a Song
 * <p>
 * (C) Clear the Playlist
 * <p>
 * (S) Shuffle Playlist
 * <p>
 * (Z) Random Song
 * <p>
 * (P) Print Playlist
 * <p>
 * (T) Get the total amount of Songs in the Playlist
 * <p>
 * (Q) Exit the Playlist
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */

public class Player
{
    /**
     * The main method of the Player driver. This is where the execution begins
     * @param args 
     *  args contains the text from the command line that starts the program
     */
    public static void main(String [] args)
    {
        //Creating a empty SongLinkedList that the user will be inputting the
        //SongNode into
        SongLinkedList songList=new SongLinkedList();
        
        songList.insertAfterCursor(new Song("Entry of the Gladiators", "Julius Fucik", "40 Famous Marches",10));
        songList.insertAfterCursor(new Song("1812 Overture", "Pyotr Trachsvisky", "Robert Albert Hall", 9));
        songList.insertAfterCursor(new Song("Auld Lang Syne", "Robert Burns", "Hogmanay", 11));
        songList.insertAfterCursor(new Song("Danse Macabre", "Robert Burns", "Hogmanay", 11));
        songList.insertAfterCursor(new Song("Marriage of Figaro", "Robert Burns", "Hogmanay", 11));
        songList.insertAfterCursor(new Song("FanfareForTheCommonMan", "Robert Burns", "Hogmanay", 11));
        songList.insertAfterCursor(new Song("Sprach Zarathustra", "Robert Burns", "Hogmanay", 11));
        songList.insertAfterCursor(new Song("Ode to Joy", "Robert Burns", "Hogmanay", 11));
        
        //The InputStreamReader and BufferedReader is used together to get the
        //input from the user
        InputStreamReader input=new InputStreamReader(System.in);
        BufferedReader reader=new BufferedReader(input);
        
        String userInput="";
        
        boolean finish=false;
        
        //Menu options
        System.out.println("(A) Add Song to Playlist"
                + "\n(F) Go to Next Song"
                + "\n(B) Go to Previous Song"
                + "\n(R) Remove Song from Playlist"
                + "\n(L) Play a Song"
                + "\n(C) Clear the Playlist"
                + "\n(S) Shuffle Playlist"
                + "\n(Z) Random Song"
                + "\n(P) Print Playlist"
                + "\n(T) Get the total amount of Songs in the Playlist"
                + "\n(Q) Exit the Playlist");
        
        while(!finish)
        {
            //This is asking the user for an input option and reading it from
            //the output
            System.out.print("Enter an option: ");
            try
            {
                userInput=reader.readLine();
            }
            catch(IOException i)
            {
                System.out.println("Error when entering option in menu");
            }
            
            //This means that the user have entered in A which means to add a
            //new Song into the playlist
            if(userInput.equalsIgnoreCase("A"))
            {
                //These variables are holder variables for the attributes of the
                //Song that we are going to add into the playlist
                String songTitle="";
                String songArtist="";
                String albumName="";
                int songLength=0;
                
                //Making a default Song object to be changed by mutator as we 
                //gather inforamtion from the user
                Song toBeAddedSong=new Song();
                
                //This is asking the user for the Song's title
                System.out.print("Enter song title: ");
                try
                {
                    songTitle=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering song title");
                }
                
                //This means that the Song name that the user entered is not empty
                //therefore we can continue
                if(!songTitle.isEmpty())
                {
                    //Setting the Song's name to the Song name user entered
                    toBeAddedSong.setName(songTitle);
                    
                    //This is asking the user for the Song's artist
                    System.out.print("Enter artist(s) of the song: ");
                    try
                    {
                        songArtist=reader.readLine();
                    }
                    catch(IOException i)
                    {
                        System.out.println("Error when entering song artist");
                    }
                    
                    //This means that the Song's artist name that the user entered
                    //is not empty therefore we can continue
                    if(!songArtist.isEmpty())
                    {
                        //Setting the Song's artist name to the Song's artist name
                        //user have entered
                        toBeAddedSong.setArtist(songArtist);
                        
                        //This is asking the user for the Song's album that was
                        //released on
                        System.out.print("Enter album: ");
                        try
                        {
                            albumName=reader.readLine();
                        }
                        catch(IOException i)
                        {
                            System.out.println("Error when entering album name");
                        }
                        
                        //This means that the Song's album name that the user enetered
                        //is not empty therefore we can continue
                        if(!albumName.isEmpty())
                        {
                            //Setting the Song's album name to the Song's album
                            //name that the user have entered
                            toBeAddedSong.setAlbum(albumName);
                            
                            //This String will be holding the String representation
                            //have what the user have entered, this is so that
                            //we can prevent exception when user entered in letters
                            //when it is asked for the duration of the Song
                            String stringLength="";
                            
                            //This is asking the user for the Song's duration
                            System.out.print("Enter length (in seconds): ");
                            try
                            {
                                stringLength=reader.readLine();
                                
                                //This if-statement first check whether the input
                                //that the user entered is empty, if it is inform the
                                //user it is empty and and prompt to the menu option
                                if(stringLength.isEmpty())
                                {
                                    System.out.println("You have entered an empty length\n");
                                }
                                //This means that the user's input was not empty
                                //But we still have to check whether it is a valid
                                //integer for just String of characters
                                else
                                {
                                    //The exception will be catch in the bigger
                                    //try and catch block if the user entered in
                                    //letters for the time
                                    songLength=Integer.parseInt(stringLength);
                                    
                                    try
                                    {
                                        //Setting the Song's length to the 
                                        //length that the user inputted
                                        toBeAddedSong.setLength(songLength);
                                        
                                        //Insert the newly created SongNode into
                                        //the list
                                        songList.insertAfterCursor(toBeAddedSong);

                                        System.out.println("'"+songTitle+"'"+" by "
                                                +songArtist+" is added to your playlist.\n");
                                    }
                                    catch(InvalidLengthException n)
                                    {
                                        System.out.println(n+"\n");
                                    }
                                }
                            }
                            catch(IOException i)
                            {
                                System.out.println("Error when entering song length");
                            }
                            catch(Exception e)
                            {
                                System.out.println("You have entered letter instead of"
                                        + " numbers\n");
                            }
                        }
                        //This means that the user have inputted in a empty album name
                        //return to the menu
                        else
                        {
                            System.out.println("You have entered an empty album name\n");
                        }
                    }
                    //This means that the user have inputted in a empty song artist
                    //name return to the menu
                    else
                    {
                        System.out.println("You have entered an empty song artist\n");
                    }
                }
                //This means that the user have inputted in a empty song name
                //reeturn to the menu
                else
                {
                    System.out.println("You have entered an empty song title\n");
                }
            }
            //This means that the user have entered F which means to move the 
            //cursor to the next Song
            else if(userInput.equalsIgnoreCase("F"))
            {
                //Moving the cursor forward by calling cursorForward method
                songList.cursorForwards();
            }
            //This means that the user have entered B which means to move
            //the cursor to the previous Song
            else if(userInput.equalsIgnoreCase("B"))
            {
                //Moving the cursor backward by calling cursorBackward method
                songList.cursorBackwards();
            }
            //This means that the user have enetered R which means to remove
            //the Song which the cursor is pointing at currently
            else if(userInput.equalsIgnoreCase("R"))
            {
                //This makes sure that there is something to remove before calling
                //the removeCursor method
                if(songList.getSize()>0)
                {
                    //Calling the removeCursor method on the songList and getting
                    //the Song that was removed back so we can use it to print messages
                    Song songRemoved=songList.removeCursor();

                    String songName=songRemoved.getName();
                    String songArtist=songRemoved.getArtist();

                    System.out.println("'"+songName+"'"+" by "+songArtist+" was"
                            + " removed from the playlist.\n");
                }
                //This means that there is nothing in the list to remove
                else
                {
                    System.out.println("There is no more songs to be removed.\n");
                }
                
            }
            //This means that the user have entered L which means to play the Song
            //that the user entered
            else if(userInput.equalsIgnoreCase("L"))
            {
                String songToPlay="";
                
                //Asking the user the Song's name to play
                System.out.print("Enter name of song to play: ");
                try
                {
                    songToPlay=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when when entering the name of the"
                            + " song to play");
                }
                
                //isClipRunning is called to check if there is any previous Song
                //playing, if there is then it is stopped
                if(songList.isClipRunning())
                {
                    songList.stopClip();
                }
                
                try
                {
                    songList.play(songToPlay);
                }
                catch(IllegalArgumentException i)
                {
                    System.out.println(i+"\n");
                }
            }
            //This means that the user have entered in C which means to
            //clear the playlist of all of the Songs
            else if(userInput.equalsIgnoreCase("C"))
            {
                //This makes sure that there is at least 1 song in the playlist
                //before clearing, or else there is no point in clearing the playlist
                if(songList.getSize()>0)
                {
                    songList.deleteAll();

                    System.out.println("Playlist cleared.\n");
                }
                //This means that there is no Song in the playlist to clear
                else
                {
                    System.out.println("There is no song in the playlist to clear.\n");
                }
            }
            //This means that the user have entered S which means to shuffle the
            //playlist
            else if(userInput.equalsIgnoreCase("S"))
            {
                //This ensure that there is at least one Song in the playlist before
                //shuffling, or else there is no point
                if(songList.getSize()>0)
                {
                    songList.shuffle();
                    
                    System.out.println("Playlist shuffled.\n");
                }
                //This means that there is no Song in the playlist to shuffle
                else
                {
                    System.out.println("There is currently no song within the list"
                            + " please add songs before you shuffle songs.\n");
                }
            }
            //This means that the user have entered Z which means to play a random
            //song from the playlist
            else if(userInput.equalsIgnoreCase("Z"))
            {
                //This methods stop the clip from playing if there is a clip
                //from before already playing
                if(songList.isClipRunning())
                {
                    songList.stopClip();
                }
                
                //This make sure that there is at least one Song to randomly pick
                //from the list before randomly picking one and playing it
                if(songList.getSize()>0)
                {
                    Song randomSong=songList.random();
                    
                    songList.play(randomSong.getName());
                }
                //This means that there is no Song in the playlist to randomly pick one
                else
                {
                    System.out.println("There is currently no song within the list"
                            + " please add songs before you play random songs.\n");
                }

            }
            //This meanas that the user have entered P which means to print a
            //neatly formatted table of the playlist data
            else if(userInput.equalsIgnoreCase("P"))
            {
                songList.printPlayerList();
            }
            //This means tht the user have entered T which means to get the total
            //number of the Songs in the playlist
            else if(userInput.equalsIgnoreCase("T"))
            {
                //Getting the total amount of Song in the playlist
                int songNumber=songList.getSize();
                
                if(songNumber==0)
                {
                    System.out.println("Your playlist is empty.\n");
                }
                else
                {
                    System.out.println("Your playlist contains "+songNumber+" songs.\n");
                }
                
            }
            //This means that the user have entered Q which means to shut down
            //the program, stop the loop
            else if(userInput.equalsIgnoreCase("Q"))
            {
                finish=true;
                System.out.println("Shutting Down Program . . . Have a nice day!");
            }
            
        }
    }
}