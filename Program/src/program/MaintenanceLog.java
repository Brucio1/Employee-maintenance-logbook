package program;
 
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class MaintenanceLog
    {
    private Scanner scan;
    private String directory = System.getProperty("user.dir");
    private final File logTextFile = new File(directory + "/assig2.txt");
    private ValidInput input = new ValidInput();
    
    private LogEntry logEntries [];
    private int totalEntries;
    private double totalHours;
    
    private int entryToBeChanged;
    
    public MaintenanceLog() throws FileNotFoundException
        {
        this.logEntries = new LogEntry [20];
        this.scan = new Scanner(this.logTextFile);
        
        //Loop scans every line of the file, divides it into three parts 
        //and assigns it as a new log entry in the logEntries array
        while(this.scan.hasNextLine())
            {
            String line = this.scan.nextLine();
            Scanner scanLine = new Scanner(line);
            
            //Divides a line by comma
            scanLine = scanLine.useDelimiter(",");
            
            String date = scanLine.next();
            String assetID = scanLine.next();
            int hours = scanLine.nextInt();
            
            this.logEntries[this.totalEntries] = new LogEntry(date, assetID, hours);
            this.totalEntries++;
            this.totalHours += hours;
            }
        //From now on the class will use scan only to read in input 
        this.scan = new Scanner(System.in);
        }
 
    public void printEntries()
        {
        int nullEntry = 0;
        for(LogEntry entry: this.logEntries)
            {
            if(entry != null)
                {
                System.out.println(entry);
                }
            else
                {
                nullEntry++;
                }
            }
        
        if(nullEntry == 20)
            System.out.println("No Entries!");
        }
    
    private void printNumberedEntries()
        {
        int i = 1;
        for(LogEntry entry: this.logEntries)
            {
            if(entry != null)
                {
                System.out.println("(" + i + ") " + entry);
                i++;
                }            
            }
        }
    
    private void writeChangesToFile() throws FileNotFoundException
        {
        PrintWriter writer = new PrintWriter(logTextFile);
        
        for(LogEntry entry: this.logEntries)
            {
            if(entry != null)
                {
                writer.println(entry);
                }
            }       
        writer.close();
        }
    
    public void addEntry() throws FileNotFoundException
        {
        LogEntry logEntry = new LogEntry();
        String currentDate = logEntry.getCurrentDate();
        
        String assetID = this.input.getAssetID();
        int hours = this.input.getHours();
        
        this.logEntries[this.totalEntries] = new LogEntry(currentDate, assetID,
                                                        + hours);
        this.totalHours += hours;
        this.totalEntries++;
        
        writeChangesToFile();
        }
    
    public void editEntry() throws FileNotFoundException
        {
        printNumberedEntries();
        
        System.out.print("\nWhich entry would you like to EDIT?");
        this.entryToBeChanged = getEntryToBeChanged();
        
        String newDate = this.input.getNewDate();
        String newAssetID = this.input.getAssetID();
        int newHours = this.input.getHours();       
        
        //Subtracts old hours from the totalHours variable 
        this.totalHours -= this.logEntries[this.entryToBeChanged-1].getEntryHours();
        this.totalHours += newHours;
        
        this.logEntries[this.entryToBeChanged-1] = new LogEntry(newDate, newAssetID,
                                                                    + newHours);        
        System.out.print("\nLog entry has been successfully edited.\n");
        
        writeChangesToFile();
        }
    
    public void deleteEntry() throws FileNotFoundException
        {
        printNumberedEntries();
        
        System.out.print("\nWhich entry would you like to DELETE?");
        this.entryToBeChanged = getEntryToBeChanged();
        
        //Subtracts the number of hours from the totalHours variable 
        this.totalHours -= this.logEntries[this.entryToBeChanged-1].getEntryHours();
        
        //Moves back entries in the array by one, overwritting the entry
        //that needs to be deleted
        for(int i = this.entryToBeChanged-1; i < this.totalEntries; i++)
            {
            this.logEntries[i] = this.logEntries[i+1];
            }
        this.totalEntries--;
        
        System.out.print("\nLog entry has been successfully deleted.\n");
        
        writeChangesToFile();
        }
    
    public void getSummary()
        {
        System.out.println("Number of log entries: " + this.totalEntries + "\n"
                         + "Average hours spent on a maintenance task: " 
                         + getAverageHours() + "\n"
                         + "Number of tasks that took more than 4 hours: " 
                         + getMoreThan4hTasks());
        }
    
    private String getAverageHours()
        {
        return String.format("%.1f", this.totalHours/this.totalEntries);
        }
    
    private int getMoreThan4hTasks()
        {
        int moreThan4h = 0;
        
        for(int i=0; i < this.totalEntries; i++)
            {
            if(this.logEntries[i].getEntryHours() > 4)
                {
                moreThan4h++;
                }
            }
        return moreThan4h;
        }
    
    private int getEntryToBeChanged()
        {
        boolean validEntryToBeChanged = false;
        int changedEntry = 0;
        
        while(!validEntryToBeChanged)
            {
            try
                {
                System.out.print("\nEnter a number: ");
                changedEntry = this.scan.nextInt();
                if(changedEntry > this.totalEntries)
                    {
                    System.out.println("\nThere are only " + this.totalEntries 
                                     + " entries.");
                    }                                                          
                else if(changedEntry <= 0)
                    {                       
                    System.out.print("\nEnter a positve number!\n");
                    }
                else
                    {
                    validEntryToBeChanged = true;
                    }
                }
            catch(Exception e)
                {
                System.out.print("\nEnter a whole number!\n");
                this.scan.next();
                }
            }
        return changedEntry;
        }
    }