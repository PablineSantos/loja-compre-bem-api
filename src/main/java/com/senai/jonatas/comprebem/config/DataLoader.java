package com.senai.jonatas.comprebem.config;

import com.senai.jonatas.comprebem.entity.Produto;
import com.senai.jonatas.comprebem.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** Cadastra 60 produtos ao subir, apenas se a tabela estiver vazia (o H2 grava em arquivo). */
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) {
        if (produtoRepository.count() > 0) {
            return;
        }
        String[] nomes = {"Cadeira", "Mesa", "Sofa", "Estante", "Luminaria", "Armario"};
        String[] categorias = {"Moveis", "Moveis", "Moveis", "Decoracao", "Iluminacao", "Moveis"};
        List<Produto> produtos = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            int t = (i - 1) % nomes.length;
            Produto p = new Produto();
            p.setNome(nomes[t] + " Modelo " + i);
            p.setDescricao("Descricao do produto " + nomes[t].toLowerCase() + " modelo " + i);
            p.setPreco(BigDecimal.valueOf(50L + i * 10L));
            p.setCategoria(categorias[t]);
            p.setImagem("https://picsum.photos/seed/produto" + i + "/300");
            p.setQuantidade(10 + i);
            produtos.add(p);
        }
        produtoRepository.saveAll(produtos);
    }
}
