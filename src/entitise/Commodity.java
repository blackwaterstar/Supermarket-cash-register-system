package entitise;

import java.util.HashMap;

public class Commodity {
	private String commodity_Id;	
	private String commodity_Name;	
	private String shelves;
	private String type;	
	private String price;	
	private String number;
	public Commodity() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Commodity(String commodity_Id, String commodity_Name, String shelves, String type, String price,
			String number) {
		super();
		this.commodity_Id = commodity_Id;
		this.commodity_Name = commodity_Name;
		this.shelves = shelves;
		this.type = type;
		this.price = price;
		this.number = number;
	}
	public String getCommodity_Id() {
		return commodity_Id;
	}
	public void setCommodity_Id(String commodity_Id) {
		this.commodity_Id = commodity_Id;
	}
	public String getCommodity_Name() {
		return commodity_Name;
	}
	public void setCommodity_Name(String commodity_Name) {
		this.commodity_Name = commodity_Name;
	}
	public String getShelves() {
		return shelves;
	}
	public void setShelves(String shelves) {
		this.shelves = shelves;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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



}
