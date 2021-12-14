package cz.cvut.fit.tjv.fittour.fittourclient.data;

import cz.cvut.fit.tjv.fittour.fittourclient.model.SnowboardDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class SnowboardClient
{
   private final WebClient snowboardWebClient;

   public SnowboardClient()
   {
       snowboardWebClient = WebClient.create("http://localhost:8080/snowboards");
   }

   // Flux - nacita postupne kolekci, aby se napr. vykreslovalo podobne pri nacitani velike stranky
   public Flux<SnowboardDto> fetchAllSnowboards()
   {
       return snowboardWebClient.get()
               .accept(MediaType.APPLICATION_JSON)
               .retrieve()
               .bodyToFlux(SnowboardDto.class);
   }



}
