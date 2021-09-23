package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de temas com metódo utilizado para se fazer pesquisas na coluna "tema" ContainingIgnoreCase
 * @param tema do tipo String
 * @return List de Temas
 * @author Cristiano
 * 
 */

@Repository
public interface TemaRepository extends JpaRepository <Tema, Long>
{
	public List<Tema>findAllByTemaContainingIgnoreCase(String tema);
}
