package com.project_2;

import java.util.Scanner;

public class User2 implements DataInsertedListener{
	public String f_name;
	public String l_name;
	public String n_name;
	public String email;
	public String password;
 
	public String getF_name() {return f_name;}
	public void setF_name(String f_name) {this.f_name = f_name;}
	public String getL_name() {return l_name;}
	public void setL_name(String l_name) {this.l_name = l_name;}
	public String getN_name() {return n_name;}
	public void setN_name(String n_name) {this.n_name = n_name;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	
	public User2(String f_name, String l_name, String n_name, String email, String password) {
		super();
		this.f_name = f_name;
		this.l_name = l_name;
		this.n_name = n_name;
		this.email = email;
		this.password = password;
	}
	public User2() {
		super();
	}	
	@Override
	public String toString() {
		return "First name: " + getF_name() + ", Last name: " + getL_name() + ", Nick name: " 
					+ getN_name() + ", Email: " + getEmail();
	}
	public void createUser(Scanner reader1) {
		System.out.println("Please enter you first name");
		setF_name(reader1.next());
		System.out.println("Please enter you last name");
		setL_name(reader1.next());
		System.out.println("Please enter you nick name");
		setN_name(reader1.next());
		System.out.println("Please enter you email");
		setEmail(reader1.next());
		System.out.println("Please enter you password");
		setPassword(reader1.next());
		System.out.println("Your details are: " + toString());
		System.out.println("Is it right? Please write 'true' or 'false'.");
		try {
			if (!reader1.nextBoolean()) {
				createUser(reader1);
			}
			else {
				return;
			}	
		} 
		catch (Exception e) {
			reader1.nextLine();
			createUser(reader1);
		}	
	}
	public boolean searchUserDetail(String searchdetail){
		String test = (getF_name() + " " + getL_name() + " " + getN_name() + " " + getEmail());
		if (test.contains(searchdetail)) {
			return true;
		}
		else {
			return false;
		}
	}	
	
	public void onSuccess(){	
	}
	public void onFailed(Exception e){	
	}
}
