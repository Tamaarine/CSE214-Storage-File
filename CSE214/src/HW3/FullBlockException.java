package HW3;

/**
 * The FullBlockException class is meant to be a custom exception that is thrown
 * when a variable tries to add into the Block that already have the maximum
 * number of variables it can possibly hold
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class FullBlockException extends Exception
{
    public FullBlockException(String msg)
    {
        super(msg+"\n");
    }
    
}