public class IntLinkedList
{
    private IntNode head;
    private IntNode tail;
    
    public IntLinkedList()
    {
        head=null;
        tail=null;
        
        
    }
    
    public void insertAtHead(IntNode newNode)
    {
        if(head==null)
        {
            head=newNode;
            tail=newNode;
        }
        else
        {
            newNode.setLink(head);
            head=newNode;
        }
    }
    
    public boolean remove(int item)
    {
        boolean output=false;
        IntNode nodePtr=head;
        IntNode trailing=null;
        
        //This means that there is no element in the list
        if(nodePtr==null)
        {
            return output;
        }
        
        //That means there is element in there thus we need to traverse through
        while(nodePtr!=null)
        {
            //If nodePtr is on the item now, break away and remove it
            if(nodePtr.getData()==item)
            {
                break;
            }
            else
            {
                trailing=nodePtr;
                nodePtr=nodePtr.getLink();
            }
        }
        
        if(nodePtr!=null)
        {
            if(nodePtr==head)
            {
                head=head.getLink();
            }
            else if(nodePtr==tail)
            {
                trailing.setLink(null);
            }
            else
            {
                trailing.setLink(nodePtr.getLink());
            }
            
            output=true;
        }
        else
        {
            return false;
        }
        
        return output;
    }
    
    public void printList()
    {
        IntNode nodePtr=head;
        
        while(nodePtr!=null)
        {
            System.out.print(nodePtr.getData()+",");
            
            nodePtr=nodePtr.getLink();
        }
        
        System.out.println("");
    }
    
    public static void main(String []args)
    {
        IntLinkedList list=new IntLinkedList();
        
        list.insertAtHead(new IntNode(3));
        list.insertAtHead(new IntNode(1));
        list.insertAtHead(new IntNode(1));
        list.insertAtHead(new IntNode(9));
        
        list.printList();
        
        list.remove(9);
        
        list.printList();
        
        list.remove(1);
        
        list.printList();
        
        list.remove(9);
        
        list.printList();
        
        list.remove(3);
        
        list.printList();
        
        
        
    }
}