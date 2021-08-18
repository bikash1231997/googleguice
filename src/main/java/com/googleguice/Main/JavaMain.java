package com.googleguice.Main;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class JavaMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hi this is main class");
		Injector injector = Guice.createInjector();
		mymethod instance = injector.getInstance(mymethod.class);
		instance.sayHello();
	}
	
	public class mymethod{
		void sayHello() {
			System.out.println("hi");
		}
	}

}
