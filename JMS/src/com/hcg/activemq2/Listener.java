package com.hcg.activemq2;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ��Ϣ���� - ������1
 *
 * @author babylon
 * 2016-5-9
 */
public class Listener implements MessageListener{

	/*
	 * �յ�����Ϣ
	 */
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("������1�յ�����Ϣ��"+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
