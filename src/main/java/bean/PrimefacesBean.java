package bean;

import org.primefaces.PrimeFaces;
import org.primefaces.config.PrimeConfiguration;
import org.primefaces.context.PrimeApplicationContext;
import service.RequestProxy;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "PrimefacesBean")
@RequestScoped
public class PrimefacesBean {
    private String theme = "saga";
    private String prompt;
    /**
     * 刷新页面存储的值不清除
     */
    private static String token;
    private String result;
    /**
     * FIXME add
     * init themes items , and gen get method
     *
     * @link index.xhtml line 46
     */


    private List<String> themes = Arrays.asList("saga",
            "arya",
            "luna-amber",
            "luna-blue",
            "luna-green",
            "luna-pink",
            "nova-colored",
            "nova-dark",
            "nova-light",
            "vela");
//    private List<String> themes = Arrays.asList("arya", "luna-amber", "luna-blue", "luna-green", "luna-pink", "nova-colored", "nova-dark", "nova-light", "saga", "vela");

    // Getter and setter for prompt
    public String getPrompt() {
        return prompt;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    // Getter and setter for token
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    // Getter and setter for result
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    // getter and setter for theme
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }

    // getter and setter for themes

    public List<String> getThemes() {
        return themes;
    }

    /**
     * not used
     *
     * @return
     */

    // Methods
    public void generateMethod(String type) {
        switch (type) {
            case "ajax":
                // call page JavaScript
                PrimeFaces.current().executeScript("sendRequest('" + prompt + "','" + token + "')");
                break;
            case "proxy":
                result = RequestProxy.request(prompt, token);
                break;
            default:
                result = "Hello, " + prompt + token + "!";
        }
    }

    /**
     * 用于接收按钮请求,当前功能无需其他处理
     * @Link index.xhtml line 49
     */
    public void useThemeAction() throws Exception {
        // Get PrimeFaces configuration object
        PrimeConfiguration config = PrimeApplicationContext.getCurrentInstance(FacesContext.getCurrentInstance()).getConfig();

        /** begin java  reflect modify final constant (Not a recommended routine) **/
        Field field = PrimeConfiguration.class.getDeclaredField("theme");
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(config, this.getTheme().toLowerCase());
        /** end java  reflect modify final constant **/

        // refresh page apply new theme
        PrimeFaces.current().executeScript("window.location.reload()");
    }
}
