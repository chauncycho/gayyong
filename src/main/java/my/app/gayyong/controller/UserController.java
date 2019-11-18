package my.app.gayyong.controller;

import my.app.gayyong.entiity.JsonResult;
import my.app.gayyong.entiity.User;
import my.app.gayyong.repository.UserRepository;
import my.app.gayyong.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController  {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping(path = "/user/add")
    public JsonResult addUser(User user){
        log.info("添加用户");
        User resultUser = userRepository.save(user);

        if (resultUser != null){
            log.info("添加用户成功："+resultUser.toString());
            return JsonResult.ok("添加成功",user);
        }else{
            log.info("添加用户失败，要添加的用户为："+user.toString());
            return JsonResult.error500("添加失败");
        }
    }

    @GetMapping(path = "/user/get")
    public JsonResult getUser(String id){
        log.info("查询用户");
        if(id != null && !id.equals("")) {
            Optional<User> findResult = userRepository.findById(id);
            if (findResult.isPresent()) {
                //找得到
                User resultUser = findResult.get();
                log.info("查询用户成功：" + resultUser);
                return JsonResult.ok("查询用户成功", resultUser);
            } else {
                //找不到
                log.info("查询用户失败，要查找的id为：" + id.toString());
                return JsonResult.ok("查询用户失败");
            }
        }else{
            log.info("需要参数id");
            return JsonResult.error500("需要参数id");
        }
    }

    @PostMapping(path = "/user/update")
    public JsonResult updateUser(User user){
        log.info("修改用户");
        try {
            if (user != null && user.getUserId() != null && !user.getUserId().equals("")) {
                Optional<User> findUserResult = userRepository.findById(user.getUserId());
                if (findUserResult.isPresent()){
                    User userResult = findUserResult.get();
                    Class userClazz = user.getClass();
                    Field[] fields = userClazz.getDeclaredFields();
                    for (Field field : fields){
                        field.setAccessible(true);
                        if (field.get(user) != null){
                            //用新的值替换掉旧的值
                            field.set(userResult,field.get(user));
                            log.debug("把属性 \""+field.getName()+"\" 改为 \""+field.get(user).toString()+"\"");
                        }
                    }

                    //保存用户
                    User userSave = userRepository.save(userResult);
                    if (userSave != null){
                        log.info("修改用户成功："+userSave.toString());
                        return JsonResult.ok("修改用户成功",userSave);
                    }else{
                        log.info("修改用户失败，要修改的用户为："+user.toString());
                        return JsonResult.error500("修改用户失败");
                    }
                }else{
                    log.info("不存在用户id="+user.getUserId());
                    return JsonResult.error500("该用户不存在");
                }
            }else{
                log.info("需要参数userId");
                return JsonResult.error500("需要参数userId");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return JsonResult.error500("服务器内部出错");
        }
    }

    @GetMapping(path = "/user/delete")
    public JsonResult deleteUser(String id){
        log.info("删除用户");
        if (id != null && !id.equals("")) {
            if (userRepository.findById(id).isPresent()) {
                //用户存在
                userRepository.deleteById(id);
                if (userRepository.findById(id).isPresent()) {
                    //删除失败
                    log.info("用户id=" + id + " 删除失败");
                    return JsonResult.error500("删除失败");
                } else {
                    //删除成功
                    log.info("用户id=" + id + " 删除成功");
                    return JsonResult.ok("删除成功");
                }
            } else {
                //用户不存在
                log.info("用户id=" + id + " 不存在");
                return JsonResult.ok("用户不存在");
            }
        }else{
            log.info("需要参数id");
            return JsonResult.error500("需要参数id");
        }
    }

    @GetMapping(path = "/user/follow")
    public JsonResult follow(String userId, String targetId){
        log.info("用户关注");
        if (userId != null && targetId != null && !userId.equals("") && !targetId.equals("")) {
            Optional<User> userIdFindResult = userRepository.findById(userId);
            Optional<User> targetIdFindResult = userRepository.findById(targetId);
            if (userIdFindResult.isPresent() && targetIdFindResult.isPresent()) {
                User user = userIdFindResult.get();
                User target = targetIdFindResult.get();

                //防止为初始化
                if (user.getFollow() == null){
                    user.setFollow(new ArrayList<>());
                }
                if (target.getFollower() == null){
                    target.setFollower(new ArrayList<>());
                }

                //关注
                if (!user.getFollow().contains(target)) {
                    user.getFollow().add(target);
                }
                if (!target.getFollower().contains(user)) {
                    target.getFollower().add(user);
                }

                //保存
                if(userService.saveTwo(user,target)){
                    log.info("用户id="+userId+" 关注id="+targetId+" 成功");
                    return JsonResult.ok("关注成功");
                }else{
                    log.info("用户id="+userId+" 关注id="+targetId+" 失败");
                    return JsonResult.error500("关注失败");
                }
            } else {
                if (!userIdFindResult.isPresent() && !targetIdFindResult.isPresent()){
                    log.info("userId和targetId未找到");
                    return JsonResult.error500("userId="+userId+"和targetId="+targetId+"未找到");
                }else if (!userIdFindResult.isPresent()) {
                    log.info("userId未找到用户");
                    return JsonResult.error500("userId未找到用户userId="+userId);
                } else if (!targetIdFindResult.isPresent()) {
                    log.info("targetId未找到用户targetId="+targetId);
                    return JsonResult.error500("targetId未找到用户");
                } else {
                    log.info("未知错误");
                    return JsonResult.error500("未知错误");
                }
            }
        }else{
            log.info("参数userId或targetId未找到");
            return JsonResult.error500("参数userId或targetId未找到");
        }
    }

    @GetMapping("/user/unfollow")
    public JsonResult unfollow(String userId, String targetId){
        log.info("取消关注");
        if (userId != null && targetId != null && !userId.equals("") && !targetId.equals("")) {
            Optional<User> userIdFindResult = userRepository.findById(userId);
            Optional<User> targetIdFindResult = userRepository.findById(targetId);
            if (userIdFindResult.isPresent() && targetIdFindResult.isPresent()) {
                User user = userIdFindResult.get();
                User target = targetIdFindResult.get();

                //防止为初始化
                if (user.getFollow() == null){
                    user.setFollow(new ArrayList<>());
                }
                if (target.getFollower() == null){
                    target.setFollower(new ArrayList<>());
                }

                //关注
                if (user.getFollow().contains(target)) {
                    user.getFollow().remove(target);
                }
                if (target.getFollower().contains(user)) {
                    target.getFollower().remove(user);
                }

                //保存
                if(userService.saveTwo(user,target)){
                    log.info("用户id="+userId+" 取消关注id="+targetId+" 成功");
                    return JsonResult.ok("取消关注成功");
                }else{
                    log.info("用户id="+userId+" 取消关注id="+targetId+" 失败");
                    return JsonResult.error500("取消关注失败");
                }
            } else {
                if (!userIdFindResult.isPresent() && !targetIdFindResult.isPresent()){
                    log.info("userId和targetId未找到");
                    return JsonResult.error500("userId和targetId未找到");
                }else if (!userIdFindResult.isPresent()) {
                    log.info("userId未找到用户");
                    return JsonResult.error500("userId未找到用户");
                } else if (!targetIdFindResult.isPresent()) {
                    log.info("targetId未找到用户");
                    return JsonResult.error500("targetId未找到用户");
                } else {
                    log.info("未知错误");
                    return JsonResult.error500("未知错误");
                }
            }
        }else{
            return JsonResult.error500("userId或targetId参数未找到");
        }
    }
}
