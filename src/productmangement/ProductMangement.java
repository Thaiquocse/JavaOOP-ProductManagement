/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productmangement;

import data.ProductLists;
import menu.Menu;

/**
 *
 * @author thaiq
 */
public class ProductMangement {

    public static void main(String[] args) {
        Menu menu = new Menu("Product Management");
        menu.addNewOption(" 1. Create A Product");
        menu.addNewOption(" 2. Check To Exist Product");
        menu.addNewOption(" 3. Search Product Information By Name");
        menu.addNewOption(" 4. Update Product Information");
        menu.addNewOption(" 5. Delete Product Information");
        menu.addNewOption(" 6. Save To File");
        menu.addNewOption(" 7. Print All Lists From File");
        menu.addNewOption(" 8. Quit");
        
        ProductLists pList = new ProductLists();

        int choice;

        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1: pList.createNewProduct();
                break;
                
                case 2: pList.checkToExistProduct();
                break;
                case 3: pList.searchProductByName();
                break;
                
                case 4: pList.updateProduct();
                break;
                
                case 5: pList.deleteProduct();
                break;
                
                case 6: pList.saveToFile();
                break;
                
                case 7: pList.showAllProductInList();
                break;
                
                case 8: 
                    System.out.println("Good Bye and See you again");
                break;
                
               
            }
        } while (choice != 8);
    }
}
