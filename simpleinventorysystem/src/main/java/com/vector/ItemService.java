package com.vector;

import java.sql.SQLException;
import java.util.List;

public interface ItemService {
    void save(Item item) throws ClassNotFoundException,SQLException;
    void update(Item item) throws ClassNotFoundException,SQLException;
    void delete(int id) throws ClassNotFoundException,SQLException;
    List<Item> filter(String search) throws ClassNotFoundException,SQLException;
    List<Item> listAll() throws ClassNotFoundException,SQLException;
    void deleteAll() throws ClassNotFoundException,SQLException;
}
