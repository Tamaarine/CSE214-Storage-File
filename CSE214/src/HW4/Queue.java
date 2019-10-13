package HW4;

import java.util.ArrayList;

public interface Queue
{
    public void enqueue(Bigram b);
    public Bigram dequeue();
    public Bigram peek();
    public int size();
    public boolean isEmpty();
}