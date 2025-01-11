package be.vinci.aj.domain;

public class VehiculeFactoryImpl implements VehiculeFactory {
    @Override
    public Vehicule getVehicule() {
        return new VehiculeImpl();
    }
}
