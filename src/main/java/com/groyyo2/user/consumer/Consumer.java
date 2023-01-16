/**
 * 
 */
package com.groyyo2.user.consumer;

import java.util.Objects;

import com.google.gson.Gson;
import com.groyyo2.user.model.UserRequest;
import com.groyyo2.user.producer.NotificationProducer;
import com.groyyo2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import lombok.extern.log4j.Log4j2;

/**
 * @author nipunaggarwal
 *
 **/
@Log4j2
@Service
@Lazy(false)
@RequiredArgsConstructor
public class Consumer<T> extends BaseConsumer<T> {

	private final UserService userService;
	@Autowired
	private NotificationProducer notificationProducer;


	@KafkaListener(topics = { "${kafka.base.topic}" }, idIsGroup = false, id = "kafka-base-topic")
	public void receive(ConsumerRecords<String, String> records) {

		if (records != null && !records.isEmpty()) {

			log.info("Received " + records.count() + " record(s) to process");

			try {

				for (ConsumerRecord<String, String> record : records) {
					log.info("Data consumed is {}", record);

					try {
						if (Objects.nonNull(record.value())) {
							System.out.println(record.key());
							System.out.println(record.value());
							MsgUserResp data = new Gson().fromJson(record.value(), MsgUserResp.class);
							UserRequest userRequest = UserRequest.builder().emailId("test@gmail.com").fullName(data.getFirstName() + " " + data.getLastName()).firstName(data.getFirstName()).phone("9071333393").build();
							userService.createUser(userRequest);
							// do something



							notificationProducer.publish("user-service-2", userRequest.getEmailId(), userRequest);

						} else {
							log.warn("Warning message");
						}
					} catch (Exception ex) {
						log.error("Error message ", ex);
					}

				}
			} catch (Exception e) {
				log.error("Error message : ", e);
			}

		}

	}
}