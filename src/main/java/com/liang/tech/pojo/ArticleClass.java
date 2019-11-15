package com.liang.tech.pojo;

public class ArticleClass {
    private Integer id;

    private Integer superid;

    private String classname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSuperid() {
        return superid;
    }

    public void setSuperid(Integer superid) {
        this.superid = superid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

	@Override
	public String toString() {
		return "ArticleClass [id=" + id + ", superid=" + superid + ", classname=" + classname + "]";
	}
    
    
}