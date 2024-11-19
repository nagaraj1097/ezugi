package api_genericutility;

import java.util.List;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonUtility extends LowLevelLogs {

    public Object getValueJsonFromBody(Response resp, String key) {
        return resp.jsonPath().get(key);
    }

    public boolean getVerifyDataOnJsonPath(Response resp, String jsonXpath, String key, String expectedData) {
        List<String> list = JsonPath.read(resp.asString(), jsonXpath);
        boolean flag = false;
        for (String str : list) {
            if (str.equals(expectedData)) {
                logPassDetails(key + ":	" + expectedData + " is avilable===>PASS");
                flag = true;
            }
        }
        if (!flag)
            logFailureDetails(key + ":	" + expectedData + " is not avilable===>FAIL");
        return flag;
    }


}
