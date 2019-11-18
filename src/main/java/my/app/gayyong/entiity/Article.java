package my.app.gayyong.entiity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Article {
    @Id
    @GeneratedValue
    private Integer artId;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String artText;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lasttime;

    @Column(length = 10)
    private String artTag;

    @ManyToMany(mappedBy = "collectionArticleList")
    @JsonIgnoreProperties({"follow","follower","articleList","collectionArticleList"})
    private List<User> collectNum;//收藏者

    @ManyToOne
    @JoinColumn(name = "article_list")
    @JsonIgnoreProperties({"follow","follower","articleList","collectionArticleList"})
    private User user;//作者

    @OneToMany(mappedBy = "article")
    @JsonIgnoreProperties({"user","article"})
    private List<Comment> comments;//评论

    public Integer getArtId() {
        return artId;
    }

    public void setArtId(Integer artId) {
        this.artId = artId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtText() {
        return artText;
    }

    public void setArtText(String artText) {
        this.artText = artText;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public String getArtTag() {
        return artTag;
    }

    public void setArtTag(String artTag) {
        this.artTag = artTag;
    }

    public List<User> getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(List<User> collectNum) {
        this.collectNum = collectNum;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Article{" +
                "artId=" + artId +
                ", title='" + title + '\'' +
                ", artText='" + artText + '\'' +
                ", lasttime=" + lasttime +
                ", artTag='" + artTag + '\'' +
                '}';
    }
}
