package cz.cvut.fit.tjv.fittour.fittourclient.data;

import cz.cvut.fit.tjv.fittour.fittourclient.model.RiderModel;
import cz.cvut.fit.tjv.fittour.fittourclient.model.SnowboardModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class RiderClient
{
    private static final String ONE_URI = "/{id}";
    private final WebClient riderWebClient;

    public RiderClient(@Value("${fittour_backend_url}") String backendUrl)
    {
        riderWebClient = WebClient.create(backendUrl + "/riders");
    }

    public Flux<RiderModel> fetchAllSnowboards()
    {
        return riderWebClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(RiderModel.class);
    }

}
