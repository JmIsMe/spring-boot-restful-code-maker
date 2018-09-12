package ${projectPath}.controller;
import ${projectPath}.domain.Result;
import ${projectPath}.domain.${domainUpperName};
import ${projectPath}.service.${domainUpperName}Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description :
 * @author : ${author}
 * @date : ${date}
 */
@RestController
@RequestMapping("/${domainUpperName}")
public class ${domainUpperName}Controller {
    @Resource
    private ${domainUpperName}Service ${domainLowerName}Service;

    @PostMapping("/add")
    public Result add(@RequestBody ${domainUpperName} ${domainLowerName}, Result result) {
        ${domainLowerName}Service.save(${domainLowerName});
        //Result? Do it yourself.
        return result;
    }


    @PostMapping("/delete")
    public Result delete(@RequestParam("id") String id, Result result) {
        ${domainLowerName}Service.deleteById(id);
        //Result? Do it yourself.
        return result;
    }

    @PostMapping("/update")
    public Result update(@RequestBody ${domainUpperName} ${domainLowerName}, Result result) {
        ${domainLowerName}Service.update(${domainLowerName});
        //Result? Do it yourself.
        return result;
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam("id") String id, Result result) {
        ${domainUpperName} ${domainLowerName} = ${domainLowerName}Service.findById(id);
        //Result? Do it yourself.
        return result);
    }
}