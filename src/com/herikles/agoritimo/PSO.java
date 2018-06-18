/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herikles.agoritimo;

import java.util.Arrays;

/**
 *
 * @author Hérikles Vinícyus <heriklesvinicyus@gmail.com>
 */
public class PSO {

    Classificador c = new Classificador();

    public String psoTodos(String[] base, String[] instancias, int fronteira) {
        String resp = "";
        int tamanho = (base[0].split(",")).length - 1;
        int quantInteracao = (int) Math.pow((base[0].split(",")).length - 1, 2);
        int[][] x = new int[quantInteracao - 1][tamanho];

        //gerar Binarios
        for (int i = 1; i < quantInteracao; i++) {
            String[] aux = Integer.toBinaryString(i).split("");
            for (int j = 0; j < aux.length; j++) {
                x[i - 1][tamanho - j - 1] = Integer.parseInt(aux[aux.length - j - 1]);
            }
        }

        for (int i = 0; i < x.length; i++) {
            String[] baseAux = gerarBaseParticulaX(base, x[i]);
            String[] baseTestAux = gerarBaseParticulaX(instancias, x[i]);
            String[] resuldadosEsperado = c.retirarSaida(baseTestAux);
            String[] saidas = new String[baseTestAux.length];

            for (int j = 0; j < baseTestAux.length; j++) {
                String aux = c.classificar(baseAux, baseTestAux[j], fronteira);
                saidas[j] = aux;
            }
            double taxaAprendidado = c.taxaAprendizado(resuldadosEsperado, saidas);
            resp += Arrays.toString(x[i]) + " - " + taxaAprendidado + "\n";
        }
        return resp;
    }

    public int[] gerarGbest(String[] base, String[] validacao, int k, int quantParticulas) {
        int tamanhoX = (base[0].split(",")).length - 1;
        double[] gBest;
        double[][] pBest = new double[quantParticulas][tamanhoX];
        double[][] auxPBest = new double[quantParticulas][tamanhoX];
        double[] taxaAprendizadoPbest = new double[quantParticulas];
        double[] taxaAprendizadoAuxPbest = new double[quantParticulas];

        for (int i = 0; i < auxPBest.length; i++) {
            auxPBest[i] = gerarParticulasInicial(tamanhoX);
        }

        for (int i = 0; i < auxPBest.length; i++) {
            int[] x = converterParaBinario(auxPBest[i]);
            String[] baseAux = gerarBaseParticulaX(base, x);
            String[] baseValidacaoAux = gerarBaseParticulaX(validacao, x);
            String[] resuldadosEsperado = c.retirarSaida(baseValidacaoAux);
            String[] saidas = new String[baseValidacaoAux.length];

            for (int j = 0; j < baseValidacaoAux.length; j++) {
                String aux = c.classificar(baseAux, baseValidacaoAux[j], k);
                saidas[j] = aux;
            }
            taxaAprendizadoAuxPbest[i] = c.taxaAprendizado(resuldadosEsperado, saidas);
        }

        for (int i = 0; i < taxaAprendizadoAuxPbest.length; i++) {
            if (taxaAprendizadoPbest[i] < taxaAprendizadoAuxPbest[i]) {
                pBest[i] = auxPBest[i];
                taxaAprendizadoPbest[i] = taxaAprendizadoAuxPbest[i];
            }
        }
        
        
        
        int auxGbest = 0;
        for (int i = 1; i < taxaAprendizadoPbest.length; i++) {
            if (taxaAprendizadoPbest[i] < taxaAprendizadoPbest[auxGbest]) {
                auxGbest = i;
            }
        }

        gBest = pBest[auxGbest];

        return converterParaBinario(gBest);
    }
    
    

    public String[] gerarBaseParticulaX(String[] base, int[] x) {
        String[] resp = new String[base.length];
        for (int i = 0; i < base.length; i++) {
            resp[i] = gerarFitnessParticula(base[i], x);
        }
        return resp;
    }

    public String gerarFitnessParticula(String instacia, int[] x) {
        String[] auxIntacia = instacia.split(",");
        String resp = "";
        for (int i = 0; i < auxIntacia.length - 1; i++) {
            if (x[i] == 1) {
                resp += auxIntacia[i] + ",";
            }
        }
        resp += auxIntacia[auxIntacia.length - 1];
        return resp;
    }

    private double[] gerarParticulasInicial(int tamanho) {
        double[] resp = new double[tamanho];
        for (int i = 0; i < resp.length; i++) {
            resp[i] = Math.random();
        }
        return resp;
    }

    public int[] converterParaBinario(double[] instacia) {
        int[] resp = new int[instacia.length];
        for (int i = 0; i < resp.length; i++) {
            resp[i] = (int) Math.round(Math.random());
        }
        return resp;

    }
}