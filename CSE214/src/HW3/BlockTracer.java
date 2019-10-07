package HW3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class BlockTracer
{
    
    public static void main(String [] args)
    {
        Stack<Block> blockStack=new Stack<Block>();
        int blockCount=0;
        
        InputStreamReader userInput=new InputStreamReader(System.in);
        BufferedReader userInputReader=new BufferedReader(userInput);
        
        String userInputString="";
        
        int j               =                5              ; 
        
        int k, b=69 ,      o=123;
        
        int c=69;
        
        int g=49, a=123, p=420;
        
        System.out.println(j);
        
        
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
                
                String oneLine=reader.readLine();
                
                //This keeps reading the file until there is no more line to read
                //from the file
                while(oneLine!=null)
                {
                    int intIndex=oneLine.indexOf("int ");
                    int semiIndex=oneLine.indexOf(";");
                    int equalIndex=oneLine.indexOf("=");
                    boolean containLeftBracket=oneLine.contains("{");
                    boolean containRightBracket=oneLine.contains("}");
                    boolean containPrint=oneLine.contains("/*$print");
                    
                    //This means to start a new line of block because we encountered
                    //a brackaet
                    if(containLeftBracket)
                    {
                        blockCount++;
                        
                        System.out.println("This is added successfully");
                        blockStack.add(new Block(blockCount));
                    }
                    //This means that there is a int variable declaration in this
                    //line which means we need to add the variable into the
                    //block that we are in currently, which is the top of the
                    //stack
                    else if(intIndex!=-1)
                    {
                        //We have to look into this line even further because
                        //it might contain multiple variable declarations
                        //The first priority is to look for commas
                        int commaIndex=oneLine.indexOf(",");
                        
                        Variable toBeAdded=new Variable();
                        
                        //This makes sure that the semi colon is in the
                        //declaration to make it a valid declaration
                        if(semiIndex!=-1)
                        {
                            //This means that the declaration line consists of at least
                            //one comma, therefore the semi colon at the end will be
                            //the cutting line
                            if(commaIndex!=-1)
                            {

                            }
                            //This means that there is only one variable in this declaration
                            //line
                            else
                            {
                                String varName="";
                                String strVarValue="";
                                
                                //This means that there is an value that is
                                //assigned to the variable
                                if(equalIndex!=-1)
                                {
                                    //This for loop collects the variable's name
                                    for(int i=intIndex+4;i<equalIndex;i++)
                                    {
                                        if(oneLine.charAt(i)!=' ')
                                        {
                                            varName+=oneLine.charAt(i);
                                        }
                                    }
                                    
                                    //This for loop collects the variable's value
                                    for(int i=equalIndex+1;i<semiIndex;i++)
                                    {
                                        if(oneLine.charAt(i)!=' ')
                                        {
                                            strVarValue+=oneLine.charAt(i);
                                        }
                                    }
                                    
                                    int varValue=Integer.parseInt(strVarValue);
                                    
                                    toBeAdded.setInitialValue(varValue);
                                    toBeAdded.setName(varName);
                                }
                                //This means that there is no vaule assigned to
                                //this variable therefore we set it default as 0
                                else
                                {
                                    //This for loop is collecting the variable's name
                                    for(int i=intIndex+4;i<semiIndex;i++)
                                    {
                                        if(oneLine.charAt(i)!=' ')
                                        {
                                            varName+=oneLine.charAt(i);
                                        }
                                    }

                                    toBeAdded.setName(varName);
                                    toBeAdded.setInitialValue(0);
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
                        }
                        
                        //smallerOneLine=oneLine.substring(oneLine.indexOf("int ")+4,commaIndex)
                    }
                    else if(containRightBracket)
                    {
                        blockStack.pop();
                    }
                    else if(containPrint)
                    {
                        int directEnd=oneLine.indexOf("*/");
                        
                        if(directEnd!=-1)
                        {
                            String toPrintString="";
                            
                            for(int i=)
                        }
                        
                        String blockFormattedPrint=blockStack.peek().formatVariable();
                        
                        System.out.println(blockFormattedPrint);
                        System.out.println("This happened bro");
                    
                    }
                    
                    
                    
                    
                    
                    
                    oneLine=reader.readLine();
                    
                    System.out.println(blockStack);
                    
                }
                
                
            }
            catch(IOException i)
            {
                System.out.println("Error when reading the files");
            }
            
            
            
            
            
            
        }
        else
        {
            System.out.println("You have entered an empty file name to read");
        }
        
        
        
        
        
        
        
        
        
        
    }
    
}