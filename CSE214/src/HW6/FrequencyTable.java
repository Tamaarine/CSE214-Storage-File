package HW6;

import java.util.ArrayList;
import java.util.Set;

/**
 * FrequencyTable will be holding a collection of FrequencyList that represent all
 * of the word's frequencies across the Passages. It will use ArrayList to store the
 * FrequencyList.
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class FrequencyTable extends ArrayList<FrequencyList>
{
    /**
     * This static method will iterate through the given passages and construct a
     * FrequencyList with each Passage's appropriate word frequencies
     * <p>
     * Postcondition: A new FrequencyTable object will be constructed that contain a
     * Collection of FrequencyLists with all the information from all Passages
     * 
     * @param passages
     *  A collection that contain the Passage objects to be parse through
     * @return Returns the FrequencyTable constructed from each PAssage in passages
     */
    public static FrequencyTable buildTable(ArrayList<Passage> passages)
    {
        //Making a new FrequencyTable
        FrequencyTable output=new FrequencyTable();
        
        //Looping through the passages 
        for(int i=0;i<passages.size();i++)
        {
            //This represent one single passsages
            Passage passageI=passages.get(i);
            
            //We get the set of words that each passages have
            Set<String> words=passageI.getWords();
            
            //Iterating through every single words to make a FrequencyList of
            //each and every single word
            for(String word:words)
            {
                //Making the new wordList to be added
                FrequencyList wordList=new FrequencyList(word,passages);
                
                //This will loop through the entire FrequencyTable to make sure that
                //we don't add in a duplicate wordList that already existed in the
                //FrequencyTable
                for(int j=0;i<output.size();j++)
                {
                    if(!output.get(j).getWord().equals(word))
                    {
                        output.add(wordList);
                    }
                }
            }
        }
        
        return output;
    }
    
    /**
     * This method add a Passage into the FrequencyTable and updates the FrequencyList
     * accordinyly.
     * <p>
     * Precondition: The Passage p is neither null nor empty
     * <p>
     * Postcondition: p's value for each of its keys have been appropriately mapped
     * into each FrequencyWList in the table
     * 
     * @param p
     *  The Passage being iterated over and integrated into the table
     * @throws IllegalArgumentException 
     *  IllegalArgumentException is thrown if the given Passage is null or empty
     */
    public void addPassage(Passage p) throws IllegalArgumentException
    {
        //This means that the passage is either null or empty therefore we have 
        //to tell the user that
        if(p==null||p.getWordCount()==0)
        {
            throw new IllegalArgumentException("Passage is empty or null");
        }
        
        //Getting the set of words that p contains
        Set<String> words=p.getWords();
        
        //Going through all of the words that p have
        for(String word:words)
        {
            //This for loop represent all of the FrequencyList that the FrequencyTable
            //have
            for(int i=0;i<size();i++)
            {
                //This means that the passage's word exist in the FrequencyTable already
                //therefore we just have to call the addPassage method
                if(get(i).getWord().equals(word))
                {
                    get(i).addPassage(p);
                }
                //Else this means that passage p's word does not exist in the FrequencyTable
                //thus we need to make a new FrequencyList and add it into the FrequencyTable
                else
                {
                    //Making the ArrayList that only contain p because to make a
                    //new FrequencyList we have to make an ArrayList in order to use it
                    ArrayList<Passage> passages=new ArrayList();
                    
                    //Adding it to the ArrayList
                    passages.add(p);
                    
                    FrequencyList toBeAdded=new FrequencyList(word,passages);
                    
                    //Adding it to the FrequencyTable
                    add(toBeAdded);
                }
            }
        }
    }
    
    /**
     * Returns the frequency of the given word in the given Passage
     * 
     * @param word
     *  The word to look for in the Passage
     * @param p
     *  The Passage to look in for word
     * @return Returns the frequency of the given word in the given Passage
     */
    public double getFrequency(String word, Passage p)
    {
        double output=p.getWordFrequency(word);
        
        return output;
    }
}