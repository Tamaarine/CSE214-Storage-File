public class IntNode
{
    private int data;
    private IntNode link;
    
    public IntNode(int num)
    {
        data=num;
        link=null;
    }
    
    public IntNode getLink()
    {
        return link;
    }
    
    public int getData()
    {
        return data;
    }
    
    public void setLink(IntNode newNode)
    {
        link=newNode;
    }
    
    public static void main(String [] args)
    {
        String workingTemp="  s   ";
        
        int whiteSpaceCounter=0;

        for(int i=0;i<workingTemp.length();i++)
        {
            if(workingTemp.charAt(i)==' ')
            {
                whiteSpaceCounter++;
            }
        }

        if(whiteSpaceCounter==workingTemp.length())
        {
            System.out.println("true");
        }
        
    }
}