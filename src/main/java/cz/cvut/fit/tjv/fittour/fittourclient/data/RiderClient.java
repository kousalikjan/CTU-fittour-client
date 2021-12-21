package cz.cvut.fit.tjv.fittour.fittourclient.data;

import cz.cvut.fit.tjv.fittour.fittourclient.model.RiderModel;
import cz.cvut.fit.tjv.fittour.fittourclient.model.SnowboardDto;
import cz.cvut.fit.tjv.fittour.fittourclient.model.SnowboardModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    public Mono<RiderModel> create(RiderModel newRider)
    {
        return riderWebClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newRider)
                .retrieve()
                .bodyToMono(RiderModel.class);
    }

    public Mono<RiderModel> readById(Integer id)
    {
        System.out.println("here2");
        return riderWebClient.get()
                .uri(ONE_URI, id)
                .retrieve()
                .bodyToMono(RiderModel.class);
    }

    public Mono<RiderModel> updateSnowboard(Integer riderId, Integer snowboardId)
    {
        return riderWebClient.put()
                .uri(ONE_URI + "/snowboard", riderId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(snowboardId)
                .retrieve()
                .bodyToMono(RiderModel.class);

    }


}
