package org.hbrs.se1.ws24.exercises.uebung1.control;

public class TranslatorFactory {
    //private static GermanTranslator translator1;

    public static Translator createTranslator(){

        return new GermanTranslator();
    }

    public static Translator createTranslator(String date){
        GermanTranslator g1 = new GermanTranslator();
        g1.setDate(date);
        return g1;
    }

}
