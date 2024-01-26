package com.nhnacademy.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_status")
public class UserStatus {

    @Id
    @Column(name="user_status_id")
    private Integer id;

    @Column(name="status_name")
    private String name;

}
