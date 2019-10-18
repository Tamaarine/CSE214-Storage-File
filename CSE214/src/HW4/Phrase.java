package HW4;

import java.util.ArrayList;

/**
 * The Phrase class is a wrapper class for the queue, in other words it implements it
 * and becomes a Queue data structure object, it will have the characteristics of a 
 * Queue, being that it will dequeue and enqueue items. Instead of containing a ArrayList
 * the Phrase extends the ArrayList API to make itself into a ArrayList therefore
 * we don't need to write isEmpty and size method from the queue class because it is
 * inherited from the ArrayList class.
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class Phrase extends ArrayList implements Queue
{
    /**
     * Default constructor of the Phrase class, it calss the super constructor
     * to construct a empty Phrase that contains nothing inside
     */
    public Phrase()
    {
        super();
    }
    
    /**
     * Builds and returns a new Phrase object, which is a queue containing bigrams representing
     * the given String s. This new Phrase object is going to be use for encryption
     * 
     * @param s
     *  The String to represent as a Bigram queue
     * @return Returns the new Phrase object which contains a queue of Bigram objects representing s
     */
    public static Phrase buildPhraseFromStringforEnc(String s)
    {
        //This will be where we store our bigram list output
        Phrase output=new Phrase();
        
        //Since we are working with the String s, we create a temporary reference to
        //it, eventhough String is immutable but I like to use another reference
        String workTemp=s;
        
        //This removes all the empty spaces
        workTemp=workTemp.replaceAll("\\s","");
        
        //This pushes workTemp into all upper cases
        workTemp=workTemp.toUpperCase();
        
        //We then also have to change all the J into I because we are not dealing with
        //J in our playfair encryption
        workTemp=workTemp.replaceAll("J","I");
        
        //Pos will be representing the index we are currently at in workTemp
        int pos=0;
        
        //This for loop will be removing all of the symbols in the workTemp
        for(int i=0;i<workTemp.length();i++)
        {
            char charI=workTemp.charAt(i);
            
            //This means that it is a symbol we need to remove from the String
            if(charI<65||charI>90)
            {
                //This is getting the first part up until the symbol but not including
                //the symbol
                String firstPart=workTemp.substring(0,i);
                
                //This is getting the second part all of the things after the symbol
                String rest=workTemp.substring(i+1);
                
                //Putting them together remvoes the symbol
                workTemp=firstPart+rest;
                
                //We need to decrement one index so we don't accidentally skip over
                //a char to check after removing the symbol
                i--;
            }
        }
        
        //If we are after the previous for loop then that means that there is no space
        //all the J is replaced with I, all symbols are removed, we can start
        //making the Bigram object by taking a pair of letters
        while(pos<workTemp.length())
        {
            //This is the Bigram that we are preparing to add to the phrase
            Bigram toBeAdded=new Bigram();
            
            //This means that we are creating a Bigram that only have one letter
            //or the Bigram we are creating is using the last letter of workTemp
            if(pos==workTemp.length()-1)
            {
                //We just need posChar not need for nextChar because we know it will
                //be X
                char posChar=workTemp.charAt(pos);
                
                toBeAdded.setFirst(posChar);
                toBeAdded.setSecond('X');
                
                //We have to increment so that we can actually stop the loop or else
                //it will be infinite
                pos++;
            }
            //Else this means that it is not a special case where it require special
            //attentions
            else
            {
                //posChar represent the char that pos is at right now
                char posChar=workTemp.charAt(pos);

                //nextChar represent the char that is right after pos
                char nextChar=workTemp.charAt(pos+1);

                //This means that the two letter forming a bigram are the same letter
                //therefore we have to add in a x after posChar to create a pair of
                //bigram, not together with nextChar
                if(posChar==nextChar)
                {
                    //Because the nextChar is the same as posChar we instead have to
                    //create a Bigram that combines posChar with a X rather than with
                    //nextChar
                    toBeAdded.setFirst(posChar);
                    toBeAdded.setSecond('X');

                    //Because we did not use nextChar we have to increment pos to just the
                    //next index because we didn't use the nextChar to create a Bigram
                    pos++;
                }
                //Then this means that posChar is not the same as nextChar therefore
                //we can just make the Bigram to be posChar and nextChar and skip two
                //index
                else
                {
                    toBeAdded.setFirst(posChar);
                    toBeAdded.setSecond(nextChar);

                    //Since we are creating a Bigram using the posChar and nextChar
                    //we have to increment pos twice to skip over the next char we already
                    //handled
                    pos=pos+2;
                }
            }
            //If we are outside then toBeAdded should have the correct value
            //we just add it to the phrase
            output.enqueue(toBeAdded);
        }
        
        return output;
    }
    
    /**
     * Encrypts this Phrase, storing the encrypted bigrams in a new Phrase queue object
     * and returns it
     * <p>
     * Precondition: key is not null
     * 
     * @param key
     *  The KeyTable to use to encrypt this Phrase
     * @return Returns the new Phrase object which contains a queue of Bigram that is
     *  encrypted
     * @throws IllegalArgumentException 
     *  Throws IllegalArgumentException to indicate that key is null
     */
    public Phrase encrypt(KeyTable key) throws IllegalArgumentException
    {
        Phrase output=new Phrase();
        
        //There is no key being used for encryption
        if(key==null)
        {
            throw new IllegalArgumentException("Invalid KeyTable");
        }
        
        //If we are out here then that means the KeyTable being used for encrpytion
        //is valid therefore we process on with the encrpytion
        for(int i=0;i<size();i++)
        {
            //This gets the non-encrypted Bigram from the current Phase
            Bigram eachB=(Bigram)this.get(i);
            
            //This is making a encrypted Bigram based on the non-encrypted Bigram
            Bigram encryptedB=new Bigram();
            
            //This gets the first letter and the second letter from the
            //non-encrypted Bigram
            char firstLetter=eachB.getFirst();
            char secondLetter=eachB.getSecond();
            
            //This finds the row and col of the first letter
            int firstLetterRow=key.findRow(firstLetter);
            int firstLetterCol=key.findCol(firstLetter);
            
            //This finds the row and col of the second letter
            int secondLetterRow=key.findRow(secondLetter);
            int secondLetterCol=key.findCol(secondLetter);
            
            //This means that the Bigram letter have the same row therefore we
            //have to take the letter one after the Bigram letter in the same col
            if(firstLetterRow==secondLetterRow)
            {
                //This means that it spill over therefore we take the letter that is
                //on the first col
                if(firstLetterCol==KeyTable.WIDTH-1)
                {
                    encryptedB.setFirst(key.getKeyTable()[firstLetterRow][0]);
                }
                //This means that it doesn't spill over we can just take the one
                //letter that is one col after this one
                else
                {
                    encryptedB.setFirst(key.getKeyTable()[firstLetterRow][firstLetterCol+1]);
                }
                
                //This means that the second letter also spill over therefore we
                //take the letter that is on the first col
                if(secondLetterCol==KeyTable.WIDTH-1)
                {
                    encryptedB.setSecond(key.getKeyTable()[secondLetterRow][0]);
                }
                //This means that it doesn't spill over we can just take the one
                //that is after the second letter
                else
                {
                    encryptedB.setSecond(key.getKeyTable()[secondLetterRow][secondLetterCol+1]);
                }
            }
            //This means that the Bigram letter have the same col therefore we 
            //have to take the letter one row after the Bigram letter in the
            //same col
            else if(firstLetterCol==secondLetterCol)
            {
                //This means that the first letter is at the last row and moving on
                //will be spilling over therefore we just set the encrypted Bigram
                //first letter to the first row same column
                if(firstLetterRow==KeyTable.HEIGHT-1)
                {
                    encryptedB.setFirst(key.getKeyTable()[0][firstLetterCol]);
                }
                //This means that it is not at the last row therefore we can just
                //increment the row to the next one
                else
                {
                    encryptedB.setFirst(key.getKeyTable()[firstLetterRow+1][firstLetterCol]);
                }
                
                //This means that the second letter is at the last row and moving on
                //will be also spilling over therefore we just need to set the
                //encrypred Bigram's second letter to the first row same column
                if(secondLetterRow==KeyTable.HEIGHT-1)
                {
                    encryptedB.setSecond(key.getKeyTable()[0][secondLetterCol]);
                }
                //This means that it is not at the last row we just need to
                //set the second letter to the next row same col's letter
                else
                {
                    encryptedB.setSecond(key.getKeyTable()[secondLetterRow+1][secondLetterCol]);
                }
            }
            //Then this means that the Bigram letter don't have the same row
            //nor does it have the same col thus we create a rectangle and take
            //the letter opposite of each Bigram letter
            else
            {
                //Swapping the indexes to get the character opposite of the letters
                encryptedB.setFirst(key.getKeyTable()[firstLetterRow][secondLetterCol]);
                encryptedB.setSecond(key.getKeyTable()[secondLetterRow][firstLetterCol]);
            }
            
            //Adding it into the encrypted Phrase
            output.enqueue(encryptedB);
        }
        
        return output;
    }
    
    /**
     * Decrypts this Phrase, storing the decrypted Bigrams in a new Phrase queue object and
     * returning it.
     * <p>
     * Precondition: key is not null
     * 
     * @param key
     *  The KeyTable to use to decrypt this Phrase
     * @return Returns the new Phrase object which contains a queue of decrypted Bigram object
     * @throws IllegalArgumentException 
     *  Throws IllegalArgumentException to indicate key is null
     */
    public Phrase decrypt(KeyTable key) throws IllegalArgumentException
    {
        Phrase output=new Phrase();
        
        //This means there is no KeyTable being used for the decryption therefore
        //we tell the user that there is no KeyTable
        if(key==null)
        {
            throw new IllegalArgumentException("Invalid KeyTable");
        }
        
        //If we are out here then that means there is a KeyTable for the decryption
        //therefore we begin the decryption
        while(!isEmpty())
        {
            //This gets all the encrypted Bigram from this Phrase for decryption
            Bigram eachB=dequeue();
            
            //This represent a decrypted Bigram for the output
            Bigram decryptedB=new Bigram();
            
            //Getting the first and second letter of the encrypted Bigram
            char firstLetter=eachB.getFirst();
            char secondLetter=eachB.getSecond();
            
            //Finding the row and col of the first letter
            int firstLetterRow=key.findRow(firstLetter);
            int firstLetterCol=key.findCol(firstLetter);
            
            //Finding the row and col of the second letter
            int secondLetterRow=key.findRow(secondLetter);
            int secondLetterCol=key.findCol(secondLetter);
            
            //This means that the encrypted Bigram have the same row index
            //therefore we move back each letter by one col to get the actual
            //message
            if(firstLetterRow==secondLetterRow)
            {
                //This means that we want to move back one col but it spills over
                //therefore we take the letter that is in the same row
                //but the rightmost col
                if(firstLetterCol==0)
                {
                    decryptedB.setFirst(key.getKeyTable()[firstLetterRow][KeyTable.WIDTH-1]);
                }
                //This means that the index don't spill over therefore we just
                //need to move the col one back
                else
                {
                    decryptedB.setFirst(key.getKeyTable()[firstLetterRow][firstLetterCol-1]);
                }
                
                //This means that the second letter is at the first col and we want
                //to move back one col thus it spill over, we just set it to the
                //letter that is in the last col but same row
                if(secondLetterCol==0)
                {
                    decryptedB.setSecond(key.getKeyTable()[secondLetterRow][KeyTable.WIDTH-1]);
                }
                //This means that the second letter is not at the first col therefore
                //we can just move back the col by one unit
                else
                {
                    decryptedB.setSecond(key.getKeyTable()[secondLetterRow][secondLetterCol-1]);
                }
            }
            //This means that the two letters have the same col therefore we take the
            //letter that is one row before each letter
            else if(firstLetterCol==secondLetterCol)
            {
                //This means that we want to move back one col but it spills over
                //therefore we take the letter that is in the same row
                //but the rightmost col
                if(firstLetterRow==0)
                {
                    decryptedB.setFirst(key.getKeyTable()[KeyTable.HEIGHT-1][firstLetterCol]);
                }
                //This means that the index don't spill over therefore we just
                //need to move the col one back
                else
                {
                    decryptedB.setFirst(key.getKeyTable()[firstLetterRow-1][firstLetterCol]);
                }
                
                //This means that the second letter is in the first row and we want
                //to move back one row but it spill over therefore we just set second
                //letter to be the same col but the last row
                if(secondLetterRow==0)
                {
                    decryptedB.setSecond(key.getKeyTable()[KeyTable.HEIGHT-1][secondLetterCol]);
                }
                //This means that the second letter is not in the first row therefore
                //we can just move back one row
                else
                {
                    decryptedB.setSecond(key.getKeyTable()[secondLetterRow-1][secondLetterCol]);
                }
            }
            //This means that the two letter don't have the same row or the same col
            //thus we just create a rectangle and swap the col of the two
            else
            {
                //Swapping the indexes to get the character opposite of the letters
                decryptedB.setFirst(key.getKeyTable()[firstLetterRow][secondLetterCol]);
                decryptedB.setSecond(key.getKeyTable()[secondLetterRow][firstLetterCol]);
            }
            
            //Adding it into the decrypted Phrase
            output.enqueue(decryptedB);
        }
        
        return output;
    }
    
    /**
     * Adds a new Bigram to the end of the Phrase
     * 
     * @param b
     *  The Bigram to add into the Phrase
     */
    public void enqueue(Bigram b)
    {
        this.add(b);
    }

    /**
     * Removes and returns the first Bigram in the Phrase
     * 
     * @return Returns the first Bigram in the Phrase
     */
    public Bigram dequeue() 
    {
        Bigram output=null;
        
        //This makes sure that there is Bigram inside the Phrase to be removed
        //to avoid nullpointer exception
        if(this.size()>0)
        {
            output=(Bigram)this.remove(0);
            
            return output;
        }
        
        return output;
    }

    /**
     * Returns without removing the first Bigram in the Phase
     * 
     * @return The first Bigram in the Phrase without removing it
     */
    public Bigram peek() 
    {
        Bigram output=null;
        
        //This makes sure that there is Bigram inside the Phrase before
        //trying to pull out anything
        if(this.size()>0)
        {
            output=(Bigram)this.get(0);

            return output;
        }
        
        return output;
    }
}