package test;

public class Test01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String data1 = "Login.LoginId.locator";
		String data = data1.substring(0,data1.indexOf("."));
		String data2= data1.substring(0, 5);
		System.out.println(data);
		System.out.println(data1);
		System.out.println(data2);
		
	}

}
