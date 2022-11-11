package edu.example.express.controller;


import edu.example.express.entity.User;
import edu.example.express.entity.dto.ResultBean;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/express/api/system-administrator")
public class testController {
    @PostMapping("/test")
    public ResultBean addOneUser(@RequestBody User user){
        ResultBean resultBean = new ResultBean("用户增加成功","","");
        return resultBean;
    }
}
