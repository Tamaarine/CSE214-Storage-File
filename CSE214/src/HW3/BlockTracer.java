package HW3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * The BlockTracer class is meant to be the driver of the application which ask the
 * user to input in a .c file to be read. It will be keeping track of all of the
 * variable name with its initial value. There will also be special directives inside
 * the text file to instruct the program to print certain variables
 * <p>
 * /*$print LOCAL* / to print all of the variables inside the block
 * <p>
 * /*$print VARIABLE NAME* / to print the certain variable it specified
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class BlockTracer
{
    /**
     * The main method will ask the user to input a filename and then parses the file
     * then it will proceed through the file keeping track of every single variable
     * declarations and it's inital value, and also handle all of the directives.
     * Then the program finally terminates
     * 
     * @param args 
     *  args contains the text from the command line that starts the program
     */
    public static void main(String [] args)
    {
        //This creates Stack of Blocks that will be helping to keep track of variables
        //inside each Block
        Stack<Block> blockStack=new Stack<Block>();
        
        //The InputStreamReader and BUfferedReader work together to read the user inputs
        InputStreamReader userInput=new InputStreamReader(System.in);
        BufferedReader userInputReader=new BufferedReader(userInput);
        
        String userInputString="";
        
        System.out.print("Please enter a file name to read: ");
        try
        {
            userInputString=userInputReader.readLine();
        }
        catch(IOException i)
        {
            System.out.println("User input error");
        }
        
        //This means that the user didn't entered in a empty file which means
        //they have entered in a text file for the program to read
        if(!userInputString.isEmpty())
        {
            try
            {
                //fileInput, inputStream, and reader work together to read the
                //text file line by line
                FileInputStream fileInput=new FileInputStream(userInputString);
                
                InputStreamReader inputStream=new InputStreamReader(fileInput);
                BufferedReader reader=new BufferedReader(inputStream);
                
                //This gets us the first line of the text file
                String eachLine=reader.readLine();
                
                //This means that while eachLine we are reading is not an empty
                //line we proceed to read it, even if it only contains spaces
                while(eachLine!=null)
                {
                    //This is making a temporary String that is copy of eachLine
                    //so we can work with it using substring however we like
                    String workingTemp=eachLine;
                    
                    //haveString boolean variable represent whether or not there is
                    //things to read in the line. If it only contain spaces haveString
                    //will become false
                    boolean haveString=false;
                    
                    //This is the first loop that loop through the workingTemp to see
                    //if it only contain spaces
                    for(int i=0;i<workingTemp.length();i++)
                    {
                        //This means that there is a letter inside workingTemp which means
                        //it is granteeded to have something that we need to work with
                        if(workingTemp.charAt(i)!=' ')
                        {
                            haveString=true;
                            
                            //We don't need to check anything else anymore so we just break
                            //away from the loop
                            break;
                        }
                    }
                    
                    //declarationMode represent whether to start variable declarating
                    //sequences because there are multiple variables follow the int indicator
                    boolean declarationMode=false;
                    
                    //lastVariable is for the declarationMode to indicate that it is
                    //the last variable to handle
                    boolean lastVariable=false;
                    
                    //This while loop will continue to parse the String as long as
                    //have letters
                    while(haveString)
                    {
                        //This creates a Variable that we will be adding into the
                        //Block
                        Variable toBeAdded=new Variable();
                        
                        //Index of "int "
                        int intIndex=workingTemp.indexOf("int ");
                        
                        //Index of "l"
                        int semiIndex=workingTemp.indexOf(";");
                        
                        //Index of "="
                        int equalIndex=workingTemp.indexOf("=");
                        
                        //Index of ","
                        int commaIndex=workingTemp.indexOf(",");
                        
                        //Index of "{"
                        int leftBracketIndex=workingTemp.indexOf("{");
                        
                        //Index of "}"
                        int rightBracketIndex=workingTemp.indexOf("}");
                        
                        //Index of "/*$print"
                        int printIndex=workingTemp.indexOf("/*$print");
                        
                        //Index of "*/"
                        int directEnd=workingTemp.indexOf("*/");
                        
                        //pureInt boolean will be representing whether it is the
                        //print or int that really result in the intIndex
                        boolean pureInt=true;
                        
                        //This gets the String from the beginng up until the index
                        //of int. This is used to determine whether it is a int declaration
                        //or a print directive
                        String zeroToInt="";
                        
                        if(intIndex!=-1)
                        {
                            zeroToInt=workingTemp.substring(0,intIndex);
                        }
                        
                        if(zeroToInt.contains("/*$pr"))
                        {
                            pureInt=false;
                        }
                        
                        //If there is no comma left then it means it is the last
                        //variable to declare
                        if(commaIndex>semiIndex||commaIndex==-1)
                        {
                            lastVariable=true;
                        }
                        
                        //This boolean variable determines whether or not it have a
                        //left bracket and right bracket in the line it is reading right now
                        boolean containLeftBracket=workingTemp.contains("{");
                        boolean containRightBracket=workingTemp.contains("}");

                        //This boolean is used to make sure that semi colon is after
                        //an int
                        boolean semiBeforeInt=true;
                        
                        if(semiIndex<intIndex)
                        {
                            semiBeforeInt=false;
                        }
                        
                        
                        //This means that the line we are reading right now contain a
                        //left bracket which means to add in a new block
                        if(containLeftBracket)
                        {
                            //Adding in a new Block for the stack
                            blockStack.add(new Block());
                            
                            //We have to update the workingTemp String we are working
                            //with to reflect that we have handle the left bracket
                            workingTemp=workingTemp.substring(leftBracketIndex+1);
                        }
                        //This means that it is a start int declaration
                        else if(intIndex!=-1&&semiIndex!=-1&&pureInt&&semiBeforeInt)
                        {
                            //Preparing the String varName and strVarValue to gather
                            //information
                            String varName="";
                            String strVarValue="";
                            
                            //This means that there is multiple variable declaration
                            //which mean we need to set declarationMdoe to true 
                            //because there will be more variables coming but without
                            //the int indicator
                            if(commaIndex<semiIndex&&commaIndex!=-1)
                            {
                                //Setting the declarationMode to be true so that
                                //it will come back in here to handle all the rest of the
                                //variable declaration
                                declarationMode=true;
                                
                                //We have to also make sure that the equal sign is with the variable
                                //that we are dealing with
                                if(equalIndex<commaIndex&&equalIndex!=-1)
                                {
                                    //This gets the name of the variable
                                    //up until the equal sign
                                    for(int i=intIndex+4;i<equalIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                    //This gets the value of the variable up
                                    //until the comma index
                                    for(int i=equalIndex+1;i<commaIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            strVarValue+=workingTemp.charAt(i);
                                        }
                                    }
                                    
                                    //This sets the default value for varValue
                                    int varValue=0;
                                    
                                    //This means that strVarValue gathered actual data
                                    if(!strVarValue.isEmpty())
                                    {
                                        //This try to parse the data into int
                                        try
                                        {
                                            varValue=Integer.parseInt(strVarValue);
                                        }
                                        //This means that the value used is an valid
                                        //variable therefore we must look through the 
                                        //stacks to find the approatie value
                                        catch(Exception e)
                                        {
                                            //Makes a temporary stack to put our
                                            //blocks into
                                            Stack<Block> tempStack=new Stack<Block>();
                                            
                                            //This variable determines whether or not
                                            //we have found the target
                                            boolean targetFound=false;

                                            Variable target=new Variable();
                                            
                                            while(!blockStack.isEmpty()&&!targetFound)
                                            {
                                                //We popped the top of the blockStack
                                                Block poppedBlock=blockStack.pop();

                                                //And push it into tempStack
                                                tempStack.push(poppedBlock);
                                                
                                                //findVariable method tries to find it in the
                                                //poppedBlock
                                                target=poppedBlock.findVariable(strVarValue);

                                                //This means that the target is found thus
                                                //we set targetFound to true to exit the loop
                                                if(target!=null)
                                                {
                                                    targetFound=true;
                                                }
                                            }
                                            
                                            //This pushes all the blocks that is taken out
                                            //back into the original stacks
                                            while(!tempStack.isEmpty())
                                            {
                                                blockStack.push(tempStack.pop());
                                            } 
                                            
                                            //We are granteeded that the syntax is correct
                                            //therefore it won't be nullpointer error
                                            varValue=target.getInitialValue();
                                        }
                                    }

                                    toBeAdded.setName(varName);
                                    toBeAdded.setInitialValue(varValue);

                                    //We have to update the workingTemp to reflect that we have
                                    //handle the case alreday
                                    workingTemp=workingTemp.substring(commaIndex+1);
                                }
                                //No equal sign with the value thus default value
                                //we just need to get the name
                                else
                                {
                                    //This gets the name of the variable
                                    //up until the comma
                                    for(int i=intIndex+4;i<commaIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                    
                                    int varValue=0;

                                    toBeAdded.setName(varName);
                                    toBeAdded.setInitialValue(varValue);

                                    workingTemp=workingTemp.substring(commaIndex+1);
                                }
                            }
                            //This means that multiple variable declaration
                            //comes later, there is only one variable declaration to
                            //handle right now
                            else
                            {
                                //We have to also make sure that the equal sign is with
                                //the variable we are dealing with
                                if(equalIndex<semiIndex&&equalIndex!=-1)
                                {
                                    //This gets the name of the variable up until
                                    //the equal index
                                    for(int i=intIndex+4;i<equalIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                    
                                    //This gets the value of the variable up until
                                    //the semi colon index
                                    for(int i=equalIndex+1;i<semiIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            strVarValue+=workingTemp.charAt(i);
                                        }
                                    }
                                }
                                //This means that set the variable to default value
                                //because there is no equal sign
                                else
                                {
                                    //This gets the name of the variable
                                    for(int i=intIndex+4;i<semiIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                }
                                
                                //This sets the default value for varValue
                                int varValue=0;

                                //This means that strVarValue gathered actual data
                                if(!strVarValue.isEmpty())
                                {
                                    //This try to parse the data into int
                                    try
                                    {
                                        varValue=Integer.parseInt(strVarValue);
                                    }
                                    //This means that the value used is an valid
                                    //variable therefore we must look through the 
                                    //stacks to find the approatie value
                                    catch(Exception e)
                                    {
                                        //Makes a temporary stack to put our
                                        //blocks into
                                        Stack<Block> tempStack=new Stack<Block>();

                                        //This variable determines whether or not
                                        //we have found the target
                                        boolean targetFound=false;

                                        Variable target=new Variable();

                                        while(!blockStack.isEmpty()&&!targetFound)
                                        {
                                            //We popped the top of the blockStack
                                            Block poppedBlock=blockStack.pop();

                                            //And push it into tempStack
                                            tempStack.push(poppedBlock);

                                            //findVariable method tries to find it in the
                                            //poppedBlock
                                            target=poppedBlock.findVariable(strVarValue);

                                            //This means that the target is found thus
                                            //we set targetFound to true to exit the loop
                                            if(target!=null)
                                            {
                                                targetFound=true;
                                            }
                                        }

                                        //This pushes all the blocks that is taken out
                                        //back into the original stacks
                                        while(!tempStack.isEmpty())
                                        {
                                            blockStack.push(tempStack.pop());
                                        } 

                                        //We are granteeded that the syntax is correct
                                        //therefore it won't be nullpointer error
                                        varValue=target.getInitialValue();
                                    }
                                }

                                toBeAdded.setName(varName);
                                toBeAdded.setInitialValue(varValue);

                                //We have to update the workingTemp to reflect
                                //that we have handle this specfici case
                                workingTemp=workingTemp.substring(semiIndex+1);
                            }
                            
                            //Finally after handling all of the initial declaration
                            //we add it into the Block
                            try
                            {
                                blockStack.peek().addElement(toBeAdded);
                            }
                            catch(FullBlockException f)
                            {
                                System.out.println(f);
                            }
                            
                        }
                        //This means that to make a variable, but there is no
                        //int indicator so we just start from 0 and get the values
                        else if(declarationMode)
                        {
                            //Preparing the String varName and strVarValue to
                            //gather our informations in
                            String varName="";
                            String strVarValue="";
                            
                            //This means that this is the last one to handle
                            if(lastVariable)
                            {
                                //We have to set declarationMdoe to false
                                declarationMode=false;
                                
                                //This means that the last one have a value
                                if(equalIndex<semiIndex&&equalIndex!=-1)
                                {
                                    //This gets the name of the variable
                                    for(int i=0;i<equalIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                    
                                    //This gets the value of the variable
                                    for(int i=equalIndex+1;i<semiIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            strVarValue+=workingTemp.charAt(i);
                                        }
                                    }
                                }
                                //The last one doesn't have a value
                                else
                                {
                                    //This gets the name of the variable
                                    for(int i=0;i<semiIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                }
                                
                                int varValue=0;
                                
                                //This means that strVarValue gathered actual data
                                if(!strVarValue.isEmpty())
                                {
                                    //This try to parse the data into int
                                    try
                                    {
                                        varValue=Integer.parseInt(strVarValue);
                                    }
                                    //This means that the value used is an valid
                                    //variable therefore we must look through the 
                                    //stacks to find the approatie value
                                    catch(Exception e)
                                    {
                                        //Makes a temporary stack to put our
                                        //blocks into
                                        Stack<Block> tempStack=new Stack<Block>();

                                        //This variable determines whether or not
                                        //we have found the target
                                        boolean targetFound=false;

                                        Variable target=new Variable();

                                        while(!blockStack.isEmpty()&&!targetFound)
                                        {
                                            //We popped the top of the blockStack
                                            Block poppedBlock=blockStack.pop();

                                            //And push it into tempStack
                                            tempStack.push(poppedBlock);

                                            //findVariable method tries to find it in the
                                            //poppedBlock
                                            target=poppedBlock.findVariable(strVarValue);

                                            //This means that the target is found thus
                                            //we set targetFound to true to exit the loop
                                            if(target!=null)
                                            {
                                                targetFound=true;
                                            }
                                        }

                                        //This pushes all the blocks that is taken out
                                        //back into the original stacks
                                        while(!tempStack.isEmpty())
                                        {
                                            blockStack.push(tempStack.pop());
                                        } 

                                        //We are granteeded that the syntax is correct
                                        //therefore it won't be nullpointer error
                                        varValue=target.getInitialValue();
                                    }
                                }
                                
                                toBeAdded.setName(varName);
                                toBeAdded.setInitialValue(varValue);
                                
                                //We have to increment the workingTemp to show that
                                //we have handle the last variable declaration
                                workingTemp=workingTemp.substring(semiIndex+1);
                            }
                            //Means that there is more declaration to handle with
                            //no int indicator
                            else
                            {
                                //This means that the next variable have a value
                                //being assigned to it
                                if(equalIndex<commaIndex&&equalIndex!=-1)
                                {
                                    //This gets the name of the variable
                                    for(int i=0;i<equalIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                    
                                    //This gets the value of the variable
                                    for(int i=equalIndex+1;i<commaIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            strVarValue+=workingTemp.charAt(i);
                                        }
                                    }
                                }
                                //This means that it doesn't have a value assigned to it
                                else
                                {
                                    //This gets the name of the variable
                                    for(int i=0;i<commaIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                }
                                
                                int varValue=0;

                                //This means that strVarValue gathered actual data
                                if(!strVarValue.isEmpty())
                                {
                                    //This try to parse the data into int
                                    try
                                    {
                                        varValue=Integer.parseInt(strVarValue);
                                    }
                                    //This means that the value used is an valid
                                    //variable therefore we must look through the 
                                    //stacks to find the approatie value
                                    catch(Exception e)
                                    {
                                        
                                        //Makes a temporary stack to put our
                                        //blocks into
                                        Stack<Block> tempStack=new Stack<Block>();

                                        //This variable determines whether or not
                                        //we have found the target
                                        boolean targetFound=false;

                                        Variable target=new Variable();

                                        while(!blockStack.isEmpty()&&!targetFound)
                                        {
                                            //We popped the top of the blockStack
                                            Block poppedBlock=blockStack.pop();

                                            //And push it into tempStack
                                            tempStack.push(poppedBlock);

                                            //findVariable method tries to find it in the
                                            //poppedBlock
                                            target=poppedBlock.findVariable(strVarValue);

                                            //This means that the target is found thus
                                            //we set targetFound to true to exit the loop
                                            if(target!=null)
                                            {
                                                targetFound=true;
                                            }
                                        }

                                        //This pushes all the blocks that is taken out
                                        //back into the original stacks
                                        while(!tempStack.isEmpty())
                                        {
                                            blockStack.push(tempStack.pop());
                                        } 

                                        //We are granteeded that the syntax is correct
                                        //therefore it won't be nullpointer error
                                        varValue=target.getInitialValue();
                                    }
                                }
                                
                                toBeAdded.setName(varName);
                                toBeAdded.setInitialValue(varValue);
                                
                                //We have update the workingTemp to show that
                                //we have handle this variable declaration already
                                //and move to the next
                                workingTemp=workingTemp.substring(commaIndex+1);
                                
                                //Before exiting we check whether the next variable
                                //is the last one or not
                                if(workingTemp.indexOf(",")>workingTemp.indexOf(";"))
                                {
                                    lastVariable=true;
                                }
                            }
                            
                            //Finally this adds the variable into the corresponding
                            //block
                            try
                            {
                                blockStack.peek().addElement(toBeAdded);
                            }
                            catch(FullBlockException f)
                            {
                                System.out.println(f);
                            }
                        }
                        //This means that we have encountered a printing directive
                        else if(printIndex!=-1)
                        {
                            //That means the directive read is actually valid
                            //thus we need to collect what it want us to print
                            if(directEnd!=-1)
                            {
                                //This variable will be collecting what we should be printing
                                String directVar="";
                                
                                //Collecting the variable name up until the */ line
                                for(int i=printIndex+9;i<directEnd;i++)
                                {
                                    directVar+=workingTemp.charAt(i);
                                }
                                
                                //This means that the printing directive tell us to print
                                //all of the local variable inside the block
                                if(directVar.equals("LOCAL"))
                                {
                                    //We call the formatVariable to get the neatly formatted
                                    //String that contains all of the variable's information
                                    String output=blockStack.peek().formatVariable();
                                    
                                    String formatString="%-25s%-25s";
                                    
                                    //If output is empty then that means that there
                                    //is no local variable to print
                                    if(output.isEmpty())
                                    {
                                        System.out.println("No local variables to print.\n");
                                    }
                                    //This means that there is variables to print
                                    else
                                    {
                                        System.out.println(String.format(formatString, "Variable Name","Initial Value"));

                                        System.out.println(output);
                                    }
                                }
                                //This means that the printing directive want to print
                                //a specific variable therefore we have to look through
                                //all of the blocks to see if we can actually find the
                                //variable that it want to print
                                else
                                {   
                                    //Creates a temporary stack to hold our block that we
                                    //pop off
                                    Stack<Block> tempStack=new Stack<Block>();
                                    
                                    //This boolean will be reperesenting whether or not
                                    //we have found the variable
                                    boolean targetFound=false;
                                    
                                    //This while loop will continue until target is found
                                    //or blockStack is empty
                                    while(!blockStack.isEmpty()&&!targetFound)
                                    {
                                        //This pop off the block
                                        Block poppedBlock=blockStack.pop();
                                        
                                        //And push it into the temporary stack
                                        tempStack.push(poppedBlock);
                                        
                                        //Then we call the findVariable method on the
                                        //poppedBlock to see if we can find the variable inside
                                        Variable target=poppedBlock.findVariable(directVar);
                                        
                                        String formatString="%-25s%-25s";
                                        
                                        //This means that we did find the specific
                                        //target inside the block therefore we
                                        //print the information that it has
                                        //and update targetFound to be true to stop the
                                        //while loop
                                        if(target!=null)
                                        {
                                            String varName=target.getName();
                                            int varValue=target.getInitialValue();
                                            
                                            System.out.println(String.format(formatString, "Variable Name","Initial Value"));
                                            System.out.println(String.format(formatString, varName,varValue)+"\n");
                                            
                                            targetFound=true;
                                        }
                                    }
                                    
                                    //Then after while loop if targetFound become true
                                    //that means it have found the target,but if it
                                    //is false that means it is not found which means
                                    //we need to print a corresponding message
                                    if(!targetFound)
                                    {
                                        System.out.println("Variable not found: "+directVar+"\n");
                                    }
                                    
                                    //Pushes everything from the tempStack back into
                                    //the original stack
                                    while(!tempStack.isEmpty())
                                    {
                                        blockStack.push(tempStack.pop());
                                    }
                                }
                            }
                            
                            //We must update workingTemp to reflect that we have
                            //handle the case of it having print statement correct
                            workingTemp=workingTemp.substring(directEnd+2);
                        }
                        //This means that we have encountered a right bracket
                        //therefore we have to pop off the block from the stack
                        else if(containRightBracket)
                        {
                            blockStack.pop();
                            
                            //Then we have to also update workingTemp as well
                            //to cut off the right bracket have handled
                            workingTemp=workingTemp.substring(rightBracketIndex+1);
                        }
                        //This means that the line is non-sense or it is not revelant
                        //we just set haveString to false and move to the next line
                        else
                        {
                            haveString=false;
                        }

                        //This counter will be counting the leftover spaces in the
                        //workingTemp
                        int whiteSpaceCounter=0;

                        for(int i=0;i<workingTemp.length();i++)
                        {
                            if(workingTemp.charAt(i)==' ')
                            {
                                whiteSpaceCounter++;
                            }
                        }

                        //This means that if the white space are equal to the 
                        //workingTemp's length, it is all spaces therefore we just
                        //move on to the next line
                        if(whiteSpaceCounter==workingTemp.length())
                        {
                            haveString=false;
                        }
                    }
                    
                    //System.out.println(blockStack);
                    //This moves to the next line
                    eachLine=reader.readLine();
                }
            }
            //This means that the file is not found
            catch(FileNotFoundException f)
            {
                System.out.println(f);
            }
            //This means a error in reading the file
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
        //This means that the user didn't enter a file name, therefore we must
        //show a proper message to the user
        else
        {
            System.out.println("You have enetered an empty file name to read, please"
                    + " try again with an valid file name.");
        }
    }
}