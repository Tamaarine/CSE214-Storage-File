package HW3;

/**
 * The Block class is suppose to represent a code block of a certain program and in
 * this case for the purpose of the homework, it will only consist of the int
 * variable that is declared inside each block. It will only keep track of the initial
 * value of the variable so we don't need to worry about statements that affects
 * the variable's value. It will hold a maximum of BLOCK_CAPACITY int variables
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class Block
{
    //This array will be holding all of the variables that is inside each block
    private Variable vars[];
    
    //This is the counter variable to keep track of the number of variables
    //inside the block
    private int size;
    
    //This int variable is used to point to the available spaces in the array
    //for the next Variable to be added in
    private int indexPointer;
    
    //This final variable is used to represent the maximum capacity that each
    //block can hold
    private final int BLOCK_CAPACITY=10;
    
    /**
     * The default constructor for the Block class, it creates a array list of
     * Variables that have the size of BLOCK_CAPACITY to hold the variables in. 
     * It also sets the size counter and the index pointer to the correct starting value
     */
    public Block()
    {
        vars=new Variable[BLOCK_CAPACITY];
        size=0;
        indexPointer=0;
    }
    
    /**
     * Return the size of the variable array that holds the variables
     * 
     * @return Returns the number of variables that this block is currentyl holding
     */
    public int getSize()
    {
        return size;
    }
    
    /**
     * This method finds the corresponding Variable that have the same name as the
     * given variable name to find
     * <p>
     * Postcondition: The Variable to find in the current block is either found
     * and have a Variable reference with it or it is not found which returns null
     * 
     * @param varName
     *  The variable name to look for in the array list
     * @return Returns the Variable that have the same name as varName, or it will
     *  return null if it is not found within the array
     */
    public Variable findVariable(String varName)
    {
        //This is the output for the method, it initially have a null Variable
        //to indicate that no Variable of the same name is found until proven
        Variable output=null;
        
        //This for loop go through the entire array to look for the Variable that
        //have the same varName
        for(int i=0;i<size;i++)
        {
            Variable variableAtI=vars[i];
            
            //This means that we have found a variable that have the corresponding
            //variable name therefore we get the Variable reference and return it
            if(variableAtI.getName().equals(varName))
            {
                output=variableAtI;
                
                return output;
            }
        }
        
        //If we are out here that means that there is no Variable with the
        //corresponding name therefore we just return null
        return output;
    }
    
    /**
     * 
     * @param givenVariable
     * @throws FullBlockException 
     */
    public void addElement(Variable givenVariable) throws FullBlockException
    { 
        //This means that the block is not full of variables yet and thus we can
        //add in a element into the block
        if(size!=BLOCK_CAPACITY)
        {
            vars[indexPointer]=givenVariable;
            indexPointer++;
            size++;
        }
        else
        {
            throw new FullBlockException("The block is full of variables already");
        }
    }
    
    /**
     * This method format a neatly organized String that displays the all of the
     * Variable's name and initial value
     * 
     * @return Returns a formatted String which displays all of the Variable's name
     * and initial value inside this particular block
     */
    public String formatVariable()
    {
        String output="";
        String formatString="%-25s%-25s";
        
        //This for loop iterate through the array to gather all of the Variable's
        //name and initial value into one single String
        for(int i=0;i<size;i++)
        {
            Variable variableAtI=vars[i];
            
            String varName=variableAtI.getName();
            int varValue=variableAtI.getInitialValue();
            
            output+=String.format(formatString,varName,varValue)+"\n";
        }
        
        return output;
    }
    
    public String toString()
    {
        String output="";
        
        for(int i=0;i<size;i++)
        {
            output+=vars[i].getName()+"="+vars[i].getInitialValue()+",";
        }
        
        return output;
    }
   
    
    
    
}