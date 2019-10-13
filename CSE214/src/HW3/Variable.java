package HW3;

/**
 * The Variable class is suppose to model an Variable that is declared within a set
 * of brackets inside a Block. This Variable is suppose to only model after a
 * int Variable which means it only have a variable name and the initial value
 * that it was assigned to it.
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class Variable
{
    //This is used to represent the name of a single Variable in a String
    private String name;
    
    //This is used to represent the initial int value that the Variable is
    //assigned to
    private int initialValue;
    
    /**
     * Default constructor of the Variable class, it creates a Variable that have
     * no name and a default initialValue of 0 which can be later changed by
     * the mutator methods
     */
    public Variable()
    {
        //Setting the instance variables to default value
        name="";
        initialValue=0;
    }
    
    /**
     * The parameterized constructor for the Variable class in which the Variable is
     * made with given information about the name and its initial value
     * 
     * @param givenName
     *  The name of the Variable
     * @param givenValue 
     *  The initial value of the Variable
     */
    public Variable(String givenName, int givenValue)
    {
        name=givenName;
        initialValue=givenValue;
    }
    
    /**
     * Return the name of the Variable 
     * 
     * @return Returns the variable's name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Return the initial value of the Variable it holds
     * 
     * @return Returns the variable's initial value that it contains
     */
    public int getInitialValue()
    {
        return initialValue;
    }
    
    /**
     * The mutator method for the Variable's name
     * <p>
     * Postcondition: The Variable's name is set to givenName 
     * 
     * @param givenName 
     *  The given name that you want to set the Variable's name into
     */
    public void setName(String givenName)
    {
        name=givenName;
    }
    
    /**
     * The mutator method for the Variable's initial value
     * <p>
     * Postcondition: The Variable's initial value is set to givenValue
     * 
     * @param givenValue 
     */
    public void setInitialValue(int givenValue)
    {
        initialValue=givenValue;
    }
}