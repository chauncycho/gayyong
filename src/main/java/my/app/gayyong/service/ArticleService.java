package my.app.gayyong.service;

import my.app.gayyong.entiity.Article;
import my.app.gayyong.entiity.User;
import my.app.gayyong.repository.ArticleRepository;
import my.app.gayyong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public boolean collect(User user, Article article){
        try {
            //防止为空
            if (user.getCollectionArticleList() == null){
                user.setCollectionArticleList(new ArrayList<>());
            }
            if (article.getCollectNum() == null){
                article.setCollectNum(new ArrayList<>());
            }

            if (!user.getCollectionArticleList().contains(article)){
                user.getCollectionArticleList().add(article);
            }
            if (!article.getCollectNum().contains(user)){
                article.getCollectNum().add(user);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean uncollect(User user, Article article){
        try {
            //防止为空
            if (user.getCollectionArticleList() == null){
                user.setCollectionArticleList(new ArrayList<>());
            }
            if (article.getCollectNum() == null){
                article.setCollectNum(new ArrayList<>());
            }

            if (user.getCollectionArticleList().contains(article)){
                user.getCollectionArticleList().remove(article);
            }
            if (article.getCollectNum().contains(user)){
                article.getCollectNum().remove(user);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
