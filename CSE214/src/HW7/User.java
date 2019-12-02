package HW7;

import java.io.Serializable;

public class User implements Serializable
{
    //Instance variables
    private String userName;
    private int indexPos;
    private static int userCount=0;
    
    private int numberOfFollower;
    private int numberOfFollowing;
    
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
        
        numberOfFollower=0;
        numberOfFollowing=0;
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
    
    public int getNumFollower()
    {
        return numberOfFollower;
    }
    
    public int getNumFollowing()
    {
        return numberOfFollowing;
    }

    //Mutator method
    public void setUserName(String givenName)
    {
        userName=givenName;
    }
    
    public void setNumFollowing(int givenNum)
    {
        numberOfFollowing=givenNum;
    }
    
    public void setNumFollower(int givenNum)
    {
        numberOfFollower=givenNum;
    }
    
    public void setIndexPos(int givenNum)
    {
        indexPos=givenNum;
    }
    
    public static void decreaseUserCount()
    {
        userCount--;
    }
    
    public void decreaseFollower()
    {
        numberOfFollower--;
    }
    
    public void decreaseFollowing()
    {
        numberOfFollowing--;
    }
    
    //toString method
    public String toString()
    {
        String output=userName;
        
        return output;
    }
    

   
    
}