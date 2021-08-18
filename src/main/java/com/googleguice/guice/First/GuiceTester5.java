package com.googleguice.guice.First;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class GuiceTester5 {
   public static void main(String[] args) {
      Injector injector = Guice.createInjector(new TextEditorModule5());	//1
      TextEditor5 editor = injector.getInstance(TextEditor5.class);			//3
      editor.makeSpellCheck();
   } 
}

class TextEditor5 {
   private SpellChecker5 spellChecker;
   @Inject
   public TextEditor5( SpellChecker5 spellChecker) {
      this.spellChecker = spellChecker;
   }

   public void makeSpellCheck(){
      spellChecker.checkSpelling();				//4
   } 
}

//Binding Module
class TextEditorModule5 extends AbstractModule {

   @Override
   protected void configure() {
      try {
         bind(SpellChecker5.class)
            .toConstructor(SpellCheckerImpl5.class.getConstructor(String.class));   //2.0
      } catch (NoSuchMethodException | SecurityException e) {
         System.out.println("Required constructor missing");
      } 
      bind(String.class)
         .annotatedWith(Names.named("JDBC"))
         .toInstance("jdbc:mysql://localhost:5326/emp");				//2.1
   } 
}

//spell checker interface
interface SpellChecker5 {
   public void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl5 implements SpellChecker5 {

   private String dbUrl;

   public SpellCheckerImpl5(){}

   public SpellCheckerImpl5(@Named("JDBC") String dbUrl){
      this.dbUrl = dbUrl;
   } 

   @Override
   public void checkSpelling() { 
      System.out.println("Inside checkSpelling." );			//5
      System.out.println(dbUrl); 
   }
}
