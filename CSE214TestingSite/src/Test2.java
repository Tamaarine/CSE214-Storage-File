
import HW6.FrequencyTable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test2
{
    public static void main(String [] args)
    {
        ArrayList<TestClass> s=new ArrayList();
        
        s.add(new TestClass("Hello World",12));
        s.add(new TestClass("Bitch pls",12));
        
        System.out.println(s.contains(new TestClass("Bitch pls",12)));
        
        System.out.println(5.4*5.3);
        
    }
}
