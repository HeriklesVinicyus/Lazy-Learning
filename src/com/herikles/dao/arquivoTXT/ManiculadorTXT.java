/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herikles.dao.arquivoTXT;

import com.herikles.dao.DAO;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hérikles Vinicyus <heriklesvinicyus@hotmail.com>
 */
public class ManiculadorTXT implements DAO<String> {

    @Override
    public void create(String s) {
        FileWriter arq;
        try {
            arq = new FileWriter("src/com/herikles/arquivo/resultado.txt");
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.printf(s);
            arq.close();
        } catch (IOException ex) {
            Logger.getLogger(ManiculadorTXT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String read() {
        FileReader arq;
        String aux = "";
        try {
            arq = new FileReader("src/com/herikles/arquivo/Basedados.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            while (linha != null) {
                aux += linha + "\n";
                linha = lerArq.readLine();
            }
            arq.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManiculadorTXT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManiculadorTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }

}
