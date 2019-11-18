package my.app.gayyong.controller;

import my.app.gayyong.entiity.Comment;
import my.app.gayyong.entiity.JsonResult;
import my.app.gayyong.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    private final Logger log = LoggerFactory.getLogger(CommentController.class);

//    @PostMapping(path = "/comment/add")
//    public JsonResult addComment(Comment comment, String userId, Integer artId){
//        log.info("添加评论");
//
//    }

}
