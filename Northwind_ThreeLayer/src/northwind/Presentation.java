package northwind;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;
import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;



public class Presentation extends JFrame implements ActionListener{
	
	private UserBUS _userBUS;
	
	private JFrame frame = new JFrame("查询");
	private Container c = frame.getContentPane();
	private JTextField input = new JTextField();
	JLabel a1 = new JLabel("输入信息:");
	private JButton ID = new JButton("以客户ID查询");
	private JButton Name = new JButton("以客户公司名查询");
	private JButton Bar=new JButton("统计客户所在国家");
	private JButton Pie=new JButton("统计货主所在国家");
	private JButton line=new JButton("产品库存与订购量");
	
	private String CustomerID;//客户ID
	private String CompanyName;//客户公司名
	public Presentation()
	{
		_userBUS = new UserBUS();
		frame.setSize(300,300);
		
		c.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(new JLabel("查询"));
		c.add(titlePanel,"North");
		
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		
		a1.setBounds(10,20,80,20);;
		fieldPanel.add(a1);
		input.setBounds(80,20,180,20);
		fieldPanel.add(input);
		
		Bar.setBounds(70, 60, 140, 20);
		fieldPanel.add(Bar);
		Pie.setBounds(70, 100, 140, 20);
		fieldPanel.add(Pie);
		line.setBounds(70, 140, 140, 20);
		fieldPanel.add(line);
		c.add(fieldPanel,"Center");
		
		Bar.addActionListener(this);
		Pie.addActionListener(this);
		line.addActionListener(this);
		ID.addActionListener(this);
		Name.addActionListener(this);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(ID);
		buttonPanel.add(Name);
		c.add(buttonPanel,"South");
	}
	
	
	public void actionPerformed(ActionEvent e)
	{   
		if(e.getActionCommand()=="统计客户所在国家")  
        {
			JFrame frame=new JFrame("统计客户所在国家");  
			frame.setLayout(new GridLayout(1,2));  
		    frame.add(new BarChart().getChartPanel());           //添加柱形图  
		    frame.setBounds(50, 50, 800, 800);  
		    frame.setVisible(true);
        }
		
		else if(e.getActionCommand()=="统计货主所在国家")  
        {
			JFrame frame=new JFrame("统计货主所在国家");  
			frame.setLayout(new GridLayout(1,2));  
		    frame.add(new PieChart().getChartPanel());           //添加饼状图  
		    frame.setBounds(50, 50, 800, 800);  
		    frame.setVisible(true);
        }
		
		else if(e.getActionCommand()=="产品库存与订购量")  
        {
			JFrame frame=new JFrame("产品库存与订购量");  
			frame.setLayout(new GridLayout(1,2));  
		    frame.add(new LineChart().getChartPanel());           //添加折线图
		    frame.setBounds(50, 50, 800, 800);  
		    frame.setVisible(true);
        }
		
		else if(e.getActionCommand()=="以客户ID查询")  
        {  
        	CustomerID=input.getText();
        	System.out.println(CustomerID);
        	Vector<UserVO> rs=_userBUS.getUserByID(CustomerID);
        	if(rs.size()==0)
        	{
        		JOptionPane.showMessageDialog(null, "No Match Found!", "消息", JOptionPane.INFORMATION_MESSAGE);
        	}
        	else
        	{
        		new resultJframe().init(rs);
        	}
        	
        }	
        else if(e.getActionCommand()=="以客户公司名查询")  
        { 
        	CompanyName=input.getText();
        	System.out.println(CompanyName);
        	Vector<UserVO> rs=_userBUS.getUserByName(CompanyName);
        	if(rs.size()==0)
        	{
        		JOptionPane.showMessageDialog(null, "No Match Found!", "消息", JOptionPane.INFORMATION_MESSAGE);
        	}
        	else
        	{
        		new resultJframe().init(rs);
        	}
 
        }             
	}
	
	public static void main(String[] args)
	{
		new Presentation();
	}
}

class BarChart {  
    ChartPanel frame1;  
    public  BarChart(){  
        CategoryDataset dataset = getDataSet();  
        JFreeChart chart = ChartFactory.createBarChart3D(  
                             "客户所在城市统计", // 图表标题  
                            "城市名", // 目录轴的显示标签  
                            "客户人数", // 数值轴的显示标签  
                            dataset, // 数据集  
                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直  
                            true,           // 是否显示图例(对于简单的柱状图必须是false)  
                            false,          // 是否生成工具  
                            false           // 是否生成URL链接  
                            ); 
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象  
        CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表  
         domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题  
         domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题  
         ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状  
         rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));  
          chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));  
          chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体         
         frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame      
    }  
       private static CategoryDataset getDataSet() 
       {  
           DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
           UserBUS _userBUS = new UserBUS();
           Vector<UserVO> rs=_userBUS.getPopulationOfCountry();
        int i=0;
   		int size=rs.size();
   		while(i<size)
   		{
   			Vector row = new Vector();
   			UserVO uservo=new UserVO();
   			uservo=(((UserVO)rs.get(i)));
   			dataset.addValue(uservo.getPopulation(), uservo.getCountry(), "");
   			i++;
   		}
   		return dataset;
   		}  
       public ChartPanel getChartPanel()
       {  
    	   return frame1;
       }  
}  

