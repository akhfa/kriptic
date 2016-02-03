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

    /* =================== Fungsi untuk playfair ========================== */
    //public String 
    
    public static void main(String[] args) {
        char [][] huruf = {
            {'1','2','3'},
            {'4','5','6'},
            {'7','8','9'},
            {'1','1','1'}
        };
        
        Enkripsi enc = new Enkripsi("", "");
        enc.print_matrix(huruf);
        System.out.println(enc.get_string_unique_char("aku lungo pergi di pasar"));
//        System.out.println(new StringBuilder("aka").deleteCharAt(2).toString());
    }
    
    private char [][] get_matrix_key (String kunci)
    {
        char [][] matrix_key = {
            {'A','B','C','D','E'},
            {'F','G','H','J','K'},
            {'A','B','C','D','E'},
            {'A','B','C','D','E'},
            {'A','B','C','D','E'},
        };
        return matrix_key;
    }
    
    private String get_string_unique_char(String input)
    {
        StringBuilder result_builder = new StringBuilder(input);
        
        while(!this.isStringUnique(input))
        {
            Couple same_char_index = this.get_first_last(input);
            System.out.println("last index " + same_char_index.get_last());
            result_builder.deleteCharAt(same_char_index.get_last());
            input = result_builder.toString();
        }
        return result_builder.toString();
    }
    
    /**
     * Mendapatkan first dan last index karakter yang sama pada suatu string
     * @param input String yang dicek
     * @return -1,-1 jika unique atau index awal dan akhir karakter yang sama
     */
    private Couple get_first_last(String input)
    {
        boolean unique = true;
        int first = -1, last = -1;
        char [] input_char = input.toCharArray();
        for(int i = 0; i < input_char.length; i++)
        {
            if(i != input.lastIndexOf(input_char[i]))
            {
                unique = false;
                first = i;
                last = input.lastIndexOf(input_char[i]);
            }
        }
        return new Couple(first, last);
    }
    
    private boolean isStringUnique(String input)
    {
        boolean unique = true;
        char [] input_char = input.toCharArray();
        for(int i = 0; i < input_char.length; i++)
        {
            if(i != input.lastIndexOf(input_char[i]))
            {
                unique = false;
            }
        }
        return unique;
    }
    
//    private boolean isExistInMatrix(char [][] matrix, char huruf)
//    {
//        for 
//    }
    
    private void print_matrix(char [][] huruf)
    {
        for(int i = 0; i < huruf.length; i++)
        {
            for (int j = 0; j < huruf[i].length; j++)
            {
                System.out.print(huruf[i][j]);
            }
            System.out.println();
        }
    }
}
