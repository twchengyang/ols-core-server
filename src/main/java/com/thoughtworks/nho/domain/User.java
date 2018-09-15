package com.thoughtworks.nho.domain;

import com.thoughtworks.nho.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_user")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {
    @Id
    private String id;

    @Column(unique = true)
    private String name;

    private String password;

    @Column(name = "real_name")
    private String realName;

    public User() {
        id = StringUtils.uuid();
    }
}
