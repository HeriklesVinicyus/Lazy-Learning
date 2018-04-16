/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herikles.main;

import com.herikles.agoritimo.Classificador;
import com.herikles.dao.arquivoTxtDao.ManipuladorTXT;
import java.util.Arrays;

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

        //String base = mt.read("Basedados.txt");
        String base = mt.read("iris.txt");
        String saidaEsperada[] = mt.read("SaidaIrisEsperada.txt").split("\n");
        //String test = mt.read("Test.txt");
        String test = mt.read("testIris.txt");
        String baseSeparada[] = base.split("\n");
        String TestSeparado[] = test.split("\n");
        mt.setSrc("Saida.txt");
        String resultados = "";

        /*for (int i = 1; i <= baseSeparada.length; i++) {
            resultados += "K=" + i + "\n";
            for (String TestSeparado1 : TestSeparado) {
                resultados += TestSeparado1 + "," + c.classificar(baseSeparada, TestSeparado1, i) + "\n";
            }
        }*/
        
        String[] saidas = new String[TestSeparado.length];
        double[] diferanca = new double[TestSeparado.length];
        int cont = 0;
        for (String TestSeparado1 : TestSeparado) {
            resultados += TestSeparado1 + "," + c.classificar(baseSeparada, TestSeparado1, 5) + "\n";
            saidas[cont++] = c.classificar(baseSeparada, TestSeparado1, 5);
        }
        
        for (int i = 0; i < diferanca.length; i++) {
            System.out.println(saidaEsperada[i]);
            diferanca[i]+=Double.parseDouble(saidaEsperada[i])+ Double.parseDouble(saidas[i]);
        }
        
        
        mt.create(resultados);
        System.out.println("\nBase\n" + base);
        System.out.println("\nTeste\n" + test);
        System.out.println("\nSaida\n" + resultados);
        System.out.println("\ndiferen\n");
        System.out.println(Arrays.toString(diferanca));
        
    }

}
