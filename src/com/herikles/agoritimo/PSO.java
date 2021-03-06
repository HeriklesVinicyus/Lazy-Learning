/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herikles.agoritimo;

import java.util.Arrays;
import Jama.Matrix;

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
        double[] gBest = new double[tamanhoX];
        double[][] pBest = new double[quantParticulas][tamanhoX];
        double[][] auxPBest = new double[quantParticulas][tamanhoX];
        double[][] velocidades = new double[quantParticulas][tamanhoX];
        double[] taxaAprendizadoPbest = new double[quantParticulas];
        double[] taxaAprendizadoAuxPbest = new double[quantParticulas];

        for (int i = 0; i < auxPBest.length; i++) {
            auxPBest[i] = gerarParticulasInicial(tamanhoX);
        }
        for (int intaracao = 0; intaracao < 100; intaracao++) {
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
            ajustePBests(velocidades, pBest, gBest);
        }
        System.out.println(Arrays.toString(gBest));
        return converterParaBinario(gBest);
    }

    public void ajustePBests(double[][] velocidadeAtual, double[][] pbest, double[] gbest) {
        for (int i = 0; i < velocidadeAtual.length; i++) {
            velocidadeAtual[i] = velocidade(velocidadeAtual[i], pbest[i], gbest);
            pbest[i] = CalculadoraArray.soma(pbest[i], velocidadeAtual[i]);
        }
    }

    public double[] velocidade(double[] velocidadeAtual, double[] pbest, double[] gbest) {
        double w = 0.8;
        double c1 = 2;
        double r1 = Math.random();
        double c2 = 2;
        double r2 = Math.random();
        double[] aux = CalculadoraArray.soma(CalculadoraArray.multiplicacaoPorEscalar(w, velocidadeAtual), CalculadoraArray.multiplicacaoPorEscalar((c1 * r1), pbest), CalculadoraArray.multiplicacaoPorEscalar((c2 * r2), gbest));

        for (int i = 0; i < aux.length; i++) {
            if (aux[i] < -0.3) {
                aux[i] = -0.3;
            }
            if (aux[i] > 0.3) {
                aux[i] = 0.3;
            }
        }

        return aux;
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
