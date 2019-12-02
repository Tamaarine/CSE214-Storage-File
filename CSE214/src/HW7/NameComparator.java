package HW7;

import java.util.Comparator;

public class NameComparator implements Comparator<User>
{
    public NameComparator()
    {
        
    }

    @Override
    public int compare(User user1, User user2)
    {
        //Calling the compareTo method of the String to compare those two users
        return user1.getUserName().compareTo(user2.getUserName());
    }
}