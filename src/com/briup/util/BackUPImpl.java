package com.briup.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Properties;

public class BackUPImpl implements BackUP {

	String filePath;
	Object object = null;
	private static List<BIDR> list;

	@Override
	public void init(Properties p) {
		// TODO Auto-generated method stub

	}

	// ͨ��������ȡ�Ѿ����ݵ����� key�������ݵļ�
	@Override
	public Object load(String key, boolean flag) throws Exception {
		// TODO Auto-generated method stub
		File file = new File(filePath, key);
		// file�Ƿ�Ϊ��
		if (file.exists() && file.length() != 0) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			object = ois.readObject();
		}
		return object;

	}

	// ͨ�������洢���ݣ�key-�������ݵļ���date-��Ҫ���ݵ����ݣ�flag�����ֵ�Ѿ��������ݣ�׷�ӻ��Ǹ���֮ǰ������
	// �ļ�·��key
	@Override
	public void store(String key, Object date, boolean flag) throws Exception {
		// TODO Auto-generated method stub
		// ͨ��key�ұ����ļ�����ֵ�����ļ���
		// ���յ���������
		File file = new File(key);
		if(file.exists()){
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, flag));
		oos.writeObject(date);
		oos.flush();
		oos.close();
		/*
		 * File file=new File(filePath,fileName); �ж��ǲ��Ǵ��� if(!file.exits()){
		 * file.createNewFile(); } ObjectOutputStream oos=new
		 * ObjectOutputStream(new FileOutputStream(file,flag));
		 * oos.writeObject(date);
		 * 
		 */

	}
	}

}
