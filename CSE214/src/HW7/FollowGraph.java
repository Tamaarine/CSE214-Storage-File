package HW7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
            //This is the new user that is oging to be added to the list of users
            User toBeAdded=new User(userName);
            
            users.add(toBeAdded);
        }
    }
    
    public void addConnection(String userFrom, String userTo)
    {
        User theUserFrom=null;
        User theUserTo=null;
        
        if(!userFrom.equals(userTo))
        {
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

                //This means that the connection did not exist before therefore we have
                //to make it exist and increment their follower and following count
                //respectively
                if(!connections[userFromIndex][userToIndex])
                {
                    //Updating the number of followers and followings
                    //This is information for userFrom
                    int numberOfFollowing=theUserFrom.getNumFollowing();

                    //Updating the number of following for theUserFrom because it is following
                    //a new person
                    theUserFrom.setNumFollowing(numberOfFollowing+1);

                    //This is inforamtion for userTo
                    int numberOfFollower=theUserTo.getNumFollower();

                    //Updating the number of follower for the userTo because it have
                    //a new person following them
                    theUserTo.setNumFollower(numberOfFollower+1);

                    //Adding the connecting in the matrix and since the graph is directed
                    //only the connection userFrom to userTo is established
                    connections[userFromIndex][userToIndex]=true;
                }
                //Else we don't need to do any more updates because the connections already
                //existed
            }
        }
    }
    
    public void removeUser(String user)
    {
        User toRemove=null;
        
        for(int i=0;i<users.size();i++)
        {
            User userI=users.get(i);
            
            //This means that the user is found in the list of users
            if(userI.getUserName().equals(user))
            {
                toRemove=userI;
            }
        }
        
        //If the user exist then we proceed to remove it
        if(toRemove!=null)
        {
            int toRemoveIndex=toRemove.getIndexPos();
            
            //First we proceed to decrease all of the follower numbers as well
            //as the following number of those that is associated with toRemove
            for(int i=0;i<users.size();i++)
            {
                //This checks for the people that the toRemove user is following
                //if it is true then we have to decrease the follower count
                //of that specific user
                if(connections[toRemoveIndex][i]&&i!=toRemoveIndex)
                {
                    User toDecrease=users.get(i);
                    
                    toDecrease.decreaseFollower();
                }
                
                //This checks for the people that follow toRemove user if it is
                //true then we have to decrease the following count of that specific
                //user
                if(connections[i][toRemoveIndex]&&i!=toRemoveIndex)
                {
                    User toDecrease=users.get(i);
                    
                    toDecrease.decreaseFollowing();
                }
            }
            
            //After removing it from the list of users we have to update the connection
            //matrix to reflect correctly to the deletion
            //The first for loop will shift everything after the row of toRemove
            //up by one
            for(int r=toRemoveIndex+1;r<users.size();r++)
            {
                for(int c=0;c<users.size();c++)
                {
                    connections[r-1][c]=connections[r][c];
                }
            }
            
            for(int c=toRemoveIndex+1;c<users.size();c++)
            {
                for(int r=0;r<users.size();r++)
                {
                    connections[r][c-1]=connections[r][c];
                }
            }
            
            //After the previous process then we proced to remove the toRemove
            //user from the list of users
            users.remove(toRemoveIndex);
            
            for(int i=0;i<users.size();i++)
            {
                User userI=users.get(i);
                
                userI.setIndexPos(i);
            }
            
            
            //Then we have to decrease the number of users to update the next
            //available position in the matrix
            User.decreaseUserCount();
        }
        
        System.out.println(user+" have been removed");
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
            
            //This means that the connection exist therefore we have to set the
            //connection to false and update both the userFrom and userTo's
            //follower and following variable accordingly
            if(connections[userFromIndex][userToIndex])
            {
                //Removing the connections from userFrom to userTo
                connections[userFromIndex][userToIndex]=false;
                
                //We have to decrease userFrom's following
                theUserFrom.decreaseFollowing();
                theUserTo.decreaseFollower();
            }
        }
    }
    
    public String shortestPath(String userFrom, String userTo)
    {
        ArrayList<String> paths=new ArrayList();
        
        User theUserFrom=null;
        User theUserTo=null;
        
        
        for(int i=0;i<users.size();i++)
        {
            User userI=users.get(i);
            
            if(userI.getUserName().equals(userFrom))
            {
                theUserFrom=userI;
            }
            else if(userI.getUserName().equals(userTo))
            {
                theUserTo=userI;
            }
        }
        
        int userFromIndex=theUserFrom.getIndexPos();
        int userToIndex=theUserTo.getIndexPos();
        
        String none=shortestPathHelper(0,0,userToIndex,paths);
        
        int smallest=0;
        
        for(int i=1;i<paths.size();i++)
        {
            if(paths.get(i).length()<paths.get(smallest).length())
            {
                smallest=i;
            }
        }
        
       
        return paths.get(smallest);
        
    }
    
    public String shortestPathHelper(int source, int dest, int goal, boolean visited[][], ArrayList<Integer> paths)
    {
        visited[source][dest]=true;
        
        
        
        
    }
    
    /*
    public String shortestPath(String userFrom, String userTo)
    {
        Double dist[][]=new Double[users.size()][users.size()];
        
        Integer next[][]=new Integer[users.size()][users.size()];
        
        //Users
        User theUserFrom=null;
        User theUserTo=null;
        
        for(int i=0;i<users.size();i++)
        {
            User userI=users.get(i);
            
            if(userI.getUserName().equals(userFrom))
            {
                theUserFrom=userI;
            }
            else if(userI.getUserName().equals(userTo))
            {
                theUserTo=userI;
            }
        }
        
        for(int r=0;r<dist.length;r++)
        {
            for(int c=0;c<dist[0].length;c++)
            {
                //Setting the minimum distances initially to infinity first
                dist[r][c]=Double.POSITIVE_INFINITY;
            }
        }
        
        //Then this for loop will be assinging the weight of the edge between two
        //users
        for(int r=0;r<connections.length;r++)
        {
            for(int c=0;c<connections[0].length;c++)
            {
                //This means that the two users do have a connection, which means
                //we have to assign their weight in dist
                if(connections[r][c])
                {
                    //Getting the source and destination user
                    User source=users.get(r);
                    User destination=users.get(c);
                    
                    int sourcePos=source.getIndexPos();
                    int destinationPos=destination.getIndexPos();
                    
                    //Then we assign its weight to be 1
                    dist[sourcePos][destinationPos]=new Double(1);
                    
                    next[sourcePos][destinationPos]=c;
                }
                
                
            }
        }
        
        for(int k=0;k<users.size();k++)
        {
            for(int i=0;i<users.size();i++)
            {
                for(int j=0;j<users.size();j++)
                {
                    if(dist[i][k]+dist[k][j]<dist[i][j])
                    {
                        dist[i][j]=dist[i][k]+dist[k][j];
                        
                        next[i][j]=next[i][k];
                    }
                }
            }
        }
        
        //Procedure path
        int userFromIndex=theUserFrom.getIndexPos();
        int userToIndex=theUserTo.getIndexPos();
        
        String output="";
        
        if(next[userFromIndex][userToIndex]==null)
        {
            return output;
        }
        
        ArrayList<Integer> path=new ArrayList();
        
        path.add(userFromIndex);
        
        for(int r=0;r<connections.length;r++)
        {
            for(int c=0;c<connections[0].length;c++)
            {
                //Consist of a vertex
                if(connections[r][c])
                {
                    path.add(c);
                }
                
                if(r==c)
                {
                    break;
                }
            }
        }
        
        for(Integer i:path)
        {
            User inPath=users.get(i);
            
            output+=inPath.getUserName()+", ";
        }
        
        return output;
        
    }
    */
    public List<String> allPaths(String userFrom, String userTo)
    {
        
    }
    
    public void printAllUsers(Comparator comp)
    {
        ArrayList<User> temp=new ArrayList();
        
        for(int i=0;i<users.size();i++)
        {
            temp.add(users.get(i));
        }
        
        //Sorting the list with the given comparator
        Collections.sort(temp, comp);
        
        //Then printing out the list of users 
        System.out.println("Users:");
        
        String format="%-30s%-35s%-35s";
        String userFormat="%-38s%-35s%-10s";
        System.out.println(String.format(format, "User Name","Number of Followers","Number Following"));

        for(int i=0;i<temp.size();i++)
        {
            User userI=temp.get(i);
            
            String userName=userI.getUserName();
            
            int numFollower=userI.getNumFollower();
            int numFollowing=userI.getNumFollowing();
            
            System.out.println(String.format(userFormat,userName,numFollower,numFollowing));
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
        try
        {
            FileInputStream fileInput=new FileInputStream(filename);
            
            InputStreamReader input=new InputStreamReader(fileInput);
            BufferedReader reader=new BufferedReader(input);
            
            String eachLine=reader.readLine();
            
            while(eachLine!=null)
            {
                addUser(eachLine);
                
                System.out.println(eachLine+" has been added");
                
                eachLine=reader.readLine();
            }
            
            System.out.println();
        }
        catch(FileNotFoundException f)
        {
            System.out.println(f);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
    
    public void loadAllConnections(String filename)
    {
        try
        {
            FileInputStream fileInput=new FileInputStream(filename);
            
            InputStreamReader input=new InputStreamReader(fileInput);
            BufferedReader reader=new BufferedReader(input);
            
            String eachLine=reader.readLine();
            
            while(eachLine!=null)
            {
                //Finding the comma's index
                int commaIndex=eachLine.indexOf(",");
                
                //To get the source user we substring from the beginning up to
                //comma's index but not including the comma
                String sourceUser=eachLine.substring(0,commaIndex);

                //Then to get the destination user we substring from the comma's
                //index plus two because there is a space and the rest
                String destinationUser=eachLine.substring(commaIndex+2);
                
                addConnection(sourceUser,destinationUser);

                System.out.println(eachLine);

                eachLine=reader.readLine();
            }
            
            System.out.println();
        }
        catch(FileNotFoundException f)
        {
            System.out.println(f);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
    
}