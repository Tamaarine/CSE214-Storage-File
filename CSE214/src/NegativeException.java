/**
 * The NegativeException class is meant to be a custom Exception class
 * that reports the exception of the user inputting a negative GPA
 * for the Applicant 
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class NegativeException extends Exception
{
     /**
     * Constructor of the NegativeException, it takes the a input of a
     * msg that is printed when the exception is evoked
     * 
     * @param msg 
     *  The message to display to the user when the exception is called
     */
    public NegativeException(String msg)
    {
        super(msg);
    }
}
