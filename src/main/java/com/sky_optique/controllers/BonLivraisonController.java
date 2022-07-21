package com.sky_optique.controllers;

import com.sky_optique.entities.BonLivraison;
import com.sky_optique.entities.Fournisseur;
import com.sky_optique.entities.Livraison;
import com.sky_optique.repositories.FournisseurRepository;
import com.sky_optique.repositories.StockRepository;
import com.sky_optique.repositories.LivraisonRepository;
import com.sky_optique.repositories.BonLivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Chris
 */
@RestController
@RequestMapping(path = "/bonLivraison")
@CrossOrigin("*")
public class BonLivraisonController {

    @Autowired
    private BonLivraisonRepository bonLivraisonRepository;
    @Autowired
    private LivraisonRepository livraisonRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private FournisseurRepository fournisseurRepository;

    @GetMapping(path = "/")
    public @ResponseBody Iterable <BonLivraison> getAllBonLivraison() {
        return bonLivraisonRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<BonLivraison> getBonLivraisonById(@PathVariable long id) {
        return bonLivraisonRepository.findById(id);
    }

    /*
    @GetMapping(path = "lot/{ip}/{pv}")
    public @ResponseBody Optional<Stock> getlot(@PathVariable long ip, @PathVariable BigDecimal pv) {
        return stockRepository.findLotProductByPrixVente(ip, pv);
    }
    */

    @PostMapping(path = "/")
    @Transactional
    public @ResponseBody
    BonLivraison addBonLivraison(@RequestBody BonLivraison bonLivraison) {
        System.out.println(bonLivraison);
        Fournisseur f = fournisseurRepository.findById(bonLivraison.getFournisseur().getId()).get();

        for(Livraison livraison : bonLivraison.getLivraisons()){
            /*
            Optional<Stock> opt = lotRepository.findLotProductByPrixVente(livraison.getProduit().getStock().getProduit().getId(), livraison.getPrixVente());
            if(opt.isPresent()){
                Stock stock = opt.get();
                stock.setQte(stock.getQte() + livraison.getQte());
                lotRepository.save(stock);
                livraison.setLot(stock);
            }
            else {
                lotRepository.save(livraison.getLot());
            }*/
        }
        bonLivraison.setFournisseur(f);
        return bonLivraisonRepository.save(bonLivraison);
    }

    @PutMapping(path = "/{id}")
    @Transactional
    public @ResponseBody BonLivraison updateBonLivraison(@PathVariable long id, @RequestBody BonLivraison bonLivraison) {
        bonLivraison.setId(id);
        return bonLivraisonRepository.save(bonLivraison);
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public @ResponseBody boolean deleteBonLivraisonById(@PathVariable long id) {
        bonLivraisonRepository.deleteById(id);
        return true;
    }

}
