package br.com.williamramos.apipagamento.controller;

import br.com.williamramos.apipagamento.domain.model.Extrato;
import br.com.williamramos.apipagamento.domain.model.Pagamento;
import br.com.williamramos.apipagamento.domain.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    private Extrato extrato;

    public PagamentoController() {
        this.extrato = new Extrato();
    }

    //retorna uma lista contendo todos os pagamentos
    @GetMapping
    public List<Pagamento> listAll() {
        return repository.findAll();
    }

    //retorna um pagamento buscando pelo id, caso não exista retorna um 404;
    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> findById(@PathVariable("id") Long id) {
        return repository.findById(id).map(item -> ResponseEntity.ok().body(item)).orElse(ResponseEntity.notFound().build());
    }

    // retorna uma lista de pagamento feitas pelo usuario, filtrando pelo cpf;
    @GetMapping("/cpf/{cpf}")
    public List<Pagamento> findByCpf(@PathVariable("cpf") String cpf) {
        return repository.findByCpf(cpf);
    }

    // retorna uma lista de pagamento feitas pelo usuario, filtrando pelo cpf;
    @GetMapping("/extrato/{cpf}")
    public Extrato getExtrato(@PathVariable("cpf") String cpf) {
        List<Pagamento> pagamentos = repository.findByCpf(cpf);
        if (pagamentos != null) {
            this.extrato.setListaPagamentos(pagamentos);
            return this.extrato;
        } else {
            return null;
        }
    }

    @GetMapping("/relatorio/{cpf}")
    public Extrato extrato(@RequestParam(name = "dataInicio", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
                           @RequestParam(name = "dataFim", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
                           @PathVariable("cpf") String cpf) {

        List<Pagamento> pagamentos = repository.findByDataPagamentoBetweenAndCpf(dataInicio, dataFim, cpf);
        if (pagamentos != null) {
            this.extrato.setListaPagamentos(pagamentos);
            return this.extrato;
        } else {
            return null;
        }
    }

    // criar um novo pagamento pix
    @PostMapping
    public Pagamento save(@RequestBody Pagamento pagamento) {
        if (pagamento.getDataPagamento() == null) {
            pagamento.setDataPagamento(LocalDate.now());
        }
        return repository.save(pagamento);
    }


    // Exclui um pagamento pelo id caso o pagamento exista, caso não exista retorna um 404
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return repository.findById(id).map(item -> {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());

    }

}
