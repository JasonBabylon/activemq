package com.hcg.activemq2;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * ��Ϣ������ - ��Ϣ������1
 * @author babylon
 * 2016-5-9
 */
public class JMSConsumer {
	
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;				// Ĭ�ϵ������û���
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;	// Ĭ�ϵ���������
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;	// Ĭ�ϵ����ӵ�ַ
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;   // ���ӹ���
		Connection connection = null; 	// ����
		Session session;				// �Ự�����ջ��߷�����Ϣ���߳�
		Destination destination;	// ��Ϣ��Ŀ�ĵ�
		MessageConsumer messageConsumer;	// ��Ϣ������
		
		// ʵ�������ӹ���
		connectionFactory = new ActiveMQConnectionFactory(JMSConsumer.USERNAME, JMSConsumer.PASSWORD, JMSConsumer.BROKEURL);
		try {
			connection = connectionFactory.createConnection();		// ͨ�����ӹ�����ȡ����
			connection.start();		// ��������
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);	// �����Ự����������
			// destination = session.createQueue("FirstQueue1");	// ������Ϣ����
			destination = session.createTopic("FirstTopic1");
			messageConsumer = session.createConsumer(destination);		// ������Ϣ������
			messageConsumer.setMessageListener(new Listener());			// ע����Ϣ����
		} catch (JMSException e) {
			e.printStackTrace();
		} 
	}

}
