package br.com.cafi.associacaoapriori.modelo.dao;

import br.com.cafi.associacaoapriori.modelo.entidade.Produto;
import javax.persistence.EntityManager;

public class ProdutoDAO extends DAO<Long, Produto> {
    public ProdutoDAO(EntityManager entityManager) {
        super(entityManager);
    }
    
}
