package HW2Edited;

/**
 * The SongNode class is a node wrapper class for the Song object, it will essentially
 * make up one node of a doubly linked list that overall represent a play list. 
 * Therefore the data that one of the SongNode is storing will be a Song object,
 * and it will contain the references to the previous node and the next node in the
 * doubly linked list.
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */

public class SongNode
{
    //This SongNode variable will be representing the SongNode of the previous node 
    //relative to this current node
    private SongNode prev;
    
    //This SongNode variable will be representing the SongNode of the next node
    //relative to this current node
    private SongNode next;
    
    //An Song to represent what is stored inside this current SongNode
    private Song data;
    
    /**
     * Default constructor of the SongNode class, it will just created a SongNode
     * object with all of the variables, including the references to the previous node,
     * the references to the next node, and the Song it contains to null. Later on
     * the user can change the information for each variables using the mutator
     * methods
     */
    public SongNode()
    {
        //Setting the instance variables to the default value
        prev=null;
        next=null;
        data=null;
    }
    
    /**
     * Parameterized constructor of the SongNode class, it will constructor a SongNode
     * object with a specified Song to be stored inside the SongNode. The references
     * to the previous and next node still remains null
     * 
     * @param givenSong 
     *  The specific Song object to be stored inside of the SongNode
     */
    public SongNode(Song givenSong)
    {
        //Setting the instance variables with the givenSong, but prev and next
        //still remains null
        prev=null;
        next=null;
        data=givenSong;
    }
    
    /**
     * Returns the Song that is stored within the SongNode
     * 
     * @return Returns the Song object that is stored in the SongNode object
     */
    public Song getSong()
    {
        return data;
    }
    
    /**
     * Returns the SongNode references to the next SongNode relative to the current
     * SongNode
     * 
     * @return Returns the next SongNode references relative to this SongNode
     */
    public SongNode getNext()
    {
        return next;
    }
    
    /**
     * Returns the SongNode references to the previous SongNode relative to the 
     * current SongNode
     * 
     * @return Returns the previous SongNode references relative to this SongNode
     */
    public SongNode getPrev()
    {
        return prev;
    }
    
    /**
     * The mutator method for the references to the next SongNode of this
     * SongNode
     * <p>
     * Postcondition: The references to the next SongNode of this SongNode is
     * set to givenNode
     * 
     * @param givenNode
     *  The SongNode to set the Song's next SongNode to
     */
    public void setNext(SongNode givenNode)
    {
        next=givenNode;
    }
    
    /**
     * The mutator method for the references to previous SongNode of this
     * SongNode
     * <p>
     * Postcondition: The references to the previous SongNode of this SongNode is
     * set tot givenNode
     * 
     * @param givenNode 
     *  The SongNode to set the Song's prev SongNode to
     */
    public void setPrev(SongNode givenNode)
    {
        prev=givenNode;
    }
    
    /**
     * The mutator method for the references to the Song that the SongNode is
     * holding
     * <p>
     * Postcondition: The Song that this SongNode contains is set to the
     * given Song object
     * 
     * @param givenSong 
     *  The Song to set the Song that the SongNode contains to
     */
    public void setData(Song givenSong)
    {
        data=givenSong;
    }
}