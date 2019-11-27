package my.app.gayyong.controller;

import com.oracle.tools.packager.Log;
import my.app.gayyong.entiity.Article;
import my.app.gayyong.entiity.JsonResult;
import my.app.gayyong.entiity.User;
import my.app.gayyong.repository.ArticleRepository;
import my.app.gayyong.repository.UserRepository;
import my.app.gayyong.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleService articleService;

    private final Logger log = LoggerFactory.getLogger(ArticleController.class);

    @PostMapping(path = "/article/add")
    public JsonResult addArticle(Article article, String userId){
        log.info("添加文章");
        if (userId != null && !userId.equals("")) {
            Optional<User> userFindResult = userRepository.findById(userId);
            if (userFindResult.isPresent()) {
                User user = userFindResult.get();
                article.setUser(user);
                article.setLasttime(new Date());
                Article resultArticle = articleRepository.save(article);
                if (resultArticle != null) {
                    log.info("保存文章成功：" + article.toString());
                    return JsonResult.ok("保存成功", article);
                } else {
                    log.info("保存文章失败，要保存的文章为：" + article.toString());
                    return JsonResult.error500("保存失败");
                }
            } else {
                return JsonResult.ok("用户未找到");
            }
        }else{
            log.info("需要参数userId");
            return JsonResult.error500("需要参数userId");
        }
    }

    @GetMapping(path = "/article/get")
    public JsonResult getArticle(Integer artId){
        log.info("查询文章");
        if (artId != null){
            Optional<Article> articleFindResult = articleRepository.findById(artId);
            if (articleFindResult.isPresent()){
                Article articleResult = articleFindResult.get();
                log.info("查询文章成功："+articleResult.toString());
                return JsonResult.ok("查询文章成功",articleResult);
            }else{
                log.info("未找到文章id="+artId.toString());
                return JsonResult.ok("未找到文章");
            }
        }else{
            log.info("需要参数artId");
            return JsonResult.error500("需要参数artId");
        }
    }

    @PostMapping(path = "/article/update")
    public JsonResult updateArticle(Article article){
        article.setLasttime(new Date());
        log.info("修改文章");
        try {
            if (article != null && article.getArtId() != null){
                Optional<Article> findArticleResult = articleRepository.findById(article.getArtId());
                if (findArticleResult.isPresent()){
                    Article articleResult = findArticleResult.get();
                    Class articleClazz = article.getClass();
                    Field[] fields = articleClazz.getDeclaredFields();
                    for (Field field : fields){
                        field.setAccessible(true);
                        if (field.get(article) != null){
                            //用新的值替换掉旧的值
                            field.set(articleResult,field.get(article));
                            log.debug("把属性 \""+field.getName()+"\" 改为 \""+field.get(article).toString()+"\"");
                        }
                    }

                    //保存文章
                    Article articleSave = articleRepository.save(articleResult);
                    if (articleSave != null){
                        log.info("修改文章成功："+articleSave.toString());
                        return JsonResult.ok("修改文章成功",articleSave);
                    }else{
                        log.info("修改文章失败，要修改的文章为："+article.toString());
                        return JsonResult.ok("修改文章失败");
                    }
                }else{
                    log.info("不存在文章artId="+article.getArtId());
                    return JsonResult.error500("该文章不存在");
                }
            }else{
                log.info("需要参数artId");
                return JsonResult.error500("需要参数artId");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return JsonResult.error500("服务器内部出错");
        }
    }

    @GetMapping(path = "/article/delete")
    public JsonResult deleteArticle(Integer artId){
        log.info("删除文章");
        if (artId != null){
            if(articleRepository.findById(artId).isPresent()){
                //文章存在
                articleRepository.deleteById(artId);
                if (articleRepository.findById(artId).isPresent()){
                    //删除失败
                    log.info("文章artId=" + artId + " 删除失败");
                    return JsonResult.error500("删除失败");
                }else{
                    //删除成功
                    log.info("文章artId=" + artId + " 删除成功");
                    return JsonResult.ok("删除成功");
                }
            }else{
                //文章不存在
                log.info("文章artId="+artId+" 不存在");
                return JsonResult.ok("文章不存在");
            }
        }else{
            log.info("需要参数artId");
            return JsonResult.error500("需要参数artId");
        }
    }

    @GetMapping(path = "/article/collect")
    public JsonResult collect(String userId, Integer artId){
        log.info("文章收藏");
        if(userId != null && !userId.equals("") && artId != null){
            Optional<User> userIdFindResult = userRepository.findById(userId);
            Optional<Article> artIdFindResult = articleRepository.findById(artId);
            if (userIdFindResult.isPresent() && artIdFindResult.isPresent()){
                if (articleService.collect(userIdFindResult.get(),artIdFindResult.get())){
                    log.info("用户id="+userId+" 收藏文章id="+artId+" 成功");
                    return JsonResult.ok("收藏成功");
                }else{
                    log.info("用户id="+userId+" 收藏文章id="+artId+" 失败");
                    return JsonResult.ok("收藏失败");
                }
            }else{
                if (!userIdFindResult.isPresent() && !artIdFindResult.isPresent()){
                    log.info("userId="+userId+"和artId="+artId+"未找到");
                    return JsonResult.error500("userId和artId未找到");
                }else if(!userIdFindResult.isPresent()){
                    log.info("userId未找到用户userId="+userId);
                    return JsonResult.error500("userId未找到用户");
                }else if(!artIdFindResult.isPresent()){
                    log.info("artId未找到文章artId="+artId);
                    return JsonResult.error500("artId未找到文章");
                }else{
                    log.info("未知错误");
                    return JsonResult.error500("未知错误");
                }
            }
        }else{
            log.info("参数userId或artId未找到");
            return JsonResult.error500("参数userId或artId未找到");
        }
    }

    @GetMapping(path = "/article/uncollect")
    public JsonResult uncollect(String userId, Integer artId){
        log.info("取消收藏");
        if(userId != null && !userId.equals("") && artId != null){
            Optional<User> userIdFindResult = userRepository.findById(userId);
            Optional<Article> artIdFindResult = articleRepository.findById(artId);
            if (userIdFindResult.isPresent() && artIdFindResult.isPresent()){
                if (articleService.uncollect(userIdFindResult.get(),artIdFindResult.get())){
                    log.info("用户id="+userId+" 取消收藏文章id="+artId+" 成功");
                    return JsonResult.ok("取消收藏成功");
                }else{
                    log.info("用户id="+userId+" 取消收藏文章id="+artId+" 失败");
                    return JsonResult.ok("取消收藏失败");
                }
            }else{
                if (!userIdFindResult.isPresent() && !artIdFindResult.isPresent()){
                    log.info("userId="+userId+"和artId="+artId+"未找到");
                    return JsonResult.error500("userId和artId未找到");
                }else if(!userIdFindResult.isPresent()){
                    log.info("userId未找到用户userId="+userId);
                    return JsonResult.error500("userId未找到用户");
                }else if(!artIdFindResult.isPresent()){
                    log.info("artId未找到文章artId="+artId);
                    return JsonResult.error500("artId未找到文章");
                }else{
                    log.info("未知错误");
                    return JsonResult.error500("未知错误");
                }
            }
        }else{
            log.info("参数userId或artId未找到");
            return JsonResult.error500("参数userId或artId未找到");
        }
    }

    @GetMapping(path = "/article/list")
    public JsonResult getArticleList(String order, String property){
        log.info("查询文章列表");
        Sort.Order orderType;
        if (property == null || property.equals("")){
            property = "artId";
        }
        if (order != null && !order.equals("")){
            if (order.equals("asc")){
                orderType = new Sort.Order(Sort.Direction.ASC,property);
            }else{
                orderType = new Sort.Order(Sort.Direction.DESC,property);
            }
        }else{
            order = "asc";
            orderType = new Sort.Order(Sort.Direction.ASC,property);
        }
        log.info("根据属性:"+property+" 排序:"+order+" 进行排序");
        Sort sort = new Sort(orderType);
        List<Article> articles = articleRepository.findAll(sort);
        return JsonResult.ok("获取文章列表成功",articles);
    }
}
