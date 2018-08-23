package com.pazz.controller;

import com.pazz.exception.BusinessException;
import com.pazz.log.ILogExceptionWriter;
import com.pazz.response.PageResponse;
import com.pazz.response.PageResponseBuilder;
import com.pazz.response.Response;
import com.pazz.response.ResponseBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 彭坚
 * @create 2018/8/23 21:37
 * @see
 */
public abstract class AbstractController {

    protected final Log log = LogFactory.getLog(getClass());
    /**
     * 数据验证错误
     */
    public final static String ERROR_CODE_VALIDATOR = "10000";
    /**
     * 业务异常错误
     */
    public final static String ERROR_CODE_BUSINESS = "20000";
    /**
     * 系统异常
     */
    public final static String ERROR_CODE_SYSTEM = "90000";

    @Autowired(required = false)
    private ILogExceptionWriter logExceptionWriter;

    /**
     * 功能描述: 返回成功
     *
     * @author 彭坚
     * @date 2018/8/23 21:53
     * @since V1.0.0
     */
    public Response returnSuccess() {
        return returnSuccess(null);
    }


    /**
     * 功能描述: 封装返回对象
     *
     * @author 彭坚
     * @date 2018/8/23 21:54
     * @since V1.0.0
     */
    public Response returnSuccess(Object result) {
        return ResponseBuilder.create().builderSuccess(result);
    }


    /**
     * 功能描述: 封装结果集对象包括结果集长度
     *
     * @author 彭坚
     * @date 2018/8/23 21:56
     * @since V1.0.0
     */
    public PageResponse returnPageSuccess(Object result, long totalCount) {
        return PageResponseBuilder.create().buildSuccess(result, totalCount);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response handlerException(Exception exception) {
        //打印异常
        log.error(exception.getMessage(), exception);
        if (logExceptionWriter != null) {
            logExceptionWriter.write(exception);
        }
        if (exception instanceof IllegalArgumentException) {
            //参数校验异常
            return ResponseBuilder.create().buildBusinessException(AbstractController.ERROR_CODE_VALIDATOR, exception.getMessage());
        }
//        if (exception instanceof AccessNotAllowException) {
//            //访问拒绝异常
//            return ResponseBuilder.create().buildBusinessException(AbstractController.ERROR_CODE_VALIDATOR, "权限不足!");
//        }
//        if (exception instanceof DataValidatorException) {
//            //数据验证异常
//            return ResponseBuilder.create().buildBusinessException(AbstractController.ERROR_CODE_VALIDATOR, ((BusinessException) exception).getErrorCode());
//        }
        if (exception instanceof BusinessException) {
            //业务异常
            return ResponseBuilder.create().buildBusinessException(AbstractController.ERROR_CODE_BUSINESS, ((BusinessException) exception).getErrorCode());
        } else {
            //系统异常
            return ResponseBuilder.create().buildSystemException(AbstractController.ERROR_CODE_SYSTEM, NestedExceptionUtils.buildMessage(null, exception));
        }
    }

//    @ExceptionHandler(Exception.class)
//    public String handleUserNotLoginException() {
//        //用户未登录重定向到根目录
//        return "redirect:/";
//    }

//    @ResponseBody
//    @ExceptionHandler(RuntimeException.class)//可以直接写@ExceptionHandler,不指明异常类，会自动映射
//    public Response customGenericExceptionHandler(RuntimeException exception) { //还可以声明接收其他任意参数
//        Response response = new Response();
//        response.setResult(exception);
//        return response;
//    }
//
//    @ResponseBody
//    @ExceptionHandler(IOException.class)//可以直接写@ExceptionHandler,不指明异常类，会自动映射
//    public Response allExceptionHandler(IOException exception) { //还可以声明接收其他任意参数
//        Response response = new Response();
//        response.setResult(exception);
//        return response;
//    }

}
