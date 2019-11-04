package HW5;

public class OrganismTree
{
    //Instance variables
    private OrganismNode root;
    private OrganismNode cursor;
    
    private String foodChainStr;
    
    //Default constructor
    public OrganismTree(OrganismNode apexPredator)
    {
        root=apexPredator;
        cursor=apexPredator;
        foodChainStr="";
    }
    
    //This rests the cursor to the apex predator or the root of the organism tree
    public void cursorRest()
    {
        cursor=root;
    }
    
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
    }
    
    //Wrong method
    /*
    public void moveCursor(String name) throws IllegalArgumentException
    {
        boolean found=findTarget(name,cursor);
        
        if(found)
        {
            System.out.println("Cursor successfully moved to "+name);
        }
        else
        {
            System.out.println(name+" cannot be found as a child of Cursor");
        }
    }
    
    private boolean findTarget(String name, OrganismNode dummyNode)
    {
        OrganismNode node=dummyNode;
        
        boolean output=false;
        
        //This is found return true no need to keep on going, cursor is on
        //the target species
        if(name.equalsIgnoreCase(node.getName()))
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
    
    //This method will be tracking from the root of the food tree, which is the apex
    //predator all the way up to the cursor
    public String listFoodChain()
    {
        foodChainStr="";
        
        listFoodChainHelper(root);
        
        return foodChainStr;
        
    }
    
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
    
    
    public void printOrganismTree()
    {
       fixDepth(cursor,0);
        
       printOrganismTreeHelper(cursor);
    }
    
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
    
    public String listAllPlants()
    {
        String output="";
        
        output=listAllPlantsHelper(cursor);
        
        return output;
    }
    
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
            
            toBeAdded.setName(name);
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
            System.out.println("Using listPrey in addAnimalChild method on a plant,"
                    + " therefore the node cannot be added");
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
    
    
    /*
    public void removeChild(String name) throws IllegalArgumentException
    {
        boolean complete=removeHelper(name,null,cursor);
        
        if(complete)
        {
            System.out.println(name+" is removed successfully");
        }
        else
        {
            System.out.println(name+" is not found");
        }
    }
    
    private boolean removeHelper(String name, OrganismNode parent, OrganismNode child)
    {
        boolean taskComplete=false;
        
        //This is the base case when the child is the one that we are looking for
        //therefore we must remove the child from the parent
        if(name.equals(child.getName()))
        {
            //This means that the child is the root of the entire organismTree
            //and it is the one we need to remove, therefore we just set the entire
            //tree to be null because we are removing that root
            if(parent==null)
            {
                root=null;
            }
            else
            {
                OrganismNode parentLeft=parent.getLeft();
                OrganismNode parentMiddle=parent.getMiddle();
                OrganismNode parentRight=parent.getRight();

                if(parentLeft==child)
                {
                    parent.setLeft(parentMiddle);
                    parent.setMiddle(parentRight);
                    parent.setRight(null);
                }
                else if(parentMiddle==child)
                {
                    parent.setMiddle(parentRight);
                    parent.setRight(null);
                }
                else if(parentRight==child)
                {
                    parent.setRight(null);
                }
            }
            
            return true;
        }
        
        //If we are not in the base case then that means that the child is not the
        //node we are looking for
        if(child.getLeft()!=null&&!taskComplete)
        {
            OrganismNode dummyParent=child;
            OrganismNode dummyChild=child.getLeft();
            
            taskComplete=removeHelper(name,dummyParent,dummyChild);
        }
        
        if(child.getMiddle()!=null&&!taskComplete)
        {
            OrganismNode dummyParent=child;
            OrganismNode dummyChild=child.getMiddle();
            
            taskComplete=removeHelper(name,dummyParent,dummyChild);
        }
        
        if(child.getRight()!=null&&!taskComplete)
        {
            OrganismNode dummyParent=child;
            OrganismNode dummyChild=child.getRight();
            
            taskComplete=removeHelper(name,dummyParent,dummyChild);
        }
        
        return taskComplete;
    }

    */
    
    public OrganismNode getCursor()
    {
        return cursor;
    }
    
    public OrganismNode getRoot()
    {
        return root;
    }
    
    
    
    
    
    
    
    
    
}




