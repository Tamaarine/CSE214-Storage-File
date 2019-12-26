public class TestClass
{
    private String s;
    private int v;
    
    public TestClass(String str,int value)
    {
        s=str;
        v=value;
    }

    public static void main(String [] args)
    {
        int keys[]={1,1,1,1,1,0};
        print(keys);
        
        sort(keys);
        
        System.out.println("---------------");
        
        int a=5;
        
        int result=a++ +a+ ++a;
        
        System.out.println(result);
        
    }
    public static void sort(int keys[])
    {
        int zeroCounter=0;
        int oneCounter=0;
        
        for(int i=0;i<keys.length;i++)
        {
            if(keys[i]==0)
            {
                zeroCounter++;
            }
        }
        
        oneCounter=keys.length-zeroCounter;
        
        for(int i=0;i<keys.length;i++)
        {
            if(zeroCounter>0)
            {
                keys[i]=0;
                zeroCounter--;
            }
            else
            {
                keys[i]=1;
            }
        }
        
        print(keys);
    }
    
    public static void print(int keys[])
    {
        for(int i=0;i<keys.length;i++)
        {
            System.out.print(keys[i]+",");
        }
        
        System.out.println("");
    }
}