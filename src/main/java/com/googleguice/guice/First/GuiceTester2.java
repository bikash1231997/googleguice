package com.googleguice.guice.First;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

@BindingAnnotation
@Target({ FIELD, PARAMETER, METHOD })
@Retention(RUNTIME)
@interface WinWord2 {
}
public class GuiceTester2 {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new TextEditorModule2());
		TextEditor2 editor = injector.getInstance(TextEditor2.class);
		editor.makeSpellCheck();
	}
}

class TextEditor2 {
	private SpellChecker2 spellChecker;

	@Inject
	public TextEditor2(@WinWord2 SpellChecker2 spellChecker) {
		this.spellChecker = spellChecker;
	}

	public void makeSpellCheck() {
		spellChecker.checkSpelling();
	}
}

// Binding Module
class TextEditorModule2 extends AbstractModule {

	@Override
	protected void configure() {
		bind(SpellChecker2.class).annotatedWith(WinWord2.class).to(OpenOfficeWordSpellCheckerImpl2.class);
	}
}

// spell checker interface
interface SpellChecker2 {
	public void checkSpelling();
}

// spell checker implementation
class SpellCheckerImpl2 implements SpellChecker2 {

	@Override
	public void checkSpelling() {
		System.out.println("Inside checkSpelling.");
	}
}

// subclass of SpellCheckerImpl
class OpenOfficeWordSpellCheckerImpl2 extends SpellCheckerImpl2 {
	@Override
	public void checkSpelling() {
		System.out.println("Inside OpenOfficeWordSpellCheckerImpl.checkSpelling.");
	}
}
