package com.agg.grafana.service.config;

import com.agg.grafana.dao.Host;
import com.agg.grafana.dao.HostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangzhimin
 * @date 2023/7/7 4:06 下午
 */
@Service
public class HostService {

	@Autowired
	private HostMapper hostMapper;

	public List<Host> queryList(){
		return hostMapper.selectList(null);
	}

	public void insert(Host host){
		hostMapper.insert(host);
	}

	public void del(int id){
		hostMapper.deleteById(id);
	}
}
