/**
 * The Applicant class is meant to model an applicant that will be applying to a
 * company. It will contain applicant's name, GPA, college, skills that he/she have
 * and the companies that he/she is applying to.
 * <p> It implements Cloneable interface in order to create a clone of itself
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */

public class Applicant implements Cloneable
{
    private String companyName[]; //An array that holds the applicant's company
    private String applicantSkills[]; //An array that holds the applicant's skill
    
    //This variable is meant to hold the applicant's company in a string format
    //it is used in refineSearch
    private String companyNameString;
    
    //This variable is meant to hold the applicant's skill in a string format
    //it is used in refineSearch
    private String applicantSkillString; 
    
    //This variable is meant to hold all of applicant's company in a nicely formatted string
    //it is used in toString
    private String formatCompanyName;
    
    //This variable is meant to hold all of applicant's skill in a nicely formatted string
    //it is used in toString
    private String formatSkills;
    
    private String applicantName; //This is applicant's name in a String
    private double applicantGPA; //This is applicant's GPA in a double
    private String applicantCollege; //This is applicant's college in a String
    
    //This counter variable is used to keep track of how many skills an applicant have
    private int skillCounter;
    
    //This counter variable is used to keep track of how many company an applicant have
    private int companyCounter;
    
    
    /**
     * The default constructor of Applicant class it is just used to constructor
     * a John Doe Applicant if no information is given, later on you can use mutator
     * methods to change a default Applicant's information
     */
    public Applicant()
    {
        //Setting everything to the default constructor
        skillCounter=0;
        companyCounter=0;
        
        companyName=new String[HiringTable.MAX_COMPANIES];
        applicantSkills=new String[HiringTable.MAX_SKILLS];
        applicantName="No Name";
        applicantGPA=0.0;
        applicantCollege="None";
        companyNameString="";
        applicantSkillString="";
        formatCompanyName="";
        formatSkills="";
    }
    
    /**
     * The parameterized constructor for a Applicant when given specific information
     * 
     * @param givenCompany
     *  The array of company that the Applicant have worked for
     * @param givenName
     *  The name of the Applicant
     * @param givenGPA
     *  Applicant's GPA
     * @param givenCollege
     *  The name of the college that Applicant went to
     * @param givenSkills
     *  The array of skills that the Applicant have
     * @throws NegativeException 
     *  If the given GPA is less than zero
     */
    public Applicant(String givenCompany[],String givenName,double givenGPA,String givenCollege,String givenSkills[]) throws NegativeException
    {
        //Setting the counters to 0 initially
        skillCounter=0;
        companyCounter=0;
        
        //Count the non-empty String in the array of given companies 
        for(int i=0;i<givenCompany.length;i++)
        {
            if(givenCompany[i]!=null)
            {
                companyCounter++;
            }
        }
        
        //Count the non-empty String in the array of given skills
        for(int i=0;i<givenSkills.length;i++)
        {
            if(givenSkills[i]!=null)
            {
                skillCounter++;
            }
        }
        
        
        companyName=givenCompany;
        applicantName=givenName;
        
        //This means that the user have inputted a negative GPA therefore
        //This applicant is invalid throw the exception
        if(givenGPA<0)
        {
            throw new NegativeException("Applicant have negative GPA, applicant "
            +applicantName+" has not been successfully added to the hiring system\n");
        }
        else
        {
            applicantGPA=givenGPA;
        }
        
        applicantCollege=givenCollege;
        applicantSkills=givenSkills;
        
        //The purpose of this for loop is to construct the Applicant's companies
        //in a String format to be used in refineSearch
        String output="";
        
        for(int i=0;i<companyCounter;i++)
        {
            output+=companyName[i]+"$";
        }
        
        companyNameString=output;
        
        //The purpose of this for loop is to construct the Applicant's skills
        //in a String format to be used in refineSearch
        output="";
        
        for(int i=0;i<skillCounter;i++)
        {
            output+=applicantSkills[i]+"$";
        }
        
        applicantSkillString=output;
        
        //The purpose of this for loop is to construct the Applicant's companies
        //in a nicely String format to be used in toString
        output="";
        
        for(int i=0;i<companyCounter;i++)
        {
            if(i!=companyCounter-1)
            {
                output+=companyName[i]+", ";
            }
            else
            {
                output+=companyName[i];
            }
        }
        
        formatCompanyName=output;
        
        //The purpose of this for loop is to construct the Applicant's skills
        //in a nicely String format to be used in toString
        output="";
        
        for(int i=0;i<skillCounter;i++)
        {
            if(i!=skillCounter-1)
            {
                output+=applicantSkills[i]+", ";
            }
            else
            {
                output+=applicantSkills[i];
            }
        }
        
        formatSkills=output;
        
    }
    
