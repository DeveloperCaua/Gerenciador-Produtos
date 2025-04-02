/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/* Classe ProdutoDAO que gerencia uma lista de objetos Produto. */
package model;

import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.Iterator;

public class ProdutoDAO {
    private ArrayList<Produto> produtos;

    /* Construtor que inicializa a lista de produtos. */
    public ProdutoDAO() {
        produtos = new ArrayList<Produto>();
    }
    
    /* getter */
    public ArrayList<Produto> getListaProdutos(){
        return produtos;
    }
    
    /* setter */
    public void setListaProdutos(ArrayList<Produto> produtos){
        this.produtos = produtos;
    }

    /* Adiciona um novo produto à lista. */
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }
    
    /* Ordena a lista de produtos usando o nome (ordem alfabetica), como definido em 'Produto'(local onde foi feita a implementação do Comparable). */
    public void listarProdutosOrdenados() {
        sort(produtos);
    } 

    /* Remove um produto da lista baseado no nome. */
    public void removerProduto(String nome) {
        Iterator<Produto> it = produtos.iterator();
        while (it.hasNext()) {
            Produto p = it.next();
            if (p.getNome().equals(nome)) { /* Quando o nome coincide, o produto com esse nome é removido. */
                it.remove();
            }
        }
    }
}