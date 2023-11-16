package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.repositories.GameListRepository;

@Service /*tambem pode usar o @Component*/
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;
	
	@Transactional(readOnly = true) 
	public List<GameListDTO> findAll(){
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();	
	}
}

/*Camada de acesso de SERVIÇO - O QUE FICA NO MEIO DA API*/
/*Service injeta o Repository*/
/*É um componente do sistema, e ele deve ser injetado um no outro para funcionar utilizando o @Autowired*/