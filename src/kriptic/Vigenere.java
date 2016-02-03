/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kriptic;

/**
 *
 * @author akhfa
 */
public class Vigenere implements Enkripsi{
    private String text;
    private String kunci;
    private boolean basic;
    
    public Vigenere(String _text, String _kunci, boolean _basic)
    {
        this.text = _text;
        this.kunci = _kunci;
        this.basic = _basic;
    }
    
    /**
     * Fungsi untuk melakukan enkripsi ataupun dekripsi vigenere. 
     * @param basic Menandakan vigenere mana yang akan digunakan. True untuk basic, false untuk ext
     * @param encrypt Menandakan apakah akan memanggil fungsi encrypt atau decrypt
     * @return Sring hasil enkripsi maupun deskripsi
     */
    public String vigenere(boolean basic, boolean encrypt)
    {
        char [] huruf = this.getString(basic).toCharArray();
            
        // Hilangkan spasi dan ubah jadi uppercase
        if(basic)
        {
            this.text = this.text.toUpperCase();
            this.kunci = this.kunci.toUpperCase();
        }
        
        char [] text_char = this.text.toCharArray();
        char [] kunci_char = this.kunci.toCharArray();
        StringBuilder result = new StringBuilder(text_char.length);
        char result_char = '\0';
        
        int indexKunci = 0;
        for(int i = 0; i < text_char.length; i++)
        {
            if(getIndexOf(huruf, text_char[i]) != -1)
            {
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
    
    /**
     * Mendapatkan semua char yang bisa di enkrip atau decrypt. Char dibentuk menjadi string.
     * @param basic Apakah string digunakan untuk vigenere basik atau ext. 
     * True jika basic, dan false jika ext.
     * @return String yang beranggotakan char yang dapat di enkrip dekrip
     */
    private String getString(boolean basic)
    {
        String result = "";
        if(basic)
        {
            result = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            
        }
        else
        {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 255; i++)
            {
                builder.append((char)i);
            }
            result = builder.toString();
        }
        return result;
    }
    
    /**
     * Daftar huruf yang menunjukkan indeks "posisi" dari suatu karakter
     * @param huruf Daftar huruf yang menunjukkan indeks "posisi" dari suatu karakter
     * @param plain_char Karakter chiper yang akan didekrip
     * @param kunci_char Karakter kunci
     * @return Char yang telah dienkrip
     */
    private char enc(char [] huruf,char plain_char, char kunci_char)
    {
        int plain_int = getIndexOf(huruf, plain_char);
        int kunci_int = getIndexOf(huruf, kunci_char);
        int chiper_int = (plain_int + kunci_int) % huruf.length;
        return huruf[chiper_int];
    }
    
    /**
     * Melakukan dekripsi menggunakan perhitungan vigenere chiper
     * @param huruf Daftar huruf yang menunjukkan indeks "posisi" dari suatu karakter
     * @param chiper_char Karakter chiper yang akan didekrip
     * @param kunci_char Karakter kunci
     * @return Char yang telah didekrip
     */
    private char dec(char [] huruf,char chiper_char, char kunci_char)
    {
        int chiper_int = getIndexOf(huruf, chiper_char);
        int kunci_int = getIndexOf(huruf, kunci_char);
        int plain_int = (chiper_int - kunci_int) % huruf.length;
        if(plain_int < 0)
            plain_int += huruf.length;
        return huruf[plain_int];
    }
    
    /**
     * Mencari index karakter a pada array of karakter daftarKarakter
     * @param daftarKarakter
     * @param a
     * @return Indeks dari karakter yang dicari. -1 jika tidak ditemukan
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

    @Override
    public void setPlain(String _text) {
        this.text = _text;
    }

    @Override
    public void setKunci(String _kunci) {
        this.kunci = _kunci;
    }

    @Override
    public String encrypt() {
        char [] huruf = this.getString(basic).toCharArray();
            
        // Hilangkan spasi dan ubah jadi uppercase
        if(basic)
        {
            this.text = this.text.toUpperCase();
            this.kunci = this.kunci.toUpperCase();
        }
        
        char [] text_char = this.text.toCharArray();
        char [] kunci_char = this.kunci.toCharArray();
        StringBuilder result = new StringBuilder(text_char.length);
        char result_char = '\0';
        
        int indexKunci = 0;
        for(int i = 0; i < text_char.length; i++)
        {
            if(getIndexOf(huruf, text_char[i]) != -1)
            {
                result_char = enc(huruf, text_char[i], kunci_char[indexKunci]);
                
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
    
    /**
     * Mendapatkan semua char yang bisa di enkrip atau decrypt. Char dibentuk menjadi string.
     * @param basic Apakah string digunakan untuk vigenere basik atau ext. 
     * True jika basic, dan false jika ext.
     * @return String yang beranggotakan char yang dapat di enkrip dekrip
     */
    private String getString()
    {
        String result = "";
        if(basic)
        {
            result = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
        else
        {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 255; i++)
            {
                builder.append((char)i);
            }
            result = builder.toString();
        }
        return result;
    }

    @Override
    public String decrypt() {
        char [] huruf = this.getString(basic).toCharArray();
            
        // Hilangkan spasi dan ubah jadi uppercase
        if(basic)
        {
            this.text = this.text.toUpperCase();
            this.kunci = this.kunci.toUpperCase();
        }
        
        char [] text_char = this.text.toCharArray();
        char [] kunci_char = this.kunci.toCharArray();
        StringBuilder result = new StringBuilder(text_char.length);
        char result_char = '\0';
        
        int indexKunci = 0;
        for(int i = 0; i < text_char.length; i++)
        {
            if(getIndexOf(huruf, text_char[i]) != -1)
            {
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
}
