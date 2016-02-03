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
public class Playfair implements Enkripsi{
    private String text;
    private String kunci;
    
    public Playfair (String _text, String _kunci)
    {
        this.text = _text;
        this.kunci = _kunci;
    }
    
    public static void main(String[] args) {
        char [][] huruf = {
            {'1','2','3'},
            {'4','5','6'},
            {'7','8','9'},
            {'1','1','1'}
        };
        
        Playfair playfair = new Playfair("sidvneidne evn eji", "");
        playfair.print_matrix(huruf);
        
        String test = "aku lungo pergi di pasar".toUpperCase();
        System.out.println(playfair.get_string_unique_char(test.replaceAll(" ", "")));
        char [][] test2 = playfair.get_matrix_key();
        playfair.print_matrix(test2);
        System.out.println(playfair.get_string_unique_char(test.replaceAll(" ", "")));
        System.out.println(playfair.parse("haii ii"));
//        System.out.println(new StringBuilder("aka").deleteCharAt(2).toString());

        playfair.print_bigram(playfair.get_bigram());
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
    private char [][] get_matrix_key ()
    {
        String new_kunci = this.kunci.replaceAll(" ", "").toUpperCase();
        String unique_key = this.get_string_unique_char(this.kunci);
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
        Bigram [] bigrams = this.get_bigram();
        char [][] matriks_key = this.get_matrix_key();
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//    public boolean is_same_row(char [][] matriks, char a, char b)
//    {
//        boolean same = false;
//        for(int i = 0; i < matriks.length ; i++)
//        {
//            String temp = new String (matriks[i]);
//            if(temp.indexOf(a) != -1  && temp.indexOf(b) != -1)
//            {
//                same = true;
//            }
//        }
//        return same;
//    }
    
//    public boolean is_same_column(char [][] matriks, char a, char b)
//    {
//        boolean same = false;
//        for(int i = 0; i < matriks.length ; i++)
//        {
//            for(int j = 0; j < matriks[i].length; i++)
//            {
//                String temp = new String (matriks[i]);
//                if(temp.indexOf(a) != -1  && temp.indexOf(b) != -1)
//                {
//                    same = true;
//                }
//            }
//        }
//        return same;
//    }

    @Override
    public String decrypt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
