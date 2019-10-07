package HW2;

public class Song
{
    //Instance variables
    private String name;
    private String artist;
    private String album;
    private int length;
    
    public Song()
    {
        name="";
        artist="";
        album="";
        length=0;
    }
    
    public Song(String givenName,String givenArtist,String givenAlbum,int givenLength)
    {
        name=givenName;
        artist=givenArtist;
        album=givenAlbum;
        length=givenLength;
    }
    
    //Accessor methods
    public String getName()
    {
        return name;
    }
    
    public String getArtist()
    {
        return artist;
    }
    
    public String getAlbum()
    {
        return album;
    }
    
    public int getLength()
    {
        return length;
    }
    
    //Mutator methods
    public void setName(String n)
    {
        name=n;
    }
    
    public void setArtist(String n)
    {
        artist=n;
    }
    
    public void setAlbum(String n)
    {
        album=n;
    }
    
    public void setLength(int n) throws InvalidLengthException
    {
        if(n<=0)
        {
            throw new InvalidLengthException("You have entered an invalid length");
        }
        
        length=n;
    }
    
    public String toString()
    {
        return name;
    }
    
    
    
    
    
    
    
    
}