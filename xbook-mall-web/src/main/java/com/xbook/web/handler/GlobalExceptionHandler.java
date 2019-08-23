package com.xbook.web.handler;


import com.xbook.cart.service.exception.CartException;
import com.xbook.common.core.Result;
import com.xbook.common.enums.CodeMsgEnum;
import com.xbook.common.exception.BaseException;
import com.xbook.order.exception.OrderException;
import com.xbook.product.service.exception.ProductException;
import com.xbook.user.service.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return Result.error(CodeMsgEnum.SERVER_ERROR);
    }

    /**
     * 业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public Result handleBusiException(BaseException e) {
        if(e instanceof UserException) {
            log.error("用户服务异常", e);
            return Result.error(e.getCodeMsgEnum());
        } else if(e instanceof ProductException) {
            log.error("产品服务异常", e);
            return Result.error(e.getCodeMsgEnum());
        } else if (e instanceof CartException) {
            log.error("购物车服务异常", e);
            return Result.error(e.getCodeMsgEnum());
        } else if (e instanceof OrderException) {
            log.error("订单服务异常", e);
            return Result.error(e.getCodeMsgEnum());
        }
        return Result.error(CodeMsgEnum.SERVER_ERROR);
    }
}
