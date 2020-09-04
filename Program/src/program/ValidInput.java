package program;

import java.util.Scanner;

public class ValidInput
    {   
    private Scanner scan = new Scanner(System.in);

    public String getPlatform()
        {
        String [] platforms = new String[]{"P", "T", "M", "O"};
        int platformNo = 0;
        boolean validPlatformNo = false;
        
        System.out.print("What platform are you using?\n\n"
                       + "(1) PC\n"
                       + "(2) Tablet\n"
                       + "(3) Mobile\n"
                       + "(4) Other\n\n");
        
        while(!validPlatformNo)
            {
            try
                {
                System.out.print("Enter a number [1-4]: ");
                platformNo = this.scan.nextInt();
                if((platformNo < 1) || (platformNo > 4))
                    {
                    System.out.println("\nEnter a number between 1 and 4!\n");
                    }
                else
                    {
                    validPlatformNo = true;
                    }
                }
            catch(Exception e)
                {
                System.out.println("\nEnter a whole number!\n");
                this.scan.next();
                }
            }
        
        return platforms[platformNo-1];
        }
    
    public String getAssetID()
        {
        boolean accurateLength = false;
        String restOfAssetID = null;
        
        String platform = getPlatform();
        
        while(!accurateLength)
            {
            System.out.print("\nEnter Asset ID: " + platform);
            restOfAssetID = this.scan.next();
            if(restOfAssetID.length() == 5)
                {
                accurateLength = true;
                }
            else
                {
                System.out.println("\nAsset ID should be exactly six "
                                  +"characters in length.");
                }
            }
        
        String assetID = platform + restOfAssetID;
        
        return assetID;
        }
    
    public int getHours()
        {
        boolean validHours = false;
        int hours = 0;
        
        while(!validHours)           
            {
            try{
                System.out.print("Enter number of hours: ");
                hours = this.scan.nextInt();
                if(hours > 0)
                    {
                    validHours = true;
                    }
                else
                    {
                    System.out.println("\nEnter a positve number!\n");
                    }
                }
            catch(Exception e)
                {
                System.out.println("\nEnter a whole number!\n");
                this.scan.next();
                }
            }        
        
        return hours;
        }
    
    public String getNewDate()
        {
        boolean validNewDate = false;
        String newDate = null;
        
        while(!validNewDate)
            {
            try
                {
                System.out.print("\nEnter a new date in a YYYYMMDD format: ");                
                newDate = this.scan.next();
                System.out.print("\n");
                
                //Converts newDate to an integer
                int dateInt = Integer.parseInt(newDate);
                
                //Takes a month and a day in a string format
                String month = newDate.substring(4,6);
                String day = newDate.substring(6,8);
                
                //Converts the month and the day to integers
                int monthInt = Integer.parseInt(month);
                int dayInt = Integer.parseInt(day);
                
                //Converts a two digit number (e.g. 02) to a single
                //digit number in a String format
                month = String.format("%01d", monthInt);
                day = String.format("%01d", dayInt);
                
                //Converts the final month and day to integers
                monthInt = Integer.parseInt(month);
                dayInt = Integer.parseInt(day);
                
                if((dateInt <= 0) || (newDate.length() != 8))
                    {
                    System.out.print("Date is not of accurate format!\n");
                    }
                else if((monthInt <= 0) || (monthInt > 12))
                    {
                    System.out.print("A month number must be between 1 and 12!\n");
                    }
                else if((dayInt <= 0) || (dayInt > 31))
                    {
                    System.out.print("A day number must be between 1 and 31!\n");
                    }
                else
                    {
                    validNewDate = true;
                    }
                }
            catch(Exception e)
                {
                System.out.print("Date is not of accurate format!\n");
                }           
            }
        
        return newDate;
        }
    }
