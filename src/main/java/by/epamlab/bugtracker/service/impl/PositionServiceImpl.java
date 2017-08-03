package by.epamlab.bugtracker.service.impl;

import by.epamlab.bugtracker.model.bean.Position;
import by.epamlab.bugtracker.dao.intefaces.PositionDAO;
import by.epamlab.bugtracker.service.interfaces.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDAO positionDAO;

    public void addPosition(Position position) {
        positionDAO.addPosition(position);
    }

    public Position getById(Integer id){
        return positionDAO.getById(id);
    }

    public List<Position> listPosition() {
        return positionDAO.listPosition();
    }

    public void removePosition(Integer id) {
        positionDAO.removePosition(id);
    }
}
