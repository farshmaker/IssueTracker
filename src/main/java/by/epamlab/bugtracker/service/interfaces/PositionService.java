package by.epamlab.bugtracker.service.interfaces;

import by.epamlab.bugtracker.model.bean.Position;

import java.util.List;

public interface PositionService {

    public void addPosition(Position position);

    public Position getById(Integer id);

    public List<Position> listPosition();

    public void removePosition(Integer id);
}
