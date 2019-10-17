package HW4;

import java.util.ArrayList;

/**
 * 
 * 
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class Phrase extends ArrayList implements Queue
{
    public Phrase()
    {
        super();
    }
    
    //Don't need to write size and isEmpty method because Phrase is already a ArrayList
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
        
        int pos=0;
        
        //This for loop will be removing all of the symbols in the workTemp
        for(int i=0;i<workTemp.length();i++)
        {
            char charI=workTemp.charAt(i);
            
            //This means that it is a symbol we need to remove from the String
            if(charI<65||charI>90)
            {
                String firstPart=workTemp.substring(0,i);
                String rest=workTemp.substring(i+1);
                
                workTemp=firstPart+rest;
                
                //We need to decrement one index so we don't accidentally skip over
                //a char to check after removing the symbol
                i--;
            }
        }
        
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
            Bigram eachB=(Bigram)this.get(i);
            
            Bigram encryptedB=new Bigram();
            
            char firstLetter=eachB.getFirst();
            char secondLetter=eachB.getSecond();
            
            //This is the index of the first 
            int firstLetterRow=key.findRow(firstLetter);
            int firstLetterCol=key.findCol(firstLetter);
            
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
                if(firstLetterRow==KeyTable.HEIGHT-1)
                {
                    encryptedB.setFirst(key.getKeyTable()[0][firstLetterCol]);
                }
                else
                {
                    encryptedB.setFirst(key.getKeyTable()[firstLetterRow+1][firstLetterCol]);
                }
                
                if(secondLetterRow==KeyTable.HEIGHT-1)
                {
                    encryptedB.setSecond(key.getKeyTable()[0][secondLetterCol]);
                }
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
                encryptedB.setFirst(key.getKeyTable()[firstLetterRow][secondLetterCol]);
                encryptedB.setSecond(key.getKeyTable()[secondLetterRow][firstLetterCol]);
            }
            
            output.enqueue(encryptedB);
        }
        
        return output;
    }
    
    
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
            Bigram eachB=(Bigram)get(0);
            
            Bigram decryptedB=new Bigram();
            
            char firstLetter=eachB.getFirst();
            char secondLetter=eachB.getSecond();
            
            int firstLetterRow=key.findRow(firstLetter);
            int firstLetterCol=key.findCol(firstLetter);
            
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
                
                if(secondLetterCol==0)
                {
                    decryptedB.setSecond(key.getKeyTable()[secondLetterRow][KeyTable.WIDTH-1]);
                }
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
                
                if(secondLetterRow==0)
                {
                    decryptedB.setSecond(key.getKeyTable()[KeyTable.HEIGHT-1][secondLetterCol]);
                }
                else
                {
                    decryptedB.setSecond(key.getKeyTable()[secondLetterRow-1][secondLetterCol]);
                }
            }
            //This means that the two letter don't have the same row or the same col
            //thus we just create a rectangle and swap the col of the two
            else
            {
                decryptedB.setFirst(key.getKeyTable()[firstLetterRow][secondLetterCol]);
                decryptedB.setSecond(key.getKeyTable()[secondLetterRow][firstLetterCol]);
            }
            
            output.enqueue(decryptedB);
            dequeue();
        }
        
        return output;
    }
    
    @Override
    public void enqueue(Bigram b)
    {
        this.add(b);
    }

    @Override
    public Bigram dequeue() 
    {
        Bigram output=null;
        
        if(this.size()>0)
        {
            output=(Bigram)this.remove(0);
            
            return output;
        }
        
        return output;
    }

    @Override
    public Bigram peek() 
    {
        Bigram output=null;
        
        if(this.size()>0)
        {
            output=(Bigram)this.get(0);

            return output;
        }
        
        return output;
    }
}