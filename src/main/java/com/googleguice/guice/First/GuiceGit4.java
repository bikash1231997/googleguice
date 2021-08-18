package com.googleguice.guice.First;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class GuiceGit4 {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new DemoInjector1());
		DemoImpl1 d = injector.getInstance(DemoImpl1.class);
		d.democall();
	}
}

class DemoInjector1 extends AbstractModule {
	@Override
	protected void configure() {
		

		bind(String.class).annotatedWith(Names.named("demotest")).toInstance("Hi this is binding class");;
	}
}

class DemoImpl1 {
	private String demoinit;

	@Inject
	public DemoImpl1(@Named("demotest") String demoinit) {
		this.demoinit = demoinit;
	}

	public void democall() {
		System.out.println(demoinit);
	}

}
