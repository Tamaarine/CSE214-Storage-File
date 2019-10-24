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
        if(name.equals(cursor.getName()))
        {
            return;
        }
        
        if(cursor.getLeft()!=null)
        {
            cursor=cursor.getLeft();
            moveCursor(name);
        }
        if(!cursor.getName().equals(name)&&cursor.getMiddle()!=null)
        {
            cursor=cursor.getMiddle();
            moveCursor(name);
        }
        if(!cursor.getName().equals(name)&&cursor.getRight()!=null)
        {
            cursor=cursor.getRight();
            moveCursor(name);
        }
        
        
        
        
    }
    
    public String listPrey() throws IsPlantException
    {
        
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




