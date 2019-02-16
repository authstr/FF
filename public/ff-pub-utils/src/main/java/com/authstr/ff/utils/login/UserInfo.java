
package com.authstr.ff.utils.login;

import java.io.Serializable;

/**
 * 用于储存在session的用户实体类
 * @author authstr
 *
 */
public class UserInfo
implements Serializable {
    public static String NO_BODY = "nobody";
    private static final long serialVersionUID = -2830199892969953280L;
    private String userid;
    private String accountid;
    private String username;
    private String realname;
    private String truename;
    private String departmentid;
    private String departmentname;
    private String dbid;
    private String style;
    private String locale;
    private String endpoint;
    private String maps;
    private String ip;
    private String roleid;

    public String getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getMaps() {
        return this.maps;
    }

    public void setMaps(String maps) {
        this.maps = maps;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRoleid() {
        return this.roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getStyle() {
        return this.style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getAccountid() {
        return this.accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getTruename() {
        return this.truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }



    public String getDbid() {
        return this.dbid;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    public String getRealname() {
        return this.realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public UserInfo() {
    }

    public UserInfo(String userid, String departmentid) {
        this.userid = userid;
        this.departmentid = departmentid;
    }

    public UserInfo(String userid, String username, String departmentid, String departmentname) {
        this.userid = userid;
        this.username = username;
        this.departmentid = departmentid;
        this.departmentname = departmentname;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDepartmentid() {
        return this.departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartmentname() {
        return this.departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }
}

