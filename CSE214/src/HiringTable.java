/**
 * The HiriningTable class stores an Array of Applicants and is responsible for handling
 * anything that has to do with each Applicants such as, adding new Applicants into the 
 * HiringSystem, returning information about an specific Applicant, returning the number
 * of Applicants in the system, displaying a nicely formatted table that contains each
 * Applicants, and searching for a Applicants that contains certain qualities.
 * <p> It implements Cloneable in order to create a clone of itself
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class HiringTable implements Cloneable
{
    //Static final variable to represent the maximum of skills an Applicant can have
    static final int MAX_SKILLS=3;
    
    //Static final variable to represent the maximum of companies an Applicant can have
    static final int MAX_COMPANIES=3;
    
    //Static final variable to represent the maximum capacity of the Applicant array
    static final int MAX_APPLICANTS=50;
    
    private Applicant applicants[]; //An array that stores the Applicants
    
    //An counter variable to keep track of how many Applicants is in the array
    private int applicantCounter;
    
    /**
     * The default constructor of the HiringTable class. It initializes the
     * applicants array to the maximum amount of Applicants in the table, and
     * also sets the appliacntCounter to be 0
     * <p>
     * Postcondition: The HiringTable has been initialized to an empty list of Applicants
     * 
     */
    public HiringTable()
    {
        applicants=new Applicant[MAX_APPLICANTS];
        applicantCounter=0;
    }
    
    /**
     * Returns the amount of Applicants that is in the array
     * <p>
     * Preconditions: The HiringTable has been instantiated
     * 
     * @return Returns the number of Applicants that is in the array
     */
    public int size()
    {
        return applicantCounter;
    }
    
    /**
     * Adds the given Applicant into the applicants Array
     * <p>
     * The Applicant object has been instantiated and the number of applicants
     * in the HiringTable is less than MAX_APPLICANTS
     * <p>
     * Postcondition: The new Applicant is now inserted at the end of the list
     * 
     * @param newApplicant
     *  the given Applicant to be added into the applicants Array
     * @throws FullTableException 
     *  FullTableException is thrown if the applicants Array is at maximum capacity
     *  to indicate that there is no more spaces in the Array for the given Applicant
     */
    public void addApplicant(Applicant newApplicant) throws FullTableException
    {
        //This means that there is still spaces in the Array therefore we add the
        //new Applicant and increment the counter
        if(applicantCounter<MAX_APPLICANTS)
        {
            applicants[applicantCounter]=newApplicant;
            
            applicantCounter++;
            
        }
        //This means that there is no more spaces left in the array thus we need to
        //throw the FullTableException
        else
        {
            throw new FullTableException("The Hiring Table is full, therefore we"
                    + " can not add the new applicant\n");
        }
    }
    
    /**
     * Fetch the Applicant that match the specified name in the applicants Array
     * <p>
     * Precondition: The HiringTable object has been instantiated
     * 
     * @param name
     *  The name to fetch in the applicants Array
     * @return Returns the Applicant that have the matching name
     * @throws ApplicantNotFoundException 
     *  ApplicantNotFoundException is thrown to indicate that in the applicants
     *  Array there is no matching Applicants with the given name
     */
    public Applicant getApplicant(String name) throws ApplicantNotFoundException
    {
        Applicant answer=new Applicant();
        
        for(int i=0;i<applicantCounter;i++)
        {
            if(applicants[i].getApplicantName().equals(name))
            {
                answer=applicants[i];
                
                return answer;
            }
        }
        
        //If we are outside of the for looop that means that the Applicant is
        //not found thus we have to throw the exception
        throw new ApplicantNotFoundException("Applicant name you entered not found");
    }
    
    /**
     * The accessor method to get the applicants Array
     * 
     * @return Returns the applicants Array that stores all the Applicants
     */
    public Applicant[] getApplicantList()
    {
        return applicants;
    }
    
    /**
     * Creates a new reference to a HiringTable that have the same applicants Array
     * and the same applicantCounter
     * 
     * <p>
     * Preconditions: Method requires Applicant class to implement Cloneable
     * <p>
     * Postconditions: A new reference to an HiringTable object with same applicants
     * Array and the same applicantCounter
     * 
     * @return Returns a new reference to a HiringTable that have the same applicants
     * Array and the same applicantCounter
     * @throws CloneNotSupportedException
     *  The CloneNotSupportedException is thrown if the object is not cloned successfully
     */
    public Object clone() throws CloneNotSupportedException 
    {
        HiringTable answer=new HiringTable();
        
        //By calling super.clone() this is only a shallow clone because even
        //though the primitives are copied, the arrays are not copied
        //they are just given the same reference address to the same array
        //thus we need to perform deep cloning for applicantts
        try
        {
            answer=(HiringTable)super.clone();
        }
        catch(CloneNotSupportedException c)
        {
            System.out.println("Cloning hiring table unsuccessful");
        }
        
        answer.applicants=new Applicant[MAX_APPLICANTS];
        
        //Deep copying for the Applicant array
        for(int i=0;i<applicantCounter;i++)
        {
            answer.applicants[i]=(Applicant)applicants[i].clone();
        }
        
        return answer;
    }
    
    /**
     * Print out a neatly formatted table of each item in the list
     * 
     * <p>
     * Precondition: The HiringTable has been instantiated
     * <p>
     * Postcondition: Displays a neatly formatted table of each Applicant from the
     * HiringTable
     * 
     */
    public void printApplicantTable()
    {
        String topFormat="%-50s%-15s%10s%25s%40s";
        
        System.out.println(String.format(topFormat, "Company Name","Applicant","GPA","College","Skills"));
        System.out.println("---------------------------------------------------"
                + "-----------------------------------------------------------"
                + "------------------------------");
        
        for(int i=0;i<applicantCounter;i++)
        {
            Applicant applicantAtI=applicants[i];
            
            String printFormat="%-50s%-15s%10s%25s%40s";
            String applicantName=applicantAtI.getApplicantName();
            String applicantCollege=applicantAtI.getApplicantCollege();
            String applicantGPA=applicantAtI.getGPA()+"";
            String applicantCompany=applicantAtI.getFormatCompanyName();
            String applicantSkills=applicantAtI.getFormatSkills();
            
            System.out.println(String.format(printFormat,applicantCompany,applicantName,applicantGPA,applicantCollege,applicantSkills));
        }
       
        
        
    }

    /**
     * Remove the specified Applicant name from the applicants Array
     * 
     * <p>
     * Precondition: The HiringTable has been instantiated
     * <p>
     * Postcondition: The Applicant with the name given has been removed from the
     * list. Any Applicant that was in a spot after the removed Applicant are shifted
     * upwards one spot.
     * 
     * @param name
     *  The Applicant name that needs to be removed from the applicants Array
     * @throws ApplicantNotFoundException 
     *  Indicates that the applicant with the given name was not found
     */
    public void removeApplicant(String name) throws ApplicantNotFoundException
    {
        //This variable will be holding the index of the Applicant to be removed
        //initally it will be -1 to indicate that it hasn't been found yet until later
        int removePos=-1;
        
        //This for loop is used to find the Applicant within the applicant Array
        for(int i=0;i<applicantCounter;i++)
        {
            if(applicants[i].getApplicantName().equals(name))
            {
               removePos=i;
            }
        }
        
        //This means that the Applicant we need to removed is within the Array
        //thus we need to remove it
        if(removePos!=-1)
        {
            //This for loop handles the shifting of the each Applicant
            for(int i=removePos;i<applicantCounter;i++)
            {
                if(i==applicantCounter-1)
                {
                    applicants[i]=null;
                }
                else
                {
                    applicants[i]=applicants[i+1];
                }
            }
            
            //Decrementing the applicantCounter
            applicantCounter--;
            
            System.out.println("Applicant "+name+" has been successfully removed from the hiring table\n");
        }
        //This means that the Applicant is not found within the Array therefore
        //throw the ApplicantNotFoundException
        else
        {
            throw new ApplicantNotFoundException(name+" is not found in the hiring system");
        }
    }
    
    /**
     * Filter out and find the Applicants that matches the different listed traits
     * 
     * <p>
     * Precondition: The HiringTable object has been instantiated
     * <p>
     * Postcondition: Display a neatly formatted table of each Applicant filtered
     * from the HiringTable
     * 
     * @param table
     *  The list of applicants to search in
     * @param company
     *  The company must be in the Applicant's application
     * @param skill
     *  The skill that must be in the Applicant's application
     * @param college
     *  The college that must be in the Applicant's application
     * @param GPA 
     *  The minimum GPA that must be in the Application's application. It is set
     *  to 0 if the user didn't specify one
     */
    public static void refineSearch(HiringTable table, String company, String skill, String college, double GPA)
    {
        //This Array of Applicant holds the ones that went through the first
        //process of filtering which is with college and GPA
        Applicant[] filteredApplicants=new Applicant[MAX_APPLICANTS];
        
        //This table is meant to hold the Applicant that have all of the traits
        //so that at the end we just need to call printApplicantTable when displaying
        HiringTable filteredTable=new HiringTable();
        
        //This is the counter variable to keep track of how many Applicants went
        //through the first process of filtering with college and GPA
        int  filteredCounter=0;
        
        for(int i=0;i<table.size();i++)
        {
            //The possible candidate
            Applicant candidate=table.getApplicantList()[i];
            
            //This means that if the candidate have a GPA greater than the given GPA
            //and that the user didn't specify to filter for any colleges. We just
            //add that to the filteredApplicants
            if(candidate.getGPA()>=GPA&&college.equals(""))
            {
                filteredApplicants[filteredCounter]=candidate;
                
                filteredCounter++;
            }
            //This means that if the candidate have a GPA greater than the given GPA
            //and that the college user specified matches the Applicant's college
            //he/she is a qualifying Applicant
            else if(candidate.getGPA()>=GPA&&candidate.getApplicantCollege().equals(college))
            {
                filteredApplicants[filteredCounter]=candidate;
                
                filteredCounter++;
            }
        }
        
        //This Array of boolean will be tracking whether or not the filteredApplicant
        //have the matching company
        boolean qualifyWithCompany[]=new boolean[filteredCounter];
        
        //This Array of boolean will be tracking whether or not the filteredApplicant
        //have the matching skill
        boolean qualifyWithSkill[]=new boolean[filteredCounter];
        
        //This for loop will be going through each of the filteredApplicants to check whether
        //or not they have qualifying skills and company
        for(int i=0;i<filteredCounter;i++)
        {
            //This means that the user did entered a company to filter for
            if(!company.equals(""))
            {
                //This returns the index position of the company within the
                //companies in a String
                int possiblePos=filteredApplicants[i].getConvertedCompany().indexOf(company);
                
                //This means that the company specified by the user is within the company String
                //however we must do further work to check if it is actually the matching company
                if(possiblePos!=-1)
                {
                    //This returns the specified company's String length
                    int companyLength=company.length();
                    
                    //This will return the character right after the matching company
                    //If it is a $, that means the converted String ends there
                    //However if it is not a $ but other letter that means that 
                    //The specified company is not in the Applicant's company
                    String maybeCompany=filteredApplicants[i].getConvertedCompany().substring(possiblePos+(companyLength),possiblePos+(companyLength+1));
                    
                    if(maybeCompany.equals("$"))
                    {
                        qualifyWithCompany[i]=true;
                    }
                    else
                    {
                        qualifyWithCompany[i]=false;
                    }
                    
                }
            }
            //This means that the user did not enter a company to filter for therefore
            //any Applicant is qualified
            else
            {
                qualifyWithCompany[i]=true;
            }
            
            //This means that the user did entered a skill to filter for
            //thus we need to check whether each Applicant is qualified
            if(!skill.equals(""))
            {
                //This returns the index position of the skill within the
                //skills in a String
                int possiblePos=filteredApplicants[i].getConvertedSkill().indexOf(skill);
                
                //This means that the skill specified by the user is within the skill String
                //however we must do further work to check if it is actually the matching skill
                if(possiblePos!=-1)
                {
                    //This returns the specified skill's String length
                    int skillLength=skill.length();
                    
                    //This will return the character right after the matching skill
                    //If it is a $, that means the converted String ends there
                    //However if it is not a $ but other letter that means that 
                    //The specified skill is not in the Applicant's skills
                    String maybeSkill=filteredApplicants[i].getConvertedSkill().substring(possiblePos+(skillLength),possiblePos+(skillLength+1));
                    
                    if(maybeSkill.equals("$"))
                    {
                        qualifyWithSkill[i]=true;
                    }
                    else
                    {
                        qualifyWithSkill[i]=false;
                    }
                }
            }
            //This means that the user did not enter a skill to filter for therefore
            //any Applicant is qualified
            else
            {
                qualifyWithSkill[i]=true;
            }
            
        }
        
        //Finally we use a for loop to cycle thorugh the two boolean Array
        //If they are both true it means that the Applicant is the qualifies
        //Thus we add it to the filteredTable and display
        for(int i=0;i<filteredCounter;i++)
        {
            if(qualifyWithCompany[i]&&qualifyWithSkill[i])
            {
                try
                {
                    filteredTable.addApplicant(filteredApplicants[i]);
                }
                catch(FullTableException f)
                {
                    System.out.println(f);
                }
            }
        }
        
        filteredTable.printApplicantTable();
    }
    
}