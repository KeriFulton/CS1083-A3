import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
This class represents a profit report generator. It creates an output text file.
@author Keri Fulton 3616473
*/
public class ProfitReportGenerator{
	static int counter = 0;
	public static void main(String[] args) throws MissingFileNameArgumentException{
		Scanner scan = null;
		File inFile = null;
		if(args.length == 0){
			throw new MissingFileNameArgumentException("Missing input and output file names.");
		}
		else if(args.length == 1){
			throw new MissingFileNameArgumentException("Missing either input or output file name.");

		}
		else{
		try{
		inFile = new File(args[0]);
		boolean valid = false;
				scan = new Scanner(inFile);
				String name = scan.nextLine();
				String businessDomain = scan.nextLine();
				String license = scan.nextLine();
				int fiscalYear = scan.nextInt();
				scan.nextLine();
				Company company = new Company(name, businessDomain, license, fiscalYear);
				while(scan.hasNext()){
					String str = scan.nextLine();
					Scanner scan2 = new Scanner(str);
					scan2.useDelimiter(",");
					String branchID = scan2.next();
					String location = scan2.next();
					Branch branch = new Branch(branchID, location);
					company.addBranch(branch);
					counter++;
					for(int i = 0; i< 4; i++){
						try{
						double one = scan2.nextDouble();
						double two = scan2.nextDouble();
						branch.calculateQuarterlyProfit(one, two);
						}catch(InvalidQuarterException e){
							System.out.println(e.getMessage());
						}
					}
				}
				String outputFile = args[1];
				generateReport(company, outputFile);

		}
		catch(NoSuchElementException e){
			System.out.println("No such element or invalid input!\n" + e);

		}
		catch(IOException ex){
				System.out.println("Error reading input file!\n" + ex);
		}
		finally{
			if(scan != null){
				scan.close();
			}
		}
		}


	}
	/**
	This method generates the report for the company and the branches.
	@param company the Company object
	@param outputFile the name of the output file passed through the command line.
	*/
	public static void generateReport(Company company, String outputFile){
		PrintWriter output = null;
		try{
			output = new PrintWriter(outputFile);
			output.print(company.toString());
			for(int i =0; i< counter; i++){
				output.print(company.getBranches()[i].toString());
			}
			output.print(company.getProfitTotals());
		}catch(IOException e){
			System.out.println("Error reading input!\n" + e);
		}
		finally{
			if(output!= null){
				output.close();
			}
		}




	}
}
