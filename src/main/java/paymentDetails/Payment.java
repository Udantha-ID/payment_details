package paymentDetails;

public class Payment {
	

	private int id;
    private String cardHolderName;
    private String emailAddress;
    private double amount;
    private long cardNumber;
    private String expiryDate;
    private int cvv;
    
    public Payment(int id, String cardHolderName, String emailAddress, double amount, long cardNumber,
			String expiryDate, int cvv) {
		super();
		this.id = id;
		this.cardHolderName = cardHolderName;
		this.emailAddress = emailAddress;
		this.amount = amount;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
	}

	public Payment(String cardHolderName, String emailAddress, double amount, long cardNumber, String expiryDate,
			int cvv) {
		super();
		this.cardHolderName = cardHolderName;
		this.emailAddress = emailAddress;
		this.amount = amount;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
	}

	public int getId() {
		return id;
	}

	
	public String getCardHolderName() {
		return cardHolderName;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public double getAmount() {
		return amount;
	}


	public long getCardNumber() {
		return cardNumber;
	}


	public String getExpiryDate() {
		return expiryDate;
	}


	public int getCvv() {
		return cvv;
	}

}
