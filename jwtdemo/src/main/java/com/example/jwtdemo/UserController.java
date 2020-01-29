package com.example.jwtdemo;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class UserController {
//    @Autowired
//    UserService userService;

    @PostMapping("/login")
    public ResultModel<User> login(@RequestBody User user){

        ResultModel<User> model = new ResultModel<>();
//        User user =userService.findByUsername(user);
        if(user ==null){
            model.setStatus(400);
            model.setMessage("登录失败,用户不存在");
            return model;
        }else {
            if (!user.getPassword().equals("123456")){
                model.setStatus(500);
                model.setMessage("登录失败,密码错误");
                return model;
            }else {

                String token = JwtUtils.getToken(user);

                user.setToken(token);
                model.setData(user);
                model.setStatus(200);
                return model;
            }
        }
    }

    @Authorization
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
