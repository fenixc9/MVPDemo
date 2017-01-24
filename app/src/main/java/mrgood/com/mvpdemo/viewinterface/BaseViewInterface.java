package mrgood.com.mvpdemo.viewinterface;

/**
 * Created by Administrator on 2017/1/24 0024.
 */

public interface BaseViewInterface {
    void errorState(Throwable e);
    void emptyState();
    void loadingState();
    void successState(Object...object);
    void toast(String toast);
    void next();
    void back();
}
