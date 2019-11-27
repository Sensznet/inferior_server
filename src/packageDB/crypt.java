
package packageDB;

import java.io.*;
import java.util.Base64;

public class crypt {
    static int key=5;
    public crypt()
    {
        //
    }
    public static String encrypt(String str)
    {
        String s="";
        s=encodeString2(str);
        s=encodeString1(s);
        return s;
    }
    public static String decrypt(String str)
    {
        String s="";
        s=decodeString1(str);
        s=decodeString2(s);
        return s;
    }
    private static String encodeString1(String p_sString)
    {
        if(p_sString==null)
        {
            return "";
        }
        return Base64.getEncoder().encodeToString(p_sString.getBytes());
    }
    private static String decodeString1(String p_sString)
    {
        if(p_sString==null || p_sString.equals(""))
        {
             return null;
        }
        return new String(Base64.getDecoder().decode(p_sString));
    }
    private static String encodeString2(String s)
    {
        char[] c= s.toCharArray();
        String res="";
        for(int i=0;i<c.length-1;i=i+2)
        {
            char c1=c[i];
            char c2=c[i+1];
            char c3=(char)('a'+26*Math.random());
            res=res+c2+c3+c1;

        }
        if (c.length%2!=0)
        {
            res=res+c[c.length-1];
        }
        return res;
    }
    private static String decodeString2(String s)
    {
        char[] c=s.toCharArray();
        String res="";
        for (int i=0;i<c.length-2;i=i+3)
        {
            res=res+c[i+2];

       res=res+c[i];

    }
    if (c.length % 3 != 0) {
      res=res + c[c.length - 1];
    }
        return res;
    }
}