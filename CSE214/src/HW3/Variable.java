package HW3;

public class Variable
{
    //Instance variables
    private String name;
    private int initialValue;
    
    public Variable()
    {
        name="";
        initialValue=0;
    }
    
    public Variable(String givenName, int givenValue)
    {
        name=givenName;
        initialValue=givenValue;
    }
    
    //Accessor methods
    public String getName()
    {
        return name;
    }
    
    public int getInitialValue()
    {
        return initialValue;
    }
    
    //Mutator methods
    public void setName(String givenName)
    {
        name=givenName;
    }
    
    public void setInitialValue(int givenValue)
    {
        initialValue=givenValue;
    }
    
    
    
}