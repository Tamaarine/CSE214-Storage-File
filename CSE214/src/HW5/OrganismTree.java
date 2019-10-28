package HW5;

public class OrganismTree
{
    //Instance variables
    private OrganismNode root;
    private OrganismNode cursor;
    
    //Default constructor
    public OrganismTree(OrganismNode apexPredator)
    {
        root=apexPredator;
        cursor=apexPredator;
    }
    
    //This rests the cursor to the apex predator or the root of the organism tree
    public void cursorRest()
    {
        cursor=root;
    }
    
    public void moveCursor(String name) throws IllegalArgumentException
    {
        cursorRest();
        
        findTarget(name,cursor);
    }
    
    private boolean findTarget(String name, OrganismNode dummyNode)
    {
        OrganismNode node=dummyNode;
        
        boolean output=false;
        
        //This is found return true no need to keep on going, cursor is on
        //the target species
        if(name.equals(node.getName()))
        {
            output=true;
            
            cursor=node;
            
            return output;
        }
        
        if(node.getLeft()!=null)
        {
            output=findTarget(name, node.getLeft());
        }
        
        if(output==false&&node.getMiddle()!=null)
        {
            
            output=findTarget(name, node.getMiddle());
        }
        
        if(output==false&&node.getRight()!=null)
        {
            
            output=findTarget(name, node.getRight());
        }
        
        return output;
        
            
    }
    
    public String listPrey() throws IsPlantException
    {
        String output=cursor.getName()+"->";
        
        OrganismNode left=cursor.getLeft();
        OrganismNode middle=cursor.getMiddle();
        OrganismNode right=cursor.getRight();
        
        if(cursor.getIsPlant())
        {
            throw new IsPlantException("The species is a plant, it doesn't have eat prey");
        }
        
        if(left!=null)
        {
            output+=left.getName()+",";
        }
        
        if(middle!=null)
        {
            output+=middle.getName()+",";
        }
        
        if(right!=null)
        {
            output+=right.getName();
        }
        
        return output;
        
    }
    
    public String listFoodChain()
    {
        
        cursorRest();
        
        System.out.println(cursor.getName());
        
        if(cursor.getLeft()!=null)
        {
            cursor=cursor.getLeft();
            listFoodChain();
        }
        if(cursor.getMiddle()!=null)
        {
            cursor=cursor.getMiddle();
            listFoodChain();
        }
        if(cursor.getRight()!=null)
        {
            cursor=cursor.getRight();
            listFoodChain();
        }
        
    }
    
    public void printOrganismTree()
    {
        
    }
    
    public String listAllPlants()
    {
        
    }
    
    public String addAnimalChild(String name, boolean isHerbivore, boolean isCarnivore) throws IllegalArgumentException, PositionNotAvailableException
    {
        
    }
    
    public void addPlantChild(String name) throws IllegalArgumentException, PositionNotAvailableException
    {
        
    }
    
    public void removeChild(String name) throws IllegalArgumentException
    {
        
    }
    
    public OrganismNode getCursor()
    {
        return cursor;
    }
    
    public OrganismNode getRoot()
    {
        return root;
    }
    
    
    
    
    
    
    
    
    
}