    /**
     * The mutator method for the Applicant's company array.
     * <p>
     * Precondition: The Applicant's array of companies must be instantiated
     * <p> 
     * Postcondition: The Applicant's array of companies is set to givenCompany
     * 
     * @param givenCompany 
     *  The given array of company that you want to give to the Applicant
     */
    public void setCompanyName(String[] givenCompany)
    {
        companyName=givenCompany;
        
        //The purpose of this for loop is to construct the Applicant's companies
        //in a String format to be used in refineSearch
        String output="";
        
        for(int i=0;i<companyCounter;i++)
        {
            output+=companyName[i]+"$";
        }
        
        companyNameString=output;
        
        //The purpose of this for loop is to construct the Applicant's companies
        //in a nicely String format to be used in toString
        output="";
        
        for(int i=0;i<companyCounter;i++)
        {
            if(i!=companyCounter-1)
            {
                output+=companyName[i]+", ";
            }
            else
            {
                output+=companyName[i];
            }
        }
        
        formatCompanyName=output;
    }
    
    /**
     * The mutator method for the Applicant's skill array
     * <p>
     * Precondition: The Applicant's array of skills must be instantiated
     * <p>
     * Postcondition: The Applicant's array of skills is set to givenSkills
     * 
     * @param givenSkills 
     *  The given array of skill that you want to give to the Applicant
     */
    public void setApplicantSkills(String[] givenSkills)
    {
        applicantSkills=givenSkills;
        
        //The purpose of this for loop is to construct the Applicant's skills
        //in a String format to be used in refineSearch
        String output="";
        
        for(int i=0;i<skillCounter;i++)
        {
            output+=applicantSkills[i]+"$";
        }
        
        applicantSkillString=output;
        
        //The purpose of this for loop is to construct the Applicant's skills
        //in a nicely String format to be used in toString
        output="";
        
        for(int i=0;i<skillCounter;i++)
        {
            if(i!=skillCounter-1)
            {
                output+=applicantSkills[i]+", ";
            }
            else
            {
                output+=applicantSkills[i];
            }
        }
        
        formatSkills=output;
    }
    
    /**
     * The mutator method for the Applicant's name
     * <p>
     * Postcondition: The Applicant's name is set to givenName
     * 
     * @param givenName 
     *  The given name that you want to set Applicant's name to
     */
    public void setApplicantName(String givenName)
    {
        applicantName=givenName;
    }
    
    /**
     * The mutator method for the Applicant's GPA. It will throw NegativeException
     * if the givenGPA is less than 0 
     * <p>
     * Postcondition: The Applicant's GPA is set to givenGPA if it is not a negative number
     * 
     * @param givenGPA
     *  The given GPA that you want to set the Applicant's GPA to
     * @throws NegativeException 
     *  Indicate that the user have inputted a negative GPA for the Applicant
     */
    public void setApplicantGPA(double givenGPA) throws NegativeException
    {
        if(givenGPA<0)
        {
            throw new NegativeException("GPA < 0");
        }
        
        applicantGPA=givenGPA;
    }
    
    /**
     * The mutator method for the Applicant's company counter
     * <p>
     * Postcondition: The Applicant's company counter is set to n
     * 
     * @param n 
     *  The int to set the companyCounter to
     */
    public void setCompanyCounter(int n)
    {
        companyCounter=n;
    }
    
    /**
     * The mutator method for the Applicant's skill counter
     * <p>
     * Postcondition: The Applicant's skill counter is set to n
     * 
     * @param n 
     *  The int to set the skillCounter to
     */
    public void setSkillCounter(int n)
    {
        skillCounter=n;
    }
    
    /**
     * The mutator method for the Applicant's college
     * <p>
     * Postcondition: The Applicant's college is set to givenCollege
     * 
     * @param givenCollege 
     *  The given college that you want to set the Applicant's college to
     */
    public void setApplicantCollege(String givenCollege)
    {
        applicantCollege=givenCollege;
    }
    
    
    
    /**
     * Returns the array of company name of the Applicant
     * 
     * @return Returns the array of company name of the Applicant
     */
    public String[] getCompanyName()
    {
        return companyName;
    }
    
    /**
     * Returns the array of skills that the Applicant have
     * 
     * @return Returns the array of skills of the Applicant
     */
    public String[] getApplicantSkills()
    {
        return applicantSkills;
    }
    
    /**
     * Returns the name of the Applicant
     * 
     * @return Returns the name of the Applicant
     */
    public String getApplicantName()
    {
        return applicantName;
    }
    
    /**
     * Returns Applicant's GPA
     * 
     * @return Returns the GPA of the Applicant
     */
    public double getGPA()
    {
        return applicantGPA;
    }
    
    /**
     * Returns Applicant's college
     * 
     * @return Returns the college of the Applicant
     */
    public String getApplicantCollege()
    {
        return applicantCollege;
    }
    
    /**
     * Returns the amount of skills that the Applicant have
     * 
     * @return Returns the skillCounter for the Applicant's skills
     */
    public int getSkillCounter()
    {
        return skillCounter;
    }
    
