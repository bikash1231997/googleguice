package com.googleguice.guice.First;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

/*
 * Using Provider class 
 */
public class GuiceGit6 {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new DemoInjector4());
		DemoImpl4 d = injector.getInstance(DemoImpl4.class);
		d.democall();
	}
}

// binding module
class DemoInjector4 extends AbstractModule {
	@Override
	protected void configure() {
		bind(DemoInit4.class).toProvider(DemoProvider.class);
	}
}

class DemoImpl4 {
	private DemoInit4 demoinit; // interface referance

	@Inject
	public DemoImpl4(DemoInit4 demoinit) {
		this.demoinit = demoinit;
	}

	public void democall() {
		demoinit.democheck();
	}

}

//interface
interface DemoInit4 {
	public void democheck();
}

//interface implementation
class DemocheckImpl4 implements DemoInit4 {
	private String url;

	@Inject
	public DemocheckImpl4(String url) {
		this.url = url;
	}

	@Override
	public void democheck() {
		System.out.println("this is binding");
		System.out.println(url);
	}
}

// provider class
class DemoProvider implements Provider<DemoInit4> {

	@Override
	public DemoInit4 get() {
		String url = "this is url";
		DemoInit4 DemoInit4 = new DemocheckImpl4(url);
		return DemoInit4;
	}
}
