package com.caoyuqian.common.error;

import com.caoyuqian.common.api.Status;
import lombok.Getter;

/**
 * @author qian
 * @version V1.0
 * @Title: ServiceException
 * @Package: com.caoyuqian.commom.error
 * @Description: ServiceException
 * @date 2019/12/2 2:39 下午
 **/
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -1866714411123430858L;
    @Getter
    private final Status status;

    public ServiceException(String message){
        super(message);
        this.status = Status.FAILURE;
    }

    public ServiceException(Status status){
        super(status.getMsg());
        this.status = status;
    }

    public ServiceException(Status status, String msg) {
        super(msg);
        this.status = status;
    }

    public ServiceException(Status status, Throwable cause) {
        super(cause);
        this.status = status;
    }

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
        this.status = Status.FAILURE;
    }

    /**
     * for better performance
     *
     * @return Throwable
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }
}
