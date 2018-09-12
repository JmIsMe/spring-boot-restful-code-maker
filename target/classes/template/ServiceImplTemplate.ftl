package ${projectPath}.service.serviceImpl;

import ${projectPath}.dao.${domainUpperName}Mapper;
import ${projectPath}.model.${domainUpperName};
import ${projectPath}.service.${domainUpperName}Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @description :
 * @author : ${author}
 * @date : ${date}
 */
@Service
@Transactional
public class ${domainUpperName}ServiceImpl extends ServiceCommonImpl<${domainUpperName}> implements ${domainUpperName}Service {
    @Resource
    private ${domainUpperName}Mapper ${domainLowerName}Mapper;

}
