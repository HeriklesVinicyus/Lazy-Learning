/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herikles.main;

import com.herikles.agoritimo.Classificador;
import com.herikles.agoritimo.PSO;
import com.herikles.dao.arquivoTxtDao.ManipuladorTXT;
import java.util.Arrays;

/**
 *
 * @author Hérikles Vinícyus <heriklesvinicyus@hotmail.com>
 */
public class Main {

    public static void main(String[] args) {
        ManipuladorTXT mt = new ManipuladorTXT();
        Classificador c = new Classificador();
        PSO p = new PSO();
        mt.setSrc("Saida.txt");

        String baseCompleta[] = (mt.read("iris.txt")).split("\n");
        String separado[][] = separaBase(baseCompleta, 70, 30, 50);
        String baseSeparada[] = separado[0];
        String validacaoSeparada[] = separado[1];
        String testSeparado[] = separado[2];

        /*String resultados = "";

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
            System.out.println((i + 1) + " - " + taxaAprendidado);

        }

        mt.create(resultados);

        */
        System.out.println(p.psoTodos(baseSeparada, testSeparado, 7));
        
    }
    
    public static String[][] separaBase(String[] base, int tamBase, int tamValidacao, int tamTeste){
        String[][] resp = {
            new String[tamBase],
            new String[tamValidacao],
            new String[tamTeste]
        };
        
        int cont = 0;
        for (String[] resp1 : resp) {
            for (int j = 0; j < resp1.length; j++) {
                resp1[j] = base[cont++]; 
            }
        }
        
        return resp;
    }

}
