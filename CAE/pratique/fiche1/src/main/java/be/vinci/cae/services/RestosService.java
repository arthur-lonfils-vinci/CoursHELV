package be.vinci.cae.services;

import be.vinci.cae.models.Resto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RestosService {
    public List<Resto> getAllRestos() {

        Resto[] restos = {
                new Resto("Comme Chez Toi", "Française"),
                new Resto("Le Chalet de la Forêt", "Belge"),
                new Resto("La Villa Lorraine", "Française"),
                new Resto("Belga Queen", "Belge"),
                new Resto("Bia Mara", "Fish"),
                new Resto("Aux Armes de Bruxelles", "Belge"),
                new Resto("Noordzee Mer du Nord", "Poisson"),
                new Resto("Fin de Siècle", "Européenne"),
                new Resto("Bon Bon", "Française"),
                new Resto("La Quincaillerie", "Belge"),
                new Resto("Café Georgette", "Belge"),
                new Resto("Amadeus", "Ribs"),
                new Resto("Le Pain Quotidien", "Bio"),
                new Resto("The Sister Brussels Café", "Végétarienne"),
                new Resto("Certo", "Italienne"),
                new Resto("Brugmann", "Française"),
                new Resto("Chez Léon", "Belge"),
                new Resto("Yi Chan", "Asiatique"),
                new Resto("Kamo", "Japonaise"),
                new Resto("Humus x Hortense", "Végétarienne")
        };

        return Arrays.stream(restos).toList();
    }
}
