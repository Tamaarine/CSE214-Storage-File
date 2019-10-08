package HW3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * 
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class BlockTracer
{
    public static void main(String [] args)
    {
        Stack<Block> blockStack=new Stack<Block>();
        
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
        
        if(!userInputString.isEmpty())
        {
            try
            {
                FileInputStream fileInput=new FileInputStream(userInputString);
                
                InputStreamReader inputStream=new InputStreamReader(fileInput);
                BufferedReader reader=new BufferedReader(inputStream);
                
                String eachLine=reader.readLine();
                
                
                while(eachLine!=null)
                {
                    String workingTemp=eachLine;
                    
                    boolean haveString=false;
                    
                    for(int i=0;i<workingTemp.length();i++)
                    {
                        if(workingTemp.charAt(i)!=' ')
                        {
                            haveString=true;
                            
                            break;
                        }
                    }
                    
                    boolean declarationMode=false;
                    boolean lastVariable=false;
                    
                    while(haveString)
                    {
                        Variable toBeAdded=new Variable();
                        
                        int intIndex=workingTemp.indexOf("int ");
                        int semiIndex=workingTemp.indexOf(";");
                        int equalIndex=workingTemp.indexOf("=");
                        int commaIndex=workingTemp.indexOf(",");
                        int leftBracketIndex=workingTemp.indexOf("{");
                        int rightBracketIndex=workingTemp.indexOf("}");
                        int printIndex=workingTemp.indexOf("/*$print");
                        int directEnd=workingTemp.indexOf("*/");
                        
                        boolean pureInt=true;
                        String zeroToInt="";
                        
                        if(commaIndex==-1)
                        {
                            lastVariable=true;
                        }
                        
                        if(intIndex!=-1)
                        {
                            zeroToInt=workingTemp.substring(0,intIndex);
                        }
                        
                        if(zeroToInt.contains("/*$pr"))
                        {
                            pureInt=false;
                        }
                        
                        boolean containLeftBracket=workingTemp.contains("{");
                        boolean containRightBracket=workingTemp.contains("}");

                        if(containLeftBracket)
                        {
                            blockStack.add(new Block());
                            
                            workingTemp=workingTemp.substring(leftBracketIndex+1);
                            
                        }
                        else if(intIndex!=-1&&semiIndex!=-1&&pureInt)
                        {
                            String varName="";
                            String strVarValue="";
                            
                            //This means that there is multiple variable declaration
                            //which mean we need to set declarationMdoe to true 
                            //because there will be more variables coming but without
                            //the int indicator
                            if(commaIndex<semiIndex&&commaIndex!=-1)
                            {
                                declarationMode=true;
                                
                                //We have to also make sure that the equal sign is with the variable
                                //that we are dealing with
                                if(equalIndex<commaIndex&&equalIndex!=-1)
                                {
                                    //This gets the name
                                    for(int i=intIndex+4;i<equalIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                    //This gets the value
                                    for(int i=equalIndex+1;i<commaIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            strVarValue+=workingTemp.charAt(i);
                                        }
                                    }
                                    
                                    int varValue=0;
                                    
                                    if(!strVarValue.isEmpty())
                                    {
                                        varValue=Integer.parseInt(strVarValue);
                                    }

                                    toBeAdded.setName(varName);
                                    toBeAdded.setInitialValue(varValue);

                                    workingTemp=workingTemp.substring(commaIndex+1);
                                    
                                }
                                //No equal sign with the value thus default value
                                //we just need to get the name
                                else
                                {
                                    //This gets the name
                                    for(int i=intIndex+4;i<commaIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                    
                                    int varValue=0;
                                    
                                    if(!strVarValue.isEmpty())
                                    {
                                        varValue=Integer.parseInt(strVarValue);
                                    }

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
                                    //This gets the name
                                    for(int i=intIndex+4;i<equalIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                    
                                    //This gets the value
                                    for(int i=equalIndex+1;i<semiIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            strVarValue+=workingTemp.charAt(i);
                                        }
                                    }
                                }
                                //This means that set the variable to default value
                                else
                                {
                                    //This gets the name
                                    for(int i=intIndex+4;i<semiIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                }
                                
                                int varValue=0;
                                    
                                if(!strVarValue.isEmpty())
                                {
                                    varValue=Integer.parseInt(strVarValue);
                                }

                                toBeAdded.setName(varName);
                                toBeAdded.setInitialValue(varValue);

                                workingTemp=workingTemp.substring(semiIndex+1);
                            }
                                
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
                                    //This gets the name
                                    for(int i=0;i<equalIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                    
                                    //This gets the value
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
                                    //This gets the name
                                    for(int i=0;i<semiIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                }
                                
                                int varValue=0;
                                    
                                if(!strVarValue.isEmpty())
                                {
                                    varValue=Integer.parseInt(strVarValue);
                                }
                                
                                toBeAdded.setName(varName);
                                toBeAdded.setInitialValue(varValue);
                                
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
                                    //This gets the name
                                    for(int i=0;i<equalIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                    
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
                                    //This gets the name
                                    for(int i=0;i<commaIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            varName+=workingTemp.charAt(i);
                                        }
                                    }
                                    
                                    
                                }
                                
                                int varValue=0;

                                if(!strVarValue.isEmpty())
                                {
                                    varValue=Integer.parseInt(strVarValue);
                                }
                                
                                toBeAdded.setName(varName);
                                toBeAdded.setInitialValue(varValue);
                                
                                workingTemp=workingTemp.substring(commaIndex+1);
                                
                                if(workingTemp.indexOf(",")==-1)
                                {
                                    lastVariable=true;
                                }
                            }
                            
                            try
                            {
                                blockStack.peek().addElement(toBeAdded);
                            }
                            catch(FullBlockException f)
                            {
                                System.out.println(f);
                            }

                        }
                        else if(printIndex!=-1)
                        {
                            //That means the directive read is actually valid
                            //thus we need to collect what it want us to print
                            if(directEnd!=-1)
                            {
                                String directVar="";
                                
                                for(int i=printIndex+9;i<directEnd;i++)
                                {
                                    directVar+=workingTemp.charAt(i);
                                }
                                
                                if(directVar.equals("LOCAL"))
                                {
                                    String output=blockStack.peek().formatVariable();
                                    
                                    String formatString="%-25s%-25s";
                                    
                                    if(output.isEmpty())
                                    {
                                        System.out.println("No local variables to print.\n");
                                    }
                                    else
                                    {
                                        System.out.println(String.format(formatString, "Variable Name","Initial Value"));

                                        System.out.println(output);
                                    }
                                }
                                else
                                {
                                    Stack<Block> tempStack=new Stack<Block>();
                                    boolean targetFound=false;
                                    
                                    while(!blockStack.isEmpty()&&!targetFound)
                                    {
                                        Block poppedBlock=blockStack.pop();
                                        
                                        tempStack.push(poppedBlock);
                                        
                                        Variable target=poppedBlock.findVariable(directVar);
                                        
                                        String formatString="%-25s%-25s";
                                        
                                        if(target!=null)
                                        {
                                            String varName=target.getName();
                                            int varValue=target.getInitialValue();
                                            
                                            System.out.println(String.format(formatString, "Variable Name","Initial Value"));
                                            System.out.println(String.format(formatString, varName,varValue)+"\n");
                                            
                                            targetFound=true;
                                        }
                                    }
                                    
                                    if(!targetFound)
                                    {
                                        System.out.println("Variable not found: "+directVar+"\n");
                                    }
                                    
                                    while(!tempStack.isEmpty())
                                    {
                                        blockStack.push(tempStack.pop());
                                    }
                                }
                                
                            }
                            
                            workingTemp=workingTemp.substring(directEnd+2);
                        }
                        else if(containRightBracket)
                        {
                            blockStack.pop();
                            
                            workingTemp=workingTemp.substring(rightBracketIndex+1);
                        }
                        else
                        {
                            haveString=false;
                        }

                        int whiteSpaceCounter=0;

                        for(int i=0;i<workingTemp.length();i++)
                        {
                            if(workingTemp.charAt(i)==' ')
                            {
                                whiteSpaceCounter++;
                            }
                        }

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
            catch(FileNotFoundException f)
            {
                System.out.println(f);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
        //This means that the user didn't enter a file name
        else
        {
            System.out.println("You have enetered an empty file name to read, please"
                    + " try again with an valid file name.");
        }
        
        
        
        
    }
}