package com.mongodb.transaction.TrasactionDemo.Repository;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Spaceship {

    @Id
    private String id;
    private int cost;

}
