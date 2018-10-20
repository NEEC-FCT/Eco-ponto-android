package com.neec.fct.ecopontos.GPS;

import android.content.Context;
import android.support.annotation.StringRes;


public interface FunctionalExampleFragment extends ActionBarModel, InfoViewModel, OptionsViewModel {

    /**
     * Method created just once when position menu chosen.
     */
    void onMenuItemSelected();

    boolean onBackPressed();

    String getString(@StringRes int textId);

    Context getContext();

    boolean isMapRestored();

    String getFragmentTag();

}