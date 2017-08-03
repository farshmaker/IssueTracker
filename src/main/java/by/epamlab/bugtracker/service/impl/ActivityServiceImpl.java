package by.epamlab.bugtracker.service.impl;

import by.epamlab.bugtracker.dao.intefaces.ActivityDAO;
import by.epamlab.bugtracker.model.bean.Activity;
import by.epamlab.bugtracker.service.interfaces.ActivityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDAO activityDAO;

    @Override
    public void addActivity(Activity activity) {
        activityDAO.addActivity(activity);
    }

    public Activity getActivity(int id) {
        return activityDAO.getActivity(id);
    }

    public List<Activity> listActivity() {
        return activityDAO.listActivity();
    }

    public List<Activity> listActivity(int fromIndex, int toIndex) {
        return activityDAO.listActivity(fromIndex, toIndex);
    }

    @Override
    public String getJsonString(List<Activity> activities) {
        List<Map<String, String>> jsonList = new ArrayList<Map<String, String>>();
        for (Activity activity : activities) {
            Map<String, String> jsonObject = new HashMap<String, String>();
            jsonObject.put("date", activity.getDate().toString());
            jsonObject.put("duration", "" + activity.getDuration());
            jsonObject.put("project", activity.getMember().getProject().getName());
            jsonObject.put("login", activity.getMember().getEmployee().getLogin());
            jsonObject.put("comment", activity.getComment());
            jsonList.add(jsonObject);
        }

        String jsonString = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = mapper.writeValueAsString(jsonList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
