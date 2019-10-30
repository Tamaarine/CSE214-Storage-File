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
    
    public void addAnimalChild(String name, boolean isHerbivore, boolean isCarnivore) throws IllegalArgumentException, PositionNotAvailableException
    {
        String preys="";
        try
        {
            preys=listPrey();
            
            //This means that the new prey is already a prey of the spcies
            //therefore we have to throw a new error to tell the user
            if(preys.indexOf(name)!=-1)
            {
                throw new IllegalArgumentException(name+" is already an prey of "+cursor.getName());
            }
            
            OrganismNode toBeAdded=new OrganismNode();
            
            toBeAdded.setIsCarnivore(isCarnivore);
            toBeAdded.setIsHerbivore(isHerbivore);
            toBeAdded.setIsPlant(false);
            
            try
            {
                cursor.addPrey(toBeAdded);
            }
            catch(DietMismatchException d)
            {
                System.out.println(d);
            }
        }
        catch(IsPlantException i)
        {
            System.out.println(i);
        }
        
    }
    
    public void addPlantChild(String name) throws IllegalArgumentException, PositionNotAvailableException
    {
        //preys will be containing the listPrey String of the cursor, this will
        //allow us to check if there are any duplicates of the same prey on
        //the cursor before adding it
        String preys="";
        try
        {
            preys=listPrey();
            
            //This means that the prey that is going to be added is a duplicate
            //prey therefore we must throw the error to let the user know
            if(preys.indexOf(name)!=-1)
            {
                throw new IllegalArgumentException(name+" is already an prey of "+cursor.getName());
            }

            //This is the new plant that is going to be added to the cursor as a child
            OrganismNode toBeAdded=new OrganismNode();

            //Setting the name of the new plant to the given name
            toBeAdded.setName(name);
            toBeAdded.setIsPlant(true);

            try
            {
                cursor.addPrey(toBeAdded);

                System.out.println(name+" has successfully been added as prey for the "+cursor.getName());
            }
            catch(IsPlantException i)
            {
                System.out.println(i);
            }
            catch(DietMismatchException d)
            {
                System.out.println(d);
            }
        }
        catch(IsPlantException i)
        {
            System.out.println(i);
        }
        


        
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




