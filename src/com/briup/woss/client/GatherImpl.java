package com.briup.woss.client;

import java.io.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import com.briup.util.BIDR;
import com.briup.util.BackUP;
import com.briup.util.Configuration;
import com.briup.woss.ConfigurationAWare;

public class GatherImpl implements Gather,ConfigurationAWare{
	String pathName;
	String pathName1="src/com/briup/woss/File/map.txt";
	//list������������
	static List<BIDR> list=new ArrayList<BIDR>();
   //�洢����������Map<IP,BIDR>
	static Map<String,BIDR> map=new HashMap();
	Configuration conf=null;
	//���ر����ļ�
	@Override
	public void init(Properties p) {
		// TODO Auto-generated method stub
		pathName=(String) p.get("src-file");
	}

	@Override
	public Collection<BIDR> gather() throws Exception {
		// TODO Auto-generated method stub
		BackUP bi=conf.getBackup();
		Map<String, BIDR> newMap=(Map<String,BIDR>)bi.load(pathName1, bi.LOAD_REMOVE);
		if(newMap!=null){
			map.putAll(newMap);
		}
		//1.��temp.txt,�ɹ��ı�־�Ǵ�ӡ������̨
		BufferedReader br=new BufferedReader(new FileReader(new File(pathName)));
		BIDR bidr=new BIDR();
		String str="";
		while((str=br.readLine())!=null){
			//System.out.println(str);
			String[] line=str.split("[|]");
				//System.out.println(line.length);
				if(line[2].equals("7")){
					bidr=new BIDR();
					bidr.setAAA_login_name(line[0].substring(1));
					bidr.setNAS_ip(line[1]);
					Long login_date=Long.parseLong(line[3]);
					Timestamp login_time=new Timestamp(login_date*1000);
					bidr.setLogin_date(login_time);
					bidr.setLogin_ip(line[4]);	
					//���治������Ϣ
					map.put(line[4],bidr);
					}else if(line[2].equals("8")){
						BIDR bidr1 = map.get(line[4]);
						if(bidr1!=null){
							//�����û�����ʱ�� 
							Long logout_date=Long.parseLong(line[3]);
							Timestamp logout_time=new Timestamp(logout_date*1000);
							bidr1.setLogout_date(logout_time);
							//������û�����ʱ��
							Integer time_deration=(int) (logout_date - (bidr1.getLogin_date().getTime())/1000);
							bidr1.setTime_deration(time_deration);
							//�������ݴ���list
							list.add(bidr1);
							map.remove(line[4]);
						}	
						
					}
		}
		bi.store(pathName1, map, bi.STORE_OVERRIDE);
		System.out.println("���ݵĲ���������Ϊ��"+map.size());
		br.close();
		System.out.println("list="+list.size());
		return list;
		
		
	}
	public static void main(String[] args) throws Exception {
		new GatherImpl().gather();
		/*for(BIDR bidr:list){
			System.out.println(bidr.getAAA_login_name()+","+bidr.getLogin_ip()+" "+bidr.getNAS_ip()+" "+bidr.getTime_deration()+","+bidr.getLogin_date()+","+bidr.getLogout_date());
		}*/
		System.out.println("��������ϢΪ"+list.size());
		System.out.println("��������Ϣ��"+map.size());
	}

	@Override
	public void setConfiguration(Configuration co) {
		// TODO Auto-generated method stub
		this.conf=co;
	}

}
