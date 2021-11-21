package com.sige.application.repository;

import com.sige.application.enums.StatusCarrinho;
import com.sige.application.enums.StatusPagamento;
import com.sige.application.model.Carrinho;
import com.sige.application.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    @Query("select c from Carrinho c where c.usuario = ?1 and c.statusCarrinho = ?2")
    Carrinho getByUsuario(Usuario usuario, StatusCarrinho statusCarrinho);

    @Query("select c from Carrinho c where c.usuario = ?1 and c.statusCarrinho = ?2")
    List<Carrinho> getByUsuarioList(Usuario usuario, StatusCarrinho statusCarrinho);

    List<Carrinho> getByStatusPagamento(StatusPagamento statusPagamento);

    @Query("select sum(c.valorTotal) from Carrinho c where c.statusPagamento = ?1")
    Double valorTotalByStatusPagamento(StatusPagamento statusPagamento);
}
