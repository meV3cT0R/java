package com.vector;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    private Scanner scanner;
    private ItemService is;
    public App(Scanner scanner,ItemService is) {
        this.scanner = scanner;
        this.is = is;
    }

    public void run() throws Exception {
        boolean running = true;   
        while(running) {
            System.out.println();
            System.out.println();
            System.out.println("******************************");
            System.out.println("*   Simple Inventory System  *");
            System.out.println("******************************");
            System.out.println();
            System.out.println("Options:");
            System.out.println(" 1. view all items");
            System.out.println(" 2. delete");
            System.out.println(" 3. add");
            System.out.println(" 4. search");
            System.out.println("-1. quit");
            System.out.print("Choose from the option:");
            
            int userInput = Integer.parseInt(scanner.nextLine());

            switch(userInput) {
                case 1:
                    System.out.println();
                    is.listAll().forEach(item->{
                        System.out.printf("%d\t%s\t%d\t%d\t%s%n",item.getId(),item.getName(),item.getQuantity(),item.getCostPerUnit(),item.getUnit());
                    });
                    break;
                case 2:
                    while(true) {
                        System.out.println();
                        System.out.println(" -2: delete all records");
                        System.out.println(" -1: go back");
                        System.out.println(" id: to delete particular id");
                        System.out.print("choose an option:");
                        int id = Integer.parseInt(scanner.nextLine());
                        if(id==-1) break;
                        if(id==-2){ is.deleteAll(); break;};
                        is.delete(id);
                    }
                    
                    break;
                case 3:
                    Item newItem = showItemForm();
                    if(newItem ==null) break;
                    is.save(newItem);
                    break;

                case 4:
                    System.out.println();
                    System.out.print("search : ");
                    String search = scanner.nextLine();
                    is.filter(search).forEach(item->{
                        System.out.printf("%d\t%s\t%d\t%d\t%s%n",item.getId(),item.getName(),item.getQuantity(),item.getCostPerUnit(),item.getUnit());
                    });
                    break;
                case -1:
                    running =false;
                default:
                    break;
            }
        }
    }

    public Item showItemForm() throws Exception {
        long quantity;
        String unit,name; 
        int cpu;

        Item item = new Item();
        System.out.print("Enter item name:");
        name = scanner.nextLine();
        if(name.isBlank() || name.isEmpty()){
            System.out.println("name shouldn't be empty");
            return null;
        }
        if(name.length() > 30 || name.length() < 5) {
            System.out.println("not valid length");
            return null;
        }
        if(!name.matches("[0-9a-zA-Z ]*")){
            System.out.println("not valid name");
            return null; 

        } 

        
        try{
            System.out.print("Enter item quantity:");
            quantity = Long.parseLong(scanner.nextLine());
            
            System.out.print("Enter item quantity unit:");
            unit = scanner.nextLine();
            
            System.out.print("Enter item's cost per unit:");
            cpu = Integer.parseInt(scanner.nextLine());
        }catch(Exception e) {
            e.printStackTrace();
            return null;
            
        }
        item.setName(name);
        item.setQuantity(quantity);
        item.setCostPerUnit(cpu);
        item.setUnit(unit);
        return item;
    }
    public static void main( String[] args ) throws Exception{
        new App(new Scanner(System.in),new ItemServiceImpl()).run();
    }
}
