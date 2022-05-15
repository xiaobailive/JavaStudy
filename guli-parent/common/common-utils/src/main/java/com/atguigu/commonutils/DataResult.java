package com.atguigu.commonutils;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author 小白
 * @create 2022-05-14 3:38
 */

@Data
public class DataResult {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data = new HashMap<String,Object>();

    private DataResult() {}

    public static DataResult ok(){
        DataResult r = new DataResult();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("操作成功");
        return r;
    }

    public static DataResult error(){
        DataResult r = new DataResult();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("操作失败");
        return r;
    }

    public DataResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public DataResult message(String message){
        this.setMessage(message);
        return this;
    }
    public DataResult code(Integer code){
        this.setCode(code);
        return this;
    }
    public DataResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public DataResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}
