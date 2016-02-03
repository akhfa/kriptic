/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kriptic;

import java.util.function.BiConsumer;

/**
 *
 * @author akhfa
 */
public class Bigram {
    private char first;
    private char last;
    public Bigram(){};
    public Bigram(char _first, char _last)
    {
        first = _first;
        last = _last;
    }
    public char get_first()
    {
        return first;
    }
    public char get_last()
    {
        return last;
    }
    public void set_first(char a){
        first = a;
    }
    public void set_last(char b){
        last = b;
    }
    
}
