package ru.akirsanov.mca.vcs;

import org.junit.Before;
import org.junit.Test;
import ru.akirsanov.mca.vcs.provider.CheckoutProvider;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * User: akirsanov
 * Date: 16.02.12 0:06
 */

public class CheckoutActionTest {
    private CheckoutProvider checkoutProvider;
    private CheckoutAction checkoutAction;
    private String project;

    @Before
    public void prepare() {
        checkoutProvider = mock(CheckoutProvider.class);
        checkoutAction = new CheckoutAction(checkoutProvider);
        project = "test";
    }

    @Test
    public void testActionPerformed() {
        checkoutAction.actionPerformed("test");
        verify(checkoutProvider, times(1)).doCheckout(project, VcsManager.getInstance(project).getCompositeCheckoutListener());
    }


}
