package HW3;

/**
 * 
 * 
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class Variable
{
    //Instance variables
    private String name;
    private int initialValue;
    
    /**
     * Default constructor of the Variable class, it creates a Variable that have
     * no name and a default initialValue of 0 which can be later changed by
     * the mutator methods
     */
    public Variable()
    {
        name="";
        initialValue=0;
    }
    
    /**
     * 
     * @param givenName
     * @param givenValue 
     */
    public Variable(String givenName, int givenValue)
    {
        name=givenName;
        initialValue=givenValue;
    }
    
    /**
     * 
     * @return 
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * 
     * @return 
     */
    public int getInitialValue()
    {
        return initialValue;
    }
    
    /**
     * 
     * @param givenName 
     */
    public void setName(String givenName)
    {
        name=givenName;
    }
    
    /**
     * 
     * @param givenValue 
     */
    public void setInitialValue(int givenValue)
    {
        initialValue=givenValue;
    }
    
    
    
}