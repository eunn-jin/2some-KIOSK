package user.main;

public class Product {

	public int mn_idx;	//메뉴코드
	public int item_num;	//갯수
	public int item_price;	//가격
	public String name;	//메뉴이름
	public int u_num;	//구입 유저 고객번호
	
	public Product(int idx, int item_num, int item_price, String name){
		this.mn_idx = idx;
		this.item_num = item_num;
		this.item_price = item_price;
		this.name = name;
	}
}
