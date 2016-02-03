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
public class Couple {
    private int first;
    private int last;
    public Couple(int _first, int _last)
    {
        first = _first;
        last = _last;
    }
    public int get_first()
    {
        return first;
    }
    public int get_last()
    {
        return last;
    }
    public void set_first(int _first)
    {
        this.first = _first;
    }
    public void set_last(int _last)
    {
        this.last = _last;
    }
}
