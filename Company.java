/**
This class represents a company.
@author Keri Fulton 3616473
*/
import java.text.NumberFormat;
public class Company{
	/** Instance variable for the company's name*/
	private String name;
	/**Instance variable for the company's business domain.*/
	private String businessDomain;
	/**Instance variable for the company's license*/
	private String license;
	/**Instance variable for the company's fiscal year*/
	private int fiscalYear;
	/**Instance variable for counting number of branches*/
	private int branchCount = 0;
	/**Instance variable for a list of branch objects*/
	private Branch[] branches = new Branch[50];

	/**
	Creates a company object. Includes the name, business domain, license and fiscal year.
	@param name the name of the company.
	@param businessDomain the company's business domain.
	@param license the company's license number.
	@param fiscalYear the company's fiscal year.
	*/
	public Company(String name, String businessDomain, String license, int fiscalYear){
		this.name = name;
		this.businessDomain = businessDomain;
		this.license = license;
		this.fiscalYear = fiscalYear;

	}
	/**
	This method adds a branch to the array.
	@param branch a Branch object.
	*/
	public void addBranch(Branch branch){
		branches[branchCount] = branch;
		branchCount++;


	}
	/**
	This method returns the branches in the company
	@return a branch object of all the branches.
	*/
	public Branch[] getBranches(){
		Branch[] branchOutput = new Branch[branchCount];
		for(int i = 0; i< branchOutput.length; i++){
			branchOutput[i] = branches[i];
		}
		return branchOutput;

	}
	/**
	This method calculates the profit totals for each quarter and then adds up
	the annual profits for all the company branches, to give the annual company profit.
	@return a string of the profit totals.
	*/
	public String getProfitTotals(){
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		nf.setGroupingUsed(false);
		double annualTotal = 0;
		double quarter1Total = 0;
		double quarter2Total =0;
		double quarter3Total = 0;
		double quarter4Total =0;
		for(int i = 0; i< branchCount; i++){


			try{
				quarter1Total += branches[i].getQuarterlyProfit(0);
				quarter2Total += branches[i].getQuarterlyProfit(1);
				quarter3Total += branches[i].getQuarterlyProfit(2);
				quarter4Total += branches[i].getQuarterlyProfit(3);

			}catch(InvalidQuarterException e){
				System.out.println(e.getMessage());
			}


			annualTotal += branches[i].getAnnualProfit();
		}
		return "Total:," + nf.format(quarter1Total) +"," + nf.format(quarter2Total) + "," + nf.format(quarter3Total) + "," + nf.format(quarter4Total) + "," + nf.format(annualTotal);
	}
	/**
	This method returns information of a company, seperated with new lines.
	@return the company's information.
	*/
	public String toString(){
		String result = "";
		result+= name +" \n" +businessDomain +" \n" + license +" \n";
		result += ""+fiscalYear + "\n";
		return result;

	}

}
