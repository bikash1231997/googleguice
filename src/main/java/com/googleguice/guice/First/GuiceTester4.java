package com.googleguice.guice.First;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;

public class GuiceTester4 {
   public static void main(String[] args) {
      Injector injector = Guice.createInjector(new TextEditorModule4()); 		// 1
      TextEditor4 editor = injector.getInstance(TextEditor4.class);				// 2
      editor.makeSpellCheck(); 
   } 
}

class TextEditor4 {
   private SpellChecker4 spellChecker;
   @Inject
   public TextEditor4( SpellChecker4 spellChecker) {							//6
      this.spellChecker = spellChecker;
   }
   public void makeSpellCheck(){											
      spellChecker.checkSpelling();												//7
   } 
}

//Binding Module
class TextEditorModule4 extends AbstractModule {

   @Override
   protected void configure() {} 

   @Provides																	// call due to provide annotation
   public SpellChecker4 provideSpellChecker(){  								//3

      String dbUrl = "jdbc:mysql://localhost:5326/emp"; 
      String user = "user";
      int timeout = 100;

      SpellChecker4 SpellChecker = new SpellCheckerImpl4(dbUrl, user, timeout);	//4
      return SpellChecker;
   }
}

//spell checker interface
interface SpellChecker4 {
public void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl4 implements SpellChecker4 {

   private String dbUrl;
   private String user;
   private Integer timeout;
   																				//5
   @Inject
   public SpellCheckerImpl4(String dbUrl, String user, Integer timeout){
      this.dbUrl = dbUrl;
      this.user = user;
      this.timeout = timeout;
   } 

   @Override
   public void checkSpelling() { 
      System.out.println("Inside checkSpelling." );								//8		
      System.out.println(dbUrl);
      System.out.println(user);
      System.out.println(timeout);
   }
}