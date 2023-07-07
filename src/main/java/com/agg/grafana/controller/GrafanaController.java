package com.agg.grafana.controller;

import com.agg.grafana.dao.Host;
import com.agg.grafana.service.aggregate.ProGraMonitorService;
import com.agg.grafana.service.config.HostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.net.URISyntaxException;

/**
 * @author wangzhimin
 * @date 2022/7/16 10:50 下午
 */
@Controller
@RequestMapping
public class GrafanaController {

	@Resource
	private ProGraMonitorService proGraMonitorService;

	@Resource
	private HostService hostService;


	@GetMapping("/grafana")
	public String index(Model model) throws URISyntaxException {
		model.addAttribute("grafanaList",proGraMonitorService.getAllGrafanaInfo());
		model.addAttribute("prometheusList",proGraMonitorService.getAllPrometheusInfo());
		model.addAttribute("hostList",hostService.queryList());
		return "index";
	}

	@GetMapping("/add")
	public String add(@RequestParam("address")String address,@RequestParam("type")String type,
	                  @RequestParam("desc")String desc,Model model) throws URISyntaxException {
		Host host = new Host();
		host.setAddress(address);
		host.setType(type);
		host.setDesc(desc);
		hostService.insert(host);
		return "redirect:/grafana";
	}

	@GetMapping("/del/{id}")
	public String del(@PathVariable("id")Integer id, Model model) throws URISyntaxException {
		hostService.del(id);
		return "redirect:/grafana";
	}
}
