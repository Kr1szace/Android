package nik.oe.hu.mapservice.exceptions;

import nik.oe.hu.model.Product;

/**
 * Created by Haku on 2018. 05. 13..
 */

public class NotAllowedShoppingListActionException extends Exception {

    Product product;

    public NotAllowedShoppingListActionException(Product product) {
        this.product = product;
    }
}

