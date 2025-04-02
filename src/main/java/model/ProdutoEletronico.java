/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/* Classe ProdutoEletronico que herda de Produto e adiciona marca e modelo como atributos. */
public class ProdutoEletronico extends Produto{
    private String marca;
    private String modelo;
    
    /* Construtor que inicializa os atributos da classe base e os específicos da classe. */
    public ProdutoEletronico(String nome, int quantidade, double preco, String marca, String modelo){
        super(nome, quantidade, preco);
        this.marca = marca;
        this.modelo = modelo;
    }
    
    /* Sobrescreve o método toString para incluir marca e modelo. */
    @Override
    public String toString(){ 
        return super.toString() + " | Marca: " + marca + " | Modelo: " + modelo;
    }   
     
    /* getters e setters */
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public String getMarca(){
        return marca;
    }
    
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    
    public String getModelo(){
        return modelo;
    }
}