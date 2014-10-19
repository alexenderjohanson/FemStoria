package com.gsy.femstoria.restful;


public interface IAsyncResponseHandler<T> {
    void onSuccess(T result);
    void onFailure(ResponseType type, String errorMessage);
}
