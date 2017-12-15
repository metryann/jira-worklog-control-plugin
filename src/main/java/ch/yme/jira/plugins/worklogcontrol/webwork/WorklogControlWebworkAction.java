package ch.yme.jira.plugins.worklogcontrol.webwork;

import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.net.Request;
import com.atlassian.sal.api.net.RequestFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Scanned
public class WorklogControlWebworkAction extends JiraWebActionSupport {
    private static final Logger log = LoggerFactory.getLogger(WorklogControlWebworkAction.class);

    private RequestFactory requestFactory;

    public WorklogControlWebworkAction(@ComponentImport RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public String execute() throws Exception {

        log.info("Request Factory init " + (this.requestFactory != null));
        String query = "http://agile.utopix.ch/jira/plugins/servlet/tempo-getWorklog/?dateFrom=2017-01-01&dateTo=2017-12-31&format=xml&diffOnly=false&tempoApiToken=ef1e6aa6-05dc-4903-a22a-365804e5a7db";

        final Request request = requestFactory.createRequest(Request.MethodType.GET, query);
        final String response = request.execute();
        System.out.println(response);

        return "dashboard";
    }
}
