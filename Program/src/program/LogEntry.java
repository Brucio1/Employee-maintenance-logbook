package program;

import java.util.Date;
import java.text.SimpleDateFormat;

public class LogEntry 
    {
    private String date;
    private String assetID;
    private int hours;
    
    public LogEntry(String date, String assetID, int hours)
        {
        this.date = date;
        this.assetID = assetID;
        this.hours = hours;
        }
    
    public LogEntry()
        {         
        }
    
    public String getCurrentDate()
        {
        Date currDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = dateFormat.format(currDate);
        
        return currentDate;
        }
    
    public int getEntryHours()
        {
        return this.hours;
        }
    
    @Override
    public String toString()
        {
        return this.date + "," + this.assetID + "," + this.hours;
        }
    }
