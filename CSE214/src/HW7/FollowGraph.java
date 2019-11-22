package HW7;

import java.io.Serializable;
import java.util.List;

public class FollowGraph implements Serializable
{
    //Instaence variables
    private List<User> users;
    private static int MAX_USERS;
    private boolean connections[][];
    
    public FollowGraph()
    {
        
    }
}