package com.briup.woss.main;

import java.util.Collection;
import com.briup.util.BIDR;
import com.briup.util.ConfigurationImpl;
import com.briup.util.Logger;
import com.briup.woss.server.DBStore;
import com.briup.woss.server.Server;

public class ServerTest {
	public static void main(String[] args) throws Exception {
		ConfigurationImpl conf = new ConfigurationImpl();
		Logger log = conf.getLogger();
		try {
			//1.����������
			log.info("����������");
			Server serverImpl = conf.getServer();
			log.info("���������");
			//2.��������
			log.info("�ȴ���������");
			Collection<BIDR> bidrs = serverImpl.revicer();
			DBStore dbStore = conf.getDBStore();
			log.info("��ʼ�������");
			dbStore.saveToDB(bidrs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("�������������쳣������������");
			
		}
		//2.��������
		
		/*ConfigurationImpl conf=new ConfigurationImpl();
		try {
			//����������
			new ServerTest().Accept();
//		    DBStoreImpl db=new DBStoreImpl();
			DBStore db=conf.getDBStore();
		    System.out.println(db);
			db.saveToDB(bidr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void Accept() throws Exception{
		ConfigurationImpl conf=new ConfigurationImpl();
		//�������ݣ�
//		ServrImpl si=new ServrImpl();
		Server si = conf.getServer();
		
		System.out.println(si);
		try {
			 bidr=(List<BIDR>) si.revicer();
			 //fos=new FileOutputStream("src/a.txt");
			System.out.println("��������");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
}
}
