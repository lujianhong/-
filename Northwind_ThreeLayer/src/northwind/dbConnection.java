package northwind;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbConnection {

    private ResultSet rs=null;
    private Connection conn=null;
    String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL="jdbc:sqlserver://47.106.123.245:1433;DatabaseName=Northwind";
	String userName="sa";
	String userPwd="123456";

    public dbConnection()
    {
    	try
		{
    		Class.forName(driverName);
    		conn=DriverManager.getConnection(dbURL,userName,userPwd);
    		System.out.println("连接数据库成功");
    		rs=null;
		}
    	catch(Exception e)
		{
			e.printStackTrace();
			
			System.out.print("连接失败:"+e.getStackTrace().toString());
		}
    }

    /// <method>
    /// Open Database Connection if Closed or Broken
    /// </method>
    private Connection openConnection()
    {
    	try
		{
    		if (conn.isClosed())
    		{
    			Class.forName(driverName);
        		conn=DriverManager.getConnection(dbURL,userName,userPwd);
        		System.out.println("连接数据库成功");
        		rs=null;
    		}
		}
    	catch(Exception e)
		{
			e.printStackTrace();
			
			System.out.print("连接失败:"+e.getStackTrace().toString());
		}
        return conn;
    }
    
    public ResultSet executeSelectQuery(String _query)
    {
        try
        {
        	Statement statement=conn.createStatement();
        	rs=statement.executeQuery(_query);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        
			System.out.print("查询失败:"+e.getStackTrace().toString());
            return null;
        }
        return rs;
    }

    public boolean executeInsertQuery(String _query)
    {
    	try
        {
        	Statement statement=conn.createStatement();
        	statement.addBatch(_query);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	
			System.out.print("插入失败:"+e.getStackTrace().toString());
            return false;
        }
        return true;
    }

    public boolean executeUpdateQuery(String _query)
    {
    	try
        {
        	Statement statement=conn.createStatement();
        	statement.executeUpdate(_query);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	
			System.out.print("更新失败:"+e.getStackTrace().toString());
            return false;
        }
        return true;
    }
    
}
