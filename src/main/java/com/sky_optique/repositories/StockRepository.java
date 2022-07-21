package com.sky_optique.repositories;

import com.sky_optique.entities.Stock;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

 
public interface StockRepository extends CrudRepository<Stock, Long>{

    @Query("select l from Stock l where l.produit.id = :ip")
    public Stock findStockByProductId(@Param("ip")Long idProduit);
    
    @Query("select l from Stock l where l.produit.discriminator = :d")
    public Iterable<Stock> findStockByProductType(@Param("d")String discriminator);
    

    public Iterable<Stock> getAllByQteIsGreaterThanEqual(int qte);

}
