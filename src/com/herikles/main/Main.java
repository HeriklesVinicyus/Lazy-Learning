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

    public static void main(String[] args) {
        ManipuladorTXT mt = new ManipuladorTXT();
        Classificador c = new Classificador();
        mt.setSrc("Saida.txt");

        String baseSeparada[] = (mt.read("iris.txt")).split("\n");
        String testSeparado[] = (mt.read("testIris.txt")).split("\n");

        String resultados = "";

        String[] saidas = new String[testSeparado.length];
        String[] resuldadosEsperado = c.retirarSaida(testSeparado);
        double taxaAprendidado = 0;
        int epocas = 100;

        for (int i = 0; i < epocas; i++) {
            for (int j = 0; j < testSeparado.length; j++) {
                String aux = c.classificar(baseSeparada, testSeparado[j], i + 1);
                resultados += aux + "\n";
                saidas[j] = aux;
            }
            taxaAprendidado = c.taxaAprendizado(resuldadosEsperado, saidas);
            System.out.println((i+1) + " - " + taxaAprendidado);
        }

        mt.create(resultados);

    }

}
