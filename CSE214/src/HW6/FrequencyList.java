package HW6;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class will be representing one word's frequency in each and every single given
 * Passages. The frequencies will be stored in an external data structure which is
 * an ArrayList of Integers. It will contain a String instance variable to keep track
 * of which word this FrequencyList is representing
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class FrequencyList
{   
    //The word this FrequencyList represents
    private String word;
    
    //This stores the frequency of the word as it relattes to each Passages
    private ArrayList<Double> frequencies;
    
    //passageindices will have a collection of the title of each Passage as its key
    //and the corresponding index in the ArrayList
    private HashMap<String, Integer> passageIndices;
    
    //arrayCounter will be keeping track of the net available space in frequencies
    private int arrayCounter;
    
    /**
     * Paramaterized constructor of the FrequencyList, it will construct a
     * FrequencyList based on the given word and the given passages
     * 
     * @param word
     *  The word for this FrequencyList to represent
     * @param passages 
     *  The list of passages to pull the information about word from
     */
    public FrequencyList(String word, ArrayList<Passage> passages)
    {
        //Setting the word to the given word
        this.word=word;
        
        //Setting the array counter to be 0 for the next available space
        arrayCounter=0;
        
        //Default value
        frequencies=new ArrayList();
        passageIndices=new HashMap();
        
        //This for loop will go through each and every single passages and check
        //for the word frequency of each
        for(int i=0;i<passages.size();i++)
        {
            //p will be representing every single passage in passages
            Passage p=passages.get(i);
            
            //Getting the frequency of the word from the passage
            double wordFrequency=p.getWordFrequency(word);
            
            //Getting the title of the passage
            String title=p.getTitle();
            
            //We only want to add word that have a frequency greater than 0
            //to avoid the word taking up unnecessary space if it is not in the
            //passages
            if(wordFrequency>0)
            {
                //First we have to add the number of frequency inside the ArrayList
                frequencies.add(wordFrequency);
                
                //Then we have to store the corresponding title with its index value 
                //for the frequencies ArrayList into the passageIndices
                
                passageIndices.put(title, arrayCounter);
                
                //Remember to increment the index
                arrayCounter++;
            }
        }
    }
    
    /**
     * This method will add a Passage to this FrequencyList
     * <p>
     * Postcondition: The passageIndices will now contain p's title which maps to
     * the next available index in this ArrayList, and that the ArrayList will now
     * also contain an additional index containing the frequency of word in the Passage
     * 
     * @param p 
     *  The Passage to add to this FrequencyList
     */
    public void addPassage(Passage p)
    {
        //Getting the title's name
        String passageTitle=p.getTitle();
        
        //Getting the freqeuency of word in this passage
        double frequency=p.getWordFrequency(word);
        
        //This means that the passage does contain word inside therefore, we have
        //to add it to our frequencies and passageIndices
        if(frequency>0)
        {
            //First we have to add it into our ArrayList of frequencies
            frequencies.add(frequency);
            
            //Then we put it inside passageIndicies with the title as key
            //and the index that it is stored inside frequencies
            passageIndices.put(passageTitle,arrayCounter);
            
            //We must increment arrayCounter
            arrayCounter++;
        }
        
        //And we don't add it if the frequency is 0 since there is no point
    }
    
    /**
     * Returns the frequency of the word that this FrequencyList hold in the given
     * Passage. It will return 0 if the Passage does not contain the word
     * 
     * @param p
     *  The Passage to check the word in
     * @return Returns the frequency of the word that occurs in the given passage
     */
    public double getFrequency(Passage p)
    {
        //Output will represent the frequency of word in Passage p
        double output=0;
        
        //Getting the title of the Passage so we can search through passageIndices
        String passageTitle=p.getTitle();
        
        //Searching through passageIndices
        Object obj=passageIndices.get(passageTitle);
        
        //That means that the given passage is inside the ArrayList
        //which have word occurences
        if(obj!=null)
        {
            int index=(int)obj;
            
            //We get it from our frequencies list and then return it
            output=frequencies.get(index);
            
            return output;
        }
        //We return 0 if the passage is not inside the ArrayList
        else
        {
            return output;
        }
    }
    
    /**
     * Return the word that this FrequencyList is currently holding
     * 
     * @return Returns the word that this FrequencyList is currently holding
     */
    public String getWord()
    {
        return word;
    }
   
}