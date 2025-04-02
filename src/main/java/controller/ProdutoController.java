/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/* Controlador para gerenciar as operações sobre produtos, como adicionar, editar, remover e listar. */
package controller;

import model.ProdutoAlimento;
import model.ProdutoEletronico;
import model.Produto;
import view.ProdutoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.ProdutoDAO;

public class ProdutoController{
    private ProdutoDAO produtos; /* Lista de produtos gerenciada pelo DAO */
    private ProdutoView telaProdutos; /* Interface gráfica para interação com os produtos */
    
    public ProdutoController(){
        telaProdutos = new ProdutoView();
        produtos = new ProdutoDAO();
        
        /* A seguir, vem as configurações dos botões com seus respectivos listeners, assim com ensinado em sala */
        JButton adicionar = telaProdutos.getAdicionarProdutoButton();
        adicionar.addActionListener(new ActionListener(){
     
        @Override
        public void actionPerformed(ActionEvent e){
            adicionarProduto();
        }
        });
        
        JButton ordenar = telaProdutos.getListarOrdenadoProdutosButton();
        ordenar.addActionListener(new ActionListener(){
            
        @Override
        public void actionPerformed(ActionEvent e){
            ordenarProdutos();
        }
        });
        
        JButton naoOrdenar = telaProdutos.getListarNaoOrdenadoProdutosButton();
        naoOrdenar.addActionListener(new ActionListener(){
            
        @Override
        public void actionPerformed(ActionEvent e){
            naoOrdenarProdutos();
        }
        });
        
        JButton remover = telaProdutos.getRemoverProdutoButton();
        remover.addActionListener(new ActionListener(){
            
        @Override
        public void actionPerformed(ActionEvent e){
            removerProduto();
        }
        });
        
        JButton editar = telaProdutos.getEditarProdutoButton();
        editar.addActionListener(new ActionListener(){
            
        @Override
        public void actionPerformed(ActionEvent e){
            editarProduto();
        }
        });
    }
    
    /* Método que faz a pesquisa de um produto pelo nome presente na lista. */
    private Produto pesquisarProduto(String nome){
        
        Iterator<Produto> it = produtos.getListaProdutos().iterator();
        Produto p = null;
                
        while (it.hasNext()) {
            p = it.next();
            if (p.getNome().equals(nome)){
                return p;
            }
        }
        
        return null;
    }
    
    /* Atualiza os campos da tela com as informações dos produtos. Basicamente, esse método faz um "clean" em todos os campos e atualiza o TextArea com a lista, para refletir atualizações na mesma */
    public void atualizarTela(ProdutoDAO produtos){
        telaProdutos.setNomeProdutoTextField();
        telaProdutos.setQuantidadeProdutoTextField();
        telaProdutos.setPrecoProdutoTextField();
        telaProdutos.setValidadeAlimentoTextField();
        telaProdutos.setMarcaEletronicoTextField();
        telaProdutos.setModeloEletronicoTextField();
        telaProdutos.setProdutosJTextArea(produtos);
    }
    
