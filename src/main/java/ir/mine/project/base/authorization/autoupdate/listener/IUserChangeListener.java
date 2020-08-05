package ir.mine.project.base.authorization.autoupdate.listener;

import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUser;

public interface IUserChangeListener {

    void userSaved(BaseUser user);

    void userUpdated(BaseUser user);
}
