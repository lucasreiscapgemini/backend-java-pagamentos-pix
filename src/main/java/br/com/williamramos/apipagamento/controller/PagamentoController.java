package br.com.williamramos.apipagamento.controller;

import br.com.williamramos.apipagamento.domain.model.Pagamento;
import br.com.williamramos.apipagamento.domain.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/pagamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PagamentoController {

    @Autowired
    private PagamentoRepository repository;

    @GetMapping
    public List<Pagamento> listAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> findById(@PathVariable("id") Long id) {
        return repository.findById(id).map(item -> ResponseEntity.ok().body(item)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public List<Pagamento> findByCpf(@PathVariable("cpf") String cpf) {
        return repository.findByCpf(cpf);
    }


    @PostMapping
    public Pagamento save(@RequestBody Pagamento pagamento) {
        pagamento.setDataPagamento(LocalDate.now());
        return repository.save(pagamento);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return repository.findById(id).map(item -> {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());

    }

}
