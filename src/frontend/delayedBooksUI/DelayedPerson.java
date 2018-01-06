package frontend.delayedBooksUI;

import java.time.LocalDate;


/*
 * This is a class created by Jacob Olsson
 * 
 * This class is mix between both the user class and the book class.
 * 
 * It keeps track of relavant informaiton, in order to display it in the delayed book scree.
 * 
 */

public class DelayedPerson {

	public String title;
	public String Name;
	public int userId;
	public double debt;
	public double allDebt;
	public String date;
	public String returndate;
	public String ISBN;
	
	//:Constructor for the class
	public DelayedPerson(String title, String Name, int userId, double debt, double allDebt, String date , String returndate, String ISBN ) {
		super();
		this.title = title;
		this.Name = Name;
		this.userId = userId;
		this.debt = debt;
		this.allDebt = allDebt;
		this.date = date;
		this.returndate = returndate;
		this.ISBN = ISBN;
		
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public double getAllDebt() {
		return allDebt;
	}

	public void setAllDebt(double allDebt) {
		this.allDebt = allDebt;
	}

	public String getReturndate() {
		return returndate;
	}

	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getDebt() {
		return debt;
	}

	public void setDebt(double debt) {
		this.debt = debt;
	}
	
	
}
