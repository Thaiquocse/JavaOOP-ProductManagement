 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import utils.MyTool;

/**
 *
 * @author thaiq
 */
public class ProductLists {

    private Scanner sc = new Scanner(System.in);
    private ArrayList<Product> productList = new ArrayList<>();
    private static final String NAMEFORMAT = "^\\S{5,}$";

    String filename = "product.txt";

    public ProductLists() {
        loadDealerFromFile();
    }

    public void loadDealerFromFile() {

        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");
                String ID = stk.nextToken().trim();
                String name = stk.nextToken().trim();
                double unitPrice = Double.parseDouble(stk.nextToken().trim());
                int quantity = Integer.parseInt(stk.nextToken().trim());
                String status = stk.nextToken().trim();
                productList.add(new Product(ID, name, unitPrice, quantity, status));

            }

            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void createNewProduct() {
        String id;
        String name;
        double unitPrice;
        int quantity;
        String status;
        int pos;
        System.out.println("Now you will input Information of new Product: ");
        id = MyTool.getString("Input ID: ", "Not blank or empty.");
        do {
            name = utils.MyTool.regexString("Input Name: ", "A Product Name must be at least FIVE CHARACTERS and NO BLANK, SPACE or EMPTY ", NAMEFORMAT);
            pos = checkname(name);
            if (pos >= 0) {
                System.out.println("Exist Product Name" + ". Please Try Again With Another Name !!!");
            }
        } while (pos != -1);
        unitPrice = MyTool.getADouble("Input UnitPrice: ", "A UnitPrice from 0 to 10000", 0, 10000);
        quantity = MyTool.getAnInteger("Input A Quantiy: ", "A Quantity ranges from 0 to 1000", 1, 1000);
        status = MyTool.getString("Input Status: ", "Just Available or Not Avaiable");
        productList.add(new Product(id, name.toUpperCase(), unitPrice, quantity, status.toUpperCase()));
        System.out.println("A new product is added sucessfully !!!");

    }

    public void checkToExistProduct() {
        String name = MyTool.getString("input name of product you want to check: ", "Please Try Again !!!").toUpperCase();
        int index = checkname1(name);
        if (index >= 0) {
            System.out.println("Exist Product");
        } else {
            System.out.println("Product doesn't exist");
        }

    }

