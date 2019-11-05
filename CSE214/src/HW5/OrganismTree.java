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
     * This method moves cursor to one of cursor's children
     * <p>
     * Precondition: The name reference to a valid name of one of cursor's children
     * <p>
     * Postcondition: The method either throw an exception or the cursor is now pointing
     * to the node whose name is reference by the given name. Essentially mean
     * cursor is now pointing to a child of the original cursor
     * 
     * @param name
     *  The name of the node to move to
     * @throws IllegalArgumentException
     *  IllegalArgumentException is thrown if the name is not a direct child of
     *  cursor
     */
    public void moveCursor(String name) throws IllegalArgumentException
    {
        //Getting the left, middle, and right node of the cursor for comparsion
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
     * This method returns a String that include the organism at the cursor and all
     * the possible prey it can eat on
     * <p>
     * Postcondition: The cursor is not moved
     * 
     * @return Returns a String that contain the name of the cursor and all of its prey
     * @throws IsPlantException 
     *  IsPlantException is thrown if the cursor is currently pointing to a plant
     *  OrganismNode
     */
    public String listPrey() throws IsPlantException
    {
        //Output represent the chain of prey that the cursor can have
        String output=cursor.getName()+"->";
        
        //Getting the left, middle, and right node of cursor
        OrganismNode left=cursor.getLeft();
        OrganismNode middle=cursor.getMiddle();
        OrganismNode right=cursor.getRight();
        
        //This means that the cursor is currently a plant node therefore we have
        //to throw the exception telling the user
        if(cursor.getIsPlant())
        {
            throw new IsPlantException("The species is a plant, it doesn't have eat prey");
        }
        
        //This makes sure to check that left is not null before getting the name
        //to prevent the null exception error
        if(left!=null)
        {
            output+=left.getName()+",";
        }
        
        //This makes sure to check the mdidle is not null before getting the name
        //to prevent the null exception error
        if(middle!=null)
        {
            output+=middle.getName()+",";
        }
        
        //This makes sure to check the right is not null before getting the name
        //to prevent the null exception error
        if(right!=null)
        {
            output+=right.getName();
        }
        
        return output;
    }
    
    /**
     * This method will return a String that contain the correct path of organisms
     * it needs to take to lead from the apex predator to the organism at the cursor
     * <p>
     * Postcondition: cursor have not been moved
     * 
     * @return Returns a String that contain the food chain path from apex predator
     *  to the cursor
     */
    public String listFoodChain()
    {
        foodChainStr="";
        
        //This means taht the cursor is at the root therefore there is nothing
        //to be printed
        if(root==cursor)
        {
            foodChainStr="Cursor is at the root, it is the apex predator";
        }
        else
        {
            //Calling the listFoodChainHelper method
            listFoodChainHelper(root);
        }
        
        return foodChainStr;
        
    }
    
    /**
     * The helper method for listFoodChain, it will recursively go through the 
     * nodes to check whether or not it is the correct path and then use the foodChainStr
     * global variable to help make the path to the cursor
     * 
     * @param dummyNode
     *  The node to start the searching from, which will be root in the 1st call
     *  It will also be used as recursively
     * @return Returns a boolean value to tell the recursive calls whether or not
     *  the target have been found
     */
    private boolean listFoodChainHelper(OrganismNode dummyNode)
    {
        OrganismNode node=dummyNode;
        
        //This output will be initially false until the recursively case make it 
        //true which means the target have been fouund
        boolean output=false;
        
        //This is found return true no need to keep on going, cursor is on
        //the target species
        //This is also the base case
        if(cursor.getName().equalsIgnoreCase(node.getName()))
        {
            output=true;
            
            foodChainStr=node.getName()+foodChainStr;
            
            return output;
        }
        
        //First recursive case to go to the left
        if(node.getLeft()!=null)
        {
            output=listFoodChainHelper(node.getLeft());
        }
        
        //After each recursive call we have to check whether or not the returned output
        //is true, if it is true then we stop going to the other recursive call since
        //we have found the correct path
        if(output)
        {
            foodChainStr=node.getName()+"->"+foodChainStr;
            
            return output;
        }
        
        //This is the second recursive case to go to the middle
        if(output==false&&node.getMiddle()!=null)
        {
            
            output=listFoodChainHelper( node.getMiddle());
        }
        
        //Again after the middle recursive call we check whether or not output have been found
        //so we can ignore the other recursive calls
        if(output)
        {
            foodChainStr=node.getName()+"->"+foodChainStr;
            
            return output;
        }
        
        //This is the third recursive case
        if(output==false&&node.getRight()!=null)
        {
            
            output=listFoodChainHelper(node.getRight());
        }
        
        //As well we must check whether or not output is found so we can
        //stop the other recursive calls
        if(output)
        {
            foodChainStr=node.getName()+"->"+foodChainStr;
            
            return output;
        }
        
        return output;
    }
    
    /**
     * This method will print out a layered, indented tree by performing a
     * preorder traversal starting at the cursor throughout the entire OrganismTree
     * with the cursor noting as the root of the tree. It will be calling
     * the printOrganismTreeHelper to do this task
     * <p>
     * Postcondition: Cursor is not moved and so is root is not moved as well
     */
    public void printOrganismTree()
    {
        //First we have to call the fixDepth method to assign the depth of each 
        //node respect to the cursor because we are referring cursor as the root
        //in this method. So that we can properly indent the nodes
       fixDepth(cursor,0);
        
       //Then we call the printOrganismTreeHelper with cursor as the root to print
       //out the OrganismNode in the layered and indented tree style
       printOrganismTreeHelper(cursor);
       
        System.out.println("");
    }
    
    /**
     * This is the helper method for the printOrganismTree method, it takes in a
     * OrganismNode and perform a preorder traversal and printing it.
     * <p>
     * Postcondition: A layered, indented tree is printed with the cursor as the root
     * and all the other OrganismNode to its correct palce
     * 
     * @param dummyNode 
     *  The root of the OrganismTree to start the printing on
     */
    private void printOrganismTreeHelper(OrganismNode dummyNode)
    {
        //The base case, which when the node is null we don't do anything
        if(dummyNode==null)
        {
            return;
        }
        
        //This is the thing we want to do for the preorder traversal which is
        //to print out the OrganismNode's name with the proper sign in front of it.
        //And this is the case for when it is a plant which we just use - in front
        if(dummyNode.getIsPlant())
        {
            //Using the depth of the node to properly indent it to it's corresponding
            //place in the tree
            for(int i=0;i<dummyNode.getDepth();i++)
            {
                System.out.print("  ");
            }
            
            System.out.print("-"+dummyNode.getName()+"\n");

        }
        //This is the second case which is when the node is an animal therefore
        //we use |- in front of the node name
        else
        {
            //Using the depth of the node to properly indent it to it's corresponding
            //place in the tree
            for(int i=0;i<dummyNode.getDepth();i++)
            {
                System.out.print("  ");
            }
            
            System.out.print("|-"+dummyNode.getName()+"\n");
        }
        
        //Recursive case to go to the left first
        if(dummyNode.getLeft()!=null)
        {
            printOrganismTreeHelper(dummyNode.getLeft());
        }
        
        //Recursive case to the middle after the left
        if(dummyNode.getMiddle()!=null)
        {
            printOrganismTreeHelper(dummyNode.getMiddle());
        }
        
        //Recursive case to the right as the final one
        if(dummyNode.getMiddle()!=null)
        {
            printOrganismTreeHelper(dummyNode.getRight());
        }
    }
    
    /**
     * Helper method for the printOrganismTree which will perform a preorder traversal
     * that sets the depth of each tree node respect to the given node as the 0
     * depth
     * 
     * @param dummyNode
     *  The node to denote as the root, and it also represent all the recursive call's
     *  OrganismNode
     * @param depth
     *  The starting depth will always be 0 and it will be passed on recursively
     *  to assign to each dummyNode
     */
    private void fixDepth(OrganismNode dummyNode, int depth)
    {
        //Setting the depth of the node to depth
        dummyNode.setDepth(depth);
        
        //Recursive call for the left node
        if(dummyNode.getLeft()!=null)
        {
            fixDepth(dummyNode.getLeft(),depth+1);
        }
        
        //Recursive call for the middle node
        if(dummyNode.getMiddle()!=null)
        {
            fixDepth(dummyNode.getMiddle(),depth+1);
        }
        
        //Recursive call for the right node
        if(dummyNode.getRight()!=null)
        {
            fixDepth(dummyNode.getRight(),depth+1);
        }
    }
    
    /**
     * This method will return a list of all of the plants that is at cursor and
     * below it in the food pyramid
     * <p>
     * Postcondition: cursor is not moved and so is root
     * 
     * @return Returns a String that contain a list of all of the plants in the food pyramid
     *  at and after the cursor
     */
    public String listAllPlants()
    {
        String output="";
        
        output=listAllPlantsHelper(cursor);
        
        if(output.isEmpty())
        {
            output="There are no plants supporting this cursor";
        }
        
        return output;
    }
    
    /**
     * This is the helper method for the listAllPlants method, it will return a
     * String that contains a list of all of the plant at the cursor and then
     * below it
     * <p>
     * Postcondition: The cursor is not moved and so is the root
     * 
     * @param dummyNode
     *  This is the node to start gathering all of the plant node from
     * @return Returns a String that represent all of the plants that is
     *  supporting this OrganismNode
     */
    private String listAllPlantsHelper(OrganismNode dummyNode)
    {
        String output="";
        
        //This is found return true no need to keep on going, cursor is on
        //the target species
        if(dummyNode==null)
        {
            return "";
        }
        
        //This means that the dummyNode we are at right now is a plant therefore
        //we add it to the output
        if(dummyNode.getIsPlant())
        {
            output=dummyNode.getName()+", ";
            
        }
        
        //Recursive case to the left node
        if(dummyNode.getLeft()!=null)
        {
            output+=listAllPlantsHelper(dummyNode.getLeft());
        }
        
        //Recursive case to the middle node
        if(dummyNode.getMiddle()!=null)
        {
            output+=listAllPlantsHelper(dummyNode.getMiddle());
        }
        
        //Recursive case to the right node
        if(dummyNode.getRight()!=null)
        {
            output+=listAllPlantsHelper(dummyNode.getRight());
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
            preys="$"+listPrey()+"$";
            
            int nameIndex=preys.indexOf(name);
            
            //This means that the new prey is already a prey of the spcies
            //therefore we have to throw a new error to tell the user.
            //However before throwing the error we must check further because
            //a cursor having pondweed and then adding weed can still result in
            //this error thus we need to check even further
            if(nameIndex!=-1)
            {
                String rightAfterName=preys.substring(nameIndex+name.length(),nameIndex+name.length()+1);
                String rightBeforeName=preys.substring(nameIndex-1,nameIndex);
                
                //This is 100% certain that it is a duplicate name therefore we can
                //throw the error now
                if(rightAfterName.equals(",")&&rightBeforeName.equals(">"))
                {
                    throw new IllegalArgumentException(name+" is already an prey of "+cursor.getName());
                }
                //This is another case that it can happen
                else if(rightAfterName.equals(",")&&rightBeforeName.equals(","))
                {
                    throw new IllegalArgumentException(name+" is already an prey of "+cursor.getName());
                }
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
            preys="$"+listPrey()+"$";
            
            int nameIndex=preys.indexOf(name);
            
            //This means that the new prey is already a prey of the spcies
            //therefore we have to throw a new error to tell the user.
            //However before throwing the error we must check further because
            //a cursor having pondweed and then adding weed can still result in
            //this error thus we need to check even further
            if(nameIndex!=-1)
            {
                String rightAfterName=preys.substring(nameIndex+name.length(),nameIndex+name.length()+1);
                String rightBeforeName=preys.substring(nameIndex-1,nameIndex);
                
                //This is 100% certain that it is a duplicate name therefore we can
                //throw the error now
                if(rightAfterName.equals(",")&&rightBeforeName.equals(">"))
                {
                    throw new IllegalArgumentException(name+" is already an prey of "+cursor.getName());
                }
                //This is another case that it can happen
                else if(rightAfterName.equals(",")&&rightBeforeName.equals(","))
                {
                    throw new IllegalArgumentException(name+" is already an prey of "+cursor.getName());
                }
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
            throw new IllegalArgumentException("There is no child to remove for this organism node\n");
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
                    +cursor.getName()+"\n");
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
                    +cursor.getName()+"\n");
        }
        //This means that the right node is the one we need to remove
        //and we must set right to null
        else if(right!=null&&name.equalsIgnoreCase(right.getName()))
        {
            //Since it only removed the right there is no need to shift all of the other nodes
            cursor.setRight(null);
            
            //Print out the approariate message after removing
            System.out.println(name+" has been successfully removed as prey for the "
                    +cursor.getName()+"\n");
        }
        //If it dooesn't match with any of the child thus we throw the exception
        else
        {
            throw new IllegalArgumentException("ERROR: This prey does not exist for"
                    + " this predator thus cannot successfully remove.\n");
        }
    }
    
    /**
     * Returns the boolean value that represent whether the cursor's three child
     * is completely filled or not
     * 
     * @return Returns a boolean value that shows whether or not cursor's child is full
     */
    public boolean cursorIsFull()
    {
        boolean output=false;
        
        //Getting the left, middle, and right node of cursor
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
     *  Returns the boolean value that represent whether the cursor's three child
     *  is completely empty or not
     * 
     * @return Returns a boolean value that shows whether or not cursor is empty
     */
    public boolean cursorIsEmpty()
    {
         boolean output=false;
        
        //Getting the left, middle, and right node of cursor
        OrganismNode left=cursor.getLeft();
        OrganismNode middle=cursor.getMiddle();
        OrganismNode right=cursor.getRight();
        
        //This means that all of the nodes of the cursor are filled which means
        //that the cursor is full of child
        if(left==null&&middle==null&&right==null)
        {
            output=true;
            
            return output;
        }
        
        //If we are outside then that means that there is at least one node
        //of the cursor is empty thus the cursor is not completely full
        return output;
    }
    
    /**
     * Returns the cursor OrganismNode
     * 
     * @return Returns the cursor
     */
    public OrganismNode getCursor()
    {
        return cursor;
    }
    
    
    
    
    
    
    
    
    
    
}




