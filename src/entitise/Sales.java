package entitise;

public class Sales {
	@Override
	public String toString() {
		return "Sales [commodity_Name=" + commodity_Name + ", price=" + price + ", number=" + number + ", allprice="
				+ allprice + ", date=" + date + "]";
	}
	private String commodity_Name;
	private String price;
	private String number;
	private String allprice;
	private String date;
	public Sales() {
		super();
		// TODO 自动生成的构造函数存根
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Sales(String commodity_Name, String price, String number, String allprice, String date) {
		super();
		this.commodity_Name = commodity_Name;
		this.price = price;
		this.number = number;
		this.allprice = allprice;
		this.date = date;
	}
}
