package bidwin.database;

import java.sql.*;

public interface Query{

    String customerQ = "select * from customer " ;
    String orderQ = "select * from order ";
    String marketQ = "select * from market ";
    String retailerQ = "select * from retailer ";
    String productQ = "select * from product ";
    String inventoryQ = "select * from inventory ";
    String bidQ = "select * from bid ";
}