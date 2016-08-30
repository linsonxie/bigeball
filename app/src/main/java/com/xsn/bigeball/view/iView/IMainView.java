package com.xsn.bigeball.view.iView;

import com.xsn.bigeball.model.AllType;

/**
 * Created by XSN on 2016/8/30.
 */
public interface IMainView extends IBaseView {
    void showProgress();

    void hideProgress();

    void showErrorView();

    void showAllTypes(AllType allTypes);

    void showAllNames(String allTypes);
}
