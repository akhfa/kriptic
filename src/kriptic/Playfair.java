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
        
        Playfair playfair = new Playfair("GOOD BROOMS SWEEP CLEAN", "STANDERCHBK");
//        playfair.print_matrix(huruf);
//        
//        String test = "aku lungo pergi di pasar".toUpperCase();
//        System.out.println(playfair.get_string_unique_char(test.replaceAll(" ", "")));
//        char [][] test2 = playfair.get_matrix_key();
//        playfair.print_matrix(test2);
//        System.out.println(playfair.get_string_unique_char(test.replaceAll(" ", "")));
//        System.out.println(playfair.parse("haii ii"));
////        System.out.println(new StringBuilder("aka").deleteCharAt(2).toString());
//
//        playfair.print_bigram(playfair.get_bigram());


        System.err.println("plain " + playfair.getPlain());
        System.err.println("kunci " + playfair.getKunci());
        System.err.println(playfair.parse(true));
        System.err.println(playfair.get_bigram_string(playfair.get_bigram(true)));
        playfair.print_matrix(playfair.get_matrix_key());
        System.out.println(playfair.encrypt());
        
//        System.out.println("===================================");
//        
        Playfair playfair2 = new Playfair("FP WU BL FW WU VE VD VR VB XG KB ND", "STANDERCHBK");
        System.err.println(playfair2.get_bigram_string(playfair2.get_bigram(false)));
        System.out.println(playfair2.decrypt());
    }
    
    /**
     * Mendapatkan bigram-bigram dari text yang diinputkan
     * @return Array of bigram hasil dari pengolahan string
     */
    private Bigram [] get_bigram(boolean encrypt)
    {
        this.text = this.parse(encrypt);

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
    public String parse(boolean encrypt)
    {
        text = text.toUpperCase();
        text = text.replaceAll("J", "I");
        text = text.replaceAll("[^A-Z]", "");
        
        if(encrypt)
        {
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
        Bigram [] bigrams = this.get_bigram(true);
        char [][] matriks_key = this.get_matrix_key();
        for(int i = 0; i < bigrams.length; i++) {
            Point first = this.get_posisi(matriks_key, bigrams[i].get_first());
            Point last = this.get_posisi(matriks_key, bigrams[i].get_last());
            
            // Kondisi pergeseran enkripsi
            int first_x = -1, first_y = -1, last_x = -1, last_y = -1;
            
            // jika dalam 1 baris, geser ke kanan 1 huruf
            if(first.get_x() == last.get_x())
            {
                // geser ke kanan 1 langkah
                first_x = first.get_x();
                first_y = (first.get_y() + 1) % 5;
                last_x = last.get_x();
                last_y = (last.get_y() + 1) % 5;
                
            }
            //jika dalam 1 kolom, geser ke bawah 1 huruf
            else if(first.get_y() == last.get_y())
            {
                // geser ke bawah 1 langkah
                first_x = (first.get_x() + 1) % 5;
                first_y = first.get_y();
                last_x = (last.get_x() + 1) % 5;
                last_y = last.get_y();
                
            }
            else // tukar Y nya
            {
                first_x = first.get_x();
                first_y = last.get_y();
                last_x = last.get_x();
                last_y = first.get_y();
            }
            bigrams[i].set_first(matriks_key[first_x][first_y]);
            bigrams[i].set_last(matriks_key[last_x][last_y]);
        }
        return this.get_bigram_string(bigrams);
    }
    
    private Point get_posisi(char [][] matriks, char a)
    {
        int x = -1, y = -1;
        for(int i = 0; i < matriks.length; i++)
        {
            for (int j = 0; j < matriks[i].length; j++)
            {
                if(matriks[i][j] == a)
                {
                    x = i;
                    y = j;
                }
            }
        }
        return new Point(x, y);
    }

    @Override
    public String decrypt() {
        Bigram [] bigrams = this.get_bigram(false);
        char [][] matriks_key = this.get_matrix_key();
        for(int i = 0; i < bigrams.length; i++) {
            Point first = this.get_posisi(matriks_key, bigrams[i].get_first());
            Point last = this.get_posisi(matriks_key, bigrams[i].get_last());
            
            // Kondisi pergeseran enkripsi
            int first_x = -1, first_y = -1, last_x = -1, last_y = -1;
            
            // jika dalam 1 baris, geser ke kiri 1 huruf
            if(first.get_x() == last.get_x())
            {
                // geser ke kanan 1 langkah
                first_x = first.get_x();
                first_y = (first.get_y() + 4) % 5;
                last_x = last.get_x();
                last_y = (last.get_y() + 4) % 5;
                
            }
            //jika dalam 1 kolom, geser ke atas 1 huruf
            else if(first.get_y() == last.get_y())
            {
                // geser ke bawah 1 langkah
                first_x = (first.get_x() + 4) % 5;
                first_y = first.get_y();
                last_x = (last.get_x() + 4) % 5;
                last_y = last.get_y();

            }
            else // tukar Y nya
            {
                first_x = first.get_x();
                first_y = last.get_y();
                last_x = last.get_x();
                last_y = first.get_y();
            }
            bigrams[i].set_first(matriks_key[first_x][first_y]);
            bigrams[i].set_last(matriks_key[last_x][last_y]);
        }
        return this.get_bigram_string(bigrams);
    }

    @Override
    public String getPlain() {
        return this.text;
    }

    @Override
    public String getKunci() {
        return this.kunci;
    }
}
