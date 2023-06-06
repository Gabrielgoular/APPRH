package com.goulartDev.appRH.AppRH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.goulartDev.appRH.AppRH.model.Candidato;
import com.goulartDev.appRH.AppRH.model.Vaga;
@Repository
public interface CandidatoRepository extends  CrudRepository<Candidato, Long>{
	Iterable<Candidato>findByVaga(Vaga vaga);
	
	Candidato findByRg(String rg); 
	
	Candidato findById(long id);
	@Query(value = "select u from Candidato u where u.nomeCandidato like %?1%")
	List<Candidato> findByNomesCandidatos(String nomeCandidato);
}

