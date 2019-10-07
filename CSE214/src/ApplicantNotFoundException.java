/**
 * The ApplicantNotFoundException class is meant to be a custom Exception class
 * that reports the exception of the Applicant not being found when searching through
 * the HiringTable
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class ApplicantNotFoundException extends Exception
{
    /**
     * Constructor of the ApplicantNotFoundException, it takes the a input of a
     * msg that is printed when the exception is evoked
     * 
     * @param msg 
     *  The message to display to the user when the exception is called
     */
    public ApplicantNotFoundException(String msg)
    {
        super(msg);
        
        
    }
}