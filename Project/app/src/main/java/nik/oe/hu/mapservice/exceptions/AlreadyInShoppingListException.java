package nik.oe.hu.mapservice.exceptions;

import nik.oe.hu.model.Product;

public class AlreadyInShoppingListException extends NotAllowedShoppingListActionException{

    public AlreadyInShoppingListException(Product product) {
        super(product);
    }

    @Override
    public  String getMessage(){
        return super.product.getName() + " tétel már szerepel a beásárlólistájában.";
    }
}
