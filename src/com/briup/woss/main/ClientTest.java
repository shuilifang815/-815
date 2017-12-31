package com.briup.woss.main;
import java.util.Collection;
import com.briup.util.BIDR;
import com.briup.util.ConfigurationImpl;
import com.briup.util.Logger;
import com.briup.woss.client.Client;
import com.briup.woss.client.Gather;

public class ClientTest {
	
	public static void main(String[] args) throws Exception {
		ConfigurationImpl confi=new ConfigurationImpl();
		Logger log=confi.getLogger();
		try {
			// 1.�ɼ�����
			log.debug("��ʼ�ɼ�����");
			Gather gi=confi.getGather();
			Collection<BIDR> bidrs=gi.gather();
			//2.������������
			log.debug("������������");
			Client ci=confi.getClient();
			//3.��������
			log.debug("���ݿ�ʼ����");
			ci.send(bidrs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("�������������쳣");
		}

	}

}
