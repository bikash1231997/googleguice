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
public class GuiceGit5 {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new DemoInjector3());
		DemoImpl3 d = injector.getInstance(DemoImpl3.class);
		d.democall();
	}
}

class DemoInjector3 extends AbstractModule {
	@Override
	protected void configure() {}
	
	@Provides
	public DemoInit3 proDemoInit3() {
		String url ="Hi this is url";
		DemoInit3 DemoInit3 = new DemocheckImpl3(url);
		return DemoInit3;
	}
}

class DemoImpl3 {
	private DemoInit3 demoinit;

	@Inject
	public DemoImpl3(DemoInit3 demoinit) {
		this.demoinit = demoinit;
	}

	public void democall() {
		demoinit.democheck();
	}

}

interface DemoInit3 {
	public void democheck();
}

class DemocheckImpl3 implements DemoInit3{
	private String url;
	
	@Inject
	public DemocheckImpl3(String url) {
		this.url = url;
	}
	
	@Override
	public void democheck() {
		System.out.println("this is binding");
		System.out.println(url);
	}
}


