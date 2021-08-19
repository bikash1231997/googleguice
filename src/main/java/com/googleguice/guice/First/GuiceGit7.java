package com.googleguice.guice.First;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/*
 * Provider annotation 
 */
public class GuiceGit7 {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new DemoInjector5());
		DemoImpl5 d = injector.getInstance(DemoImpl5.class);
		d.democall();
	}
}

class DemoInjector5 extends AbstractModule {
	@Override
	protected void configure() {
		try {
		bind(DemoInit4.class).toConstructor(DemocheckImpl5.class.getConstructor(String.class));
	} catch (NoSuchMethodException | SecurityException e) {
        System.out.println("Required constructor missing");
     } 
		bind(String.class)
        .annotatedWith(Names.named("URL"))
        .toInstance("This is url");
	}
}

class DemoImpl5 {
	private DemoInit5 demoinit;

	@Inject
	public DemoImpl5(DemoInit5 demoinit) {
		this.demoinit = demoinit;
	}

	public void democall() {
		demoinit.democheck();
	}

}

interface DemoInit5 {
	public void democheck();
}

class DemocheckImpl5 implements DemoInit5 {
	private String url;

	@Inject
	public DemocheckImpl5(@Named("URL")String url) {
		this.url = url;
	}

	@Override
	public void democheck() {
		System.out.println("this is binding");
		System.out.println(url);
	}
}
