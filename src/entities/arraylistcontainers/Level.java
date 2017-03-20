package entities.arraylistcontainers;

import entities.ArrayListContainer;
import entities.Stock;
import java.util.ArrayList;

public class Level extends ArrayListContainer<Rack, Stock> {
    public Level(Rack rack) {
        super(rack);
    }
}
