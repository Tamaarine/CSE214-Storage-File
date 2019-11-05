package HW5Extra;

import HW5.*;

/**
 * IsPlantException is a custom exception that is thrown when the OrganismNode is a
 * plant and the user is attempting to add a prey to the plant OrganismNode, but
 * because it is a plant it cannot have any prey
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class IsPlantException extends Exception
{
    public IsPlantException(String msg)
    {
        super(msg);
    }
}