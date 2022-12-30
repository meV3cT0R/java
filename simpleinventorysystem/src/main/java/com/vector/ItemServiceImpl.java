package com.vector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService{

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        Connection conn = DB.getConnection();
        String sql = String.format("DELETE FROM items WHERE id='%d';",id);
        Statement stm = conn.createStatement();
        stm.executeUpdate(sql); 
    }

    @Override
    public List<Item> filter(String search) throws SQLException, ClassNotFoundException {
        List<Item> items = listAll();
        List<Item> filtered = new ArrayList<>();
        String[] searchArr = search.split("[><=!]+");
        if(searchArr.length == 1) {
            items.forEach(i ->{
                if(i.getName().contains(search)) {
                    filtered.add(i);
                }
            });
        }else {
            if(searchArr[0].toLowerCase().trim().equals("quantity")) {
                String operator ="";
                if(search.split("(>)").length > 1) operator = ">";
                if(search.split("(>=)").length >1) operator = ">=";
                if(search.split("(<=)").length >1) operator = "<=";
                if(search.split("(<)").length >1) operator = "<";
                return filterItems(items, operator, Long.parseLong(searchArr[1].trim()));      
            }
        }
        return filtered;
    }

    private List<Item> filterItems(List<Item> items,String operator,long value) {
        return items.stream().filter(item-> {
            switch(operator.trim()) {
                case ">":
                    return item.getQuantity() > value;
                case ">=":
                    return item.getQuantity() >= value;
                case "<":
                    return item.getQuantity() <value;
                case "<=":
                    return item.getQuantity() <= value;
                default:
                    return false;
            }
            
        }).toList();
    }
    @Override
    public List<Item> listAll() throws SQLException, ClassNotFoundException {
        List<Item> items = new ArrayList<>();
        Connection conn = DB.getConnection();
        String sql = "SELECT * FROM items;";
        Statement stm = conn.createStatement();

        ResultSet rs = stm.executeQuery(sql);

        while(rs.next()){
            Item item = new Item();
            item.setId(rs.getLong("id"));
            item.setName(rs.getString("name"));
            item.setQuantity(rs.getLong("quantity"));
            item.setCostPerUnit(rs.getInt("cpu"));
            item.setUnit(rs.getString("unit"));
            items.add(item);
        }
        return items;
    }

    @Override
    public void save(Item item) throws SQLException, ClassNotFoundException {
        Connection conn = DB.getConnection();
        String sql = "INSERT INTO items (name,quantity,cpu,unit) values(?,?,?,?);";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, item.getName());
        pstm.setLong(2, item.getQuantity());
        pstm.setInt(3, item.getCostPerUnit());
        pstm.setString(4, item.getUnit());
        pstm.executeUpdate();
    }

    @Override
    public void update(Item item) throws SQLException, ClassNotFoundException {    
        Connection conn = DB.getConnection();
        String sql = "UPDATE items SET name = ?,quantity = ?,cpu=?,unit=? WHERE id = ?;";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,item.getName());
        pstm.setLong(2,item.getQuantity());
        pstm.setInt(3,item.getCostPerUnit());
        pstm.setString(4,item.getUnit());
        pstm.setLong(5,item.getId());
    }

    @Override
    public void deleteAll() throws SQLException, ClassNotFoundException {
        Connection conn = DB.getConnection();
        String sql = "DELETE FROM items;";
        Statement stm = conn.createStatement();
        stm.executeUpdate(sql);
    }
    
}
