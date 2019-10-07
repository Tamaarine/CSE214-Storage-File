import java.io.*;

/**
 * The HiringSystem acts as the driver for the application that first creates
 * an empty HiringTable object and then the program will prompts multiple command
 * for the user to execute in order to perform an operation. The user will be asked
 * for more information if it is needed to perform certain operation
 * <p>
 * (A) Add Application Adds a new applicant onto the end of the list
 * <p>
 * (R) Remove Applicant Removes the applicant based on name
 * <p>
 * (G) Get Application Displays the information of the applicant based on the name entered
 * <p>
 * (P) Print List Prints all of the applicants in the list
 * <p>
 * (RS) Refine Search Returns a list of applicants that have met the search criteria
 * <p>
 * (S) Size Displays the number of applicants in the list
 * <p>
 * (B) Backup Creates a backup of the list in memory
 * <p>
 * (CB) Compare Backup Checks if the current list and the backup are the same
 * <p>
 * (RB) Revert Backup Reverts to the previously backed-up list
 * <p>
 * (Q) Quit Terminates the program
 * 
 * @author Ricky Lu
 *  email: ricky.lu@stonybrook.edu
 *  Stony Brook ID: 112829937
 *  Recitation: Wednesday 11:00AM - 11:53Am
 */
public class HiringSystem
{    
    /**
     * The main method of the HiringSystem driver. This is where the execution begins
     * @param args 
     *  args is the contains the text from the command line that starts the program
     */
    public static void main(String [] args)
    {
        //This table will be the main table that the user will be interacting with
        HiringTable table=new HiringTable();
        
        //The purpose of this table is to be used as a backup when the user is
        //backing up the data
        HiringTable backupTable=new HiringTable();
        
        //This boolean variable is a flag variable to control whether the user
        //is finished with the program or not
        boolean finish=false;
        
        //The InputStreamReader and the BufferedReader is used together to read
        //the user inputs on the keyboard
        InputStreamReader input=new InputStreamReader(System.in);
        BufferedReader reader=new BufferedReader(input);
            
        //Menu options
        System.out.println("(A) Add applicant\n"
                +"(R) Remove applicant\n"
                +"(G) Get Applicant\n"
                +"(P) Print List\n"
                +"(RS) Refine Search\n"
                +"(S) Size\n"
                +"(B) Backup\n"
                +"(CB) Compare Backup\n"
                +"(RB) Revert Backup\n"
                +"(Q) Quit\n");
        
        while(!finish)
        {
            System.out.print("Please enter a command: ");
            
            String userInput="";
            
            //This is trying to read the input of the user on the keyboard
            try
            {
                userInput=reader.readLine();
            }
            catch(IOException i)
            {
                System.out.println("User input error");
            }
            
            //This means that the user have inputted A which means to add a
            //new Applicant into the table
            if(userInput.equalsIgnoreCase("A"))
            {
                //Variables to store the information that users input in, remember to
                //reset every single one of them after adding Applicant to the table
                String applicantName="";
                String companyName[]=new String[HiringTable.MAX_COMPANIES];
                int companyCounter=0;
                String applicantSkills[]=new String[HiringTable.MAX_SKILLS];
                int skillCounter=0;
                double applicantGPA=0.0;
                String applicantCollege="";
                
                //The new Applicant that is going to be added to the system
                Applicant toBeAdded=new Applicant();

                //Asking the user to input the Applicant's name
                System.out.print("Enter Applicant Name: ");
                try
                {
                    applicantName=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("User input error");
                }
                
                if(!applicantName.equals(""))
                {
                    //Setting the Applicant's name
                    toBeAdded.setApplicantName(applicantName);
                    
                    //Asking the user to input the Applicant's GPA
                    System.out.print("Enter Applicant GPA: ");
                    try
                    {
                        String stringGPA=reader.readLine();

                        //These if statements ensure that if the user did not input
                        //a GPA it will be defaultly set to 0
                        if(!stringGPA.equals(""))
                        {
                            applicantGPA=Double.parseDouble(stringGPA);
                            
                            try
                            {
                                //Setting the GPA of the Applicant
                                toBeAdded.setApplicantGPA(applicantGPA);
                            }
                            catch(NegativeException n)
                            {
                                System.out.println(n);
                            }
                            
                            //Asking the user to input the Applicant's college
                            System.out.print("Enter Applicant College: ");
                            try
                            {
                                applicantCollege=reader.readLine();
                            }
                            catch(IOException i)
                            {
                                System.out.println("User input error when"
                                        + " entering college");
                            }

                            if(!applicantCollege.equals(""))
                            {
                                //Setting the college of the new Applicant
                                toBeAdded.setApplicantCollege(applicantCollege);

                                //This loop is responsible for asking the user 
                                //for the input of multiple companies
                                try
                                {
                                    boolean loopComplete=false;

                                    //This while loop will keep asking user for a
                                    //company if they didn't exceed the maximum
                                    //companies or have not entered a empty space
                                    while(companyCounter<HiringTable.MAX_COMPANIES&&
                                            !loopComplete)
                                    {
                                        System.out.print("Enter up to "+
                                                (HiringTable.MAX_COMPANIES-companyCounter)+
                                                " Companies: ");
                                        userInput=reader.readLine();

                                        if(!userInput.equals(""))
                                        {
                                           companyName[companyCounter]=userInput;
                                           companyCounter++;
                                        }
                                        else
                                        {
                                            loopComplete=true;
                                        }
                                    }
                                    
                                    //Setting the new Applicant's company and update
                                    //company counter
                                    toBeAdded.setCompanyCounter(companyCounter);
                                    toBeAdded.setCompanyName(companyName);
                                }
                                catch(IOException e)
                                {
                                    System.out.println("User input error when"
                                            + " entering the companies");
                                }
                                
                                if(companyCounter!=0)
                                {
                                    //This loop is responisble for asking the user for the input of
                                    //multiple skills
                                    try
                                    {
                                        boolean loopComplete=false;

                                        //This while loop will keep asking user for a skill if they
                                        //didn't exceed the maximum skills or have not entered a
                                        //empty space
                                        while(skillCounter<HiringTable.MAX_SKILLS&&
                                                !loopComplete)
                                        {
                                            System.out.print("Enter up to "
                                                    +(HiringTable.MAX_SKILLS-skillCounter)
                                                    +" Skills: ");
                                            userInput=reader.readLine();

                                            if(!userInput.equals(""))
                                            {
                                               applicantSkills[skillCounter]=userInput;
                                               skillCounter++;
                                            }
                                            else
                                            {
                                                loopComplete=true;
                                            }
                                        }
                                        
                                        //Setting the new Applicant's skill and update
                                        //skill counter
                                        toBeAdded.setSkillCounter(skillCounter);
                                        toBeAdded.setApplicantSkills(applicantSkills);
                                    }
                                    catch(IOException e)
                                    {
                                        System.out.println("User input error when"
                                                + " entering the skills");
                                    }
                                    
                                    if(skillCounter!=0)
                                    {
                                        //This try and catch block attempts to create
                                        //and add the new Applicant into the table
                                        try
                                        {
                                            table.addApplicant(toBeAdded);

                                            System.out.println("Applicant "+applicantName
                                                    +" has been"
                                                + " successfully added to the hiring system\n");
                                        }
                                        catch(FullTableException f)
                                        {
                                            System.out.println(f);
                                        }
                                    }
                                    ///This else means that the user have inputted a 
                                    //empty skill list therefore we tell the user that
                                    //there is a error and go back to the menu
                                    else
                                    {
                                        System.out.println("You have entered an"
                                                + " invalid skill list");
                                        
                                        System.out.println("Applicant is not added"
                                                + " to the system\n");
                                    }
                                }
                                //This else means that the user have inputted a empty 
                                //company list therefore we tell the user that there is a error
                                //and go back to the menu
                                else
                                {
                                    System.out.println("You have entered an invalid"
                                            + " company list");
                                    
                                    System.out.println("Applicant is not added to"
                                            + " the system\n");
                                }
                            }
                            else
                            {
                                System.out.println("You have entered an invalid"
                                        + " college");
                                
                                System.out.println("Applicant is not added to the"
                                        + " system\n");
                            }
                        }
                        //This else means that the user have inputted a empty GPA
                        //therefore we tell the user that there is a error and go back
                        //to the menu
                        else
                        {
                            System.out.println("You have entered an invalid GPA");
                            
                            System.out.println("Applicant is not added to the"
                                    + " system\n");
                        }
                    }
                    catch(IOException i)
                    {
                        System.out.println("User input error");
                    }
                }
                //This else means that the user have inputted a empty name
                //therefore we tell them that there is a error and go back to the menu
                else
                {
                    System.out.println("You have entered an invalid name");
                    
                    System.out.println("Applicant is not added to the system\n");
                }
                
                
            }
            //This means that the user have inputted R which means to
            //remove Applicant of the name they entered
            else if(userInput.equalsIgnoreCase("R"))
            {
                //Asking the user for the input of the Applicant name they want
                //to remove
                System.out.print("Enter the applicant name you wish to remove: ");
                try
                {
                    userInput=reader.readLine();
                }
                catch(IOException e)
                {
                    System.out.println("User input error when entering name that"
                            + " you wish to remove");
                }
                
                //This try and catch block attempts to remove the indicated Applicant
                //from the table
                try
                {
                    table.removeApplicant(userInput);
                }
                catch(ApplicantNotFoundException a)
                {
                    System.out.println(a+"\n");
                }
            }
            //This means that the user have inputted G which means to get the
            //information about the Applicant of the name enterered
            else if(userInput.equalsIgnoreCase("G"))
            {
                //Asking the user for the Applicant the they want to find
                System.out.print("Enter Applicant Name You Wish to Find: ");
                try
                {
                    userInput=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("User input error when entering name that"
                            + " you wish to find");
                }
                
                //Asking the user for the Applicant 
                try
                {
                    Applicant applicantWant=table.getApplicant(userInput);

                    System.out.println("");
                    System.out.println(applicantWant);
                }
                catch(ApplicantNotFoundException a)
                {
                    System.out.println(a+"\n");
                }
            }
            //This means that the user have inputted P and we need to print out a
            //neatly formatted table that consist of all of the Applicants
            else if(userInput.equalsIgnoreCase("P"))
            {
                System.out.println("");
                table.printApplicantTable();
                System.out.println("");
            }
            //This means that the user have inputted RS which we need to conduct
            //the refineSearch
            else if(userInput.equalsIgnoreCase("RS"))
            {
                //Variables to store the information that users input in, remember to
                //after user add an applicant
                String companyName="";
                String applicantSkill="";
                double minGPA=0.0;
                String applicantCollege="";
                
                //Asking the user to input a company to filter for
                //If the user input is none, then it will include everyone
                //regardless of the company they have
                System.out.print("Enter a company to filter for: ");
                try
                {
                    companyName=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("User input error when entering company");
                }
                
                //Asking the user to input a skill to filter for
                //If the user input is none, then it will include everyone
                //regardless of the skill they have
                System.out.print("Enter a skill to filter for: ");
                try
                {
                    applicantSkill=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("User input error when entering skill");
                }
                
                //Asking the user to input a college to filter for
                //If the user input is none, then it will include everyone
                //regardless of the college they have
                System.out.print("Enter a college to filter for: ");
                try
                {
                    applicantCollege=reader.readLine();
                }
                catch(IOException i)
                {
                    System.out.println("User input error when entering college");
                }
                
                //Asking the user to input a minimum GPA to filter for
                //If the user input none, then the minimum GPA will be -1.0
                //to include everyone's GPA
                System.out.print("Enter the minimum GPA to filter for: ");
                try
                {
                    String stringGPA=reader.readLine();
                    
                    if(stringGPA.equals(""))
                    {
                        //We set it to a really low number so it will include all
                        //the Applicants
                        minGPA=-1.0;
                    }
                    else
                    {
                        minGPA=Double.parseDouble(stringGPA);
                    }
                }
                catch(IOException i)
                {
                    System.out.println("User input error when entering GPA");
                }
                
                //Calling the refineSearch method
                HiringTable.refineSearch(table,companyName,applicantSkill,
                        applicantCollege,minGPA);
            }
            //This means that the user have inputted S which means to display
            //the total amount of Applicants inside the system
            else if(userInput.equalsIgnoreCase("S"))
            {
                System.out.println("There are "+table.size()+" applicants in the"
                        + " hiring system\n");
            }
            //This means that the user have inputted B which means to backup
            //the table. Thus we need to clone table to backupTable
            else if(userInput.equalsIgnoreCase("B"))
            {
                try
                {
                    backupTable=(HiringTable)table.clone();
                }
                catch(CloneNotSupportedException c)
                {
                    System.out.println(c);
                }
                
                
                System.out.println("Successfully created backup\n");
            }
            //This means that the user have inputted CB which means to compare the
            //table to the backupTable to see if there is a difference between them
            else if(userInput.equalsIgnoreCase("CB"))
            {
                //Keeping a boolean variable to determine whether or not the
                //table is the same as the backupTable
                boolean theSame=true;
                
                //If the table's size is different that means they are different
                //for sure
                if(table.size()!=backupTable.size())
                {
                    theSame=false;
                }
                //This means that the table are the same in length, thus we must do further
                //inspection to see if the content inside each table is the same as well
                else 
                {
                    //If the table are the same size and have no applicant inside
                    //there is nothing to compare
                    if(table.size()==0)
                    {
                        System.out.println("There is nothing in the list to compare");
                    }
                    else
                    {
                        //This for loop will be checking each Applicant to see if they are
                        //different
                        for(int i=0;i<table.size();i++)
                        {
                            Applicant applicantAtI=table.getApplicantList()[i];
                            
                            if(!applicantAtI.equals(backupTable.getApplicantList()[i]))
                            {
                                theSame=false;
                            }
                        }
                    }
                }
                
                //This means that the two tables are the same
                if(theSame)
                {
                    System.out.println("Current list is the same as the backup copy\n");
                }
                //This means that the two tables are different
                else
                {
                    System.out.println("Current list is not the same as the backup copy\n");
                }
            }
            //This means that the user have inputted RB which means to revert back
            //to the backupTable
            else if(userInput.equalsIgnoreCase("RB"))
            {
                try
                {
                    table=(HiringTable)backupTable.clone();
                }
                catch(CloneNotSupportedException c)
                {
                    System.out.println(c);
                }
                
                
                System.out.println("Successfully reverted to the backup copy\n");
            }
            //This means that the user have inputted Q which means to close and
            //shut down the program
            else if(userInput.equalsIgnoreCase("Q"))
            {
                finish=true;
                
                System.out.println("Shutting down program . . .");
            }
        }
    }
    
    
}
