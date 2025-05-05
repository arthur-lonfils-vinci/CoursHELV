package be.vinci.aj.domain;

public class TrainFactoryImpl implements TrainFactory {

    @Override
    public Train getTrain() {
        return new TrainImpl();
    }
}
