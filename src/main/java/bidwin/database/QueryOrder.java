package bidwin.database;

import bidwin.models.Bid;
import bidwin.models.Order;
import bidwin.models.Product;
import cz.zcu.kiv.server.sqlite.UserAlreadyExistsException;
import cz.zcu.kiv.server.sqlite.UserDoesNotExistException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryOrder {
    private static Log logger = LogFactory.getLog(QueryOrder.class);

    public static Order addOrder(Order order) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = mysqlDB.getInstance().connect();
            preparedStatement =
                    connection.prepareStatement("INSERT INTO `orders` (`product_id`, `market_id`, `buynow`, `startbid`, `minrating`, `duration`, `customer_id`) VALUES (?, ?, ?, ?, ?, ?, ?);",
                            Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, order.getProductId());
            preparedStatement.setLong(2, order.getMarketId());
            preparedStatement.setDouble(3, order.getBuyNow());
            preparedStatement.setDouble(4, order.getStartBid());
            preparedStatement.setDouble(5, order.getMinRating());
            preparedStatement.setTimestamp(6, new Timestamp(order.getDuration()));
            preparedStatement.setLong(7,order.getCustomerId());
            preparedStatement.executeUpdate();

            ResultSet tableKeys = preparedStatement.getGeneratedKeys();
            tableKeys.next();
            long autoGeneratedID = tableKeys.getLong(1);
            order.setId(autoGeneratedID);
            return order;
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

    public static List<Order> getAllOpenOrders() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Order> orders = new ArrayList<>();
        try {
            connection = mysqlDB.getInstance().connect();
            preparedStatement =
                    connection.prepareStatement("SELECT * FROM orders where status=0;");


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setProductId(resultSet.getLong("product_id"));
                order.setMarketId(resultSet.getLong("market_id"));
                order.setBuyNow(resultSet.getDouble("buynow"));
                order.setStartBid(resultSet.getDouble("startbid"));
                order.setMinRating(resultSet.getInt("minrating"));
                order.setDuration(resultSet.getTimestamp("duration").getTime());
                order.setTimestamp(new Date(resultSet.getTimestamp("timestamp").getTime()));
                order.setStatus(resultSet.getInt("status"));
                orders.add(order);
            }
            return orders;

        } catch (SQLException e) {
            e.printStackTrace();
            return orders;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
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

    public static Order getOrder(long orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = mysqlDB.getInstance().connect();
            preparedStatement =
                    connection.prepareStatement("SELECT * FROM orders WHERE id=?;");
            preparedStatement.setLong(1,orderId);


            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setProductId(resultSet.getLong("product_id"));
                order.setMarketId(resultSet.getLong("market_id"));
                order.setBuyNow(resultSet.getDouble("buynow"));
                order.setStartBid(resultSet.getDouble("startbid"));
                order.setMinRating(resultSet.getInt("minrating"));
                order.setDuration(resultSet.getTimestamp("duration").getTime());
                order.setTimestamp(new Date(resultSet.getTimestamp("timestamp").getTime()));
                order.setStatus(resultSet.getInt("status"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                return order;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
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
        Order order = new Order();
        order.setProductId(123);
        order.setMarketId(456);
        order.setBuyNow(400);
        order.setStartBid(350);
        order.setMinRating(2);

        try {
            addOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateOrder(long orderId, int status) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = mysqlDB.getInstance().connect();
            preparedStatement =
                    connection.prepareStatement("UPDATE orders SET status=? WHERE id=?;");
            preparedStatement.setInt(1,status);
            preparedStatement.setLong(2,orderId);


            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
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

    public static List<Order> getOrdersByEmail(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Order> orders = new ArrayList<>();
        try {
            connection = mysqlDB.getInstance().connect();
            preparedStatement =
                    connection.prepareStatement("SELECT * FROM orders where customer_id=?;");

            preparedStatement.setLong(1,QueryCustomers.getUserByEmail(email).getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setProductId(resultSet.getLong("product_id"));
                order.setMarketId(resultSet.getLong("market_id"));
                order.setBuyNow(resultSet.getDouble("buynow"));
                order.setStartBid(resultSet.getDouble("startbid"));
                order.setMinRating(resultSet.getInt("minrating"));
                order.setDuration(resultSet.getTimestamp("duration").getTime());
                order.setTimestamp(new Date(resultSet.getTimestamp("timestamp").getTime()));
                order.setStatus(resultSet.getInt("status"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                orders.add(order);
            }
            return orders;

        } catch (SQLException e) {
            e.printStackTrace();
            return orders;
        } catch (UserDoesNotExistException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
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
        return orders;
    }
}