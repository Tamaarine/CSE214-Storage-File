package HW5Extra;

import HW5.*;

/**
 * OrganismNode class represent a single node in the OrganismTree. The node will represent
 * a single species that is either a plant or an animal. The OrganismNode will have
 * three OrganismNode references named left, middle, and right that will be referring
 * to the prey of this organism species.
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class OrganismNode
{
    //This String variable represent the name of the organism species
    private String name;
    
    //isPlant boolean variable will differentiate whether this OrganismNode is an
    //animal or a plant
    private boolean isPlant;
    
    //isHerbivore boolean variable will tell the diet of the animal, in this case
    //isHerbivore meaning that the animal can only eat plants
    private boolean isHerbivore;
    
    //isCarnivore boolean variable will tell the diet of the animal, in this case
    //isCarnivore meaning that the animal can only eat meats
    private boolean isCarnivore;
    
    //This OrganismNode left represent the left prey of this species
    private OrganismNode left;
    
    //This OrganismNode middle represent the middle prey of this species
    private OrganismNode middle;
    
    //This OrganismNode right represent the right prey of this species
    private OrganismNode right;
    
    //This int variable will be represneting the how deep it is into the tree
    //with the root of the tree representing the depth of 0
    private int depth;

    /**
     * Default constructor of the OrganismNode of the OrganismNode, it create a
     * empty OrganismNode that have all of the characteristics of the species as
     * default values and then be changed later on through the mutator methods
     */
    public OrganismNode()
    {
        //Setting the instance variables to the default value
        name="";
        isPlant=false;
        isHerbivore=false;
        isCarnivore=false;
        left=null;
        middle=null;
        right=null;
        depth=0;
    }
    
    /**
     * Return the name of this OrganismNode
     * 
     * @return Returns the name of this OrganismNode species
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Return the boolean value of isPlant that determines whether or not this
     * OrganismNode is a plant or not
     * 
     * @return Returns the boolean value of isPlant which tells whether this
     *  OrganismNode is a plant or an animal
     */
    public boolean getIsPlant()
    {
        return isPlant;
    }
    
    /**
     * Return the boolean value of isHerbivore that determines the diet of the
     * OrganismNode being restricted to only plants
     * 
     * @return Returns the boolean value of isHerbivore which tells whether or not
     *  this OrganismNode eat plants or meats
     */
    public boolean getIsHerbivore()
    {
        return isHerbivore;
    }
    
    /**
     * Return the boolean value of isCarnivore that determines the diet of the
     * OrganismNode being restricted to only meats
     * 
     * @return Returns the boolean value of isCarnivore which tells whether or not
     *  this OrganismNode eat plants or meats
     */
    public boolean getIsCarnivore()
    {
        return isCarnivore;
    }
    
    /**
     * Return the OrganismNode that represent the left prey of the OrganismNode
     * 
     * @return Returns the OrganismNode that is the left prey of this species
     */
    public OrganismNode getLeft()
    {
        return left;
    }
    
    /**
     * Return the OrganismNode that represent the middle prey of the OrganismNode
     * 
     * @return Returns the OrganismNode that is the middle prey of this species
     */
    public OrganismNode getMiddle()
    {
        return middle;
    }
    
    /**
     * Return the OrgsanismNode that represent the right prey of the OrganismNode
     * 
     * @return Returns the OrganismNode that is the right prey of this species
     */
    public OrganismNode getRight()
    {
        return right;
    }
    
    /**
     * Return the depth of this OrganismNode that represent how deep it is 
     * respect to the OrganismTree
     * 
     * @return Returns the value of depth which represent the depth in the OrganismTree
     */
    public int getDepth()
    {
        return depth;
    }
    
    /**
     * The mutator method for the OrganismNode's name
     * <p>
     * Postcondition: The OrganismNode's name is set to givenName
     * 
     * @param givenName 
     *  The given name to set the OrganismNode's name into
     */
    public void setName(String givenName)
    {
        name=givenName;
    }
    
    /**
     * The mutator method for the Organism's isPlant variable
     * <p>
     * Postcondition: The OrganismNode's isPlant variable is set to the method's
     * isPlant
     * 
     * @param isPlant
     *  The method's isPlant is what is set to the OrganismNode's isPlant into
     */
    public void setIsPlant(boolean isPlant)
    {
        this.isPlant=isPlant;
    }
    
    /**
     * The mutator method for the OrganismNode's isCarnivore variable
     * <p>
     * Postcondition: The OrganismNode's isCarnivore variable is set to the method's
     * isCarnivore
     * 
     * @param isCarnivore 
     *  The method's isCarnivore is what is set to the OrganismNode's isCarnivore into
     */
    public void setIsCarnivore(boolean isCarnivore)
    {
        this.isCarnivore=isCarnivore;
    }
    
    /**
     * The mutator method for the OrganismNode's isHerbivore variable
     * <p>
     * Postcondition: The OrganismNode's isHerbivore variable is set to the method's
     * isHerbivore
     * 
     * @param isHerbivore 
     *  The method's isHerbivore is what is set to the OrganismNode's isHerbivore into
     */
    public void setIsHerbivore(boolean isHerbivore)
    {
        this.isHerbivore=isHerbivore;
    }
    
    /**
     * The mutator method for the OrganismNode's left prey
     * <p>
     * Postcondition: The OrganismNode's left OrganismNode is set to givenNode
     * 
     * @param givenNode 
     *  The given node to set the left OrganismNode into
     */
    public void setLeft(OrganismNode givenNode)
    {
        left=givenNode;
    }
    
    /**
     * The mutator method for the OrganismNode's middle prey
     * <p>
     * Postcondition: The OrganismNode's middle OrganismNode is set to the givenNode
     * 
     * @param givenNode 
     *  The given node to set the middle OrganismNode into
     */
    public void setMiddle(OrganismNode givenNode)
    {
        middle=givenNode;
    }
    
    /**
     * The mutator method for the OrganismNode's right prey
     * <p>
     * Postcondition: The OrganismNode's right OrganismNode is set to the givenNode
     * 
     * @param givenNode 
     *  The given node to set the right OrganismNode into
     */
    public void setRight(OrganismNode givenNode)
    {
        right=givenNode;
    }
    
    /**
     * The mutator method for the OrganismNode's depth level
     * <p>
     * Postcondition: The OrganismNode's depth value is set to givenDepth
     * 
     * @param givenDepth
     *  The given depth value to set the OrganismNode's depth into
     */
    public void setDepth(int givenDepth)
    {
        depth=givenDepth;
    }
    
    /**
     * This method add preyNode as a prey to this OrganismNode with the order of
     * filling up left, then middle, then finally right
     * <p>
     * Precondition: This node is not a plant, at least one of the three child
     * node positions of this node is available, and the type of prey correctly
     * match with the species node of this node
     * <p>
     * Postcondition: Either an exception is thrown, or the preyNode is successfully
     * added as a child of this node in left, middle, or right
     * 
     * @param preyNode
     *  The OrganismNode to be added as prey of this OrganismNode
     * @throws PositionNotAvailableException
     *  PositionNotAvailableException is thrown if there is no available child position
     *  for preyNode to be added into this node
     * @throws IsPlantException
     *  IsPlantException is thrown if this node is a plant since plant can't have any
     *  prey
     * @throws DietMismatchException 
     *  DietMisMatchException is thrown if preyNode does not match to correct diet
     *  of this animal
     */
    public void addPrey(OrganismNode preyNode) throws PositionNotAvailableException, IsPlantException, DietMismatchException
    {
        //Getting the charactertics of the prey that we are adding
        boolean preyIsPlant=preyNode.getIsPlant();
        
        //Getting the name of the organism
        String name=preyNode.getName();
        
        //This means that the left middle and right, all three children of the 
        //species node is all full therefore we must throw the PositionNotAvailableException
        //to indicate that we can't add anymore prey node to the species
        if(left!=null&&middle!=null&&right!=null)
        {
            //Throwing the error message to the user
            throw new PositionNotAvailableException("Species node are all full\n");
        }
        
        //This means that this species node is a plant therefore it cannot have any
        //prey because a plant is not a predator
        if(isPlant)
        {
            throw new IsPlantException("This is a plant therefore it cannot have any"
                    + " child");
        }
        
        //This if-statement check if whether or not this specific Organism is being
        //mismatched to the prey due to dietary preferrences
        if(!isCarnivore||!isHerbivore)
        {
            //This means that the preyNode is a plant while this species is a carnivore
            //thus it cannot eat a plant and error is thrown
            if(preyIsPlant&&isCarnivore)
            {
                throw new DietMismatchException("This species is not a herbivore\n");
            }
            //This means that the preyNode is not a plant while this species is a
            //herbivore thus it cannot eat a meat and error is thrown
            else if(!preyIsPlant&&isHerbivore)
            {
                throw new DietMismatchException("This species is not a carnivore\n");
            }
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
        
        //This show the correct message to be shown after successfully adding
        //prey node to the species
        if(preyIsPlant)
        {
            System.out.println(name+" has successfully been added as prey for the "+this.name+"!\n");
        }
        else
        {
            System.out.println("A(n) "+name+" has successfully been added as prey for the "+this.name+"!\n");
        }
    }
}