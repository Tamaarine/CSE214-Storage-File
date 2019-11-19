package HW6;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.text.DecimalFormat;

public class TextAnalyzer
{
    //Instance variables
    FrequencyTable frequencyTable;
    
    public TextAnalyzer(FrequencyTable givenTable)
    {
        frequencyTable=givenTable;
    }
    
    public static void main(String [] args)
    {
        DecimalFormat df=new DecimalFormat("0");
        
        TextAnalyzer analyzer;
        
        InputStreamReader input=new InputStreamReader(System.in);
        BufferedReader reader=new BufferedReader(input);
        
        String userInput="";
        
        Passage dummyPassage=new Passage();
        
        //Asking the user for the file directory input
        System.out.print("Enter the directory of a folder of text files: ");
        
        try
        {
            userInput=reader.readLine();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
        
        ArrayList<Passage> passages=new ArrayList();
        ArrayList<String> titles=new ArrayList();
        
        File directoryOfFiles[]=new File("C:\\Users\\FreezyCold\\Desktop\\CSE214 Storage File\\HW6 Test").listFiles();
        
        //This is the file to store the location of the StopWords
        //Since we are guranteed that StopWords.txt will be in the directory
        //the initial value of stopWordFile does not matter
        File stopWordFile=new File("Default");
        
        //This for loop will be responsible for looking for the StopWordFIle in the given
        //directory
        for(File i:directoryOfFiles)
        {
            if(i.getName().equals("StopWords.txt"))
            {
                stopWordFile=i;
            }
        }
        
        //Using the dummyPassage we create the static stopWordStr for all of the rest
        //of the passages
        dummyPassage.produceStopWord(stopWordFile);
        
        //This for loop will be responsible for adding each passages and
        //creating the stopWordStr in each of them
        for(File i:directoryOfFiles)
        {
            if(!i.getName().equals("StopWords.txt"))
            {
                String title=i.getName();
                
                Passage toBeAdded=new Passage(title,i);
                
                //ASdding the passage into the array of passages
                passages.add(toBeAdded);
                
                titles.add(title);
            }
        }
        
        FrequencyTable toBeAdded=FrequencyTable.buildTable(passages);
        
        analyzer=new TextAnalyzer(toBeAdded);
        
        for(int i=0;i<passages.size();i++)
        {
            for(int j=0;j<passages.size();j++)
            {
                Passage p1=passages.get(i);
                Passage p2=passages.get(j);
                
                if(i!=j)
                {
                    Passage.cosineSimilarity(passages.get(i),passages.get(j));
                }
            }
        }
        
        String titleFormat="%-40s|%-50s";
        System.out.println(String.format(titleFormat, "Hello there mother","HIIIIIiiii"));
        
        String formattedPercent="";
        
        for(Passage p:passages)
        {
            HashMap map=p.getSimilarTitles();
            
            for(String name:titles)
            {
                if(!name.equals(p.getTitle()))
                {
                    formattedPercent+=name+df.format((double)map.get(name)*100)+",";
                }
            }
            
            System.out.println(String.format(titleFormat, p.getTitle(),formattedPercent));
            
            formattedPercent="";
        }
        
        System.out.println("------------");
        
        Passage pass1=new Passage("Night",new File("C:\\Users\\FreezyCold\\Desktop\\CSE214 Storage File\\HW6 Test\\Frankenstein.txt"));
        Passage pass2=new Passage("Hey",new File("C:\\Users\\FreezyCold\\Desktop\\CSE214 Storage File\\HW6 Test\\ThatLittleSquareBox.txt"));
        
        System.out.println(Passage.cosineSimilarity(pass1, pass2));
        
        System.out.println(passages.get(2).wordCount);
         
    }
}