package HW7;

import java.util.Comparator;

public class FollowingComparator implements Comparator<User>
{
    @Override
    public int compare(User user1, User user2)
    {
        //Getting the amount of following from both users
        int user1Following=user1.getNumFollowing();
        int user2Following=user2.getNumFollowing();
        
        //If they have the same amount of following then return 0
        if(user1Following==user2Following)
        {
            return 0;
        }
        //This mean that user1 have a greater number of following thus return 1
        else if(user1Following>user2Following)
        {
            return -1;
        }
        //This means that user2 have a greater number of following thus return 2
        else
        {
            return 1;
        }
        
    }
    
}