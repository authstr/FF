package com.authstr.ff.basic.controller;

import com.authstr.ff.utils.web.controller.AbstractController;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * 控制层的父类,
 * 主要定义了
 */
@Component
public class BasicController extends AbstractController {


	@Override
	public  void exceptionHandlerBefore(Exception e){

	};

	@Override
	public  void exceptionHandlerAfter(Exception e,Map info){

	};


}
