package com.dh.Back_Client2;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;

public class DentistaCliente {


    public Dentista salvar(Dentista dentista){
        HttpResponse<String> response = null;
        Dentista dentista1 = null;

        try {
           response = Unirest.post("http://localhost:8080/clinica/dentista/")
                    .header("Content-Type", "application/json")
                    .header("Authorization", buscarToken())
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
            response = Unirest.get("http://localhost:8080/clinica/dentista")
                    .header("Content-Type", "application/json")
                    .header("Authorization", buscarToken())
                    .asString();

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
            response = Unirest.get("http://localhost:8080/clinica/dentista/buscar/" + id)
                    .header("Content-Type", "application/json")
                    .header("Authorization", buscarToken())
                    .asString();

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
            response = Unirest.put("http://localhost:8080/clinica/dentista/alterar")
                    .header("Content-Type", "application/json")
                    .header("Authorization", buscarToken())
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
            response = Unirest.delete("http://localhost:8080/clinica/dentista/excluir/" + id)
                    .header("Content-Type", "application/json")
                    .header("Authorization", buscarToken())
                    .asString();

        } catch (Exception e){
            e.printStackTrace();
        }

        return response != null ? String.valueOf(response.getStatus()) : null;
    }

    private String buscarToken() throws ParseException, UnirestException {
        AppUser user = new AppUser("admin", "password2");
        HttpResponse<String> json;

        json = Unirest.post("http://localhost:8080/authenticate")
                .header("Content-Type", "application/json")
                .body(JsonUtil.asJsonString(user)).asString();

        JSONParser parser = new JSONParser(json.getBody());
        Object Obj = parser.parse();
        String token = "Bearer " + ((LinkedHashMap) Obj).get("jwt").toString();
        return token;
    }

}
