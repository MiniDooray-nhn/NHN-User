package com.nhnacademy.account.domain;

import com.nhnacademy.account.dto.UserModifyRequest;
import com.nhnacademy.account.dto.UserResponse;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_status_id")
    private UserStatus userStatus;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    public UserResponse convetToDto() {

        UserResponse userResponse = new UserResponse();
        userResponse.setStatusId(this.userStatus.getId());
        userResponse.setId(this.id);
        userResponse.setName(this.name);
        return userResponse;
    }

    public void setUserByModifyRequest(UserModifyRequest modifyRequest) {
        this.email = modifyRequest.getEmail();
        this.name = modifyRequest.getName();
        this.password = modifyRequest.getPassword();
    }

}
