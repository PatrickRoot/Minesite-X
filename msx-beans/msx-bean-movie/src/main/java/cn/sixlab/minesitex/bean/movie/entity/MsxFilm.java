/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/10/26 10:59
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.minesitex.bean.movie.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class MsxFilm implements Serializable{
    
    @Id
    @GeneratedValue
    private Integer id;
    private String movieName;
    private String produceYear;
    private String director;
    private String remark;
    private Date viewDate;
    private String doubanKey;
    private Double doubanScore;
    private String doubanInfo;
    private String infoStatus;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getMovieName() {
        return movieName;
    }
    
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    
    public String getProduceYear() {
        return produceYear;
    }
    
    public void setProduceYear(String produceYear) {
        this.produceYear = produceYear;
    }
    
    public String getDirector() {
        return director;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public Date getViewDate() {
        return viewDate;
    }
    
    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }
    
    public String getDoubanKey() {
        return doubanKey;
    }
    
    public void setDoubanKey(String doubanKey) {
        this.doubanKey = doubanKey;
    }
    
    public Double getDoubanScore() {
        return doubanScore;
    }
    
    public void setDoubanScore(Double doubanScore) {
        this.doubanScore = doubanScore;
    }
    
    public String getDoubanInfo() {
        return doubanInfo;
    }
    
    public void setDoubanInfo(String doubanInfo) {
        this.doubanInfo = doubanInfo;
    }
    
    public String getInfoStatus() {
        return infoStatus;
    }
    
    public void setInfoStatus(String infoStatus) {
        this.infoStatus = infoStatus;
    }
}
