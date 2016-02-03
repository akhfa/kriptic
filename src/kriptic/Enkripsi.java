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
public interface Enkripsi {
    
    public abstract void setPlain(String _text);
    
    public abstract void setKunci(String _kunci);
    
    public abstract String encrypt();
    
    public abstract String decrypt();
    
    public abstract String getPlain();
    
    public abstract String getKunci();
}
