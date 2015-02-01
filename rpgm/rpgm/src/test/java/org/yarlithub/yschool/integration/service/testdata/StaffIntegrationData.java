package org.yarlithub.yschool.integration.service.testdata;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 1/22/2014
 * Time: 8:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class StaffIntegrationData {

    public static List staffData1,staffData2,staffDataUpdate;

    static {
            staffData1 = Arrays.asList(new Object[][]{
                    {"090200u", "alkdlaksjf", "dlsakjfdlkaj alkdjfa",null},
                    {"sdfs", "alkdlaksjf", "dlsakjfdlkaj alkdjfa",null},
                    {"dfs00u", "alkdlaksjf", "dlsakjfdlkaj alkdjfa",null}
            });

        staffData2 = Arrays.asList(new Object[][]{
                {"090100u", "al11kdlaksjf", "dlsakjfdlkaj alkdjfa",null},
                {"sd11fs", "alkdl11aksjf", "dlsakjfdlkaj alkdjfa",null},
                {"dfs1100u", "alkd11laksjf", "dlsakjfdlkaj alkdjfa",null}
        });


        staffDataUpdate = Arrays.asList(new Object[][]{
                {"090200uXXX", "al11kdlaksjf", "dlsakjfdlkaj alkdjfa",null},
                {"sdfsXXX", "alkdl11aksjf", "dlsakjfdlkaj alkdjfa",null},
                {"dfs00uXXX", "alkd11laksjf", "dlsakjfdlkaj alkdjfa",null}
        });
    }
}
