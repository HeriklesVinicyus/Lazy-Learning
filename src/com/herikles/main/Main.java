/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herikles.main;

import com.herikles.agoritimo.Classificador;
import com.herikles.dao.arquivoTxtDao.ManiculadorTXT;

/**
 *
 * @author Hérikles Vinícyus <heriklesvinicyus@hotmail.com>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ManiculadorTXT mt = new ManiculadorTXT();
        Classificador c = new Classificador();
        
        mt.create("lala");
        
        System.out.println(mt.read());
        System.out.println(c.classificar(mt.read().split("\n"), "0,4", 3));
    }

}
