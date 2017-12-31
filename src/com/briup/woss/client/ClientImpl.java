package com.briup.woss.client;

import java.io.*;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import com.briup.util.BIDR;
import com.briup.util.Configuration;
import com.briup.woss.ConfigurationAWare;

public class ClientImpl implements Client{
   private static String id;
   private static int port;
  
	
	private static ObjectOutputStream oo;

	@Override
	public void init(Properties p) {
		// TODO Auto-generated method stub
		id=p.getProperty("id");
	    System.out.println(p.getProperty("port"));
		port=Integer.parseInt(p.getProperty("port"));
		
		
		
	}
	
     
	@Override
	public void send(Collection<BIDR> collection) throws Exception {
		Socket socket=new Socket(id,port);
		
		//������
		OutputStream os=socket.getOutputStream();
		oo=new ObjectOutputStream(os);//�����ݱ����ڶ�������
		oo.writeObject(collection);
		System.out.println(collection);
		oo.flush();
		oo.close();
		socket.close();
	    
		
	//����Socketָ��ip��ַ�˿ں�
	// TODO Auto-generated method stub
	//1.id+port
	//2.������
		

	}


	
	}

