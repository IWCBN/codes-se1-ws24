package org.hbrs.se1.ws24.exercises.uebung9;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class TextDocument extends CoreDocument{

    private byte[] text;
    private Encoding encoding;

    public TextDocument(String text, Encoding encoding) throws UnsupportedEncodingException {
        super();

        this.encoding = encoding;

        if(encoding == Encoding.UTF8){
            this.text = text.getBytes(StandardCharsets.UTF_8);
        } else if(encoding == Encoding.UTF16){
            this.text = text.getBytes(StandardCharsets.UTF_16);
        } else if(encoding == Encoding.UTF32){
            this.text = text.getBytes("UTF-32");
        } else {
            throw new UnsupportedEncodingException();
        }
    }

    @Override
    public int getSize() {
        return text.length;
    }

    public enum Encoding {
        UTF8, UTF16, UTF32
    }
}

