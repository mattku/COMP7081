/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Brian
 */
public class Util
{
    private static MessageDigest md5;
    
    static
    {
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex)
        {}
    }
    
    public static String mySQLCompatibleMD5(String s)
    {
        byte[] bytes;
        try
        {
            bytes = md5.digest(s.getBytes("UTF-8"));
        }
        catch(UnsupportedEncodingException uee)
        {   
            bytes = new byte[0];
        }
        StringBuilder sb = new StringBuilder();
        for( int i=0; i<bytes.length; i++ )     
        {
           byte b = bytes[ i ];
           String hex = Integer.toHexString((int) 0x00FF & b);
           if (hex.length() == 1) 
           {
              sb.append("0");
           }
           sb.append( hex );
        }
        return sb.toString();
    }
}
