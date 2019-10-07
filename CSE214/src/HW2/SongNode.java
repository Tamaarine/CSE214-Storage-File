package HW2;

public class SongNode
{
    //Instance variables
    private SongNode prev;
    private SongNode next;
    private Song data;
    
    public SongNode()
    {
        prev=null;
        next=null;
        data=null;
    }
    
    public SongNode(Song givenSong)
    {
        prev=null;
        next=null;
        data=givenSong;
    }
    
    public SongNode(SongNode givenPrev,SongNode givenNext,Song givenSong)
    {
        prev=givenPrev;
        next=givenNext;
        data=givenSong;
    }
    
    //Accessor methods
    public Song getSong()
    {
        return data;
    }
    
    public SongNode getNext()
    {
        return next;
    }
    
    public SongNode getPrev()
    {
        return prev;
    }
    //Mutator methods
    public void setNext(SongNode givenNode)
    {
        next=givenNode;
    }
    
    public void setPrev(SongNode givenNode)
    {
        prev=givenNode;
    }
    
    public void setData(Song givenSong)
    {
        data=givenSong;
    }
    
  
    
    
    
    
    
}