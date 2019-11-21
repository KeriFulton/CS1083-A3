import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedReader;
/**
This class represents a profit report viewer. It displays the profit genetrator
made from ProfitReportGenerator.
@author Keri Fulton 3616473
*/
public class ProfitReportViewer {

    public static void main(String[] args) throws MissingFileNameArgumentException{
    // TODO: implement this method.
        FileReader file = null;
        BufferedReader buffer = null;
        Scanner bScan = null;
        Scanner scan = null;
        double total1 =0, total2= 0, total3 = 0, total4 = 0, total5 = 0;
        if(args.length == 0){
                throw new MissingFileNameArgumentException("Missing input file name");
        }
        try{
        file = new FileReader(args[0]);
        buffer = new BufferedReader(file);
        bScan = new Scanner(buffer);
        //try scanning in the name from the buffer reader.
        try{
            String name = bScan.nextLine();
            String domain = bScan.nextLine();
            String license = bScan.nextLine();
            int year = bScan.nextInt();
            bScan.nextLine();
            printReportHeader(name, domain, license, year);
            while(bScan.hasNext()){
                String branch = bScan.nextLine();
                scan = new Scanner(branch);
                scan.useDelimiter(",");
                String id = scan.next();
                if(id.equals("Total:")){

                    total1 = Double.parseDouble(scan.next());
                    total2 = Double.parseDouble(scan.next());
                    total3 = Double.parseDouble(scan.next());
                    total4 = Double.parseDouble(scan.next());
                    total5 = Double.parseDouble(scan.next());
                    printSeparatorLine();
                    printBranchProfit("Totals:", "", total1, total2, total3, total4, total5);
                    printSeparatorLine();
                    System.out.println();

                }
                else{
                    String location = scan.next();
                    total1 = Double.parseDouble(scan.next());
                    total2 = Double.parseDouble(scan.next());
                    total3 = Double.parseDouble(scan.next());
                    total4 = Double.parseDouble(scan.next());
                    total5 = Double.parseDouble(scan.next());
                    printBranchProfit(id, location, total1, total2, total3, total4, total5);

                }
            }


        }
        //handling any NoSuchElement exceptions
        catch(NoSuchElementException exp){
                System.err.println("\nNo such element or invalid input!!\n" + exp);

        }
        }
        //catching any IO exceptions
        catch(IOException exp){
            System.err.println("Error opening file!\n"+ exp);
        }
        //finally, close the scanner and the buffer
        finally{
            System.out.println();
            if(scan!= null){
                scan.close();
            }
            if(buffer!= null){
              try{
                buffer.close();
              }catch(IOException exp){
                //handles the IOException for trying to close the buffer reader.
                System.out.println("Error closing buffer!" + exp);
              }
            }

        }
    }


    /**
     * This method prints the company's information part of the profit report
     * plus the header for the quarterly profit details of the company branches.
     * @param name Company name
     * @param domain Business domain of the company
     * @param license License number of the company
     * @param fiscalYear Profit report fiscal year
     */
    private static void printReportHeader(String name, String domain, String license, int fiscalYear) {

        System.out.println();
        System.out.println("\t\t\t\t\t\tCompany name: " + name);
        System.out.println("\t\t\t\t\t\tDomain: " + domain);
        System.out.println("\t\t\t\t\t\tLicense: " + license);
        System.out.println("\t\t\t\t\t\tFiscal Year: " + fiscalYear);
        System.out.println("\n");
        System.out.println("\t\t\t\t\t\t\tProfit Report\n\n");
        String format = "%-15s %-15s %15s %15s %15s %15s %20s";
        System.out.format(format,"Branch ID", "Location", "Quarter 1", "Quarter 2",
                            "Quarter 3", "Quarter 4", "Annual Profit");
        printSeparatorLine();
    }


    /**
     * This method prints the profit report line for a given company branch.
     * It is also used to print the totals line at the end of the report.
     * @param brnId - represents branch id
     * @param brnLocation - represents branch location
     * @param q1Profit - represents the profit for the first quarter
     * @param q2Profit - represents the profit for the second quarter
     * @param q3Profit - represents the profit for the third quarter
     * @param q4Profit represents the profit for the fourht quarter
     * @param annualProfit - represents the total profit for the whole year
     */
    private static void printBranchProfit(String brnId, String brnLocation, double q1Profit,
                                          double q2Profit, double q3Profit,
                                          double q4Profit, double annualProfit ) {

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
	formatter.setMinimumFractionDigits(2);
        formatter.setMinimumIntegerDigits(1);
        System.out.println();

        String format = "%-15s %-15s %15s %15s %15s %15s %20s";
        System.out.format(format, brnId, brnLocation, formatter.format(q1Profit),
                          formatter.format(q2Profit), formatter.format(q3Profit),
                          formatter.format(q4Profit), formatter.format(annualProfit));
    }


    /**
     * Reusable separator line print method.
     */
    private static void printSeparatorLine() {
        System.out.print("\n----------------------------------------------------------" +
                            "----------------------------------------------------------");
    }

}
