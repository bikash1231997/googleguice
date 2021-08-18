package com.googleguice.guice.First;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class GuiceTester1 {
	// Linking Binding
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new TextEditorModule1());
		TextEditor1 editor = injector.getInstance(TextEditor1.class);
		editor.makeSpellCheck();
	}
}

class TextEditor1 {
	private SpellChecker1 spellChecker;

	@Inject
	public TextEditor1(SpellChecker1 spellChecker) {
		this.spellChecker = spellChecker;
	}

	public void makeSpellCheck() {
		spellChecker.checkSpelling();
	}
}

// Binding Module
class TextEditorModule1 extends AbstractModule {

	@Override
	protected void configure() {
		bind(SpellChecker1.class).to(SpellCheckerImpl1.class);
		bind(SpellCheckerImpl1.class).to(WinWordSpellCheckerImpl1.class);
	}
}

// spell checker interface
interface SpellChecker1 {
	public void checkSpelling();
}

// spell checker implementation
class SpellCheckerImpl1 implements SpellChecker1 {

	@Override
	public void checkSpelling() {
		System.out.println("Inside checkSpelling.");
	}
}

// subclass of SpellCheckerImpl
class WinWordSpellCheckerImpl1 extends SpellCheckerImpl1 {
	@Override
	public void checkSpelling() {
		System.out.println("Inside WinWordSpellCheckerImpl.checkSpelling.");
	}
}
