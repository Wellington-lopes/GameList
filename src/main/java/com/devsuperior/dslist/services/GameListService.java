package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;

@Service /*tambem pode usar o @Component*/
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true) 
	public List<GameListDTO> findAll(){
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();	
	}
	
	/*Usado para fazer a mudança da posição do jogo no view do usuario no front end e no banco de dados*/
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		
		GameMinProjection obj =  list.remove(sourceIndex); /*Cópia e remove o objeto da lista*/
		
		list.add(destinationIndex, obj); /*Move para o destino o objeto*/
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex; /*acha o minimo entre eles*/
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex; /*acha o max entre eles*/
		
		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}
}

/*Camada de acesso de SERVIÇO - O QUE FICA NO MEIO DA API*/
/*Service injeta o Repository*/
/*É um componente do sistema, e ele deve ser injetado um no outro para funcionar utilizando o @Autowired*/