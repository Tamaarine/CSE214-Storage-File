package HW2Edited;

/**
 * The InvalidLengthException class is meant to be a custom Exception class
 * that reports the exception of the user inputting an invalid length for the
 * Song
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class InvalidLengthException extends Exception
{
    public InvalidLengthException(String msg)
    {
        super(msg);
    }
}