package HW6;

import java.util.ArrayList;
import java.util.Set;

public class FrequencyTable extends ArrayList<FrequencyList>
{
    public FrequencyTable()
    {
        super();
    }
    
    public static FrequencyTable buildTable(ArrayList<Passage> passages)
    {
        FrequencyTable output=new FrequencyTable();
        
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
                FrequencyList wordList=new FrequencyList(word,passages);
                
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
    
    public void addPassage(Passage p) throws IllegalArgumentException
    {
        
    }
    
    public double getFrequency(String word, Passage p) throws IllegalArgumentException
    {
        double output=p.getWordFrequency(word);
        
        return output;
    }
    
    public String toString()
    {
        String output="";
        
        for(FrequencyList f:this)
        {
            output+=f+"\n";
        }
        
        return output;
    }
    
    
}