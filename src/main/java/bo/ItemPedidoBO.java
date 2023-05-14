/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import br.com.cafi.associacaoapriori.modelo.dao.ItemPedidoDAO;
import br.com.cafi.associacaoapriori.modelo.dao.SimpleEntityManager;
import br.com.cafi.associacaoapriori.modelo.entidade.ItemPedido;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Jk-Sa
 */
public class ItemPedidoBO {
    private ItemPedidoDAO dao;
     
    private SimpleEntityManager simpleEntityManager;
     
    public ItemPedidoBO(){
        this.simpleEntityManager = new SimpleEntityManager("HELDERnomeDoPersistence");
        dao = new ItemPedidoDAO(simpleEntityManager.getEntityManager());
    }
     
    public void save(ItemPedido obj){
        try{
            simpleEntityManager.beginTransaction();
            dao.save(obj);
            simpleEntityManager.commit();
        }catch(Exception e){
            e.printStackTrace();
            simpleEntityManager.rollBack();
        }
    }
    
      public void delete(ItemPedido obj){
        try{
            simpleEntityManager.beginTransaction();
            dao.delete(obj);
            simpleEntityManager.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
      
      public void update(ItemPedido pedido) {
        try {
            simpleEntityManager.beginTransaction();
            dao.update(pedido);
            simpleEntityManager.commit();
        } catch (Exception e) {
            e.printStackTrace();
            simpleEntityManager.rollBack();
        }

    }
     
    public List<ItemPedido> findAll(){
        return dao.findAll();
    }
    
     public List<ItemPedido> findWhere(Long id, String descricao) {
        Query query = dao.getEntityManager().createQuery(
                "From ItemPedido where pedido_id = ?1 and produto_id in (select id from Produto where descricao = ?2)"
        );
        query.setParameter(1, id);
        query.setParameter(2, descricao);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
      public ItemPedido getById(long id){
        return dao.getById(id);
    }
}
