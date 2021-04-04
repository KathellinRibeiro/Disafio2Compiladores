/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio.pkg2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author kathe
 */
public class Desafio2 {

    static Scanner scan;
    static PrintWriter pw;
    static FileWriter arq;

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        try {
            arq = new FileWriter("./src/prog-check.txt");
            pw = new PrintWriter(arq);
            scan = new Scanner(new File("./src/prog.txt"), "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            
            // Chamando a fun��o passando a express�o como par�metro
            if (estaValido(line)) {
                pw.write("\n" + line + " - OK" + "\n");
                System.out.println(line + " - OK ");
                pw.flush();
                
            } else {
                pw.append(line + " - Inv�lido");
                System.out.println("\n" + line + " - Inv�lido " + "\n");
                pw.flush();
            }
        }
    }

    // Fun��o para checar se est� valido a express�o
    public static boolean estaValido(String expr) {

        Deque<Character> pilha = new ArrayDeque<Character>();

        // Lendo a express�o
        for (int i = 0; i < expr.length(); i++) {

            char x = expr.charAt(i);
            if (x == '(' || x == '[' || x == '{') {

                pilha.push(x);
                continue;
            }

            // SE o caractere atual atual n�o estiver abrindo
            // colchete, ent�o ele deve estar fechando. Ent�o empilhe
            // n�o pode estar vazio neste ponto.
            if (pilha.isEmpty()) {
                return false;
            }

            char check;
            switch (x) {
                case ')':
                    check = pilha.pop();
                    if (check == '{' || check == '[') {
                        return false;
                    }
                    break;

                case '}':
                    check = pilha.pop();
                    if (check == '(' || check == '[') {
                        return false;
                    }
                    break;

                case ']':
                    check = pilha.pop();
                    if (check == '(' || check == '{') {
                        return false;
                    }
                    break;
            }
        }
        // verificar se a pilha est� vazia
        return (pilha.isEmpty());
    }
}
