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
public class Test {
    public static void main(String[] args) {
        Enkripsi enc = new Enkripsi("aku pergi ke pasar", "abx");
        System.out.println(enc.vigenere());
    }
    
    
    
}
