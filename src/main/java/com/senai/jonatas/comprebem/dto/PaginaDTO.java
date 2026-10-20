package com.senai.jonatas.comprebem.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record PaginaDTO<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean last
) {
    public static <T> PaginaDTO<T> of(Page<T> p) {
        return new PaginaDTO<>(p.getContent(), p.getNumber(), p.getSize(),
                p.getTotalElements(), p.getTotalPages(), p.isLast());
    }
}
