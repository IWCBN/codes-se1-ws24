package org.hbrs.se1.ws24.exercises.uebung9;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class TextDocument extends CoreDocument{

    private byte[] text;
    private Encoding encoding;

    public TextDocument(String text, Encoding encoding) throws UnsupportedEncodingException {
        super();

        this.encoding = encoding;

        switch(encoding) {
            case UTF8:
                this.text = text.getBytes(StandardCharsets.UTF_8);
                break;
            case UTF16:
                this.text = text.getBytes(StandardCharsets.UTF_16);
                break;
            case UTF32:
                this.text = text.getBytes("UTF-32");
                break;
            default:
                throw new UnsupportedEncodingException();
        }
    }

    @Override
    public int getSize() {
        return text.length;
    }

    public enum Encoding {
        UTF8("UTF-8"), UTF16("UTF-16"), UTF32("UTF-32");

        private final String code;

        private Encoding(String code) {
            this.code = code;
        }
        public String getCode() {
            return code;
        }
    }
}

