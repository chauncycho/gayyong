package my.app.gayyong.entiity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @Column(nullable = false,length = 20)
    private String userId;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false, length = 4)
    private String sex;

    @Column(nullable = false)
    private String nickname;

    @Column(length = 1000)
    private String headUrl;

    @ManyToMany
    @JoinTable(name = "collection_list", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "userId")},
    inverseJoinColumns = {@JoinColumn(name = "art_id", referencedColumnName = "artId")})
    @JsonIgnoreProperties({"collectNum","user","comments"})
    private List<Article> collectionArticleList;//收藏的文章

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"collectNum","user","comments"})
    private List<Article> articleList;//创作的文章

    @ManyToMany
    @JoinTable(name = "follow_list", joinColumns = {@JoinColumn(name = "followed_id", referencedColumnName = "userId")},
    inverseJoinColumns = {@JoinColumn(name = "follower_id", referencedColumnName = "userId")})
    @JsonIgnoreProperties({"follow","follower","articleList","collectionArticleList"})
    private List<User> follow;

    @ManyToMany(mappedBy = "follow")
    @JsonIgnoreProperties({"follow","follower","articleList","collectionArticleList"})
    private List<User> follower;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        String passwd = null;
        passwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
        this.pwd = passwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public List<Article> getCollectionArticleList() {
        return collectionArticleList;
    }

    public void setCollectionArticleList(List<Article> collectionArticleList) {
        this.collectionArticleList = collectionArticleList;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<User> getFollow() {
        return follow;
    }

    public void setFollow(List<User> follow) {
        this.follow = follow;
    }

    public List<User> getFollower() {
        return follower;
    }

    public void setFollower(List<User> follower) {
        this.follower = follower;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex='" + sex + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headUrl='" + headUrl + '\'' +
                '}';
    }
}
