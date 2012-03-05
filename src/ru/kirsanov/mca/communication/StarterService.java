package ru.kirsanov.mca.communication;

import org.tmatesoft.svn.core.SVNException;
import ru.kirsanov.mca.vcs.CheckoutAction;
import ru.kirsanov.mca.vcs.Project;
import ru.kirsanov.mca.vcs.listener.CheckoutListener;
import ru.kirsanov.mca.vcs.listener.VcsCheckoutListener;
import ru.kirsanov.mca.vcs.provider.CheckoutProvider;
import ru.kirsanov.mca.vcs.provider.CheckoutProviderFactory;
import ru.kirsanov.mca.communication.entity.CheckoutActionInfo;
import ru.kirsanov.mca.communication.entity.RepositoryInfo;

import javax.ws.rs.*;

/**
 * User: akirsanov
 * Date: 29.02.12 0:10
 */
@Path("/service")
public class StarterService {
    private VcsCheckoutListener checkoutListener = new CheckoutListener();

    @POST
    @Path("/project")
    @Consumes("application/json")
    public void registerProject(RepositoryInfo info) {
        System.out.println(info);

        if (info == null)
            return;
        try {

            CheckoutProvider checkoutProvider = CheckoutProviderFactory.getInstance().createSVNCheckoutProvider(info);
            CheckoutAction checkoutAction = new CheckoutAction(checkoutProvider);
            Project project = new Project(info.projectName);
            checkoutAction.actionPerformed(project);
        } catch (SVNException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/project")
    @Produces("application/json")
    @Consumes("application/json")
    public CheckoutActionInfo getCheckoutState(@PathParam("info") RepositoryInfo info) {
        if (info == null)
            return null;
        return new CheckoutActionInfo(info.projectName, checkoutListener.getState(new Project(info.projectName)));
    }


    @GET
    @Path("/test")
    @Produces("application/json")
    @Consumes("application/json")
    public RepositoryInfo test() {

        return new RepositoryInfo("url", "projectName", "userName", "pass");
    }

}