    /**
     * Returns the amount of companies that the Applicant have
     * 
     * @return  Returns the companyCounter for the Applicant's company
     */
    public int getCompanyCounter()
    {
        return companyCounter;
    }
    
    /**
     * Returns the String form of the Applicant's skills. It is used in refineSearch
     * 
     * @return Returns the Applicant's skills in the form of a String
     */
    public String getConvertedSkill()
    {
        return applicantSkillString;
    }
    
    /**
     * Returns the String form of the Applicant's company. It is used in refineSearch
     * 
     * @return Returns the Applicant's company in the form of a String
     */
    public String getConvertedCompany()
    {
        return companyNameString;
    }
    
    /**
     * Returns the nicely formatted String of the Applicant's company. It is used in
     * toString
     * 
     * @return Returns the Applicant's company in a nicely formatted String
     */
    public String getFormatCompanyName()
    {
        return formatCompanyName;
    }
    
    /**
     * Returns the nicely formatted String of the Applicant's skill. It is used in
     * toString 
     * 
     * @return Returns the Applicant's skills in a nicely formatted String
     */
    public String getFormatSkills()
    {
        return formatSkills;
    }
    
    /**
     * Creates a new reference to a Applicant that has the same attributes
     * 
     * <p>
     * Preconditions: Method requires Applicant class to implement Cloneable
     * <p>
     * Postconditions: A new reference to an applicant object with same attributes
     * 
     * @return A new references to an Applicant object with same attributes
     * @throws CloneNotSupportedException 
     *  The CloneNotSupportedException is thrown if the object is not cloned successfully
     */
    public Object clone() throws CloneNotSupportedException
    {
        Applicant answer=new Applicant();
        
        try
        {
            answer=(Applicant)super.clone();
        }
        catch(CloneNotSupportedException c)
        {
            System.out.println(c);
        }
        
        //Deep cloning the array of skills and company. Because String are immutable
        //we can just clone the array
        answer.applicantSkills=applicantSkills.clone();
        answer.companyName=companyName.clone();
        
        return answer;
    }
    
    /**
     * Check if the given object is equals to this object
     * <p>
     * Postcondition: Returns whether or not the given object is equal to the
     * object being compared
     * 
     * @param obj
     *  The given object that you want to compare this object to
     * @return Returns a boolean to indicate whether the object is equals to the
     * object you are comparing to
     */
    public boolean equals(Object obj) 
    {
        //If the object being compared to is not a instance of Applicant
        //then there is no point in keep checking it is not the same
        if(obj instanceof Applicant)
        {
            //Turning the object into a Applicant object to in order to compare things
            Applicant candidate=(Applicant)obj;
            
            //This means that if the college of the two Applicants are not equal then
            //they are not the same
            if(!candidate.applicantCollege.equals(applicantCollege))
            {
                return false;
            }
            //This means that if the name of the two Applicants are not equal then
            //they are not the same Applicant
            else if(!candidate.applicantName.equals(applicantName))
            {
                return false;
            }
            //This means that if the GPA of the two Applicants are not equal then
            //they are not the same Applicant
            else if(candidate.applicantGPA!=applicantGPA)
            {
                return false;
            }
            //This means that the given Applicant have the same college
            //same name and same GPA thus further checking is required
            else
            {
                //This means that if the two Applicants have different amount of companies
                //then it is obvious that they are not the same
                if(candidate.companyCounter!=companyCounter)
                {
                    return false;
                }
                //This means that the two Applicants have the same amount of companies
                //then we must further check if each of the company matches the other
                else
                {
                    for(int i=0;i<companyCounter;i++)
                    {
                        if(!candidate.getCompanyName()[i].equals(companyName[i]))
                        {
                            return false;
                        }
                    }
                }
                
                //If we reached here that means that the company of the Applicants are the same
                //therefore we must check if the skills attributes are the same
                if(candidate.skillCounter!=skillCounter)
                {
                    return false;
                }
                //This means that the two Applicants also have the same amount of skills
                //Therefore we need to proceed to check if they are the same
                else
                {
                    for(int i=0;i<skillCounter;i++)
                    {
                        if(!candidate.getApplicantSkills()[i].equals(applicantSkills[i]))
                        {
                            return false;
                        }
                    }
                }
                
                //If we then reached here it is for sure that the two Applicants are the same
                return true;
            }
        }
        
        //This means that the Object given isn't even a Applicant thus return false
        return false;
    }
    
    /**
     * Returns a String representation of the Applicant object
     * 
     * @return A String that is nicely formatted that contains Applicant's information
     */
    public String toString()
    {
        String output="Applicant Name: "+applicantName+"\nApplicant Applying From: "
                +formatCompanyName+"\nApplicant GPA: "+applicantGPA+"\nApplicant College: "
                +applicantCollege+"\nApplicant Skills: "+formatSkills+"\n";
        
        return output;
        
        
    }
}