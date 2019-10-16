package HW4;

public class Bigram
{
    private char first;
    private char second;
    
    public Bigram()
    {
        first=0;
        second=0;
        
    }
    
    //Accessor method
    public char getFirst()
    {
        return first;
    }
    
    public char getSecond()
    {
        return second;
    }
    
    //Mutator method
    public void setFirst(char givenFirst)
    {
        first=givenFirst;
    }
    
    public void setSecond(char givenSecond)
    {
        second=givenSecond;
    }
    
    public String toString()
    {
        String output="";
        
        output=first+""+second;
        
        return output;
    }
    
}