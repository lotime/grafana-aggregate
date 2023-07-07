package com.agg.grafana.dao;

import com.agg.grafana.GrafanaAggApplication;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangzhimin
 * @date 2023/7/6 9:56 上午
 */
@SpringBootTest(classes = GrafanaAggApplication.class)
@RunWith(SpringRunner.class)
public class ConfigMapperTest {

	@Autowired
	private HostMapper hostMapper;

	@Test
	public void insert(){
		Host config = new Host();
		config.setAddress("localhost:9090");
		config.setType("Prometheus");
		config.setDesc("本地测试");
		hostMapper.insert(config);
	}

	@Test
	public void select(){
//		System.out.println(JSON.toJSONString(hostMapper.selectById(2)));
		QueryWrapper<Host> hostWrapper = new QueryWrapper<>();
		hostWrapper.eq("type","Prometheus");
		System.out.println(JSON.toJSONString(hostMapper.selectList(hostWrapper)));
	}

	@Test
	public void delete(){
		hostMapper.deleteById(2);
	}

	@Test
	public void update(){
		Host config = new Host();
		config.setId(2);
		config.setDesc("本地测试1");
		hostMapper.updateById(config);
	}

}