    /* Essa parte é referente a criação de novos objetos de acordo com as entradas da interface e sua adição na lista de 'Produtos' */
    private void adicionarProduto(){
        if(telaProdutos.getNomeProdutoTextField().getText().isEmpty() || telaProdutos.getPrecoProdutoTextField().getText().isEmpty() || telaProdutos.getQuantidadeProdutoTextField().getText().isEmpty()){
            JOptionPane.showMessageDialog(telaProdutos, "Por favor, preencha todos os campos obrigatórios antes de continuar!");
            return;
        }
        
        Produto auxiliar = pesquisarProduto(telaProdutos.getNomeProdutoTextField().getText());
        if(auxiliar != null){
           JOptionPane.showMessageDialog(telaProdutos, "Esse produto já foi adicionado!");
           atualizarTela(produtos);
           return;
        }
        
        String opcao = telaProdutos.getOpcaoComboBox().getSelectedItem().toString();
        Produto produto = null;
        try{
            if(opcao.equals("Alimento")){
                if(telaProdutos.getValidadeAlimentoTextField().getText().isEmpty()){
                    JOptionPane.showMessageDialog(telaProdutos, "Por favor, preencha todos os campos obrigatórios antes de continuar!");
                    return;
                }
                produto = new ProdutoAlimento(telaProdutos.getNomeProdutoTextField().getText(), Integer.parseInt(telaProdutos.getQuantidadeProdutoTextField().getText()), Double.parseDouble(telaProdutos.getPrecoProdutoTextField().getText()), telaProdutos.getValidadeAlimentoTextField().getText());
            }
            if(opcao.equals("Eletrônico")){
                boolean situacao = false;
                if(telaProdutos.getMarcaEletronicoTextField().getText().isEmpty()){
                    JOptionPane.showMessageDialog(telaProdutos, "Por favor, preencha todos os campos obrigatórios antes de continuar!");
                    situacao = true;
                }
                if(telaProdutos.getModeloEletronicoTextField().getText().isEmpty()){
                    JOptionPane.showMessageDialog(telaProdutos, "Por favor, preencha todos os campos obrigatórios antes de continuar!");
                    situacao = true;
                }
                
                if(!situacao){
                    produto = new ProdutoEletronico(telaProdutos.getNomeProdutoTextField().getText(), Integer.parseInt(telaProdutos.getQuantidadeProdutoTextField().getText()), Double.parseDouble(telaProdutos.getPrecoProdutoTextField().getText()), telaProdutos.getMarcaEletronicoTextField().getText(), telaProdutos.getModeloEletronicoTextField().getText()); 
                }
            }
        }
        
        catch(NumberFormatException erroDeFormatoDeNumero){
            JOptionPane.showMessageDialog(telaProdutos, "Informe valores válidos!");
        }
        
        if (produto != null) {
            produtos.adicionarProduto(produto);
            atualizarTela(produtos);
        }
    }
     
    /* Utilizando dos métodos criados em 'ProdutoDAO', é feita a ordenação dos produtos pelo nome e atualiza a tela. Essa ordenação não é feita na lista original, é feita uma cópia da lista, somente. */
    private void ordenarProdutos() {
        if(produtos.getListaProdutos().isEmpty()){
            JOptionPane.showMessageDialog(telaProdutos, "Não há produtos cadastrados no sistema! Por favor, adicione um produto antes de continuar.");
        } else{
            ProdutoDAO auxiliar = new ProdutoDAO();
            ArrayList<Produto> copiaLista = new ArrayList<>(produtos.getListaProdutos()); 
            auxiliar.setListaProdutos(copiaLista);
            auxiliar.listarProdutosOrdenados();
            atualizarTela(auxiliar);
        }
    }
    
    /* Retorna para a visualização da lista original em ordem de inserção de 'Produtos' */
    private void naoOrdenarProdutos(){
        if(produtos.getListaProdutos().isEmpty()){
            JOptionPane.showMessageDialog(telaProdutos, "Não há produtos cadastrados no sistema! Por favor, adicione um produto antes de continuar.");
        } else{
            atualizarTela(produtos);
        }
    }
    
    /* Utilizando o método de pesquisa por nome, é feito a remoção, caso presente, do produto com o respectivo nome procurado. Como em todos os métodos anteriores, a maioria dos possíveis erros estão sendo tratados */
    private void removerProduto() {
        if (telaProdutos.getNomeProdutoTextField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(telaProdutos, "Por favor, informe o nome do produto!");
            return;
        }

        Produto auxiliar = pesquisarProduto(telaProdutos.getNomeProdutoTextField().getText());
        
        if (auxiliar == null){
            JOptionPane.showMessageDialog(telaProdutos, "Produto não encontrado!");
        } else{
            produtos.removerProduto(auxiliar.getNome());
        }
        
        atualizarTela(produtos);
    }
    
