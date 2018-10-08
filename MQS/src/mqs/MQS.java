package mqs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.common.utils.ServiceSettings;
import com.aliyun.mns.model.QueueMeta;
import com.aliyun.mns.model.Message;
import java.lang.String;

public class MQS extends JFrame
{
	public static void main(String[] args)
	{
		CreateQueue.create();
		frame();
	}
	static void frame()
	{
		JFrame f = new JFrame("MQS");
		f.setBounds(400, 200, 400, 310);
		f.setLayout(null);
		
        JTextArea t_send=new JTextArea();
        t_send.setBounds(20, 10, 100, 100);
        t_send.setLineWrap(true);
        f.add(t_send);
        JButton send = new JButton("发送消息1");
        send.setBounds(140, 20, 100, 30);
        f.add(send);
        
        JTextArea t_receive=new JTextArea();
        t_receive.setBounds(260, 10, 100, 100);
        t_receive.setLineWrap(true);
        f.add(t_receive);
        JButton receive = new JButton("接收消息1");
        receive.setBounds(140, 60, 100, 30);
        f.add(receive);
        
        JTextArea t_send_2=new JTextArea();
        t_send_2.setBounds(20, 150, 100, 100);
        t_send_2.setLineWrap(true);
        f.add(t_send_2);
        JButton send_2 = new JButton("发送消息2");
        send_2.setBounds(140, 160, 100, 30);
        f.add(send_2);
        
        JTextArea t_receive_2=new JTextArea();
        t_receive_2.setBounds(260, 150, 100, 100);
        t_receive_2.setLineWrap(true);
        f.add(t_receive_2);
        JButton receive_2 = new JButton("接收消息2");
        receive_2.setBounds(140, 200, 100, 30);
        f.add(receive_2);
        
        JButton receive_3 = new JButton("同时接收消息");
        receive_3.setBounds(130, 110, 120, 30);
        f.add(receive_3);
        
        send.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {

                //获取文本框中的文本
                String s_str = t_send.getText().trim();
                t_send.setText("");
                Producer.producer(s_str);
            }
        });
        receive.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {

                //获取队列中的消息
                String s_str = Comsumer.comsumer();
                t_receive.setText(s_str);
            }
        });
        
        send_2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {

                //获取文本框中的文本
                String s_str = t_send_2.getText().trim();
                t_send_2.setText("");
                Producer.producer(s_str);
            }
        });
        receive_2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {

                //获取队列中的消息
                String s_str = Comsumer.comsumer();
                t_receive_2.setText(s_str);
            }
        });
        
        receive_3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {

                //获取队列中的消息
                String s_str = Comsumer.comsumer();
                t_receive.setText(s_str);
                t_receive_2.setText(s_str);
            }
        });
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
	}
}


class CreateQueue {
    static void create() {
        CloudAccount account = new CloudAccount(
            ServiceSettings.getMNSAccessKeyId(),
            ServiceSettings.getMNSAccessKeySecret(),
            ServiceSettings.getMNSAccountEndpoint());
        MNSClient client = account.getMNSClient(); //this client need only initialize once
        try
        {   //Create Queue
            QueueMeta qMeta = new QueueMeta();
            qMeta.setQueueName("cloud-queue-demo");
            qMeta.setPollingWaitSeconds(30);//use long polling when queue is empty.
            CloudQueue cQueue = client.createQueue(qMeta);
            System.out.println("Create queue successfully. URL: " + cQueue.getQueueURL());
        } catch (ClientException ce)
        {
            System.out.println("Something wrong with the network connection between client and MNS service."
                    + "Please check your network and DNS availablity.");
            ce.printStackTrace();
        } catch (ServiceException se)
        {
            if (se.getErrorCode().equals("QueueNotExist"))
            {
                System.out.println("Queue is not exist.Please create before use");
            } else if (se.getErrorCode().equals("TimeExpired"))
            {
                System.out.println("The request is time expired. Please check your local machine timeclock");
            }
            se.printStackTrace();
        } catch (Exception e)
        {
            System.out.println("Unknown exception happened!");
            e.printStackTrace();
        }
        client.close();
    }
}
class Producer {

    static void producer(String s_str) {
        CloudAccount account = new CloudAccount(
                ServiceSettings.getMNSAccessKeyId(),
                ServiceSettings.getMNSAccessKeySecret(),
                ServiceSettings.getMNSAccountEndpoint());
        MNSClient client = account.getMNSClient(); //this client need only initialize once
        try{
            CloudQueue queue = client.getQueueRef("cloud-queue-demo");// replace with your queue name
            
            Message message = new Message();
            message.setMessageBody(s_str); // use your own message body here
            Message putMsg = queue.putMessage(message);
            System.out.println("Send message id is: " + putMsg.getMessageId());

        } catch (ClientException ce)
        {
            System.out.println("Something wrong with the network connection between client and MNS service."
                    + "Please check your network and DNS availablity.");
            ce.printStackTrace();
        } catch (ServiceException se)
        {
            if (se.getErrorCode().equals("QueueNotExist"))
            {
                System.out.println("Queue is not exist.Please create before use");
            } else if (se.getErrorCode().equals("TimeExpired"))
            {
                System.out.println("The request is time expired. Please check your local machine timeclock");
            }
            se.printStackTrace();
        } catch (Exception e)
        {
            System.out.println("Unknown exception happened!");
            e.printStackTrace();
        }

        client.close();
    }
}

class Comsumer
{
    static String comsumer() 
    {
        CloudAccount account = new CloudAccount(
                ServiceSettings.getMNSAccessKeyId(),
                ServiceSettings.getMNSAccessKeySecret(),
                ServiceSettings.getMNSAccountEndpoint());
        MNSClient client = account.getMNSClient(); //this client need only initialize once
        String receive=null;
        try{
            CloudQueue queue = client.getQueueRef("cloud-queue-demo");// replace with your queue name
            Message popMsg = queue.popMessage();
            if (popMsg != null){
                System.out.println("message handle: " + popMsg.getReceiptHandle());
                System.out.println("message body: " + popMsg.getMessageBodyAsString());
                System.out.println("message id: " + popMsg.getMessageId());
                System.out.println("message dequeue count:" + popMsg.getDequeueCount());
                queue.deleteMessage(popMsg.getReceiptHandle());
                System.out.println("delete message successfully.\n");

                receive=popMsg.getMessageBodyAsString();
            }
            else
            	receive="队列中没有消息了";
        } catch (ClientException ce)
        {
            System.out.println("Something wrong with the network connection between client and MNS service."
                    + "Please check your network and DNS availablity.");
            ce.printStackTrace();
        } catch (ServiceException se)
        {
            if (se.getErrorCode().equals("QueueNotExist"))
            {
                System.out.println("Queue is not exist.Please create queue before use");
            } else if (se.getErrorCode().equals("TimeExpired"))
            {
                System.out.println("The request is time expired. Please check your local machine timeclock");
            }
            se.printStackTrace();
        } catch (Exception e)
        {
            System.out.println("Unknown exception happened!");
            e.printStackTrace();
        }
        client.close();
        return receive;
    }
}
