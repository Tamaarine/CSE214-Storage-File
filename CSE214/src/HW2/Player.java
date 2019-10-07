package HW2;

import java.io.*;
import javax.sound.sampled.LineUnavailableException;

public class Player
{
    //Static variables
    public static SongLinkedList songList;
    
    
    public static void main(String [] args)
    {
        songList=new SongLinkedList();
        
        songList.insertAfterCursor(new Song("Entry of the Gladiators", "Julius Fucik", "40 Famous Marches",10));
        songList.insertAfterCursor(new Song("1812 Overture", "Pyotr Trachsvisky", "Robert Albert Hall", 9));
        songList.insertAfterCursor(new Song("Auld Lang Syne", "Robert Burns", "Hogmanay", 11));
        songList.insertAfterCursor(new Song("Danse Macabre", "Robert Burns", "Hogmanay", 11));
        songList.insertAfterCursor(new Song("Marriage of Figaro", "Robert Burns", "Hogmanay", 11));
        songList.insertAfterCursor(new Song("FanfareForTheCommonMan", "Robert Burns", "Hogmanay", 11));
        songList.insertAfterCursor(new Song("Sprach Zarathustra", "Robert Burns", "Hogmanay", 11));
        songList.insertAfterCursor(new Song("Ode to Joy", "Robert Burns", "Hogmanay", 11));
        
        InputStreamReader input=new InputStreamReader(System.in);
        BufferedReader reader=new BufferedReader(input);
        
        String userInput="";
        
        boolean finish=false;
        
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
            System.out.print("Enter an option: ");
            
            try
            {
                userInput=reader.readLine();
            }
            catch(IOException i)
            {
                System.out.println("Error when entering option in menu");
            }
            
            if(userInput.equalsIgnoreCase("A"))
            {
                String songTitle="";
                String songArtist="";
                String albumName="";
                int songLength=0;
                
                Song toBeAddedSong=new Song();
                
                System.out.print("Enter song title: ");
                try
                {
                    songTitle=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("Error when entering song title");
                }
                
                if(!songTitle.isEmpty())
                {
                    toBeAddedSong.setName(songTitle);
                    
                    System.out.print("Enter artist(s) of the song: ");
                    try
                    {
                        songArtist=reader.readLine();
                    }
                    catch(IOException i)
                    {
                        System.out.println("Error when entering song artist");
                    }
                    
                    if(!songArtist.isEmpty())
                    {
                        toBeAddedSong.setArtist(songArtist);
                        
                        System.out.print("Enter album: ");
                        try
                        {
                            albumName=reader.readLine();
                        }
                        catch(IOException i)
                        {
                            System.out.println("Error when entering album name");
                        }
                        
                        if(!albumName.isEmpty())
                        {
                            toBeAddedSong.setAlbum(albumName);
                            String stringLength="";
                            
                            boolean enteredLetter=false;
                            
                            System.out.print("Enter length (in seconds): ");
                            try
                            {
                                stringLength=reader.readLine();
                                
                                if(stringLength.isEmpty())
                                {
                                    System.out.println("You have entered an empty length\n");
                                }
                                else
                                {
                                    songLength=Integer.parseInt(stringLength);
                                    
                                    try
                                    {
                                        toBeAddedSong.setLength(songLength);
                                        
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
                        else
                        {
                            System.out.println("You have entered an empty album name\n");
                        }
                    }
                    else
                    {
                        System.out.println("You have entered an empty song artist\n");
                    }
                }
                else
                {
                    System.out.println("You have entered an empty song title\n");
                }
            }
            else if(userInput.equalsIgnoreCase("F"))
            {
                songList.cursorForwards();
            }
            else if(userInput.equalsIgnoreCase("B"))
            {
                songList.cursorBackwards();
            }
            else if(userInput.equalsIgnoreCase("R"))
            {
                if(songList.getSize()>0)
                {
                    Song songRemoved=songList.removeCursor();

                    String songName=songRemoved.getName();
                    String songArtist=songRemoved.getArtist();

                    System.out.println("'"+songName+"'"+" by "+songArtist+" was"
                            + " removed from the playlist.\n");
                }
                else
                {
                    System.out.println("There is no more songs to be removed.\n");
                }
                
            }
            else if(userInput.equalsIgnoreCase("L"))
            {
                String songToPlay="";
                
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
            else if(userInput.equalsIgnoreCase("C"))
            {
                songList.deleteAll();
                
                System.out.println("Playlist cleared.\n");
            }
            else if(userInput.equalsIgnoreCase("S"))
            {
                if(songList.getSize()>0)
                {
                    songList.shuffle();
                    
                    System.out.println("Playlist shuffled.\n");
                }
                else
                {
                    System.out.println("There is currently no song within the list"
                            + " please add songs before you shuffle songs.\n");
                }
            }
            else if(userInput.equalsIgnoreCase("Z"))
            {
                //This methods stop the clip from playing if there is a clip
                //from before already playing
                if(songList.isClipRunning())
                {
                    songList.stopClip();
                }
                
                if(songList.getSize()>0)
                {
                    Song randomSong=songList.random();
                    String songName=randomSong.getName();
                    String artistName=randomSong.getArtist();
                    
                    
                    songList.play(randomSong.getName());
                }
                else
                {
                    System.out.println("There is currently no song within the list"
                            + " please add songs before you play random songs.\n");
                }

            }
            else if(userInput.equalsIgnoreCase("P"))
            {
                songList.printPlayerList();
            }
            else if(userInput.equalsIgnoreCase("T"))
            {
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
            else if(userInput.equalsIgnoreCase("Q"))
            {
                finish=true;
                System.out.println("Shutting Down Program . . . Have a nice day!");
            }
            
        }
        
        
        
        
        
    }

    
}