package my.app.gayyong.controller;

import my.app.gayyong.entiity.Article;
import my.app.gayyong.entiity.Comment;
import my.app.gayyong.entiity.JsonResult;
import my.app.gayyong.entiity.User;
import my.app.gayyong.repository.ArticleRepository;
import my.app.gayyong.repository.CommentRepository;
import my.app.gayyong.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.Optional;

@RestController
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;

    private final Logger log = LoggerFactory.getLogger(CommentController.class);

    @PostMapping(path = "/comment/add")
    public JsonResult addComment(Comment comment, String userId, Integer artId){
        log.info("添加评论");
        if (userId != null && !userId.equals("") && artId != null){
            Optional<User> findUserResult = userRepository.findById(userId);
            Optional<Article> findArticleResult = articleRepository.findById(artId);
            if (findUserResult.isPresent() && findArticleResult.isPresent()){
                User user = findUserResult.get();
                Article article = findArticleResult.get();
                comment.setUser(user);
                comment.setArticle(article);

                //保存
                Comment commentResult = commentRepository.save(comment);
                if (commentResult != null){
                    log.info("添加评论成功");
                    return JsonResult.ok("添加评论成功",commentResult);
                }else{
                    log.error("添加评论失败");
                    return JsonResult.error500("添加评论失败");
                }
            }else{
                StringBuilder sb = new StringBuilder();
                StringBuilder res = new StringBuilder();
                if (!findUserResult.isPresent()){
                    sb.append(" 找不到userId="+userId+" ");
                    res.append(" 找不到userId ");
                }
                if (!findUserResult.isPresent()){
                    sb.append(" 找不到artId="+artId+" ");
                    res.append(" 找不到artId ");
                }
                log.error(sb.toString());
                return JsonResult.error500(res.toString());
            }
        }else{
            log.error("需要参数userId,artId");
            return JsonResult.error500("需要参数userId，artId");
        }
    }

    @GetMapping(path = "/comment/get")
    public JsonResult getComment(Integer commentId){
        log.info("查询评论");
        if (commentId != null){
            Optional<Comment> findCommentResult = commentRepository.findById(commentId);
            if (findCommentResult.isPresent()){
                Comment comment = findCommentResult.get();
                log.info("查询评论成功："+comment.toString());
                return JsonResult.ok("查询评论成功",comment);
            }else{
                log.info("未找到评论commentId="+commentId);
                return JsonResult.ok("未找到评论");
            }
        }else{
            log.error("需要参数commentId");
            return JsonResult.error500("需要参数commentId");
        }
    }

    @PostMapping(path = "/comment/update")
    public JsonResult updateComment(Comment comment){
        log.info("修改评论");
        try {
            if (comment != null && comment.getCommentId() != null){
                Optional<Comment> findCommentResult = commentRepository.findById(comment.getCommentId());
                if (findCommentResult.isPresent()) {
                    Comment commentResult = findCommentResult.get();
                    Class commentClazz = comment.getClass();
                    Field[] fields = commentClazz.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        if (field.get(comment) != null) {
                            //用新的值替换掉旧的值
                            field.set(commentResult,field.get(comment));
                            log.debug("把属性 \""+field.getName()+"\" 改为 \""+field.get(comment).toString()+"\"");
                        }
                    }

                    //保存评论
                    Comment commentSave = commentRepository.save(commentResult);
                    if (commentSave != null){
                        log.info("修改评论成功："+commentSave.toString());
                        return JsonResult.ok("修改评论成功",commentSave);
                    }else{
                        log.info("修改文章失败，要修改的文章为："+comment.toString());
                        return JsonResult.ok("修改文章失败");
                    }
                }else{
                    log.error("评论不存在commentId="+comment.getCommentId());
                    return JsonResult.error500("该评论不存在");
                }
            }else{
                log.error("需要参数commentId");
                return JsonResult.error500("需要参数commentId");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return JsonResult.error500("服务器内部出错");
        }
    }

    @GetMapping(path = "/comment/delete")
    public JsonResult deleteComment(Integer commentId){
        log.info("删除评论");
        if (commentId != null){
            if(commentRepository.findById(commentId).isPresent()){
                //评论存在
                commentRepository.deleteById(commentId);
                if (commentRepository.findById(commentId).isPresent()){
                    //删除失败
                    log.info("评论commentId=" + commentId + " 删除失败");
                    return JsonResult.error500("删除失败");
                }else{
                    //删除成功
                    log.info("评论commentId=" + commentId + " 删除成功");
                    return JsonResult.ok("删除成功");
                }
            }else{
                //文章不存在
                log.info("评论commentId="+commentId+" 不存在");
                return JsonResult.ok("评论不存在");
            }
        }else{
            log.info("需要参数commentId");
            return JsonResult.error500("需要参数commentId");
        }
    }
}
