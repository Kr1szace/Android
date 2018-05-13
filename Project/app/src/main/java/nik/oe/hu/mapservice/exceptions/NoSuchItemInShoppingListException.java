package nik.oe.hu.mapservice.exceptions;

import nik.oe.hu.model.Product;

/**
 * Created by Haku on 2018. 05. 13..
 */

public class NoSuchItemInShoppingListException extends NotAllowedShoppingListActionException {
    public NoSuchItemInShoppingListException(Product productct) {
        super(productct);
    }

    @Override
    public  String getMessage(){
        return product.getName() + " tétel nem szerepel a beásárlólistájában.";
    }
}
