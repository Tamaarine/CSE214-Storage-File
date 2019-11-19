package HW6;

import java.util.ArrayList;
import java.util.HashMap;

public class FrequencyList
{   
    //Instance variables
    private String word;
    private ArrayList<Double> frequencies;
    private HashMap<String, Integer> passageIndices;
    
    //arrayCounter will be keeping track of the net available space in frequencies
    private int arrayCounter;
    
    public FrequencyList(String word, ArrayList<Passage> passages)
    {
        this.word=word;
        
        arrayCounter=0;
        
        frequencies=new ArrayList();
        passageIndices=new HashMap();
        
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
    
    public double getFrequency(Passage p)
    {
        double output=0;
        
        String passageTitle=p.getTitle();
        
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
    
    //Accessor method
    public String getWord()
    {
        return word;
    }
   
}