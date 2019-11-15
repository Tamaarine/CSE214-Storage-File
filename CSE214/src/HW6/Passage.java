package HW6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;

public class Passage extends HashMap
{
    //Instance variables
    private String title;
    private int wordCount;
    private HashMap similarTitles;
    
    //Paramaterized constructor
    public Passage(String title, File file)
    {
        this.title=title;
        
        parseFile(file);
        
    }
    
    public static double cosineSimilarity(Passage passage1, Passage passage2)
    {
        
        
        
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
            
            String eachLine=reader.readLine();
            
            int indexOfSpace=eachLine.indexOf(" ");
            
            String eachWord=eachLine.substring(0,indexOfSpace);
            
            while(!eachWord.isEmpty())
            {
                int hashValue=eachWord.hashCode();
                
                put(hashValue,eachWord);
                
                indexOfSpace=eachLine.indexOf(" ");
                
                eachWord=eachLine.substring(0,indexOfSpace);
                
                
                
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
        
        
        
    }
    
    //Accessor method
    public double getWordFrequency(String word)
    {
        
    }
    
    public Set<String> getWords()
    {
        
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
        
    }
    
    
    
    
    
    
}   
