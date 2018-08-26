package bidwin.database;

import bidwin.models.Inventory;
import bidwin.models.Product;
import cz.zcu.kiv.server.sqlite.UserAlreadyExistsException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryInventory {
    private static Log logger = LogFactory.getLog(QueryProducts.class);

    public static Inventory addInventory(Inventory inventory) throws SQLException, UserAlreadyExistsException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = mysqlDB.getInstance().connect();
            preparedStatement =
                    connection.prepareStatement("INSERT INTO `inventory` (`product_id`, `retailer_id`, `market_id`, `sellnow`, `minprice`, `count`, `scores`) VALUES (?, ?, ?, ?, ?, ?, ?);");

            preparedStatement.setLong(1, inventory.getProductId());
            preparedStatement.setLong(2, inventory.getRetailerId());
            preparedStatement.setLong(3, inventory.getMarketId());
            preparedStatement.setDouble(4, inventory.getSellNow());
            preparedStatement.setDouble(5, inventory.getMinPrice());
            preparedStatement.setInt(6, inventory.getCount());
            preparedStatement.setInt(7, inventory.getScores());

            preparedStatement.executeUpdate();


            return inventory;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }
        }

    }

    public static List<Inventory> getAllInventories() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Inventory> inventories = new ArrayList<>();
        try {
            connection = mysqlDB.getInstance().connect();
            preparedStatement =
                    connection.prepareStatement("SELECT * FROM inventory;");


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Inventory inventory = new Inventory();
                inventory.setProductId(resultSet.getLong("product_id"));
                inventory.setRetailerId(resultSet.getLong("retailer_id"));
                inventory.setRetailerId(resultSet.getLong("market_id"));
                inventory.setRetailerId(resultSet.getLong("sellnow"));
                inventory.setRetailerId(resultSet.getLong("minprice"));
                inventory.setRetailerId(resultSet.getInt("count"));
                inventory.setRetailerId(resultSet.getInt("scores"));
                inventories.add(inventory);
            }
            return inventories;

        } catch (SQLException e) {
            logger.error(e);
            return inventories;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }

        }

    }


    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.setProductId(123);
        inventory.setRetailerId(456);
        inventory.setMarketId(789);
        inventory.setSellNow(450);
        inventory.setMinPrice(350);
        inventory.setCount(10);
        inventory.setScores(4);

        try {
            addInventory(inventory);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }

    }
}