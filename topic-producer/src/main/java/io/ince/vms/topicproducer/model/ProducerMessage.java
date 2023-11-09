package io.ince.vms.topicproducer.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ProducerMessage {
    private Integer id;
    private String message;
}
