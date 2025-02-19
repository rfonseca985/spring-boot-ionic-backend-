package cursomodelagemconceitual.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cursomodelagemconceitual.domain.Categoria;
import cursomodelagemconceitual.domain.Produto;

@Repository
@Transactional(readOnly=true)
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

// Metodo usando query e anota��es
@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
Page<Produto> search(
@Param("nome") String nome,
@Param("categorias") List<Categoria> categorias,
Pageable pageRequest);

/*Metodo alterando o nome do metodo e sem anota��es
 * 
 * Page<Produto> findDistinctByNomContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRquest);
 * */
}