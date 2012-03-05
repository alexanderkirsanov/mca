package ru.kirsanov.mca.vcs;

import org.junit.Before;
import org.junit.Test;
import org.tmatesoft.svn.core.SVNException;
import ru.kirsanov.mca.vcs.provider.CheckoutProvider;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * User: akirsanov
 * Date: 16.02.12 0:06
 */

public class CheckoutActionTest {
    private CheckoutProvider checkoutProvider;
    private CheckoutAction checkoutAction;
    private Project project;

    @Before
    public void setUp() {
        checkoutProvider = mock(CheckoutProvider.class);
        checkoutAction = new CheckoutAction(checkoutProvider);
        project = new Project("test");
    }

    @Test
    public void testActionPerformed() throws SVNException {
        checkoutAction.actionPerformed(project);
        verify(checkoutProvider, times(1)).doCheckout(VcsManager.getInstance(project).getCompositeCheckoutListener());
    }


}
