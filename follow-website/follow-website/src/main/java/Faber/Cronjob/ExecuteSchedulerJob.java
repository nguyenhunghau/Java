package Faber.Cronjob;

import Faber.Business.HanldeUrl;
import Faber.DAO.UrlDAO;
import Faber.DTO.UrlDTO;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Nguyen Hung Hau
 */
public class ExecuteSchedulerJob implements Job {

    HanldeUrl hanlde = new HanldeUrl();
    UrlDAO urlDao = new UrlDAO();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            String type = "both";
            List<UrlDTO> listUrl = urlDao.getListUrl();
            for (UrlDTO url : listUrl) {
                if (url.getHtml() == null) {
                    type = "capture";
                }
                if (url.getPc() == null) {
                    type = "html";
                }
                hanlde.saveWebsite(url.getLinkUrl(), type, String.valueOf(url.getIdUser()));
            }
        } catch (Exception e) {
        }
    }

}
