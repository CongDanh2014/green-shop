/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.shopping;

import java.util.HashMap;
import java.util.Map;
import sample.users.ProductDTO;

/**
 *
 * @author ASUS
 */
public class Cart {

    Map<String, ProductDTO> cart;

    public Cart() {
    }

    public Cart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public boolean add(ProductDTO p) {
        boolean check = false;
        if (cart == null) {
            cart = new HashMap<>();
        }
        if (this.cart.containsKey(p.getProductID())) {
            p.setQuantity(p.getQuantity());
        }
        this.cart.put(p.getProductID(), p);

        check = true;

        return check;
    }


    public boolean delete(String id) {
        boolean check = false;
        if (this.cart != null) {

            if (this.cart.containsKey(id)) {
                this.cart.remove(id);
                check = true;
            }
        }

        return check;
    }

    public boolean update(String id, ProductDTO p) {
        boolean check = false;
        if (this.cart != null) {
            if (this.cart.containsKey(id)) {
                this.cart.replace(id, p);
                check = true;
            }
        }

        return check;
    }

}
