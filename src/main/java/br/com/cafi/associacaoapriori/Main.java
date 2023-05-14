/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cafi.associacaoapriori;

import bo.ItemPedidoBO;
import bo.PedidoBO;
import bo.ProdutoBO;
import br.com.cafi.associacaoapriori.modelo.entidade.ItemPedido;
import br.com.cafi.associacaoapriori.modelo.entidade.Pedido;
import br.com.cafi.associacaoapriori.modelo.entidade.Produto;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import weka.associations.Apriori;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 *
 * @author Jk-Sa
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Apriori apriori = new Apriori();
        
        ProdutoBO bo = new ProdutoBO();

        List<Produto> lista = bo.findAll();
        ArrayList<String> listaString = new ArrayList<>();
        listaString.add("s");
        listaString.add("?");
        ArrayList<Attribute> list = new ArrayList<>();
        for (Produto obj : lista) {
            
            Attribute a = new Attribute(obj.getDescricao(), listaString);

            list.add(a);
        }
        
        Instances data = new Instances("Mercado", list,1);
        
        ItemPedidoBO ipbo = new ItemPedidoBO();
        List<ItemPedido> listIp = ipbo.findAll();

        PedidoBO bop = new PedidoBO();
        List<Pedido> listPedidos = bop.findAll();
        for (Pedido p : listPedidos) {
            Instance in = new DenseInstance(list.size());

            for (Attribute at : list) {
                listIp = ipbo.findWhere(p.getId(), at.name());
                if (listIp.size() > 0) {
                    System.out.println("O atributo é "+at);
                    in.setValue(at, "s");
                } 

            }
            data.add(in);

        }
        
        
        apriori.buildAssociations(data);
        System.out.println(apriori);
    }
}
