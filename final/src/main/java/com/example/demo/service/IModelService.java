package com.example.demo.service;

import java.util.Collection;
import java.util.Set;

public interface IModelService<T> {
    void crear(T t);
    Collection<T> buscarTodos();
    T buscarPorId(Long id);
    void actualizar(T t);
    void eliminar(Long id);
}
