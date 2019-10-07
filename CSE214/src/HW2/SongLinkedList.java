package HW2;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SongLinkedList
{
    //Instance variables
    private SongNode head;
    private SongNode tail;
    private SongNode cursor;
    private int size;
    
    //Audio stream variables
    private AudioInputStream audioInput;
    private Clip c;
    
    
    public SongLinkedList()
    {
        head=null;
        tail=null;
        cursor=null;
        size=0;
        
    }
    
    //Accessor methods
    public SongNode getHead()
    {
        return head;
    }
    
    public SongNode getTail()
    {
        return tail;
    }
    
    
    
    //Mutator methods
    
    
    //Interesting methods
    public void play(String name) throws IllegalArgumentException
    {
        //We need to perform a search before the 
        Song targetSong=linearSearch(name);
        
        if(targetSong!=null)
        {
            try
            {
                audioInput=AudioSystem.getAudioInputStream(
                    this.getClass().getResource(name+".wav"));

                c=AudioSystem.getClip();
                c.open(audioInput);

                c.start();
                
                String songName=targetSong.getName();
                String artistName=targetSong.getArtist();
                
                System.out.println("'"+songName+"' by "+artistName+" is now playing.\n");
            }
            catch(UnsupportedAudioFileException u)
            {
                System.out.println(u);
            }
            catch(LineUnavailableException l)
            {
                System.out.println(l);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
            catch(Exception e)
            {
                System.out.println("The song you entered is either not in the"
                        + " source folder or the song you entered is not on the"
                        + " playlist, please try again\n");
            }
        }
        else
        {
            throw new IllegalArgumentException("'"+name+"' not found");
        }
        
    }
    
    public Song linearSearch(String songTargetName)
    {
        Song output=null;
        
        SongNode nodePtr=head;
        
        while(nodePtr!=null)
        {
            if(nodePtr.getSong().getName().equals(songTargetName))
            {
                output=nodePtr.getSong();
                
                return output;
            }
            
            nodePtr=nodePtr.getNext();
        }
        
        return output;
    }
    
    public boolean isClipRunning()
    {
        if(c!=null)
        {
            return c.isRunning();
        }
        
        return false;
        
    }
    
    public void stopClip()
    {
        try
        {
            c.stop();
        }
        catch(Exception e)
        {
            System.out.println("There is either no clip playing or just no clip");
        }
    }
    
    public void cursorForwards()
    {
        if(cursor!=null)
        {
            if(cursor!=tail)
            {
                cursor=cursor.getNext();
                System.out.println("Cursor moved to next song.\n");

            }
            else
            {
                System.out.println("Already at end of playlist.\n");
            }
        }
        else
        {
            System.out.println("There is no song to move forward to.\n");
        }
    }
    
    public void cursorBackwards()
    {
        if(cursor!=null)
        {
            if(cursor!=head)
            {
                cursor=cursor.getPrev();
                System.out.println("Cursor moved to previous song.\n");
            }
            else
            {
                System.out.println("Already at beginning of playlist.\n");
            }
        }
        else
        {
            System.out.println("There is no song to move backward to.\n");
        }
    }
    
    public void insertAfterCursor(Song newSong) throws IllegalArgumentException
    {
        SongNode newNode=new SongNode(newSong);
        
        if(newSong==null)
        {
            throw new IllegalArgumentException("The song you entered is invalid");
        }
        else
        {
            if(cursor==null)
            {
                head=new SongNode(newSong);
                tail=head;
                cursor=head;

                size++;
            }
            else
            {
                if(cursor.getNext()==null)
                {
                    newNode.setNext(null);
                    newNode.setPrev(cursor);
                    cursor.setNext(newNode);
                    
                    size++;
                    
                    cursor=newNode;
                    tail=cursor;
                }
                else
                {
                    newNode.setNext(cursor.getNext());
                    newNode.setPrev(cursor);
                    cursor.setNext(newNode);
                    newNode.getNext().setPrev(newNode);

                    size++;
                    
                    cursor=newNode;
                }
            }
        }
    }
    
    public Song removeCursor()
    {
        SongNode nodeRemoved=new SongNode();
        
        if(cursor!=head&&cursor!=tail)
        {
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            
            size--;
            
            nodeRemoved=cursor;
            
            if(cursor.getNext()==null)
            {
                cursor=cursor.getPrev();
            }
            else
            {
                cursor=cursor.getNext();
            }
        }
        
        else if(cursor==head&&size==1)
        {
            head=null;
            tail=null;
            
            nodeRemoved=cursor;
            
            cursor=null;
            
            size--;
        }
        //Don't need this case, remove after explaining
        else if(cursor==tail&&size==1)
        {
            head=null;
            tail=null;
            
            nodeRemoved=cursor;
            
            cursor=null;
            
            size--;
        }
        else if(cursor==head)
        {
            nodeRemoved=head;
            
            head=head.getNext();
            head.setPrev(null);
            cursor=head;
            
            
            size--;
        }
        else if(cursor==tail)
        {
            nodeRemoved=tail;
            
            tail=tail.getPrev();
            tail.setNext(null);
            cursor=tail;
            
            size--;
        }
        
        Song removedSong=nodeRemoved.getSong();
        
        return removedSong;
        
        
        
    }
    
    public int getSize()
    {
        return size;
    }
    
    public Song random()
    {
        
        SongNode randomNode=findRandomSongNode();
        
        Song randomSong=randomNode.getSong();
        
        return randomSong;
    }
    

    
    private SongNode findRandomSongNode()
    {
        int randomInt=(int)(Math.random()*(size)+1);
        
        SongNode nodePtr=head;
        int elementCounter=1;
        
        while(elementCounter<randomInt)
        {
            nodePtr=nodePtr.getNext();
            elementCounter++;
        }
        
        return nodePtr;
    }
    
    public void shuffle()
    {
        SongLinkedList newList=new SongLinkedList();
        
        while(size>0)
        {
            SongNode randomNode=findRandomSongNode();
            
            SongNode toBeInsert=new SongNode(randomNode.getSong());
            
            if(toBeInsert.getSong().getName().equals(cursor.getSong().getName()))
            {
                newList.cursor=toBeInsert;
            }
            
            deleteGivenNode(randomNode);
            
            newList.insertNodeAtHead(toBeInsert);
        }
        
        head=newList.head;
        tail=newList.tail;
        size=newList.size;
        cursor=newList.cursor;
        
        //If we are outside of the for loop then that means that the shuffle is complete
        //Therefore we make the newList as the songList
    }
    
    private void insertNodeAtHead(SongNode givenNode)
    {
        if(head!=null)
        {
            givenNode.setNext(head);
            head.setPrev(givenNode);
            head=givenNode;
            
            size++;
        }
        else
        {
            head=givenNode;
            cursor=head;
            tail=head;
            
            size++;
        }
        
    }
    
    private void deleteGivenNode(SongNode toRemove)
    {
        if(toRemove!=head&&toRemove!=tail)
        {
            toRemove.getPrev().setNext(toRemove.getNext());
            toRemove.getNext().setPrev(toRemove.getPrev());

            size--;
        }
        else if(toRemove==head&&size==1)
        {
            head=null;
            tail=null;
            cursor=null;
            
            size--;
        }
        //Don't need this case, remove after explaining
        else if(toRemove==tail&&size==1)
        {
            head=null;
            tail=null;
            cursor=null;
            
            size--;
        }
        else if(toRemove==head)
        {
            head=head.getNext();
            head.setPrev(null);
            
            size--;
        }
        else if(toRemove==tail)
        {
            tail=tail.getPrev();
            tail.setNext(null);
            
            size--;
        }
    }
    
    public void printPlayerList()
    {
        String titleFormat="%-40s|%-24s|%-24s|%-24s";
        
        System.out.println("Playlist:");
        System.out.println(String.format(titleFormat, "Song","Artist","Album","Length (s)")); 
        System.out.println("-----------------------------------------------------"
                + "-----------------------------------------------------------");
        
        System.out.println(this);
    }
    
    public void deleteAll()
    {
        head=null;
        tail=null;
        size=0;
        cursor=null;
    }
    
    public String toString()
    {
        String output="";
        
        
        String songFormat="%-40s%-25s%-30s%-3s";
        
        SongNode nodePtr=head;
        
        while(nodePtr!=null)
        {
            Song nodeSong=nodePtr.getSong();

            String songName=nodeSong.getName();
            String songArtist=nodeSong.getArtist();
            String albumName=nodeSong.getAlbum();
            String songLength=nodeSong.getLength()+"";
            
            
            //This means that we need to add a arrow key after it because
            //the song nodePtr is on right now is the cursor
            if(nodePtr==cursor)
            {
                output=output+String.format(songFormat,songName,songArtist,albumName,
                        songLength)+" <-\n";
            }
            //This means that the song is not the cursor therefore, we just
            //need the song name, artist name, album name, and the length
            else
            {
                output=output+String.format(songFormat,songName,songArtist,albumName,
                        songLength)+"\n";
            }
            
            nodePtr=nodePtr.getNext();
        }
        
        return output;
        
    }
    
    
    
    
}