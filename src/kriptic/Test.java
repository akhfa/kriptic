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
        Enkripsi enc = new Enkripsi("aku pergi\nke pasar", "abx");
        System.out.println(enc.vigenere(true));
        
        Enkripsi dec = new Enkripsi("BMS QGPHK IF RYTCP", "abx");
        System.out.println(dec.vigenere(false));
//        System.out.println(dec.vi);
    }
}