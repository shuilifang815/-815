package com.briup.woss.main;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jTest {
	public static void main(String[] args) {
		//����log4j����
		//getRootLogger()
		//getLogger(Log4jTest.class)
		//����
		Logger logger=Logger.getRootLogger();
		//��ʼ��log4j������Ϣ
		//BasicConfigurator.configure();
		//��ʼ��
		PropertyConfigurator.configure("src/com/briup/woss/File/log4j.properties");
		//debug info warn error
		//������־����
		logger.setLevel(Level.INFO);
		logger.debug("log4j--debug");
		logger.info("log4j--info");
		logger.warn("log4j--warn");
		logger.error("log4j--error");
	}

}
