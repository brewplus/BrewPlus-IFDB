/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmash.inventario.model;

import java.util.List;
import jmash.Hop;
import jmash.Malt;
import jmash.RecipeData;
import jmash.Yeast;

/**
 *
 * @author a.cerella
 */
public class RecipesModel {
    
    private List<RecipeData> ricette;
    private List<Hop> hops;
    private List<Malt> malts;
    private List<Yeast> yeasts;

    public List<RecipeData> getRicette() {
        return ricette;
    }

    public void setRicette(List<RecipeData> ricette) {
        this.ricette = ricette;
    }

    public List<Hop> getHops() {
        return hops;
    }

    public void setHops(List<Hop> hops) {
        this.hops = hops;
    }

    public List<Malt> getMalts() {
        return malts;
    }

    public void setMalts(List<Malt> malts) {
        this.malts = malts;
    }

    public List<Yeast> getYeasts() {
        return yeasts;
    }

    public void setYeasts(List<Yeast> yeasts) {
        this.yeasts = yeasts;
    }
    
    
}
