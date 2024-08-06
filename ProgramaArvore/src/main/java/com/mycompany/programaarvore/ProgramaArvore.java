/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.programaarvore;

/**
 *
 * @author diogo
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

class Nodo {
    int valor;
    Nodo esquerdo;
    Nodo direito;

    Nodo(int valor) {
        this.valor = valor;
        this.esquerdo = null;
        this.direito = null;
    }
}

class ArvoreBinariaBusca {
    Nodo raiz;

    public ArvoreBinariaBusca() {
        this.raiz = null;
    }

    public void inserir(int valor) {
        raiz = inserirRecursivamente(raiz, valor);
    }

    private Nodo inserirRecursivamente(Nodo raiz, int valor) {
        if (raiz == null) {
            return new Nodo(valor);
        }
        if (valor < raiz.valor) {
            raiz.esquerdo = inserirRecursivamente(raiz.esquerdo, valor);
        } else {
            raiz.direito = inserirRecursivamente(raiz.direito, valor);
        }
        return raiz;
    }

    public void imprimirPreOrdem() {
        imprimirPreOrdemRecursivamente(raiz);
        System.out.println();
    }

    private void imprimirPreOrdemRecursivamente(Nodo raiz) {
        if (raiz != null) {
            System.out.print(raiz.valor + " ");
            imprimirPreOrdemRecursivamente(raiz.esquerdo);
            imprimirPreOrdemRecursivamente(raiz.direito);
        }
    }

    public void imprimirInOrdem() {
        imprimirInOrdemRecursivamente(raiz);
        System.out.println();
    }

    private void imprimirInOrdemRecursivamente(Nodo raiz) {
        if (raiz != null) {
            imprimirInOrdemRecursivamente(raiz.esquerdo);
            System.out.print(raiz.valor + " ");
            imprimirInOrdemRecursivamente(raiz.direito);
        }
    }

    public void imprimirPosOrdem() {
        imprimirPosOrdemRecursivamente(raiz);
        System.out.println();
    }

    private void imprimirPosOrdemRecursivamente(Nodo raiz) {
        if (raiz != null) {
            imprimirPosOrdemRecursivamente(raiz.esquerdo);
            imprimirPosOrdemRecursivamente(raiz.direito);
            System.out.print(raiz.valor + " ");
        }
    }

    public void imprimirEmNivel() {
        if (raiz == null) return;

        Queue<Nodo> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            Nodo nodoAtual = fila.poll();
            System.out.print(nodoAtual.valor + " ");

            if (nodoAtual.esquerdo != null) fila.add(nodoAtual.esquerdo);
            if (nodoAtual.direito != null) fila.add(nodoAtual.direito);
        }
        System.out.println();
    }

    public boolean remover(int valor) {
        if (raiz == null) return false;
        raiz = removerRecursivamente(raiz, valor);
        return true;
    }

    private Nodo removerRecursivamente(Nodo raiz, int valor) {
        if (raiz == null) return null;

        if (valor < raiz.valor) {
            raiz.esquerdo = removerRecursivamente(raiz.esquerdo, valor);
        } else if (valor > raiz.valor) {
            raiz.direito = removerRecursivamente(raiz.direito, valor);
        } else {
            if (raiz.esquerdo == null) return raiz.direito;
            if (raiz.direito == null) return raiz.esquerdo;

            Nodo menorValor = encontrarMenorValor(raiz.direito);
            raiz.valor = menorValor.valor;
            raiz.direito = removerRecursivamente(raiz.direito, menorValor.valor);
        }
        return raiz;
    }

    private Nodo encontrarMenorValor(Nodo raiz) {
        Nodo atual = raiz;
        while (atual.esquerdo != null) {
            atual = atual.esquerdo;
        }
        return atual;
    }
}

public class ProgramaArvore {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> numerosAleatorios = new ArrayList<>();
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

 
        for (int i = 0; i < 20; i++) {
            int numero = random.nextInt(101);
            numerosAleatorios.add(numero);
            arvore.inserir(numero);
        }

        System.out.println("Numeros inseridos na arvore:");
        System.out.println(numerosAleatorios);


        System.out.println("Impressao em pre-ordem:");
        arvore.imprimirPreOrdem();

        System.out.println("Impressao em in-ordem:");
        arvore.imprimirInOrdem();

        System.out.println("Impressao em pos-ordem:");
        arvore.imprimirPosOrdem();

        System.out.println("Impressao em nivel:");
        arvore.imprimirEmNivel();

      
        for (int i = 0; i < 5; i++) {
            int numeroRemover = numerosAleatorios.remove(random.nextInt(numerosAleatorios.size()));
            arvore.remover(numeroRemover);
            System.out.println("Numero removido: " + numeroRemover);
        }

     
        System.out.println("Impressao apos remocoes:");

        System.out.println("Impressao em pre-ordem:");
        arvore.imprimirPreOrdem();

        System.out.println("Impressao em in-ordem:");
        arvore.imprimirInOrdem();

        System.out.println("Impressao em pos-ordem:");
        arvore.imprimirPosOrdem();

        System.out.println("Impressao em nivel:");
        arvore.imprimirEmNivel();
    }
}

