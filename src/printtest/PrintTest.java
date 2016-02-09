package printtest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PrintTest {
    public static final String A4 = "A4";
    public static final double BLACK_SINGLE_SIDED_AMOUNT = 0.15;
    public static final double BLACK_DOUBLE_SIDED_AMOUNT = 0.10;
    public static final double COLOUR_SINGLE_SIDED_AMOUNT = 0.25;
    public static final double COLOUR_DOUBLE_SIDED_AMOUNT = 0.20;
    public static double totalAmount = 0.0;
    public static final int BLACK_INDEX = 0;
    public static final int COLOUR_INDEX = 1;
    public static final int DOUBLE_SIDED_INDEX = 2;
    public static final String CSV  = ".csv";
    
    public static double calculateAmount(String paperSize, int pages, double amountPerPage) {
        double amount = 0.0;
        
        if (paperSize.equals(A4)) {
            amount = pages * amountPerPage;
        }
        
        totalAmount += amount;
        
        return amount;
    }
    
    public static ArrayList<String> putCSVtoArrayList(String lineInput) {
        ArrayList<String> lineArrayList = new ArrayList<>();

        if (lineInput != null) {
            String[] splitData = lineInput.split("\\s*,\\s*");
            System.out.println("splitData.length: "+splitData.length);
            if (splitData.length == 3)
                for (int i = 0; i < splitData.length; i++) {
                    if ((splitData[i] != null) || (splitData[i].length() != 0)) {
                        lineArrayList.add(splitData[i].trim());
                    }
                }
            else
                System.out.println("Invalid file format. Please use the format <no. of black and white pages>,<no. of colour pages>,<is double sided (true/false>");
        }

        return lineArrayList;
    }
    
    public static int validateNumber(String number) {
        int convertedNumber;
        try {
            convertedNumber = Integer.parseInt(number);
            return convertedNumber;
        } catch (Exception e) {
            System.out.println("Invalid number: " + number);
        }
        
        return 0;
    }
    
    public static void readCSV(String filename) {
        PrintJob printJob = new PrintJob();
        ArrayList<String> listJobs = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.##");
        
        BufferedReader brInputFile = null;
		
        try {
            String lineInput;
            brInputFile = new BufferedReader(new FileReader(filename));
            double amountPerBlackPage;
            double amountPerColourPage;

            // read CSV file
            while ((lineInput = brInputFile.readLine()) != null) {
                if (!lineInput.equals("")) {
                    System.out.println("CSV data: " + lineInput);
                    
                    // convert string to array list
                    listJobs = putCSVtoArrayList(lineInput);
                    
                    if (listJobs != null && !listJobs.isEmpty()) {
                        // populate print job object
                        printJob.setBlackPages(validateNumber(listJobs.get(BLACK_INDEX)));
                        printJob.setColourPages(validateNumber(listJobs.get(COLOUR_INDEX)));
                        printJob.setDoubleSided(Boolean.parseBoolean(listJobs.get(DOUBLE_SIDED_INDEX)));

                        if (printJob.isDoubleSided()) {
                            amountPerBlackPage = BLACK_DOUBLE_SIDED_AMOUNT;
                            amountPerColourPage = COLOUR_DOUBLE_SIDED_AMOUNT;
                        } else {
                            amountPerBlackPage = BLACK_SINGLE_SIDED_AMOUNT;
                            amountPerColourPage = COLOUR_SINGLE_SIDED_AMOUNT;
                        }

                        // add call to calculate amount
                        printJob.setBlackAmount(calculateAmount(A4, printJob.getBlackPages(), amountPerBlackPage));
                        printJob.setColourAmount(calculateAmount(A4, printJob.getColourPages(), amountPerColourPage));
                        printJob.setJobAmount(printJob.getBlackAmount() + printJob.getColourAmount());
                        System.out.println("Black amount: " + printJob.getBlackAmount());
                        System.out.println("Colour amount: " + printJob.getColourAmount());
                        System.out.println("Job amount: " + printJob.getJobAmount() + "\n");

                    }
                }
            }
            
            System.out.println("Total amount: " + df.format(totalAmount));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (brInputFile != null) brInputFile.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        String filename = "printjobs (1).csv";
        
        if (args.length > 0)
            if (args[0].endsWith(CSV))
                readCSV(args[0]);
            else
                System.out.println("Invalid file type. Please choose a CSV file");
        else
            readCSV(filename);
    }
    
}
