package com.devsuperior.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long>{

}

/*Camada de acesso de dados*/
/*É um componente do sistema, e ele deve ser injetado um no outro para funcionar*/