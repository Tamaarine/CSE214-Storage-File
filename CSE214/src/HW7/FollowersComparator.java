package HW7;

import java.util.Comparator;

public class FollowersComparator implements Comparator<User>
{
    @Override
    public int compare(User user1, User user2)
    {
        //Getting the number of followers of both user
        int user1Followers=user1.getNumFollower();
        int user2Followers=user2.getNumFollower();
        
        //This means that they have the same amount of follower thus return 0
        if(user1Followers==user2Followers)
        {
            return 0;
        }
        //This means that user1 have a greater number of followers thus we return 1
        else if(user1Followers>user2Followers)
        {
            return -1;
        }
        //Else if they are equal or less than return -1
        else
        {
            return 1;
        }
    }
}