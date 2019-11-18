package my.app.gayyong.entiity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Integer commentId;

    @Column(nullable = false)
    private String comText;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"follow","follower","articleList","collectionArticleList"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "art_id")
    @JsonIgnoreProperties({"collectNum","user","comments"})
    private Article article;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getComText() {
        return comText;
    }

    public void setComText(String comText) {
        this.comText = comText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", comText='" + comText + '\'' +
                '}';
    }
}
