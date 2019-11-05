package HW5Extra;

import HW5.*;

/**
 * PositionNotAvailableException is a custom exception that is thrown when the
 * OrganismNode is being added a prey OrganismNode when it have the maximum of
 * three preys already to signal the user that the OrganismNode is full.
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class PositionNotAvailableException extends Exception
{
    public PositionNotAvailableException(String msg)
    {
        super(msg);
    }
}