package com.googleguice.guice.First;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;

public class GuiceGit2 {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new Gitnewclass()); // 1.0
		Gitclass editor = injector.getInstance(Gitclass.class); // 2
		editor.gitmethodcall();
	}
}

interface gitinter {
	public void gitmethod();						
}

class Gitclass {									//2.1
	private gitinter git;

	@Inject
	public Gitclass(gitinter git) {
		this.git = git;
	}

	public void gitmethodcall() {					//2.2
		git.gitmethod();
	}

}

class Gitnewclass extends AbstractModule {
	@Override
	protected void configure() {
		bind(gitinter.class).to(gitinterimpl.class);  //1.1
	}

}
class gitinterimpl implements gitinter{					//2.3
	@Override
	public void gitmethod() {
		System.out.println("hi");
	}
}