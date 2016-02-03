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
        
        Enkripsi enc = new Enkripsi("sidvneidne evn eji", "");
        enc.print_matrix(huruf);
        
        String test = "aku lungo pergi di pasar".toUpperCase();
        System.out.println(enc.get_string_unique_char(test.replaceAll(" ", "")));
        char [][] test2 = enc.get_matrix_key(enc.get_string_unique_char(test.replaceAll(" ", "")));
        enc.print_matrix(test2);
        System.out.println(enc.get_string_unique_char(test.replaceAll(" ", "")));
        System.out.println(enc.parse("haii ii"));
//        System.out.println(new StringBuilder("aka").deleteCharAt(2).toString());

        enc.print_bigram(enc.get_bigram());
    }
    
    /**
     * Mendapatkan bigram-bigram dari text yang diinputkan
     * @return Array of bigram hasil dari pengolahan string
     */
    private Bigram [] get_bigram()
    {
        String text = this.parse(this.text);
        Bigram [] bigram = new Bigram[text.length() / 2];
        int indeks_bigram = 0;
        
        for(int i = 0; i < text.length(); i+=2)
        {
            bigram[indeks_bigram] = new Bigram(text.charAt(i), text.charAt(i+1));
            indeks_bigram++;
        }
        return bigram;
    }
    
    /**
     * Mencetak array of bigram dalam format [first,last]
     * @param bigrams Array of bigram yang akan dicetak
     */
    private void print_bigram(Bigram [] bigrams)
    {
        for (Bigram bigram : bigrams) {
            System.out.println("[" + bigram.get_first() + ", " + bigram.get_last() + "]");
        }
    }
    
    /**
     * Mendapatkan string dari array of bigram untuk ditampilkan
     * @param bigrams Array of bigram yang ingin dijadikan string
     * @return String dari nilai bigram-bigram yang dimasukkan
     */
    private String get_bigram_string(Bigram [] bigrams)
    {
        String result = "";
        for (Bigram bigram : bigrams) {
            result = result + bigram.get_first() + bigram.get_last() + " ";
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }
    
    /**
     * Melakukan parse terhadap suatu string. Parsing tersebut meliputi
     * 1. Mengubah menjadi uppercase
     * 2. Mereplace J menjadi I
     * 3. Menghilangkan semua karakter bukan huruf
     * @param text Text yang akan di parse
     * @return String hasil parse
     */
    private String parse(String text)
    {
        text = text.toUpperCase();
        text = text.replaceAll("J", "I");
        text = text.replaceAll("[^A-Z]", "");
        
        // Cari huruf yang sama dan bersebelahan
        for (int i = 1; i < text.length(); i++)
        {
            if(text.charAt(i) == text.charAt(i-1))
            {
                text = new StringBuilder(text).insert(i, 'Z').toString();
            }
        }
        
        // Jika jumlah karakter ganjil, tambahkan z di belakang sendiri
        if(text.length()%2 != 0)
        {
            text = new StringBuilder(text).insert(text.length(), 'Z').toString();
        }
        
        return text;
    }
    
    /**
     * Membuat matriks key
     * @param unique_key Key yang sudah dibuat unique, sehingga tidak ada spasi 
     * dan huruf yang sama
     * @return Char [][] kunci
     */
    private char [][] get_matrix_key (String unique_key)
    {
        char [][] matrix_key = new char[5][5];
        char [] unique_key_array = unique_key.toCharArray();
        int i = 0;
        int j = 0;
        
        // masukkan kunci ke matriks
        for (int k = 0; k < unique_key.length(); k++)
        {
            matrix_key[i][j] = unique_key_array[k];
            j++;
            
            // Jika pengisian sudah sampai kolom terakhir
            if(j == 5)
            {
                j = 0;
                i++;
            }
        }
        
        String remaining_char = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        for(int l = 0; l < remaining_char.length(); l++)
        {
            // jika remaining karakter tidak ada di unique key, masukkan ke matriks sisanya
            if(unique_key.indexOf(remaining_char.charAt(l)) == -1)
            {
                matrix_key[i][j] = remaining_char.charAt(l);
                j++;
                // Jika pengisian sudah sampai kolom terakhir
                if(j == 5)
                {
                    j = 0;
                    i++;
                }
            }
        }
        
        return matrix_key;
    }
    
    /**
     * Mendapatkan string dengan char-char yang unik
     * @param input String yang ingin dijadikan unik karakternya
     * @return String yang karakternya sudah unik
     */
    private String get_string_unique_char(String input)
    {
        StringBuilder result_builder = new StringBuilder(input);
        
        while(!this.isStringUnique(input))
        {
            Couple same_char_index = this.get_first_last(input);
//            System.out.println("last index " + same_char_index.get_last());
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
    
    /**
     * Mengecek apakah karakter yang ada pada suatu string sudah unik semua
     * @param input String yang akan dicek unik atau tidak
     * @return True jika karakternya unik, false jika tidak
     */
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
    
    /**
     * Mencetak matrik
     * @param matriks Matrik yang ingin dicetak
     */
    private void print_matrix(char [][] matriks)
    {
        for(int i = 0; i < matriks.length; i++)
        {
            for (int j = 0; j < matriks[i].length; j++)
            {
                System.out.print(matriks[i][j]);
            }
            System.out.println();
        }
    }
}
