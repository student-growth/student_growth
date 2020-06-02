package com.info.interceptor;

import com.alibaba.fastjson.JSON;
import com.info.common.ReturnValue;
import com.info.common.StateMsg;
import com.info.util.LogUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author : yue
 * @Date : 2020/5/31 / 23:29
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String jsonErrorHandler(HttpServletRequest request,Throwable t) throws Exception{
        ReturnValue<String>  rtn = new ReturnValue<>();
        rtn.setStateMsg(StateMsg.StateMsg_102);
        rtn.setSystemError(t.getMessage());

        StringWriter stringWriter= new StringWriter();
        String jsonCallback = request.getParameter("callback");
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        String returnJson = JSON.toJSONString(rtn);
        if (!StringUtils.isEmpty(jsonCallback)) {
            returnJson = jsonCallback + "(" + returnJson + ")";
        }
        LogUtil.error(buffer.toString());
        return returnJson;
    }
}
