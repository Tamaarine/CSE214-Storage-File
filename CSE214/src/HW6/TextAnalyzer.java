package HW6;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
        TextAnalyzer analyzer;
        
        InputStreamReader input=new InputStreamReader(System.in);
        BufferedReader reader=new BufferedReader(input);
        
        String userInput="";
        
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
        
        File directoryOfFiles[]=new File(userInput).listFiles();
        
        for(File i:directoryOfFiles)
        {
            Passage toBeAdded=new Passage(i.getName(),i);
            
            passages.add(toBeAdded);
            
            System.out.println(toBeAdded);
        }
        
        FrequencyTable toBeAdded=FrequencyTable.buildTable(passages);
        
        analyzer=new TextAnalyzer(toBeAdded);
        
        Passage passage1=new Passage("adsfa", new File("C:\\Users\\Rwiky\\Desktop\\CSE214-Storage-File\\HW6 Test\\Testing.txt"));
        Passage passage2=new Passage("adsfa", new File("C:\\Users\\Rwiky\\Desktop\\CSE214-Storage-File\\HW6 Test\\Testing2.txt"));
        
        
        System.out.println(Passage.cosineSimilarity(passage1, passage2));
        
        
    }
}