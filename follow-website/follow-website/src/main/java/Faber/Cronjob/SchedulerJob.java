package Faber.Cronjob;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Faber.Business.HanldeUrl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class SchedulerJob implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        Scheduler scheduler;
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            JobDetail job = JobBuilder.newJob(ExecuteSchedulerJob.class)
                    .withIdentity("cronjob").build();
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("cronjob")
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule("0 0 1-5 * * ?"))
                    .build();

            //schedule it
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
