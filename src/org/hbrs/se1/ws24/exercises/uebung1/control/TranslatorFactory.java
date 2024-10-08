package org.hbrs.se1.ws24.exercises.uebung1.control;

public class TranslatorFactory {
    //private static GermanTranslator translator1;
    
    //Umbenennen in createGermanTranslator
    public static Translator createTranslator(){
        return new GermanTranslator();
    }

    //Umbenennen in createGermanTranslator
    public static Translator createTranslator(String date){
        GermanTranslator t1 = new GermanTranslator();
        t1.setDate(date);
        return t1;
    }

}
