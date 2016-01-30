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
    String plain;
    String kunci;
    
    public Enkripsi(String _plain, String _kunci)
    {
        this.plain = _plain;
        this.kunci = _kunci;
    }
    
    public void setPlain(String _plain)
    {
        this.plain = _plain;
    }
    
    public void setKunci(String _kunci)
    {
        this.kunci = _kunci;
    }
    
    public String vigenere()
    {
        char [] huruf = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        
        // Hilangkan spasi dan ubah jadi uppercase
        this.plain = this.plain.toUpperCase();
        this.kunci = this.kunci.toUpperCase();
        
        char [] plain_char = this.plain.toCharArray();
        char [] kunci_char = this.kunci.toCharArray();
        StringBuilder result = new StringBuilder(plain_char.length);
        char chiper = '\0';
        
        int indexKunci = 0;
        for(int i = 0; i < plain_char.length; i++)
        {
            if(plain_char[i] != ' ')
            {
                chiper = jumlah(huruf, plain_char[i], kunci_char[indexKunci]);
                
                if(indexKunci == kunci_char.length - 1)
                    indexKunci = 0;
                else
                    indexKunci++;
                result.append(chiper);
            }
            else
                result.append(" ");
            
        }
        return result.toString();
    }
    
    private char jumlah(char [] huruf,char plain_char, char kunci_char)
    {
        int plain_int = getIndexOf(huruf, plain_char);
        int kunci_int = getIndexOf(huruf, kunci_char);
        int chiper_int = ((plain_int + kunci_int) + 1) % 26;
        return huruf[chiper_int];
    }
    
    /**
     * Mencari index karakter a pada array of karakter daftarKarakter
     * @param daftarKarakter
     * @param a
     * @return 
     */
    private int getIndexOf(char [] daftarKarakter, char a)
    {
        int result = -1;
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
