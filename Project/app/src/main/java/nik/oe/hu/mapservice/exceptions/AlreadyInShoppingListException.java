package nik.oe.hu.mapservice.exceptions;

import nik.oe.hu.model.Product;

public class AlreadyInShoppingListException extends NotAllowedShoppingListActionException{

    public AlreadyInShoppingListException(Product productct) {
        super(productct);
    }

    @Override
    public  String getMessage(){
        return product.getName() + " tétel már szerepel a beásárlólistájában.";
    }
}
