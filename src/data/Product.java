/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author thaiq
 */
public class Product implements Comparable{
    private String ID, name, status;
    private double unitPrice;
    private int quantity;

    public Product() {
    }

    public Product(String ID, String name, double unitPrice, int quantity, String status) {
        this.ID = ID;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.status = status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ID +",  "+ name + ",  " + unitPrice  + ",      " + quantity  + ",      " + status;
    }

    @Override
    public int compareTo(Object o) {
       return ID.compareTo(((Product)o).ID);
       //return Double.compare(unitPrice, unitPrice);
    }

     
}
