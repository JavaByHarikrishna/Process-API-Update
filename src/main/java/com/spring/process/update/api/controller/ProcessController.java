package com.spring.process.update.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.process.update.api.dto.Processor;
import com.spring.process.update.api.service.ProcessService;

@Controller
@RequestMapping("/process")
public class ProcessController {
	@Autowired
	private ProcessService service;

	List<Processor> processes = null;
	List<String> tableList = null;

	@PostConstruct
	public void init() {
		tableList = new ArrayList<>();
		tableList.add("");

	}

	@GetMapping("/loadProcess")
	public String loadProcessorInfo(Model model) {
		processes = service.getCurrentProcess();
		model.addAttribute("tableList", tableList);
		model.addAttribute("size", processes.size());
		model.addAttribute("processes", processes);
		return "ProcessorPage";
	}

	@GetMapping("/destroy")
	public String destroyProcess(@RequestParam("id") long id, Model model) {
		processes = service.getCurrentProcess();
		model.addAttribute("tableList", tableList);
		model.addAttribute("size", processes.size());
		model.addAttribute("processes", processes);
		model.addAttribute("message", service.stopProcess(id));
		return "ProcessorPage";
	}

	@GetMapping("/startProcessor")
	public String startProcessorView(Model model) {
		List<String> dummylist = new ArrayList<>();
		dummylist.add("");
		model.addAttribute("dummylist", dummylist);
		model.addAttribute("size", service.getCurrentProcess().size());
		return "ProcessorPage";
	}

	@GetMapping("/start")
	public String startProcess(@RequestParam("processorName") String processorName, Model model) throws Exception {
		processes = service.getCurrentProcess();
		model.addAttribute("tableList", tableList);
		model.addAttribute("size", processes.size());
		model.addAttribute("processes", processes);
		model.addAttribute("message", service.startProcess(processorName));
		return "ProcessorPage";
	}
}
