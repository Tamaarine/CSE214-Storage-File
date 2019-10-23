package HW5;

public class OrganismNode
{
    //Instance variables
    private String name;
    private boolean isPlant;
    private boolean isHerbivore;
    private boolean isCarnivore;
    private OrganismNode left;
    private OrganismNode middle;
    private OrganismNode right;
    
    //Default constructor
    public OrganismNode()
    {
        name="";
        isPlant=false;
        isHerbivore=false;
        isCarnivore=false;
        left=null;
        middle=null;
        right=null;
    }
    
    public OrganismNode(String givenName, boolean isPlant, boolean isHerbivore, boolean isCarnivore)
    {
        name=givenName;
        this.isPlant=isPlant;
        this.isHerbivore=isHerbivore;
        this.isCarnivore=isCarnivore;
        left=null;
        middle=null;
        right=null;
    }
    
    //Accesor method
    public String getName()
    {
        return name;
    }
    
    public boolean getIsPlant()
    {
        return isPlant;
    }
    
    public boolean getIsHerbivore()
    {
        return isHerbivore;
    }
    
    public boolean getIsCarnivore()
    {
        return isCarnivore;
    }
    
    public OrganismNode getLeft()
    {
        return left;
    }
    
    public OrganismNode getMiddle()
    {
        return middle;
    }
    
    public OrganismNode getRight()
    {
        return right;
    }
    
    public void addPrey(OrganismNode preyNode) throws PositionNotAvailableException, IsPlantException, DietMismatchException
    {
        //Getting the charactertics of the prey that we are adding
        boolean preyIsPlant=preyNode.getIsPlant();
        
        //This means that the left middle and right, all three children of the 
        //species node is all full therefore we must throw the PositionNotAvailableException
        //to indicate that we can't add anymore prey node to the species
        if(left!=null&&middle!=null&&right!=null)
        {
            throw new PositionNotAvailableException("Species node are all full");
        }
        
        //This means that this species node is a plant therefore it cannot have any
        //prey because a plant is not a predator
        if(isPlant)
        {
            throw new IsPlantException("This is a plant therefore it cannot have any"
                    + " child");
        }
        
        if(preyIsPlant&&isCarnivore)
        {
            throw new DietMismatchException("This species is not a herbivore");
        }
        else if(!preyIsPlant&&isHerbivore)
        {
            throw new DietMismatchException("This species is not a carnivore");
        }
        
        //This means that the left node is empty therefore we can put the new prey
        //node there
        if(left==null)
        {
            left=preyNode;
        }
        //This means that the middle node is empty therefore we can put the new
        //prey node at the middle node
        else if(middle==null)
        {
            middle=preyNode;
        }
        //This means that the right definitely is not null therefore we can put the
        //new prey node at the right node
        else
        {
            right=preyNode;
        }
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
}