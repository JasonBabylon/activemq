package com.hcg.activemq2;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * ��Ϣ������ - ��Ϣ������
 * 
 * @author babylon
 * 2016-5-9
 */
public class JMSProducer {
	
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;				// Ĭ�ϵ������û���
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;	// Ĭ�ϵ���������
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;	// Ĭ�ϵ����ӵ�ַ
	private static final int SENDNUM = 10;	// ���͵���Ϣ����
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;   // ���ӹ���
		Connection connection = null; 	// ����
		Session session;				// �Ự�����ջ��߷�����Ϣ���߳�
		Destination destination;	// ��Ϣ��Ŀ�ĵ�
		MessageProducer messageProducer;	// ��Ϣ������
		
		// ʵ�������ӹ���
		connectionFactory = new ActiveMQConnectionFactory(JMSProducer.USERNAME, JMSProducer.PASSWORD, JMSProducer.BROKEURL);
		try {
			connection = connectionFactory.createConnection();		// ͨ�����ӹ�����ȡ����
			connection.start();		// ��������
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);	// �����Ự
			// destination = session.createQueue("FirstQueue1");	// ������Ϣ����
			destination = session.createTopic("FirstTopic1");
			messageProducer = session.createProducer(destination);		// ������Ϣ������
			// ������Ϣ
			sendMessage(session, messageProducer);
		    // ��ʽ�ύ������Ϣ�Ĳ���
			session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		}  finally {
			// �ر�����
			if(connection!=null){
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * ��������Ϣ
	 * @param session
	 * @param messageProducer
	 * @throws JMSException 
	 */
	public static void sendMessage(Session session, MessageProducer messageProducer) throws JMSException{
		for(int i=0; i < JMSProducer.SENDNUM; i++){
			TextMessage message = session.createTextMessage("ActiveMQ  ���͵���Ϣ"+i);
			System.out.println("������Ϣ��"+i);
			messageProducer.send(message);
		}
	}

}
