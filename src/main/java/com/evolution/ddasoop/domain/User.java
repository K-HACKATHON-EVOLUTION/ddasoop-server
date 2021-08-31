package com.evolution.ddasoop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
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

    public void updateUserName(String userName){
        this.userName = userName;
    }

    public void updateTotalCarbon(Double carbon){
        totalCarbon += carbon;
    }

    public void updateForest(Forest forest){
        this.forest = forest;
    }

    public void deleteForest(){
        this.forest = null;
    }

    public void updateDeleteFlag(){
        this.deleteFlag = true;
    }
}
