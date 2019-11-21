
/**
This class represents a branch.
@author Keri Fulton 3616473
*/
import java.text.NumberFormat;
public class Branch{
	/** Instance variable for the branch ID*/
	private String branchId;
	/**Instance variable for the branch location*/
	private String branchLocation;
	/**Instance variable that holds an array of quarter profit for the branch.*/
	private double[] quarterProfit = new double[4];
	/**Instance variable that acts a counter to count the number of quarters.*/
	private int quarterCount = 0;


	/**
	This constructor creates a branch object. Includes the branch ID and location.
	@param branchId the branch ID.
	@param branchLocation the branch's location.
	*/
	public Branch(String branchId, String branchLocation){
		this.branchId = branchId;
		this.branchLocation = branchLocation;

	}
	/**
	This method calculates the quarterly profit of a particular branch and stores it into quarterProfit,
	which is used to keep track of the quarterly profit
	@param expense the company's quarterly expenses.
	@param revenue the company's quarterly revenue.
	@throws InvalidQuarterException if the quarter count is out of bounds from the conditions specified.
	*/
	public void calculateQuarterlyProfit(double expense, double revenue) throws InvalidQuarterException{
		if(quarterCount >=0 && quarterCount <= 3){
		quarterProfit[quarterCount] = revenue - expense;
		quarterCount++;
		}
		else{
			throw new InvalidQuarterException("Invalid quarter.");
		}

	}
	/**
	This method gets the quarterly profit of a particular branch.
	@return the quarterly profit.
	*/
	public double getQuarterlyProfit(int quarter) throws InvalidQuarterException{
		if(quarter >=0 && quarter <=3){
			return quarterProfit[quarter];
		}
		else{
			throw new InvalidQuarterException("Invalid quarter.");
		}
	}
	/**
	This method gets the annual profit of a particular branch.
	@return  the annual profit.
	*/
	public double getAnnualProfit(){
		double annualProfit = 0;
		for(int i = 0; i< quarterProfit.length; i++){
			annualProfit += quarterProfit[i];
		}
		return annualProfit;

	}
	/**
	This methods print outs a row of branch information for the branch, including
	each quarter profit and the annual profit. This method will be invoked when
	used for the output txt file.
	@return the branch information.
	*/
	public String toString(){
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		nf.setGroupingUsed(false);
		String str = "";
		str += branchId +"," + branchLocation + ",";
		for(int i =0; i< quarterProfit.length; i++){
			str += nf.format(quarterProfit[i]) + ",";

		}
		str += "" + nf.format(this.getAnnualProfit()) + "\n";
		return str;

	}
}
