package com.project_2;

import java.util.ArrayList;

public class RegistrationHelper implements Runnable {
	User2 user;
	ArrayList<User2> pList;
	DataInsertedListener iListener ;
	@Override
	public void run() {
	}
	public RegistrationHelper (User2 user, ArrayList<User2> pList, DataInsertedListener iListener){
		this.user = user;
		this.pList = pList;
		this.iListener = iListener;
	}
}
