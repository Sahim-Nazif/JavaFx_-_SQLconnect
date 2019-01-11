/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_store;

/**
 *
 * @author nazif
 */
public class GameList {
    
    private String barcode;
    private String gameName;
    private String gameType;
    private String price;
    private String inStock;

    public GameList(String barcode, String gameName, String gameType, String price, String inStock) {
        this.barcode = barcode;
        this.gameName = gameName;
        this.gameType = gameType;
        this.price = price;
        this.inStock = inStock;
    }

    /**
     * @return the barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * @param barcode the barcode to set
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * @return the gameName
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * @param gameName the gameName to set
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * @return the gameType
     */
    public String getGameType() {
        return gameType;
    }

    /**
     * @param gameType the gameType to set
     */
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return the inStock
     */
    public String getInStock() {
        return inStock;
    }

    /**
     * @param inStock the inStock to set
     */
    public void setInStock(String inStock) {
        this.inStock = inStock;
    }
    
    
}
