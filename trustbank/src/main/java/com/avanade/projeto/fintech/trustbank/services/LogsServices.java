package com.avanade.projeto.fintech.trustbank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.projeto.fintech.trustbank.entities.Logs;
import com.avanade.projeto.fintech.trustbank.repository.LogsRepository;

@Service
public class LogsServices {

	@Autowired
	private LogsRepository logsRepository;
	
	
	public Logs incluirLog(Logs log) {
		return logsRepository.save(log);
	}
	
	public List<Logs> listarLogs(){
		return logsRepository.listarLogs();
	}
	
}
