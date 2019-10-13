package HW4;

import java.util.ArrayList;
import java.util.Date;

public class Phrase extends ArrayList implements Queue
{
    public static void main(String [] args)
    {
        ArrayList arr=new ArrayList();
        
        arr.add(new Object());
        arr.add(new Integer(5));
        arr.add(new Date());
        
        System.out.println(arr);
        
        ArrayList<Integer> arr2=new ArrayList<>();
        arr2.add(new Integer(3));
        
        System.out.println(arr2);
        
    }
    @Override
    public void enqueue(Bigram b)
    {
        
    }

    @Override
    public Bigram dequeue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bigram peek() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}