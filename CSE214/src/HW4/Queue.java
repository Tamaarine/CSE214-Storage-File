package HW4;

/**
 * The Queue interface represent a set of instruction/methods that a Queue
 * data structure can perform such as enqueue, dequeue, peek, size, and isEmpty.
 * It is implemented by the Phrase class to make Phrase into a Queue data structure
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