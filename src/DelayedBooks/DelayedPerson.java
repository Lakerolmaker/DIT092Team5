package DelayedBooks;

public class DelayedPerson {

	public String title;
	public String Name;
	public int userId;
	public double debt;
	
	public DelayedPerson(String title,String Name, int userId, double debt) {
		super();
		this.title = title;
		this.Name = Name;
		this.userId = userId;
		this.debt = debt;
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
