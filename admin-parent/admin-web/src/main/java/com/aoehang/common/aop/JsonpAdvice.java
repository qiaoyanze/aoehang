package com.aoehang.common.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * Jsonp 支持，只要带了callback参数的请求就会自动返回jsonp数据
 * 
 * @author 乔彦泽
 * @since 2017年7月13日
 */
@ControllerAdvice
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {

	public JsonpAdvice() {
		super("callback");
	}
}
