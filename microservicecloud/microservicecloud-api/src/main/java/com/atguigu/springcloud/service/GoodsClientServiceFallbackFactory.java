package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // 不要忘记添加，不要忘记添加
public class GoodsClientServiceFallbackFactory implements FallbackFactory<GoodsClientService>
{
	@Override
	public GoodsClientService create(Throwable throwable)
	{
		return new GoodsClientService() {
			@Override
			public Object getAll() {
				return "获取失败";
			}
		};
	}
}
