package HW4;

/**
 * The KeyTable class represents key to a Playfair Cipher, it is a 5 by 5 matrix
 * that contains the set of a set of key. The KeyTable is used both for encrypting
 * and decrypting the data
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class KeyTable
{
    //The key 2-D array represent the 5 by 5 martrix to store the keys in
    private char key[][];
    
    //WIDTH is a static final variable to represent the overall width of the key
    //martrix
    static final int WIDTH=5;
    
    //HEIGHT is a static final variable to represent the overall height of the key
    //martrix
    static final int HEIGHT=5;
    
    /**
     * Default constructor of the KeyTable class, it just establishes a key matrix
     * with the size of HEIGHT and WIDTH and setting default value inside the matrix 
     */
    public KeyTable()
    {
        key=new char[HEIGHT][WIDTH];
    }
    
    /**
     * This method builds a new KeyTable object from the provided string and returns it.
     * The rule is that any duplicate letters are removed, spaces are removed, any
     * non-alphabetical chars are removed
     * <p>
     * Precondition: keyphrase is not null
     * 
     * @param keyphrase
     *  The String to use as the key for the KeyTable
     * @return Returns the new KeyTable object formed by using the keyphrase
     * @throws IllegalArgumentException 
     *  Throws IllegalArgumentException if keyphrase is null
     */
    public static KeyTable buildFromString(String keyphrase) throws IllegalArgumentException
    {
        //This is our output KeyTable object that we will be storing the new keys in
        KeyTable output=new KeyTable();
        
        //outputKey represent the 5x5 key matrix that we will be giving to output
        //after we finished filling in the correct items using the rule for making a
        //key
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
        //an empty String we don't have to do anything because it will be later on
        //filled by the left over letters
        if(workTemp.isEmpty())
        {
            //Intentionally left blank
        }
        //This means that the workTemp is not empty and have letters to generate
        //for the key table
        else
        {
            //This for loop will be gathering all of the valid letters to be put inside
            //outputKey, the valid letters will be put together in toPutInTable String
            //that later on we just have to iterate through toPutInTable and put every
            //character into outputKey without worrying about anything
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
                        //This means there is a duplicate therefore we set duplciate to true
                        //to signal to not put into toPutInTable
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
        
        //We need a counter variable outside to represent the letter we are at currently
        //in toPutInTable because we are going to fill in the outputKey with all of the
        //letters in toPutInTable
        int strIndex=0;
        
        //Then we just add everything into the outputKey
        for(int r=0;r<HEIGHT;r++)
        {
            for(int c=0;c<WIDTH;c++)
            {
                //Filling in the letter
                outputKey[r][c]=toPutInTable.charAt(strIndex);
                
                //Increment strIndex so we can move on to the next, we don't need to
                //check if we are out of bound because toPutInTable have for sure 25 letters
                strIndex++;
            }
        }
        
        //We set the output KeyTable's key to outputKey and finally return it
        output.setKey(outputKey);
        
        System.out.println("Generation success!\n");
        
        return output;
    }
    
    /**
     * Returns the 2-D dimension array that holds the information of the Key
     * 
     * @return Returns key that represent the key for encryption and decryption
     */
    public char[][] getKeyTable()
    {
        return key;
    }
    
    /**
     * Mutator method for the key
     * <p>
     * Postcondition: The key is set to givenKey
     * 
     * @param givenKey 
     *  The given key to set the key into
     */
    public void setKey(char givenKey[][])
    {
        key=givenKey;
    }
    
    /**
     * Returns the row in which c occurs
     * <p>
     * Precondition: c is a valid letter
     * 
     * @param c
     *  The character to locate within the key matrix
     * @return Returns the index of the row in which c occurs
     */
    public int findRow(char c)
    {
        //This is the output of the method, we assume it is at row of 0
        //until we prove it later in the nested for loop
        int output=0;
        
        //The nested for loop go through the entire matrix to look for c
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
    
    /**
     * Return the column in which c occurs
     * <p>
     * Precondition: c is a valid letter
     * 
     * @param c
     *  The character to locate within the key matrix
     * @return The index of the column in which c occurs
     */
    public int findCol(char c)
    {
        //This is the output of the method, we assume it is at col of 0
        //until we prove it later in the nested for loop
        int output=0;
        
        //The nested for loop go through the entire matrix to look for c
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
    
    /**
     * The toString method returns a 5x5 matrix that shows the key
     * 
     * @return Returns a String that shows the 5x5 key matrix
     */
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


