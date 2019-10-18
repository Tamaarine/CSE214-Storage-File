package HW4;

/**
 * The Bigram class represent a bigram which just contain two characters. It is used
 * to represent the pair of letter that is used for both encryption and decryption
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class Bigram
{
    //The first char variable represent the first letter of the Bigram letter pair
    private char first;
    
    //The second char variable represent the second letter of the Bigram letter pair
    private char second;
    
    /**
     * Default constructor of the Bigram object it creates a Bigram that have
     * the default value of both first and second set to 0 and can be modified 
     * later by using the mutator method
     */
    public Bigram()
    {
        //Setting the default value
        first=0;
        second=0;
    }
    
    /**
     * Return the first character of the Bigram pair
     * 
     * @return Return the first variable that represent the first letter of the Bigram
     *  pair
     */
    public char getFirst()
    {
        return first;
    }
    
    /**
     * Return the second character of the Bigram pair
     * 
     * @return Return the second variable that represent the second letter of the Bigram
     *  pair
     */
    public char getSecond()
    {
        return second;
    }
    
    /**
     * The mutator method for Bigram's first letter
     * <p>
     * Postcondition: The Bigram's first letter is set to givenFirst
     * 
     * @param givenFirst 
     *  The given letter to set the Bigram's first letter into
     */
    public void setFirst(char givenFirst)
    {
        first=givenFirst;
    }
    
    /**
     * The mutator method for Bigram's second letter
     * <p>
     * Postcondition: The Bigram's second letter is set to givenSecond
     * 
     * @param givenSecond 
     *  The given letter to set the Bigram's second letter into
     */
    public void setSecond(char givenSecond)
    {
        second=givenSecond;
    }
    
    /**
     * The toString method just return a nice String representation of the Bigram
     * for us to see
     * 
     * @return Return a String that represent the Bigram
     */
    public String toString()
    {
        String output="";
        
        output=first+""+second;
        
        return output;
    }
    
}