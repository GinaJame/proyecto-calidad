package com.example.demo.model;

public class JsonResponse<T> {

    private int codigo;
    private T data;

    public JsonResponse(int codigo, T data) {
        this.codigo = codigo;
        this.data = data;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}