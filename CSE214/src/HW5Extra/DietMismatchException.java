package HW5Extra;

import HW5.*;

/**
 * The DietMismatchException is a custom exception that is thrown when the OrganismNode
 * is being added a prey OrganismNode that does not match with its dietary preferences.
 * Meaning a herbivore is being fed with meats or carnivore being fed with vegetables.
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class DietMismatchException extends Exception
{
    public DietMismatchException(String msg)
    {
        super(msg);
    }
}