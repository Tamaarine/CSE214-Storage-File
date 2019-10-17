package HW4;

/**
 * 
 * 
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public interface Queue
{
    public void enqueue(Bigram b);
    public Bigram dequeue();
    public Bigram peek();
    public int size();
    public boolean isEmpty();
}