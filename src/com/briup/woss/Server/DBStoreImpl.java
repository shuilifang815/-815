package com.briup.woss.Server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import com.briup.util.BIDR;
import com.briup.util.BackUP;
import com.briup.util.Configuration;
import com.briup.util.Logger;
import com.briup.woss.ConfigurationAWare;
import com.briup.woss.server.DBStore;

public class DBStoreImpl implements DBStore,ConfigurationAWare{
	/*
	 * driver=oracle.jdbc.driver.OracleDriver
	 * url=jdbc:oracle:thin:@localhost:1521:XE username=oracle password=orcle
	 */
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static Connection connection;
	private String aaa_login_name;
	private String login_ip;
	private java.sql.Date login_date;
	private java.sql.Date logout_date;
	private String nas_ip;
	private String time_duration;
	private int i_date;
	private static PreparedStatement[] ps = new PreparedStatement[31];;
	String sql;
	static int i = 0;
	private static String pathName = "src/com/briup/woss/File/list.txt";
	Configuration conf=null;
	// �����������
	static List<BIDR> list = new ArrayList<>();
	@Override
	public void init(Properties p) {
		driver=p.getProperty("driver");
		url=p.getProperty("url");
		username=p.getProperty("username");
		password=p.getProperty("password");
	}
	@Override
	public void saveToDB(Collection<BIDR> collction) throws Exception {
		BackUP bi=conf.getBackup();
		Logger log=conf.getLogger();
		try {
			// ע������
			Class.forName(driver);
			// ��������
			connection = DriverManager.getConnection(url,username,password);
			System.out.println(connection);
			for (int i = 0; i < 31; i++) {

				sql = "insert into t_detail_" + i
						+ "(aaa_login_name,login_ip,login_date,logout_date,nas_ip,time_duration) "
						+ "values(?,?,?,?,?,?)";
				ps[i] = connection.prepareStatement(sql);
			}
			//connection��ഴ��300��prepareStatement
			//��ps = connection.prepareStatement(sql);���������for (BIDR bidr : collction)������������ڱ����������࣬����ֳ����α����ֵ�쳣
        	//����31�Σ����������bidr����ʱ���洢��Ϣʱ��ps���Ӧ
			for (BIDR bidr : collction) {
				Timestamp login_d = bidr.getLogin_date();
				String s_date = login_d.toString();
				String[] str1 = s_date.split(" ");
				String[] str2 = str1[0].split("-");
				i_date = Integer.parseInt(str2[2]);
				aaa_login_name = bidr.getAAA_login_name();
				login_ip = bidr.getLogin_ip();
				login_date = new java.sql.Date(bidr.getLogin_date().getTime());
				logout_date = new java.sql.Date(bidr.getLogout_date().getTime());
				// ͨ��PreparedStatement����Ϣ�洢�����ݿ���
				ps[i_date].setString(1, aaa_login_name);
				ps[i_date].setString(2, login_ip);
				ps[i_date].setDate(3, login_date);
				ps[i_date].setDate(4, logout_date);
				ps[i_date].setString(5, nas_ip);
				ps[i_date].setString(6, time_duration);
				// ִ��sql
				ps[i_date].executeUpdate();
				i++;
				if (i == 200) {
					i = 1 / 0;
				}
				list.add(bidr);
				System.out.println("�������ݳɹ���");
			}

			log.info("������ݵĸ���" + list.size());
			collction.removeAll(list);
			log.info("δ������ݵĸ���" + collction.size());

		} catch (Exception e) {
	 		connection.rollback();
			bi.store(pathName, list, BackUP.STORE_OVERRIDE);//����.�ֶλ���ֻ�ɫ�����ߣ����Ըĳ�����.�ֶ�
			log.debug("��������Ϊ"+list.size());
			log.debug("δ������ݵĸ���" + collction.size());
			log.debug("�������Ϊ��"+i);
			
		}

	}

	/*public static void main(String[] args) throws Exception {
		GatherImpl ga = new GatherImpl();
		new DBStoreImpl().saveToDB(ga.gather());
		System.out.println("�������ݵĸ���Ϊ��" + list.size());
		System.out.println(i);
	}*/
	@Override
	public void setConfiguration(Configuration co) {
		// TODO Auto-generated method stub
		this.conf=co;
		
	}

}
