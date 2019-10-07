package HW2Edited;

/**
 * The Song class will be modeling a song in a playlist, which will contain
 * multiple songs. A single song have a name, the song's artist name, the album
 * that this song belongs to, and the duration of this song as an int in the units
 * of seconds
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class Song
{
    //This is used to represent the Song's name in a String.
    //This variable name will also be used as the reference to the .wav file
    //for the audio in the source file
    private String name;
    
    //This is the Song's artist name in a form of String
    private String artist;
    
    //This represent the album that the Song is released on in a
    //form of String
    private String album;
    
    //This is the Song's duration in a form of int, and it represented in the units
    //of seconds
    private int length;
    
    /**
     * Default constructor of the Song class, it is just used to created a Song
     * object that have empty attributes so that the user can set it later
     * after it is created using the mutator methods
     */
    public Song()
    {
        //Setting all of the instance variables to a default value
        name="";
        artist="";
        album="";
        length=0;
    }
    
    /**
     * The parameterized constructor of the Song class in which the Song object
     * is made with the given information
     * 
     * @param givenName
     *  The name of the song, this is also what the name of the wav file in the source
     *  file, so that it is less complicated if you want to play the song
     * @param givenArtist
     *  The name of the performer of the song
     * @param givenAlbum
     *  The album that this song is released on 
     * @param givenLength 
     *  The length of the song in the unit of seconds
     */
    public Song(String givenName,String givenArtist,String givenAlbum,int givenLength)
    {
        //Setting the instance variables to the corresponding given informations
        name=givenName;
        artist=givenArtist;
        album=givenAlbum;
        length=givenLength;
    }

    /**
     * Return the name of the title of the Song
     * 
     * @return Returns the name of the song
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Return the name artist or the performer of the Song
     * 
     * @return Returns the artist's name for the song
     */
    public String getArtist()
    {
        return artist;
    }
    
    /**
     * Return the name of the album of the song that is released on
     * 
     * @return Returns the name of the album that the song is released on
     */
    public String getAlbum()
    {
        return album;
    }
    
    /**
     * Return the duration of the song in the unit of seconds
     * 
     * @return Returns a int that represent the duration of the song in the unit
     * of seconds
     */
    public int getLength()
    {
        return length;
    }

    /**
     * The mutator method for the Song's name
     * <p>
     * Postcondition: The name of the Song is set to n
     * 
     * @param n 
     *  The given name that you want to set the Song's name to
     */
    public void setName(String n)
    {
        name=n;
    }
    
    /**
     * The mutator method for the Song's artist
     * <p>
     * Postcondition: The artist of the Song is set to n
     * 
     * @param n 
     *  The given artist name that you want to set the Song's artist name to
     */
    public void setArtist(String n)
    {
        artist=n;
    }
    
    /**
     * The mutator method for the Song's album name
     * <p>
     * Postcondition: The Song's album name is set to n
     * 
     * @param n 
     *  The given album name to set the Song's album name to
     */
    public void setAlbum(String n)
    {
        album=n;
    }
    
    /**
     * The mutator method for the Song's duration length, the given length to set
     * the Song's duration to must be a valid length which must be greater than
     * 0 seconds or else it will throw an InvalidLengthException
     * 
     * @param n
     *  The given length to set the Song's length to
     * @throws InvalidLengthException 
     *  InvalidLengthException is thrown if the given length n is less than or equal
     *  to 0 to indicate that the given length is not a valid length to set the
     * Song's length to
     */
    public void setLength(int n) throws InvalidLengthException
    {
        //This means that the given length is less than or equals to 0 therefore
        //we must throw the InvalidLengthException
        if(n<=0)
        {
            throw new InvalidLengthException("You have entered an invalid length");
        }
        
        length=n;
    }
}