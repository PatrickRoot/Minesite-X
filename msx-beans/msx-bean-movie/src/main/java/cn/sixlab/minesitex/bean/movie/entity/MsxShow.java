/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
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
import java.util.Date;

@Entity
public class MsxShow {
    
    @Id
    @GeneratedValue
    private Integer id;
    private String showName;
    private Integer showSeason;
    private Integer showEpisode;
    private String viewStatus;
    private String tv;
    private String remark;
    private String doubanKey;
    private Date beginDate;
    private Date updateDate;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getShowName() {
        return showName;
    }
    
    public void setShowName(String showName) {
        this.showName = showName;
    }
    
    public Integer getShowSeason() {
        return showSeason;
    }
    
    public void setShowSeason(Integer showSeason) {
        this.showSeason = showSeason;
    }
    
    public Integer getShowEpisode() {
        return showEpisode;
    }
    
    public void setShowEpisode(Integer showEpisode) {
        this.showEpisode = showEpisode;
    }
    
    public String getViewStatus() {
        return viewStatus;
    }
    
    public void setViewStatus(String viewStatus) {
        this.viewStatus = viewStatus;
    }
    
    public String getTv() {
        return tv;
    }
    
    public void setTv(String tv) {
        this.tv = tv;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String getDoubanKey() {
        return doubanKey;
    }
    
    public void setDoubanKey(String doubanKey) {
        this.doubanKey = doubanKey;
    }
    
    public Date getBeginDate() {
        return beginDate;
    }
    
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    
    public Date getUpdateDate() {
        return updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
