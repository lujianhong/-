package northwind;

import java.sql.ResultSet;
import java.util.Vector;


public class UserBUS {
	private UserDAO _userDAO;

    public UserBUS()
    {
        _userDAO  = new UserDAO();
    }

    public Vector<UserVO> getPopulationOfCountry()
    {
    	Vector<UserVO> result=new Vector<UserVO>();
        
        ResultSet dataTable = null;

        dataTable = _userDAO.searchPopulationOfCountry();
        try
		{
        	while (dataTable.next()) 
    		{
            	UserVO userVO = new UserVO();
            	userVO.setCountry(dataTable.getString("Country"));
            	userVO.setPopulation(dataTable.getInt("population"));
            	result.add(userVO);
    		}
		}
        catch(Exception e)
		{
			e.printStackTrace();
			
			System.out.print("´íÎó:"+e.getStackTrace().toString());
		}
        return result;
    }
    
    public Vector<UserVO> getSupplierOfCountry() 
    {
    	Vector<UserVO> result=new Vector<UserVO>();
        
        ResultSet dataTable = null;

        dataTable = _userDAO.searchSupplierOfCountry();
        try
		{
        	while (dataTable.next()) 
    		{
            	UserVO userVO = new UserVO();
            	userVO.setCountry(dataTable.getString("ShipCountry"));
            	userVO.setPopulation(dataTable.getInt("population"));
            	result.add(userVO);
    		}
		}
        catch(Exception e)
		{
			e.printStackTrace();
			
			System.out.print("´íÎó:"+e.getStackTrace().toString());
		}
        return result;
	}
    
    public Vector<UserVO> getInStockAndOnOrder() 
    {
    	Vector<UserVO> result=new Vector<UserVO>();
        
        ResultSet dataTable = null;

        dataTable = _userDAO.searchInStockAndOnOrder();
        try
		{
        	while (dataTable.next()) 
    		{
            	UserVO userVO = new UserVO();
            	userVO.setProductID(dataTable.getInt("ProductID"));
            	userVO.setUnitsInStock(dataTable.getInt("UnitsInStock"));
            	userVO.setUnitsOnOrder(dataTable.getInt("UnitsOnOrder"));
            	result.add(userVO);
    		}
		}
        catch(Exception e)
		{
			e.printStackTrace();
			
			System.out.print("´íÎó:"+e.getStackTrace().toString());
		}
        return result;
	}
    
    public Vector<UserVO> getUserByID(String CustomerID)
    {
    	Vector<UserVO> result=new Vector<UserVO>();
        
        ResultSet dataTable = null;

        dataTable = _userDAO.searchByID(CustomerID);
        try
		{
        	while (dataTable.next()) 
    		{
            	UserVO userVO = new UserVO();
            	userVO.setCustomerID(dataTable.getString("CustomerID"));
            	userVO.setCompanyName(dataTable.getString("CompanyName"));
            	userVO.setContactName(dataTable.getString("ContactName"));
            	userVO.setContactTitle(dataTable.getString("ContactTitle"));
            	userVO.setAddress(dataTable.getString("Address"));
            	userVO.setCity(dataTable.getString("City"));
            	userVO.setRegion(dataTable.getString("Region"));
            	userVO.setPostalCode(dataTable.getString("PostalCode"));
            	userVO.setCountry(dataTable.getString("Country"));
            	userVO.setPhone(dataTable.getString("Phone"));
            	userVO.setFax(dataTable.getString("Fax"));
            	result.add(userVO);
    		}
		}
        catch(Exception e)
		{
			e.printStackTrace();
			
			System.out.print("´íÎó:"+e.getStackTrace().toString());
		}
        return result;
    }

    public Vector<UserVO> getUserByName(String CompanyName)
    {
        Vector<UserVO> result=new Vector<UserVO>();
        
        ResultSet dataTable = null;

        dataTable = _userDAO.searchByName(CompanyName);
        try
		{
        	while (dataTable.next()) 
    		{
            	UserVO userVO = new UserVO();
            	userVO.setCustomerID(dataTable.getString("CustomerID"));
            	userVO.setCompanyName(dataTable.getString("CompanyName"));
            	userVO.setContactName(dataTable.getString("ContactName"));
            	userVO.setContactTitle(dataTable.getString("ContactTitle"));
            	userVO.setAddress(dataTable.getString("Address"));
            	userVO.setCity(dataTable.getString("City"));
            	userVO.setRegion(dataTable.getString("Region"));
            	userVO.setPostalCode(dataTable.getString("PostalCode"));
            	userVO.setCountry(dataTable.getString("Country"));
            	userVO.setPhone(dataTable.getString("Phone"));
            	userVO.setFax(dataTable.getString("Fax"));
            	result.add(userVO);
    		}
		}
        catch(Exception e)
		{
			e.printStackTrace();
			
			System.out.print("´íÎó:"+e.getStackTrace().toString());
		}
        return result;
    }

	

}
