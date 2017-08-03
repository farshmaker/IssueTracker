package by.epamlab.bugtracker.dao.intefaces;

import by.epamlab.bugtracker.model.bean.Position;

import java.util.List;

public interface PositionDAO {

    public void addPosition(Position position);

    public Position getById(Integer id);

    public List<Position> listPosition();

    public void removePosition(Integer id);
}
