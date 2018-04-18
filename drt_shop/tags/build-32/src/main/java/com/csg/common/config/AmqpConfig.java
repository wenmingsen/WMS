package com.csg.common.config;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AmqpConfig {

	private Logger logger = LoggerFactory.getLogger(AmqpConfig.class);
	public static final String EXCHANGEKEY = "yx.exchange";
	public static final String ROUTINGKEY = "yx.routing";
	public static final String QUEUENAMEKEY = "yx.queue.name";
	public static final String QUEUENAME = "drt-yx-queue";
	public static JSONObject queueNameJson=null;

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(AppConfig.getProperty("rabbitMQ.ip"));
		connectionFactory.setUsername(AppConfig.getProperty("rabbitMQ.username"));
		connectionFactory.setPassword(AppConfig.getProperty("rabbitMQ.password"));
		connectionFactory.setChannelCacheSize(Integer.parseInt(AppConfig.getProperty("rabbitMq.ChannelCacheSize", "100")));
		connectionFactory.setVirtualHost("/");
		connectionFactory.setPublisherConfirms(true); // 必须要设置才能进行消息的回调。
		return connectionFactory;
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	// 必须是prototype类型
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate();
		ConnectionFactory conn = connectionFactory();
		template.setConnectionFactory(conn);
		template.setMandatory(true);
		return template;
	}

	@Bean
	public DirectExchange defaultExchange() {
		return new DirectExchange(AppConfig.getProperty(EXCHANGEKEY));
	}

	/*
	 * @Bean public Queue queue() { return new Queue("spring-boot-queue", true);
	 * // 队列持久 }
	 */
	@Bean
	public List<Queue> queues() {
		List<Queue> list = new ArrayList<Queue>();
		String queueName = AppConfig.getProperty(QUEUENAMEKEY);
		if (queueName != null && !queueName.isEmpty()) {
			try {
				queueNameJson = JSONObject.fromObject(queueName);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}
		if (queueNameJson != null) {
			for (Object str : queueNameJson.keySet()) {
				list.add(new Queue(QUEUENAME + "-"
						+ queueNameJson.getString(str.toString()), true)); // 队列持久
			}
		}

		return list;
	}

	/*
	 * @Bean public Queue queue2() { return new Queue("spring-boot-queue2",
	 * true); // 队列持久
	 * 
	 * }
	 */

	@Bean
	public List<Binding> binding() {
		List<Binding> list = new ArrayList<Binding>();
		List<Queue> queues = queues();
		if (!queues.isEmpty()) {
			for (Queue queue : queues) {
				String name = queue.getName();
				name = name
						.replace(AmqpConfig.QUEUENAME, AppConfig.getProperty(ROUTINGKEY));
				list.add(BindingBuilder.bind(queue).to(defaultExchange())
						.with(name));
			}
		}

		// list.add(BindingBuilder.bind(queue2()).to(defaultExchange())
		// .with(AmqpConfig.ROUTINGKEY2));
		return list;
	}

	// @Bean
	// public SimpleMessageListenerContainer messageContainer() {
	// SimpleMessageListenerContainer container = new
	// SimpleMessageListenerContainer(
	// connectionFactory());
	// container.setQueues(new Queue(QUEUENAME+"-1",true));
	// container.setExposeListenerChannel(true);
	// container.setMaxConcurrentConsumers(1);
	// container.setConcurrentConsumers(1);
	// container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置确认模式手工确认
	// container.setMessageListener(new ChannelAwareMessageListener() {
	// @Override
	// public void onMessage(Message message, Channel channel)
	// throws Exception {
	// byte[] body = message.getBody();
	// try {
	// Thread.sleep(3000);
	// } catch (Exception e) {
	//
	// }
	//
	// System.out.println("receive msg : " + new String(body));
	// channel.basicAck(message.getMessageProperties()
	// .getDeliveryTag(), false); // 确认消息成功消费
	// }
	//
	// });
	// return container;
	// }
}
