package org.example.sample.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.kafka.service.KafkaConsumerService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {

    private final KafkaConsumerService kafkaConsumerService;

//    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}",
//            topics = "${spring.kafka.consumer.topic}")
    public void handleKafkaEvent(ConsumerRecord<String, String> consumerRecord) {
        var recordKey = consumerRecord.key();
        var recordTime = consumerRecord.timestamp();

        log.info("Processing kafka event, key {}, partition {}, offset: {}",
                recordKey, consumerRecord.partition(), consumerRecord.offset());

        kafkaConsumerService.process(consumerRecord);
    }

}
