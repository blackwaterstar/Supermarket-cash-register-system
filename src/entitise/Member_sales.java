package entitise;

public class Member_sales {
	private String member_id;
	private String commodity_Name;
	private String price;
	private String number;
	private String allprice;
	public Member_sales() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getCommodity_Name() {
		return commodity_Name;
	}
	public void setCommodity_Name(String commodity_Name) {
		this.commodity_Name = commodity_Name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAllprice() {
		return allprice;
	}
	public void setAllprice(String allprice) {
		this.allprice = allprice;
	}
	public Member_sales(String member_id, Single single) {
		super();
		this.member_id = member_id;
		this.commodity_Name = single.getCommodity_Name();
		this.price = single.getPrice();
		this.number = single.getNumber();
		this.allprice = single.getAllprice();
	}
}
