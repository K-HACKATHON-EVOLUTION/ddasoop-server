package com.evolution.ddasoop.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@Table(name= "heart")
public class Heart extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartIdx;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(targetEntity = Course.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_idx")
    private Course course;

    private Boolean deleteFlag;

    @Builder
    public Heart(User user, Course course, Boolean deleteFlag){
        this.user = user;
        this.course = course;
        this.deleteFlag = deleteFlag;
    }

    public void updateDeleteFlag(){
        deleteFlag = true;
    }
}
