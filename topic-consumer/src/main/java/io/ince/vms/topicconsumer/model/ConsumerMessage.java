package io.ince.vms.topicconsumer.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ConsumerMessage {
    private Integer id;
    private String message;
}
