package Final_Project;

public class customer {
	private String name; 
	private String contactInfo; 
	private String paymentInfo; 
	private int age; 
	
	public customer( String name, int age,  String contactInfo, String paymentInfo) {
		this.name = name;
		this.age = age; 
		this.contactInfo = contactInfo; 
		this.paymentInfo = paymentInfo; 
	}	
	
	




	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getContactInfo() {
		return contactInfo;
	}
	
	public String paymentInfo() {
		return paymentInfo; 
	}
	
	public void setName(String name) {
		this.name = name; 
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo; 
	}
	
	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo; 
	}
	

}
