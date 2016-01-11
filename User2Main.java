package com.project_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class User2Main {
	public static void main(String[] args) {
		int option;
		ArrayList<User2> pList = new ArrayList<>();
		Scanner reader1 = new Scanner(System.in);
		Scanner reader = new Scanner(System.in);
		boolean stop=true;
		while (stop) {
			System.out.println("Please, choose you option: ");
			System.out.println("To REGISTER NEW USER please choose 1");
			System.out.println("To PRINT ALL USERS please choose 2");
			System.out.println("To SEARCH FOR USER please choose 3");
			System.out.println("To DELETE USER please choose 4");
			System.out.println("To EXIT please choose 5");
			option=reader.nextInt();
			
			switch (option) {
				case 1: 
					registerUser(pList, reader1);
					break;
				case 2:
					printUser(pList, reader1);
					break;
				case 3: 
					searchUser(pList, reader1);
					break;
				case 4:
					deleteUser(pList, reader1);
					break;	
				case 5:
					exit(pList, reader1);
					stop=false;
					break;		
				default:
					stop=false;
					break;
			}
		}
		reader.close();
		reader1.close();
	}
	public static void registerUser(ArrayList<User2> pList, Scanner reader1) {
		User2 user = new User2();
		user.createUser(reader1);
		
		DataInsertedListener iListener = new DataInsertedListener() {
			@Override
			public void onSuccess() {
				System.out.println("We Did It!!");
			}
			@Override
			public void onFailed(Exception e) {
				System.out.println("Oh No!!" + e.getMessage());
				}
		};
		
		
		Thread registrationThread = new Thread (new RegistrationHelper(user,pList,iListener) {	
			@Override
			public void run() {
			try {
				System.out.println("We interact with the database...");
				Thread.sleep(2000);
				System.out.println("The connection was made");
				Thread.sleep(1000);
				///
				for (int i=0; i<pList.size(); i++){ 
					if (user.email==pList.get(i).email){
						throw new Exception("We have another user with same email");
					}
				}
				if (!user.email.equals("@")){
					throw new Exception("The email not correct");	
				}
				pList.add(user);
				iListener.onSuccess();
				} 
			catch (Exception e) {
					iListener.onFailed(e);
				} 	
			}
		});
		registrationThread.start();		
	}
	
	public static void printUser(ArrayList<User2> pList, Scanner reader1) {
		if (pList.size()==0){
			System.out.println("Sorry, no users for print...");
		}
		else {
			for (int i=0; i<pList.size(); i++){
			System.out.println("User " + (i+1) + ": " + pList.get(i));
			}
		}
	}
	public static void searchUser(ArrayList<User2> pList, Scanner reader1){
		System.out.println("Please enter user detail for search.");
		String searchdetail=reader1.next();
		int flag=0;
		for (int i=0; i<pList.size(); i++){
			if (((User2) pList.get(i)).searchUserDetail(searchdetail)) {
				System.out.println("The User detail is: " + pList.get(i).toString());	
				flag = 1;
				break;
			}
		}
			if (flag!=1){
				System.out.println("Sorry, no users for print...");
			}	
	}
	public static void deleteUser(ArrayList<User2> pList, Scanner reader1) {
		int delete;
		for (int i=0; i<pList.size(); i++){
			System.out.println("User " + (i+1) + ": " + pList.get(i));
			}
		System.out.println("If you want to delete a some User please enter his number, "
				+ "else enter '0'");
		delete=reader1.nextInt();
		if (delete!=0){
			pList.remove(delete-1);
		}
		/*for (int i=0; i<pList.size(); i++){
			System.out.println("User " + (i+1) + ": " + pList.get(i));
			}*/
		printUser(pList, reader1);
	}
	public static void exit(ArrayList<User2> pList, Scanner reader1) {
		File file = new File("C:\\Users\\HOME\\workspace1\\Users delails.txt");
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
			try {
				FileOutputStream output = new FileOutputStream(file);
				for (int i=0; i<pList.size(); i++){
					try {
						output.write(pList.get(i).toString().getBytes());
						output.flush();
					} 
					catch (IOException e) {
						e.printStackTrace();
					};
				}
				output.close();
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
	}
}
