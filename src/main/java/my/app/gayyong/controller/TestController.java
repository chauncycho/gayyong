package my.app.gayyong.controller;

import my.app.gayyong.entiity.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test/getResult")
    public JsonResult getTestResult(){
        return JsonResult.ok("Login Success");
    }
}
