package org.hbrs.se1.ws24.exercises.uebung1.control;

public class TranslatorFactory {
    //private static GermanTranslator translator1;

    public static Translator creatTranslator(){

        return new GermanTranslator();
    }

    public static Translator creatTranslator(String date){
        GermanTranslator g1 = new GermanTranslator();
        g1.setDate(date);
        return g1;
    }

}
