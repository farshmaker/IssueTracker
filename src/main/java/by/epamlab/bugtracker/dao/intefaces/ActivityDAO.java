package by.epamlab.bugtracker.dao.intefaces;

import by.epamlab.bugtracker.model.bean.Activity;

import java.util.List;

public interface ActivityDAO {

    public void addActivity(Activity activity);

    public Activity getActivity(int id);

    public List<Activity> listActivity();

    public List<Activity> listActivity(int fromIndex, int toIndex);


}
