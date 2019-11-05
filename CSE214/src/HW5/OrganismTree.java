package HW5;

/**
 * The OrganismTree class will be the representation of the ternary tree of OrganismNode.
 * It consist of different interaction such as inserting, removing, printing,
 * moving the cursor with the OrganismTree. It will contain two OrganismNode named
 * root and cursor where the root will be the root of the OrganismTree which is the
 * apex predator while the cursor will be referencing the node that the user is
 * pointing to.
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class OrganismTree
{
    //The root OrganismNode will represent the root of the tree and the apex predator
    //it starts the OrganismTree
    private OrganismNode root;
    
    //The cursor OrganismNode will pointing to the node that the user want it to
    //be it can freely move around the OrganismTree according to the user
    private OrganismNode cursor;
    
    //This particular String is used in order to carry out the printOrganismTree method
    //as it is a global variable used to do the method
    private String foodChainStr;
    
    /**
     * Parameterized constructor of the OrganismTree that create a new OrganismTree
     * with the apexPredator as the root. The user will be asked information about the
     * apex predator when the program starts to fill in the information about the
     * apex predator
     * <p>
     * Postcondition: An OrganismTree object is created, with apexPredator representing
     * the apex predator or the root. Both of the root and cursor will be initially
     * pointing to the apexPredator
     * 
     * @param apexPredator 
     *  The apex predator of the food pyramid, it must be an animal and not a plant
     *  since a plant cannot prey on other Organism
     */
    public OrganismTree(OrganismNode apexPredator)
    {
        //Setting the values of root, cursor to apexPredator and foodChainStr to
        //a empty string value
        root=apexPredator;
        cursor=apexPredator;
        foodChainStr="";
    }
    
    /**
     * This method will move the cursor back to the root of the tree
     * <p>
     * Postcondition: cursor is now referncing the root of the tree
     */
    public void cursorRest()
    {
        cursor=root;
    }
    
    /**
     * 
     * 
     * 
     * @param name
     * @throws IllegalArgumentException 
     */
    public void moveCursor(String name) throws IllegalArgumentException
    {
        OrganismNode left=cursor.getLeft();
        OrganismNode middle=cursor.getMiddle();
        OrganismNode right=cursor.getRight();
        
        //Before we check the name we must make sure that all of the organism node
        //are not null or else it will be null pointer exception
        if(left==null&&middle==null&&right==null)
        {
            //If cursor is currently on a leaf of the organism tree, we just throw
            //the IllegalArgumentException telling the user that there is no child
            throw new IllegalArgumentException("There is no child for this node");
        }
        
        //If we are outside then that means that there are at least one child node
        //that we must check with the name
        //This means that the name matches with the left node therefore we make
        //cursor pointing to the left child
        if(left!=null&&name.equals(left.getName()))
        {
            cursor=cursor.getLeft();
        }
        //This means that the name matches with the middle node therefore we make
        //cursor pointing to the mdidle child
        else if(middle!=null&&name.equalsIgnoreCase(middle.getName()))
        {
            cursor=cursor.getMiddle();
        }
        //This means that the name matches with the right node therefore we make
        //cursor pointing to the right child
        else if(right!=null&&name.equalsIgnoreCase(right.getName()))
        {
            cursor=cursor.getRight();
        }
        //If it dooesn't match with any of the child thus we throw the exception
        else
        {
            throw new IllegalArgumentException("ERROR: This prey does not exist for"
                    + " this predator.");
        }
        
        //If we are out here then that means that the exception is not thrown
        //and the cursor moving is successful therefore we can just display the
        //success moving message
        System.out.println("Cursor successfully moved to "+name+"\n");
    }
    
    /**
     * 
     * 
     * 
     * @return
     * @throws IsPlantException 
     */
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
    
    /**
     * 
     * 
     * @return 
     */
    public String listFoodChain()
    {
        foodChainStr="";
        
        listFoodChainHelper(root);
        
        return foodChainStr;
        
    }
    
    /**
     * 
     * 
     * 
     * @param dummyNode
     * @return 
     */
    private boolean listFoodChainHelper(OrganismNode dummyNode)
    {
        OrganismNode node=dummyNode;
        
        boolean output=false;
        
        //This is found return true no need to keep on going, cursor is on
        //the target species
        if(cursor.getName().equalsIgnoreCase(node.getName()))
        {
            output=true;
            
            foodChainStr=node.getName()+foodChainStr;
            
            return output;
        }
        
        if(node.getLeft()!=null)
        {
            output=listFoodChainHelper(node.getLeft());
        }
        
        if(output)
        {
            foodChainStr=node.getName()+"->"+foodChainStr;
            
            return output;
        }
        
        if(output==false&&node.getMiddle()!=null)
        {
            
            output=listFoodChainHelper( node.getMiddle());
        }
        
        if(output)
        {
            foodChainStr=node.getName()+"->"+foodChainStr;
            
            return output;
        }
        
        if(output==false&&node.getRight()!=null)
        {
            
            output=listFoodChainHelper(node.getRight());
        }
        
        if(output)
        {
            foodChainStr=node.getName()+"->"+foodChainStr;
            
            return output;
        }
        
        return output;
    }
    
    /**
     * 
     */
    public void printOrganismTree()
    {
       fixDepth(cursor,0);
        
       printOrganismTreeHelper(cursor);
    }
    
    /**
     * 
     * 
     * @param dummyNode 
     */
    public void printOrganismTreeHelper(OrganismNode dummyNode)
    {
        if(dummyNode==null)
        {
            return;
        }
        
        if(dummyNode.getIsPlant())
        {
            for(int i=0;i<dummyNode.getDepth();i++)
            {
                System.out.print("  ");
            }
            System.out.print("-"+dummyNode.getName()+"\n");

        }
        else
        {
            for(int i=0;i<dummyNode.getDepth();i++)
            {
                System.out.print("  ");
            }
            System.out.print("|-"+dummyNode.getName()+"\n");
        }
        
        if(dummyNode.getLeft()!=null)
        {
            printOrganismTreeHelper(dummyNode.getLeft());
        }
        
        if(dummyNode.getMiddle()!=null)
        {
            printOrganismTreeHelper(dummyNode.getMiddle());
        }
        
        if(dummyNode.getMiddle()!=null)
        {
            printOrganismTreeHelper(dummyNode.getRight());
        }
        
    }
    
    /**
     * 
     * 
     * @param dummyNode
     * @param depth 
     */
    private void fixDepth(OrganismNode dummyNode, int depth)
    {
        dummyNode.setDepth(depth);
        
        if(dummyNode.getLeft()!=null)
        {
            fixDepth(dummyNode.getLeft(),depth+1);
        }
        
        if(dummyNode.getMiddle()!=null)
        {
            fixDepth(dummyNode.getMiddle(),depth+1);
        }
        
        if(dummyNode.getRight()!=null)
        {
            fixDepth(dummyNode.getRight(),depth+1);
        }
    }
    
    /**
     * 
     * 
     * @return 
     */
    public String listAllPlants()
    {
        String output="";
        
        output=listAllPlantsHelper(cursor);
        
        return output;
    }
    
    /**
     * 
     * 
     * @param dummyNode
     * @return 
     */
    private String listAllPlantsHelper(OrganismNode dummyNode)
    {
        OrganismNode node=dummyNode;
        
        String output="";
        
        //This is found return true no need to keep on going, cursor is on
        //the target species
        if(node==null)
        {
            return "";
        }
        
        //This means that the dummyNode we are at right now is a plant therefore
        //we add it to the output
        if(dummyNode.getIsPlant())
        {
            output=dummyNode.getName()+", ";
            
        }
        
        if(node.getLeft()!=null)
        {
            output+=listAllPlantsHelper(node.getLeft());
        }
        
        if(node.getMiddle()!=null)
        {
            output+=listAllPlantsHelper(node.getMiddle());
        }
        
        if(node.getRight()!=null)
        {
            output+=listAllPlantsHelper(node.getRight());
        }
        
        return output;
    }
    
    /**
     * This method creates a new animal nodew ith the specified name and the dietary
     * preferences and add it to the cursor as one of its child
     * <p>
     * Precondition: The name is not duplicate, it is not the same name as one of
     * the cursor's already's child. The cursor have an available position for another
     * child node to be added
     * <p>
     * Postcondition: Either an exception is thrown or the new animal node named name
     * is added as a child of the cursor with the specified diet preferences and the
     * cursor did not mvoed
     * 
     * @param name
     *  This is the name of the child node to be added
     * @param isHerbivore
     *  Dietary preference whether or not the child eat plant or not
     * @param isCarnivore
     *  Dietary preference whether or not the child eat meat
     * @throws IllegalArgumentException
     *  IllegalArgumentException is thrown to indicate that the given name shares
     *  the same name of the already child of cursor
     * @throws PositionNotAvailableException 
     *  PositionNotAvailableException is thrown to indicate that there is no more
     *  available child position for the new node to be added
     */
    public void addAnimalChild(String name, boolean isHerbivore, boolean isCarnivore) throws IllegalArgumentException, PositionNotAvailableException
    {
        //This String represent will be storing all of the prey of the cursor in
        //a String format
        String preys="";
        
        try
        {
            //Getting the cursor's prey
            preys=listPrey();
            
            //This means that the new prey is already a prey of the spcies
            //therefore we have to throw a new error to tell the user
            if(preys.indexOf(name)!=-1)
            {
                throw new IllegalArgumentException(name+" is already an prey of "+cursor.getName());
            }
            
            //If we are outside then the name is not a duplicate therefore we can
            //proceed to create the new OrganismNode with the name name to be
            //add it into the cursor as a child
            OrganismNode toBeAdded=new OrganismNode();
            
            //Setting the name of the new node
            toBeAdded.setName(name);
            
            //Setting the dietary preference of the new node
            toBeAdded.setIsCarnivore(isCarnivore);
            toBeAdded.setIsHerbivore(isHerbivore);
            
            //Because this method adds an animal child thus the new node is not a
            //plant
            toBeAdded.setIsPlant(false);
            
            //Adding it to the cursor
            try
            {
                cursor.addPrey(toBeAdded);
            }
            //This means that the diet doesn't match with the cursor thus the error
            //is thrown to tell the user
            catch(DietMismatchException d)
            {
                System.out.println(d);
            }
        }
        //This means that the cursor is a plant therefore it cannot have any prey
        //thus the error is thrown to tell the user
        catch(IsPlantException i)
        {
            System.out.println("Using listPrey in addAnimalChild method on a plant,"
                    + " therefore the node cannot be added");
        }
        
    }
    
    /**
     * This method create a new plant OrganismNode with the specified name and
     * add it as a child of the cursor node
     * <p>
     * Precondition: The name is not a duplicate of a already child of the cursor,
     * and that the cursor have an available postiion for another child node to be
     * added to it
     * 
     * @param name
     *  This is the name of the child node that is going to be added
     * @throws IllegalArgumentException
     *  IllegalArgumentException is thrown to indicate that the given name is a 
     *  duplicate of a child node in the cursor
     * @throws PositionNotAvailableException 
     *  PositionNotAvailableException is thrown to indicate that there is no more
     *  available child spot for the new node to be added
     */
    public void addPlantChild(String name) throws IllegalArgumentException, PositionNotAvailableException
    {
        //preys will be containing the listPrey String of the cursor, this will
        //allow us to check if there are any duplicates of the same prey on
        //the cursor before adding it
        String preys="";
        try
        {
            //Gettign the cursor's prey
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
            
            //Adding the new node into the cursor
            try
            {
                cursor.addPrey(toBeAdded);
            }
            //IsPlantException is thrown if the cursor is a plant to tell the user
            //that a plant cannot have any prey
            catch(IsPlantException i)
            {
                System.out.println(i);
            }
            //DietMismatchException is thrown if the new node does not match with
            //the cursor species' diet preferences
            catch(DietMismatchException d)
            {
                System.out.println(d);
            }
        }
        //Because listPrey throws a error when being used we must catch it and
        //tell the user about it 
        catch(IsPlantException i)
        {
            System.out.println(i);
        }
    }
    
    /**
     * This method remove the child node of cursor with the name name, and then
     * shifts the remaining nodes to the leftmost spot. The descendants of the
     * deleted node are remvoed as well
     * <p>
     * Precondition: The name references to a direct child of the cursor
     * <p>
     * Postcondition: The child node of cursor with the name name have been removed
     * and the remaining node have been properly shifted, and the descendants of the
     * the removed node are also deleted as well, and that cursor have not been moved
     * 
     * @param name
     *  The name of the node that is going to be deleted
     * @throws IllegalArgumentException 
     *  IllegalArgumentException is thrown if the name does not match with a
     *  direct child of the cursor
     */
    public void removeChild(String name) throws IllegalArgumentException
    {
        //Getting the left, middle, right child of the cursor to be checking
        //with the name
        OrganismNode left=cursor.getLeft();
        OrganismNode middle=cursor.getMiddle();
        OrganismNode right=cursor.getRight();
        
        //This means that there is no child to remove for the cursor
        if(left==null&&middle==null&&right==null)
        {
            throw new IllegalArgumentException("There is no child to remove for this organism node");
        }
        
        //This means that the left node is the one we need to remove
        //and we must shift middle to left, and right to middle, and set right to null
        if(left!=null&&name.equals(left.getName()))
        {
            //Shifting the remaining child of the cursor so it is to the leftmost
            //as possible
            cursor.setLeft(middle);
            cursor.setMiddle(right);
            cursor.setRight(null);
            
            //Print out the approariate message after removing
            System.out.println(name+" has been successfully removed as prey for the "
                    +cursor.getName());
        }
        //This means that the middle node is the one we need to remove
        //and we must shift right to middle, and right to null
        else if(middle!=null&&name.equalsIgnoreCase(middle.getName()))
        {
            //Shifting the remaining child of the cursor so it is to the leftmost
            //as possible
            cursor.setMiddle(right);
            cursor.setRight(null);
            
            //Print out the approariate message after removing
            System.out.println(name+" has been successfully removed as prey for the "
                    +cursor.getName());
        }
        //This means that the right node is the one we need to remove
        //and we must set right to null
        else if(right!=null&&name.equalsIgnoreCase(right.getName()))
        {
            //Since it only removed the right there is no need to shift all of the other nodes
            cursor.setRight(null);
            
            //Print out the approariate message after removing
            System.out.println(name+" has been successfully removed as prey for the "
                    +cursor.getName());
        }
        //If it dooesn't match with any of the child thus we throw the exception
        else
        {
            throw new IllegalArgumentException("ERROR: This prey does not exist for"
                    + " this predator thus cannot successfully remove.");
        }
    }
    
    /**
     * 
     * 
     * @return 
     */
    public boolean cursorIsFull()
    {
        boolean output=false;
        
        OrganismNode left=cursor.getLeft();
        OrganismNode middle=cursor.getMiddle();
        OrganismNode right=cursor.getRight();
        
        //This means that all of the nodes of the cursor are filled which means
        //that the cursor is full of child
        if(left!=null&&middle!=null&&right!=null)
        {
            output=true;
            
            return output;
        }
        
        //If we are outside then that means that there is at least one node
        //of the cursor is empty thus the cursor is not completely full
        return output;
    }
    
    /**
     * 
     * 
     * @return 
     */
    public OrganismNode getCursor()
    {
        return cursor;
    }
    
    
    
    
    
    
    
    
    
    
}




