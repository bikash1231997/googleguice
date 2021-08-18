package com.googleguice.guice.First;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@BindingAnnotation
@Target({ FIELD, PARAMETER, METHOD })
@Retention(RUNTIME)
@interface WinWord3 {
}

public class GuiceTester3 {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new TextEditorModule3());
		TextEditor3 editor = injector.getInstance(TextEditor3.class);
		editor.makeSpellCheck();
	}
}

class TextEditor3 {
	private SpellChecker3 spellChecker;

	@Inject
	public TextEditor3(@WinWord3 SpellChecker3 spellChecker) {
		this.spellChecker = spellChecker;
	}

	public void makeSpellCheck() {
		spellChecker.checkSpelling();
	}
}

//Binding Module
class TextEditorModule3 extends AbstractModule {

	@Override
	protected void configure() {
		bind(SpellChecker3.class).annotatedWith(WinWord3.class).to(WinWordSpellCheckerImpl3.class);
	}
}

//spell checker interface
interface SpellChecker3 {
	public void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl3 implements SpellChecker3 {

	@Override
	public void checkSpelling() {
		System.out.println("Inside checkSpelling.");
	}
}

//subclass of SpellCheckerImpl
class WinWordSpellCheckerImpl3 extends SpellCheckerImpl3 {
	@Override
	public void checkSpelling() {
		System.out.println("Inside WinWordSpellCheckerImpl.checkSpelling.");
	}
}
