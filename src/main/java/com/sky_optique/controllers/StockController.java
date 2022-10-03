/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.Lentille;
import com.sky_optique.entities.Monture;
import com.sky_optique.entities.Stock;
import com.sky_optique.repositories.LentilleRepository;
import com.sky_optique.repositories.MontureRepository;
import com.sky_optique.repositories.StockRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Chris
 */
@RestController
@RequestMapping(path = "/stock")
@CrossOrigin("*")
public class StockController {
    
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private MontureRepository montureRepository;
    @Autowired
    private LentilleRepository lentilleRepository;
    
	
    @GetMapping(path = "/")
    public @ResponseBody Iterable <Stock> getAllStock() {
        return stockRepository.findAll();
    }
    @GetMapping(path = "/monture/")
    public @ResponseBody Iterable <Stock> getAllStockMonture() {
        return stockRepository.findStockByProductType("M");
    }
    @GetMapping(path = "/lentille/")
    public @ResponseBody Iterable <Stock> getAllStockLentille() {
    	return stockRepository.findStockByProductType("L");
    }
    
    @GetMapping(path = "/stockByQte/{qte}")
    public @ResponseBody Iterable<Stock> getAllStocksByQte(@PathVariable int qte) {
        return stockRepository.getAllByQteIsGreaterThanEqual(qte);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Stock> getStockById(@PathVariable long id) {
        return stockRepository.findById(id);
    }
    
    @PostMapping(path = "/")
    public @ResponseBody Stock addStock(@RequestBody Stock stock) {
        return stockRepository.save(stock);
    }
    @Transactional
    @PostMapping(path = "/monture/")
    public @ResponseBody Stock addStockMonture(@RequestBody Stock stock) {
        Monture monture = new Monture();
        System.out.println(stock);

        if(stock.getProduit() !=null && stock.getProduit().getId() == null)
            stock.setProduit(montureRepository.save((Monture) stock.getProduit()));

    	//stock.setProduit(montureRepository.save((Monture)stock.getProduit()));
        return stockRepository.save(stock);
    }
    @Transactional
    @PostMapping(path = "/lentille/")
    public @ResponseBody Stock addStockLentille(@RequestBody Stock stock) {
    	stock.setProduit(lentilleRepository.save((Lentille)stock.getProduit()));
        return stockRepository.save(stock);
    }
	
    @PutMapping(path = "/{id}")
    public @ResponseBody Stock updateStock(@PathVariable long id, @RequestBody Stock stock) {
    	stock.setId(id);
        return stockRepository.save(stock);
    }
  
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteStockById(@PathVariable long id) {
    	stockRepository.deleteById(id);
        return true;
    }

}
