package HW6;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.text.DecimalFormat;

/**
 * The TextAnalyzer class will be the main driver application. It will prompt the user
 * to enter in the directory of a folder when it begins. After processing all of the
 * text file in the folder it will determine the relations between each Passage
 * object by showing the similarity percentages. And it will end by determining which text
 * is written by the same author. 60% or above will represent that two of the Passages
 * are written by the same author
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class TextAnalyzer
{
    //This variable will be representing the FrequencyTable
    FrequencyTable frequencyTable;
    
    /**
     * The main method will start the program and then ask the user for a
     * folder directory in order to process 
     * 
     * @param args 
     *  args contains the text from the command line that starts the program
     */
    public static void main(String [] args)
    {
        //This will represent whether or not the user have entered in a valid folder
        //path for the program
        boolean validFile=false;
        
        //Keep looping until the user have entered in a valid path
        while(!validFile)
        {
            try
            {
                //DecimalFormat will be helping to do the roundings
                DecimalFormat df=new DecimalFormat("0");

                //InputStreamReader and reader will be responsible for reading in the
                //file input by the users
                InputStreamReader input=new InputStreamReader(System.in);
                BufferedReader reader=new BufferedReader(input);

                String userInput="";

                //dummyPassage will be used for the construction of the stopWordStr
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

                //Creating a new ArrayList of passages to store them in
                ArrayList<Passage> passages=new ArrayList();

                //This will be the array of files that the program need to be reading from
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
                        //Taking out the .txt
                        String fileName=i.getName();
                        int comma=fileName.indexOf(".");
                        String title=fileName.substring(0,comma);

                        Passage toBeAdded=new Passage(title,i);

                        //ASdding the passage into the array of passages
                        passages.add(toBeAdded);
                    }
                }

                //This for loop will be going through each and every passages and
                //calling the cosineSImilarity between two pair of Passages
                for(int i=0;i<passages.size();i++)
                {
                    for(int j=0;j<passages.size();j++)
                    {
                        if(i!=j)
                        {
                            Passage.cosineSimilarity(passages.get(i),passages.get(j));
                        }
                    }
                }
                
                //Formatting the title of the output
                String titleFormat="%-35s|%-35s";
                System.out.println(String.format(titleFormat, "Text (title)","Similarities (%)"));
                System.out.println("---------------------------------------------------------"
                        + "--------------------------------------------------------------");

                //Printing out the similarity results
                for(Passage p:passages)
                {
                    System.out.println(String.format(titleFormat, p.getTitle(),p));

                    System.out.println("----------------------------------------------------------"
                            + "----------------------------------------------------------");
                }

                //This part is reponsible for determining which two Passages have the
                //same author
                System.out.println("Suspected Texts With Same Authors");
                System.out.println("----------------------------------------------------------------------------");

                //This for loop will be responsible for printing out the suspected
                //Passage that have the same authors. It will remove the one that
                //is already printed to prevent duplicates from printing
                for(Passage p:passages)
                {
                    //The HashMap of the p
                    HashMap map=p.getSimilarTitles();

                    //This will be the second passage to compare p to
                    for(Passage p2:passages)
                    {
                        //Getting the name of the second passage
                        String name=p2.getTitle();

                        //Getting the similarTitle of the second passage
                        HashMap map2=p2.getSimilarTitles();

                        //This means that to only check if p and p2 are different
                        if(!name.equals(p.getTitle()))
                        {
                            //Getting the similar percentage of p2 in p
                            Object obj=map.get(name);

                            if(obj!=null)
                            {
                                double similarPercent=(double)obj;

                                //Checking the similarity percentage if it is
                                //greater than or equal to 60 we will print it out
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
                
                //Since we have reached here that means the program had no trouble in running
                //therefore make validFile to true to stop the loop
                validFile=true;
                
                //Have a great day!
                System.out.println("Program terminating... Have a great day ^ w ^!!!");
            }
            catch(Exception e)
            {
                System.out.println("Invalid file path please try again");
            }
        }
    }
}