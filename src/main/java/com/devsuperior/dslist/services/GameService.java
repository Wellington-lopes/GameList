package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;

@Service /*tambem pode usar o @Component*/
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true) /*fazer sempre nas seeds*/
	public GameDTO findById(Long id) {
		Game result = gameRepository.findById(id).get();
		return new GameDTO(result);
	}
	
	@Transactional(readOnly = true) 
	public List<GameMinDTO> findAll() {
		List<Game> result = gameRepository.findAll();
		return result.stream().map(x -> new GameMinDTO(x)).toList();	
	}
	
	@Transactional(readOnly = true) 
	public List<GameMinDTO> findByList(Long listID) {
		List<GameMinProjection> result = gameRepository.searchByList(listID);
		return result.stream().map(x -> new GameMinDTO(x)).toList();
	}
}

/*Camada de acesso de SERVIÇO - O QUE FICA NO MEIO DA API*/
/*Service injeta o Repository*/
/*É um componente do sistema, e ele deve ser injetado um no outro para funcionar utilizando o @Autowired*/