package net.sasu.lib.elapsedtime.util;

/**
 * String-related static utility methods.
 */
public class StringUtil {

    private StringUtil(){
        throw new IllegalStateException("Utility class, static use only");
    }

    /**
     * Pads given input string with the given padding character
     * so that it reaches the given length. If the input string
     * already has the at least the desired length, the original
     * string is returned.
     *
     * @param inputString string to pad
     * @param padChar pad character
     * @param len total (desired) length after padding
     * @return padded String
     */
    public static String padLeft(String inputString, char padChar, int len){
        if(inputString == null){
            throw new IllegalArgumentException("inputString may not be null");

        }
        if(len < 1){
            throw new IllegalArgumentException("len has to be at least 1");
        }

        if(inputString.length() >= len){
            //already padded
            return inputString;
        }

        char[] retCharArray = new char[len];

        char[] ica = inputString.toCharArray();

        int padLen = len - ica.length;
        for(int i = 0; i < padLen; i++ ){
            retCharArray[i] = padChar;
        }

        int j = 0;
        for(int i = padLen; i < len; i++){
            retCharArray[i] = ica[j++];
        }

        return new String(retCharArray);
    }
}
