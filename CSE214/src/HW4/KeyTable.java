package HW4;

/**
 * 
 * 
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class KeyTable
{
    //Instance variables
    private char key[][];
    static final int WIDTH=5;
    static final int HEIGHT=5;
    
    //ASCII value is from 65 A to 90 Z
    
    public KeyTable()
    {
        key=new char[HEIGHT][WIDTH];
    }
    
    //This method will be building a new KeyTable using a String
    //The rule is any duplicate letters are removed
    //Spaces are removed
    //And the remaining spaces up until 25 letters are filled with the left over
    //unused letters in alphabetical order, don't use J
    public static KeyTable buildFromString(String keyphrase) throws IllegalArgumentException
    {
        KeyTable output=new KeyTable();
        
        char outputKey[][]=new char[HEIGHT][WIDTH];
        
        //This means that the keyphrase String is a null, therefre we need to
        //tell the user that it is a null String
        if(keyphrase==null)
        {
            throw new IllegalArgumentException("keyphrase for building table is null");
        }
        
        //Before we go on with the making of a key we have to convert keyphrase into
        //all upper case. We create a new String that is a replica of keyphrase
        //so we work with workTemp instead of keyphrase
        String workTemp=keyphrase.toUpperCase();
        
        //We need to also remove white spaces from workTemp
        workTemp=workTemp.replaceAll("\\s","");
        
        //We also need to remove the "J" letter
        workTemp=workTemp.replaceAll("J","");
        
        //This String will be holding all the letters from workTemp to put inside
        //the KeyTable object
        String toPutInTable="";
        
        //After removing spaces if the workTemp consist of no character but just
        //an empty String then we set a default key table value to it
        if(workTemp.isEmpty())
        {
            
        }
        //This means that the workTemp is not empty and have letters to generate
        //for the key table
        else
        {
            for(int i=0;i<workTemp.length();i++)
            {
                char charI=workTemp.charAt(i);
                
                //This is to make sure that the letter we are looking at is a
                //alphabetic characters, we don't pick it up if it is a
                //symbol or non-alphabetic character
                if(charI>=65&&charI<=90)
                {
                    //This means that the letter we are looking at is the first letter
                    //therefore we have to append to toPutInTable because it is the first
                    //letter
                    if(i==0)
                    {
                        toPutInTable+=workTemp.charAt(i);
                    }

                    //This variable will be responsible to check whether or not there is a
                    //duplicate value of the letter at i, it will be false at first
                    //until it is proven to be true in the inner for loop
                    boolean duplicate=false;

                    //Then when we hit this for loop we are gurantee that it is not the
                    //first letter of the workTemp we have to compare to all of the previous
                    //letter before workTemp to see if there is any duplicates
                    for(int j=0;j<i;j++)
                    {
                        //This means there is a duplicate
                        if(workTemp.charAt(j)==workTemp.charAt(i))
                        {
                            duplicate=true;
                        }

                    }

                    //This means that if we are outside of the inner for loop
                    //we have done looking through the String up until i
                    //if duplicate is true, then we don't add that letter at i
                    //if it is false then we add it to toPutInTable because it is not
                    //a duplciate letter
                    if(!duplicate&&i!=0)
                    {
                        toPutInTable+=workTemp.charAt(i);
                    }
                }
            }
        }
        //System.out.println(toPutInTable);
        
        //If we are outside then that means toPutInTable have been capacitlized
        //removed white spaces, and removed duplicate letters. The next step is to
        //fill in the remaining spaces with the remaining letters
        for(int i=65;i<=90;i++)
        {
            //tempChar represent all of the 26 alphabetic letters
            char tempChar=(char)i;
            
            //This will make sure we don't add in J to the key table
            if(tempChar!='J')
            {
                //This means that the letter is not used in workTemp therefore we
                //add into workTemp
                if(toPutInTable.indexOf(tempChar)==-1)
                {
                    toPutInTable+=tempChar;
                }
            }
        }
        
        int strIndex=0;
        
        //Then we just add everything into the outputKey
        for(int r=0;r<HEIGHT;r++)
        {
            for(int c=0;c<WIDTH;c++)
            {
                outputKey[r][c]=toPutInTable.charAt(strIndex);
                
                strIndex++;
            }
        }
        
        output.setKey(outputKey);
        
        System.out.println("Generation success!\n");
        
        return output;
    }
    
    public char[][] getKeyTable()
    {
        return key;
    }
    
    public void setKey(char givenKey[][])
    {
        key=givenKey;
    }
    
    public int findRow(char c)
    {
        //This is the output of the method, we assume it is at row of 0
        //until we prove it later in the nested for loop
        int output=0;
        
        for(int row=0;row<HEIGHT;row++)
        {
            for(int col=0;col<WIDTH;col++)
            {
                //This means that the character in the matrix is found
                //therefore we just set output to be the row
                if(key[row][col]==c)
                {
                    output=row;
                    
                    //We break it out to save time
                    break;
                }
            }
        }
        
        return output;
    }
    
    public int findCol(char c)
    {
        //This is the output of the method, we assume it is at col of 0
        //until we prove it later in the nested for loop
        int output=0;
        
        for(int row=0;row<HEIGHT;row++)
        {
            for(int col=0;col<WIDTH;col++)
            {
                //This means that the character in the matrix is found at the
                //row and col therefore we just set output to be the col's value
                if(key[row][col]==c)
                {
                    output=col;
                    
                    //We break out to save time
                    break;
                }
            }
        }
        
        return output;
    }
    
    public String toString()
    {
        String output="";
        
        for(int r=0;r<HEIGHT;r++)
        {
            for(int c=0;c<WIDTH;c++)
            {
                output=output+key[r][c]+" "; 
            }
            
            output=output+"\n";
        }
        
        return output;
    }
    
    
}


