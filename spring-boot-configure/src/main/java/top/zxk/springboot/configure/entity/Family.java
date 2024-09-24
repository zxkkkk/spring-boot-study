package top.zxk.springboot.configure.entity;


import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import top.zxk.springboot.configure.loader.YamlPropertyLoader;

@Data
@Component
@ConfigurationProperties(prefix = "family")
//@PropertySource(value = {"classpath:family.properties"})//加上这个注释才可以用properties
@PropertySource(value = {"classpath:family.yml"}, factory = YamlPropertyLoader.class)
@Validated
public class Family {
    @Length(min = 5, max = 10, message = "家庭名称必须在5到10位之间！ ")
    private String familyNmae;
    private String father;
    private String mother;
    private String child;

    @Range(min = 3, message = "家庭年限必须大于三年！")
    private Integer age;
}
