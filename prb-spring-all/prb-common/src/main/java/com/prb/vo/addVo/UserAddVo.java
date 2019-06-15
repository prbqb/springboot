package com.prb.vo.addVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "用户添加实体")
public class UserAddVo {

    @ApiModelProperty(value = "用户id",name = "userId",notes = "必须传入",dataType = "Long",required = true)
    private Long userId;
    @ApiModelProperty(value = "用户名",name = "userName",notes = "必须要传的值",dataType = "String",required = true)
    private String userName;
    @ApiModelProperty(value = "年龄",name = "age",notes = "用户的年龄，默认为23岁",dataType = "Integer")
    private Integer age;
    @ApiModelProperty(value = "性别",name = "sex",notes = "用户的性别（0男1女）",dataType = "Integer")
    private Integer sex;
    @ApiModelProperty(value = "用户邮箱",name = "email",notes = "邮箱必须要传",required = true)
    private String email;

}
