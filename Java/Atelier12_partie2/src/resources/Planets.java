package resources;

import annotations.GET;
import annotations.POST;
import annotations.Path;

public class Planets {

    public static void oneStaticMethod() {
    }

    @GET
    @Path("/planets")
    public void getPlanets() {
        System.out.println("List of all planets");
    }

    @POST
    @Path("/planets")
    public void addPlanet() {
        System.out.println("Saving a new planet");
    }

    private void onePrivateMethod() {
    }

}
