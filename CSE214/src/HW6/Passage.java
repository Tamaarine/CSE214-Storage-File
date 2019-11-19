package HW6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * The Passage class is a class that is a HashMap which maps a word to an integer value
 * which represents its total occurences of that word in a given text file. Passage
 * will represent a single text file with all of its different words' occurrences
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class Passage extends HashMap<String, Integer>
{
    //This String variable represent the title of the Passage which is what the
    //file name is
    private String title;
    
    //wordCount represent the total number of words occured in this Passage without
    //including stop words
    private int wordCount;
    
    //The HashMap similarTitles represent a collection of other Passages as a key
    //and the similarity percentage between this Passage nad the other Passages as
    //the value
    private HashMap<String, Double> similarTitles;
    
    //This static HashMap variable represent the mapped values of all of the StopWords
    //in the StopWord.txt
    private static HashMap stopWordStr;
    
    //DecimalFormat will help to round the numbers
    private DecimalFormat df=new DecimalFormat("0");
    
    /**
     * Paramaterized constructor of the Passage class, it sets the title of the
     * Passage and calls the parseFile method
     * 
     * @param title
     *  The title of the Passage to set to
     * @param file 
     *  The file that is used to map all of the words
     */
    public Passage(String title, File file)
    {
        //Default values
        this.title=title;
        similarTitles=new HashMap();
        
        //Calling the parseFile method
        parseFile(file);
    }
    
    /**
     * Defalut constructor of the Passage class. The purpose of this class is just
     * used for the dummyPassage that is created in the beginning of TextAnalayzer
     * for the sake of making the stopWordStr for all of the rest of Passages
     */
    public Passage()
    {
        //Default value
        title="";
        
        similarTitles=new HashMap();
        
        stopWordStr=new HashMap();
    }
    
    /**
     * This method reads the given text file and count 
     * @param file 
     */
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
                
                //Going toward the next line
                eachLine=reader.readLine();
            }
        }
        //The exception of file not being found will be thrown
        catch(FileNotFoundException f)
        {
            System.out.println(f);
        }
        //This means that there is a error when reading the files
        catch(IOException i)
        {
            System.out.println("Error when reading the files");
        }
        
        //After the adding process, there might be empty spaces that is mapped
        //into passage therefore we must remove it
        Object obj=this.get("");
        
        //spaces represent the number of spaces that we have to take out
        int spaces=0;
        if(obj!=null)
        {
            //Getting the number of spaces from the passages
            spaces=(int)obj;
        }
        
        //Removing the extra number that spaces contribute to wordCount
        wordCount=wordCount-spaces;
        
        //Removing the empty spaces
        this.remove("");
    }
     /**
     * This method will help determine whether or not the given word is a
     * stop word or not
     * 
     * @param givenWord
     *  The word to check whether or not if it is a stop word
     * @return Returns true or false on whether not the given word is a stop word
     */
    public boolean isAStopWord(String givenWord)
    {
        //Getting the stop word from the stopWordStr hash map
        Object obj=stopWordStr.get(givenWord);
        
        //If it is not null then that means it is a stop word
        if(obj!=null)
        {
            return true;
        }
        //If it is null then that means it is not a stop word
        else
        {
            return false;
        }
    }
    
    /**
     * This method is used by the default constructed Passage object to produce
     * the stopWordStr to contain all of the stop words from StopWords.txt
     * 
     * @param file 
     *  The file to get the stop words from
     */
    public void produceStopWord(File file)
    {
        try
        {
            //Together this reads the lines in stopWord.txt to accumlate each words
            //into stopWordStr
            FileInputStream input=new FileInputStream(file);
            
            InputStreamReader inputReader=new InputStreamReader(input);
            BufferedReader reader=new BufferedReader(inputReader);
            
            //eachLine will be storing each of the lines
            String eachLine=reader.readLine();
            
            //If the lines are not empty then we put eachLine into the stopWordStr map
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
    
    /**
     * This method calculates the similarity between two Passage objects using the
     * the cosine forumla and it modifies the similarTitles
     * 
     * @param passage1
     *  passage1 represent one of the Passages to check the similarity with
     * @param passage2
     *  passage2 represent the other Passages to check the similarity with
     * @return 
     *  Return the percentages of similar between the two given passages
     */
    public static double cosineSimilarity(Passage passage1, Passage passage2)
    {
        //Output will be storing the output
        double output;
        
        //Getting the two similarTitles from the two passages, it will be used to
        //put update their respective similarTitles
        HashMap similar1=passage1.getSimilarTitles();
        HashMap similar2=passage2.getSimilarTitles();
        
        //Getting the name of the two Passages
        String passage1Name=passage1.getTitle();
        String passage2Name=passage2.getTitle();
        
        //Getting the two set of words from the two Passages
        Set<String> words1=passage1.getWords();
        Set<String> words2=passage2.getWords();
        
        //Getting the length of the words 
        int length1=words1.size();
        int length2=words2.size();
        
        //topPortion will be storing the sums of the numerator for the forumla
        ArrayList<Double> topPortion=new ArrayList();
        
        //botLeftPart will be storing the squared numbers of passage1's word frequencies
        ArrayList<Double> botLeftPart=new ArrayList();
        
        //botRightPart will be storing the squared numbers of passage2's word frequencies
        ArrayList<Double> botRightPart=new ArrayList();
        
        //If length2 is greater than length1 then we use the set1 becausee
        //all the words that is not in set2 will not be in set1 thus the value will be
        //0 anyway therefore there is no point in putting them in
        if(length1<length2)
        {
            //Going through each word set and getting the frequencies
            for(String word:words1)
            {
                double passage1Freq=passage1.getWordFrequency(word);
                double passage2Freq=passage2.getWordFrequency(word);
                
                //Multiplying them and storing them
                topPortion.add(passage1Freq*passage2Freq);
            }
        }
        //This means to use set2 instead because set2 is smaller
        else
        {
            //Going through each word set and getting the frequencies
            for(String word:words2)
            {
                double passage1Freq=passage1.getWordFrequency(word);
                double passage2Freq=passage2.getWordFrequency(word);
                
                //Multiplying them and storing them
                topPortion.add(passage1Freq*passage2Freq);
            }
        }
        
        //This for loop to get the total squared sum for the passage1
        for(String word:words1)
        {
            double passage1Freq=passage1.getWordFrequency(word);

            botLeftPart.add(Math.pow(passage1Freq, 2));
        }
        
        //This for loop will get the total squared sum for the passage2
        for(String word:words2)
        {
            double passage2Freq=passage2.getWordFrequency(word);

            botRightPart.add(Math.pow(passage2Freq, 2));
        }
        
        //Then we just sum all of numbers inside topPortion
        double sumOfTop=0;
        for(Double d:topPortion)
        {
            sumOfTop+=d;
        }
        
        //Sum up all the numbers inside botLeftPart
        double sumOfLeft=0;
        for(Double d:botLeftPart)
        {
            sumOfLeft+=d;
        }
        
        //Sum up all the numbers inside botRightPart
        double sumOfRight=0;
        for(Double d:botRightPart)
        {
            sumOfRight+=d;
        }
        
        //Then we use the forumla
        output=(sumOfTop)/(Math.sqrt(sumOfLeft)*Math.sqrt(sumOfRight));
        
        //Multiplying by 100 to get the percentages
        output=output*100;
        
        //keyValue1 and keyValue2 will be representing whether or not that passage
        //is already inside the passage
        Object keyValue1=similar1.get(passage2Name);
        Object keyValue2=similar2.get(passage1Name);
        
        //This means that passage2's similarity is not present in passage1
        if(keyValue1==null)
        {
            similar1.put(passage2Name, output);
        }
        
        //This means that passage1's similarity is not present in passage2
        if(keyValue2==null)
        {
            similar2.put(passage1Name, output);
        }
        
        return output;
    }
    
    /**
     * This method returns the relative frequency of the given word in this
     * Passage
     * 
     * @param word
     *  The word to find the frequency in this Passage
     * @return Returns a double that represent the frequency of word in this Passage
     */
    public double getWordFrequency(String word)
    {
        //Ouput will be storing the frequency
        double output;
        
        //Getting the word object from this map
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
    
    /**
     * Returns a Set that contains all of the words in this Passage
     * 
     * @return Returns a Set of String that contain all of the words of this Passage
     */
    public Set<String> getWords()
    {
        //Getting the keySet
        Set<String> output=this.keySet();
        
        return output;
    }
    
    /**
     * Returns the title of this Passage
     * 
     * @return Returns a String that represent the title of this Passage
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Return the total wordCount that this Passage have excluding the stop words
     * 
     * @return Returns a int that represent the total word count of Passage not
     *  including the stop words
     */
    public int getWordCount()
    {
        return wordCount;
    }
    
    /**
     * Return the HashMap similarTitles that represent all of the Passage similar
     * percentage with other Passages
     * 
     * @return Returns a HashMap that contains information about the similarity
     *  percentage with other Passage
     */
    public HashMap getSimilarTitles()
    {
        return similarTitles;
    }
    
    /**
     * Mutator method for the Passage's title
     * <p>
     * Postcondition: Passage's title is set to the given title
     * 
     * @param title 
     *  The title to set the Passage's title to
     */
    public void setTitle(String title)
    {
        this.title=title;
    }
    
    /**
     * Mutator method for the Passage's similarTitles
     * <p>
     * Postcondition: Passage's similarTitle is set to the given similarTitle
     * 
     * @param similarTitles 
     *  The similarTitles to set the Passage's similarTitles to
     */
    public void setSimilarTitles(HashMap similarTitles)
    {
        this.similarTitles=similarTitles;
    }
    
    /**
     * ToString method of the Passage class that returns a String of similar titles
     * and their percentage similarity
     * 
     * @return Returns a String that represent the similar titles and their percentage
     * similarity
     */
    public String toString()
    {
        String output="";
        
        Set<String> titles=similarTitles.keySet();
        
        for(String s:titles)
        {
            output+=s+" ("+df.format(similarTitles.get(s))+"%), ";
        }

        return output;
    }
    
    
    
    
    
    
}   
