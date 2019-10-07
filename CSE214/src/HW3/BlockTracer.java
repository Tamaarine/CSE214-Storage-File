package HW3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BlockTracer
{
    public static void main(String [] args)
    {
        Stack<Block> blockStack=new Stack<Block>();
        int blockCount=1;
        
        InputStreamReader userInput=new InputStreamReader(System.in);
        BufferedReader userInputReader=new BufferedReader(userInput);
        
        String userInputString="";
        
        /*
        int j               =                5              ; 
        
        int k, b=69 ,      o=123;
        
        int c=69;
        
        int g=49, a=123, p=420;
       
        
        System.out.println(j);
        */
        
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
                        
                        boolean commaBeforeSemi=commaIndex<semiIndex;
                        
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
                            blockStack.add(new Block(blockCount));
                            
                            blockCount++;
                            
                            workingTemp=workingTemp.substring(leftBracketIndex+1);
                            
                        }
                        else if((intIndex!=-1&&semiIndex!=-1&&pureInt)||declarationMode)
                        {
                            String varName="";
                            String strVarValue="";
                            
                            if(commaBeforeSemi)
                            {
                                //This means that this is the beginning of a new
                                //variable declarSation and there is no comma in it
                                //just one variable
                                if(intIndex!=-1&&commaIndex==-1)
                                {
                                    //This means that there is a equal sign for the
                                    //value of the variable, therefore we need to
                                    //also collect the value of the variable
                                    if(equalIndex!=-1)
                                    {
                                        //This collects the variable name up to
                                        //the equal sign
                                        for(int i=intIndex+4;i<equalIndex;i++)
                                        {
                                            if(workingTemp.charAt(i)!=' ')
                                            {
                                                varName+=workingTemp.charAt(i);
                                            }
                                        }

                                        //This collects the value of the variable
                                        //up until the semi colon because there is no
                                        //comma
                                        for(int i=equalIndex+1;i<semiIndex;i++)
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

                                        //We update the working string to represent
                                        //we have handle that portion of the variable
                                        workingTemp=workingTemp.substring(semiIndex+1);
                                    }
                                    //This means that there is no equal sign in this line of declaration
                                    //thus we just need to collect the variable's name 
                                    //up until the semi colon
                                    else
                                    {
                                        for(int i=intIndex+4;i<semiIndex;i++)
                                        {
                                            if(workingTemp.charAt(i)!=' ')
                                            {
                                                varName+=workingTemp.charAt(i);
                                            }
                                        }

                                        toBeAdded.setName(varName);
                                        toBeAdded.setInitialValue(0);

                                        //We need to update the working string
                                        //to show that we have finish the portion of the
                                        //variable
                                        workingTemp=workingTemp.substring(semiIndex+1);
                                    }
                                }
                                //This means that this is the start of a new declaration
                                //with comma following it
                                else if(intIndex!=-1&&commaIndex!=-1)
                                {
                                    declarationMode=true;

                                    //This means that the first variable declaration
                                    //have the equal sign therefore we proceed normally
                                    if(equalIndex<commaIndex)
                                    {
                                        //This means that there is a equal symbol, which means
                                        //we need to also collect the variable's value as well
                                        if(equalIndex!=-1)
                                        {
                                            for(int i=intIndex+4;i<equalIndex;i++)
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
                                        //This means that there is no equal sign thus
                                        //we just need to collect the variable's name
                                        else
                                        {
                                            for(int i=intIndex+4;i<commaIndex;i++)
                                            {
                                                if(workingTemp.charAt(i)!=' ')
                                                {
                                                    varName+=workingTemp.charAt(i);
                                                }
                                            }

                                            strVarValue="0";
                                        }
                                    }
                                    //This means that however, the equal sign is not
                                    //with the variable it is some other variable's
                                    //equal sign 
                                    else
                                    {
                                        for(int i=intIndex+4;i<commaIndex;i++)
                                        {
                                            if(workingTemp.charAt(i)!=' ')
                                            {
                                                varName+=workingTemp.charAt(i);
                                            }
                                        }

                                        strVarValue="0";
                                    }

                                    int varValue=0;

                                    if(!strVarValue.isEmpty())
                                    {
                                        varValue=Integer.parseInt(strVarValue);
                                    }

                                    toBeAdded.setName(varName);
                                    toBeAdded.setInitialValue(varValue);

                                    //After we have completed this we must update the
                                    //wokringTemp to show that we have completed this 
                                    //first time of int declaration with commas
                                    //after it
                                    workingTemp=workingTemp.substring(commaIndex+1);
                                }
                                //This is the continuation of the declaration after
                                //the previous case
                                else if(commaIndex!=-1)
                                {
                                    //However, since we have already truncated down the
                                    //first part of the declaration, we just have to
                                    //start at index of 0 and up until the indicator
                                    if(equalIndex!=-1)
                                    {
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
                                    //This means that there is no equal sign thus
                                    //we just need to collect the variable's name
                                    else
                                    {
                                        for(int i=0;i<commaIndex;i++)
                                        {
                                            if(workingTemp.charAt(i)!=' ')
                                            {
                                                varName+=workingTemp.charAt(i);
                                            }
                                        }

                                        strVarValue="0";
                                    }

                                    int varValue=Integer.parseInt(strVarValue);

                                    if(!strVarValue.isEmpty())
                                    {
                                        varValue=Integer.parseInt(strVarValue);
                                    }

                                    toBeAdded.setName(varName);
                                    toBeAdded.setInitialValue(varValue);

                                    //After we have completed this we must update the
                                    //wokringTemp to show that we have completed this 
                                    //first time of int declaration with commas
                                    //after it
                                    workingTemp=workingTemp.substring(commaIndex+1);
                                }
                                //This then means that we have reached the end of the cycle
                                //no more commas, thus declaration mode is turn off
                                else
                                {
                                    declarationMode=false;

                                    //This means that with the last case which is when
                                    //there is only one variable declaration left and
                                    //there is an equal sign which means we need to get
                                    //the variable's value as well
                                    if(equalIndex!=-1)
                                    {
                                        for(int i=0;i<equalIndex;i++)
                                        {
                                            if(workingTemp.charAt(i)!=' ')
                                            {
                                                varName+=workingTemp.charAt(i);
                                            }
                                        }

                                        for(int i=equalIndex+1;i<semiIndex;i++)
                                        {
                                            if(workingTemp.charAt(i)!=' ')
                                            {
                                                strVarValue+=workingTemp.charAt(i);
                                            }
                                        }
                                    }
                                    //Then this means that there is no equal value
                                    //thus we only need to collect the variable's name
                                    else
                                    {
                                        for(int i=0;i<semiIndex;i++)
                                        {
                                            if(workingTemp.charAt(i)!=' ')
                                            {
                                                varName+=workingTemp.charAt(i);
                                            }
                                        }

                                        strVarValue="0";
                                    }

                                    int varValue=0;

                                    if(!strVarValue.isEmpty())
                                    {
                                        varValue=Integer.parseInt(strVarValue);
                                    }

                                    toBeAdded.setName(varName);
                                    toBeAdded.setInitialValue(varValue);

                                    //As well we need to update the working string
                                    //after we finish the last case
                                    workingTemp=workingTemp.substring(semiIndex+1);
                                }
                            }
                            //This means that there are semi colon after the first
                            //declaration
                            else
                            {
                                if(equalIndex!=-1)
                                {
                                    if(intIndex!=-1)
                                    {
                                        for(int i=intIndex+4;i<equalIndex;i++)
                                        {
                                            if(workingTemp.charAt(i)!=' ')
                                            {
                                                varName+=workingTemp.charAt(i);
                                            }
                                        }
                                    }
                                    else
                                    {
                                        for(int i=0;i<equalIndex;i++)
                                        {
                                            if(workingTemp.charAt(i)!=' ')
                                            {
                                                varName+=workingTemp.charAt(i);
                                            }
                                        }
                                    }
                                    
                                    for(int i=equalIndex+1;i<semiIndex;i++)
                                    {
                                        if(workingTemp.charAt(i)!=' ')
                                        {
                                            strVarValue+=workingTemp.charAt(i);
                                        }
                                    }
                                }
                                else
                                {
                                    if(intIndex!=-1)
                                    {
                                        for(int i=intIndex+4;i<semiIndex;i++)
                                        {
                                            if(workingTemp.charAt(i)!=' ')
                                            {
                                                varName+=workingTemp.charAt(i);
                                            }
                                        }
                                    }
                                    else
                                    {
                                        for(int i=0;i<semiIndex;i++)
                                        {
                                            if(workingTemp.charAt(i)!=' ')
                                            {
                                                varName+=workingTemp.charAt(i);
                                            }
                                        }
                                    }
                                    
                                    declarationMode=false;

                                    strVarValue="0";
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
                        else if(printIndex!=-1)
                        {
                            declarationMode=false;
                            
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