package by.epamlab.bugtracker.service.interfaces;

import by.epamlab.bugtracker.model.bean.Activity;

import java.util.List;

public interface ActivityService {

    public void addActivity(Activity activity);

    public Activity getActivity(int id);

    public List<Activity> listActivity();

    public List<Activity> listActivity(int fromIndex, int toIndex);

    public String getJsonString(List<Activity> activities);
}