    public int checkname(String name) {
        if (productList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().contains(name)) {
                return i;
            }
        }
        return -1;

    }
    public int checkname1(String name) {
        if (productList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }
    public int checkID(String ID) {
        if (productList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getID().equalsIgnoreCase(ID)) {
                return i;
            }

        }
        return -1;
    }

    public void searchProductByName() {
        String name = utils.MyTool.getString("Please input name to find: ", "Not found A product. Please try again!!!").toUpperCase();
        int pos = checkname(name);

        if (productList.isEmpty()) {
            System.out.println("Nothing to show");
        }
        if (pos >= 0) {
            System.out.println("ID     NAME   UNITPRICE   QUANTITY    STATUS");
            //int count = 0;
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getName().contains(name)) {
                    System.out.println(productList.get(i).toString());
                    //count++;
                }
            }
            //System.out.println("Have " + count + " Product been found");
        } else {
            System.out.println("Do not have any product like: " + name);
        }

    }

    public void updateProduct() {
        String id = MyTool.getString("Enter ID of product to update: ", "Not blank or empty").toUpperCase();
        int index = checkID(id);
        if (index >= 0) {
        String newName;   
            do {                
                  newName = MyTool.regexString("Input new name: ", "A Product Name must be at least FIVE CHARACTERS and NO BLANK, SPACE or EMPTY", NAMEFORMAT).toUpperCase();
                 if(newName.equalsIgnoreCase(productList.get(index).getName())){
                     System.out.println("Name is duplicated please try again");
                 }else{
                     productList.get(index).setName(newName);
                 }
                 
            } while (newName != (productList.get(index).getName()));
            double newUnitprice = MyTool.getADouble("Input new Unit Price: ", "A UnitPrice from 0 to 10000", 0, 10000);
            //if(newUnitprice > 0 || newUnitprice < 10000)
            productList.get(index).setUnitPrice(newUnitprice);
            int newQuantity = MyTool.getAnInteger("Input new Quantity: ", "Quantity from 0 to 1000", 0, 1000);
            //if(newQuantity > 0 || newQuantity < 1000)
            productList.get(index).setQuantity(newQuantity);
            String newStatus = MyTool.getString("Input new Status: ", "Just Available or un Available").toUpperCase();
            if (!newStatus.isEmpty()) {
                productList.get(index).setStatus(newStatus);
            }
        } else {
            System.out.println("Product " + id + " not found. Please try again with another ID !!!");
        }
    }

    public void deleteProduct() {
        String ID = MyTool.getString("Input ID of product you want to delete: ", "Please try again !!!");
        int index = checkID(ID);
        if (index >= 0) {
            productList.remove(productList.get(index));
            System.out.println("Remove successfully");
        } else {
            System.out.println("ID not exist ");
        }
    }

    public void saveToFile() {
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < productList.size(); i++) {
                pw.println(productList.get(i).toString());
            }
            fw.close();
            pw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("Save to file sucessfully.");
    }

    public void showAllProductInList() {
        if (productList.isEmpty()) {
            System.out.println("Nothing to show");
            return;
        }
        System.out.println("ID     NAME   UNITPRICE   QUANTITY    STATUS");
        Collections.sort(productList, compareByQuantity);
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).toString());
        }
    }

    public void sortProductByPrice() {
        System.out.println("The product list before sorting: ");
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).toString());
        }
        System.out.println("");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("");
        System.out.println("The product list After Sorting: ");
        Collections.sort(productList, compareByPrice);
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).toString());
        }
    }

    public void change() {
        String check = MyTool.getString("input True or False: ", "try again please");
        for (int i = 0; i < productList.size(); i++) {
            if (check.equalsIgnoreCase("True")) {
                productList.get(i).setStatus("Valid");
            } else if (check.equalsIgnoreCase("False")) {
                productList.get(i).setStatus("UnValid");
            }
            System.out.println(productList.get(i).toString());

        }
    }

    public static Comparator compareByName = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Product p1 = (Product) o1;
            Product p2 = (Product) o2;
            int d = p1.getName().compareTo(p2.getName());
            if (d > 0) {
                return -1;
            }
            if (d == 0) {
                return 0;
            }
            return 1;
        }

    };
    public static Comparator compareByQuantity = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Product p1 = (Product) o1;
            Product p2 = (Product) o2;
            int d = Integer.compare(p1.getQuantity(), p2.getQuantity());
            if (d > 0) {
                return -1;
            }
            if (d == 0) {
                int c = Double.compare(p1.getUnitPrice(), p2.getUnitPrice());
                if( c < 0)
                    return -1;      
            }
            return 1;
        }
    };

    public static Comparator compareByPrice = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Product p1 = (Product) o1;
            Product p2 = (Product) o2;
            int d = Double.compare(p1.getUnitPrice(), p2.getUnitPrice());
            if (d < 0) {// < 0 la tang dan || > 0 la giam dan
                return -1;
            }
            if (d == 0) {
                return 0;
            }
//            if(d == 0){
//                int c = p1.getID().compareToIgnoreCase(p2.getID());
//                //int c = Integer.compare(p1.getQuantity(), p2.getQuantity());
//            if (c > 0) {
//                return -1;
//            }
//            if (c == 0) {
//                return 0;
//            }
//            return 1;
//            } 
            return 1;
        }
    }; 
    //        Comparator sortPrice = new Comparator<Product>(){
//            @Override
//            public int compare(Product o1, Product o2) {
//                  //return Double.compare(o1.getUnitPrice(), o2.getUnitPrice());
//                  return o1.getID().compareTo(o2.getID());
//                  
//            }
//            
//        };
    public void increasePrice(){
        System.out.println("Input Amount of Price you wana increase: ");
        double newPrice = sc.nextDouble();
        for (int i = 0; i < productList.size(); i++) {
            productList.get(i).setUnitPrice(productList.get(i).getUnitPrice() + newPrice);
            System.out.println(productList.get(i).toString());
        }
        
    }
    public void showAvaiable(){
        if(productList.isEmpty()){
            System.out.println("Nothing to show");
        }
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getStatus().equalsIgnoreCase("Available")){
                System.out.println(productList.get(i).toString());
            }
        }
    }
    
    public void showUnAvaiable(){
        if(productList.isEmpty()){
            System.out.println("Nothing to show");
        }
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getStatus().equalsIgnoreCase("unavailable")){
                System.out.println(productList.get(i).toString());
            }
        }
    }
    
}
