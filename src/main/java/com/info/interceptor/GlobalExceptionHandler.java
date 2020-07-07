package com.info.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.info.common.ReturnValue;
import com.info.common.StateMsg;
import com.info.util.LogUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by TerryJ on 2020/03/04.
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String  jsonErrorHandler(HttpServletRequest req, Throwable t) throws Exception {
        ReturnValue<String> rtn = new ReturnValue<>();

        rtn.setStateMsg(StateMsg.StatusMsg_144);
        rtn.setSystemerrormsg(t.getMessage());

        StringWriter stringWriter= new StringWriter();

        String jsonCallback = req.getParameter("callback");

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