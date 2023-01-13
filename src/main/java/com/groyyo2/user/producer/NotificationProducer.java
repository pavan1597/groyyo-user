/**
 * 
 */
package com.groyyo2.user.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Objects;

/**
 * @author nipunaggarwal
 *
 * 
 *
 **/
@Log4j2
@Service
public class NotificationProducer {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void publish(String topic, String className, Object value) {

		ProducerRecord<String, String> record = null;

		try {
			record = new ProducerRecord<String, String>(topic, className, objectMapper.writeValueAsString(value));
		} catch (Exception e) {
			log.error("Failed to serialize message to send to topic: " + topic, e);
		}

		publishRecord(record);
	}

	public void publish(String topic, int partition, String className, Object value) {

		ProducerRecord<String, String> record = null;
		try {
			record = new ProducerRecord<String, String>(topic, partition, className, objectMapper.writeValueAsString(value));
		} catch (Exception e) {
			log.error("Failed to serialize message to send to topic: " + topic, e);
		}

		publishRecord(record);
	}

	private void publishRecord(ProducerRecord<String, String> record) {

		if (Objects.nonNull(record)) {

			String messageId = "af7c1fe6-d669-414e-b066-e9733f0de7a8";
			record.headers().add("messageId", messageId.getBytes());

			log.info("Publishing records: {}", record);

			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(record);

			future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

				@Override
				public void onSuccess(SendResult<String, String> result) {

					if (log.isDebugEnabled()) {

						log.debug("Sent Message=[topic: " + record.topic() + ", partition: " + record.partition() + ", messageId: " + messageId + "] with offset="
								+ result.getRecordMetadata().offset() + " and timestamp= " + result.getRecordMetadata().timestamp());

					}
				}

				@Override
				public void onFailure(Throwable ex) {
					log.error("Unable to send message=[" + record.value() + "] due to : " + ex.getMessage());

				}
			});
		}
	}



	public void publish(String topic, String className, Object value ,String messageId) {

		ProducerRecord<String, String> record = null;

		try {
			record = new ProducerRecord<String, String>(topic, className, objectMapper.writeValueAsString(value));
		} catch (Exception e) {
			log.error("Failed to serialize message to send to topic: " + topic, e);
		}

		publishRecord(record,messageId);
	}

	public void publish(String topic, int partition, String className, Object value,String messageId) {

		ProducerRecord<String, String> record = null;
		try {
			record = new ProducerRecord<String, String>(topic, partition, className, objectMapper.writeValueAsString(value));
		} catch (Exception e) {
			log.error("Failed to serialize message to send to topic: " + topic, e);
		}

		publishRecord(record,messageId);
	}



	private void publishRecord(ProducerRecord<String, String> record,String messageId) {

		if (Objects.nonNull(record)) {

			record.headers().add("messageId", messageId.getBytes());

			log.info("Publishing records: {}", record);

			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(record);

			future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

				@Override
				public void onSuccess(SendResult<String, String> result) {

					if (log.isDebugEnabled()) {

						log.debug("Sent Message=[topic: " + record.topic() + ", partition: " + record.partition() + ", messageId: " + messageId + "] with offset="
								+ result.getRecordMetadata().offset() + " and timestamp= " + result.getRecordMetadata().timestamp());

					}
				}

				@Override
				public void onFailure(Throwable ex) {
					log.error("Unable to send message with message id =["+ messageId +"] and message=[" + record.value() + "] due to : " + ex.getMessage());

				}
			});
		}
	}


}