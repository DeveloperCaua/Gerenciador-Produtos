/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/* Classe abstrata Produto que define atributos e métodos comuns para produtos. Implementa Comparable para permitir a ordenação por nome. */
package model;

public abstract class Produto implements Comparable<Produto>{
    private String nome;
    private int quantidade;
    private double preco;
    
    /* Construtor que inicializa os atributos do produto. */
    public Produto(String nome, int quantidade, double preco){
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    
    /* Método toString que retorna uma representação do produto com nome, preço e quantidade. */
    @Override
    public String toString(){
        return "Nome: " + nome + " | Preço: " +  preco + " | Quantidade: " + quantidade;
    }
    
    /* Método compareTo para ordenar produtos por nome. */
    @Override
    public int compareTo(Produto outroProduto) {
        return this.nome.compareTo(outroProduto.nome);
    }
    
     /* getters e setters. */
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setPreco(double preco){
        this.preco = preco;
    }
    
    public double getPreco(){
        return preco;
    }
    
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }
    
    public int getQuantidade(){
        return quantidade;
    }
}
