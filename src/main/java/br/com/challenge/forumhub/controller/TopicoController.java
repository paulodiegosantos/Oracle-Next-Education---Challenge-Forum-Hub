package br.com.challenge.forumhub.controller;

import br.com.challenge.forumhub.domain.topico.*;
import br.com.challenge.forumhub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroTopico dados) {

        if(repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())){
            throw new RuntimeException("Tópico duplicado");
        }

        Topico topico = new Topico(
                dados.titulo(),
                dados.mensagem(),
                dados.autor(),
                dados.curso()
        );

        repository.save(topico);
    }

    @GetMapping
    public List<DadosListagemTopico> listar() {
        return repository.findAll().stream()
                .map(topico -> new DadosListagemTopico(
                        topico.getTitulo(),
                        topico.getMensagem(),
                        topico.getDataCriacao(),
                        topico.getStatus(),
                        topico.getAutor(),
                        topico.getCurso()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public Topico detalhar(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        // Regras de negócio: evitar duplicidade de título e mensagem
        if(repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem()) &&
                !(topico.getTitulo().equals(dados.titulo()) && topico.getMensagem().equals(dados.mensagem()))) {
            throw new RuntimeException("Tópico duplicado");
        }

        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setCurso(dados.curso());

        repository.save(topico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        repository.deleteById(topico.getId());
    }
}