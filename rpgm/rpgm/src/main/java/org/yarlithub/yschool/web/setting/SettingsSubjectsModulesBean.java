package org.yarlithub.yschool.web.setting;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by jaykrish on 4/5/14.
 */
@ManagedBean
@Scope(value = "session")
@Controller
public class SettingsSubjectsModulesBean {
    String pageNewSubject = "";
    String pageNewModule = "";

    public void setPageNewSubject() {
        this.pageNewSubject = "_newSubject";
    }

    public void setPageNewModule() {
        this.pageNewModule = pageNewModule;
    }
}
