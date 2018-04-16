/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herikles.main;

import com.herikles.agoritimo.Classificador;
import com.herikles.dao.arquivoTxtDao.ManipuladorTXT;

/**
 *
 * @author Hérikles Vinícyus <heriklesvinicyus@hotmail.com>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ManipuladorTXT mt = new ManipuladorTXT();
        Classificador c = new Classificador();
        
        String base = mt.read("src/com/herikles/arquivo/Basedados.txt");
        String test = mt.read("src/com/herikles/arquivo/Test.txt");
        
        
        mt.create("lala");
        
        System.out.println(mt.read());
        System.out.println(c.classificar(mt.read().split("\n"), "0,4", 3));
    }

}
