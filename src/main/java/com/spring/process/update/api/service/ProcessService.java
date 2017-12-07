package com.spring.process.update.api.service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.process.update.api.dto.Processor;

@Service
public class ProcessService {

	public List<Processor> getCurrentProcess() {
		List<Processor> processes = new ArrayList<>();
		ProcessHandle.allProcesses().filter(ph -> ph.info().command().isPresent())
				.forEach(ph -> processes.add(new Processor(ph.pid(), ph.info().totalCpuDuration().get().getSeconds(),
						ph.info().command().orElse(""),
						ph.info().startInstant().orElse(Instant.now()).toString().replace("T", " ").replace("Z", ""),
						ph.info().totalCpuDuration().orElse(Duration.ofMillis(0)).toMillis() + "ms",
						ph.info().user().orElse(""))));
		return processes;
	}

	public String stopProcess(long pid) {
		Optional<ProcessHandle> o = ProcessHandle.of(pid);
		ProcessHandle processHandle = o.get();
		processHandle.destroy();
		return "process destroyed with id " + pid;
	}

	public String startProcess(String processorName) throws Exception {
		Process p = new ProcessBuilder(processorName).start();
		return "new Processor started  " + processorName + " with pid " + p.pid();
	}

}
