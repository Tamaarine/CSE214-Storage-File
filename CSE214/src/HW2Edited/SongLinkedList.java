package HW2Edited;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The SongLinkedList class represent the whole Doubly-Linked List class that holds
 * Song object as data for each node. It contains a head to represent the first
 * SongNode of the doubly-linked list, it contains a tail to represent the last
 * SongNode of the doubly-linked list, it also contains a cursor to point to 
 * certain SongNode. The SongLinkedList will have size variable to keep track of
 * the elements that it have in it's link
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */

public class SongLinkedList
{
    //This SongNode will be holding the reference to the first SongNode element
    //of the Doubly-Linked List chain
    private SongNode head;
    
    //This SongNode will be holding the refernce to the last SongNode element
    //of the Doubly-Linked List chain
    private SongNode tail;
    
    //This will be representing a pointer to point to a specific SongNode element
    //as the Linked List is added or removed with elements
    private SongNode cursor;
    
    //This variable will be keeping track of the total amount of SongNode that is
    //in the Doubly-Linked list
    private int size;
    
    //The AudioInputStream class audioInput and Clip class c, combine to work
    //together to play the song in the source folder
    private AudioInputStream audioInput;
    private Clip c;
    
    /**
     * Default constructor of the SongLinkedList, all of the values are set to
     * default, the head, tail, and cursor will all be set to null and the size
     * will be set to 0 because there is no element in the Linked-List in the
     * beginning
     */
    public SongLinkedList()
    {
        //Setting the instance variables to the default value
        head=null;
        tail=null;
        cursor=null;
        size=0;
    }
    
    /**
     * Returns the head SongNode of the SongLinkedList, in other words
     * the first element of the list
     * 
     * @return 
     *  Returns head, which is the first element of the list
     */
    public SongNode getHead()
    {
        return head;
    }
    
    /**
     * Returns the tail SongNode of the SongLinkedList, in other words
     * the last element of the list
     * 
     * @return 
     *  Returns tail, which is the last element of the the list
     */
    public SongNode getTail()
    {
        return tail;
    }
    
    /**
     * Returns the size of the SongLinkedList
     * 
     * @return 
     *  Returns a int that represent the size of the list or the total elements
     *  that is in the SongLinkedList
     */
    public int getSize()
    {
        return size;
    }
    
