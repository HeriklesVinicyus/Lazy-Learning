/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herikles.agoritimo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Hérikles Vinicyus <heriklesvinicyus@hotmail.com>
 */
public class Classificador {

    public String classificar(String[] base, String instancia, int fronteira) {
        double distaciasAux[] = todasDistanciasEntreIstanciaTest(base, instancia);
        int menoresInstancias[] = retornarIndiceMenoresDistancias(distaciasAux, fronteira);
        String saidasMenoresBase[] = montarArraySaidaBase(base, menoresInstancias);
        return saidaInstacia(saidasMenoresBase);
    }

    private String saidaInstacia(String[] saidasMenoresBase) {
        int cont[];
        ArrayList<String> tiposSaidas = new ArrayList<>();

        if (saidasMenoresBase.length > 0) {
            tiposSaidas.add(saidasMenoresBase[0]);
        }
        for (int i = 1; i < saidasMenoresBase.length; i++) {
            for (int j = 0; j < tiposSaidas.size(); j++) {
                if (saidasMenoresBase[i].equals(tiposSaidas.get(j))) {
                    break;
                }
                tiposSaidas.add(saidasMenoresBase[i]);
            }
        }

        cont = new int[tiposSaidas.size()];
        for (int i = 0; i < cont.length; i++) {
            for (String saidasMenoresBase1 : saidasMenoresBase) {
                if (saidasMenoresBase1 == null ? tiposSaidas.get(i) == null : saidasMenoresBase1.equals(tiposSaidas.get(i))) {
                    cont[i]++;
                }
            }
        }

        int id = 0;
        for (int i = 0; i < cont.length; i++) {
            if (cont[i] > cont[id]) {
                id = i;
            }
        }

        return tiposSaidas.get(id);
    }

    private String[] montarArraySaidaBase(String base[], int ind[]) {
        String res[] = new String[ind.length];

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < base.length; j++) {
                if (j == ind[i]) {
                    String aux[] = base[j].split(",");
                    res[i] = aux[aux.length - 1];
                }
            }
        }
        return res;
    }

    private int[] retornarIndiceMenoresDistancias(double[] distacias, int numeroInstancias) {
        int res[] = new int[numeroInstancias];
        double auxDistacias[] = new double[distacias.length];

        System.arraycopy(distacias, 0, auxDistacias, 0, auxDistacias.length);
        Arrays.sort(auxDistacias);

        for (int i = 0; i < numeroInstancias; i++) {
            for (int j = 0; j < distacias.length; j++) {
                if (auxDistacias[i] == distacias[j] && (i - 1 < 0 || j != res[i - 1])) {
                    res[i] = j;
                    break;
                }
            }
        }
        return res;
    }

    private double[] todasDistanciasEntreIstanciaTest(String[] base, String inst) {
        double distaciasAux[] = new double[base.length];
        String instaciaSepadata[] = inst.split(",");

        for (int i = 0; i < base.length; i++) {
            String baseSeparada[] = base[i].split(",");
            double distanciaInstaciaBase = 0;
            for (int j = 0; j < (baseSeparada.length - 1); j++) {
                distanciaInstaciaBase += Math.pow((Double.parseDouble(baseSeparada[j]) - Double.parseDouble(instaciaSepadata[j])), 2);
            }
            distaciasAux[i] = Math.sqrt(distanciaInstaciaBase);
        }
        return distaciasAux;
    }

}
