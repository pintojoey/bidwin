package bidwin.database;

import bidwin.models.Product;
import cz.zcu.kiv.server.sqlite.UserAlreadyExistsException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryProducts {
    private static Log logger = LogFactory.getLog(QueryProducts.class);

    public static Product addProduct(Product product) throws SQLException, UserAlreadyExistsException {

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                connection = mysqlDB.getInstance().connect();
                preparedStatement =
                        connection.prepareStatement("INSERT INTO `products` (`name`, `description`) VALUES (?, ?);",
                                Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.executeUpdate();

                ResultSet tableKeys = preparedStatement.getGeneratedKeys();
                tableKeys.next();
                long autoGeneratedID = tableKeys.getLong(1);
                product.setId(autoGeneratedID);
                return product;
            } finally {
                if(preparedStatement!=null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e1) {
                        logger.error(e1);
                    }
                }
                if(connection!=null){
                    try {
                        connection.close();
                    } catch (SQLException e1) {
                        logger.error(e1);
                    }
                }
        }

    }

    public static List<Product> getAllProducts(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Product>products=new ArrayList<>();
        try {
            connection = mysqlDB.getInstance().connect();
            preparedStatement =
                    connection.prepareStatement("SELECT * FROM products;" );


            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                products.add(product);
            }
            return products;

        }
        catch (SQLException e){
            logger.error(e);
            return products;
        }
        finally {
            if(preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }

        }

    }

    public static Product getProduct(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = mysqlDB.getInstance().connect();
            preparedStatement =
                    connection.prepareStatement("SELECT * FROM products where id =?;" );
            preparedStatement.setLong(1,id);


            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                return product;
            }
            else return null;

        }
        catch (SQLException e){
            logger.error(e);
            return null;
        }
        finally {
            if(preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }

        }
    }

    public static void main(String[] args) {
        Product product = new Product();
        product.setName("iphone");
        product.setDescription("helloworld");
        try {
            addProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }

    }
    /*

    public static User getUserByEmail(String email) throws SQLException, UserDoesNotExistException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = mysqlDB.getInstance().connect();
            preparedStatement =
                    connection.prepareStatement("SELECT * FROM users WHERE email=?;" );

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                User user=new User();
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUsername(resultSet.getString("username"));
                user.setToken(resultSet.getString("token"));
                user.setId(resultSet.getLong("id"));
                user.setActive(resultSet.getBoolean("active"));
                user.setReset(resultSet.getBoolean("reset"));
                return user;
            }
            else throw new UserDoesNotExistException(email);
        }
        finally {
            if(preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }

        }
    }

    public static User updateUser(User user) throws SQLException, UserDoesNotExistException {
        //Check if user exists
        User oldUser=getUserByEmail(user.getEmail());
        {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                connection = mysqlDB.getInstance().connect();
                preparedStatement =
                        connection.prepareStatement("UPDATE users SET password=?, token=?, active=?, reset=? WHERE id=?;",
                                Statement.RETURN_GENERATED_KEYS);

                boolean passwordChanged=!oldUser.getPassword().equals(user.getPassword());
                preparedStatement.setString(1, passwordChanged?MD5(user.getPassword()):user.getPassword());
                preparedStatement.setString(2, user.getToken());
                preparedStatement.setBoolean(3, user.getActive());
                preparedStatement.setBoolean(4, user.getReset());
                preparedStatement.setLong(5, user.getId());

                preparedStatement.executeUpdate();
                return user;
            }
            finally {
                if(preparedStatement!=null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e1) {
                        logger.error(e1);
                    }
                }
                if(connection!=null){
                    try {
                        connection.close();
                    } catch (SQLException e1) {
                        logger.error(e1);
                    }
                }
            }

        }
    }

    public static boolean checkAuthorized(String email, String token) throws SQLException{
        try {
            User user=getUserByEmail(email);
            if(!user.getToken().equals(token))
                return false;
        } catch (UserDoesNotExistException e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    public static boolean userExists(String email) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = mysqlDB.getInstance().connect();
            preparedStatement =
                    connection.prepareStatement("SELECT * FROM users WHERE email=?;" );

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else return false;
        }
        finally {
            if(preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }

        }
    }

    public static String MD5(String md5)  {
        java.security.MessageDigest md;
        try {
            md = java.security.MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
            //Continuing execution with same string returned.
            return md5;
        }
        byte[] array = md.digest(md5.getBytes(Charset.defaultCharset()));
            StringBuilder sb = new StringBuilder();
            for (byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
    }
    */
}
