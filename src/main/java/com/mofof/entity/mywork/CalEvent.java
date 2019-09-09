package com.mofof.entity.mywork;


import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.system.User;

@Entity
@SQLDelete(sql = "update cal_event set del = 1 where id = ?")
@SQLDeleteAll(sql = "update cal_event set del = 1 where id = ?")
@Where(clause = "del = 0")
public class CalEvent extends BaseEntity {
  public static final String INNER_EVT = "inner";
  public static final String OUTING_EVT = "outing";
  public static final String TRIP_EVT = "trip";
  public static final int FEWDAY = 0;
  public static final int ALLDAY = 2;
  public static final int DAYS = 3;

	private String title;			         //标题
	private String location;		       //位置
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startedAt;	 //开始时间
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endedAt;	   //结束时间
	@ManyToOne(cascade = CascadeType.MERGE)
	//	@JoinColumn(name = "userId")	
	private User creatorUser;		       //用户
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime canceledAt;  //取消时间
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
	@JoinTable(                                
      name="cal_event_user",                    
      joinColumns= {@JoinColumn(name="cal_event_id")},        
      inverseJoinColumns= {@JoinColumn(name="user_id")})    
	private Set<User> attendees;		    //参与人
	private String topic;			           //日程类型 0-公司内部， 1-本地外出， 2-异地出差
	private int importance;		         //重要程度 0-10 (不重要 -> 重要)
	private int timeRange;             //  0-日内 , 2-单天 , 3- 多天
	private String memo;               
	
	@Transient
	private Alert alert;
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDateTime getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(LocalDateTime startedAt) {
		this.startedAt = startedAt;
	}

	public LocalDateTime getEndedAt() {
		return endedAt;
	}

	public void setEndedAt(LocalDateTime endedAt) {
		this.endedAt = endedAt;
	}

	public int getImportance() {
    return importance;
  }

  public void setImportance(int importance) {
    this.importance = importance;
  }

  public User getCreatorUser() {
		return creatorUser;
	}

	public void setCreatorUser(User creatorUser) {
		this.creatorUser = creatorUser;
	}

  public Alert getAlert() {
    return alert;
  }

  public void setAlert(Alert alert) {
    this.alert = alert;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public LocalDateTime getCanceledAt() {
    return canceledAt;
  }

  public void setCanceledAt(LocalDateTime canceledAt) {
    this.canceledAt = canceledAt;
  }

  public int getTimeRange() {
    return timeRange;
  }

  public void setTimeRange(int timeRange) {
    this.timeRange = timeRange;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public Set<User> getAttendees() {
    return attendees;
  }

  public void setAttendees(Set<User> attendees) {
    this.attendees = attendees;
  }
}
