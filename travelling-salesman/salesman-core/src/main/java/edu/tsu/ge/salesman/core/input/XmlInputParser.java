package edu.tsu.ge.salesman.core.input;

import edu.tsu.ge.salesman.core.input.xml.Cities;
import edu.tsu.ge.salesman.core.input.xml.City;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlInputParser implements InputParser {

    private Cities cities;

    /**
     * read .xml file, which contains information regarding cities.
     * City (name, x , y)
     */
    public XmlInputParser(InputStream inputStream) throws Exception {
        try {
            JAXBContext context = JAXBContext.newInstance(Cities.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            cities = (Cities) unmarshaller.unmarshal(inputStream);
        } catch (Throwable t) {
            throw new RuntimeException(t.getMessage(), t);
        }
    }

    /**
     * transform and return cities from xml to cities from model
     *
     * @return list of the cities from the input .xml file
     */
    @Override
    public List<edu.tsu.ge.salesman.core.model.City> getCities() {
        List<edu.tsu.ge.salesman.core.model.City> result = new ArrayList<>();
        if (cities != null) {
            for (City city : cities.getCity()) {
                edu.tsu.ge.salesman.core.model.City c = new edu.tsu.ge.salesman.core.model.City(city.getX(), city.getY(), city.getName());
                result.add(c);
            }
        }
        return result;
    }
}
