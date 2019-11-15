package com.liang.tech.pojo;

public class Sentence {
    private Integer id;

    private String sentences;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSentences() {
        return sentences;
    }

    public void setSentences(String sentences) {
        this.sentences = sentences == null ? null : sentences.trim();
    }

	@Override
	public String toString() {
		return "Sentence [id=" + id + ", sentences=" + sentences + "]";
	}
    
}