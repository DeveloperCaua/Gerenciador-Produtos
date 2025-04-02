/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/* Classe ProdutoAlimento que herda de Produto e adiciona a data de validade como atributo. */
package model;

public class ProdutoAlimento extends Produto{
    private String dataValidade;
    
    /* Construtor que inicializa os atributos da classe base e a data de validade. */
    public ProdutoAlimento(String nome, int quantidade, double preco, String dataValidade){
        super(nome, quantidade, preco);
        this.dataValidade = dataValidade;
    }
    
    /* Sobrescreve o método toString para incluir a data de validade. */
    @Override
    public String toString(){
        return super.toString() + " | Data de validade: " + dataValidade;
    }
    
    /* getter e setter */
    public void setDataValidade(String dataValidade){
        this.dataValidade = dataValidade;
    }
    
    public String getDataValidade(){
        return dataValidade;
    }
} 
