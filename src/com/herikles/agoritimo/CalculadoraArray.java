/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herikles.agoritimo;

/**
 *
 * @author Hérikles Vinícyus <heriklesvinicyus@gmail.com>
 */
public class CalculadoraArray {

    public static double[] soma(double[] m1, double[] m2) {
        double[] aux = new double[m1.length];
        for (int i = 0; i < aux.length; i++) {
            aux[i] = m1[i] + m2[i];
        }
        return aux;
    }

    public static double[] soma(double[] m1, double[] m2, double[] m3) {
        double[] aux = new double[m1.length];
        for (int i = 0; i < aux.length; i++) {
            aux[i] = m1[i] + m2[i] + m3[i];
        }
        return aux;
    }

    public static double[] subtracao(double[] m1, double[] m2) {
        double[] aux = new double[m1.length];
        for (int i = 0; i < aux.length; i++) {
            aux[i] = m1[i] - m2[i];
        }
        return aux;
    }

    public static double[] multiplicacaoPorEscalar(double m1, double[] m2) {
        double[] aux = new double[m2.length];
        for (int i = 0; i < aux.length; i++) {
            aux[i] = m1 * m2[i];
        }
        return aux;
    }
}
