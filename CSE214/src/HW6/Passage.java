package HW6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Passage extends HashMap<String, Integer>
{
    //Instance variables
    private String title;
     int wordCount;
    private HashMap<String, Double> similarTitles;
    private static HashMap stopWordStr;
    
    //Paramaterized constructor
    public Passage(String title, File file)
    {
        this.title=title;
        
        similarTitles=new HashMap();
        
        parseFile(file);
    }
    
    public Passage()
    {
        title="";
        
        similarTitles=new HashMap();
        
        stopWordStr=new HashMap();
    }
    
    public static double cosineSimilarity(Passage passage1, Passage passage2)
    {
        double output;
        
        HashMap similar1=passage1.getSimilarTitles();
        HashMap similar2=passage2.getSimilarTitles();
        
        String passage1Name=passage1.getTitle();
        String passage2Name=passage2.getTitle();
        
        Set<String> words1=passage1.getWords();
        Set<String> words2=passage2.getWords();
        
        int length1=words1.size();
        int length2=words2.size();
        
        ArrayList<Double> topPortion=new ArrayList();
        ArrayList<Double> botLeftPart=new ArrayList();
        ArrayList<Double> botRightPart=new ArrayList();
        
        //We use words1
        if(length1>length2)
        {
            for(String word:words1)
            {
                double passage1Freq=passage1.getWordFrequency(word);
                double passage2Freq=passage2.getWordFrequency(word);
                
                topPortion.add(passage1Freq*passage2Freq);
            }
        }
        //We use words2
        else
        {
            for(String word:words2)
            {
                double passage1Freq=passage1.getWordFrequency(word);
                double passage2Freq=passage2.getWordFrequency(word);
                
                topPortion.add(passage1Freq*passage2Freq);
            }
        }
        
        for(String word:words1)
        {
            double passage1Freq=passage1.getWordFrequency(word);

            botLeftPart.add(Math.pow(passage1Freq, 2));
        }
        
        for(String word:words2)
        {
            double passage2Freq=passage2.getWordFrequency(word);

            botRightPart.add(Math.pow(passage2Freq, 2));
        }
        
        double sumOfTop=0;
        for(Double d:topPortion)
        {
            sumOfTop+=d;
        }
        
        double sumOfLeft=0;
        for(Double d:botLeftPart)
        {
            sumOfLeft+=d;
        }
        
        double sumOfRight=0;
        for(Double d:botRightPart)
        {
            sumOfRight+=d;
        }
        
        output=(sumOfTop)/(Math.sqrt(sumOfLeft)*Math.sqrt(sumOfRight));
        
        Object keyValue1=similar1.get(passage2Name);
        Object keyValue2=similar2.get(passage1Name);
        
        if(keyValue1==null)
        {
            similar1.put(passage2Name, output);
        }
        
        if(keyValue2==null)
        {
            similar2.put(passage1Name, output);
        }
        
        
        return output;
        
    }
    
    //This method is used to read up the files and count up the
    //word occurences excluding the stop words
    public void parseFile(File file)
    {   
        try
        {
            //This is getting the file that is going to be read by the program
            //line by line
            FileInputStream input=new FileInputStream(file);
            
            InputStreamReader inputReader=new InputStreamReader(input);
            BufferedReader reader=new BufferedReader(inputReader);
            
            //This is getting each line by line and then proceed to do work with it
            String eachLine=reader.readLine();
            
            while(eachLine!=null)
            {
                //However, before we proceed to do any work on it, we have filter
                //each line first
                //First we need to make the line all into lower cases, so that
                //capitalized word and lower case word won't be count differently
                eachLine=eachLine.toLowerCase();

                //Next we have to remove any punctuation that it have on the line
                //which means to take out any letter that doesn't have the ascii
                //value between 97 to 122
                for(int i=0;i<eachLine.length();i++)
                {
                    //Getting each character of the line
                    char eachChar=eachLine.charAt(i);

                    //This means that it is not a lower case alphebetical letter
                    //therefore we must proeed to remove it. 32 is for space
                    if((eachChar<97||eachChar>122)&&eachChar!=32)
                    {
                        //Getting the first part before the symbol
                        String firstPart=eachLine.substring(0,i);

                        //Getting the second part after the symbol
                        String secondPart=eachLine.substring(i+1);

                        //Then we put them back together which remove the unwanted 
                        //letter
                        eachLine=firstPart+secondPart;

                        //Because we remove a character we have to move back i one
                        //to account for the change in index
                        i--;
                    }
                }

                int indexOfSpace=eachLine.indexOf(" ");

                //eachWord will be storing each word that we get in eachLine
                String eachWord="";

                //This means that after removing all of the symbols
                //this could be the existences of only one word in the line.
                //thus we must add it into the hashMap
                if(indexOfSpace==-1)
                {
                    eachWord=eachLine;
                    
                    //This means that if only the word is not a stop word we can
                    //store it into the hashMap
                    if(!isAStopWord(eachWord))
                    {
                        Object obj=this.get(eachWord);

                        //This means that there is a value, thus we can turn obj into
                        //int and add it into the already mapped value
                        if(obj!=null)
                        {
                            int occurrences=(int)obj;

                            put(eachWord,occurrences+1);
                            
                            wordCount++;
                        }
                        //This means that there is no previously mapped value therefore
                        //we make a new one
                        else
                        {
                            put(eachWord,1);
                            
                            wordCount++;
                        }
                    }
                }
                //This while loop will be going through the line and putting every single
                //word as a key and mapped and store it inside the hashMap
                while(indexOfSpace!=-1)
                {
                    //Geting the eachWord to prepare for hashing
                    eachWord=eachLine.substring(0,indexOfSpace);
                    
                    if(!isAStopWord(eachWord))
                    {
                        //This is trying to get a aleady mapped key inside Passage
                        //already. If obj is null then that means there is no previously
                        //mapped key, therefore we make it as a new one. However, if it is
                        //not null then that mean the key already existed. Thus we must
                        //update its value because we are adding a new word into it
                        Object obj=this.get(eachWord);

                        //This means that there is a value, thus we can turn obj into
                        //int and add it into the already mapped value
                        if(obj!=null)
                        {
                            int occurrences=(int)obj;

                            put(eachWord,occurrences+1);
                            
                            wordCount++;
                        }
                        //This means that there is no previously mapped value therefore
                        //we make a new one
                        else
                        {
                            put(eachWord,1);
                            
                            wordCount++;
                        }

                        //Updating the line to cut out the part we have already take
                        eachLine=eachLine.substring(indexOfSpace+1);

                        //Then getting the index of next space
                        indexOfSpace=eachLine.indexOf(" ");

                        //If indexOfSpace hits -1 that means that we have reach the 
                        //last word so we have to hash this value into the hashMap
                        //or else it won't come back into the while loop to do it
                        if(indexOfSpace==-1)
                        {
                            eachWord=eachLine;

                            //This is trying to get a aleady mapped key inside Passage
                            //already. If obj is null then that means there is no previously
                            //mapped key, therefore we make it as a new one. However, if it is
                            //not null then that mean the key already existed. Thus we must
                            //update its value because we are adding a new word into it
                            obj=this.get(eachWord);

                            if(!isAStopWord(eachWord))
                            {
                                //This means that there is a value, thus we can turn obj into
                                //int and add it into the already mapped value
                                if(obj!=null)
                                {
                                    int occurrences=(int)obj;

                                    put(eachWord,occurrences+1);
                                    
                                    wordCount++;
                                }
                                //This means that there is no previously mapped value therefore
                                //we make a new one
                                else
                                {
                                    put(eachWord,1);
                                    
                                    wordCount++;
                                }
                            }
                            else
                            {
                                eachLine="";
                            }
                        }
                    }
                    //This means that it is a stop word, therefore we move on
                    //without adding it in
                    else
                    {
                        //Updating the line to cut out stop word we skipped over
                        eachLine=eachLine.substring(indexOfSpace+1);

                        //Then getting the index of next space
                        indexOfSpace=eachLine.indexOf(" ");
                        
                        if(indexOfSpace==-1)
                        {
                            eachWord=eachLine;
                            
                            Object obj=this.get(eachWord);

                            if(!isAStopWord(eachWord))
                            {
                                //This means that there is a value, thus we can turn obj into
                                //int and add it into the already mapped value
                                if(obj!=null)
                                {
                                    int occurrences=(int)obj;

                                    put(eachWord,occurrences+1);
                                    
                                    wordCount++;
                                }
                                //This means that there is no previously mapped value therefore
                                //we make a new one
                                else
                                {
                                    put(eachWord,1);
                                    
                                    wordCount++;
                                }
                            }
                            else
                            {
                                eachLine="";
                            }
                        }
                    }
                }
                
                eachLine=reader.readLine();
            }
            
            
        }
        catch(FileNotFoundException f)
        {
            System.out.println(f);
        }
        catch(IOException i)
        {
            System.out.println("Error when reading the files");
        }
        
        //After the adding process, there might be empty spaces that is mapped
        //into passage therefore we must remove it
        Object obj=this.get("");
        
        int spaces=0;
        
        if(obj!=null)
        {
            spaces=(int)obj;
        }
        
        wordCount=wordCount-spaces;
        
        this.remove("");
    }
    
    public boolean isAStopWord(String givenWord)
    {
        Object obj=stopWordStr.get(givenWord);
        
        if(obj!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void produceStopWord(File file)
    {
        try
        {
            //Together this reads the lines in stopWord.txt to accumlate each words
            //into stopWordStr
            FileInputStream input=new FileInputStream(file);
            
            InputStreamReader inputReader=new InputStreamReader(input);
            BufferedReader reader=new BufferedReader(inputReader);
            
            String eachLine=reader.readLine();
            
            while(eachLine!=null)
            {
                stopWordStr.put(eachLine, 1);
                
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
    
    //Accessor method
    public double getWordFrequency(String word)
    {
        double output;
        
        Object obj=this.get(word);
        
        //That means there is at least one occurrences for the word
        if(obj!=null)
        {
            output=(int)obj/(double)wordCount;
        }
        //That means there is no occurrences for the word
        else
        {
            output=0;
        }
        
        return output;
    }
    
    public Set<String> getWords()
    {
        Set<String> output=this.keySet();
        
        return output;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public int getWordCount()
    {
        return wordCount;
    }
    
    public HashMap getSimilarTitles()
    {
        return similarTitles;
    }
    
    //Mutator method
    public void setTitle(String title)
    {
        this.title=title;
    }
    
    public void setSimilarTitles(HashMap similarTitles)
    {
        this.similarTitles=similarTitles;
    }
    
    public String toString()
    {
        String output=super.toString();
        
        System.out.println(wordCount);
        
        return output;
    }
    
    
    
    
    
    
}   
