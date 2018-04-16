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

        String base = mt.read("Basedados.txt");
        String test = mt.read("Test.txt");
        String baseSeparada[] = mt.read("Basedados.txt").split("\n");
        String TestSeparado[] = mt.read("Test.txt").split("\n");
        mt.setSrc("Saida.txt");
        String resultados = "";

        for (int i = 1; i <= baseSeparada.length; i++) {
            resultados += "K=" + i + "\n";
            for (String TestSeparado1 : TestSeparado) {
                resultados += TestSeparado1 + "," + c.classificar(baseSeparada, TestSeparado1, i) + "\n";
            }
        }

        mt.create(resultados);
        System.out.println("\nBase\n" + base);
        System.out.println("\nTeste\n" + test);
        System.out.println("\nSaida\n" + resultados);
    }

}
