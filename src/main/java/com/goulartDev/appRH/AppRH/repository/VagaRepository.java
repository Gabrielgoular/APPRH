package com.goulartDev.appRH.AppRH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.goulartDev.appRH.AppRH.model.Vaga;

@Repository
public interface VagaRepository  extends CrudRepository<Vaga, Long>{
  Vaga findByCodigo(long codigo);
  
  List<Vaga> findByNome(String nome);
  
  @Query(value = "select u from Vaga u where u.nome like %?1%")
	List<Vaga> findByNomesVaga(String nome);

}