class PieChart {  
    ChartPanel frame1;  
    public PieChart(){  
          DefaultPieDataset data = getDataSet();  
          JFreeChart chart = ChartFactory.createPieChart3D("货主所在国家统计",data,true,false,false);  
        //设置百分比  
          PiePlot pieplot = (PiePlot) chart.getPlot();  
          DecimalFormat df = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题  
          NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象  
          StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//获得StandardPieSectionLabelGenerator对象  
          pieplot.setLabelGenerator(sp1);//设置饼图显示百分比  
        
      //没有数据的时候显示的内容  
          pieplot.setNoDataMessage("无数据显示");  
          pieplot.setCircular(false);  
          pieplot.setLabelGap(0.02D);  
        
          pieplot.setIgnoreNullValues(true);//设置不显示空值  
          pieplot.setIgnoreZeroValues(true);//设置不显示负值  
         frame1=new ChartPanel (chart,true);  
          chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体  
          PiePlot piePlot= (PiePlot) chart.getPlot();//获取图表区域对象  
          piePlot.setLabelFont(new Font("宋体",Font.BOLD,10));//解决乱码  
          chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,10));  
    }  
    private static DefaultPieDataset getDataSet() {  
        DefaultPieDataset dataset = new DefaultPieDataset();  
        UserBUS _userBUS = new UserBUS();
        Vector<UserVO> rs=_userBUS.getSupplierOfCountry();
        int i=0;
		int size=rs.size();
		while(i<size)
		{
			Vector row = new Vector();
			UserVO uservo=new UserVO();
			uservo=(((UserVO)rs.get(i)));
			dataset.setValue(uservo.getCountry(),uservo.getPopulation());
			i++;
		}  
        return dataset;  
}  
    public ChartPanel getChartPanel(){  
        return frame1;  
          
    }  
} 

class LineChart {  
    ChartPanel frame1;  
    public LineChart(){  
    	DefaultCategoryDataset linedataset = createDataset();  
        JFreeChart chart = ChartFactory.createLineChart("产品库存与订单量的关系", "产品编号", "数量",linedataset,PlotOrientation.VERTICAL,true, true, true);  
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis dateaxis=plot.getDomainAxis(); 
        
        frame1=new ChartPanel(chart,true);  
        dateaxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题  
        dateaxis.setTickLabelFont(new Font("宋体",Font.BOLD,6));  //垂直标题  
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状  
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));  
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));  
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体  
  
    }   
     private static DefaultCategoryDataset createDataset()
     {  //这个数据集有点多，但都不难理解  
    	 DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
    	 UserBUS _userBUS = new UserBUS();
         Vector<UserVO> rs=_userBUS.getInStockAndOnOrder();
         int i=0;
 		int size=rs.size();
 		while(i<size)
 		{
 			Vector row = new Vector();
 			UserVO uservo=new UserVO();
 			uservo=(((UserVO)rs.get(i)));
 			linedataset.addValue(uservo.getUnitsInStock(), "库存数量",String.valueOf(uservo.getProductID()));
 			linedataset.addValue(uservo.getUnitsOnOrder(), "订购数量",String.valueOf(uservo.getProductID()));
 			i++;
 		} 
 		  return linedataset; 
     }  
      public ChartPanel getChartPanel()
      {  
            return frame1;  
              
        }  
}

class resultJframe {
	private JFrame jFrame;
	private JPanel jPanel;
	private JTable jTable;
	private JScrollPane jScrollPane;
	public void init(Vector rs) {
		jFrame = new JFrame("查询记录");
		jPanel = new JPanel(new BorderLayout());
		Vector content = new Vector();
		int i=0;
		int size=rs.size();
		while(i<size)
		{
			Vector row = new Vector();
			UserVO uservo=new UserVO();
			uservo=(((UserVO)rs.get(i)));
			row.add(uservo.getCustomerID());
			row.add(uservo.getCompanyName());
			row.add(uservo.getContactName());
			row.add(uservo.getContactTitle());
			row.add(uservo.getAddress());
			row.add(uservo.getCity());
			row.add(uservo.getRegion());
			row.add(uservo.getPostalCode());
			row.add(uservo.getCountry());
			row.add(uservo.getPhone());
			row.add(uservo.getFax());
			content.add(row);
			i++;
		}
		Vector column = new Vector();
		column.add("CustomerID");
		column.add("CompanyName");
		column.add("ContactName");
		column.add("ContactTitle");
		column.add("Address");
		column.add("City");
		column.add("Region");
		column.add("PostalCode");
		column.add("Country");
		column.add("Phone");
		column.add("Fax");
		jTable = new JTable(content,column);
		jScrollPane = new JScrollPane(jTable);
		jPanel.add(jScrollPane,BorderLayout.CENTER);
		jFrame.setVisible(true);
		jFrame.setSize(1000,250);
		jFrame.setContentPane(jPanel);
		jFrame.setLocationRelativeTo(null);
		jFrame.setAlwaysOnTop(true);
	}
	
}