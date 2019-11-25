package HW7;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FollowGraph implements Serializable
{
    //Instaence variables
    private List<User> users;
    private static final int MAX_USERS=100;
    private boolean connections[][];
    
    //Default constructor of the FollowGraph class
    public FollowGraph()
    {
        users=new ArrayList<User>();
        
        connections=new boolean[MAX_USERS][MAX_USERS];
        
        
    }
    
    public void addUser(String userName)
    {
        //This is the new user that is oging to be added to the list of users
        User toBeAdded=new User(userName);
        
        //This variable will be determining whether the user already existed in
        //the ArrayList
        boolean userExisted=false;
        
        //However before we do that we must check the list of user to check
        //if the user already existed
        for(int i=0;i<users.size();i++)
        {
            User userAtI=users.get(i);
            
            if(userAtI.getUserName().equals(userName))
            {
                //Since this if-statement is true then that means that the
                //user is already inside the ArrayList
                userExisted=true;
            }
        }
        
        //And if we are out of the for loop and userExisted is false, then we
        //can add in the user
        if(!userExisted)
        {
            users.add(toBeAdded);
            
            System.out.println(userName+" has been successfully added\n");
        }
        else
        {
            System.out.println(userName+" already exist in the system\n");
        }
    }
    
    public void addConnection(String userFrom, String userTo)
    {
        User theUserFrom=null;
        User theUserTo=null;
        
        //This for loop will be responisble going through the entire list of
        //users and finding the two users, userFrom and userTo's User object
        for(int i=0;i<users.size();i++)
        {
            User userAtI=users.get(i);
            
            //This means that the userFrom is found
            if(userAtI.getUserName().equals(userFrom))
            {
                theUserFrom=userAtI;
            }
            //This means that the userTo is found
            else if(userAtI.getUserName().equals(userTo))
            {
                theUserTo=userAtI;
            }
        }
        
        //After the for loop either the userFrom and userTo are both found or
        //one of the is null or both of them are null, if one of them are not found
        //we don't do anything. But if they are found then we will add the new
        //connections by getting their respective index
        if(theUserFrom!=null&&theUserTo!=null)
        {
            //Getting the index of both user
            int userFromIndex=theUserFrom.getIndexPos();
            int userToIndex=theUserTo.getIndexPos();
            
            //Adding the connecting in the matrix and since the graph is directed
            //only the connection userFrom to userTo is established
            connections[userFromIndex][userToIndex]=true;
        }
        
    }
    
    public void removeConnection(String userFrom, String userTo)
    {
        User theUserFrom=null;
        User theUserTo=null;
        
        //This for loop will be responsible going through the list of users
        //to find whether or not userFrom and userTo exists before proceeding to
        //do the removal
        for(int i=0;i<users.size();i++)
        {
            User userAtI=users.get(i);
            
            //This means that the userFrom is found in the list of users
            if(userAtI.getUserName().equals(userFrom))
            {
                theUserFrom=userAtI;
            }
            //This means that the userTo is found in the list of usersd
            else if(userAtI.getUserName().equals(userTo))
            {
                theUserTo=userAtI;
            }
        }
        
        //This means that both of theUserFrom and theUserTo exist in the
        //list of users therefore we will proceed to remove the connections
        if(theUserFrom!=null&&theUserTo!=null)
        {
            //Getting the index of both user
            int userFromIndex=theUserFrom.getIndexPos();
            int userToIndex=theUserTo.getIndexPos();
            
            //Removing the connections from userFrom to userTo
            connections[userFromIndex][userToIndex]=false;
        }
    }
    
    public String shortestPath(String userFrom, String userTo)
    {
        
    }
    
    public List<String> allPaths(String userFrom, String userTo)
    {
        
    }
    
    public void printAllUsers(Comparator comp)
    {
        for(int r=0;r<connections.length;r++)
        {
            for(int c=0;c<connections[0].length;c++)
            {
                System.out.print(connections[r][c]+", ");
            }
            
            System.out.println("");
        }
    }
    
    public void printAllFollowers(String userName)
    {
        
    }
    
    public void printAllFollowing(String userName)
    {
        
    }
    
    public List<String> findAllLoops()
    {
        
    }
    
    public void loadAllUsers(String filename)
    {
        
    }
    
    public void loadAllConnections(String filename)
    {
        
    }
    
}