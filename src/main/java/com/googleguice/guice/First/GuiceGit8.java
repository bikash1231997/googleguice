package com.googleguice.guice.First;

import java.util.logging.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class GuiceGit8 {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new DemoModule());
		DemoImplement d = injector.getInstance(DemoImplement.class);
		d.democall();
	}
}

class DemoImplement{
	private Logger log;
	
	@Inject
	public DemoImplement (Logger logger) {
		this.log=logger;
	}
	
	public void democall() {
		log.info(" logger");
	}
}

class DemoModule extends AbstractModule{
	@Override
	protected void configure() {
		
	}
}