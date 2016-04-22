package com.example.rocklct.bangumi.mybangumi.ui.bean;

/**
 * Created by rocklct on 2016/4/21.
 */
public class RankBean extends BaseBean {
    int rank;
    String subject_id;

    public RankBean(int rank,String subject_id){
        this.rank = rank;
        this.subject_id = subject_id;
    }
}
