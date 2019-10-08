package HW3;

/**
 *
 * 
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class Block
{
    private Variable vars[];
    private int size;
    private int indexPointer;
    private final int BLOCK_CAPACITY=10;
    
    /**
     * 
     */
    public Block()
    {
        vars=new Variable[BLOCK_CAPACITY];
        size=0;
        indexPointer=0;
    }
    
    /**
     * 
     * @return 
     */
    public int getSize()
    {
        return size;
    }
    
    /**
     * 
     * @param varName
     * @return 
     */
    public Variable findVariable(String varName)
    {
        Variable output=null;
        
        for(int i=0;i<size;i++)
        {
            Variable variableAtI=vars[i];
            
            if(variableAtI.getName().equals(varName))
            {
                output=variableAtI;
                
                return output;
            }
        }
        
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
     * 
     * @return 
     */
    public String formatVariable()
    {
        String output="";
        String formatString="%-25s%-25s";
        
        for(int i=0;i<size;i++)
        {
            Variable variableAtI=vars[i];
            
            String varName=variableAtI.getName();
            int varValue=variableAtI.getInitialValue();
            
            output+=String.format(formatString,varName,varValue)+"\n";
        }
        
        return output;
    }
    
   
    
    
    
}