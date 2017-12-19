package yycgpt.base.process.exception;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import yycgpt.base.process.result.ExceptionResultInfo;
import yycgpt.base.process.result.ResultInfo;

/***
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 全局异常处理器
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author :zhuqiujie
 * @date 2017年11月22日 下午11:12:42
 */
class ExceptionResolverCustom implements HandlerExceptionResolver {
	// json转换器
	// 将异常信息转换为json
	private HttpMessageConverter<ExceptionResultInfo> jsonMessageConverter;

	/*
	 * 前端控制器就是调用这个方法来处理异常 handler，执行action类，包装了（url的方法）
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// 输出异常信息
		ex.printStackTrace();
		// 转出springmvc底层的一个对象（就是action方法的封装对象，只有一个方法）
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// 反射取出方法
		Method method = handlerMethod.getMethod();
		// 判断是否返回json，只要返回有注解@responsebody就表示返回json

		ResponseBody responseBody = AnnotationUtils.findAnnotation(method,
				ResponseBody.class);
		if (responseBody != null) {
			// 将异常信息转化为json
			return this.resolveJsonException(request, response, handlerMethod,
					ex);

		}
		// 说明action返回的是jsp页面
		// 解析异常
		ExceptionResultInfo exceptionResultInfo = resolveExceptionCustom(ex);
		
		String view = "/base/error";
		// 获取异常代码
		int messageCode = exceptionResultInfo.getResultInfo().getMessageCode();
		if (messageCode == 106) {
			view = "/base/login";
		}

		// 将异常信息在jsp页面显示
		// 跳转到错误页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exceptionResultInfo",
				exceptionResultInfo.getResultInfo());
		// 逻辑视图名
		modelAndView.setViewName(view);
		return modelAndView;
	}

	// 异常信息的解析方法
	private ExceptionResultInfo resolveExceptionCustom(Exception exception) {
		ResultInfo resultInfo = null;
		if (exception instanceof ExceptionResultInfo) {
			resultInfo = ((ExceptionResultInfo) exception).getResultInfo();
		} else {
			// 未知错误异常
			resultInfo = new ResultInfo();
			resultInfo.setMessage("系统维护中");
			resultInfo.setIndex(ResultInfo.TYPE_RESULT_FAIL);
		}
		return new ExceptionResultInfo(resultInfo);
	}

	public ModelAndView resolveJsonException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		// 异常解析
		ExceptionResultInfo exceptionResultInfo = resolveExceptionCustom(ex);

		HttpOutputMessage outputMessage = new ServletServerHttpResponse(
				response);
		try {
			// exceptionResultInfo对象转成json
			jsonMessageConverter.write(exceptionResultInfo,
					MediaType.APPLICATION_JSON, outputMessage);
		} catch (HttpMessageNotWritableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public HttpMessageConverter<ExceptionResultInfo> getJsonMessageConverter() {
		return jsonMessageConverter;
	}

	public void setJsonMessageConverter(
			HttpMessageConverter<ExceptionResultInfo> jsonMessageConverter) {
		this.jsonMessageConverter = jsonMessageConverter;
	}

}
