package com.googleguice.guice.First;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

@BindingAnnotation
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@interface demobinding {
}

public class GuiceGit3 {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new DemoInjector());
		DemoImpl d = injector.getInstance(DemoImpl.class);
		d.democall();
	}
}

class DemoInjector extends AbstractModule {
	@Override
	protected void configure() {
		bind(DemoInit.class).annotatedWith(demobinding.class).to(DemocheckImpl.class);
	}
}

class DemoImpl {
	private DemoInit demoinit;

	@Inject
	public DemoImpl(@demobinding DemoInit demoinit) {
		this.demoinit = demoinit;
	}

	public void democall() {
		demoinit.democheck();
	}

}

interface DemoInit {
	public void democheck();
}

class DemocheckImpl implements DemoInit {
	@Override
	public void democheck() {
		System.out.println("this is binding");
	}
}

class demo extends DemocheckImpl {

	@Override
	public void democheck() {
		System.out.println("this is binding new");
	}
}
