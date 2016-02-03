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
public class Point extends Couple{
    
    public Point(int _first, int _last) {
        super(_first, _last);
    }
    
    public int get_x()
    {
        return super.get_first();
    }
    
    public int get_y()
    {
        return super.get_last();
    }
    
    public void set_x(int _x)
    {
        super.set_first(_x);
    }
    
    public void set_y(int _y)
    {
        super.set_last(_y);
    }
}
