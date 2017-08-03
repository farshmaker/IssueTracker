package by.epamlab.bugtracker.web.converter;

import by.epamlab.bugtracker.model.bean.Position;
import by.epamlab.bugtracker.service.interfaces.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PositionConverter implements Converter<String, Position> {

    @Autowired
    private PositionService positionService;

    public Position convert(String id) {
        return positionService.getById(Integer.parseInt(id));
    }
}
