package br.edu.cortaFacil.service;

import br.edu.cortaFacil.aux.Logradouro;
import br.edu.cortaFacil.dao.Barbeiro;
import br.edu.cortaFacil.entity.BarbeiroEntity;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BarbeariaService {

    public BarbeariaService(Barbeiro barbeiro){

    }

    @Autowired
    Barbeiro barbeiroDAO;

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public void cadastrar(BarbeiroEntity barbeiroEntity) throws IOException {

        getCidade(barbeiroEntity);

        barbeiroDAO.save(barbeiroEntity);

    }

    private void getCidade(BarbeiroEntity barbeiroEntity) throws IOException {

        StringBuilder url = new StringBuilder("https://viacep.com.br/ws/");

        url.append(barbeiroEntity.getCep())
           .append("/json/");

        HttpGet getRequest = new HttpGet(url.toString());

        HttpResponse httpResponse = httpClient.execute(getRequest);

        HttpEntity httpEntity = httpResponse.getEntity();

        String result = EntityUtils.toString(httpEntity);

        Logradouro logradouro = new Gson().fromJson(result, Logradouro.class);

        barbeiroEntity.setCidade(logradouro.getLocalidade());

    }

}
