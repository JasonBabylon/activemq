package com.hcg.activemq2;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 消息监听 - 订阅者2
 *
 * @author babylon
 * 2016-5-9
 */
public class Listener2 implements MessageListener{

	/*
	 * 收到的消息
	 */
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("订阅者2收到的消息："+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
