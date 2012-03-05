package ru.kirsanov.mca.vcs.listener;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * User: akirsanov
 * Date: 19.02.12 18:34
 */
public class ListenersServiceTest {
    @Test
    public void testListeners() throws Exception {
        CheckoutListener checkoutListener = new CheckoutListener();
        ListenersService.register(checkoutListener);
        assertEquals(checkoutListener, ListenersService.getListeners().get(0));
    }
}
