package com.evolution.ddasoop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @Column
    private String userName;

    @Column
    private String userEmail;

    @Column
    private String userPassword;

    @Column
    private Boolean deleteFlag;

    @Column
    private Double totalCarbon;

    @ManyToOne(targetEntity = Forest.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "forestIdx")
    private Forest forest;

    @Builder
    public User(String userName, String userEmail, String userPassword, Boolean deleteFlag, Double totalCarbon, Forest forest){
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.deleteFlag = deleteFlag;
        this.totalCarbon = totalCarbon;
        this.forest = forest;
    }

    public void updateDeleteFlag(Boolean flag){
        this.deleteFlag =flag;
    }
}
