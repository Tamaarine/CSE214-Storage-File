public class Method2
{
    //Instance variables
    private int num1;
    
    public Method2()
    {
        num1=0;
    }
    
    public void switchNumber(int num2) throws BadNumberException, RandomException
    {
        if(num2==2)
        {
            throw new BadNumberException("2 is a bad number");
        }
        else if(num2==3)
        {
            throw new RandomException("random");
        }
        else
        {
            num1=num2;
        }
    }
}