/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kriptic;

import java.util.ArrayList;

/**
 *
 * @author akhfa
 */
public class Enkripsi {
    String text;
    String kunci;
    
    public Enkripsi(String _text, String _kunci)
    {
        this.text = _text;
        this.kunci = _kunci;
    }
    
    public void setPlain(String _text)
    {
        this.text = _text;
    }
    
    public void setKunci(String _kunci)
    {
        this.kunci = _kunci;
    }
    
    public String vigenere(boolean encrypt)
    {
        char [] huruf = " ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        
        // Hilangkan spasi dan ubah jadi uppercase
        this.text = this.text.toUpperCase();
        this.kunci = this.kunci.toUpperCase();
        
        char [] text_char = this.text.toCharArray();
        char [] kunci_char = this.kunci.toCharArray();
        StringBuilder result = new StringBuilder(text_char.length);
        char result_char = '\0';
        
        int indexKunci = 0;
        for(int i = 0; i < text_char.length; i++)
        {
            if(getIndexOf(huruf, text_char[i]) != 0)
            {
                System.out.println("getIndex " + getIndexOf(huruf, text_char[i]));
                if(encrypt)
                    result_char = enc(huruf, text_char[i], kunci_char[indexKunci]);
                else
                    result_char = dec(huruf, text_char[i], kunci_char[indexKunci]);
                
                if(indexKunci == kunci_char.length - 1)
                    indexKunci = 0;
                else
                    indexKunci++;
                result.append(result_char);
            }
            else
                result.append(text_char[i]);
            
        }
        return result.toString();
    }
    
    private char enc(char [] huruf,char plain_char, char kunci_char)
    {
        int plain_int = getIndexOf(huruf, plain_char);
        int kunci_int = getIndexOf(huruf, kunci_char);
        int chiper_int = (plain_int + kunci_int) % 26;
        return huruf[chiper_int];
    }
    
    private char dec(char [] huruf,char chiper_char, char kunci_char)
    {
        System.out.println("chiper_char " + chiper_char);
        int chiper_int = getIndexOf(huruf, chiper_char);
        System.out.println("chiper_int " + chiper_int);
        int kunci_int = getIndexOf(huruf, kunci_char);
        System.out.println("kunci_int " + kunci_int);
        int plain_int = (chiper_int - kunci_int) % 26;
        if(plain_int < 0)
            plain_int += 26;
        System.out.println("plain_int " + huruf[plain_int]);
        return huruf[plain_int];
    }
    
    /**
     * Mencari index karakter a pada array of karakter daftarKarakter
     * @param daftarKarakter
     * @param a
     * @return 
     */
    private int getIndexOf(char [] daftarKarakter, char a)
    {
        int result = 0;
        for(int i = 0; i < daftarKarakter.length; i++)
        {
            if(daftarKarakter[i] == a)
            {
                result = i;
            }
        }
        return result;
    }
    
    public String vigenere_ascii(String plain)
    {
        
        return "";
    }
}
