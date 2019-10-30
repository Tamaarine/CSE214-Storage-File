public class Method1
{
    private Method2 m2;
    
    public Method1()
    {
        m2=new Method2();
    }
    
    public static void main(String [] args)
    {
        Method1 m1=new Method1();
        
        try
        {
            m1.changeM2(2);
            System.out.println("done");
        }
        catch(BadNumberException b)
        {
            System.out.println(b);
        }
        
    }
    
    public void changeM2(int num2) throws BadNumberException
    {
        try
        {
            m2.switchNumber(num2);
        }
        catch(RandomException r)
        {
            System.out.println(r);
        }
        
        
    }
    
    
}