package northwind;

import java.sql.ResultSet;


public class UserDAO {
	private dbConnection conn;
    public UserDAO()
    {
        conn = new dbConnection();
    }

    public ResultSet searchByID(String CustomerID)
    {
        String query = "select * from Customers where CustomerID ='"+CustomerID+"'";
        
        return conn.executeSelectQuery(query);
    }

    public ResultSet searchByName(String CompanyName)
    {
        String query = "select * from Customers where CompanyName = '"+CompanyName+"'";
        
        return conn.executeSelectQuery(query);
    }

	public ResultSet searchPopulationOfCountry() 
	{
		String query = "select Country,count(*) population from Customers group by Country";
        
        return conn.executeSelectQuery(query);
	}

	public ResultSet searchSupplierOfCountry() 
	{
		String query = "select ShipCountry,count(*) population from Orders group by ShipCountry";
        
        return conn.executeSelectQuery(query);
	}
	
	public ResultSet searchInStockAndOnOrder() 
	{
		String query = "select ProductID,UnitsInStock,UnitsOnOrder from Products";
        
        return conn.executeSelectQuery(query);
	}
}
