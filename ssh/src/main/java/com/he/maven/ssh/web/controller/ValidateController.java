package com.he.maven.ssh.web.controller;

import com.he.maven.core.bean.Result;
import com.he.maven.ssh.bean.TestBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author heyanjing
 * @date 2018/8/29 9:45
 */
@Slf4j
@Controller
@RequestMapping("/validate")
public class ValidateController {
    /**
     * /validate/
     */
    @GetMapping("/")
    public String temp() {
        return "/validate";
    }

    /**
     * /validate/validate
     */
    @RequestMapping("/validate")
    @ResponseBody
    public Result validate(@Validated TestBean bean, BindingResult bindingResult) {
        Result errors = this.getErrors(bindingResult);
        if (errors != null) {
            return errors;
        }
        return Result.success();
    }

    /**
     * 如果同一个类，在不同的使用场景下有不同的校验规则，那么可以使用分组校验。未成年人是不能喝酒的，而在其他场景下我们不做特殊的限制，这个需求如何体现同一个实体，不同的校验规则呢？分组校验
     * <p>
     * /validate/group
     */
    @RequestMapping("/group")
    @ResponseBody
    public Result group(@Validated(value = TestBean.Child.class) TestBean bean, BindingResult bindingResult) {
        Result errors = this.getErrors(bindingResult);
        if (errors != null) {
            return errors;
        }
        return Result.success();
    }

    private Result getErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> errors = new HashMap<>(5);
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Result.failureWithError(errors);
        }
        return null;
    }
}