    /* Também utilizando o método de pesquisa, caso o 'Produto' seja encontrado, é feita a alteração ou a manutenção do valor inicial de seus campos, de acordo com os valores que o usuário informar nas OptionPane. */
    private void editarProduto() {
        if (!(telaProdutos.getNomeProdutoTextField().getText().isEmpty())) {
            
            Produto produtoEscolhido = pesquisarProduto(telaProdutos.getNomeProdutoTextField().getText());
            if(produtoEscolhido != null){
                String auxiliar = null;
                    
                    auxiliar = JOptionPane.showInputDialog(telaProdutos, "Novo nome:", produtoEscolhido.getNome()); 
                    while(auxiliar.isEmpty()){
                        JOptionPane.showMessageDialog(telaProdutos, "Informe um nome válido!");
                        auxiliar = JOptionPane.showInputDialog(telaProdutos, "Novo nome:", produtoEscolhido.getNome()); 
                    }
                    produtoEscolhido.setNome(auxiliar);
                    
                    boolean valido = false;
                    while(!valido){
                        try{
                            auxiliar = JOptionPane.showInputDialog(telaProdutos, "Novo preço:", produtoEscolhido.getPreco());
                            produtoEscolhido.setPreco(Double.parseDouble(auxiliar));
                            valido = true;
                        }

                        catch (NumberFormatException erroDeFormatoDeNumero){
                        JOptionPane.showMessageDialog(telaProdutos, "Informe valores válidos!");
                        }
                    }
                    
                    valido = false;
                    while(!valido){
                        try{
                            auxiliar = JOptionPane.showInputDialog(telaProdutos, "Nova quantidade:", produtoEscolhido.getQuantidade());
                            produtoEscolhido.setQuantidade(Integer.parseInt(auxiliar));
                            valido = true;
                        }

                        catch (NumberFormatException erroDeFormatoDeNumero){
                        JOptionPane.showMessageDialog(telaProdutos, "Informe valores válidos!");
                        }
                    }

                    if(produtoEscolhido instanceof ProdutoAlimento produtoAlimento){
                        auxiliar = JOptionPane.showInputDialog(telaProdutos, "Nova data de validade:", produtoAlimento.getDataValidade());
                        while(auxiliar.isEmpty()){
                            JOptionPane.showMessageDialog(telaProdutos, "Informe uma data de validade válida!");
                            auxiliar = JOptionPane.showInputDialog(telaProdutos, "Nova data de validade:", produtoAlimento.getDataValidade());
                        }
                        produtoAlimento.setDataValidade(auxiliar);
                    }

                    if(produtoEscolhido instanceof ProdutoEletronico produtoEletronico){
                        auxiliar = JOptionPane.showInputDialog(telaProdutos, "Nova marca:", produtoEletronico.getMarca());
                        while(auxiliar.isEmpty()){
                            JOptionPane.showMessageDialog(telaProdutos, "Informe uma marca válida!");
                            auxiliar = JOptionPane.showInputDialog(telaProdutos, "Nova marca:", produtoEletronico.getMarca());
                        }
                        produtoEletronico.setMarca(auxiliar);
                        auxiliar = JOptionPane.showInputDialog(telaProdutos, "Novo modelo:", produtoEletronico.getModelo());
                        while(auxiliar.isEmpty()){
                            JOptionPane.showMessageDialog(telaProdutos, "Informe um modelo válido!");
                            auxiliar = JOptionPane.showInputDialog(telaProdutos, "Novo modelo:", produtoEletronico.getModelo());
                        }
                        produtoEletronico.setModelo(auxiliar);
                    }       
            } else{
                JOptionPane.showMessageDialog(telaProdutos, "Produto não encontrado!");
            }

            atualizarTela(produtos);
            
        } else {
            JOptionPane.showMessageDialog(telaProdutos, "Por favor, informe o nome do produto!");
        }
    }  
}