package br.com.williamramos.apipagamento.domain.repository;

import br.com.williamramos.apipagamento.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByCpf(String cpf);

    List<Pagamento> findByChavePix(String chavePix);


}
