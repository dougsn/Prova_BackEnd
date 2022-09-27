package com.dh.Back_Client2;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.util.List;

public class DentistaCliente {

    public Dentista salvar(Dentista dentista){
        HttpResponse<String> response = null;
        Dentista dentista1 = null;

        try {
            response = Unirest.post("http://localhost:8080/dentista/")
                    .header("Content-Type", "application/json")
                    .body(JsonUtil.asJsonString(dentista)).asString();

            dentista1 = JsonUtil.objectFromString(Dentista.class, response.getBody());

        } catch (Exception e){
            e.printStackTrace();
        }
        return dentista1;
    }

    public List<Dentista> buscarTodos(){
        HttpResponse<String> response = null;
        List<Dentista> dentista1 = null;

        try {
            response = Unirest.get("http://localhost:8080/dentista").asString();

            dentista1 = (List<Dentista>) JsonUtil.jsonStringToObjectArray(response.getBody(), Dentista.class);

        } catch (Exception e){
            e.printStackTrace();
        }
        return dentista1;

    }

    public Dentista buscarPorId(Integer id){
        HttpResponse<String> response = null;
        Dentista dentista1 = null;

        try {
            response = Unirest.get("http://localhost:8080/dentista/buscar/" + id).asString();

            dentista1 = JsonUtil.objectFromString(Dentista.class, response.getBody());

        } catch (Exception e){
            e.printStackTrace();
        }
        return dentista1;
    }

    public Dentista alterar(Dentista dentista){
        HttpResponse<String> response = null;
        Dentista dentista1 = null;

        try {
            response = Unirest.put("http://localhost:8080/dentista/alterar")
                    .header("Content-Type", "application/json")
                    .body(JsonUtil.asJsonString(dentista)).asString();

            dentista1 = JsonUtil.objectFromString(Dentista.class, response.getBody());

        } catch (Exception e){
            e.printStackTrace();
        }
        return dentista1;
    }

    public String excluir(Integer id){
        HttpResponse<String> response = null;
        Dentista dentista1 = null;

        try {
            response = Unirest.delete("http://localhost:8080/dentista/excluir/" + id).asString();

        } catch (Exception e){
            e.printStackTrace();
        }

        return response != null ? String.valueOf(response.getStatus()) : null;
    }


}
