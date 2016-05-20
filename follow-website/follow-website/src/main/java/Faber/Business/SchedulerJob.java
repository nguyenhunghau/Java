package Faber.Business;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Nguyen Hung Hau
 */
public class SchedulerJob implements Job {

    HanldeUrl hanlde = new HanldeUrl();

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            String url = dataMap.getString("url");
            String user = dataMap.getString("user");
            String type = dataMap.getString("type");
            String result = hanlde.saveWebsite(url, type, user);
            dataMap.put("result", result);
        } catch (Exception e) {

        }
    }

}
