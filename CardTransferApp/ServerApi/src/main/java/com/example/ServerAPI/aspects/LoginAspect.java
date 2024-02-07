package com.example.ServerAPI.aspects;

import lombok.extern.java.Log;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Log
@Component
public class LoginAspect {

    /**
     * Аспектный метод логирования через аннотацию
     * @param returnValue кастомное сообщение в лог
     */
    @AfterReturning(value = "@annotation(com.example.ServerAPI.aspects.MyLog)", returning = "returnValue")
    public void log(Object returnValue) {
        log.info("Метод отработал: " + returnValue);
    }

}
