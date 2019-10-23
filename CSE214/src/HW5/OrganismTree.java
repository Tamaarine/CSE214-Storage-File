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
        
    }
    
    public String listPrey() throws IsPlantException
    {
        
    }
    
    public String listFoodChain()
    {
        
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
    
    
    
    
    
    
    
    
    
    
}




