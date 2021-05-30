package entitise;

public class Single {
	private String commodity_Name;
	private String price;
	private String number;
	private String allprice;
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
	public Single(String commodity_Name, String price, String number, String allprice) {
		super();
		this.commodity_Name = commodity_Name;
		this.price = price;
		this.number = number;
		this.allprice = allprice;
	}
	public Single() {
		super();
		// TODO 自动生成的构造函数存根
	}
}
