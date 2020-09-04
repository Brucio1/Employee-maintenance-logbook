package program;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Program 
    {
    private int userChoice;
    private Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException
        {
        Program program = new Program();
        
        program.menu();
        }
    
    public void menu() throws FileNotFoundException
        {
        MaintenanceLog log = new MaintenanceLog();       
        String again = "y";
        
        while(again.equals("y") || again.equals("Y"))
            {
            System.out.println("\n(1) Display Maintenance log.\n"
                             + "(2) Add an entry.\n"
                             + "(3) Edit an entry.\n"
                             + "(4) Delete an entry.\n"
                             + "(5) See Management summary.");
            
            setUserChoice();
            
            switch(this.userChoice)
                {
                case 1:
                    log.printEntries();
                    break;
                case 2:
                    log.addEntry();
                    break;
                case 3:
                    log.editEntry();
                    break;
                case 4:
                    log.deleteEntry();
                    break;
                case 5:
                    log.getSummary();
                }
            
            System.out.print("\nContinue? (Y/N): ");
            again = scan.next();
            }
        System.out.println("\nGoodbye!");
        }
    
    public void setUserChoice()
        {
        boolean validUserChoice = false;
            
            while(!validUserChoice)
                {
                try
                    {
                    System.out.print("\nChoose an option [1-5]: ");
                    this.userChoice = this.scan.nextInt();
                    System.out.print("\n");
                    if((this.userChoice < 1) || (this.userChoice > 5))
                        {
                        System.out.print("Enter a number between 1 and 5!\n");
                        }
                    else
                        {
                        validUserChoice = true;
                        }
                    }
                catch(Exception e)
                    {
                    System.out.print("\nEnter a number!\n");
                    this.scan.next();
                    }
                }
        }
    }