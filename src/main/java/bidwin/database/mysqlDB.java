package bidwin.database;


import cz.zcu.kiv.server.Workflow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static cz.zcu.kiv.server.Workflow.DATA_FOLDER;
import static cz.zcu.kiv.server.Workflow.UPLOAD_FOLDER;


public class mysqlDB {
    private static Log logger = LogFactory.getLog(mysqlDB.class);

    //public static final String SQLITE_DB = DATA_FOLDER+"/sqlite.db";

    private boolean initialized = false;
    private static mysqlDB instance;
    private mysqlDB(){

    }

    public static mysqlDB getInstance(){
        if(instance==null){
            instance=new mysqlDB();
        }
      return instance;
    }
    /**
     * Connect to a database
     */
    Connection connect() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/bidwin?user=root&password=root";

            // create a connection to the database
            conn = DriverManager.getConnection(url);

            if(!initialized){
                /*
                String createUsersTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                        + "	id integer PRIMARY KEY AUTOINCREMENT,"
                        + "	email text NOT NULL,"
                        + "	password text NOT NULL,"
                        + "	username text NOT NULL,"
                        + "	token text NOT NULL,"
                        + "	reset boolean NOT NULL,"
                        + "	active boolean NOT NULL"
                        + ");";

                String createModulesTableSQL = "CREATE TABLE IF NOT EXISTS modules ("
                        + "	id integer PRIMARY KEY AUTOINCREMENT,"
                        + "	jarName text NOT NULL,"
                        + "	packageName text NOT NULL,"
                        + "	publicJar boolean NOT NULL,"
                        + "	author text NOT NULL,"
                        + "	lastUpdate text NOT NULL,"
                        + " FOREIGN KEY(author) REFERENCES users(email) "
                        + ");";

                String createJobsTableSQL = "CREATE TABLE IF NOT EXISTS jobs ("
                        + "	id integer PRIMARY KEY AUTOINCREMENT,"
                        + "	owner text NOT NULL,"
                        + "	status text NOT NULL,"
                        + "	startTime text NULL,"
                        + "	endTime text NULL,"
                        + "	workflow text NOT NULL,"
                        + "	workflowOutputFile text NULL,"
                        + " FOREIGN KEY(owner) REFERENCES users(email) "
                        + ");";

                stmt = conn.createStatement();
                // create a new table
                stmt.execute(createUsersTableSQL);
                stmt.execute(createModulesTableSQL);
                stmt.execute(createJobsTableSQL);

                initialized=true;
                */
            }

            return conn;


        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e);
        } finally {
            try {
                if(stmt !=null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                logger.error(ex);
            }
        }
        return null;
    }
}
