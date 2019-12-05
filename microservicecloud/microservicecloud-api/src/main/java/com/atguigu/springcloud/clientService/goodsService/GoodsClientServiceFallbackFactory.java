package com.atguigu.springcloud.clientService.goodsService;

import com.atguigu.springcloud.entities.ajax.ResponseResult;
import com.atguigu.springcloud.entities.goods.Goods;
import com.atguigu.springcloud.util.ResultGeneratorUtil;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component // 不要忘记添加，不要忘记添加
public class GoodsClientServiceFallbackFactory implements FallbackFactory<GoodsClientService>
{
	@Override
	public GoodsClientService create(Throwable throwable)
	{
		return new GoodsClientService() {
			@Override
			public ResponseResult insert(Goods goods) {
				return null;
			}

			@Override
			public ResponseResult deleteById(int id) {
				return null;
			}

			@Override
			public ResponseResult update(Goods goods) {
				return null;
			}

			@Override
			public ResponseResult getById(int id) {
				return null;
			}

			@Override
			public ResponseResult getAll() {
				return ResultGeneratorUtil.getResultFail("远程服务出错~");
			}


		};
	}
}
