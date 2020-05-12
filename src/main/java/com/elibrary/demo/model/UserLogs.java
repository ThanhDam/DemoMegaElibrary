package com.elibrary.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.boot.autoconfigure.web.ResourceProperties.Strategy;

@Entity
@Table(name = "userLogs")
public class UserLogs {

	private String idLog;
	private String sessionId;
	private Date startTime;
	private Date endTime;
	private String idUser;
	@Id
	public String getIdLog() {
		return idLog;
	}
	public void setIdLog(String idLog) {
		this.idLog = idLog;
	}
	@Column(name = "sessionId", nullable = false)
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@Column(name = "startTime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Column(name = "endTime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name = "idUser", nullable = false)
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public UserLogs() {
		
	}
	public UserLogs(String idLog, String sessionId, Date startTime, Date endTime, String idUser) {
		this.idLog = idLog;
		this.sessionId = sessionId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.idUser = idUser;
	}
	
	
	
	
	
	
}
