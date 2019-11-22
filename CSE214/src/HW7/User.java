package HW7;

public class User
{
    //Instance variables
    private String userName;
    private int indexPos;
    private static int userCount=0;
    
    //Paramaterized constructor of the user
    public User(String givenName)
    {
        userName=givenName;
        
        //We can just set the indexPos to the userCount because of how we are assigning
        //in the index position before incrementing the userCount. So if this constructor
        //is called 
        indexPos=userCount;
        //We have to increment the userCount because we just added in a new user
        userCount++;
    }
    
    //Accessor method
    public int getUserCount()
    {
        return userCount;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public int getIndexPos()
    {
        return indexPos;
    }

    //Mutator method
    public void setUserName(String givenName)
    {
        userName=givenName;
    }
    
    public String toString()
    {
        
    }
    
}