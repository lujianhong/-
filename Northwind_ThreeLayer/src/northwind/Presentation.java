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
	
	private JFrame frame = new JFrame("��ѯ");
	private Container c = frame.getContentPane();
	private JTextField input = new JTextField();
	JLabel a1 = new JLabel("������Ϣ:");
	private JButton ID = new JButton("�Կͻ�ID��ѯ");
	private JButton Name = new JButton("�Կͻ���˾����ѯ");
	private JButton Bar=new JButton("ͳ�ƿͻ����ڹ���");
	private JButton Pie=new JButton("ͳ�ƻ������ڹ���");
	private JButton line=new JButton("��Ʒ����붩����");
	
	private String CustomerID;//�ͻ�ID
	private String CompanyName;//�ͻ���˾��
	public Presentation()
	{
		_userBUS = new UserBUS();
		frame.setSize(300,300);
		
		c.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(new JLabel("��ѯ"));
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
		if(e.getActionCommand()=="ͳ�ƿͻ����ڹ���")  
        {
			JFrame frame=new JFrame("ͳ�ƿͻ����ڹ���");  
			frame.setLayout(new GridLayout(1,2));  
		    frame.add(new BarChart().getChartPanel());           //�������ͼ  
		    frame.setBounds(50, 50, 800, 800);  
		    frame.setVisible(true);
        }
		
		else if(e.getActionCommand()=="ͳ�ƻ������ڹ���")  
        {
			JFrame frame=new JFrame("ͳ�ƻ������ڹ���");  
			frame.setLayout(new GridLayout(1,2));  
		    frame.add(new PieChart().getChartPanel());           //��ӱ�״ͼ  
		    frame.setBounds(50, 50, 800, 800);  
		    frame.setVisible(true);
        }
		
		else if(e.getActionCommand()=="��Ʒ����붩����")  
        {
			JFrame frame=new JFrame("��Ʒ����붩����");  
			frame.setLayout(new GridLayout(1,2));  
		    frame.add(new LineChart().getChartPanel());           //�������ͼ
		    frame.setBounds(50, 50, 800, 800);  
		    frame.setVisible(true);
        }
		
		else if(e.getActionCommand()=="�Կͻ�ID��ѯ")  
        {  
        	CustomerID=input.getText();
        	System.out.println(CustomerID);
        	Vector<UserVO> rs=_userBUS.getUserByID(CustomerID);
        	if(rs.size()==0)
        	{
        		JOptionPane.showMessageDialog(null, "No Match Found!", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
        	}
        	else
        	{
        		new resultJframe().init(rs);
        	}
        	
        }	
        else if(e.getActionCommand()=="�Կͻ���˾����ѯ")  
        { 
        	CompanyName=input.getText();
        	System.out.println(CompanyName);
        	Vector<UserVO> rs=_userBUS.getUserByName(CompanyName);
        	if(rs.size()==0)
        	{
        		JOptionPane.showMessageDialog(null, "No Match Found!", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
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
                             "�ͻ����ڳ���ͳ��", // ͼ�����  
                            "������", // Ŀ¼�����ʾ��ǩ  
                            "�ͻ�����", // ��ֵ�����ʾ��ǩ  
                            dataset, // ���ݼ�  
                            PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ  
                            true,           // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)  
                            false,          // �Ƿ����ɹ���  
                            false           // �Ƿ�����URL����  
                            ); 
        CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������  
        CategoryAxis domainAxis=plot.getDomainAxis();         //ˮƽ�ײ��б�  
         domainAxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����  
         domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����  
         ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״  
         rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));  
          chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));  
          chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������         
         frame1=new ChartPanel(chart,true);        //����Ҳ������chartFrame,����ֱ������һ��������Frame      
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
          JFreeChart chart = ChartFactory.createPieChart3D("�������ڹ���ͳ��",data,true,false,false);  
        //���ðٷֱ�  
          PiePlot pieplot = (PiePlot) chart.getPlot();  
          DecimalFormat df = new DecimalFormat("0.00%");//���һ��DecimalFormat������Ҫ������С������  
          NumberFormat nf = NumberFormat.getNumberInstance();//���һ��NumberFormat����  
          StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//���StandardPieSectionLabelGenerator����  
          pieplot.setLabelGenerator(sp1);//���ñ�ͼ��ʾ�ٷֱ�  
        
      //û�����ݵ�ʱ����ʾ������  
          pieplot.setNoDataMessage("��������ʾ");  
          pieplot.setCircular(false);  
          pieplot.setLabelGap(0.02D);  
        
          pieplot.setIgnoreNullValues(true);//���ò���ʾ��ֵ  
          pieplot.setIgnoreZeroValues(true);//���ò���ʾ��ֵ  
         frame1=new ChartPanel (chart,true);  
          chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������  
          PiePlot piePlot= (PiePlot) chart.getPlot();//��ȡͼ���������  
          piePlot.setLabelFont(new Font("����",Font.BOLD,10));//�������  
          chart.getLegend().setItemFont(new Font("����",Font.BOLD,10));  
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
        JFreeChart chart = ChartFactory.createLineChart("��Ʒ����붩�����Ĺ�ϵ", "��Ʒ���", "����",linedataset,PlotOrientation.VERTICAL,true, true, true);  
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis dateaxis=plot.getDomainAxis(); 
        
        frame1=new ChartPanel(chart,true);  
        dateaxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����  
        dateaxis.setTickLabelFont(new Font("����",Font.BOLD,6));  //��ֱ����  
        ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״  
        rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));  
        chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));  
        chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������  
  
    }   
     private static DefaultCategoryDataset createDataset()
     {  //������ݼ��е�࣬�����������  
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
 			linedataset.addValue(uservo.getUnitsInStock(), "�������",String.valueOf(uservo.getProductID()));
 			linedataset.addValue(uservo.getUnitsOnOrder(), "��������",String.valueOf(uservo.getProductID()));
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
		jFrame = new JFrame("��ѯ��¼");
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