    /**
     * This method plays the song indicated by name. It will stop any song before
     * playing a new song
     * <p>
     * Precondition: The name must match an actual song name in the playlist and
     * there must be a file associated with it
     * <p>
     * Postcondition: The song is now playing
     * 
     * @param name
     *  The name of the song to play
     * @throws IllegalArgumentException
     *  IllegalArgumentException is thrown to indicate that the provided name does
     *  not correspond to a song in the playlist, or that the wav file could not be
     *  found.
     */
    public void play(String name) throws IllegalArgumentException
    {
        //Before we go straight into playing the given name of the song by using
        //AudioInputStream and Clip we need to perform a search in the list to
        //make sure that the given name is in the list first, or else it is playing
        //a song that is not even in the list and that does not make sense. We should
        //only be able to play the song within the SongLinkedList
        //This is calling the linearSearch helper method to see if we can find a
        //Corresponding song with the same song name.
        Song targetSong=linearSearch(name);
        
        //This means that targetSong is found within the list thus we have to play
        //it
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
            //This exception is to handle the exception of the user entering a
            //Song that is in the list but the wav file is not in the source file
            //to indicate that the song cannot be played because it is not in the 
            //source file
            catch(Exception e)
            {
                System.out.println("The song you entered is either not in the"
                        + " source folder or the song you entered is not on the"
                        + " playlist, please try again\n");
            }
        }
        //This means that targetSound with the same name is not found within the
        //list therefore throw the IllegalArgumentException to indicate that
        //the song is not found thus we can't play it
        else
        {
            throw new IllegalArgumentException("'"+name+"' not found");
        }
        
    }
    
    /**
     * The helper method for play method. It will look through the SongLinkedList
     * from head to tail to see if it can find a Song with the name songTargetName.
     * If it finds it the Song with the corresponding name will be returned
     * immediately, however, if by the time when the search reaches the end and it
     * doesn't find a song with the corresponding name it will return a null song
     * to indicate that there is no Song found with songTargetName
     * 
     * @param songTargetName
     *  The name of the song to look for in the SongLinkedList
     * @return The Song object that have the same name as songTargetName, if the
     *  method does not find a Song with the same name as songTargetName it will
     *  return a null Song to indicate that there is no Song with the same name
     *  as songTargetName
     */
    private Song linearSearch(String songTargetName)
    {
        //This variable will be holding the Song that is found to have the same
        //name as songTargetName, it will be initlized to null as we assume
        //there is no Song with the same name until we find it
        Song output=null;
        
        //nodePtr will be a temporary pointer variable to traverse through the
        //SongLinkedList and it will start with head
        SongNode nodePtr=head;
        
        //This while loop continues to traverse through the SongLinkedList until
        //the end
        while(nodePtr!=null)
        {
            //This means that we have found a Song that have the exact same name
            //as songTargetName which means we have found the corresponding Song
            if(nodePtr.getSong().getName().equals(songTargetName))
            {
                //Since we have found the desired Song, we assigned it to output
                //and return it right here to terminate the loop so it doeesn't 
                //need to go through the entire list for nothing
                output=nodePtr.getSong();
                
                return output;
            }
            
            //If we are here that means that the current SongNode doesn't
            //contain the Song that have the corresponding song name thus we
            //move to the next SongNode
            nodePtr=nodePtr.getNext();
        }
        
        //If we are out here that means that the while loop did not find the 
        //Song with the same name therefore we return the null Song object to indicate
        //that we did not find the Song
        return output;
    }
    
    /**
     * This method will determine whether or not the Clip c that is being used by
     * the SongLinkedList is playing or inactive.
     * 
     * @return Returns true if the Clip C is running and false if the Clip is not
     *  running
     */
    public boolean isClipRunning()
    {
        //This makes sure that Clip c is instantiated first before going to check
        //whether or not the clip is running
        if(c!=null)
        {
            return c.isRunning();
        }
        
        //If we are out here that means that the clip is not instanitated yet,
        //therefore we just return false
        return false;
    }
    
    /**
     * This method will stop the Clip c that is used by the SongLinkedList from
     * playing
     * <p>
     * Precondition: Clip c must be instantiated
     * <p>
     * Postcondition: The clip is stopped
     */
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
    
    /**
     * Moves the cursor to point at the next SongNode, it is a part of the user's
     * main way of moving around the Linked List
     * <p>
     * Precondition: The list is not empty, cursor is not null
     * <p>
     * Postcondition: The cursor has been advanced to the next SongNode, or has
     * remained at the tail of the list
     * 
     */
    public void cursorForwards()
    {
        //This means that the cursor or the list is not emtpy, therefore we may
        //be able to move the cursor forward
        if(cursor!=null)
        {
            //This means that since the cursor is not the tail therefore we can
            //move the cursor to point to the next node
            if(cursor!=tail)
            {
                cursor=cursor.getNext();
                System.out.println("Cursor moved to next song.\n");
            }
            //This means that cursor is already at the end therefore we cannot
            //move the cursor anymore
            else
            {
                System.out.println("Already at end of playlist.\n");
            }
        }
        //This means that the cursor or the list is indeed empty, therefore
        //there is no point of moving cursor because there is none
        else
        {
            System.out.println("There is no song to move forward to.\n");
        }
    }
    
    /**
     * Moves the cursor to point at the previous SongNode, this is also a part of
     * user's main way of moving around the Linked List
     * <p>
     * Precondition: The list is not empty, cursor is not null
     * <p>
     * Postcondition: The cursor has been moved back to the previous SongNode, or
     * has remained at the head of the list
     */
    public void cursorBackwards()
    {
        //This means that the cursor or the list is not empty, therefore we may
        //be able to move the cursor backward
        if(cursor!=null)
        {
            //This means that since the cursor is not the head therefore we can
            //move the cursor to point to the previous node
            if(cursor!=head)
            {
                cursor=cursor.getPrev();
                System.out.println("Cursor moved to previous song.\n");
            }
            //This means that the cursor is already at the beginning therefore
            //we cannot move the cursor anymore to the front
            else
            {
                System.out.println("Already at beginning of playlist.\n");
            }
        }
        //This means that the cursor or the list is empty, therefore there is
        //absolutely no point of moving cursor since there is no cursor anyway
        else
        {
            System.out.println("There is no song to move backward to.\n");
        }
    }
    
    /**
     * Inserts a song into the playlist after the cursor position, if there is no
     * song in the playlist then it will just add it to the playlist and update
     * all of the pointer variable including head, tail, cursor, and size accordingly
     * <p>
     * Precondition: The newSong object has been instantiated
     * <p>
     * Postcondition: The new Song is inserted into the playlist after the position
     * of the cursor. All Song objects previously in the playlist are still in the playlist,
     * and the order of the playlist is preserved. The cursor now points to the inserted
     * node
     * 
     * @param newSong
     *  The Song to be inserted into the playlist
     * @throws IllegalArgumentException 
     *  IllegalArgumentException is thrown to indicate that newSong is null
     */
    public void insertAfterCursor(Song newSong) throws IllegalArgumentException
    {
        //This is turning the newSong Song object into a SongNode with no prev and
        //next references
        SongNode newNode=new SongNode(newSong);
        
        //This means that the newSong is null therefore we throw the IllegalArugmentException
        if(newSong==null)
        {
            throw new IllegalArgumentException("The song you entered is invalid");
        }
        //This means that the newSong is not null therefore we have to add it
        //into the list
        else
        {
            //This means that the list is empty, therefore we have to set all of the
            //pointer variables head, tail, cursor to the newly created node
            //and update the size
            if(cursor==null)
            {
                head=new SongNode(newSong);
                tail=head;
                cursor=head;

                size++;
            }
            //This means that the list is not empty, but we have to differentiate
            //two cases of insertion, one at the end of the list and one that is not
            //at the end of the list
            else
            {
                //This means that the cursor is the tail which means this is the case
                //where insertion occurs at the end of the list. Thus we need to add
                //the newNode after the cursor, and after that we have to update cursor
                //to be the newNode and also update tail to become the cursor which is the
                //last element. And also the size
                if(cursor.getNext()==null)
                {
                    newNode.setNext(null);
                    newNode.setPrev(cursor);
                    cursor.setNext(newNode);
                    
                    size++;
                    
                    cursor=newNode;
                    tail=cursor;
                }
                //This means that the cursor is not at the tail which means this is the
                //case where insertion is occuring not at the end of the list. Thus
                //we just need to add the newNode after the cursor and update the
                //cursor only. And also the size
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
    
    /**
     * Remove the SongNode referenced by the cursor and returns the Song contained
     * within the node
     * <p>
     * Precondition: The cursor is not null
     * <p>
     * Postcondition: The SongNode referenced by the cursor has been removed from the
     * playlist. And the cursor now references the next node, or if there is no next
     * node then references to the previous node
     * 
     * @return Returns the Song that is contained within the removed node
     */
    public Song removeCursor()
    {
        //This is creating a holder variable that will contain the SongNode that
        //is removed
        SongNode nodeRemoved=new SongNode();
        
        //This means that the cursor is not at the head and is not at the tail
        //The removal precedure is just setting the refernces of the previous and
        //next node. Don't need to update tail or head. But we do need to update size
        if(cursor!=head&&cursor!=tail)
        {
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            
            size--;
            
            //We set the SongNode at cursor to the holder variable nodeRemoved
            //so we can move the cursor without losing the removed node
            nodeRemoved=cursor;
            
            //We update the cursor to be the next node
            cursor=cursor.getNext();
        }
        //This means that the cursor is at the head and there is only one element
        //left therefore we just need to set everything including the cursor to null
        //and update the size. This will also handle the case when cursor is at the tail
        //because when size is 1 head and tail are the same.
        else if(cursor==head&&size==1)
        {
            head=null;
            tail=null;
            
            nodeRemoved=cursor;
            
            cursor=null;
            
            size--;
        }
        //This means that the cursor is pointing at the head and there is still more
        //than 1 element in the Linked List. Therefore we just need to set head to be
        //the next node after it and set prev of the next node to be null to remove the
        //head. Don't forget to update size and cursor.
        else if(cursor==head)
        {
            nodeRemoved=head;
            
            head=head.getNext();
            head.setPrev(null);
            cursor=head;
            
            size--;
        }
        //This means that the cursor is pointing at the tail and there is still more than
        //1 element in the Linked List. Therefore we just need to set tail to be the
        //previous node before it and set next of the previous node to be null to
        //remove the tail. Don't forget to update size and cursor
        else if(cursor==tail)
        {
            nodeRemoved=tail;
            
            tail=tail.getPrev();
            tail.setNext(null);
            cursor=tail;
            
            size--;
        }
        
        //Then we are finally gettign the Song from the removed node and return it
        Song removedSong=nodeRemoved.getSong();
        
        return removedSong;
    }
    
    /**
     * Selects one of the songs in the playlist and play it at random. It will not
     * change the order of the playlist
     * <p>
     * Postcondition: The randomly selected song is now playing
     * 
     * @return The Song which was randomly picked
     */
    public Song random()
    {
        //Calling the helper method to find a random SongNode
        SongNode randomNode=findRandomSongNode();
        
        //Then from the SongNode we get the randomly selected Song from it and
        //return it
        Song randomSong=randomNode.getSong();
        
        return randomSong;
    }
    
    /**
     * Helper method of the random method. It is used to find a random SongNode
     * from the Linked List
     * 
     * @return A randomly selected SongNode from the SongLinkedList
     */
    private SongNode findRandomSongNode()
    {
        //This generates a random nth element that will be the SongNode to be
        //returned
        int randomInt=(int)(Math.random()*(size)+1);
        
        //We are going to perform a search throughout the Linked List until
        //we arrived at the randomInt-th element
        SongNode nodePtr=head;
        
        //This will be the counter to make sure we are counting correctly
        int elementCounter=1;
        
        //This while loop will keep on going until we reach the randomInt-th element
        while(elementCounter<randomInt)
        {
            nodePtr=nodePtr.getNext();
            elementCounter++;
        }
        
        //If we are outside of the while loop it means that nodePtr contain the
        //randomly selected SongNode
        return nodePtr;
    }
    
    /**
     * Randomly shuffles the order of the songs contained within the playlist
     * <p>
     * Postcondition: The playlist is randomly reordererd. Cursor should reference
     * the SongNode which contains the same Song as when this method was entered
     */
    public void shuffle()
    {
        //This is creating a brand new list to throw the randomly selected Songs
        //from the old list into it, so by the time when old list is empty
        //newList is a shuffled version of the old list
        SongLinkedList newList=new SongLinkedList();
        
        //This means that while there is still element inside the old list
        //we keep removing and inserting from the old list to the new list
        while(size>0)
        {
            //This is calling the findRandomSongNode method from the random method
            //to help us find a random node
            SongNode randomNode=findRandomSongNode();
            
            //This is making a new SongNode to be insert into the newList, we need
            //to make sure we use NEW because we don't want the old references
            //of prev and next
            SongNode toBeInsert=new SongNode(randomNode.getSong());
            
            //This if-statement is making sure that if the randomly selected SongNode
            //is the cursor of the old list, it will also be the cursor in the new list
            if(toBeInsert.getSong().getName().equals(cursor.getSong().getName()))
            {
                //Setting the new list's cursor to be the old list's cursor's song
                newList.cursor=toBeInsert;
            }
            
            //This then deletes the randomly selected SongNode from the old list
            deleteGivenNode(randomNode);
            
            //This then inserts the randomly selected SongNode into the new list
            //with NEW REFERENCES
            newList.insertNodeAsHead(toBeInsert);
        }
        
        //Then we make the old list's pointer variable point to the new list's
        //pointer variable
        head=newList.head;
        tail=newList.tail;
        size=newList.size;
        cursor=newList.cursor;
    }
    
    /**
     * Helper method of the shuffle method, it inserts a given SongNode into
     * a SongLinkedList as the head of it. This will be used to put Songs into
     * the new list, because the SongNode we give is randomized, just inserting
     * at the head should be consider random as well
     * 
     * @param givenNode 
     *  The SongNode to be inserted as the head to the SongLinkedList
     */
    private void insertNodeAsHead(SongNode givenNode)
    {
        //This means that if the list is not empty we will be setting the givenNode
        //as the head of the list. And make sure we increment the size
        if(head!=null)
        {
            givenNode.setNext(head);
            head.setPrev(givenNode);
            head=givenNode;
            
            size++;
        }
        //This means that the list is empty therefore we just make every pointer
        //variable to become the reference hold by head. Also make sure to increment
        //the size
        else
        {
            head=givenNode;
            cursor=head;
            tail=head;
            
            size++;
        }
    }
    
    /**
     * Helper method of the shuffle method, which will delete the given node that
     * you want to remove from a SongLinkedList. It will be used to delete the
     * nodes that is randomly pulled out of the old list to make sure you don't
     * randomly select the same SongNode again
     * 
     * @param toRemove 
     *  The SongNode to be removed from the SongLinkedList
     */
    private void deleteGivenNode(SongNode toRemove)
    {
        //This means that the SongNode we need to remove is not the head and is
        //not the tail therefore it is just in betweene, thus we don't need to
        //worry about updating the pointer variables. Just need to skip
        //the SongNode we are removing and update the size
        if(toRemove!=head&&toRemove!=tail)
        {
            toRemove.getPrev().setNext(toRemove.getNext());
            toRemove.getNext().setPrev(toRemove.getPrev());

            size--;
        }
        //This means that the SongNode we need to remove is the head and there is
        //only one element left, therefore we just set every pointer variable to
        //null and decrement the size. This will also handle the case if the one we need
        //to remove is the tail because if there is only one element, head and tail
        //are the same
        else if(toRemove==head&&size==1)
        {
            head=null;
            tail=null;
            cursor=null;
            
            size--;
        }
        //This means that the SongNode we need to remove is just the head and there
        //is more than one element therefore we need to make the head to the next
        //SongNode and set the next SongNode's previous to null to cut off the old
        //head and decrement the size
        else if(toRemove==head)
        {
            head=head.getNext();
            head.setPrev(null);
            
            size--;
        }
        //This means that the SongNode we need to remove is just the tail and there is
        //more than one element therefore we need to make the tial to the previous
        //SongNode and set the previous SongNode's next to null to cut off the 
        //old tail and decrement the size
        else if(toRemove==tail)
        {
            tail=tail.getPrev();
            tail.setNext(null);
            
            size--;
        }
    }
    
    /**
     * Prints the playlist in a neatly-formatted table. This is using the toString method
     * of the SongLinkedList to print the rest of the tablar form
     */
    public void printPlayerList()
    {
        String titleFormat="%-40s|%-24s|%-24s|%-24s";
        
        System.out.println("Playlist:");
        System.out.println(String.format(titleFormat, "Song","Artist","Album","Length (s)")); 
        System.out.println("-----------------------------------------------------"
                + "-----------------------------------------------------------");
        
        System.out.println(this);
    }
    
    /**
     * This will simply delete all of the songs from the playlist
     * <p>
     * Postcondition: All songs have been removed
     */
    public void deleteAll()
    {
        //Just sets everything to the default value again
        head=null;
        tail=null;
        size=0;
        cursor=null;
    }
    
    /**
     * Returns a neatly formatted String representation of the playlist
     * 
     * @return Returns a neatly formatted String representing the playlist in
     * tabular form
     */
    public String toString()
    {
        String output="";
        
        String songFormat="%-40s%-25s%-30s%-3s";
        
        SongNode nodePtr=head;
        
        //This is making the neatly formatted tablar String representation of the
        //list
        while(nodePtr!=null)
        {
            Song nodeSong=nodePtr.getSong();
            
            //Getting the information of the Song
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