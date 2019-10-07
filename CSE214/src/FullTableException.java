/**
 * The FullTableException class is meant to be a custom Exception class
 * that reports the exception of the Applicant not being able to be added
 * into the HiringTable because the table have reached maximum capacity
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class FullTableException extends Exception
{
     /**
     * Constructor of the FullTableException, it takes the a input of a
     * msg that is printed when the exception is evoked
     * 
     * @param msg 
     *  The message to display to the user when the exception is called
     */
    public FullTableException(String msg)
    {
        super(msg);
    }
    
    
}