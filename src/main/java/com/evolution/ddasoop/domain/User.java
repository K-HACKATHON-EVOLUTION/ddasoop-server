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
@Table(name= "user")
public class User {

    @Id
    private String userIdx;

    @Column
    private String userName;

    @Column
    private Boolean deleteFlag;

    @Column
    private Double totalCarbon;

    @ManyToOne(targetEntity = Forest.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "forest_idx")
    private Forest forest;

    @Builder
    public User(String userIdx, String userName, Boolean deleteFlag, Double totalCarbon, Forest forest){
        this.userIdx = userIdx;
        this.userName = userName;
        this.deleteFlag = deleteFlag;
        this.totalCarbon = totalCarbon;
        this.forest = forest;
    }

    public void updateDeleteFlag(Boolean flag) {
        this.deleteFlag = flag;
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
