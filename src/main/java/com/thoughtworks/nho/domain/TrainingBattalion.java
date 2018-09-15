package com.thoughtworks.nho.domain;

import com.thoughtworks.nho.util.StringUtils;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_training_battalion")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor

public class TrainingBattalion {

    @Id
    private String id;

    private String title;
    private String desc;
    private String createTime;

    public TrainingBattalion() {
        id = StringUtils.uuid();
    }
}
