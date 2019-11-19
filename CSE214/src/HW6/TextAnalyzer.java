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
        
        File directoryOfFiles[]=new File(userInput).listFiles();
        
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
                String fileName=i.getName();
                
                int comma=fileName.indexOf(".");
                
                String title=fileName.substring(0,comma);
                
                Passage toBeAdded=new Passage(title,i);
                
                //ASdding the passage into the array of passages
                passages.add(toBeAdded);
                
                titles.add(title);
            }
        }
        
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
        
        String titleFormat="%-35s|%-35s";
        System.out.println(String.format(titleFormat, "Text (title)","Similarities (%)"));
        System.out.println("---------------------------------------------------------"
                + "--------------------------------------------------------------");
        
        String formattedPercent="";
        
        for(Passage p:passages)
        {
            HashMap map=p.getSimilarTitles();
            
            for(String name:titles)
            {
                if(!name.equals(p.getTitle()))
                {
                    formattedPercent+=name+" ("+df.format((double)map.get(name))+"%),";
                }
            }
            
            System.out.println(String.format(titleFormat, p.getTitle(),formattedPercent));
            
            System.out.println("----------------------------------------------------------"
                    + "----------------------------------------------------------");
            
            formattedPercent="";
        }
        
        System.out.println("Suspected Texts With Same Authors");
        System.out.println("----------------------------------------------------------------------------");
        
        for(Passage p:passages)
        {
            HashMap map=p.getSimilarTitles();
            
            for(Passage p2:passages)
            {
                String name=p2.getTitle();
                
                HashMap map2=p2.getSimilarTitles();
                
                if(!name.equals(p.getTitle()))
                {
                    Object obj=map.get(name);
                    
                    if(obj!=null)
                    {
                        double similarPercent=(double)obj;
                        
                        if(similarPercent>=60)
                        {
                            System.out.println("'"+p.getTitle()+"' and '"+name+"' may"
                                    + " have the same author ("+df.format(similarPercent)+"% similar).");
                            map.remove(name);
                            map2.remove(p.getTitle());
                        }
                    }
                }
            }
        }
        
        
            
        
    }
}